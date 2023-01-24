package Controleur;

import Vue.Vue_Connexions.VueConnexion;
import Vue.Vue_Connexions.VueInscriptionPart;
import Vue.Vue_Connexions.VueInscriptionPro;

public class JO_PARIS {

	private static VueConnexion uneVueConnexion;
	private static VueInscriptionPart uneVueInscriptionPart;
	private static VueInscriptionPro uneVueInscriptionPro;
//	private static VueGenerale uneVueGenerale;
	
	public static void main(String[] args) {
		uneVueConnexion = new VueConnexion();
		uneVueInscriptionPart = new VueInscriptionPart();
		uneVueInscriptionPro = new VueInscriptionPro();
//		uneVueGenerale = new VueGenerale();
	}
	
	public static void gererVueConnexion(boolean active) {
		uneVueConnexion.setVisible(active);
	}
	
	public static void gererVueInscriptionPart(boolean active) {
		uneVueInscriptionPart.setVisible(active);
	}
	
	public static void gererVueInscriptionPro(boolean active) {
		uneVueInscriptionPro.setVisible(active);
	}
	
//	public static void gererVueGenerale(boolean active) {
//		uneVueGenerale.setVisible(active);
//	}
}
