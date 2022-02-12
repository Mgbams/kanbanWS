package fr.orsys.kingsley.kanbanWS.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.orsys.kingsley.kanbanWS.business.Colonne;
import fr.orsys.kingsley.kanbanWS.business.Developpeur;
import fr.orsys.kingsley.kanbanWS.business.Tache;
import fr.orsys.kingsley.kanbanWS.dao.TacheDao;
import fr.orsys.kingsley.kanbanWS.service.ColonneService;
import fr.orsys.kingsley.kanbanWS.service.DeveloppeurService;
import fr.orsys.kingsley.kanbanWS.service.ProjetService;
import fr.orsys.kingsley.kanbanWS.service.TacheService;
import fr.orsys.kingsley.kanbanWS.service.TypeTacheService;

@Service
public class TacheServiceImpl implements TacheService {

	private final TacheDao tacheDao;
	private final DeveloppeurService developpeurService;
	private final ColonneService colonneService;
	private final TypeTacheService typeTacheService;
	private final ProjetService projetService;

	private static Random random = new Random();

	public TacheServiceImpl(TacheDao tacheDao, DeveloppeurService developpeurService, ColonneService colonneService,
			TypeTacheService typeTacheService, ProjetService projetService) {
		super();
		this.tacheDao = tacheDao;
		this.developpeurService = developpeurService;
		this.colonneService = colonneService;
		this.typeTacheService = typeTacheService;
		this.projetService = projetService;
	}

	@Override
	public Tache ajouterTache(String intitule) {
		Tache tache = new Tache(intitule);
		tache.setDateCreation(null);
		tache.setProjet(projetService.recupererProjets().get(0));
		tache.setNbHeuresPrevues(1 + random.nextInt(144));
		tache.setColonneActuelle(colonneService.recupererColonnes().get(0));
		tache.setTypeTache(
				typeTacheService.recupererTypesTache().get(random.nextInt((int) typeTacheService.compterTypesTache())));
		// On choisit un développeur au hasard
		tache.setDeveloppeur(developpeurService.recupererDeveloppeurs()
				.get(random.nextInt((int) developpeurService.compterDeveloppeurs())));
		tacheDao.save(tache);
		return tache;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tache> recupererTaches() {
		return tacheDao.findAll();
	}

	/**
	 * Cette méthode récupère une tâche dont l'id est donné en paramètre
	 */
	@Override
	@Transactional(readOnly = true)
	public Tache recupererTache(Long id) {
		// Avant on écrivait :
		// if (tacheDao.getById(id)!=null) {
		// return tacheDao.getById(id);
		// }
		// else {
		// return null;
		// }
		return tacheDao.findById(id).orElse(null);
	}

	@Override
	public void supprimerTache(Tache tache) {
		tacheDao.delete(tache);
	}

	@Override
	public Tache enregistrerTache(Tache tache) {
		return tacheDao.save(tache);
	}

	@Override
	public Page<Tache> recupererTaches(Pageable pageable) {
		return tacheDao.findAll(pageable);
	}

	@Override
	public Tache ajouterTacheEtTypeTache(String intitule, String typeTache) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tache findById(String tacheId) {
		return tacheDao.findById(tacheId);
	}

	@Override
	public Page<Tache> recupererTaches(Colonne colonneRecu, PageRequest pageRequest) {
		// return tacheDao.recupererTaches(colonneRecu, pageRequest);
		return null;
	}

	@Override
	public Page<Tache> recupererTaches(Colonne colonneRecu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Tache> recupererTaches(Pageable recupererColonne, Pageable pageable) {
		// return tacheDao.findByColonne(recupererColonne, pageable);
		return null;
	}

	@Override
	public List<Tache> recupererTaches(String intitule) {
		return tacheDao.findByIntituleContaining(intitule);
	}

	@Override
	public List<Tache> recupererTachesAFaire(Developpeur developpeur) {
		return tacheDao.findByDeveloppeurAndColonneActuelle(developpeur, colonneService.recupererColonne("A faire"));
	}

	@Override
	public int calculerTotalHeuresPrevues(LocalDateTime dateDebut, LocalDateTime dateFin) {
		return tacheDao.findSumHeuresPrevues(dateDebut, dateFin);
	}

	@Override
	public void supprimerTacheInColonneActuelle(Long id) {
		Colonne colonne = colonneService.recupererColonneParId(id);
		List<Tache> taches = tacheDao.findByColonneActuelle(colonne);

		for (Tache tache : taches) {
			tacheDao.delete(tache);
		}
	}

	@Override
	public Tache deplacerTacheDansColonne(Long idTache, Long idColonne) {
		Colonne colonne = colonneService.recupererColonneParId(idColonne);
		Tache tache = recupererTache(idTache);

		tache.setColonneActuelle(colonne);
		enregistrerTache(tache);
		return tache;
	}

	@Override
	public Page<Tache> recupererTachesContenantIntitule(String intitule, Pageable pageable) {
		return tacheDao.findAllByIntituleContaining(intitule, pageable);
	}

//	@Override
//	public void supprimerTaches(Long colonneId) {
//		tacheDao.deleteByColonneActuelle(colonneService.recupererColonne(colonneId));
//	}

}