package  fr.orsys.kingsley.kanbanWS.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import fr.orsys.kingsley.kanbanWS.business.Client;
import fr.orsys.kingsley.kanbanWS.business.Projet;
import fr.orsys.kingsley.kanbanWS.dao.ProjetDao;
import fr.orsys.kingsley.kanbanWS.service.ProjetService;

@Service
public class ProjetServiceImpl implements ProjetService {

	private ProjetDao projetDao;
	
	public ProjetServiceImpl(ProjetDao projetDao) {
		super();
		this.projetDao = projetDao;
	}

	@Override
	public Projet ajouterProjet(String nom, Client client) {
		return projetDao.save(new Projet(nom, client));
	}

	@Override
	public List<Projet> recupererProjets() {
		return projetDao.findAll();
	}

}
