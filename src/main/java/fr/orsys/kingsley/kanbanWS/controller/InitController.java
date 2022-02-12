package  fr.orsys.kingsley.kanbanWS.controller;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import fr.orsys.kingsley.kanbanWS.business.Colonne;
import fr.orsys.kingsley.kanbanWS.business.Developpeur;
import fr.orsys.kingsley.kanbanWS.business.Tache;
import fr.orsys.kingsley.kanbanWS.business.TypeTache;
import fr.orsys.kingsley.kanbanWS.service.ClientService;
import fr.orsys.kingsley.kanbanWS.service.ColonneService;
import fr.orsys.kingsley.kanbanWS.service.DeveloppeurService;
import fr.orsys.kingsley.kanbanWS.service.ProjetService;
import fr.orsys.kingsley.kanbanWS.service.TacheService;
import fr.orsys.kingsley.kanbanWS.service.TypeTacheService;

@Controller
public class InitController {

	private final ClientService clientService;
	private final TacheService tacheService;
	private final DeveloppeurService developpeurService;
	private final ColonneService colonneService;
	private final TypeTacheService typeTacheService;
	private final ProjetService projetService;
	
	public InitController(ClientService clientService, TacheService tacheService, DeveloppeurService developpeurService,
			ColonneService colonneService, TypeTacheService typeTacheService, ProjetService projetService) {
		super();
		this.clientService = clientService;
		this.tacheService = tacheService;
		this.developpeurService = developpeurService;
		this.colonneService = colonneService;
		this.typeTacheService = typeTacheService;
		this.projetService = projetService;
	}


	@PostConstruct
	private void init() {
		
		if (clientService.recupererClients().isEmpty()) {
			for (int i = 1; i <= 20; i++) {
				clientService.ajouterClient("Client " + i);						
			}
		}
		
		Colonne aFaire = null;
		if (colonneService.recupererColonnes().isEmpty()) {
			aFaire = colonneService.ajouterColonne("A faire");
			colonneService.ajouterColonne("En cours");
			colonneService.ajouterColonne("A tester");
			colonneService.ajouterColonne("Terminé");
		} else {
			aFaire = colonneService.recupererColonne("A faire");
		}

		TypeTache fonctionnalite = null;
		if (typeTacheService.recupererTypesTache().isEmpty()) {
			typeTacheService.ajouterTypeTache("Bug", "ffac2d");
			fonctionnalite = typeTacheService.ajouterTypeTache("Fonctionnalité", "009ad7");
			typeTacheService.ajouterTypeTache("Amélioration", "68af27");
			typeTacheService.ajouterTypeTache("Spike", "67319a");
		}
		
		if (developpeurService.recupererDeveloppeurs().isEmpty()) {
			developpeurService.ajouterDeveloppeur("Kierann");
			developpeurService.ajouterDeveloppeur("Kingsley");
			developpeurService.ajouterDeveloppeur("Roman");
			developpeurService.ajouterDeveloppeur("Jon Richard Wissem");
			developpeurService.ajouterDeveloppeur("Alaric");
			developpeurService.ajouterDeveloppeur("Oleg");
			developpeurService.ajouterDeveloppeur("Ahmed");
			developpeurService.ajouterDeveloppeur("Moulaye");
			developpeurService.ajouterDeveloppeur("Hugo");
		}

		if (projetService.recupererProjets().isEmpty()) {
			projetService.ajouterProjet("Projet 1", clientService.recupererClients().get(0));
		}
		
		if (tacheService.recupererTaches().isEmpty()) {
			List<String> intitules = Arrays.asList("Inscription", "Connexion/Déconnexion", "Liste des courses", "Ajout d'une course", "Modification d'une course", "Téléversement de l'avatar");
			for (Developpeur developpeur : developpeurService.recupererDeveloppeurs()) {
				for (String intitule : intitules) {
					Tache tache1 = new Tache();
					tache1.setTypeTache(fonctionnalite);
					tache1.setIntitule(intitule);
					tache1.setColonneActuelle(aFaire);
					tache1.setNbHeuresPrevues(2);
					tache1.setDeveloppeur(developpeur);
					tacheService.enregistrerTache(tache1);					
				}
			}
		}
	}
}