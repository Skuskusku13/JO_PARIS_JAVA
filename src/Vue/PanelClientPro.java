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

import Controleur.C_ClientPro;
import Controleur.ClientPro;
import Controleur.Tableau;

public class PanelClientPro extends PanelPrincipal implements ActionListener{
	
	private JPanel panelInscriptionPro = new JPanel();
	private JPanel panelTable = new JPanel();
	private Tableau unTableau;
	
	private JTable tablePro;
	private String[] entetes = {"NOM", "EMAIL", "TELEPHONE", "ADRESSE",  "NUMERO SIRET"};
	
	private JTextField[] textfields = new JTextField[5];
	private String[] jtfNames = {"txtNom", "txtEmail", "txtTel", "txtSiret", "txtAdresse"};
	private String[] jlNames = {"Nom", "Email", "Tel", "Siret", "Adresse"};
	
	private JPasswordField txtMdp = new JPasswordField();
	
	private JButton[] buttons = new JButton[2];
	private String[] btNames = {"btAnnuler", "btInscription"};
	private String[] btText = {"Annuler", "Inscription"};
	
	
	private static final long serialVersionUID = 1L;

	public PanelClientPro() {
		super(new Color(255, 255, 255));
		
		this.panelInscriptionPro.setBounds(30, 50, 350, 400);
		this.panelInscriptionPro.setBackground(new Color(255, 255, 255));
		this.panelInscriptionPro.setLayout(new GridLayout(7, 2));
		
		for (int i = 0; i < textfields.length; i++) {
			textfields[i] = new JTextField();
			textfields[i].setName(jtfNames[i]);
			textfields[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			JLabel jl = new JLabel(jlNames[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			this.panelInscriptionPro.add(jl);
			this.panelInscriptionPro.add(textfields[i]);
		}
		
		// ajout du JPasswordField()
		this.panelInscriptionPro.add(new JLabel("Password")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscriptionPro.add(txtMdp);
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(btNames[i]);
			buttons[i].setText(btText[i]);
			buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(btNames[i]);
			
			this.panelInscriptionPro.add(buttons[i]);
		}
		

		this.add(panelInscriptionPro);
		
		this.panelTable.setBounds(500, 50, 600, 500);
		this.panelTable.setBackground(new Color(255, 255, 255));
		this.panelTable.setLayout(null);
		
		this.unTableau = new Tableau(this.obtenirClientsPro(), entetes);
		this.tablePro = new JTable(this.unTableau);
		
		JScrollPane unScroll = new JScrollPane(this.tablePro);
		unScroll.setBounds(0, 0, 600, 350);
		this.panelTable.add(unScroll);
		this.add(panelTable);
	}
	
	public Object[][] obtenirClientsPro() {
		ArrayList<ClientPro> lesClientsPro = C_ClientPro.selectAllClientPro();
		Object [] [] matrice = new Object [lesClientsPro.size()] [5];
		int i  = 0;
		for (ClientPro unClient : lesClientsPro) {
			matrice[i][0] = unClient.getNom();
			matrice[i][1] = unClient.getEmail();
			matrice[i][2] = unClient.getTel();
			matrice[i][3] = unClient.getAdresse();
			matrice[i][4] = unClient.getNum_siret();
			i++;
		}
		return matrice;
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

	public void traitement() {
		String nom = this.textfields[0].getText();
		String siret = this.textfields[1].getText();
		String email = this.textfields[2].getText();
		String adresse = this.textfields[3].getText();
		String tel = this.textfields[4].getText();
		String role = "clientPro";
		String mdp = new String(this.txtMdp.getPassword());
		
		String[] list = {nom, siret, email, adresse, tel, role, mdp};
		
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
			ClientPro unClientPro = new ClientPro(nom, email, mdp, tel, role, siret, adresse);
			if(unClientPro != null) {				
				C_ClientPro.insertClientPro(unClientPro);
				JOptionPane.showMessageDialog(this, 
						"Insertion de MME/M " +unClientPro.getNom() + " résusi"
						);
				//on vide les champs après l'insertion du client
				Object [] ligne  = {nom, email, tel, adresse, siret};
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
