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

import Controleur.C_ClientPro;
import Controleur.ClientPro;
import Controleur.JO_PARIS;

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
		this.setBounds(350, 20, 700, 750);
		this.setLayout(null);
		
		// ajout du logo
		ImageIcon logo = new ImageIcon("src/images/JOPARIS.png");
		JLabel monLogo = new JLabel(logo);
		monLogo.setBounds(230, 10, 240, 240);
		this.add(monLogo);
	
	
		
		
		this.panelInscription.setBounds(100, 300, 500, 300);
		this.panelInscription.setBackground(new Color(255, 255, 204));
		this.panelInscription.setLayout(new GridLayout(7, 2));
		
		// Construction du panel de l'insription
		
		this.panelInscription.add(new JLabel("Nom")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscription.add(this.txtNom);
		
		this.panelInscription.add(new JLabel("Numéro de Siret")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscription.add(this.txtSiret);
		
		this.panelInscription.add(new JLabel("Email")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscription.add(this.txtEmail);
		
		this.panelInscription.add(new JLabel("Password")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscription.add(this.txtMdp);
		
		this.panelInscription.add(new JLabel("Telephone")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscription.add(this.txtTel);
		
		this.panelInscription.add(new JLabel("Adresse")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscription.add(this.txtAdresse);
		
		this.panelInscription.add(this.btAnnuler).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscription.add(this.btInscription).setFont(new Font("Paris2024", Font.ITALIC, 18));
		
		this.add(panelInscription);
		
		// panel des boutons retour et quitter
		
		this.panelButtonAction.setBounds(250, 620, 200, 80);
		this.panelButtonAction.setBackground(new Color(255, 255, 204));
		this.panelButtonAction.setLayout(new GridLayout(2, 1));
		
		this.panelButtonAction.add(this.btRetour).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelButtonAction.add(this.btQuitter).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.add(panelButtonAction);
		
		
		// rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btInscription.addActionListener(this);
		
		this.btRetour.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		
		// définir la police de chaque bouton
		this.txtNom.setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.txtAdresse.setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.txtEmail.setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.txtSiret.setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.txtTel.setFont(new Font("Paris2024", Font.ITALIC, 18));
		
		
		
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
					JO_PARIS.gererVueConnexion(true);
					VueConnexion.activerPanel(0);
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
			JO_PARIS.gererVueConnexion(true);
			VueConnexion.activerPanel(0);
		} else if(e.getSource() == this.btQuitter) {
			System.exit(0);
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

