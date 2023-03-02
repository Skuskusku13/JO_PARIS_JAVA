package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VueGenerale extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JPanel panelMenu = new JPanel();
	
	private JButton[] buttons = new JButton[6];
	private String[] buttonNames = {"btAdmin", "btClientPart", "btClientPro", "btCategorie", "btEvenement", "btQuitter"};
	private String[] buttonText = {"Administrateurs", "Clients Particulier", "Clients Pro", "Catégories", "Évènement", "Quitter"};
	
	private static PanelAdmin unPanelAdmin = new PanelAdmin();
	private static PanelClientPart unPanelClientPart = new PanelClientPart();
	private static PanelClientPro unPanelClientPro = new PanelClientPro();
	private static PanelCategorie unPanelCategorie = new PanelCategorie();
	private static PanelEvenement unPanelEvenement = new PanelEvenement();

	public VueGenerale() {

		this.setTitle("Gestion des JO 2024");
		this.setBounds(50, 20, 1350, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(255, 255, 204));
		this.setLayout(null);
		
		// construction du panel menu
		this.panelMenu.setBounds(100, 40, 1000, 60);
		this.panelMenu.setLayout(new GridLayout(1, 6));
		this.panelMenu.setBackground(new Color(255, 255, 204));
		
		for (int i = 0; i < buttons.length; i++) {
		    buttons[i] = new JButton();
		    buttons[i].setName(buttonNames[i]);
		    buttons[i].setText(buttonText[i]);
		    buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
		    buttons[i].addActionListener(this);
		    buttons[i].setActionCommand(buttonNames[i]);
		    
		    this.panelMenu.add(buttons[i]);
		}
		
		this.add(panelMenu);
		 
		this.add(unPanelAdmin);
		this.add(unPanelClientPart);
		this.add(unPanelClientPro);
		this.add(unPanelCategorie);
		this.add(unPanelEvenement);

		this.setVisible(false);

	}
	
	public void activerPanelPrincipal(int choix) {
		unPanelAdmin.setVisible(false);
		unPanelClientPart.setVisible(false);
		unPanelClientPro.setVisible(false);
		unPanelCategorie.setVisible(false);
		unPanelEvenement.setVisible(false);
		
		switch(choix) {
		case 1: unPanelClientPart.setVisible(true); break;
		case 2: unPanelClientPro.setVisible(true); break;
		case 3: unPanelCategorie.setVisible(true); break;
		case 4: unPanelEvenement.setVisible(true); break;
		case 5: unPanelAdmin.setVisible(true); break;
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		
		switch (command) {
		case "btClientPart":
			this.activerPanelPrincipal(1);
			break;
		case "btClientPro":
			this.activerPanelPrincipal(2);
			break;
		case "btCategorie":
			this.activerPanelPrincipal(3);
			break;
		case "btEvenement":
			this.activerPanelPrincipal(4);
			break;
		case "btAdmin":
			this.activerPanelPrincipal(5);
			break;
			
			
		case "btQuitter":
			int retour = JOptionPane.showConfirmDialog(this,
				"Etes-vous sur de vouloir quitter ?",
				"Quitter", 
				JOptionPane.YES_NO_OPTION
			);
			if(retour != 1) {				
				System.exit(0);
			}
			break;
		}
	}




}
