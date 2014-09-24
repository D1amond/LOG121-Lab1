package gui;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: FenetreFormes.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Fr�d�ric Bourdeau
2014-09-11 Utilisation des constantes dans le constructeur de la Dimension
*******************************************************/  

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JComponent;

import outils.ListeFormeAffiche;

/**
 * Cette fen�tre g�re l'affichage des formes 
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
		// Testing...
		formes.Carre carre = new formes.Carre("12345");
		carre.setX1(45).setY1(45).setX2(55).setY2(55);
		
		g.setColor(Color.blue);
		g.fillRect(carre.getX1(), carre.getY1(), carre.getX2(), carre.getY2());
	}
	
	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit r�server 
	 * l'espace n�cessaire àson affichage
	 */
	@Override 
	public Dimension getPreferredSize(){
		return dimension;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("test");
		if (arg0 instanceof ListeFormeAffiche) {
			System.out.println("FORME AJOUTÉE");
		}
	}
}
