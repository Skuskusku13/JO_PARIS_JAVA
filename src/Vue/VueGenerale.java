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
	
	private JButton[] buttons = new JButton[8];
	private String[] buttonNames = {"btAdmin", "btClientPart", "btClientPro", "btCategorie", "btEvenement", "btServices", "btTypesServices","btQuitter"};
	private String[] buttonText = {"Administrateurs", "Clients Particulier", "Clients Pro", "Catégories", "Évènement", "Autre Services", "Types Services", "Quitter"};
	
	PanelAdmin unPanelAdmin = PanelAdmin.getInstance();
	PanelClientPart unPanelClientPart = PanelClientPart.getInstance();
	PanelClientPro unPanelClientPro = PanelClientPro.getInstance();
	PanelCategorie unPanelCategorie = PanelCategorie.getInstance();
	PanelEvenement unPanelEvenement = PanelEvenement.getInstance();
	PanelServices unPanelServices = PanelServices.getInstance();
	PanelTypesServices unPanelTypesServices = PanelTypesServices.getInstance();

	private static VueGenerale instance;
	
	public static VueGenerale getInstance() {
		if(instance == null) {
			instance = new VueGenerale();
		}
		return instance;
	}
	
	private VueGenerale() {

		this.setTitle("Gestion des JO 2024");
		this.setBounds(50, 20, 1350, 750);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setBackground(new Color(255, 255, 204));
		this.setLayout(null);
		
		// construction du panel menu
		this.panelMenu.setBounds(100, 40, 1100, 60);
		this.panelMenu.setLayout(new GridLayout(1, 8));
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
		this.add(unPanelServices);
		this.add(unPanelTypesServices);

		this.setVisible(false);

	}
	
	public void activerPanelPrincipal(int choix) {
		unPanelAdmin.setVisible(false);
		unPanelClientPart.setVisible(false);
		unPanelClientPro.setVisible(false);
		unPanelCategorie.setVisible(false);
		unPanelEvenement.setVisible(false);
		unPanelServices.setVisible(false);
		unPanelTypesServices.setVisible(false);
		
		switch(choix) {
		case 1: unPanelClientPart.setVisible(true); break;
		case 2: unPanelClientPro.setVisible(true); break;
		case 3: unPanelCategorie.setVisible(true); break;
		case 4: unPanelEvenement.setVisible(true); break;
		case 5: unPanelAdmin.setVisible(true); break;
		case 6: unPanelServices.setVisible(true); break;
		case 7: unPanelTypesServices.setVisible(true); break;
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
		case "btServices":
			this.activerPanelPrincipal(6);
			break;
		case "btTypesServices":
			this.activerPanelPrincipal(7);
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
