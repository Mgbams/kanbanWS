package  fr.orsys.kingsley.kanbanWS.service.impl;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.orsys.kingsley.kanbanWS.business.Colonne;
import fr.orsys.kingsley.kanbanWS.dao.ColonneDao;
import fr.orsys.kingsley.kanbanWS.service.ColonneService;

@Service
public class ColonneServiceImpl implements ColonneService {

	private ColonneDao colonneDao;
		
	public ColonneServiceImpl(ColonneDao colonneDao) {
		super();
		this.colonneDao = colonneDao;
	}


	@Override
	public Colonne ajouterColonne(String nom) {
		Colonne colonne = new Colonne(nom);
		// On confie l'objet colonne à la DAO
		colonneDao.save(colonne);
		return colonne;
	}


	@Override
	public List<Colonne> recupererColonnes() {
		return colonneDao.findAll();
	}


	@Override
	public Colonne recupererColonneParId(Long id) {
		return colonneDao.findById(id).orElse(null);
	}


	@Override
	public Pageable recupererColonne(Long colonneId) {
		return (Pageable) colonneDao.findById(colonneId).orElse(null);
	}


	@Override
	public Colonne recupererColonne(String nom) {
		// return colonneDao.findByColonneActuelle(nom);
		return null;
	}

}
