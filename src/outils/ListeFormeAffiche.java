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
Date dern. modif. 2014‐09‐24
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-23 Version initiale
2014-09-24 Correction de l'ajout d'une forme à la liste
2014-09-24 Génération de la Javadoc
*******************************************************/

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Observable;

import formes.Forme;

/**
 * @author Frédéric Bourdeau
 *
 */
public class ListeFormeAffiche extends Observable {

	final private int LIMITE = 10;
	
	private ArrayList<Forme> listeForme;
	
	/**
	 * Constructeur
	 */
	public ListeFormeAffiche() {
		listeForme = new ArrayList<Forme>();
	}
	
	/**
	 * @param la forme à ajouter à la liste.
	 */
	public void ajouterForme(Forme forme) {
		if (listeForme.size() >= LIMITE) {
			retirerForme(LIMITE - 1);
		}
		this.listeForme.add(0, forme);
		setChanged();  
		notifyObservers();
	}
	
	/**
	 * @param position à laquelle se trouve la forme à retirer de la liste.
	 * @return la forme retirée.
	 */
	public Forme retirerForme(int position) {
		if (position < listeForme.size()) {
			return this.listeForme.remove(position);
		} else {
			return null;
		}
	}
	
	/**
	 * @param position à laquelle se trouve la forme recherchée dans la liste.
	 * @return la forme recherchée.
	 */
	public Forme getFormeAt(int position) {
		if (position < listeForme.size()) {
			return this.listeForme.get(position);
		} else {
			return null;
		}
	}
	
	/**
	 * @return la liste des formes.
	 */
	public ArrayList<Forme> getListeForme() {
		return this.listeForme;
	}
	
}
