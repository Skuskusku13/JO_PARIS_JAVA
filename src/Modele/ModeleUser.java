package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controleur.User;

public class ModeleUser {
	
private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	// fonction de connexion à la bdd 

	public static User selectWhereUser(String email, String mdp) {
		String req = "SELECT * FROM User WHERE email='"+email+"' AND mdp='"+mdp+"' AND role='admin';";
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

	
	public static ArrayList<User> selectAllUser() {
		String req = "SELECT * FROM User;";
		ArrayList<User> lesUser = new ArrayList<User>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(req);
			
			while(desResultats.next()) {
				User unUser = new User(
						desResultats.getInt("iduser"),
						desResultats.getString("nom"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("tel"),
						desResultats.getString("role")
						);
				lesUser.add(unUser);
			}
			
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'execution de la requete : " + req);
			System.out.println(e.getMessage());
		}
		return lesUser;
	}
}
