package  fr.orsys.kingsley.kanbanWS.service;

import java.util.List;

import fr.orsys.kingsley.kanbanWS.business.Developpeur;

public interface DeveloppeurService {

	Developpeur ajouterDeveloppeur(String nom, String prenom);

	Developpeur ajouterDeveloppeur(String string);

	List<Developpeur> recupererDeveloppeurs();

	long compterDeveloppeurs();

	Developpeur recupererDeveloppeur(Long id);

}
