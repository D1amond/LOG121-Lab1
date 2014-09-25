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
2014-09-23 Retrait test d'affichage et ajout affichage des formes contenues dans la liste
2014-09-24 Inversement de la liste lors de l'affichage pour toujours avoir la derni�re forme en premier plan
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
		Collections.reverse(liste.getListeForme());
		for (Forme forme : liste.getListeForme()) {
			forme.dessiner(g);
		}
		Collections.reverse(liste.getListeForme());
	}
	
	/*
	 * Le Layout qui utilise (contient) FenetreFormes doit r�server 
	 * l'espace n�cessaire àson affichage
	 */
	@Override 
	public Dimension getPreferredSize(){
		return dimension;
	}

	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof ListeFormeAffiche) {
			System.out.println("FORME AJOUTÉE: " + ((ListeFormeAffiche) arg0).getFormeAt(0) + " (" + ((ListeFormeAffiche) arg0).getListeForme().size() + ")");
		}
		repaint();
	}
}
