package fr.orsys.kingsley.kanbanWS.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.orsys.kingsley.kanbanWS.business.Colonne;
import fr.orsys.kingsley.kanbanWS.business.Tache;
import fr.orsys.kingsley.kanbanWS.service.ColonneService;
import fr.orsys.kingsley.kanbanWS.service.DeveloppeurService;
import fr.orsys.kingsley.kanbanWS.service.TacheService;

@RestController
@RequestMapping("/api")
public class KanbanRestController {
	private final TacheService tacheService;
	private final ColonneService colonneService;
	private final DeveloppeurService developpeurService;

	public KanbanRestController(TacheService tacheService, ColonneService colonneService,
			DeveloppeurService developpeurService) {
		super();
		this.tacheService = tacheService;
		this.colonneService = colonneService;
		this.developpeurService = developpeurService;
	}

	@GetMapping("/")
	public List<Tache> getTaches() {
		return tacheService.recupererTaches();
	}

	// B) une méthode permettant d’obtenir toutes les informations sur une tâche
	@GetMapping("/taches/{id}")
	public Tache recupererTache(@PathVariable Long id) {
		return tacheService.recupererTache(id);
	}

	/**
	 * Cette méthode permet d'ajouter une tâche via le service Web
	 * 
	 * @return la nouvelle tâche
	 */
	@PostMapping("/taches/{intitule}")
	public Tache ajouterTache(@PathVariable String intitule) {
		System.out.println("Demande d'ajout d'une tâche");
		Tache tache = tacheService.ajouterTache(intitule);
		return tache;
	}

	/**
	 * Cette methode permet d'ajouter une tâche et une description
	 * 
	 * @param intitule
	 * @param description
	 * @return
	 */
	@PostMapping("/taches/{intitule}/{typeTache}")
	public Tache ajouterTacheEtTypeTache(@PathVariable String intitule, @PathVariable String typeTache) {
		System.out.println("Demande d'ajout d'une tâche et type tache");
		Tache tache = tacheService.ajouterTacheEtTypeTache(intitule, typeTache);
		return tache;
	}

	// C) une méthode qui permet de mettre à jour l’intitulé d’une tâche dont l’id
	// est précisé dans
	// l’URL

	@PatchMapping("/taches/{intitule}/{idtache}")
	public Tache ajouterTacheWithTheme(@PathVariable String intitule, @PathVariable Long idtache) {
		Tache tache = tacheService.recupererTache(idtache);
		tache.setIntitule(intitule);
		return tacheService.enregistrerTache(tache);
	}

	// D) une méthode permettant de supprimer une tâche en précisant son id
	@DeleteMapping("/taches/{idTache}")
	public String supprimerTache(@PathVariable Long idTache) {
		Tache tache = tacheService.recupererTache(idTache);
		tacheService.supprimerTache(tache);
		return "Votre tache a bien été supprimer";
	}

	/*
	 * E) une méthode permettant d’obtenir une page de toutes les tâches placées
	 * dans une colonne
	 */
	@GetMapping("/colonnes/{colonneId}/taches?page={page}&size={size}")
	public Page<Tache> recupererTachesColonne(@PathVariable Long colonneId, @PageableDefault Pageable pageable) {
		return tacheService.recupererTaches(colonneService.recupererColonne(colonneId), pageable);
	}

	@GetMapping("/colonnes/{id}/taches")
	public Page<Tache> recupererListTacheParColonne(@PathVariable Long id, @RequestParam Integer page,
			@RequestParam Integer size) {
		Colonne colonneRecu = colonneService.recupererColonneParId(id);
		Page<Tache> taches = tacheService.recupererTaches(colonneRecu, PageRequest.of(page, size));
		return taches;
	}

	/*
	 * F) une méthode permettant d’obtenir les tâches ayant le statut « à faire » et
	 * confiées à un développeur en particulier
	 */
	@GetMapping("/developpeurs/{id}/tachesAFaire")
	public List<Tache> recupererTachesAFaireDeveloppeur(@PathVariable Long id) {
		return tacheService.recupererTachesAFaire(developpeurService.recupererDeveloppeur(id));
	}

	/*
	 * G) une méthode permettant d’obtenir toutes les tâches dont l’intitulé
	 * contient le mot précisé dans l’URL
	 */
	@GetMapping("/taches")
	public Page<Tache> recupererListTaches(@RequestParam(defaultValue = "", name = "intitule") String intituleTache,
			@PageableDefault Pageable pageable) {
		return tacheService.recupererTachesContenantIntitule(intituleTache, pageable);
	}

	/*
	 * H) une méthode permettant de déterminer le total des heures prévues pour les
	 * tâchées créées entre deux dates données en paramètre
	 */

	@GetMapping("/totalHeuresPrevues")
	public int calculerTotalHeuresPrevues(@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate dateDebut,
			@RequestParam @DateTimeFormat(iso = ISO.DATE) LocalDate dateFin) {
		return tacheService.calculerTotalHeuresPrevues(LocalDateTime.of(dateDebut, LocalTime.MIN),
				LocalDateTime.of(dateFin, LocalTime.MIN));
	}

	// I) une méthode permettant de supprimer toutes les tâches d’une colonne
	@DeleteMapping("/supprimer/tacheInColonne/{id}")
	public String supprimerToutesTachesColonne(@PathVariable Long id) {
		tacheService.supprimerTacheInColonneActuelle(id);
		return "Colonne supprimer avec succès";
	}

//	@Transactional //mauvaise pratique, à placer dans le service
//	@DeleteMapping("/viderColonne/{id}")
//	public boolean supprimerTaches(@PathVariable Long id) {
//	    tacheService.supprimerTaches(id);
//	    return true;
//	}

	/*
	 * J) une méthode permettant de gérer le déplacement d’une tâche effectué sur le
	 * tableau Kanban de la page Web de l’application
	 */
	@PutMapping("/taches/deplacer")
	public Tache deplacerDansColonneTacheById(@RequestParam(name = "ID_TACHE") Long idTache,
			@RequestParam(name = "ID_COLONNE") Long idColonne) {
		Tache tache = tacheService.deplacerTacheDansColonne(idTache, idColonne);
		return tache;
	}
}
