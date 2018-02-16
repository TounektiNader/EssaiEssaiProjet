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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nader
 */
public class ServiceUser implements iServicesUser  {
     private ObservableList<User> data;
    Connection connexion;

    public ServiceUser() {
        connexion = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void ajouterUser(User u) {
        try {
            String query = "INSERT INTO user (username,nom,prenom,mdp,role,mail,status,jeton,nationalite) "
                    + "values ( '" + u.getUsername()+ "','" + u.getNom()+ "', '" + u.getPrenom()+ "','" + u.getMdp()+ "' , '" + u.getRole() + "','" + u.getMail() + "','false','"+u.getJeton()+"','"+u.getNationalite()+"')";
            Statement stm = connexion.createStatement();
            stm.executeUpdate(query);
            int b;
            b = (int) (Math.random() * 9999);

            String code = "" + b;

            ServiceCode c = new ServiceCode();
            c.ajouterCode(code);
            mail a = new mail();
            a.send(u.getMail(), "Validation", code);

            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }

    @Override
    public void modifierUser(User u) {
        try {
            String query = "UPDATE user SET nom='" + u.getNom() + "',prenom='" + u.getPrenom() + "',mdp='" + u.getMdp() + "',role='" + u.getRole() + "',mail='" + u.getMail() +"',jeton='"+u.getJeton() +"' where username='" +u.getUsername() + "'";

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
    public User afficherUser(String pseudo ,String mdp) {
        User user = new User();
        try {

            String query = "Select * FROM user WHERE username='" + pseudo + "'and mdp='"+mdp+"'";
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
             
         String req="select * from user where role='Admin'";
        
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
                
              
               
               
               
               data.add(new User(nom,prenom,pseudo,mdp,role,mail,status,jeton,nationalite));
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
                
            }

        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
        return user;
    }
    
}
