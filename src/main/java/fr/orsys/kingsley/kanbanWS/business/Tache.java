package  fr.orsys.kingsley.kanbanWS.business;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Annotation
@Entity
// Pour avoir un nom différent de celui de la classe Java
//@Table(name="task")
public class Tache {

	private final static int NB_CARACTERES_MIN_INTITULE = 5;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) // Auto-increment
	private Long id;

	@Size(min=NB_CARACTERES_MIN_INTITULE, message="L''intitulé de la tâche doit comporter au minimum " + NB_CARACTERES_MIN_INTITULE + " caractères")
	private String intitule;

	//@Temporal(TemporalType.DATE)
	private LocalDateTime dateCreation;

	@Min(value = 1, message = "Le nombre d''heures doit être au moins de 1") // RG9
	@Max(value= 144, message = "Le nombre d''heures doit être au maximum de 144") // RG9
	private int nbHeuresPrevues;

	@JsonIgnore
	private int nbHeuresEffectives;

	@ManyToOne
	//@NotEmpty(message="La liste des développeurs à laquelle une tâche est confiée ne peut être vide")
	//@Size(min=1, message="La liste des développeurs à laquelle une tâche est confiée ne peut être vide") // RG4
	private Developpeur developpeur;

	@ManyToOne
	private TypeTache typeTache;

	// La colonne dans laquelle la tâche se trouve
	@ManyToOne
	private Colonne colonneActuelle;
	
	@ManyToOne
	private Projet projet;
	
	// Surcharge de constructeurs (overload)
	public Tache() {
		setDateCreation(LocalDateTime.now());
	}
	
	public Tache(String intitule) {
		this();
		this.intitule = intitule;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public LocalDateTime getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(LocalDateTime dateCreation) {
		this.dateCreation = dateCreation;
	}

	
	public Developpeur getDeveloppeur() {
		return developpeur;
	}

	public void setDeveloppeur(Developpeur developpeur) {
		this.developpeur = developpeur;
	}

	public TypeTache getTypeTache() {
		return typeTache;
	}

	public void setTypeTache(TypeTache typeTache) {
		this.typeTache = typeTache;
	}

	public Projet getProjet() {
		return projet;
	}

	public void setProjet(Projet projet) {
		this.projet = projet;
	}

	public Colonne getColonneActuelle() {
		return colonneActuelle;
	}

	public void setColonneActuelle(Colonne colonne) {
		this.colonneActuelle = colonne;
	}
	
	public int getNbHeuresPrevues() {
		return nbHeuresPrevues;
	}

	public void setNbHeuresPrevues(int nbHeuresPrevues) {
		this.nbHeuresPrevues = nbHeuresPrevues;
	}

	public int getNbHeuresEffectives() {
		return nbHeuresEffectives;
	}

	public void setNbHeuresEffectives(int nbHeuresEffectives) {
		this.nbHeuresEffectives = nbHeuresEffectives;
	}

	@Override
	public String toString() {
		return "Tache [id=" + id + ", intitule=" + intitule + ", dateCreation=" + dateCreation + ", nbHeuresPrevues="
				+ nbHeuresPrevues + ", nbHeuresEffectives=" + nbHeuresEffectives + ", developpeur=" + developpeur
				+ ", typeTache=" + typeTache + ", colonne=" + colonneActuelle + ", projet=" + projet + "]";
	}

}