package fr.orsys.kingsley.kanbanWS.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.orsys.kingsley.kanbanWS.business.Colonne;

public interface ColonneDao extends JpaRepository<Colonne, Long> {

	// Requête par dérivation
	List<Colonne> findByNomStartingWith(String filtre);

	// Colonne findByColonneActuelle(String nom);
}
