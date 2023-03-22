

import Vue.VueConnexion;
import Vue.VueGenerale;

public class JO_PARIS {

	public static void main(String[] args) {
	}
	
	public static void gererVueConnexion(boolean active) {
		VueConnexion.getInstance().setVisible(active);
	}
	
	public static void gererVueGenerale(boolean active) {
		VueGenerale.getInstance().setVisible(active);
	}
}
