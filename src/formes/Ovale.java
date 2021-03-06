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
Nom du fichier : Ovale.java
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
 * Classe représentant un ovale
 * @author Frédéric Bourdeau
 *
 */
public class Ovale extends Forme {

	private int centreX;
	private int centreY;
	private int rayonH;
	private int rayonV;
	
	/**
	 * Constructeur
	 */
	public Ovale(String nseq) {
		super(nseq);
	}
	
	/**
	 * @return CentreX
	 */
	public int getCentreX() {
		return this.centreX;
	}
	
	/**
	 * @param CentreX
	 * @return L'Ovale courant
	 */
	public Ovale setCentreX(int centreX) {
		this.centreX = centreX;
		return this;
	}
	
	/**
	 * @return CentreY
	 */
	public int getCentreY() {
		return this.centreY;
	}
	
	/**
	 * @param CentreY
	 * @return L'Ovale courant
	 */
	public Ovale setCentreY(int centreY) {
		this.centreY = centreY;
		return this;
	}
	
	/**
	 * @return RayonH
	 */
	public int getRayonH() {
		return this.rayonH;
	}
	
	/**
	 * @param RayonH
	 * @return L'Ovale courant
	 */
	public Ovale setRayonH(int rayonH) {
		this.rayonH = rayonH;
		return this;
	}
	
	/**
	 * @return RayonV
	 */
	public int getRayonV() {
		return this.rayonV;
	}
	
	/**
	 * @param RayonV
	 * @return L'Ovale courant
	 */
	public Ovale setRayonV(int rayonV) {
		this.rayonV = rayonV;
		return this;
	}

	@Override
	public void initCoordonnees(ArrayList<Integer> listePoint) {
		centreX = listePoint.get(0);
		centreY = listePoint.get(1);
		rayonH = listePoint.get(2);
		rayonV = listePoint.get(3);
	}

	@Override
	public void dessiner(Graphics g) {
		g.setColor(Color.cyan);
		g.fillOval(getCentreX(), getCentreY(), getRayonH(), getRayonV());
	}
	
	@Override
	public String toString() {
		return "Ovale " + getNseq() + " - " + getCentreX() + " " + getCentreY() + " " + getRayonH() + " " + getRayonV() + ";";
	}
	
}
