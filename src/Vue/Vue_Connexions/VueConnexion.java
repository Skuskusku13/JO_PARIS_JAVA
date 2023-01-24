package Vue.Vue_Connexions;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.C_User;
import Controleur.User;

public class VueConnexion extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	private JTextField txtEmail = new JTextField("");
	private JPasswordField txtMdp = new JPasswordField("");
	
	// création d'une list pour le texte des boutons
	private String[] boutonsText = {
			"Inscription Client Particulier",
			"Inscription Client Pro",
			"Annuler",
			"Se Connecter",
			"Quitter"
	};
	
	// création d'une list pour le nom des boutons
	private String[] boutonsNames = {
			"btClientPart",
			"btClientPro",
			"btAnnuler",
			"btSeConnecter",
			"btQuitter"
	};
	
	private JPanel panelConnexion = new JPanel();
	private JPanel panelInscriptions = new JPanel();
	private JPanel panelQuitter = new JPanel();
	
	private static VueInscriptionPart uneInscriptionPart = new VueInscriptionPart();
	private static VueInscriptionPro uneInscriptionPro = new VueInscriptionPro();
	
	
	public VueConnexion() {
		
		this.setTitle("Programme java JO PARIS 2024");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(255, 255, 204));
		this.setBounds(350, 20, 700, 750);
		this.setLayout(null);
		
		ImageIcon logo = new ImageIcon("src/images/JOPARIS.png");
		JLabel monLogo = new JLabel(logo);
		monLogo.setBounds(230, 10, 240, 240);
		this.add(monLogo);
		
		// Construction du panel de connection
		this.panelConnexion.setBounds(100, 300, 500, 200);
		this.panelConnexion.setBackground(new Color(255, 255, 204));
		this.panelConnexion.setLayout(new GridLayout(3, 2));
		
		this.panelConnexion.add(new JLabel("Email")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelConnexion.add(this.txtEmail).setFont(new Font("Paris2024", Font.ITALIC, 18));
		
		this.panelConnexion.add(new JLabel("Password")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelConnexion.add(this.txtMdp).setFont(new Font("Paris2024", Font.ITALIC, 18));
					
		
		this.add(panelConnexion);
		
		
		// construction du panel d'inscriptons
		this.panelInscriptions.setBounds(125, 550, 450, 50);
		this.panelInscriptions.setBackground(new Color(255, 255, 204));
		this.panelInscriptions.setLayout(new GridLayout(1, 2));
		
		this.add(panelInscriptions);
		
		// construction du panel pour quitter
		this.panelQuitter.setBounds(300, 650, 100, 50);
		this.panelQuitter.setBackground(new Color(255, 255, 204));
		this.panelQuitter.setLayout(new GridLayout(1, 1));
		
		this.add(panelQuitter);
		
		
		// boucles d'ajouts des boutons 
		for (int i = 0; i < boutonsNames.length; i++) {
			JButton jb = new JButton();
			jb.setName(boutonsNames[i]);
			jb.setText(boutonsText[i]);
			jb.setFont(new Font("Paris2024", Font.ITALIC, 18));
//			création de setactioncommand avec le nom des boutons pour le recuperer dans la fonction actionperformed
			jb.addActionListener(this);
			jb.setActionCommand(boutonsNames[i]);
			
			if (jb.getName() == boutonsNames[0] || jb.getName() == boutonsNames[1]) {
				this.panelInscriptions.add(jb);
			} else if(jb.getName() == boutonsNames[4]) {
				jb.addActionListener(this);
				this.panelQuitter.add(jb);
			} else {
				jb.addActionListener(this);
				this.panelConnexion.add(jb);
			}	
		}
		
		// rendre les evenements de touches ecoutables lorsqu'on est sur l'un des JLabel
		// entree pour valider la connexion par exemple
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		
		
		
		
		this.setVisible(true);
	}
	

	public void traitement() {
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		
		if(email.equals("") || mdp.equals("")) {
			JOptionPane.showMessageDialog(this, 
			"Veuillez remplir tous les champs !",
			"Champs vide", 
			JOptionPane.ERROR_MESSAGE);
		} else {
			// verification dans la bdd
			User unUser = C_User.selectWhereUser(email, mdp);
			if(unUser == null) {
				JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants");
			} else {
				JOptionPane.showMessageDialog(this,  "Bienvenue MME/M  " + unUser.getNom());
				this.txtEmail.setText("");
				this.txtMdp.setText("");
				
				//ouverture de la session
				// changer l"argument quand les pages seront créées
				activerPanel(1);
			}
		}
		
	}
	
	
	public static void activerPanel(int choix) {
		uneInscriptionPart.setVisible(false);
		uneInscriptionPro.setVisible(false);
		
		switch(choix) {
		case 0: break;
		case 1: uneInscriptionPart.setVisible(true); break;
		case 2: uneInscriptionPro.setVisible(true); break;
		}
	}
	
//	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch (command) {
		case "btSeConnecter":
			this.traitement();
			break;
		case "btAnnuler":
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			break;
		case "btClientPart":
			this.dispose();
			activerPanel(1);
			break;
		case "btClientPro":
			this.dispose();
			activerPanel(2);
			break;	
		case "btQuitter":
			System.exit(0);
		}
	
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}


}
