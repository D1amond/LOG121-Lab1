package exceptions;

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
Date créé : 2014‐09‐16
Date dern. modif. 2014‐09‐16
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-16 Version initiale
*******************************************************/

import javax.swing.JOptionPane;

public class EntreeInvalideException extends Exception {
	
	private static final long serialVersionUID = -2882044994696078871L;

	public EntreeInvalideException() {
		JOptionPane.showMessageDialog(null, "Le saisie que vous venez d'effectué est invalide.", "Saisie invalide", JOptionPane.ERROR_MESSAGE);
	}
}
