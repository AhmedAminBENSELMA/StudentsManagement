package dao;

import java.util.List;

import metier.Filiere;

public interface IFiliere {
	void addFiliere(Filiere f)throws Exception;
	Filiere getFiliereByNom(String nom)throws Exception;
	List<Filiere> getAllFiliere()throws Exception;

}
