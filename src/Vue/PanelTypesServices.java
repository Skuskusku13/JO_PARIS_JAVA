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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import Controleur.C_TypesServices;
import Controleur.Tableau;
import Controleur.TypesServices;

public class PanelTypesServices extends PanelPrincipal implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	private JPanel panelTypesServices = new JPanel();
	private JPanel panelTable = new JPanel();
	private JTable tableTypesServices = new JTable();
	
	private Tableau unTableau;
	
	private JTextField[] textfields = new JTextField[1];
	private String[] jtfNames = {"txtLibelle"};
	private String[] jlNames = {"Libelle"};
	private String[] entetes = {"LIBELLE"};
	
	private JButton[] buttons = new JButton[2];
	private String[] btNames = {"btAnnuler", "btInscription"};
	private String[] btText = {"Annuler", "Inscription"};
	
	private static PanelTypesServices instance;
	
	public static PanelTypesServices getInstance() {
		if(instance == null) {
			instance = new PanelTypesServices();
		}
		return instance;
	}

	private PanelTypesServices() {
		super(new Color(255, 255, 255));
		
		this.panelTypesServices.setBounds(30, 50, 350, 150);
		this.panelTypesServices.setBackground(new Color(255, 255, 255));
		this.panelTypesServices.setLayout(new GridLayout(2, 2));
		
		
		for (int i = 0; i < jtfNames.length; i++) {
			textfields[i] = new JTextField();
			textfields[i].setName(jtfNames[i]);
			textfields[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			JLabel jl = new JLabel();
			jl.setText(jlNames[i]);
			jl.setFont(new Font("Paris2024", Font.ITALIC, 18));
			
			this.panelTypesServices.add(jl);
			this.panelTypesServices.add(textfields[i]);
		}
		
		for (int i = 0; i < btNames.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(btNames[i]);
			buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			buttons[i].setText(btText[i]);
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(btNames[i]);
			
			this.panelTypesServices.add(buttons[i]);
		}
		
		this.add(panelTypesServices);
		
		this.panelTable.setBounds(500, 50, 600, 500);
		this.panelTable.setBackground(new Color(255, 255, 255));
		this.panelTable.setLayout(null);
		
		this.unTableau = new Tableau(this.obtenirTypesServices(), entetes);
		this.tableTypesServices = new JTable(this.unTableau);
		
		JScrollPane unScroll = new JScrollPane(this.tableTypesServices);
		unScroll.setBounds(0, 0, 600, 350);
		this.panelTable.add(unScroll);
		
		
		this.tableTypesServices.addMouseListener(new MouseListener() {
			
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
					numLigne = tableTypesServices.getSelectedRow();
					int retour = JOptionPane.showConfirmDialog(null,
							"Voulez-vous supprimer ce TypeService ", 
							"suppresion TypeService",
							JOptionPane.YES_NO_OPTION
							);
					if(retour == 0) {
						//suppression du client de la base 
						String libelle = (String) unTableau.getValueAt(numLigne, 0);
						C_TypesServices.deleteTypeService(libelle);
						
						//suppression de la ligne dans le tableau
						unTableau.supprimerLigne(numLigne);
					}
				}
				
			}
		});
		this.add(panelTable);
	}
	
	public Object [][] obtenirTypesServices() {
		ArrayList<TypesServices> lesTypesServices = C_TypesServices.selectAllTypesServices();
		Object [][] matrice = new Object [lesTypesServices.size()][1];
		int i = 0;
		for(TypesServices unTypeService : lesTypesServices) {
			matrice [i][0] = unTypeService.getLibelle();
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
		
		String[] list = {libelle};
		
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
			TypesServices unTypeService = new TypesServices(libelle);
			if(unTypeService != null) {
				C_TypesServices.insertTypeService(unTypeService);
				JOptionPane.showMessageDialog(this,
						"Insertion du type service réussi");
				Object [] ligne  = {libelle};
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
