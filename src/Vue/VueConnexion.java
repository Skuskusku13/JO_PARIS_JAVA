package Vue;

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
	
	private JButton btClientPart = new JButton("Inscription Client Particulier");
	private JButton btClientPro = new JButton("Inscription Client Pro");
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeConnecter = new JButton("Se Connecter");
	private JButton btQuitter= new JButton("Quitter");
	
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
		
		this.panelConnexion.add(this.btAnnuler).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelConnexion.add(this.btSeConnecter).setFont(new Font("Paris2024", Font.ITALIC, 18));
		
		
				
		
		this.add(panelConnexion);
		
		
		// construction du panel d'inscriptons
		this.panelInscriptions.setBounds(125, 550, 450, 50);
		this.panelInscriptions.setBackground(new Color(255, 255, 204));
		this.panelInscriptions.setLayout(new GridLayout(1, 2));
		
		this.panelInscriptions.add(this.btClientPart).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscriptions.add(this.btClientPro).setFont(new Font("Paris2024", Font.ITALIC, 18));
		
		this.add(panelInscriptions);
		
		
		// construction du panel pour quitter
		this.panelQuitter.setBounds(300, 650, 100, 50);
		this.panelQuitter.setBackground(new Color(255, 255, 204));
		this.panelQuitter.setLayout(new GridLayout(1, 1));
		
		this.panelQuitter.add(this.btQuitter).setFont(new Font("Paris2024", Font.ITALIC, 18));
		
		this.add(panelQuitter);
		
		
		
		// rendre les deux boutons (Annuler, Se Connecter) écoutables
		this.btAnnuler.addActionListener(this);
		this.btSeConnecter.addActionListener(this);
		
		this.btClientPart.addActionListener(this);
		this.btClientPro.addActionListener(this);
		
		this.btQuitter.addActionListener(this);
		
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
			JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs !", "Champs vide", JOptionPane.YES_OPTION);
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
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		} else if (e.getSource() == this.btSeConnecter) {
			this.traitement();
		} else if(e.getSource() == this.btClientPart) {
			dispose();
			activerPanel(1);
		} else if(e.getSource() == this.btClientPro) {
			dispose();
			activerPanel(2);
		} else if(e.getSource() == this.btQuitter) {
			this.dispose();
		}

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}
