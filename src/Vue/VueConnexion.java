package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Controleur.C_User;
import Controleur.JO_PARIS;
import Controleur.User;

public class VueConnexion extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtEmail = new JTextField("a@gmail.com");
	private JPasswordField txtMdp = new JPasswordField("123");

	private JButton btAnnuler = new JButton("Annuler");
	private JButton btConnexion = new JButton("Connexion");
	private JButton btQuitter = new JButton("Quitter");

	private JPanel panelConnexion = new JPanel();
	private JPanel panelQuitter = new JPanel();

	private static VueConnexion instance;

	public static VueConnexion getInstance(){
		if(instance == null){
			instance = new VueConnexion();
		}
		return instance;
	}
	

	private VueConnexion() {

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

		// construction du panel de connexion
		this.panelConnexion.setBounds(100, 300, 500, 200);
		this.panelConnexion.setBackground(new Color(255, 255, 204));
		this.panelConnexion.setLayout(new GridLayout(3, 2));

		this.panelConnexion.add(new JLabel("Email")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelConnexion.add(this.txtEmail).setFont(new Font("Paris2024", Font.ITALIC, 18));

		this.panelConnexion.add(new JLabel("Password")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelConnexion.add(this.txtMdp).setFont(new Font("Paris2024", Font.ITALIC, 18));

		this.panelConnexion.add(btAnnuler);
		this.panelConnexion.add(btConnexion);

		this.add(panelConnexion);

		// construction du panel pour quitter
		this.panelQuitter.setBounds(300, 650, 100, 50);
		this.panelQuitter.setBackground(new Color(255, 255, 204));
		this.panelQuitter.setLayout(new GridLayout(1, 1));

		this.panelQuitter.add(this.btQuitter);

		this.add(panelQuitter);

		// boutons écoutable
		this.btAnnuler.addActionListener(this);
		this.btQuitter.addActionListener(this);
		this.btConnexion.addActionListener(this);

		// boutons écoutable avec les touches
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);

		this.setVisible(true);
	}

	private void viderChamps() {
		this.txtEmail.setText("");
		this.txtMdp.setText("");
	}

	private void traitement() {
		String email = this.txtEmail.getText();
		String mdp = new String(this.txtMdp.getPassword());

		if (email.equals("") || mdp.equals("")) {
			JOptionPane.showMessageDialog(this, "Veuillez remplir tous les champs", "Champs vide",
					JOptionPane.ERROR_MESSAGE);
		} else {
			User unUser = C_User.selectWhereUser(email, mdp);
			if (unUser == null) {
				JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants d'administrateur", "erreur de connexion",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Bienvenue MME/M" + unUser.getNom());
				this.viderChamps();
				this.dispose();
				this.activerPanel(1);
			}
		}
	}

	public void quitter() {
		int retour = JOptionPane.showConfirmDialog(this,
			"Etes-vous sur de vouloir quitter ?",
			"Quitter", 
			JOptionPane.YES_NO_OPTION
		);
		if(retour != 1) {				
			System.exit(0);
		}
	}

	public void activerPanel(int choix) {
		JO_PARIS.gererVueGenerale(false);
		switch (choix) {
		case 1:
			JO_PARIS.gererVueGenerale(true);
			break;
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btAnnuler) {
			this.viderChamps();
		} else if (e.getSource() == btConnexion) {
			this.traitement();
		} else if (e.getSource() == this.btQuitter) {
			quitter();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER ) {
			this.traitement();
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			quitter();
		}
	}

	@Override
		public void keyTyped(KeyEvent e) {
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
