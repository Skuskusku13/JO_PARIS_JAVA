package Vue;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Controleur.C_Categorie;
import Controleur.Categorie;

public class PanelCategorie extends PanelPrincipal implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	
	private JPanel panelCategorie = new JPanel();
	private JTextField txtLibelle = new JTextField();
	
	private JButton[] buttons = new JButton[2];
	private String[] buttonNames = {"btAnnuler", "btValider"};
	private String[] buttonText = {"Annuler", "Valider l'évènement"};
	
	public PanelCategorie() {
		super(new Color(255, 255, 255));
		
		this.panelCategorie.setBounds(30, 50, 350, 100);
		this.panelCategorie.setBackground(new Color(255, 255, 255));
		this.panelCategorie.setLayout(new GridLayout(2, 2));
		
		
		this.panelCategorie.add(new JLabel("Libelle")).setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.txtLibelle.setBorder(BorderFactory.createLineBorder(Color.black));
		this.txtLibelle.setFont(new Font("Paris2024", Font.ITALIC, 18));
		this.panelCategorie.add(this.txtLibelle);
		
		for (int i = 0; i < buttonNames.length; i++) {
			buttons[i] = new JButton();
			buttons[i].setName(buttonNames[i]);
			buttons[i].setFont(new Font("Paris2024", Font.ITALIC, 18));
			buttons[i].setText(buttonText[i]);
			buttons[i].addActionListener(this);
			buttons[i].setActionCommand(buttonNames[i]);
			
			this.panelCategorie.add(buttons[i]);
		}

		this.add(panelCategorie);
		
	}
	
	public void traitement() {
		String libelle = this.txtLibelle.getText();
		System.out.println(libelle);
		
		if(libelle.equals("")) {
			JOptionPane.showMessageDialog(this,
					"Veuillez remplir tous les champs", 
					"Erreur de saisie", 
					JOptionPane.CANCEL_OPTION
			);
		} else {
			Categorie uneCategorie = new Categorie(libelle);
			C_Categorie.insertCategorie(uneCategorie);
			if(uneCategorie != null) {
				JOptionPane.showMessageDialog(this,
						"Insertion de la categorie " + uneCategorie.getLibelle(), 
						"Insertion validée", 
						JOptionPane.DEFAULT_OPTION
				);
				this.txtLibelle.setText("");
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String command = e.getActionCommand();
		
		switch (command) {
		case "btAnnuler":
			this.txtLibelle.setText("");
			break;

		case "btValider":
			this.traitement();
			break;
		}
		
	}

}
