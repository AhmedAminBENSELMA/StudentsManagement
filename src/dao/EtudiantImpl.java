package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Etudiant;
import metier.Filiere;

public class EtudiantImpl implements IEtudiant {

	@Override
	public void ajouterEtudiant(Etudiant e)throws Exception{
		Connection cnx= (Connection) SingletonConnection.getInstance();
		PreparedStatement st= cnx.prepareStatement("insert into etudiant (nom,prenom,sexe,filiere) values (?,?,?,?)");
		st.setString(1, e.getNom());
		st.setString(2, e.getPrenom());
		st.setString(3, e.getSexe());
		st.setInt(4, e.getFiliere().getId());
		st.executeUpdate();

		
	}

	@Override
	public List<Etudiant> rechercherParMC(String mc) throws Exception {
		List<Etudiant> liste = new ArrayList<>();
		Connection cnx= (Connection) SingletonConnection.getInstance();
		PreparedStatement st= cnx.prepareStatement("SELECT e.id, e.nom, e.prenom, e.sexe, f.id, f.nom FROM etudiant e INNER JOIN filiere f ON e.filiere=f.id where "+mc);
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			Filiere fl= new Filiere(rs.getInt(5),rs.getString(6));
			Etudiant e=new Etudiant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),fl);
			liste.add(e);
		}
		return liste;
	}

	@Override
	public List<Etudiant> listeDesEtudiants() throws Exception {
		List<Etudiant> liste = new ArrayList<>();
		Connection cnx= (Connection) SingletonConnection.getInstance();
		PreparedStatement st= cnx.prepareStatement("SELECT e.id, e.nom, e.prenom, e.sexe, f.id, f.nom FROM etudiant e INNER JOIN filiere f ON e.filiere=f.id");
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			
			Filiere fl= new Filiere(rs.getInt(5),rs.getString(6));
			Etudiant e=new Etudiant(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),fl);
			liste.add(e);
		}
		return liste;
	}

	@Override
	public void supprimerEtudiant(String s) throws Exception {
		Connection cnx= (Connection) SingletonConnection.getInstance();
		PreparedStatement st= cnx.prepareStatement("DELETE FROM etudiant WHERE id IN "+s);
		st.executeUpdate();
		
	}

	@Override
	public void modifierEtudiant(Etudiant e) throws Exception {
		Connection cnx= (Connection) SingletonConnection.getInstance();
		PreparedStatement st= cnx.prepareStatement("update etudiant set nom=?,prenom=?,sexe=?,filiere=? where id=?");
		st.setString(1, e.getNom());
		st.setString(2, e.getPrenom());
		st.setString(3, e.getSexe());
		st.setInt(4, e.getFiliere().getId());
		st.setInt(5, e.getId());
		st.executeUpdate();
		
	}

	@Override
	public Etudiant getEtudiant(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Etudiant> trierEtudiantsParId(List<Etudiant> liste) {
		liste.sort((a,b)->Integer.compare(b.getId(), a.getId()));
		return liste;
		
	}

	@Override
	public List<Etudiant> trierEtudiantsParNom(List<Etudiant> liste) {
		liste.sort((a,b)->a.getNom().compareTo(b.getNom()));
		return liste;
		
	}

	@Override
	public List<Etudiant> trierEtudiantsParPrenom(List<Etudiant> liste) {
		liste.sort((a,b)->a.getPrenom().compareTo(b.getPrenom()));
		return liste;
	}

	@Override
	public List<Etudiant> trierEtudiantsParFiliere(List<Etudiant> liste) {
		liste.sort((a,b)->a.getFiliere().getNom().compareTo(b.getFiliere().getNom()));
		return liste;
	}

	
	
}
