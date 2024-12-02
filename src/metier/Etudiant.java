package metier;

public class Etudiant {
	
	
	
	private int id;
	private String nom,prenom,sexe;
	private Filiere filiere;
	private static int cpt;
	
	
	
	public Etudiant(String nom, String prenom, String sexe, Filiere filiere) {
		super();
		this.id=cpt++;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.filiere = filiere;
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenom() {
		return prenom;
	}



	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}



	public String getSexe() {
		return sexe;
	}



	public void setSexe(String sexe) {
		this.sexe = sexe;
	}



	public Filiere getFiliere() {
		return filiere;
	}



	public void setFiliere(Filiere filiere) {
		this.filiere = filiere;
	}



	public Etudiant(int id, String nom, String prenom, String sexe, Filiere filiere) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.sexe = sexe;
		this.filiere = filiere;
	}



	@Override
	public String toString() {
		return "Etudiant [nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe + ", filiere=" + filiere + "]";
	}
	
	

}
