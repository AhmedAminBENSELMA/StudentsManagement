package metier;

public class Filiere {
	
	
	public Filiere(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
	}
	public Filiere(String nom) {
		super();
		this.nom = nom;
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
	
	
	
	@Override
	public String toString() {
		return nom;
	}



	private int id;
	private String nom ;
	

}
