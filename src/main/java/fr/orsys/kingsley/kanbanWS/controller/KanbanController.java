package  fr.orsys.kingsley.kanbanWS.controller;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import fr.orsys.kingsley.kanbanWS.business.Tache;
import fr.orsys.kingsley.kanbanWS.service.ClientService;
import fr.orsys.kingsley.kanbanWS.service.ColonneService;
import fr.orsys.kingsley.kanbanWS.service.DeveloppeurService;
import fr.orsys.kingsley.kanbanWS.service.ProjetService;
import fr.orsys.kingsley.kanbanWS.service.TacheService;
import fr.orsys.kingsley.kanbanWS.service.TypeTacheService;

@Controller
@RequestMapping("/")
@SessionAttributes("tache")
public class KanbanController {

	private final ClientService clientService;
	private final TacheService tacheService;
	private final ColonneService colonneService;
	private final TypeTacheService typeTacheService;
	private final DeveloppeurService developpeurService;
	private final ProjetService projetService;
	
	private final HttpSession httpSession;
	
	private static final int NB_TACHES_PAR_PAGE = 10;
	
	//private Logger logger = (Logger) LoggerFactory.getLogger(this.getClass());

	public KanbanController(ClientService clientService, TacheService tacheService, ColonneService colonneService,
			TypeTacheService typeTacheService, DeveloppeurService developpeurService, ProjetService projetService, HttpSession httpSession) {
		super();
		this.clientService = clientService;
		this.tacheService = tacheService;
		this.colonneService = colonneService;
		this.typeTacheService = typeTacheService;
		this.developpeurService = developpeurService;
		this.projetService = projetService;
		this.httpSession = httpSession;
	}

	@RequestMapping(value = { "/index", "/" })
	public ModelAndView accueil() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("index");
		mav.addObject("taches", tacheService.recupererTaches());
		return mav;
	}
		
	// Cette méthode sera invoquée lorsque qq1 écrit l'URL http://localhost:8080/clients dans
	// la barre d'adresse du navigateur et tape Entrée
	@GetMapping("clients")
	public ModelAndView clientsGet() {
		ModelAndView mav = new ModelAndView();
		// Le ModelAndView va faire appel à une vue nommée clients.jsp
		// Ce n'est pas nécessaire de préciser l'extension .jsp car l'application a été configurée
		// pour ajouter .jsp à la fin du nom de la vue
		mav.setViewName("clients"); 
		// On place dans le compartiment à petites billes les objets clients
		mav.addObject("clients", clientService.recupererClients());
		return mav;
	}
	
	@GetMapping("client")
	public ModelAndView clientGet() {
		ModelAndView mav = new ModelAndView("client");
		return mav;
	}
	
	@PostMapping("client")
	public ModelAndView clientPost(@RequestParam("NOM") String nom) {
		clientService.ajouterClient(nom);
		return clientsGet();
	}
	
	// Cette méthode sera invoquée lorsque qq1 écrit l'URL http://localhost:8080/clients dans
	// la barre d'adresse du navigateur et tape Entrée
	@GetMapping("taches")
	public ModelAndView tachesGet(@PageableDefault(size=NB_TACHES_PAR_PAGE)
									@SortDefault(sort="nbHeuresPrevues", direction = Sort.Direction.DESC)
									Pageable pageable) {
		httpSession.setAttribute("pageable", pageable);
		ModelAndView mav = new ModelAndView("taches");
		mav.addObject("pageDeTaches", tacheService.recupererTaches(pageable));
		return mav;
	}
		
	@GetMapping("tache")
	public ModelAndView tacheGet(@RequestParam(name = "ID", required=false) Long id) {
		ModelAndView mav = new ModelAndView("tache");
		Tache tache = null;
		
		if (id==null) {
			tache = new Tache();
			tache.setColonneActuelle(colonneService.recupererColonnes().get(0));
			tache.setProjet(projetService.recupererProjets().get(0));
		}
		else {
			// On nous a transmis l'id de la tâche à modifier
			tache = tacheService.recupererTache(id);
		}
		// On envoie l'objet tache à la vue, il sera considéré comme le modelAttribute
		mav.addObject("tache", tache);
		mav.addObject("typesTache", typeTacheService.recupererTypesTache());
		mav.addObject("developpeurs", developpeurService.recupererDeveloppeurs());
		return mav;
	}
	
	@PostMapping("tache")
	public ModelAndView tachePost(@Valid @ModelAttribute Tache tache, BindingResult result) {
		if (result.hasErrors()) {
			// Spring a trouvé des erreurs au moment de valider l'objet tache
			ModelAndView mav = tacheGet(tache.getId());
			// On renvoie l'objet métier à la vue (pour éviter à l'internaute de tout resaisir)
			mav.addObject("tache", tache);
			return mav;
		}
		else {
			tacheService.enregistrerTache(tache);
		}
		Pageable pageable = (Pageable) httpSession.getAttribute("pageable");
		return tachesGet(pageable);
	}

	@Scheduled(cron="* * * * * *")
	private void envoyerMail() {
		System.out.println("Envoi de mail");
	}

	@PostConstruct
	private void init() {
		//System.out.println(tacheService.recupererTaches(clientService.recupererClients().get(0)));
	}
}