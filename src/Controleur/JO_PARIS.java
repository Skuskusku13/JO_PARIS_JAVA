package Controleur;

import Vue.VueConnexion;
import Vue.VueGenerale;

public class JO_PARIS {
	
	private static VueConnexion uneVueConnexion;
	private static VueGenerale uneVueGenerale;
	
	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
		uneVueGenerale = new VueGenerale();
	}
	
	public static void gererVueConnexion(boolean active) {
		uneVueConnexion.setVisible(active);
	}
	
	public static void gererVueGenerale(boolean active) {
		uneVueGenerale.setVisible(active);
	}
}
