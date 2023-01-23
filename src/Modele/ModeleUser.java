package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Controleur.User;

public class ModeleUser {
	
private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	// fonction de connexion à la bdd 

	public static User selectWhereUser(String email, String mdp) {
		String req = "SELECT * FROM user WHERE email='"+email+"' AND mdp='"+mdp+"';";
		User unUser = null;
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet unResultat = unStat.executeQuery(req);
			//parcourrir les resultats pour créer un objet
			if (unResultat.next()) {
				unUser = new User(
						unResultat.getInt("iduser"),
						unResultat.getString("nom"),
						unResultat.getString("email"),
						unResultat.getString("mdp"),
						unResultat.getString("tel"),
						unResultat.getString("role")
						);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		} catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + req);
		}
		return unUser;
	}

}
