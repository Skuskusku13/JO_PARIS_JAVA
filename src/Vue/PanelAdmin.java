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

import Controleur.Admin;
import Controleur.C_Admin;
import Controleur.Tableau;

public class PanelAdmin extends PanelPrincipal implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panelInscriptionAdmin = new JPanel();
	private JPanel panelTable = new JPanel();
	private JTable tableAdmin = new JTable();
	private Tableau unTableau;
	
	private JTextField[] textfields = new JTextField[3];
	private String[] jtfNames = {"txtNom", "txtEmail", "txtTel"};
	private String[] jlNames = {"Nom", "Email", "Tel"};
	private String[] entetes = {"NOM", "EMAIL", "TEL"};
	
	private JPasswordField txtMdp = new JPasswordField();
	
	private JButton[] buttons = new JButton[2];
	private String[] btNames = {"btAnnuler", "btInscription"};
	private String[] btText = {"Annuler", "Inscription"};
	
	
	private static PanelAdmin instance;
	
	public static PanelAdmin getInstance() {
		if(instance == null) {
			instance = new PanelAdmin();
		}
		return instance;
	}
	
	private PanelAdmin() {
		super(new Color(255, 255, 255));
		
		this.panelInscriptionAdmin.setBounds(30, 50, 350, 400);
		this.panelInscriptionAdmin.setBackground(new Color(255, 255, 255));
		this.panelInscriptionAdmin.setLayout(new GridLayout(5, 2));
		
		for (int i = 0; i < jtfNames.length; i++) {
			textfields[i] = new JTextField();
			textfields[i].setName(jtfNames[i]);
			textfields[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			JLabel jl = new JLabel();
			jl.setText(jlNames[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			this.panelInscriptionAdmin.add(jl);
			this.panelInscriptionAdmin.add(textfields[i]);
		}
		
		this.panelInscriptionAdmin.add(new JLabel("Password")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscriptionAdmin.add(this.txtMdp);
		
		for (int i = 0; i < btNames.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(btNames[i]);
			buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			buttons[i].setText(btText[i]);
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(btNames[i]);
			
			this.panelInscriptionAdmin.add(buttons[i]);
		}
		
		this.add(panelInscriptionAdmin);
		
		this.panelTable.setBounds(500, 50, 600, 500);
		this.panelTable.setBackground(new Color(255, 255, 255));
		this.panelTable.setLayout(null);
		
		this.unTableau = new Tableau(this.obtenirAdmin(), entetes);
		this.tableAdmin = new JTable(unTableau);
		 
		
		JScrollPane unScroll = new JScrollPane(this.tableAdmin);
		unScroll.setBounds(0, 0, 600, 350);
		this.panelTable.add(unScroll);
		
		this.tableAdmin.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne;
				if(e.getClickCount() == 2) {
					numLigne = tableAdmin.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null,
							"Voulez-vous supprimer cet Admin ? ", 
							"suppresion Admin",
							JOptionPane.YES_NO_OPTION
							);
					if(retour == 0) {
						//suppression du client de la base 
						String nom = (String) unTableau.getValueAt(numLigne, 0);
						String email = (String) unTableau.getValueAt(numLigne, 1);
						C_Admin.deleteAdmin(nom, email);
						
						//suppression de la ligne dans le tableau
						unTableau.supprimerLigne(numLigne);
					}
				}
			}
		});
		
		this.add(panelTable);
		
	}
	
	public Object [][] obtenirAdmin() {
	
		ArrayList<Admin> lesAdmins = C_Admin.selectAllAdmin();
		Object [][] matrice = new Object [lesAdmins.size()] [3];
		int i = 0;
		for (Admin unAdmin : lesAdmins) {
			matrice[i][0] = unAdmin.getNom();
			matrice[i][1] = unAdmin.getEmail();
			matrice[i][2] = unAdmin.getTel();	
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
		String email = this.textfields[1].getText();
		String mdp = new String(this.txtMdp.getPassword());
		String tel = this.textfields[2].getText();
		String role = "admin";
		
		String list[] = {nom, email, mdp, tel, role};
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
					JOptionPane.CANCEL_OPTION);
		} else {
			Admin unAdmin = new Admin(nom, email, mdp, tel, role);
			if(unAdmin != null) {
				C_Admin.insertAdmin(unAdmin);
				JOptionPane.showMessageDialog(this,
						"Insertion de MME/M " +unAdmin.getNom()+ "réussi(e)"
						);
				Object ligne [] = {nom, email, tel};
				this.unTableau.insererLigne(ligne);
				
				this.actionChamps(2);
			}			
		}
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch (command) {
		case "btAnnuler":
			this.actionChamps(2);
			break;

		case "btInscription":
			this.actionChamps(1);
			break;
		}
	}
}
