package outils;

/******************************************************
Cours : LOG121
Session : A2014
Groupe : 03
Projet : Laboratoire #1
Étudiant(e)(s) : Frédéric Bourdeau
Code(s) perm. : BOUF10069403
Chargé de cours : Dominic St‐Jacques
Chargés de labo : Alvine Boaye Belle et Jean‐Nicola Blanchet
Nom du fichier : ListeFormeAffiche.java
Date créé : 2014‐09‐23
Date dern. modif. 2014‐09‐23
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-23 Version initiale
*******************************************************/

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Observable;

import formes.Forme;

public class ListeFormeAffiche extends Observable {

	final private int LIMITE = 10;
	
	private ArrayList<Forme> listeForme;
	
	public ListeFormeAffiche() {
		listeForme = new ArrayList<Forme>();
	}
	
	public boolean ajouterForme(Forme forme) {
		if (listeForme.size() >= LIMITE - 1) {
			retirerForme(LIMITE - 2);
		}
		boolean succes = this.listeForme.add(forme);
		if (succes) {
			setChanged();  
			notifyObservers();
		}
		return succes;
	}
	
	public Forme retirerForme(int position) {
		if (position < listeForme.size()) {
			return this.listeForme.remove(position);
		} else {
			return null;
		}
	}
	
	public Forme getFormeAt(int position) {
		if (position < listeForme.size()) {
			return this.listeForme.get(position);
		} else {
			return null;
		}
	}
	
	public ArrayList<Forme> getListeForme() {
		return this.listeForme;
	}
	
}
