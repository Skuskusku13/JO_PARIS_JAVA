package Vue.VuePanel;

import java.awt.Color;

import javax.swing.JPanel;

public abstract class PanelPrincipal extends JPanel{

	private static final long serialVersionUID = 1L;

	public PanelPrincipal(Color uneCouleur) {
		this.setBounds(100, 100, 1000, 800);
		this.setBackground(uneCouleur);
		this.setLayout(null);
		
		this.setVisible(false);
	}

}
