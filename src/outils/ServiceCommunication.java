package outils;
/******************************************************
Cours:  LOG121
Projet: Squelette du laboratoire #1
Nom du fichier: CommBase.java
Date créé: 2013-05-03
*******************************************************
Historique des modifications
*******************************************************
*@author Patrice Boucher
2013-05-03 Version initiale
*@author Fr�d�ric Bourdeau
2014-09-16 Ajout H�te et Port; Prototype de connexion
2014-09-23 Connexion et communication serveur fonctionnelle
2014-09-24 G�n�ration de la Javadoc manquante
*******************************************************/  

import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import exceptions.EntreeInvalideException;

/**
 * Base d'une communication via un fil d'ex�cution parall�le.
 */
public class ServiceCommunication {
	
	private final int DELAI = 1000;
	private final String HOST_REGEX = "\\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)|localhost";
	private final String PORT_REGEX = "(?<=:)(6553[0-5]|655[0-2][0-9]\\d|65[0-4](\\d){2}|6[0-4](\\d){3}|[1-5](\\d){4}|[1-9](\\d){0,3})";
	
	private SwingWorker threadComm = null;
	private PropertyChangeListener listener = null;
	private GenerateurForme generateur;
	private boolean isActif = false;
	private String hostAndPort = "";
	private String host = "";
	private int port = 0;
	
	private Socket client = null;
	private PrintWriter out = null;
	private BufferedReader in = null;
	
	/**
	 * Constructeur
	 */
	public ServiceCommunication(){
	}
	
	/**
	 * D�finir le r�cepteur de l'information re�ue dans la communication avec le serveur
	 * @param listener sera alerté lors de l'appel de "firePropertyChanger" par le SwingWorker
	 */
	public void setPropertyChangeListener(PropertyChangeListener listener){
		this.listener = listener;
	}
	
	/**
	 * Démarre la communication
	 */
	public void start(){
		creerCommunication();
	}
	
	/**
	 * Arrête la communication
	 */
	public void stop(){
		if(threadComm!=null)
			threadComm.cancel(true);
		if(client != null && out != null) {
			try {
				out.println("END");
				client.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		isActif = false;
	}
	
	/**
	 * Cr�er le n�cessaire pour la communication avec le serveur
	 */
	protected void creerCommunication(){		
		// Cr�e un fil d'ex�cusion parall�le au fil courant,
		threadComm = new SwingWorker(){
			@Override
			protected Object doInBackground() throws Exception {
				try {
					System.out.println("Le fils d'execution parallele est lance!");
					listenSocket();
					while(client!= null){
						Thread.sleep(DELAI);
				        out.println("GET");
				        
				        //Retire le "commande>" envoye par le serveur. Pourrait etre remplace par regex
				        in.readLine();
				        
				        String reponse = in.readLine();
				        System.out.println(reponse);
				        generateur.generer(reponse);
					}
				} catch (EntreeInvalideException e) {
					resetConnexion();
					stop();
				} catch (Exception e) {
					System.out.println(e);
					stop();
				}
				//La m�thode suivante alerte l'observateur 
				if (listener != null) {
					firePropertyChange("ENVOIE-FORME", null, (Object) "update");
				}
				return null;
			}
		};
		if (listener != null)
		   threadComm.addPropertyChangeListener(listener); // La m�thode "propertyChange" de ApplicationFormes sera donc appelée lorsque le SwinkWorker invoquera la méthode "firePropertyChanger" 		
		threadComm.execute(); // Lance le fil d'exécution parallèle.
		isActif = true;
	}
	
	/**
	 * @return si le fil d'exécution parallèle est actif
	 */
	public boolean isActif() {
		return isActif;
	}
	
	/**
	 * @param le generateur de forme.
	 */
	public void setGenerateur(GenerateurForme generateur) {
		this.generateur = generateur;
	}
	
	/**
	 * Cr�� la connexion avec le serveur de forme et initialise les objets de communication.
	 * @throws EntreeInvalideException 
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @throws NumberFormatException 
	 */
	private void listenSocket() throws NumberFormatException, UnknownHostException, IOException, EntreeInvalideException {
		boolean succes = true;
		client = new Socket(getHost(), getPort());
        out = new PrintWriter(client.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	}
	
	/**
	 * @return l'h�te extrait de la cha�ne de caract�res entr�e par l'utilisateur
	 * @throws EntreeInvalideException 
	 */
	private String getHost() throws EntreeInvalideException {
		if (this.host.length() == 0) {
			Pattern pattern = Pattern.compile(HOST_REGEX);
			this.host = findMatch(pattern);
		}
		return this.host;
	}
	
	/**
	 * @return l'h�te extrait de la cha�ne de caract�res entr�e par l'utilisateur
	 * @throws EntreeInvalideException 
	 * @throws NumberFormatException 
	 */
	private int getPort() throws NumberFormatException, EntreeInvalideException {
		if (this.port == 0) {
			Pattern pattern = Pattern.compile(PORT_REGEX);
			this.port = Integer.parseInt(findMatch(pattern));
		}
		return this.port;
	}
	
	/**
	 * @param pattern
	 * @return la sous-cha�ne de caract�res recherch�e
	 * @throws EntreeInvalideException
	 */
	private String findMatch(Pattern pattern) throws EntreeInvalideException {
		String match = "";
		Matcher matcher = pattern.matcher(this.getHostAndPort());
		boolean result = matcher.find();
		if (result) {
			match = matcher.group(0);
		}
		if (match.length() == 0) {
			throw new exceptions.EntreeInvalideException();
		}
		return match;
	}
	
	/**
	 * Si ce n'est pas deja fait, demande a l'utilisateur
	 * l'hote et le port du serveur de forme
	 * 
	 * @return l'h�te et le port sous la forme "h�te:port"
	 */
	private String getHostAndPort() {
		if (hostAndPort.length() == 0) {
			hostAndPort = JOptionPane.showInputDialog("Quel est le nom d'h�te et le port du serveur de formes?");
		}
		return hostAndPort;
	}
	
	/**
	 * Réinitialise les variables relatives à la connexion
	 */
	private void resetConnexion() {
		hostAndPort = "";
		host = "";
		port = 0;
	}

}
