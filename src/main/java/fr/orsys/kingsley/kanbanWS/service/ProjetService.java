package  fr.orsys.kingsley.kanbanWS.service;

import java.util.List;

import fr.orsys.kingsley.kanbanWS.business.Client;
import fr.orsys.kingsley.kanbanWS.business.Projet;

public interface ProjetService {

	Projet ajouterProjet(String nom, Client client);
	
	List<Projet> recupererProjets();
}
