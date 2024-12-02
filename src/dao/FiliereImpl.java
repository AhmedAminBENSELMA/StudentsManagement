package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import metier.Filiere;

public class FiliereImpl implements IFiliere {

	@Override
	public void addFiliere(Filiere f) throws Exception {
		Connection cnx= (Connection) SingletonConnection.getInstance();
		PreparedStatement st= cnx.prepareStatement("insert into filiere (nom) values (?)");
		st.setString(1, f.getNom());
		st.executeUpdate();
		
	}

	@Override
	public Filiere getFiliereByNom(String nom) throws Exception {
		Connection cnx=SingletonConnection.getInstance();
		PreparedStatement st= cnx.prepareStatement("select * from filiere where nom=?");
		st.setString(1, nom);
		ResultSet rs=st.executeQuery();
		rs.next();
		Filiere fl= new Filiere(rs.getInt(1),rs.getString(2));
		return fl;
	}

	@Override
	public List<Filiere> getAllFiliere() throws Exception {
		List<Filiere> liste = new ArrayList<>();
		Connection cnx=SingletonConnection.getInstance();
		PreparedStatement st= cnx.prepareStatement("select * from filiere");
		ResultSet rs=st.executeQuery();
		while (rs.next()) {
			Filiere fl= new Filiere(rs.getInt(1),rs.getString(2));
			liste.add(fl);
		}
		return liste;
	}

}
