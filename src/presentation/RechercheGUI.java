package presentation;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import dao.EtudiantImpl;
import dao.FiliereImpl;
import metier.Etudiant;
import metier.Filiere;

public class RechercheGUI extends JPanel {
	
	
	
	JLabel lrecher = new JLabel("Rechercher par : ");
	JComboBox<String> combrecherche = new JComboBox<String>(new String[] { "nom", "prenom", "filiere" });
	JLabel ltrier = new JLabel("Trier par : ");
	JComboBox<String> combtrier = new JComboBox<String>(new String[] {"id", "nom", "prenom", "filiere" });
	JButton btrier = new JButton("Trier");
	JTextField trecherche = new JTextField(10);
	JButton brechercherMc = new JButton("Rechercher");
	JButton brechercherFil = new JButton("Rechercher");
	JButton bmodifier = new JButton("Modifier");
	JButton bsupprimer = new JButton("Supprimer");
    TableModele tm = new TableModele();
    JTable tableau = new JTable(tm);
    JScrollPane jsp = new JScrollPane(tableau);
    JPanel p1 = new JPanel(new BorderLayout());
    JPanel p11 = new JPanel();
    JPanel p12 = new JPanel(new GridLayout(1, 1));
    JPanel p13 = new JPanel();
    JPanel p2 = new JPanel();
    JComboBox<Filiere> combfiliere = new JComboBox<Filiere>();
    private EtudiantImpl gestionEtudiant = new EtudiantImpl();
    private FiliereImpl gestionFiliere = new FiliereImpl();
    
    public RechercheGUI() {
    	setLayout(new BorderLayout());
    	setVisible(true);
    	
    	
    	p11.add(lrecher);
    	p11.add(combrecherche);
        p11.add(trecherche);
        p11.add(brechercherMc);
        jsp.setPreferredSize(new Dimension(120,150));
    	p12.add(jsp);
        p13.add(ltrier);
        p13.add(combtrier);
        p13.add(btrier);
        p1.add(p11,BorderLayout.NORTH);
        p1.add(p12,BorderLayout.SOUTH);
        p1.add(p13);
        p2.add(bmodifier);
    	p2.add(bsupprimer);
        this.add(p1, BorderLayout.NORTH);
        this.add(p2);
        try{
         	List<Filiere> listeFiliere = new FiliereImpl().getAllFiliere();
         	combfiliere.addItem(new Filiere(1,"All"));
         	for(Filiere f:listeFiliere) {
         		combfiliere.addItem(f);
             }
         	}catch (Exception ex) {
         		 JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
                 return;
     		}
        try {List<Etudiant> resultatRecherche = gestionEtudiant.listeDesEtudiants();
        tm.charger(gestionEtudiant.trierEtudiantsParId(resultatRecherche));
        }catch (Exception ex) {
        	JOptionPane.showMessageDialog(this, ex.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
    
    
    
        brechercherMc.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String mc = trecherche.getText().trim();

            if (!mc.isEmpty()) {
            	String cr = (String)combrecherche.getSelectedItem();
            	try {List<Etudiant> resultatRecherche = gestionEtudiant.rechercherParMC("e."+cr+" like "+"'%"+mc+"%'");
            	tm.charger(gestionEtudiant.trierEtudiantsParId(resultatRecherche));
                }catch (Exception e1) {
                	System.out.println(e1.getMessage());
				}
                
            } else {
            	try {
            	List<Etudiant> resultatRecherche = gestionEtudiant.listeDesEtudiants();
                tm.charger(gestionEtudiant.trierEtudiantsParId(resultatRecherche));
                }catch (Exception e1) {
                	System.out.println(e1.getMessage());
				}
            }
        }
    });
    
    btrier.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        	String trier = (String)combtrier.getSelectedItem();

            if (trier.equals("id")) {
            	tm.charger(gestionEtudiant.trierEtudiantsParId(tm.getListe()));
                
            } else if (trier.equals("nom")) {
            	tm.charger(gestionEtudiant.trierEtudiantsParNom(tm.getListe()));
                
            } else if (trier.equals("prenom")) {
            	tm.charger(gestionEtudiant.trierEtudiantsParPrenom(tm.getListe()));
                
            } else {
            	tm.charger(gestionEtudiant.trierEtudiantsParFiliere(tm.getListe()));
            }
            	
            }
        
    });
    
    
    bsupprimer.addActionListener(e -> {
    	String s="";
        int[] indices = tableau.getSelectedRows();
        if (indices.length == 0) {
            JOptionPane.showMessageDialog(this, "Sélectionner une ligne");
        } else {
        	s=s+"("+tm.getValueAt(indices[0], 0).toString();
        	for (int i=1;i<=indices.length-1;i++) {
        		s=s+","+tm.getValueAt(indices[i], 0).toString();
        	}
        	s=s+")";
        	try {
        		 gestionEtudiant.supprimerEtudiant(s);
        		 List<Etudiant> resultatRecherche = gestionEtudiant.listeDesEtudiants();
                 tm.charger(gestionEtudiant.trierEtudiantsParId(resultatRecherche));
                 JOptionPane.showMessageDialog(RechercheGUI.this, "etudiants supprimer avec succès.", "succès", JOptionPane.PLAIN_MESSAGE);
        	}catch (Exception e1) {
				System.out.println(e1.getMessage());
			}
           
        }
    });
    
    
    bmodifier.addActionListener(e -> {
    	
    	int indice = tableau.getSelectedRow();
        if (indice == -1) {
            JOptionPane.showMessageDialog(this, "Sélectionner une ligne");
        } else {
            	int id = (int) tm.getValueAt(indice, 0);
                String nom = (String) tm.getValueAt(indice, 1);
                String prenom = (String) tm.getValueAt(indice, 2);
                Filiere filiere = (Filiere)tm.getValueAt(indice, 3);
                String sexe = (String) tm.getValueAt(indice,4 );
                this.add(new ModifierEtudiant(new Etudiant(id, nom,prenom,sexe, filiere)), BorderLayout.SOUTH);
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(this);
                parentFrame.pack();
        }
    	
    	
    });
    
combrecherche.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if (combrecherche.getSelectedItem().equals("filiere")) {
				 p11.remove(trecherche);
		         p11.remove(brechercherMc);
		         p11.add(combfiliere);
		         p11.add(brechercherFil);
		         JFrame parentFrame1 = (JFrame) SwingUtilities.getWindowAncestor(RechercheGUI.this);
                 parentFrame1.pack();
				
			}
			else {
				p11.remove(combfiliere);
				p11.remove(brechercherFil);
				p11.add(trecherche);
		        p11.add(brechercherMc);
		         JFrame parentFrame1 = (JFrame) SwingUtilities.getWindowAncestor(RechercheGUI.this);
                 parentFrame1.pack();
			}
			
		}
	});


brechercherFil.addActionListener(new ActionListener() {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Filiere mc = (Filiere)combfiliere.getSelectedItem();

        if (mc!=null & !mc.getNom().equals("All")) {
        	try {List<Etudiant> resultatRecherche = gestionEtudiant.rechercherParMC("f.id ="+mc.getId());
        	tm.charger(gestionEtudiant.trierEtudiantsParId(resultatRecherche));
            }catch (Exception e1) {
            	System.out.println(e1.getMessage());
			}
            
        } else {
        	try {
        	List<Etudiant> resultatRecherche = gestionEtudiant.listeDesEtudiants();
            tm.charger(gestionEtudiant.trierEtudiantsParId(resultatRecherche));
            }catch (Exception e1) {
            	System.out.println(e1.getMessage());
			}
        }
		
		
		
	}
});

    
    
    }
    
}
