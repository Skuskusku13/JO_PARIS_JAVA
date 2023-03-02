package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Controleur.ClientPro;

public class ModeleClientPro extends ModeleUser {

private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");

	
	public static void insertClientPro(ClientPro unClientPro) {
		String req = "CALL insertClientPro('"
				+unClientPro.getNom()+"', '"
				+unClientPro.getEmail()+"', '"
				+unClientPro.getMdp()+"', '"
				+unClientPro.getTel()+"', '"
				+unClientPro.getRole()+"', '"
				+unClientPro.getNum_siret()+"', '"
				+unClientPro.getAdresse()+"');";
		
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
	
	public static ArrayList<ClientPro> selectAllClientPro() {
		String req = "SELECT * FROM vueclientpro;";
		ArrayList<ClientPro> lesClientsPro = new ArrayList<ClientPro>();
		
		try {
			uneBdd.seConnecter();
			Statement unstat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unstat.executeQuery(req);
			
			while (desResultats.next()) {
				ClientPro unClientPro = new ClientPro(
						desResultats.getInt("iduser"),
						desResultats.getString("nom"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("tel"),
						desResultats.getString("role"),
						desResultats.getString("num_siret"),
						desResultats.getString("adresse")
						);
				lesClientsPro.add(unClientPro);
				
			}
			unstat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'execution de la requete : " + req);
			System.out.println(e.getMessage());
		}
		return lesClientsPro;
	}
}
