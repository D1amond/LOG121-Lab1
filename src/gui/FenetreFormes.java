package gui;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date crÃ©Ã©: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Frédéric Bourdeau
2014-09-11 Utilisation des constantes dans le constructeur de la Dimension
*******************************************************/  

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

/**
 * Cette fenêtre gère l'affichage des formes 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetreFormes extends JComponent{
	
	private static final long serialVersionUID = -2262235643903749505L;
	public static final int WIDTH = 500;
	public static final int HEIGHT = 500;
	public static final Dimension dimension = new Dimension(WIDTH, HEIGHT);
		
	/**
	 * Constructeur
	 */
	public FenetreFormes(){
		//... 
	}
	
	/*
	 * Affiche la liste de formes 
	 */
	@Override 
	public void paintComponent(Graphics g){
		// Testing...
		formes.Carre carre = new formes.Carre("12345");
		carre.setX1(45).setY1(45).setX2(55).setY2(55);
		
		g.setColor(Color.blue);
		g.fillRect(carre.getX1(), carre.getY1(), carre.getX2(), carre.getY2());
	}
	
	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit réserver 
	 * l'espace nécessaire Ã son affichage
	 */
	@Override 
	public Dimension getPreferredSize(){
		return dimension;
	}
}
