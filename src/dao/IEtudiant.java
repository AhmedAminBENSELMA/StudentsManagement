package dao;

import java.util.List;

import metier.Etudiant;


public interface IEtudiant {
	
	void ajouterEtudiant(Etudiant e) throws Exception;
	List<Etudiant> rechercherParMC(String mc)throws Exception;
	List<Etudiant> listeDesEtudiants()throws Exception;
	void supprimerEtudiant(String s)throws Exception;
	void modifierEtudiant(Etudiant e)throws Exception;
	Etudiant getEtudiant(int id)throws Exception;
	public List<Etudiant> trierEtudiantsParId(List<Etudiant> liste); 
	public List<Etudiant> trierEtudiantsParNom(List<Etudiant> liste);
	public List<Etudiant> trierEtudiantsParPrenom(List<Etudiant> liste);
	public List<Etudiant> trierEtudiantsParFiliere(List<Etudiant> liste);

}
