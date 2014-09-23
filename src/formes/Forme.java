package formes;

import java.util.ArrayList;

/******************************************************
Cours : LOG121
Session : A2014
Groupe : 03
Projet : Laboratoire #1
Étudiant(e)(s) : Frédéric Bourdeau
Code(s) perm. : BOUF10069403
Chargé de cours : Dominic St‐Jacques
Chargés de labo : Alvine Boaye Belle et Jean‐Nicola Blanchet
Nom du fichier : Forme.java
Date créé : 2014‐09‐11
Date dern. modif. 2014‐09‐11
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-11 Version initiale
*******************************************************/

/**
 * Classe abstraite représentant une forme géométrique de base
 * @author Frédéric Bourdeau
 *
 */
public abstract class Forme {

	private String nseq;
	
	public abstract void initCoordonnees(ArrayList<Integer> listePoint);
	
	/**
	 * Constructeur
	 */
	public Forme(String nseq) {
		setNseq(nseq);
	}
	
	/**
	 * @return Numéro de séquence
	 */
	public String getNseq() {
		return this.nseq;
	}
	
	/**
	 * @param nseq
	 * @return La Forme courante
	 */
	public Forme setNseq(String nseq) {
		this.nseq = nseq;
		return this;
	}
	
}
