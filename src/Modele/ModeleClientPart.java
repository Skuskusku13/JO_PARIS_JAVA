package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controleur.ClientPart;

public class ModeleClientPart extends ModeleUser {
	
	private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	public static void insertClientPart(ClientPart unClientPart) {
		String req = "CALL insertClientPar('"
				+unClientPart.getNom()+"', '"
				+unClientPart.getEmail()+"', '"
				+unClientPart.getMdp()+"', '"
				+unClientPart.getTel()+"', '"
				+unClientPart.getRole()+"', '"
				+unClientPart.getPrenom()+"');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(req);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch(SQLException exp) {
			System.out.println("Erreur d'execution de : " + req);
		}
	}
	
	public static ArrayList<ClientPart> selectAllClientPart() {
		String req = "SELECT * FROM vueclientpart;";
		ArrayList<ClientPart> lesClientsPart = new ArrayList<ClientPart>();
		
		try {
			uneBdd.seConnecter();
			Statement unstat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unstat.executeQuery(req);
			
			while (desResultats.next()) {
				ClientPart unClientPart = new ClientPart(
						desResultats.getInt("iduser"),
						desResultats.getString("nom"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("tel"),
						desResultats.getString("role"),
						desResultats.getString("prenom")
						);
				lesClientsPart.add(unClientPart);
				
			}
			unstat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'execution de la requete : " + req);
			System.out.println(e.getMessage());
		}
		return lesClientsPart;
	}
}
