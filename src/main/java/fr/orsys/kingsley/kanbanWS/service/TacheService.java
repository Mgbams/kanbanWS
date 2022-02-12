package fr.orsys.kingsley.kanbanWS.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import fr.orsys.kingsley.kanbanWS.business.Colonne;
import fr.orsys.kingsley.kanbanWS.business.Developpeur;
import fr.orsys.kingsley.kanbanWS.business.Tache;

public interface TacheService {

	Tache ajouterTache(String intitule);

	List<Tache> recupererTaches();

	Tache recupererTache(Long id);

	void supprimerTache(Tache tache);

	Tache enregistrerTache(Tache tache);

	Page<Tache> recupererTaches(Pageable pageable);

	Tache ajouterTacheEtTypeTache(String intitule, String typeTache);

	Tache findById(String tacheId);

	Page<Tache> recupererTaches(Colonne colonneRecu, PageRequest of);

	Page<Tache> recupererTaches(Colonne colonneRecu);

	Page<Tache> recupererTaches(Pageable recupererColonne, Pageable pageable);

	List<Tache> recupererTachesAFaire(Developpeur recupererDeveloppeur);

	List<Tache> recupererTaches(String intitule);

	int calculerTotalHeuresPrevues(LocalDateTime dateDebut, LocalDateTime dateFin);

	void supprimerTacheInColonneActuelle(Long id);

	Tache deplacerTacheDansColonne(Long idTache, Long idColonne);

	Page<Tache> recupererTachesContenantIntitule(String intituleTache, Pageable pageable);

	

}
