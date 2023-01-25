package Vue.VuePanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controleur.JO_PARIS;

public class VueGenerale extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panelMenu = new JPanel();
	private JButton btClientPart = new JButton("Gestion Clients Particulier");
	private JButton btClientPro = new JButton("Gestion Clients Professionnels");
	private JButton btQuitter = new JButton("Quitter");
	
	private static PanelClientPart unPanelClientPart = new PanelClientPart();
	private static PanelClientPro unPanelClientPro = new PanelClientPro();
	
	public VueGenerale() {
		this.setTitle("Gestion de la BDD PARIS 2024");
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(255, 255, 204));
		this.setBounds(100, 20, 1200, 700);
		this.setLayout(null);
		
		
		// construction du panel menu après connexion
		
		this.panelMenu.setBounds(100, 20, 900, 40);
		this.panelMenu.setBackground(new Color(255, 255, 204));
		// setLayout(lignes, colonnes)
		this.panelMenu.setLayout(new GridLayout(1, 3)); 
		
		this.panelMenu.add(this.btClientPart);
		this.panelMenu.add(this.btClientPro);
		this.panelMenu.add(this.btQuitter);
		
		this.add(panelMenu);
		
		//rendre les boutuons ecoutables
		this.btClientPart.addActionListener(this);
		this.btClientPro.addActionListener(this);
		this.btQuitter.addActionListener(this);
		
		// insertions des panneaux dans la fenetre
		this.add(unPanelClientPart);
		this.add(unPanelClientPro);
		
		this.setVisible(false);
	}

	public static void activerPanelGeneral(int choix) {
		unPanelClientPart.setVisible(false);
		unPanelClientPro.setVisible(false);
		switch(choix) {
		case 1: unPanelClientPart.setVisible(true); break;
		case 2: unPanelClientPro.setVisible(true); break;
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.btQuitter) {
			int retour = JOptionPane.showConfirmDialog(this,
					"Voulez-vous quitter l'application ?",
					"Quitter Application", 
					JOptionPane.YES_NO_OPTION);
			if(retour == 0) {
				this.dispose();
				JO_PARIS.gererVueConnexion(true);
			}	
		} else if(e.getSource() == this.btClientPart) {
			activerPanelGeneral(1);
		} else if (e.getSource() == this.btClientPro) {
			activerPanelGeneral(2);
		}
	}

	public void actionPerformed1(ActionEvent arg0) {

		
	}
	
}

