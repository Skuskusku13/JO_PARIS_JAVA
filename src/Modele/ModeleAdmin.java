package Modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxDatatypes.Array;

import Controleur.Admin;

public class ModeleAdmin {

	private static Bdd uneBdd = new Bdd("localhost:8889", "jo_paris", "root", "root");
	
	public static void insertAdmin(Admin unAdmin) {
		String req = "INSERT INTO user VALUES(NULL, '"
				+unAdmin.getNom()+"', '"
				+unAdmin.getEmail()+"', '"
				+unAdmin.getMdp()+"', '"
				+unAdmin.getTel()+"', '"
				+unAdmin.getRole()+"');";
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(req);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'execution de la requete : " + req);
		}
	}
	
	public static ArrayList<Admin> selectAllAdmin() {
		
		String req = "SELECT * FROM USER where role='admin'";
		ArrayList<Admin> lesAdmins = new ArrayList<Admin>();
		
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			ResultSet desResultats = unStat.executeQuery(req);
			while(desResultats.next() ) {
				Admin unAdmin = new Admin(
						desResultats.getInt("iduser"),
						desResultats.getString("nom"),
						desResultats.getString("email"),
						desResultats.getString("mdp"),
						desResultats.getString("tel"),
						desResultats.getString("role")
						);
				lesAdmins.add(unAdmin);
			}
			unStat.close();
			uneBdd.seDeconnecter();
			
		} catch (SQLException e) {
			System.out.println("Erreur d'execution de la requete : " + req);
			System.out.println(e.getMessage());
		}
		
		return lesAdmins;
	}
	
}
