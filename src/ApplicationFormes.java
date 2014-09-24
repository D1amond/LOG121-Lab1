/******************************************************
Cours : LOG121
Session : A2014
Groupe : 03
Projet : Laboratoire #1
Étudiant(e)(s) : Frédéric Bourdeau
Code(s) perm. : BOUF10069403
Chargé de cours : Dominic St‐Jacques
Chargés de labo : Alvine Boaye Belle et Jean‐Nicola Blanchet
Nom du fichier : ApplicationFormes.java
Date créé : 2014‐09‐08
Date dern. modif. 2014‐09‐09
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013/05/04 Version initiale
*@author Frédéric Bourdeau
2014‐09‐11 Deplacer le fichier dans le package par défaut
*******************************************************/

import gui.FenetrePrincipale;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import outils.GenerateurForme;
import outils.ListeFormeAffiche;
import outils.ServiceCommunication;

/**
 * Cette classe représente l'application dans son ensemble. 
 * @author Patrice Boucher
 * @date 2013/05/04
 */
public class ApplicationFormes{
	
	/**
	 * main de l'application
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationFormes application = new ApplicationFormes();
	}
	
	/**
	 * Constructeur
	 */
	public ApplicationFormes(){
		ServiceCommunication comm = new ServiceCommunication();
		
		GenerateurForme generateur = new GenerateurForme();
		ListeFormeAffiche liste = new ListeFormeAffiche();
		generateur.setListeFormeAffiche(liste);
		comm.setGenerateur(generateur);
		
		FenetrePrincipale fenetre = new FenetrePrincipale(comm, liste);
		comm.setPropertyChangeListener(fenetre);
	}
}
