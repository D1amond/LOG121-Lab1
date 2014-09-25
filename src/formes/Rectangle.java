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
Nom du fichier : Rectangle.java
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
 * Classe représentant un rectangle
 * @author Frédéric Bourdeau
 *
 */
public class Rectangle extends Forme {

	private int x1;
	private int y1;
	private int x2;
	private int y2;
	
	/**
	 * Constructeur
	 */
	public Rectangle(String nseq) {
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
	 * @return Le Rectangle courant
	 */
	public Rectangle setX1(int x1) {
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
	 * @return Le Rectangle courant
	 */
	public Rectangle setY1(int y1) {
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
	 * @return Le Rectangle courant
	 */
	public Rectangle setX2(int x2) {
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
	 * @return Le Rectangle courant
	 */
	public Rectangle setY2(int y2) {
		this.y2 = y2;
		return this;
	}

	@Override
	public void initCoordonnees(ArrayList<Integer> listePoint) {
		x1 = listePoint.get(0);
		y1 = listePoint.get(1);
		x2 = listePoint.get(2);
		y2 = listePoint.get(3);
	}

	@Override
	public void dessiner(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(getX1(), getY1(), getX2(), getY2());
	}
	
	@Override
	public String toString() {
		return "Rectangle " + getNseq() + " - " + getX1() + " " + getY1() + " " + getX2() + " " + getY2() + ";";
	}
	
}
