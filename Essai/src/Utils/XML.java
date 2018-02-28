/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


import Entity.User;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author hedih
 */
public class XML {
    public User lire() throws org.xml.sax.SAXException, IOException {
        /*
         * Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
         */
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            	
        try {
            /*
             * Etape 2 : création d'un parseur
             */
            final DocumentBuilder builder = factory.newDocumentBuilder();
			
	    /*
	     * Etape 3 : création d'un Document
	     */
	    final Document document= builder.parse(new File("C:/Users/Nader/Documents/GitHub/EssaiEssaiProjet/Essai/src/Presentation/newXMLDocument.xml"));
			
	    //Affichage du prologue
	    System.out.println("*************PROLOGUE************");
	    System.out.println("version : " + document.getXmlVersion());
	    System.out.println("encodage : " + document.getXmlEncoding());		
            System.out.println("standalone : " + document.getXmlStandalone());
					
	    /*
	     * Etape 4 : récupération de l'Element racine
	     */
	    final Element racine = document.getDocumentElement();
		
	    //Affichage de l'élément racine
	    System.out.println("\n*************RACINE************");
	    System.out.println(racine.getNodeName());
		
	    /*
	     * Etape 5 : récupération des personnes
	     */
	    final NodeList racineNoeuds = racine.getElementsByTagName("personne");
	    final int nbRacineNoeuds = racineNoeuds.getLength();
			
	    for (int i = 0; i<nbRacineNoeuds; i++) {
	        if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
	            final Element personne = (Element) racineNoeuds.item(i);
				
		    //Affichage d'une personne
		    
			
	    	    /*
		     * Etape 6 : récupération du nom et du prénom
		     */
		    final Element nom = (Element) personne.getElementsByTagName("nom").item(0);
		    final Element prenom = (Element) personne.getElementsByTagName("prenom").item(0);
                    final Element pseudo = (Element) personne.getElementsByTagName("pseudo").item(0);
                    final Element mdp = (Element) personne.getElementsByTagName("mdp").item(0);
                    final Element role = (Element) personne.getElementsByTagName("role").item(0);
                    final Element status = (Element) personne.getElementsByTagName("status").item(0);
                    final Element jeton = (Element) personne.getElementsByTagName("jeton").item(0);
                    final Element mail = (Element) personne.getElementsByTagName("mail").item(0);
                    final Element nationalite = (Element) personne.getElementsByTagName("nationalite").item(0);
                    final Element num = (Element) personne.getElementsByTagName("num").item(0);
					
		    //Affichage du nom et du prénom
		    System.out.println("nom : " + nom.getTextContent());
		    System.out.println("prénom : " + prenom.getTextContent());
                    String j=jeton.getTextContent(); 
                    int h=Integer.parseInt(j);
			User x=new User(pseudo.getTextContent(), nom.getTextContent(), prenom.getTextContent(),mdp.getTextContent(),role.getTextContent(),mail.getTextContent(),status.getTextContent(), h, nationalite.getTextContent(),num.getTextContent());
		    /*
		     * Etape 7 : récupération des numéros de téléphone
		     */
		    return x;
	        }				
	    }			
        }
        catch (final ParserConfigurationException e) {
            e.printStackTrace();
        }
        catch (final SAXException e) {
            e.printStackTrace();
        }
        catch (final IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   public void Ecrire(String username, String nom2, String prenom2, String mdp2, String role2, String mail2, String status2, int jeton2,String nationalite2,String num2) {
        /*
	 * Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
	 */
	final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
	try {
	    /*
	     * Etape 2 : création d'un parseur
	     */
	    final DocumentBuilder builder = factory.newDocumentBuilder();
	    		
	    /*
	     * Etape 3 : création d'un Document
	     */
	    final Document document= builder.newDocument();
					
	    /*
	     * Etape 4 : création de l'Element racine
	     */
	    final Element racine = document.createElement("repertoire");
	    document.appendChild(racine);			
			
	    /*
	     * Etape 5 : création d'une personne
	     */
	    
		final Element personne = document.createElement("personne");
	    
	    racine.appendChild(personne);	
	   
			
	    /*
	     * Etape 6 : création du nom et du prénom
	     */
	    final Element nom = document.createElement("nom");
	    nom.appendChild(document.createTextNode(nom2));
			
	    final Element prenom = document.createElement("prenom");
	    prenom.appendChild(document.createTextNode(prenom2));
            
            final Element pseudo = document.createElement("pseudo");
	    pseudo.appendChild(document.createTextNode(username));
			
	    final Element mdp = document.createElement("mdp");
	    mdp.appendChild(document.createTextNode(mdp2));
            
            final Element role = document.createElement("role");
	    role.appendChild(document.createTextNode(role2));
			
	    final Element mail = document.createElement("mail");
	    mail.appendChild(document.createTextNode(mail2));
            
            final Element nationalite = document.createElement("nationalite");
	    nationalite.appendChild(document.createTextNode(nationalite2));
            
            final Element jeton = document.createElement("jeton");
            String j=""+jeton2;
	    jeton.appendChild(document.createTextNode(j));
            final Element status = document.createElement("status");
	    status.appendChild(document.createTextNode(status2));
            final Element num = document.createElement("num");
	    num.appendChild(document.createTextNode(num2));
			
	    personne.appendChild(nom);
	    personne.appendChild(prenom);			
            personne.appendChild(mail);
            personne.appendChild(pseudo);
            personne.appendChild(mdp);
            personne.appendChild(role);
            personne.appendChild(nationalite);
            personne.appendChild(jeton);
            personne.appendChild(status);
            personne.appendChild(num);
	    /*
	     * Etape 7 : création des numéros de téléphone
	     */
	    
			
	    /*
	     * Etape 8 : affichage
	     */
	    final TransformerFactory transformerFactory = TransformerFactory.newInstance();
	    final Transformer transformer = transformerFactory.newTransformer();
	    final DOMSource source = new DOMSource(document);
	    final StreamResult sortie = new StreamResult(new File("C:/Users/Nader/Documents/GitHub/EssaiEssaiProjet/Essai/src/Presentation/newXMLDocument.xml"));
	    //final StreamResult result = new StreamResult(System.out);
			
	    //prologue
	    transformer.setOutputProperty(OutputKeys.VERSION, "1.0");
	    transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	    transformer.setOutputProperty(OutputKeys.STANDALONE, "yes");			
	    		
	    //formatage
	    transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	    transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			
	    //sortie
	    transformer.transform(source, sortie);	
	}
	catch (final ParserConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerConfigurationException e) {
	    e.printStackTrace();
	}
	catch (TransformerException e) {
	    e.printStackTrace();
	}			
    }
   
   
   

}
