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
Nom du fichier : Cercle.java
Date créé : 2014‐09‐11
Date dern. modif. 2014‐09‐11
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-11 Version initiale
*******************************************************/

/**
 * Classe représentant un cercle
 * @author Frédéric Bourdeau
 *
 */
public class Cercle extends Forme {

	private int centreX;
	private int centreY;
	private int rayon;
	
	/**
	 * Constructeur
	 */
	public Cercle(String nseq) {
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
	 * @return Le Cercle courant
	 */
	public Cercle setCentreX(int centreX) {
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
	 * @return Le Cercle courant
	 */
	public Cercle setCentreY(int centreY) {
		this.centreY = centreY;
		return this;
	}
	
	/**
	 * @return Rayon
	 */
	public int getRayon() {
		return this.rayon;
	}
	
	/**
	 * @param Rayon
	 * @return Le Cercle courant
	 */
	public Cercle setRayon(int rayon) {
		this.rayon = rayon;
		return this;
	}

	@Override
	public void initCoordonnees(ArrayList<Integer> listePoint) {
		centreX = listePoint.get(0);
		centreY = listePoint.get(1);
		rayon = listePoint.get(2);
	}

	@Override
	public void dessiner(Graphics g) {
		g.setColor(Color.red);
		g.fillOval(getCentreX(), getCentreY(), getRayon(), getRayon());
	}
	
}
