package exceptions;

import javax.swing.JOptionPane;

public class EntreeInvalideException extends Exception {
	
	private static final long serialVersionUID = -2882044994696078871L;

	public EntreeInvalideException() {
		JOptionPane.showMessageDialog(null, "Le saisie que vous venez d'effectué est invalide.", "Saisie invalide", JOptionPane.ERROR_MESSAGE);
	}
}
