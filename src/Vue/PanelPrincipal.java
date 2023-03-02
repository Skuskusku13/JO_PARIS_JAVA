package Vue;

import java.awt.Color;

import javax.swing.JPanel;

public class PanelPrincipal extends JPanel {
	
	private static final long serialVersionUID = 1L;

	public PanelPrincipal(Color uneCouleur) {
		this.setBounds(100, 150, 1200, 500);
		this.setBackground(uneCouleur);
		this.setLayout(null);
		
		this.setVisible(false);	
	}

}
