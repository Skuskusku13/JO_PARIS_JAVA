package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.C_ClientPart;
import Controleur.ClientPart;
import Controleur.Tableau;

public class PanelClientPart extends PanelPrincipal implements ActionListener{
	
	private JPanel panelInscriptionPart = new JPanel();
	private JPanel panelTable = new JPanel();
	private JTable tablePart = new JTable();
	
	private Tableau unTableau;
	
	private JTextField[] textfields = new JTextField[4];
	private String[] jtfNames = {"txtNom", "txtPrenom", "txtEmail", "txtTel"};
	private String[] jlNames = {"Nom", "Prenom", "Email", "Tel"};
	private String[] entetes = {"NOM", "PRENOM", "EMAIL", "TEL"};
	
	private JPasswordField txtMdp = new JPasswordField();
	
	private JButton[] buttons = new JButton[2];
	private String[] btNames = {"btAnnuler", "btInscription"};
	private String[] btText = {"Annuler", "Inscription"};
	
	
	private static final long serialVersionUID = 1L;

	public PanelClientPart() {
		super(new Color(255, 255, 255));
		
		this.panelInscriptionPart.setBounds(30, 50, 350, 400);
		this.panelInscriptionPart.setBackground(new Color(255, 255, 255));
		this.panelInscriptionPart.setLayout(new GridLayout(6, 2));
		
		for (int i = 0; i < textfields.length; i++) {
			textfields[i] = new JTextField();
			textfields[i].setName(jtfNames[i]);
			textfields[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			JLabel jl = new JLabel(jlNames[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			this.panelInscriptionPart.add(jl);
			this.panelInscriptionPart.add(textfields[i]);
		}
		
		// ajout du JPasswordField()
		this.panelInscriptionPart.add(new JLabel("Password")).setFont(new Font("Paris2024", Font.ITALIC, 18));;
		this.panelInscriptionPart.add(txtMdp);
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(btNames[i]);
			buttons[i].setText(btText[i]);
			buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(btNames[i]);
			
			this.panelInscriptionPart.add(buttons[i]);
		}
		

		this.add(panelInscriptionPart);
		
		this.panelTable.setBounds(500, 50, 600, 500);
		this.panelTable.setBackground(new Color(255, 255, 255));
		this.panelTable.setLayout(null);
		
		
		this.unTableau = new Tableau(this.obtenirClientPart(), entetes);
		this.tablePart = new JTable(this.unTableau);
		 
		
		JScrollPane unScroll = new JScrollPane(this.tablePart);
		unScroll.setBounds(0, 0, 600, 350);
		this.panelTable.add(unScroll);
		this.add(panelTable);
	}
	
	public void actionChamps(int choix) {		
		switch (choix) {
		case 1: 
			this.traitement();
			break;
		case 2:
			for (int i = 0; i < textfields.length; i++) {
				textfields[i].setText("");
			}
			this.txtMdp.setText("");
			break;
		}
	}
	
	public Object [][] obtenirClientPart() {
		ArrayList<ClientPart> lesClientsPart = C_ClientPart.selectAllClientsPart();
		Object [][] matrice = new Object[lesClientsPart.size()][4];
		int i = 0;
		for (ClientPart unClient : lesClientsPart) {
			matrice[i][0] = unClient.getNom();
			matrice[i][1] = unClient.getPrenom();
			matrice[i][2] = unClient.getEmail();
			matrice[i][3] = unClient.getTel();
			i++;
		}
		
		
		return matrice;
	}
	
	public void traitement() {
		String nom = this.textfields[0].getText();
		String prenom = this.textfields[1].getText();
		String email = this.textfields[2].getText();
		String tel = this.textfields[3].getText();
		String role = "clientPart";
		String mdp = new String(this.txtMdp.getPassword());
		
		String[] list = {nom, email, mdp, tel, role, prenom};
		
		boolean champsVide = false;
		
		for (int i = 0; i < list.length; i++) {
			if(list[i].equals("")) {
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
			ClientPart unClientPart = new ClientPart(nom, email, mdp, tel, role, prenom);
			if(unClientPart != null) {				
				C_ClientPart.insertClientPart(unClientPart);
				JOptionPane.showMessageDialog(this, 
						"Insertion de MME/M " +unClientPart.getNom() + " réussi"
						);
				//on vide les champs après l'insertion du client
				Object [] ligne = {nom, prenom, email, tel};
				this.unTableau.insererLigne(ligne);
				this.actionChamps(2);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch(command) {
		case "btInscription":
			this.actionChamps(1);
			break;
		case "btAnnuler":
			this.actionChamps(2);
			break;
		}
		
		
	}

}
