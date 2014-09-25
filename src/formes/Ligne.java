package formes;

import java.awt.Color;
import java.awt.Graphics;
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
Nom du fichier : Ligne.java
Date créé : 2014‐09‐11
Date dern. modif. 2014‐09‐25
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-11 Version initiale
2014-09-23 Ajout override des méthodes abstraites
2014-09-25 Ajout override de toString()
*******************************************************/

/**
 * Classe représentant une ligne
 * @author Frédéric Bourdeau
 *
 */
public class Ligne extends Forme {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	/**
	 * Constructeur
	 */
	public Ligne(String nseq) {
		super(nseq);
	}
	
	/**
	 * @return X1
	 */
	public int getX1() {
		return this.x1;
	}
	
	/**
	 * @param X1
	 * @return La Ligne courante
	 */
	public Ligne setX1(int x1) {
		this.x1 = x1;
		return this;
	}
	
	/**
	 * @return Y1
	 */
	public int getY1() {
		return this.y1;
	}
	
	/**
	 * @param Y1
	 * @return La Ligne courante
	 */
	public Ligne setY1(int y1) {
		this.y1 = y1;
		return this;
	}
	
	/**
	 * @return X2
	 */
	public int getX2() {
		return this.x2;
	}
	
	/**
	 * @param X2
	 * @return La Ligne courante
	 */
	public Ligne setX2(int x2) {
		this.x2 = x2;
		return this;
	}
	
	/**
	 * @return Y2
	 */
	public int getY2() {
		return this.y2;
	}
	
	/**
	 * @param Y2
	 * @return La Ligne courante
	 */
	public Ligne setY2(int y2) {
		this.y2 = y2;
		return this;
	}

	/* (non-Javadoc)
	 * @see formes.Forme#initCoordonnees(java.util.ArrayList)
	 */
	@Override
	public void initCoordonnees(ArrayList<Integer> listePoint) {
		x1 = listePoint.get(0);
		y1 = listePoint.get(1);
		x2 = listePoint.get(2);
		y2 = listePoint.get(3);
	}

	/* (non-Javadoc)
	 * @see formes.Forme#dessiner(java.awt.Graphics)
	 */
	@Override
	public void dessiner(Graphics g) {
		g.setColor(Color.orange);
		g.drawLine(getX1(), getY1(), getX2(), getY2());
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Ligne " + getNseq() + " - " + getX1() + " " + getY1() + " " + getX2() + " " + getY2() + ";";
	}
	
}
