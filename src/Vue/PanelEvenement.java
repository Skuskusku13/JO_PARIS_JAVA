package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

import Controleur.C_Categorie;
import Controleur.C_Evenement;
import Controleur.Categorie;
import Controleur.Evenement;
import Controleur.Tableau;


public class PanelEvenement extends PanelPrincipal implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel panelEvenement = new JPanel();
	private JPanel panelTable = new JPanel();
	private Tableau unTableau;
	
	private JTable tableEvent = new JTable();
	
	private SpinnerDateModel modeleSpinnerD= new SpinnerDateModel();
	private SpinnerDateModel modeleSpinnerF= new SpinnerDateModel();
	private JSpinner[] spinners = new JSpinner[2];
	private String[] spinNames = {"btHorraireD", "btHorraireF"};
	private String[] jlSpinText = {"HorraireD", "HorraireF"};
	
	private JTextField[] textfields = new JTextField[6];
	private String[] jtfNames = {"txtType", "txtDateEvent", "txtNomEvenement", "txtDescription", "txtAdresse", "txtCapacite"};
	private String[] jlText = {"Type", "DateEvent", "NomEvenement", "Description", "Adresse", "Capacite"};

	private ArrayList<Categorie> lesCategories = C_Categorie.selectAllCategories();
	private ArrayList<JTextField> listJtext = new ArrayList<JTextField>();
	private JComboBox<String> combox = new JComboBox<String>();
	
	private JButton[] buttons = new JButton[2];
	private String[] btNames = {"btAnnuler", "btValider"};
	private String[] btText = {"Annuler", "Valider"};
	private String[] entetes = {"TYPE", "DATE EVENT", "NOM EVENT", "DESCRIPTION", "ADRESSE", "HORRAIRE DEBUT", "HORRAIRE FIN", "CAPACITE", "ID CATEGORIE"};
	
	public PanelEvenement() {
		super(new Color(255, 255, 255));
		
		this.panelEvenement.setBounds(30, 50, 350, 400);
		this.panelEvenement.setBackground(new Color(255, 255, 255));
		this.panelEvenement.setLayout(new GridLayout(10, 2));
		
		
		// pour pouvoir recuperer les deux dates en évitant tout problèmes, crééer 2modèles spinner
		this.modeleSpinnerD.setCalendarField(java.util.Calendar.MINUTE);
		this.modeleSpinnerF.setCalendarField(java.util.Calendar.MINUTE);
		
		for (int i = 0; i < textfields.length; i++) {
			JLabel jl = new JLabel();
			jl.setText(jlText[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			textfields[i] = new JTextField();
			textfields[i].setName(jtfNames[i]);
			textfields[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			listJtext.add(textfields[i]);
		
			
			if(i == 1) {
				Date date = new Date();
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				textfields[i].setText(dateFormat.format(date));
			}
			
			this.panelEvenement.add(jl);
			this.panelEvenement.add(textfields[i]);
			
			if(i == 5) {
				for (int j = 0; j < spinners.length; j++) {
					JLabel unjl = new JLabel();
					unjl.setText(jlSpinText[j]);
					unjl.setFont(new Font("Paris2024", Font.ITALIC, 18));
					this.panelEvenement.add(unjl);
					
					if(j == 0) {
						spinners[j] = new JSpinner(modeleSpinnerD);
					} else {
						spinners[j] = new JSpinner(modeleSpinnerF);
					}
					spinners[j].setName(spinNames[j]);
					spinners[j].setFont(new Font("Paris2024", Font.ITALIC, 18));
					JSpinner.DateEditor editeurSpinner = new JSpinner.DateEditor(spinners[j], "HH:mm");
					spinners[j].setEditor(editeurSpinner);
					
					spinners[j].removeChangeListener(spinners[j].getChangeListeners()[0]);
			
					this.panelEvenement.add(spinners[j]);
				}
			}
				
		}
		
		for (Categorie uneCategorie : lesCategories) {
		String nomCategorie = uneCategorie.getIdcategorie() + " -- " + uneCategorie.getLibelle();
//		System.out.println(nomCategorie);
		this.combox.addItem(nomCategorie);
		
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
		
		this.panelTable.setBounds(500, 50, 1200, 500);
		this.panelTable.setBackground(new Color(255, 255, 255));
		this.panelTable.setLayout(null);
		
		this.unTableau = new Tableau(this.obtenirEvenements(), entetes);
		this.tableEvent = new JTable(this.unTableau);
		
		JScrollPane unScroll = new JScrollPane(this.tableEvent,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		unScroll.setBounds(0, 0, 600, 350);
		this.panelTable.add(unScroll);
		this.add(panelTable);
	}
	
	
	public Object[][] obtenirEvenements() {
		ArrayList<Evenement> lesEvenements = C_Evenement.selectAllEvenements();
		Object [] [] matrice = new Object [lesEvenements.size()] [9];
		int i  = 0;
		for (Evenement unEvent : lesEvenements) {
			matrice[i][0] = unEvent.getType();
			matrice[i][1] = unEvent.getDateEvent();
			matrice[i][2] = unEvent.getNomEvenement();
			matrice[i][3] = unEvent.getDescription();
			matrice[i][4] = unEvent.getAdresse();
			matrice[i][5] = unEvent.getHorraireD();
			matrice[i][6] = unEvent.getHorraireF();
			matrice[i][7] = unEvent.getCapacite();
			matrice[i][8] = unEvent.getIdcategorie();
			i++;
		}
		return matrice;
	}
	
	
	public void traitement() {
		String type = this.textfields[0].getText();
		String dateEvent = this.textfields[1].getText();
		String nomEvenement = this.textfields[2].getText();
		String description = this.textfields[3].getText();
		String adresse = this.textfields[4].getText();
		int capacite = Integer.parseInt(this.textfields[5].getText());
		
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
		Date valeurSpinD = (Date) spinners[0].getValue();
		Date valeurSpinF = (Date) spinners[1].getValue();
		String horraireD = formatter.format(valeurSpinD);
		String horraireF = formatter.format(valeurSpinF);
		
		int idcategorie = this.combox.getSelectedIndex() + 1;

		
		String[] list = {type, dateEvent, nomEvenement, description, adresse, horraireD, horraireF};
		
		boolean champsVide = false;
		
		for (int i = 0; i < list.length; i++) {
			if(list[i].equals("") || horraireD.equals("") || horraireF.equals("")) {
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
			if(unEvenement != null) {				
				C_Evenement.insertEvenement(unEvenement);
				JOptionPane.showMessageDialog(this, 
						"Insertion de l'évènement : " +unEvenement.getType() + " réussi"
						);
				Object [] ligne = {type, dateEvent, nomEvenement, description, adresse, horraireD, horraireF, capacite, idcategorie};
				this.unTableau.insererLigne(ligne);
				this.viderChamps();
			}
		}
	}
	
	public void viderChamps() {
		for (int i = 0; i < listJtext.size(); i++) {
			textfields[i].setText("");
			
			Date date = new Date();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			textfields[1].setText(dateFormat.format(date));
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "btAnnuler":
			this.viderChamps();
			break;
		case "btValider":
			this.traitement();
			break;
		}
		
	}
	

}
