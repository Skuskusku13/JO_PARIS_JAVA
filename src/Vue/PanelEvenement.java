package Vue;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controleur.C_Categorie;
import Controleur.C_Evenement;
import Controleur.Categorie;
import Controleur.Evenement;


public class PanelEvenement extends PanelPrincipal implements ActionListener {

	private JPanel panelEvenement = new JPanel();
	
	private static Categorie uneCategorie = new Categorie();
	
	private JTextField[] textfields = new JTextField[8];
	private String[] jtfNames = {"txtType", "txtDateEvent", "txtNomEvenement", "txtDescription", "txtAdresse", "txtHorraireD", "txtHorraireF", "txtCapacite"};
	private String[] jlText = {"Type", "DateEvent", "NomEvenement", "Description", "Adresse", "Horraire Début", "Horraire Fin", "Capacite"};

	private ArrayList<Categorie> lesCategories = C_Categorie.selectAllCategories();
	private JComboBox<Categorie> combox = new JComboBox<Categorie>();
	
	private JButton[] buttons = new JButton[2];
	private String[] btNames = {"btAnnuler", "btInscription"};
	private String[] btText = {"Annuler", "Inscription"};
	
	public PanelEvenement() {
		super(new Color(255, 255, 255));
		
		this.panelEvenement.setBounds(30, 50, 350, 400);
		this.panelEvenement.setBackground(new Color(255, 255, 255));
		this.panelEvenement.setLayout(new GridLayout(10, 2));
		
		for (int i = 0; i < textfields.length; i++) {
			JLabel jl = new JLabel();
			jl.setText(jlText[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			textfields[i] = new JTextField();
			textfields[i].setName(jtfNames[i]);
			textfields[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			this.panelEvenement.add(jl);
			this.panelEvenement.add(textfields[i]);
		}
		
		
		
		for (Categorie uneCategorie : lesCategories) {
			this.combox.addItem(uneCategorie);
		}
		
		this.panelEvenement.add(new JLabel("Id Catégorie")).setFont(new Font("Paris2024",  Font.ITALIC, 18));;
		this.combox.setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelEvenement.add(this.combox);
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(btNames[i]);
			buttons[i].setText(btText[i]);
			buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));;
			
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(btNames[i]);
			
			this.panelEvenement.add(buttons[i]);
		}
		

		
		this.add(panelEvenement);
	}
	
	public void traitement() {
		String type = this.textfields[0].getText();
		String dateEvent = this.textfields[1].getText();
		String nomEvenement = this.textfields[2].getText();
		String description = this.textfields[3].getText();
		String adresse = this.textfields[4].getText();
		String horraireD = this.textfields[5].getText();
		String horraireF = this.textfields[6].getText();
		int capacite = Integer.parseInt(this.textfields[7].getText());
		int idcategorie = Integer.parseInt(this.textfields[8].getText());

		
		String[] list = {type, dateEvent, nomEvenement, description, adresse, horraireD, horraireF};
		
		boolean champsVide = false;
		
		for (int i = 0; i < list.length; i++) {
			if(list[i].equals("") ) {
				champsVide = true;
			} 
		}
		
		if(champsVide == true) {
			JOptionPane.showMessageDialog(this,
					"Veuillez remplir tous les champs", 
					"Erreur de saisie", 
					JOptionPane.CANCEL_OPTION
			);
		} else {
			Evenement unEvenement = new Evenement(type, dateEvent, nomEvenement, description, adresse, horraireD, horraireF, capacite, idcategorie);
			if(uneCategorie != null) {				
				C_Evenement.insertEvenement(unEvenement);
				JOptionPane.showMessageDialog(this, 
						"Insertion de l'évènement : " +unEvenement.getType() + " résusi"
						);
				//on vide les champs après l'insertion du client
//				this.actionChamps(2);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "btAnnuler":
			
			break;
		case "btValider":
			this.traitement();
			break;
		}
		
	}
	

}
