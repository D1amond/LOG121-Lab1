package gui;

/******************************************************
Cours : LOG121
Session : A2014
Groupe : 03
Projet : Laboratoire #1
Étudiant(e)(s) : Frédéric Bourdeau
Code(s) perm. : BOUF10069403
Chargé de cours : Dominic St‐Jacques
Chargés de labo : Alvine Boaye Belle et Jean‐Nicola Blanchet
Nom du fichier : FenetreFormes.java
Date créé : 2013-05-03
Date dern. modif. 2014‐09‐24
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Frédéric Bourdeau
2014-09-11 Utilisation des constantes dans le constructeur de la Dimension
2014-09-23 Retrait test d'affichage et ajout affichage des formes contenues dans la liste
2014-09-24 Inversement de la liste lors de l'affichage pour toujours avoir la dernière forme en premier plan
*******************************************************/

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Collections;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import formes.Forme;
import outils.ListeFormeAffiche;

/**
 * Cette fenêtre gère l'affichage des formes 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent implements Observer{
	
	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
	
	private ListeFormeAffiche liste;
		
	/**
	 * Constructeur
	 */
	public FenetreFormes(ListeFormeAffiche liste){
		this.liste = liste;
	}
	
	/*
	 * Affiche la liste de formes 
	 */
	@Override 
	public void paintComponent(Graphics g){
		Collections.reverse(liste.getListeForme());
		for (Forme forme : liste.getListeForme()) {
			forme.dessiner(g);
		}
		Collections.reverse(liste.getListeForme());
	}
	
	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit rï¿½server 
	 * l'espace nécessaire Ã son affichage
	 */
	@Override 
	public Dimension getPreferredSize(){
		return dimension;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 * Lorsque déclenché, redéssine la fenêtre.
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
}
