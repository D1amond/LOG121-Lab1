package gui;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date crÃ©Ã©: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Frédéric Bourdeau
2014-09-16 Ajout Hôte et Port; Prototype de connexion
*******************************************************/  

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import exceptions.EntreeInvalideException;

/**
 * Base d'une communication via un fil d'exécution parallèle.
 */
public class CommBase {
	
	private final int DELAI = 1000;
	private final String HOST_REGEX = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)|localhost";
	private final String PORT_REGEX = "(?<=:)(6553[0-5]|655[0-2][0-9]\\d|65[0-4](\\d){2}|6[0-4](\\d){3}|[1-5](\\d){4}|[1-9](\\d){0,3})";
	private SwingWorker threadComm =null;
	private PropertyChangeListener listener = null;
	private boolean isActif = false;
	private String host = "";
	private int port = 0;
	
	/**
	 * Constructeur
	 */
	public CommBase(){
	}
	
	/**
	 * Définir le récepteur de l'information reçue dans la communication avec le serveur
	 * @param listener sera alertÃ© lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * DÃ©marre la communication
	 */
	public void start(){
		creerCommunication();
	}
	
	/**
	 * ArrÃªte la communication
	 */
	public void stop(){
		if(threadComm!=null)
			threadComm.cancel(true); 
		isActif = false;
	}
	
	/**
	 * Créer le nécessaire pour la communication avec le serveur
	 */
	protected void creerCommunication(){		
		// Crée un fil d'exécusion parallèle au fil courant,
		threadComm = new SwingWorker(){
			@Override
			protected Object doInBackground() throws Exception {
				System.out.println("Le fils d'execution parallele est lance");
				while(true){
					Thread.sleep(DELAI);
					
					Socket MyClient;
					try {
						MyClient = new Socket(getHost(), getPort());
					} catch (IOException e) {
						System.out.println(e);
					}

 					//La méthode suivante alerte l'observateur 
					if(listener!=null)
					   firePropertyChange("ENVOIE-TEST", null, (Object) "."); 
				}
				//return null;
			}
		};
		if(listener!=null)
		   threadComm.addPropertyChangeListener(listener); // La méthode "propertyChange" de ApplicationFormes sera donc appelÃ©e lorsque le SwinkWorker invoquera la mÃ©thode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'exÃ©cution parallÃ¨le.
		isActif = true;
	}
	
	/**
	 * @return si le fil d'exÃ©cution parallÃ¨le est actif
	 */
	public boolean isActif(){
		return isActif;
	}
	
	/**
	 * @return l'hôte extrait de la chaîne de caractères entrée par l'utilisateur
	 */
	private String getHost() {
		if (this.host.length() == 0) {
			Pattern pattern = Pattern.compile(HOST_REGEX);
			try {
				this.host = findMatch(pattern);
			} catch (EntreeInvalideException e) {
				e.printStackTrace();
			}
		}
		return this.host;
	}
	
	/**
	 * @return l'hôte extrait de la chaîne de caractères entrée par l'utilisateur
	 */
	private int getPort() {
		if (this.port == 0) {
			Pattern pattern = Pattern.compile(PORT_REGEX);
			try {
				this.port = Integer.parseInt(findMatch(pattern));
			} catch (EntreeInvalideException e) {
				e.printStackTrace();
			}
		}
		return this.port;
	}
	
	/**
	 * @param pattern
	 * @return la sous-chaîne de caractères recherchée
	 * @throws EntreeInvalideException
	 */
	private String findMatch(Pattern pattern) throws EntreeInvalideException {
		String match = "";
		Matcher matcher = pattern.matcher(this.getHostAndPort());
		boolean result = matcher.find();
		if (result) {
			match = matcher.group(1);
		}
		if (match.length() == 0) {
			throw new exceptions.EntreeInvalideException();
		}
		return match;
	}
	
	/**
	 * @return l'hôte et le port sous la forme "hôte:port"
	 */
	private String getHostAndPort() {
		String hostAndPort = "";
		if (this.host.length() == 0 || this.port == 0) {
			hostAndPort = JOptionPane.showInputDialog("Quel est le nom d'hôte et le port du serveur de formes?");
		} else {
			hostAndPort = this.host + ":" + this.port;
		}
		return hostAndPort;
	}
}
