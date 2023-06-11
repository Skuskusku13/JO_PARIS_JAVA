package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.C_Services;
import Controleur.C_TypesServices;
import Controleur.Services;
import Controleur.Tableau;
import Controleur.TypesServices;

public class PanelServices extends PanelPrincipal implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel panelServices = new JPanel();
	private JPanel panelTable = new JPanel();
	private JTable tableServices = new JTable();
	
	private Tableau unTableau;
	
	private JTextField[] textfields = new JTextField[6];
	private String[] jtfNames = {"txtLibelle", "txtAdresse", "txtPrix", "txtTel", "txtEmail", "txtImage"};
	private String[] jlNames = {"Libelle", "Adresse", "Prix", "Telephone", "Email", "Chemin Image"};
	private String[] entetes = {"LIBELLE", "ADRESSE", "PRIX", "TEL", "EMAIL"};
	
	private JButton[] buttons = new JButton[2];
	private String[] btNames = {"btAnnuler", "btInscription"};
	private String[] btText = {"Annuler", "Inscription"};
	
	private JComboBox<String> combox = new JComboBox<String>();
	private ArrayList<TypesServices> lesTypesServices = C_TypesServices.selectAllTypesServices();
	
	
	private static PanelServices instance;
	
	public static PanelServices getInstance() {
		if(instance == null) {
			instance = new PanelServices();
		}
		return instance;
	}
	
	private PanelServices() {
		super(new Color(255, 255, 255));
		
		this.panelServices.setBounds(30, 50, 350, 400);
		this.panelServices.setBackground(new Color(255, 255, 255));
		this.panelServices.setLayout(new GridLayout(8, 2));
		
		
		for (int i = 0; i < jtfNames.length; i++) {
			textfields[i] = new JTextField();
			textfields[i].setName(jtfNames[i]);
			textfields[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			JLabel jl = new JLabel();
			jl.setText(jlNames[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			this.panelServices.add(jl);
			this.panelServices.add(textfields[i]);
		}
		
		for (TypesServices unTypeService : lesTypesServices) {
			String nomTypeService = unTypeService.getIdTypeService() + " -- " + unTypeService.getLibelle();
	//		System.out.println(nomCategorie);
			this.combox.addItem(nomTypeService);
		}
		
		this.panelServices.add(new JLabel("Types Services")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelServices.add(combox);
		
		for (int i = 0; i < btNames.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(btNames[i]);
			buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			buttons[i].setText(btText[i]);
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(btNames[i]);
			
			this.panelServices.add(buttons[i]);
		}
		
		this.add(panelServices);
		
		this.panelTable.setBounds(500, 50, 600, 500);
		this.panelTable.setBackground(new Color(255, 255, 255));
		this.panelTable.setLayout(null);
		
		this.unTableau = new Tableau(this.obtenirServices(), entetes);
		this.tableServices = new JTable(this.unTableau);
		
		JScrollPane unScroll = new JScrollPane(this.tableServices);
		unScroll.setBounds(0, 0, 600, 350);
		this.panelTable.add(unScroll);
				
		this.tableServices.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne;
				if(e.getClickCount() == 2) {
					numLigne = tableServices.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null,
							"Voulez-vous supprimer ce Service ", 
							"suppresion Service",
							JOptionPane.YES_NO_OPTION
							);
					if(retour == 0) {
						//suppression du client de la base 
						String libelle = (String) unTableau.getValueAt(numLigne, 0);
						String tel = (String) unTableau.getValueAt(numLigne, 3);
						C_Services.deleteService(libelle, tel);
						
						//suppression de la ligne dans le tableau
						unTableau.supprimerLigne(numLigne);
					}
				}
			}
		});
		this.add(panelTable);
	}


	public Object [][] obtenirServices() {
		ArrayList<Services> lesServices = C_Services.selectAllServices();
		Object [][] matrice = new Object [lesServices.size()][5];
		int i = 0;
		for(Services unService : lesServices) {
			matrice [i][0] = unService.getLibelle();
			matrice [i][1] = unService.getAdresse();
			matrice [i][2] = unService.getPrix();
			matrice [i][3] = unService.getTel();
			matrice [i][4] = unService.getEmail();
			i++;
		}
		
		return matrice;
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
			break;
		}
	}
	
	public void traitement() {
		String libelle = textfields[0].getText();
		String adresse = textfields[1].getText();
		String tel = textfields[3].getText();
		String email = textfields[4].getText();
		String image = textfields[5].getText();
		int idtypeservices = this.combox.getSelectedIndex() + 1;		
		float prix = 0.0f;
		
		String[] list = {libelle, adresse, tel, email, image};
		
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
			try {
				prix = Float.parseFloat(textfields[2].getText());
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(this, 
						"Merci de rentrer le format 00.0 dans la case prix");
			}
			
			Services unService = new Services(libelle, adresse, prix, tel, email, image, idtypeservices);
			if(unService != null) {
				C_Services.insertService(unService);
				JOptionPane.showMessageDialog(this,
						"Insertion du service réussi");
				Object [] ligne  = {libelle, adresse, prix, tel, email, image};
				this.unTableau.insererLigne(ligne);
				this.actionChamps(2);
			}
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "btAnnuler":
			this.actionChamps(2);
			break;
		case "btInscription":
			this.actionChamps(1);
			break;
		}
		
	}

}
