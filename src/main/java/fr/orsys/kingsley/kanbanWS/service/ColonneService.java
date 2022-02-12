package  fr.orsys.kingsley.kanbanWS.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import fr.orsys.kingsley.kanbanWS.business.Colonne;

public interface ColonneService {

	/**
	 * Cette méthode permet d'ajouter une colonne
	 * @param nom de la colonne
	 * @return l'objet de type Colonne créé 
	 */
	Colonne ajouterColonne(String nom);

	List<Colonne> recupererColonnes();

	Colonne recupererColonneParId(Long id);

	Pageable recupererColonne(Long colonneId);

	Colonne recupererColonne(String nom);
}
