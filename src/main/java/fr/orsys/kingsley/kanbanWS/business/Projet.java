package  fr.orsys.kingsley.kanbanWS.business;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Projet {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	// Un projet possède un nom (donnée obligatoire)
	@NotBlank
	@NotNull
	private String nom;
	
	@Column(unique=true)
	//@NotNull
	@Size(min=3, max=3, message="Le code du projet doit contenir exactement 3 caractères")
	private String code;
	
	@ManyToOne
	private Client client;
	
	// Un projet comporte plusieurs taches
	@JsonIgnore
	@OneToMany(mappedBy="projet")
	private List<Tache> taches;
	
	// Un projet a une date de création
	private LocalDateTime dateHeureCreation;
	
	// Un projet a une deadline
	private LocalDateTime dateHeureLivraison;
	
	@ManyToMany
	private List<Developpeur> developpeurs;
	
	public Projet() {
		// TODO Auto-generated constructor stub
	}

	public Projet(String nom, Client client) {
		this.nom = nom;
		this.client = client;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public LocalDateTime getDateHeureCreation() {
		return dateHeureCreation;
	}

	public void setDateHeureCreation(LocalDateTime dateHeureCreation) {
		this.dateHeureCreation = dateHeureCreation;
	}

	public LocalDateTime getDateHeureLivraison() {
		return dateHeureLivraison;
	}

	public void setDateHeureLivraison(LocalDateTime dateHeureLivraison) {
		this.dateHeureLivraison = dateHeureLivraison;
	}

	public List<Developpeur> getDeveloppeurs() {
		return developpeurs;
	}

	public void setDeveloppeurs(List<Developpeur> developpeurs) {
		this.developpeurs = developpeurs;
	}

	@Override
	public String toString() {
		return "Projet [id=" + id + ", nom=" + nom + ", code=" + code + ", client=" + client + ", dateHeureCreation="
				+ dateHeureCreation + ", dateHeureLivraison=" + dateHeureLivraison + "]";
	}

}
