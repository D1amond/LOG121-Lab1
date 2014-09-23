package outils;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import formes.*;

public class DecodeurChaine {

	final private String REGEX_TYPE_FORME = "([A-Z])\\w+";
	final private String REGEX_NSEQ = "([0-9])\\w+";
	final private String REGEX_COORDONNEES = "";
	
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
	
	private void initForme(Forme forme, String chaine) {
		ArrayList<Integer> listePoint = new ArrayList<Integer>();
		Pattern pattern = Pattern.compile(REGEX_COORDONNEES);
		Matcher matcher = pattern.matcher(chaine);
		boolean result = matcher.find();
		
		forme.initCoordonnees(listePoint);
	}
	
	private String getTypeForme(String chaine) {
		Pattern pattern = Pattern.compile(REGEX_TYPE_FORME);
		return findMatch(pattern, chaine);
	}
	
	private String getNseq(String chaine) {
		Pattern pattern = Pattern.compile(REGEX_NSEQ);
		return findMatch(pattern, chaine);
	}

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
