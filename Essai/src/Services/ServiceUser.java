/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DateStroge.MyConnection;
import Entity.User;
import Utils.mail;
import iService.iServicesUser;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Nader
 */
public class ServiceUser implements iServicesUser  {
    
    
    private final static String username = "russiarussia";
    private final static String password = "NADERnader27";
     
    
    private ObservableList<User> data;
    Connection connexion;

    public ServiceUser() {
        connexion = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void ajouterUser(User u) {
        try {
            String query = "INSERT INTO user (username,nom,prenom,mdp,role,mail,status,jeton,nationalite,num) "
                    + "values ( '" + u.getUsername()+ "','" + u.getNom()+ "', '" + u.getPrenom()+ "','" + u.getMdp()+ "' , '" + u.getRole() + "','" + u.getMail() + "','false','"+u.getJeton()+"','"+u.getNationalite()+"','"+u.getNum()+"')";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(query);
            int b;
            b = (int) (Math.random() * 9999);

            String code = "" + b;

            ServiceCode c = new ServiceCode();
            c.ajouterCode(code);
            mail a = new mail();
            if(u.getRole().equals("Utilisateur"))
            {
                 a.send(u.getMail(), "Validation", code);
            }
           
            
            try {
                envoieSMS(code,u.getNum());
            } catch (IOException ex) {
                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }

    @Override
    public void modifierUser(User u) {
        try {
            String query = "UPDATE user SET nom='" + u.getNom() + "',prenom='" + u.getPrenom() + "',mdp='" + u.getMdp() + "',role='" + u.getRole() + "',mail='" + u.getMail() +"',jeton='"+u.getJeton() +"',num='"+u.getNum()+"' where username='" +u.getUsername() + "'";

            Statement stm = connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }

    @Override
    public User rechercheUser(String pseudo) {
         
     User user = new User();
        try {

            String query = "Select * FROM user WHERE username='" + pseudo + "'";
            Statement stm = connexion.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {

                user.setUsername( resultat.getString("username")); 
                user.setNom(resultat.getString("nom"));
                user.setPrenom(resultat.getString("prenom"));
                user.setMdp(resultat.getString("mdp"));
                user.setRole(resultat.getString("role"));
                user.setMail(resultat.getString("email"));
                user.setStatus(resultat.getString("status"));
                user.setJeton(resultat.getInt("jeton"));
                user.setNum(resultat.getString("num"));
                
            }

        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
        return user;
}

    @Override
    public User afficherUser(String pseudo ,String mdp) {
        User user = new User();
        try {

            String query = "Select * FROM user WHERE username='" + pseudo + "'and password='"+mdp+"'";
            Statement stm = connexion.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {

                user.setUsername( resultat.getString("username")); 
                user.setNom(resultat.getString("nom"));
                user.setPrenom(resultat.getString("prenom"));
                user.setMdp(resultat.getString("password"));
                user.setRole(resultat.getString("roles"));
                user.setMail(resultat.getString("email"));
                user.setStatus(resultat.getString("status"));
                user.setJeton(resultat.getInt("jeton"));
                user.setNationalite(resultat.getString("nationalite"));
                user.setNum(resultat.getString("num"));
                
            }

        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
        return user;
    }

    @Override
    public void SupprimerUser(String pseudo) {
        try {
            String query = "DELETE FROM user WHERE username='" + pseudo + "'";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    } 

    @Override
    public void validerCompte(String code, User u) {
        ServiceCode c = new ServiceCode();
        String code2 = c.rechercheCode(code);
        System.out.println(code2);
        if (code2.equals(code)) {
            try {
                String query = "UPDATE user SET status='true' where username='" + u.getUsername() + "' ";

                Statement stm = connexion.createStatement();
                c.supprimerCode(code);
                stm.executeUpdate(query);
                System.out.println("Ajout effectué");
            } catch (SQLException ex) {
                System.out.println("Echec d'ajout");
            }
        }

    }

    
    public ObservableList<User> GetAdmin() {
       data = FXCollections.observableArrayList();
             
         String req="select * from user where role='Admin' and status='false' ;";
        
        try {   
            Statement stm = connexion.createStatement();
            
            
           ResultSet result= stm.executeQuery(req);
            
           while(result.next()){
               
               int jeton=result.getInt("jeton");
               String status=result.getString("status");
               String mail=result.getString("mail");
               String role=result.getString("role");
               String mdp=result.getString("mdp");
                String pseudo=result.getString("username");
                 String prenom=result.getString("prenom");
                  String nom=result.getString("nom");
                   String nationalite=result.getString("nationalite");
                   String num=result.getString("num");
                
              
               
               
               
               data.add(new User(nom,prenom,pseudo,mdp,role,mail,status,jeton,nationalite,num));
           }
            
        } catch (SQLException e) {
            System.err.println("probleme"+e.getMessage());
        }
       return data;
    }
    
    @Override
      public User getUser(String pseudo) {
        User user = new User();
        try {

            String query = "Select * FROM user WHERE username='" + pseudo + "'";
            Statement stm = connexion.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {

                user.setUsername( resultat.getString("username")); 
                user.setNom(resultat.getString("nom"));
                user.setPrenom(resultat.getString("prenom"));
                user.setMdp(resultat.getString("mdp"));
                user.setRole(resultat.getString("role"));
                user.setMail(resultat.getString("mail"));
                user.setStatus(resultat.getString("status"));
                user.setJeton(resultat.getInt("jeton"));
                user.setNationalite(resultat.getString("nationalite"));
                user.setNum(resultat.getString("num"));
                
            }

        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
        return user;
    }
    
         @Override
    public User afficherUser(String pseudo) {
        User user = new User();
        try {

            String query = "Select * FROM user WHERE username='" + pseudo + "'";
            Statement stm = connexion.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {

               user.setUsername( resultat.getString("username")); 
                user.setNom(resultat.getString("nom"));
               user.setPrenom(resultat.getString("prenom"));
                   user.setMdp(resultat.getString("mdp"));
                user.setRole(resultat.getString("role"));
                user.setMail(resultat.getString("mail"));
                user.setStatus(resultat.getString("status"));
                user.setJeton(resultat.getInt("jeton"));
               
            }

        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
        return user;
    }
    
    @Override
    public int nbrAdmin() {
       int nbr=0;
     try {
         String query = "SELECT COUNT(*) FROM user where role='Admin' and status='false'";
         Statement stm = connexion.createStatement();
         ResultSet resultat = stm.executeQuery(query);
         while (resultat.next())
			{
			nbr=resultat.getInt(1);
			}
     } catch (SQLException ex) {
      
     }
     return nbr;
    }
    
    @Override
    public void validerAdmin(User u) {
        if (u.getRole().equals("Admin")) {
            try {
                String query = "UPDATE user SET status='true' where username='" + u.getUsername() + "' ";

                Statement stm = connexion.createStatement();
                
                stm.executeUpdate(query);
                System.out.println("Ajout effectué");
            } catch (SQLException ex) {
                System.out.println("Echec d'ajout");
            }
        } 
    }
    
    
    public void envoieSMS (String code,String num ) throws IOException{
        
        int result = send(username, password, num, code);  
        System.out.println("Result code: " + result);
    
        
        
    
    }
    
     public static int send(String user, String pass, String to,  String body) throws IOException {
        URL url = new URL("https://www.bulletinmessenger.net/api/3/sms/out");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        try {
            
            con.setRequestMethod("POST");
            con.setRequestProperty("Authorization", encodeBasicAuth(user, pass));
            con.setDoOutput(true);
 
            StringBuffer form = new StringBuffer();
            form.append("to=");
            form.append(to);
           // form.append("&messageId=").append(messageId);
            form.append("&body=");
 
            form.append (URLEncoder.encode(body, "UTF-8"));
 
            OutputStream out = con.getOutputStream();
            try {
                out.write(form.toString().getBytes("US-ASCII"));
            } finally {
                out.close();
            }
            return con.getResponseCode();
        } finally {
            con.disconnect();
        }
    }
 
    public static String encodeBasicAuth(String user, String pass) throws UnsupportedEncodingException {
        byte[] credentials = (user + ':' + pass).getBytes("US-ASCII");
        return "Basic " + new BASE64Encoder().encode(credentials);
    }
    
}
