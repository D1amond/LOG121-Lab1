package gui;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date cr√©√©: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
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
 * Base d'une communication via un fil d'ex√©cution parall√®le.
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
	 * DÈfinir le rÈcepteur de l'information reÁue dans la communication avec le serveur
	 * @param listener sera alert√© lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * D√©marre la communication
	 */
	public void start(){
		creerCommunication();
	}
	
	/**
	 * Arr√™te la communication
	 */
	public void stop(){
		if(threadComm!=null)
			threadComm.cancel(true); 
		isActif = false;
	}
	
	/**
	 * CrÈer le nÈcessaire pour la communication avec le serveur
	 */
	protected void creerCommunication(){		
		// CrÈe un fil d'exÈcusion parallËle au fil courant,
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

 					//La mÈthode suivante alerte l'observateur 
					if(listener!=null)
					   firePropertyChange("ENVOIE-TEST", null, (Object) "."); 
				}
				//return null;
			}
		};
		if(listener!=null)
		   threadComm.addPropertyChangeListener(listener); // La mÈthode "propertyChange" de ApplicationFormes sera donc appel√©e lorsque le SwinkWorker invoquera la m√©thode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'ex√©cution parall√®le.
		isActif = true;
	}
	
	/**
	 * @return si le fil d'ex√©cution parall√®le est actif
	 */
	public boolean isActif(){
		return isActif;
	}
	
	/**
	 * @return l'hÙte extrait de la chaÓne de caractËres entrÈe par l'utilisateur
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
	 * @return l'hÙte extrait de la chaÓne de caractËres entrÈe par l'utilisateur
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
	 * @return la sous-chaÓne de caractËres recherchÈe
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
	 * @return l'hÙte et le port sous la forme "hÙte:port"
	 */
	private String getHostAndPort() {
		String hostAndPort = "";
		if (this.host.length() == 0 || this.port == 0) {
			hostAndPort = JOptionPane.showInputDialog("Quel est le nom d'hÙte et le port du serveur de formes?");
		} else {
			hostAndPort = this.host + ":" + this.port;
		}
		return hostAndPort;
	}
}
