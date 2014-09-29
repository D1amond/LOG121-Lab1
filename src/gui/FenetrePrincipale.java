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
Nom du fichier : FenetrePrincipale.java
Date créé : 2013-05-03
Date dern. modif. 2014‐09‐23
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Frédéric Bourdeau
2014-09-23 Ajout de la référence à la liste de forme et création du lien Observateur
*******************************************************/

import java.awt.BorderLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JFrame;

import outils.ListeFormeAffiche;
import outils.ServiceCommunication;
 
/**
 * Cette classe représente la fenêtre principale de l'application 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class FenetrePrincipale extends JFrame implements PropertyChangeListener{
	
	private static final long serialVersionUID = -1210804336046370508L;

	/**
	 * Constructeur
	 */
	public FenetrePrincipale(ServiceCommunication comm, ListeFormeAffiche liste){
		
		MenuFenetre menu = new MenuFenetre(comm);
		this.setLayout(new BorderLayout());
		this.add(menu, BorderLayout.NORTH); 
		FenetreFormes fenetreFormes = new FenetreFormes(liste);
		liste.addObserver(fenetreFormes);
		this.add(fenetreFormes, BorderLayout.CENTER); // Ajoute la fenêtre de forme à la fenêtre principale
		this.pack(); // Ajuste la dimension de la fenêtre principale selon celle de ses composants
		this.setVisible(true); // Rend la fenÃªtre principale visible.
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //... À  réviser selon le comportement que vous désirez ...
	}
	
	// Appelé lorsque le sujet lance "firePropertyChanger"
	@Override
	public void propertyChange(PropertyChangeEvent arg0) {
		
		if(arg0.getPropertyName().equals("ENVOIE-FORME")){
			repaint();
		}
	}
}
