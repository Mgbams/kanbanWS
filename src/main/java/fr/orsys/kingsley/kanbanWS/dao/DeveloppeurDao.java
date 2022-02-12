package  fr.orsys.kingsley.kanbanWS.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.kingsley.kanbanWS.business.Developpeur;

public interface DeveloppeurDao extends JpaRepository<Developpeur, Long> {
	
	List<Developpeur> findByDateNaissanceBetween(LocalDate dateDebut, LocalDate dateFin);
}
