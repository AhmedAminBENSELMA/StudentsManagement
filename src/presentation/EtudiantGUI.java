package presentation;


import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EtudiantGUI extends JFrame {

    JMenuBar menuBar = new JMenuBar();
    JMenuItem rechercherItem = new JMenuItem("Rechercher");
    JMenuItem ajouterEtudiantItem = new JMenuItem("Ajouter étudiant");
    JMenuItem ajouterFiliereItem = new JMenuItem("Ajouter filière");
    JPanel cardPanel = new JPanel();
    Border selectedBorder = BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.GRAY),BorderFactory.createEmptyBorder(2, 2, 2, 2));

    public EtudiantGUI() {
        super("GestionEtudiant");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        menuBar.add(rechercherItem);
        menuBar.add(ajouterEtudiantItem);
        menuBar.add(ajouterFiliereItem);
        setJMenuBar(menuBar);


        cardPanel.add(new RechercheGUI());
        add(cardPanel);
        rechercherItem.setBorder(selectedBorder);

        pack();
        ajouterEtudiantItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardPanel.removeAll();
                cardPanel.add(new AjouterEtudiantGUI());
                ajouterEtudiantItem.setBorder(selectedBorder);
                rechercherItem.setBorder(null);
                ajouterFiliereItem.setBorder(null);
                pack();
            }
        });
        
        
        ajouterFiliereItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	cardPanel.removeAll();
                cardPanel.add(new AjouterFiliereGUI());
                ajouterFiliereItem.setBorder(selectedBorder);
                rechercherItem.setBorder(null);
                ajouterEtudiantItem.setBorder(null);
                pack();
            }
        });

        rechercherItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	cardPanel.removeAll();
                cardPanel.add(new RechercheGUI());
                rechercherItem.setBorder(selectedBorder);
                ajouterEtudiantItem.setBorder(null);
                ajouterFiliereItem.setBorder(null);
                pack();
            }
        });
    }

    public static void main(String[] args) {
        new EtudiantGUI();
    }
}
