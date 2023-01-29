package Vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.C_ClientPart;
import Controleur.ClientPart;

public class PanelClientPart extends PanelPrincipal implements ActionListener{
	
	private JPanel panelInscriptionPart = new JPanel();
	
	private JTextField[] textfields = new JTextField[4];
	private String[] jtfNames = {"txtNom", "txtPrenom", "txtEmail", "txtTel"};
	private String[] jlNames = {"Nom", "Prenom", "Email", "Tel"};
	
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
			
			JLabel jl = new JLabel(jlNames[i]);
			this.panelInscriptionPart.add(jl);
			this.panelInscriptionPart.add(textfields[i]);
		}
		
		// ajout du JPasswordField()
		this.panelInscriptionPart.add(new JLabel("Password"));
		this.panelInscriptionPart.add(txtMdp);
		
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(btNames[i]);
			buttons[i].setText(btText[i]);
			
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(btNames[i]);
			
			this.panelInscriptionPart.add(buttons[i]);
		}
		

		this.add(panelInscriptionPart);
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
						"Insertion de MME/M " +unClientPart.getNom() + " résusi"
						);
				//on vide les champs après l'insertion du client
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
