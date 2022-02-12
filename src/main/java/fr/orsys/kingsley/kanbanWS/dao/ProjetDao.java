package  fr.orsys.kingsley.kanbanWS.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.kingsley.kanbanWS.business.Projet;

public interface ProjetDao extends JpaRepository<Projet, Long> {

}
