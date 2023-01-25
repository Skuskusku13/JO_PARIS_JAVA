package Vue.Vue_Connexions;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.C_ClientPart;
import Controleur.ClientPart;
import Controleur.JO_PARIS;

public class VueInscriptionPart extends JFrame implements ActionListener, KeyListener{

	private static final long serialVersionUID = 1L;
	
	private JPasswordField txtMdp = new JPasswordField();

	private String[] boutonNames = {"btAnnuler", "btInscription", "btRetour", "btQuitter"};
	
	private String[] boutonText = {"Annuler", "S'inscrire", "Retour Connexion", "Quitter"};
	
	private String[] jtfNames = {"txtNom", "txtPrenom", "txtTel", "txtEmail", "txtRole"};
	
	private String[] jlText = {"Nom", "Prénom", "Téléphone", "Email", "Role"};
	
	private JPanel panelInscription = new JPanel();
	private JPanel panelButtonAction = new JPanel();
	
	
	public VueInscriptionPart() {
		
		this.setTitle("Inscription d'un particulier");
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
		
	
		// Construction du panel d'inscription
		this.panelInscription.setBounds(100, 300, 500, 300);
		this.panelInscription.setBackground(new Color(255, 255, 204));
		this.panelInscription.setLayout(new GridLayout(7, 2));
		
		
		//construction du panel des boutons retour et quitter
		this.panelButtonAction.setBounds(250, 600, 200, 80);
		this.panelButtonAction.setBackground(new Color(255, 255, 204));
		this.panelButtonAction.setLayout(new GridLayout(2, 1));
		
		
		// boucles qui ajoute les JTexfield ainsi que les JLabel en meme temps
		for (int i = 0; i < jlText.length; i++) {
			JLabel jl = new JLabel();
			jl.setText(jlText[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			JTextField jtf = new JTextField();
			jtf.setName(jtfNames[i]);
			jtf.setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			if(jtf.getName() == "txtRole") {
				 jtf.setText("ClientPart");
			}
			
			// condition pour ne pas proposer a l'utilisateur de mettre son role
			// on ajoute pas au formulaire le input role
			if(jtf.getName() != "txtRole" && jl.getName() != "Role") {	
				this.panelInscription.add(jl);
				this.panelInscription.add(jtf);
			}
		}
//		// ajout de la case Password qui n'est pas la liste du JTexfield et du JLabel
		this.panelInscription.add(new JLabel("Password")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelInscription.add(txtMdp);

		
//		//boucles qui ajoutes les Jbutton et les rends ecouttables
		for (int i = 0; i < boutonNames.length; i++) {
			JButton jb = new JButton();
			jb.setName(boutonNames[i]);
			jb.setText(boutonText[i]);
			jb.setFont(new Font("Paris2024", Font.ITALIC, 18));
			jb.addActionListener(this);
			jb.setActionCommand(boutonNames[i]);
			
			if(jb.getName() == "btAnnuler" || jb.getName() == "btInscription") {
				this.panelInscription.add(jb);
			} else {
				this.panelButtonAction.add(jb);
			}
		}
//
//		// ajout des panels dans notre JFrame
		this.add(panelInscription);
		this.add(panelButtonAction);
		
		this.setVisible(false);
	}
	
	public void traitement() {
		
		String mdp = new String(this.txtMdp.getPassword());
//		String nom = new String(nom);
		
		for (int i = 0; i < jlText.length; i++) {
			String a = jtfNames[i];
//			a.valueOf(jtfNames[i]);

			if(a.equals("")) {
				JOptionPane.showMessageDialog(
						this, 
						"Veuillez remplir tous les champs", 
						"Champs vide", 
						JOptionPane.ERROR_MESSAGE
				);
				break;
			}  else {
				ClientPart unClientPart = new ClientPart(jlText[0], jlText[3], mdp, jlText[2], jlText[4], jlText[1]);
				C_ClientPart.insertClientPart(unClientPart);
				if(unClientPart != null) {
					int retour = JOptionPane.showConfirmDialog(
							this,
							"Votre inscription a bien été prise effectué " 
							+ unClientPart.getNom() + " "
							+ unClientPart.getPrenom(), 
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
		
//		String email = this.txtEmail.getText();
//		String mdp = new String(this.txtMdp.getPassword());
//		String tel = this.txtTel.getText();
//		String role = this.txtRole.getText();;
//		String prenom = this.txtPrenom.getText();
		
//		if(nom.equals("") || email.equals("") || mdp.equals("") || tel.equals("") || role.equals("") || prenom.equals("")) {
//			JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Champs vide", JOptionPane.ERROR_MESSAGE);
//		} else {
//			ClientPart unClientPart = new ClientPart(nom, email, mdp, tel, role, prenom);
//			C_ClientPart.insertClientPart(unClientPart);
//			if(unClientPart != null) {
//				int retour = JOptionPane.showConfirmDialog(
//						this,
//						"Votre inscription a bien été prise effectué " 
//						+ unClientPart.getNom() + " "
//						+ unClientPart.getPrenom(), 
//						"Inscription Validée !", 
//						JOptionPane.YES_OPTION
//				);
//				
//				if(retour == 0 || retour == 1) {
//					JO_PARIS.gererVueConnexion(true);
//					VueConnexion.activerPanel(0);
//				}
//				
//			}
//		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch (command) {
		case "btAnnuler":
			break;
		case "btInscription":
			this.traitement();
			break;
		case "btRetour":
			JO_PARIS.gererVueConnexion(true);
			VueConnexion.activerPanel(0);
			break;
		case "btQuitter":
			System.exit(0);
			break;
		}
//		if(e.getSource() == this.btInscription) {
//			this.traitement();
//		} else if (e.getSource() == this.btAnnuler) {
//			this.txtNom.setText("");
//			this.txtEmail.setText("");
//			this.txtMdp.setText("");
//			this.txtTel.setText("");
//			this.txtPrenom.setText("");
//			
//		} else if(e.getSource() == this.btRetour) {
//			JO_PARIS.gererVueConnexion(true);
//			VueConnexion.activerPanel(0);
//		} else if(e.getSource() == this.btQuitter) {
//			System.exit(0);
//		}
//		
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
