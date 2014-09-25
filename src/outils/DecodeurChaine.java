package outils;

/******************************************************
Cours : LOG121
Session : A2014
Groupe : 03
Projet : Laboratoire #1
Étudiant(e)(s) : Frédéric Bourdeau
Code(s) perm. : BOUF10069403
Chargé de cours : Dominic St‐Jacques
Chargés de labo : Alvine Boaye Belle et Jean‐Nicola Blanchet
Nom du fichier : DecodeurChaine.java
Date créé : 2014‐09‐23
Date dern. modif. 2014‐09‐24
*******************************************************
Historique des modifications
*******************************************************
*@author Frédéric Bourdeau
2014-09-23 Version initiale
2014-09-24 Amelioration du regex des coordonnées
2014-09-24 Génération de la Javadoc
*******************************************************/

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import formes.*;

/**
 * @author Frédéric Bourdeau
 *
 */
public class DecodeurChaine {

	final private String REGEX_TYPE_FORME = "([A-Z])\\w+";
	final private String REGEX_NSEQ = "([0-9])\\w+";
	final private String REGEX_COORDONNEES = "([0-9])\\w+|[0-9]";
	final private int LIMITE_TAILLE_COORDONNEE = 3;
	
	/**
	 * @param la chaine de caractères qui contient les informations à décoder.
	 * @return la forme créée à partir de la chaine de caractères.
	 */
	public Forme decoder(String chaine) {
		String type = getTypeForme(chaine);
		String nseq = getNseq(chaine);
		Forme forme = null;
		if (type.matches("CARRE")) {
			forme = new Carre(nseq);
		} else if (type.matches("RECTANGLE")) {
			forme = new Rectangle(nseq);
		} else if (type.matches("LIGNE")) {
			forme = new Ligne(nseq);
		} else if (type.matches("CERCLE")) {
			forme = new Cercle(nseq);
		} else if (type.matches("OVALE")) {
			forme = new Ovale(nseq);
		}
		initForme(forme, chaine);
		return forme;
	}
	
	/**
	 * @param la forme à initialiser.
	 * @param chaine la chaine de caractères contenant les coordonnées de la forme.
	 */
	private void initForme(Forme forme, String chaine) {
		ArrayList<Integer> listePoint = new ArrayList<Integer>();
		Pattern pattern = Pattern.compile(REGEX_COORDONNEES);
		Matcher matcher = pattern.matcher(chaine);
		
		while (matcher.find()) {
			String coordonnee = matcher.group();
			if (coordonnee.length() <= LIMITE_TAILLE_COORDONNEE) {
				listePoint.add(Integer.parseInt(coordonnee));
			}
	    }
		
		forme.initCoordonnees(listePoint);
	}
	
	/**
	 * @param la chaine de caractères contenant le type de forme.
	 * @return le type de la forme sous la forme "TYPEFORME".
	 */
	private String getTypeForme(String chaine) {
		Pattern pattern = Pattern.compile(REGEX_TYPE_FORME);
		return findMatch(pattern, chaine);
	}
	
	/**
	 * @param la chaine de caractères contenant le numéro de séquence de la forme.
	 * @return le numéro de séquence de la forme.
	 */
	private String getNseq(String chaine) {
		Pattern pattern = Pattern.compile(REGEX_NSEQ);
		return findMatch(pattern, chaine);
	}

	/**
	 * @param le pattern de l'information à extraire.
	 * @param la chaine de caractères qui contient l'information.
	 * @return l'information selon le pattern.
	 */
	private String findMatch(Pattern pattern, String chaine) {
		Matcher matcher = pattern.matcher(chaine);
		boolean result = matcher.find();
		if (result) {
			return matcher.group(0);
		} else {
			return null;
		}
	}
}
