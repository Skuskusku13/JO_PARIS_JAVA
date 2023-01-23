package Vue;

import java.awt.Color;
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

import Controleur.C_ClientPro;
import Controleur.ClientPro;

public class VueInscriptionPro extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	
	private JTextField txtEmail = new JTextField("");
	private JPasswordField txtMdp = new JPasswordField("");
	private JTextField txtNom = new JTextField("");
	private JTextField txtTel = new JTextField("");
	private JTextField txtRole = new JTextField("clientPro");
	private JTextField txtSiret = new JTextField("");
	private JTextField txtAdresse = new JTextField("");
	
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btInscription = new JButton("S'inscrire");
	private JButton btRetour = new JButton("Retour Connexion");
	private JButton btQuitter = new JButton("Quitter");
	
	private JPanel panelInscription = new JPanel();
	private JPanel panelButtonAction = new JPanel();
	
	
	public VueInscriptionPro() {
		
		this.setTitle("Programme java JO PARIS 2024");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(255, 255, 204));
		this.setBounds(350, 20, 700, 700);
		this.setLayout(null);
		
		// ajout du logo
		ImageIcon logo = new ImageIcon("src/images/JOPARIS.png");
		JLabel monLogo = new JLabel(logo);
		monLogo.setBounds(230, 10, 240, 240);
		this.add(monLogo);
	
		// Construction du panel de connection
		
		
		this.panelInscription.setBounds(100, 300, 500, 200);
		this.panelInscription.setBackground(new Color(255, 255, 204));
		this.panelInscription.setLayout(new GridLayout(7, 2));
		
		this.panelInscription.add(new JLabel("Nom"));
		this.panelInscription.add(this.txtNom);
		
		this.panelInscription.add(new JLabel("Numéro de Siret"));
		this.panelInscription.add(this.txtSiret);
		
		this.panelInscription.add(new JLabel("Email"));
		this.panelInscription.add(this.txtEmail);
		
		this.panelInscription.add(new JLabel("Password"));
		this.panelInscription.add(this.txtMdp);
		
		this.panelInscription.add(new JLabel("Telephone"));
		this.panelInscription.add(this.txtTel);
		
		this.panelInscription.add(new JLabel("Adresse"));
		this.panelInscription.add(this.txtAdresse);
		
		this.panelInscription.add(this.btAnnuler);
		this.panelInscription.add(this.btInscription);
		
		this.add(panelInscription);
		
		// panel des boutons retour et quitter
		
		this.panelButtonAction.setBounds(100, 600, 500, 50);
		this.panelButtonAction.setBackground(new Color(255, 255, 204));
		this.panelButtonAction.setLayout(new GridLayout(1, 2));
		
		this.panelButtonAction.add(this.btRetour);
		this.panelButtonAction.add(this.btQuitter);
		this.add(panelButtonAction);
		
		
		// rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btInscription.addActionListener(this);
		
		this.btRetour.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		this.setVisible(false);
	}
	
	public void traitement() {
		String nom = this.txtNom.getText();
		String email = this.txtEmail.getText();
		String mdp = new String(this.txtMdp.getPassword());
		String tel = this.txtTel.getText();
		String role = this.txtRole.getText();;
		String num_siret = this.txtSiret.getText();
		String adresse = this.txtAdresse.getText();
		
		if(nom.equals("") || email.equals("") || mdp.equals("") || tel.equals("") || role.equals("") || num_siret.equals("") || adresse.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Champs vide", JOptionPane.ERROR_MESSAGE);
		} else {
			ClientPro unClientPro = new ClientPro(nom, email, mdp, tel, role, num_siret, adresse);
			C_ClientPro.insertClientPro(unClientPro);
			if(unClientPro != null) {
				int retour = JOptionPane.showConfirmDialog(
						this,
						"Votre inscription a bien été prise effectué " 
						+ unClientPro.getNom(),
						"Inscription Validée !", 
						JOptionPane.YES_OPTION
				);
				
				if(retour == 0 || retour == 1) {
					VueConnexion.activerPanel(1);
				}
				
			}
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btInscription) {
			this.traitement();
		} else if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
			this.txtTel.setText("");
			this.txtSiret.setText("");
			this.txtAdresse.setText("");
			
		} else if(e.getSource() == this.btRetour) {
			VueConnexion.activerPanel(1);
		} else if(e.getSource() == this.btQuitter) {
			dispose();
		}
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {

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

