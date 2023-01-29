package Modele;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import Controleur.Evenement;

public class ModeleEvenement {
	
	private static Bdd uneBdd = new Bdd("localhost:8880", "jo_paris", "root", "root");

	public static void insertEvenement(Evenement unEvenement) {
		String req = "INSERT INTO evenement VALUES(NULL, '"
				+ unEvenement.getType() + "', '"
				+ unEvenement.getDateEvent() + "', '"
				+ unEvenement.getNomEvenement()+ "', '"
				+ unEvenement.getDescription() +"', '"
				+ unEvenement.getAdresse() + "', '"
				+ unEvenement.getHorraireD() + "', '"
				+ unEvenement.getHorraireF() + "', '"
				+ unEvenement.getCapacite() + "', '"
				+ unEvenement.getIdcategorie() + "');";
		try {
			uneBdd.seConnecter();
			Statement unStat = uneBdd.getMaConnexion().createStatement();
			unStat.execute(req);
			unStat.close();
			uneBdd.seDeconnecter();
		} catch (SQLException e) {
			System.out.println("Erreur d'execution de : " + req);
		}
	}

	
	public static void insertEvenement2(Evenement unEvenement) {
		String req = "INSERT INTO evenement (type, dateEvent, nomEvenement, description, adresse, horraireD, horraireF, capacite, idcategorie)"
				+ " VALUES(NULL, ?, ?, ?, ?, ?, ?, ?, ? ,?);";
		try {
			uneBdd.seConnecter();
			PreparedStatement statement = uneBdd.getMaConnexion().prepareStatement(req);
			statement.setString(1, unEvenement.getType());
			statement.setString(2, unEvenement.getDateEvent());
			statement.setString(3, unEvenement.getNomEvenement());
			statement.setString(4, unEvenement.getDescription());
			statement.setString(5, unEvenement.getAdresse());
			statement.setString(6, unEvenement.getHorraireD());
			statement.setString(7, unEvenement.getHorraireF());
			statement.setInt(8, unEvenement.getCapacite());
			statement.setLong(9, unEvenement.getIdcategorie());
			statement.executeQuery();
			statement.close();
			uneBdd.seDeconnecter();
		}catch (SQLException e) {
			e.getMessage();
		}
	}
}
