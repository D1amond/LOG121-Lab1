package outils;

import formes.Forme;

/******************************************************
Cours : LOG121
Session : A2014
Groupe : 03
Projet : Laboratoire #1
Étudiant(e)(s) : Frédéric Bourdeau
Code(s) perm. : BOUF10069403
Chargé de cours : Dominic St‐Jacques
Chargés de labo : Alvine Boaye Belle et Jean‐Nicola Blanchet
Nom du fichier : GenerateurForme.java
Date créé : 2014‐09‐23
Date dern. modif. 2014‐09‐23
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-23 Version initiale
*******************************************************/

public class GenerateurForme {
	
	private DecodeurChaine decodeur;
	private ListeFormeAffiche liste;
	
	public GenerateurForme() {
		decodeur = new DecodeurChaine();
	}

	public void generer(String chaine) {
		Forme forme = decodeur.decoder(chaine);
		liste.ajouterForme(forme);
	}
	
	public void setListeFormeAffiche(ListeFormeAffiche liste) {
		this.liste = liste;
	}
}
