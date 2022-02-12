package  fr.orsys.kingsley.kanbanWS.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.kingsley.kanbanWS.business.Client;

public interface ClientDao extends JpaRepository<Client, Long> {

}
