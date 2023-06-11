package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	
	private static PanelClientPart instance;
	
	public static PanelClientPart getInstance() {
		if(instance == null) {
			instance = new PanelClientPart();
		}
		return instance;
	}
	

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
		
		this.tablePart.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne;
				if(e.getClickCount() == 2) {
					numLigne = tablePart.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null,
							"Voulez-vous supprimer ce Client Particulier ", 
							"suppresion Client Particulier",
							JOptionPane.YES_NO_OPTION
							);
					if(retour == 0) {
						//suppression du client de la base 
						String nom = (String) unTableau.getValueAt(numLigne, 0);
						String prenom = (String) unTableau.getValueAt(numLigne, 1);
						String email = (String) unTableau.getValueAt(numLigne, 2);
						String tel = (String) unTableau.getValueAt(numLigne, 3);
						
						int idclient = C_ClientPart.selectIdPart(nom, prenom, email, tel);
						
						System.out.println(idclient);
						
						C_ClientPart.deleteClientPar(idclient);
						
						//suppression de la ligne dans le tableau
						unTableau.supprimerLigne(numLigne);
					}
				}
			}
		});
		
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
		boolean verifMdp = false;

		for (int i = 0; i < mdp.length(); i++) {
			verifMdp = i < 8 ? false : true;
		}
		
		if(verifMdp != true) {
			JOptionPane.showMessageDialog(this,
					"Merci de rentrer un Mot de passe contenant au moins 8 caractères",
					"Mot de passe trop court",
					JOptionPane.CLOSED_OPTION);
			return;
		}
		
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
