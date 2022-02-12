package  fr.orsys.kingsley.kanbanWS.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fr.orsys.kingsley.kanbanWS.business.Client;

public interface ClientService {

	/**
	 * Cette méthode ajoute un client avec le nom donné en paramètre
	 * 
	 * @param nom du client à ajouter
	 * @return un objet de type Client correspondant à l'enregistrement en base
	 */
	Client ajouterClient(String nom);
	
	/**
	 * Cette méthode renvoie la liste exhaustive des clients en base
	 * 
	 * @return une liste de clients
	 */
	List<Client> recupererClients();
	
	Page<Client> recupererClients(Pageable pageable);
}
