package formes;

import java.awt.Color;
import java.awt.Graphics;

/******************************************************
Cours : LOG121
Session : A2014
Groupe : 03
Projet : Laboratoire #1
Étudiant(e)(s) : Frédéric Bourdeau
Code(s) perm. : BOUF10069403
Chargé de cours : Dominic St‐Jacques
Chargés de labo : Alvine Boaye Belle et Jean‐Nicola Blanchet
Nom du fichier : Carre.java
Date créé : 2014‐09‐11
Date dern. modif. 2014‐09‐25
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-11 Version initiale
2014-09-12 Un carré est un rectangle
2014-09-23 Ajout override des méthodes abstraites
2014-09-25 Ajout override de toString()
*******************************************************/

/**
 * Classe représentant un carré
 * @author Frédéric Bourdeau
 *
 */
public class Carre extends Rectangle {

	/**
	 * Constructeur
	 */
	public Carre(String nseq) {
		super(nseq);
	}

	@Override
	public void dessiner(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(getX1(), getY1(), getX2(), getY2());
	}
	
	@Override
	public String toString() {
		return "Carré " + getNseq() + " - " + getX1() + " " + getY1() + " " + getX2() + " " + getY2() + ";";
	}
	
}
