package  fr.orsys.kingsley.kanbanWS.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.kingsley.kanbanWS.business.TypeTache;
import fr.orsys.kingsley.kanbanWS.dao.TypeTacheDao;
import fr.orsys.kingsley.kanbanWS.service.TypeTacheService;

@Service
public class TypeTacheServiceImpl implements TypeTacheService {

	private TypeTacheDao typeTacheDao;
	
	public TypeTacheServiceImpl(TypeTacheDao typeTacheDao) {
		super();
		this.typeTacheDao = typeTacheDao;
	}
 
	@Override
	public List<TypeTache> recupererTypesTache() {
		return typeTacheDao.findAll();
	}

	@Override
	public TypeTache ajouterTypeTache(String nom, String couleur) {
		return typeTacheDao.save(new TypeTache(nom, couleur));
	}

	@Override
	public long compterTypesTache() {
		return typeTacheDao.count();
	}

}
