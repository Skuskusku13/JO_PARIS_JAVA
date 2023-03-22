package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controleur.C_Categorie;
import Controleur.Categorie;

public class PanelServices extends PanelPrincipal implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panelServices = new JPanel();
	
	private JTextField[] textfields = new JTextField[5];
	private String[] jtfNames = {"txtLibelle", "txtAdresse", "txtPrix", "txtTel", "txtEmail"};
	private String[] jlNames = {"Libelle", "Adresse", "Prix", "Telephone", "Email"};
//	private String[] entetes = {"NOM", "EMAIL", "TEL"};
	
	private JButton[] buttons = new JButton[2];
	private String[] btNames = {"btAnnuler", "btInscription"};
	private String[] btText = {"Annuler", "Inscription"};
	
	private JComboBox<String> combox = new JComboBox<String>();
	private ArrayList<Categorie> lesCategories = C_Categorie.selectAllCategories();
	
	public PanelServices() {
		super(new Color(255, 255, 255));
		
		this.panelServices.setBounds(30, 50, 350, 400);
		this.panelServices.setBackground(new Color(255, 255, 255));
		this.panelServices.setLayout(new GridLayout(7, 2));
		
		
		for (int i = 0; i < jtfNames.length; i++) {
			textfields[i] = new JTextField();
			textfields[i].setName(jtfNames[i]);
			textfields[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			JLabel jl = new JLabel();
			jl.setText(jlNames[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			this.panelServices.add(jl);
			this.panelServices.add(textfields[i]);
		}
		
		for (Categorie uneCategorie : lesCategories) {
			String nomCategorie = uneCategorie.getIdcategorie() + " -- " + uneCategorie.getLibelle();
	//		System.out.println(nomCategorie);
			this.combox.addItem(nomCategorie);
		}
		this.panelServices.add(new JLabel("Types Services")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelServices.add(combox);
		
		for (int i = 0; i < btNames.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(btNames[i]);
			buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			buttons[i].setText(btText[i]);
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(btNames[i]);
			
			this.panelServices.add(buttons[i]);
		}
		
		this.add(panelServices);
	}




	@Override
	public void actionPerformed(ActionEvent e) {
//		String command = e.getActionCommand();
//		switch (command) {
//		case "btAnnuler":
//			this.viderChamps();
//			break;
//		case "btValider":
//			this.traitement();
//			break;
//		}
		
		
	}

}
