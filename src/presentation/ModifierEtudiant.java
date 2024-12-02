package presentation;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import dao.EtudiantImpl;
import dao.FiliereImpl;
import metier.Etudiant;
import metier.Filiere;

public class ModifierEtudiant extends JPanel {
	JLabel lid = new JLabel("Id");
	JLabel lnom = new JLabel("Nom");
    JLabel lprenom = new JLabel("Prenom");
    JLabel lfiliere = new JLabel("Filiere");
    JLabel lsexe = new JLabel("Sexe");
    JTextField tid = new JTextField(10);
    JTextField tnom = new JTextField(10);
    JTextField tprenom = new JTextField(10);
    JButton bappliquer = new JButton("Appliquer modification");
    JButton bannuler = new JButton("Annuler");
    JRadioButton bh = new JRadioButton("M");
    JRadioButton bf = new JRadioButton("F");
    ButtonGroup bg = new ButtonGroup();
    JComboBox<Filiere> combfiliere = new JComboBox<Filiere>();
    JPanel pid = new JPanel();
    JPanel pnom = new JPanel();
    JPanel pprenom = new JPanel();
    JPanel pfiliere = new JPanel();
    JPanel psexe = new JPanel();
    JPanel p11 = new JPanel(new GridLayout(3, 2));
    JPanel p12 = new JPanel();
    private EtudiantImpl gestionEtudiant = new EtudiantImpl();
    
    public ModifierEtudiant(Etudiant e) {
    	
    	JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
    
    	setVisible(true);
    	setLayout(new BorderLayout());
    	 bg.add(bf);
         bg.add(bh);
         pid.add(lid);
         pid.add(tid);
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
         p11.add(pid);
         p11.add(pnom);
         p11.add(pprenom);
         p11.add(pfiliere);
         p11.add(psexe);

         

         p12.add(bappliquer);
         p12.add(bannuler);
         
         this.add(p11);
         this.add(p12, BorderLayout.SOUTH);
         tid.setText(Integer.toString(e.getId()));
         tid.setEditable(false);
         tnom.setText(e.getNom());
         tprenom.setText(e.getPrenom());
         combfiliere.setSelectedItem(e.getFiliere());
         if (e.getSexe().equals("M")) {bh.setSelected(true);}
         
         else {bf.setSelected(true);}
         
         
         bappliquer.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				 int id = Integer.parseInt(tid.getText());
				 String nom = tnom.getText();
                 String prenom = tprenom.getText();
                 Filiere filiere = (Filiere) combfiliere.getSelectedItem();
                 String sexe = bh.isSelected() ? "M" : "F";

                 if (nom.isEmpty() || prenom.isEmpty()) {
                     JOptionPane.showMessageDialog(parentFrame, "Veuillez remplir tous les champs obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                     return;
                 }

                 Etudiant nouvelEtudiant = new Etudiant(id,nom, prenom, sexe,filiere);
                 int res = JOptionPane.showConfirmDialog(parentFrame, "voulez vous modifier cet etudiant ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                 if (res == JOptionPane.YES_OPTION) {
                 	try {
                 		gestionEtudiant.modifierEtudiant(nouvelEtudiant);
                 		JOptionPane.showMessageDialog(parentFrame, "Etudiant modifier avec succès.", "succès", JOptionPane.PLAIN_MESSAGE);
                 		JFrame parentFrame1 = (JFrame) SwingUtilities.getWindowAncestor(ModifierEtudiant.this);
                 		parentFrame1.getContentPane().removeAll();
                 		parentFrame1.getContentPane().add(new RechercheGUI());
                        parentFrame1.pack();
                 	}catch (Exception ex) {
 						System.out.println(ex.getMessage()); 
 					}
                     
                 }
				
			}
		});
         
         bannuler.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int res = JOptionPane.showConfirmDialog(parentFrame, "voulez vous annuler cette modification ?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (res == JOptionPane.YES_OPTION) {
                	
                	JFrame parentFrame1 = (JFrame) SwingUtilities.getWindowAncestor(ModifierEtudiant.this);
             		parentFrame1.getContentPane().removeAll();
             		parentFrame1.getContentPane().add(new RechercheGUI());
                    parentFrame1.pack();
                	
                }
				
			}
		});
    }
    

}
