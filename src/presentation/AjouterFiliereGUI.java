package presentation;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.EtudiantImpl;
import dao.FiliereImpl;
import metier.Etudiant;
import metier.Filiere;

public class AjouterFiliereGUI extends JPanel {
	
	
	
	JLabel lnom = new JLabel("Nom");
	JTextField tnom = new JTextField(10);
	JButton bajouter = new JButton("Ajouter");
    JButton bannuler = new JButton("Annuler");
    JPanel pnom = new JPanel();
    JPanel p12 = new JPanel();
    private FiliereImpl gestionFiliere = new FiliereImpl();
    
    
    public AjouterFiliereGUI(){
    	
    	 setLayout(new BorderLayout());
    	 setVisible(true);
    	 pnom.add(lnom);
         pnom.add(tnom);
         p12.add(bajouter);
         p12.add(bannuler);
         add(pnom,BorderLayout.NORTH);
         add(p12);
         
         
         
         bajouter.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String nom = tnom.getText();

                 if (nom.isEmpty() ){
                     JOptionPane.showMessageDialog(AjouterFiliereGUI.this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                     return;
                 }

                 Filiere nouvelFiliere = new Filiere(nom);
                 int res = JOptionPane.showConfirmDialog(AjouterFiliereGUI.this, "voulez vous ajouter cet filiere ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                 if (res == JOptionPane.YES_OPTION) {
                 	try {
                 		gestionFiliere.addFiliere(nouvelFiliere);
                 		JOptionPane.showMessageDialog(AjouterFiliereGUI.this, "filiere ajouter avec succès.", "succès", JOptionPane.PLAIN_MESSAGE);
                         tnom.setText("");
                 	}catch (Exception ex) {
 						System.out.println(ex.getMessage()); 
 					}
                     
                 }
             }
         });
         
         bannuler.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
             
            	 tnom.setText("");
             }
         });
         
         
    }

}
