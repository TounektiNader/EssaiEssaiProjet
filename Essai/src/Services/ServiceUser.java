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
import java.sql.PreparedStatement;
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
     
     PreparedStatement stmt;
     ResultSet rs;
    private ObservableList<User> data;
    Connection connexion;

    public ServiceUser() {
        connexion = MyConnection.getInstance().getConnexion();
    }
    
    
    @Override
    public void ajouterUser(User u) {
 String mdp = Utils.Password.hashPassword(u.getMdp());
        System.out.println(mdp);
         int b;
         int d;
            b = (int) (Math.random() * 9999);
        System.out.println(b);
     
        
        String code = "" + b;
            boolean test= false ; 
            String ab="a:0:{}";
            String ab1="a:1:{i:0;s:10:"+"\"ROLE_ADMIN\""+";}";
        try {
            String sql = "INSERT INTO user(nom,prenom,jeton,nationalite,num,username,username_canonical,email,email_canonical,enabled,password,confirmation_token,roles,mdp,role,mail,status) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            stmt = connexion.prepareStatement(sql);
           
            stmt.setString(1, u.getNom());
            stmt.setString(2, u.getPrenom());
            stmt.setInt(3, u.getJeton());
            stmt.setString(4, u.getNationalite());
            stmt.setString(5, u.getNum());
            stmt.setString(6, u.getUsername());
            stmt.setString(7, u.getUsername());
            stmt.setString(8, u.getMail());
            stmt.setString(9, u.getMail());
            stmt.setInt(10, 0);
            stmt.setString(11, mdp);
            stmt.setString(12, null);
           if(u.getRole().equals("Utilisateur"))
           {
            stmt.setString(13,ab);
            stmt.setString(15, "Utilisateur");
           }
           else {stmt.setString(13,ab1);
           stmt.setString(15, "Admin");}
            
            //stmt.setString(14,null);
            //stmt.setDate(15,null);
            //stmt.setString(16,null);
            stmt.setString(14,u.getMdp());
            //stmt.setString(15,"a");
            stmt.setString(16,"a");
            stmt.setString(17,"a");
            
            stmt.executeUpdate();
 
            ServiceCode c = new ServiceCode();
            c.ajouterCode(code);
            mail a = new mail();
            a.send(u.getMail(), "Validation", code);
            
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
    
    
    
    
    

//    @Override
//    public void ajouterUser(User u) {
//        String mdp = Utils.Password.hashPassword(u.getMdp());
//        System.out.println(mdp);
//          int b;
//            b = (int) (Math.random() * 9999);
//
//            String code = "" + b;
//            int enabled = 0;
//            String ab="a:0:{}";
//        try {
//            String query = "INSERT INTO user (username,username_canonical,nom,prenom,password,role,email,email_canonical,enabled,jeton,nationalite,num,confirmation_token,roles) "
//                    + "values ( '" + u.getUsername()+ "','" +u.getUsername()+"' , '" +u.getNom()+ "', '" + u.getPrenom()+ "','" + mdp+ "' , '" + u.getRole() + "','" + u.getMail()+"','" +u.getMail() +"','"+enabled+"','"+u.getJeton()+"','"+u.getNationalite()+"','"+u.getNum()+"','"+
//                    code+"','"+ab+"')";
//            Statement stm = connexion.createStatement();
//            stm.executeUpdate(query);
//          
//
//            ServiceCode c = new ServiceCode();
//            c.ajouterCode(code);
//            mail a = new mail();
//            a.send(u.getMail(), "Validation", code);
//            
//            try {
//                envoieSMS(code,u.getNum());
//            } catch (IOException ex) {
//                Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//            System.out.println("Ajout effectué");
//        } catch (SQLException ex) {
//            System.out.println("Echec d'ajout");
//        }
//    }

    @Override
    public void modifierUser(User u) {
        
        //nthabet fil role lahna
        String mdp = Utils.Password.hashPassword(u.getMdp());
        try {
            String query = "UPDATE user SET nom='" + u.getNom() + "',prenom='" + u.getPrenom() + "',password='" + mdp + "',email='" + u.getMail() +"',jeton='"+u.getJeton() +"',num='"+u.getNum()+"' where username='" +u.getUsername() + "'";

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
               
                if(resultat.getString("roles").equals("a:1:{i:0;s:10:"+"\"ROLE_ADMIN\""+";}")){ user.setRole("Admin");}
                else{ user.setRole("Utilisateur");}
               
                    
                user.setMail(resultat.getString("email"));
                if(resultat.getInt("enabled")==1){user.setStatus("true");}
                else{user.setStatus("false");}
                user.setJeton(resultat.getInt("jeton"));
                user.setNum(resultat.getString("num"));
                
            }

        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
        return user;
}

    
    
    
    public String testMdp(String username) {
     
        String mdp1="";
        try {

            String query = "Select * FROM user WHERE username='" + username+"'";
            Statement stm = connexion.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {

                 mdp1 =resultat.getString("password");
         
                 System.out.println(mdp1);
       
            }

        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
        
       
        return mdp1;
    }

    
   
    
    
    @Override
    public User afficherUser(String pseudo ,String mdp) {
        User user = new User();
      
      
       String md = testMdp(pseudo);
      String b="a:1:{i:0;s:10:"+"\"ROLE_ADMIN\""+";}";
        System.out.println(md);
        if(Utils.Password.checkPassword(mdp, md)){
           
        try {

            String query = "Select * FROM user WHERE username='" + pseudo + "'";
            Statement stm = connexion.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while (resultat.next()) {

                user.setUsername( resultat.getString("username")); 
                user.setNom(resultat.getString("nom"));
                user.setPrenom(resultat.getString("prenom"));
                user.setMdp(resultat.getString("mdp"));
                
                String a = resultat.getString("roles").toString();
                System.out.println(a);
                System.out.println(b);
                if(a.equals(b)){ user.setRole("Admin");}
                else{ user.setRole("Utilisateur");}
                user.setMail(resultat.getString("email"));
                System.out.println(user.getRole());
                if(resultat.getInt("enabled")==1){user.setStatus("true");}
                else{user.setStatus("false");}
                user.setJeton(resultat.getInt("jeton"));
                user.setNationalite(resultat.getString("nationalite"));
                user.setNum(resultat.getString("num"));
                
            }

        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
        return user;}
        else{
            System.out.println("Impossible");
            return user ; 
        }
     
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
        int en = 1;
        if (code2.equals(code)) {
            try {
                String query = "UPDATE user SET enabled='"+en+"' where username='" + u.getUsername() + "' ";

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
             String status="";
                String role="";
               String ad="a:1:{i:0;s:10:"+"\"ROLE_ADMIN\""+";}";
               int en = 0;
         String req="select * from user where roles='"+ad+"' and enabled='"+en+"' ;";
        
        try {   
            Statement stm = connexion.createStatement();
            
            
           ResultSet result= stm.executeQuery(req);
            
           while(result.next()){
               
               int jeton=result.getInt("jeton");
               
               if(result.getInt("enabled")==1){ status="true";}
                else{ status="false";}
               String mail=result.getString("email");
              
                 if(result.getString("roles").equals("a:1:{i:0;s:10:"+"\"ROLE_ADMIN\""+";}")){ role="Admin";}
                else{ role="Utilisateur";}
               
               
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
             if(resultat.getString("roles").equals("a:1:{i:0;s:10:"+"\"ROLE_ADMIN\""+";}")){ user.setRole("Admin");}
                else{ user.setRole("Utilisateur");}
                
                user.setMail(resultat.getString("email"));
               if(resultat.getInt("enabled")==1){user.setStatus("true");}
                else{user.setStatus("false");}
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
           if(resultat.getString("roles").equals("a:1:{i:0;s:10:"+"\"ROLE_ADMIN\""+";}")){ user.setRole("Admin");}
                else{ user.setRole("Utilisateur");}
                user.setMail(resultat.getString("email"));
               
               if(resultat.getInt("enabled")==1){user.setStatus("true");}
                else{user.setStatus("false");}
                
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
       String ab = "a:1:{i:0;s:10:"+"\"ROLE_ADMIN\""+";}";
       int en = 0;
     try {
        
         String query = "SELECT COUNT(*) nb FROM user where roles='"+ab+"' and enabled='"+en+"'";
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
        int ab = 1;
        
        if (u.getRole().equals("Admin")) {
            try {
                String query = "UPDATE user SET enabled='"+ab+"' where username='" + u.getUsername() + "' ";

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
