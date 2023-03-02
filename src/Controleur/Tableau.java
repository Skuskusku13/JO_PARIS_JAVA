package Controleur;

import javax.swing.table.AbstractTableModel;

public class Tableau extends AbstractTableModel{
	
	private static final long serialVersionUID = 1L;
	private Object [][] donnees;
	private String [] entetes;
	

	public Tableau(Object[][] donnees, String[] entetes) {
		super();
		this.donnees = donnees;
		this.entetes = entetes;
	}

	@Override
	public int getColumnCount() {
		return this.entetes.length;
	}

	@Override
	public int getRowCount() {
		return this.donnees.length;
	}

	@Override
	public Object getValueAt(int ligne, int colonne) {
		return this.donnees[ligne][colonne];
	}
	
	@Override
	public String getColumnName(int colonne) {
		//retourne le nom de la colonne
		return this.entetes[colonne];
	}
	
	public void insererLigne(Object[] ligne) {
		Object [] [] matrice = new Object[this.donnees.length + 1] [this.entetes.length];
		for (int i = 0; i < this.donnees.length; i++) {
			matrice[i] = this.donnees[i];
		}
		matrice[this.donnees.length] = ligne;
		this.donnees = matrice;
		this.fireTableDataChanged(); // actualiser la modification des donnees
	}
	
	public void supprimerLigne(int numLigne) {
		Object [] [] matrice = new Object[this.donnees.length -1] [this.entetes.length];
		int j = 0;
		for (int i = 0; i < this.donnees.length; i++) {
			if(i != numLigne) {
				matrice[j] = this.donnees[i];
				j++;
			}
		}
		this.donnees = matrice;
		this.fireTableDataChanged(); // actualiser la modification des donnees
	}
	
	public void modifierLigne(int numLigne, Object[] ligne) {
		Object [] [] matrice = new Object[this.donnees.length] [this.entetes.length];
		
		for (int i = 0; i < this.donnees.length; i++) {
			if(i == numLigne) {
				matrice[i] = ligne;
			} else {
				matrice[i] = this.donnees[i];
			}
		}
		this.donnees = matrice;
		this.fireTableDataChanged(); // actualiser la modification des donnees
	}
	
	public void setDonnees(Object [][] matrice) {
		this.donnees = matrice;
		this.fireTableDataChanged();
	}
}
