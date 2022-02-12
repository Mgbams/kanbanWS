package  fr.orsys.kingsley.kanbanWS.business;

import java.util.Date;
import java.util.List;

// JPA: Java Persistence API
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;

import com.fasterxml.jackson.annotation.JsonIgnore;

// On demande à Java de créer une table Developpeur
@Entity
public class Developpeur {

	// Attributs
	
	@Id // indique la clé primaire pour la table Developpeur
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String prenom;
	
	private String nom;
	
	@Past(message="La date de naissance doit être dans le passé")
	@Temporal(TemporalType.DATE)
	private Date dateNaissance;
	
	// La classe Tache est la classe centrale, on place l'attribut mappedBy sur la classe satellite
	// donc Developpeur
	// Au moment d'ajouter une tache, Spring va automatiquement alimenter la liste de développeurs
	// pour la nouvelle tâche
	@JsonIgnore
	@OneToMany(mappedBy="developpeur")
	private List<Tache> taches;
	
	@Email(message="L''email du développeur doit être au bon format")
	private String email;
	
	private String motDePasse;

	@Transient
	private int nbTachesEnCours;
	
	//@CreditCardNumber
	//private String numeroCarteBleue;
	
	@ManyToMany(mappedBy="developpeurs")
	private List<Projet> projets;
	
	// Constructeurs
	public Developpeur() {
		// TODO Auto-generated constructor stub
	}

	public Developpeur(String prenom, String nom) {
		super();
		this.prenom = prenom;
		this.nom = nom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Date getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	public List<Tache> getTaches() {
		return taches;
	}

	public void setTaches(List<Tache> taches) {
		this.taches = taches;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	public int getNbTachesEnCours() {
		return nbTachesEnCours;
	}

	public void setNbTachesEnCours(int nbTachesEnCours) {
		this.nbTachesEnCours = nbTachesEnCours;
	}


	public List<Projet> getProjets() {
		return projets;
	}

	public void setProjets(List<Projet> projets) {
		this.projets = projets;
	}

	@Override
	public String toString() {
		return "Developpeur [id=" + id + ", prenom=" + prenom + ", nom=" + nom + "]";
	}
	
	
}
