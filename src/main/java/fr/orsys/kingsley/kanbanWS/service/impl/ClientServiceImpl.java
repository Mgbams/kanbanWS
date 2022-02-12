package  fr.orsys.kingsley.kanbanWS.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import fr.orsys.kingsley.kanbanWS.business.Client;
import fr.orsys.kingsley.kanbanWS.dao.ClientDao;
import fr.orsys.kingsley.kanbanWS.service.ClientService;
// On indique à Spring que cette classe appartient à la couche de service
@Service
public class ClientServiceImpl implements ClientService {

	// Le service a besoin de ClientDao
	private ClientDao clientDao;
	
	// Injection de dépendances par constructeur
	public ClientServiceImpl(ClientDao clientDao) {
		super();
		this.clientDao = clientDao;
	}
	
	@Override
	public Client ajouterClient(final String nom) {
		Client client = new Client();
		client.setNom(nom);
		// On confie l'objet métier à la DAO
		clientDao.save(client);
		return client;
	}

	@Override
	public List<Client> recupererClients() {
		return clientDao.findAll();
	}

	@Override
	public Page<Client> recupererClients(Pageable pageable) {
		return clientDao.findAll(pageable);
	}

}