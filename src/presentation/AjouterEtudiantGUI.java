package presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import dao.EtudiantImpl;
import dao.FiliereImpl;
import metier.Etudiant;
import metier.Filiere;

public class AjouterEtudiantGUI extends JPanel {
	
	
	
	
	
	JLabel lnom = new JLabel("Nom");
    JLabel lprenom = new JLabel("Prenom");
    JLabel lfiliere = new JLabel("Filiere");
    JLabel lsexe = new JLabel("Sexe");
    JTextField tnom = new JTextField(10);
    JTextField tprenom = new JTextField(10);
    JButton bajouter = new JButton("Ajouter");
    JButton bannuler = new JButton("Annuler");
    JRadioButton bh = new JRadioButton("M");
    JRadioButton bf = new JRadioButton("F");
    ButtonGroup bg = new ButtonGroup();
    JComboBox<Filiere> combfiliere = new JComboBox<Filiere>();
    JPanel pnom = new JPanel();
    JPanel pprenom = new JPanel();
    JPanel pfiliere = new JPanel();
    JPanel psexe = new JPanel();
    JPanel p11 = new JPanel(new GridLayout(2, 2));
    JPanel p12 = new JPanel();
    private EtudiantImpl gestionEtudiant = new EtudiantImpl();
    
    public AjouterEtudiantGUI() {
    	
    	 setVisible(true);
    	setLayout(new BorderLayout());
    	 bg.add(bf);
         bg.add(bh);
         pnom.add(lnom);
         pnom.add(tnom);
         pprenom.add(lprenom);
         pprenom.add(tprenom);
         pfiliere.add(lfiliere);
         try{
         	List<Filiere> listeFiliere = new FiliereImpl().getAllFiliere();
         	for(Filiere f:listeFiliere) {
         		combfiliere.addItem(f);
             }
         	}catch (Exception ex) {
     			System.out.println(ex.getMessage()); 
     		}
         pfiliere.add(combfiliere);
         
         psexe.add(lsexe);
         psexe.add(bh);
         psexe.add(bf);

         p11.add(pnom);
         p11.add(pprenom);
         p11.add(pfiliere);
         p11.add(psexe);

         

         p12.add(bajouter);
         p12.add(bannuler);
         
         this.add(p11);
         this.add(p12, BorderLayout.SOUTH);
        
    	
         
         bajouter.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 String nom = tnom.getText();
                 String prenom = tprenom.getText();
                 Filiere filiere = (Filiere) combfiliere.getSelectedItem();
                 String sexe = bh.isSelected() ? "M" : "F";

                 if (nom.isEmpty() || prenom.isEmpty()) {
                     JOptionPane.showMessageDialog(AjouterEtudiantGUI.this, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                     return;
                 }

                 Etudiant nouvelEtudiant = new Etudiant(nom, prenom, sexe,filiere);
                 int res = JOptionPane.showConfirmDialog(AjouterEtudiantGUI.this, "voulez vous ajouter cet etudiant ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                 if (res == JOptionPane.YES_OPTION) {
                 	try {
                 		gestionEtudiant.ajouterEtudiant(nouvelEtudiant);
                 		JOptionPane.showMessageDialog(AjouterEtudiantGUI.this, "Etudiant ajouter avec succès.", "succès", JOptionPane.PLAIN_MESSAGE);
                         tnom.setText("");
                         tprenom.setText("");
                         combfiliere.setSelectedIndex(0);
                         bg.clearSelection();
                 	}catch (Exception ex) {
                 		JOptionPane.showMessageDialog(AjouterEtudiantGUI.this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE); 
 					}
                     
                 }
             }
         });
         
         
         
         
         bannuler.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
             
            	 tnom.setText("");
                 tprenom.setText("");
                 combfiliere.setSelectedIndex(0);
                 bg.clearSelection();
             }
         });
    	
    }
   
}
