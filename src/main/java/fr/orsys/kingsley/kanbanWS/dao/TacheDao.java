package  fr.orsys.kingsley.kanbanWS.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fr.orsys.kingsley.kanbanWS.business.Colonne;
import fr.orsys.kingsley.kanbanWS.business.Developpeur;
import fr.orsys.kingsley.kanbanWS.business.Tache;
public interface TacheDao extends JpaRepository<Tache, Long> {

	List<Tache> findTacheByIntituleStartingWith(String intitule);
	
	// La méthode est transformée par Spring Data en requête SQL
	List<Tache> findTacheByDateCreationBetween(LocalDate dateDebut, LocalDate dateFin);
	
	List<Tache> findByDateCreationBetweenAndIntituleStartingWith(LocalDate dateDebut, LocalDate dateFin, String filtre);
	
	Page<Tache> findByDateCreationBetweenAndIntituleStartingWith(LocalDate dateDebut, LocalDate dateFin, String filtre, Pageable pageable);
	
	List<Tache> findTop20ByNbHeuresPrevuesGreaterThan(Integer valeur);
	
	Page<Tache> findTop20ByNbHeuresPrevuesGreaterThan(Integer valeur, Pageable pageable);

	Tache findById(String tacheId);

	List<Tache> findByIntituleContaining(String intitule);
	
	//Coalesce retourne le premier paramètre non null
	@Query("SELECT COALESCE(SUM(t.nbHeuresPrevues),0) FROM Tache t WHERE t.dateCreation BETWEEN :dateDebut AND :dateFin")
	int findSumHeuresPrevues(LocalDateTime dateDebut, LocalDateTime dateFin);

	List<Tache> findByColonneActuelle(Colonne colonne);

	List<Tache> findByDeveloppeurAndColonneActuelle(Developpeur developpeur, Colonne recupererColonne);

	Page<Tache> findAllByIntituleContaining(String intitule, Pageable pageable);
	
	//void deleteByColonneActuelle(Colonne colonne);
	
}