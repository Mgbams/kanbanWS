package  fr.orsys.kingsley.kanbanWS.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.kingsley.kanbanWS.business.TypeTache;

public interface TypeTacheDao extends JpaRepository<TypeTache, Long> {
	
}