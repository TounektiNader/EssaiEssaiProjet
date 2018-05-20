/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DateStroge.MyConnection;
import Entity.Bet;
import Entity.Cadeau;
import Entity.Recompense;
import Entity.User;
import iService.IRecompense;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class RecompenseService implements IRecompense{
    PreparedStatement stmt;
    Connection conn;
    ResultSet rs;

    public RecompenseService() {
        conn = MyConnection.getInstance().getConnexion();

    }
    
    @Override
    public User getUser(String username) {
        User user = new User();

        try {

            String sql = "Select * from User where username=? ";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                user.setUsername(rs.getString("username"));
                user.setNom(rs.getString("nom"));
                user.setPrenom(rs.getString("prenom"));
                user.setRole(rs.getString("mdp"));
                user.setMdp(rs.getString("role"));
                user.setMail(rs.getString("mail"));
                user.setJeton(rs.getInt("jeton"));
                user.setStatus(rs.getString("status"));
              

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return user;
    }

    
      @Override
    public List<Recompense> listRecompense() {

        List<Recompense> list = new ArrayList<Recompense>();
        CadeauService cadeauService = new CadeauService();
        ServiceBet serviceBet= new ServiceBet();
        User user = new User();
        Cadeau cadeau = new Cadeau();
        try {
            String sql = "select * from  recompense ;";
            stmt = conn.prepareStatement(sql);
         
            rs = stmt.executeQuery();
            while (rs.next()) {
                cadeau=cadeauService.cadeau(rs.getInt("idCadeau"));
                user=getUser(rs.getString("username"));
                Recompense recompense = new Recompense(rs.getInt("idRecompense"),cadeau ,user);
                list.add(recompense);
            }

        } catch (SQLException ex) {
        }
        return list;

    }

        @Override 
    public List<Recompense> listRecompense(String username) {

        List<Recompense> list = new ArrayList<Recompense>();
        CadeauService cadeauService = new CadeauService();
        ServiceBet serviceBet= new ServiceBet();
        User user = new User();
        Cadeau cadeau = new Cadeau();
        
        try {
            String sql = "select * from  recompense where username=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();
            while (rs.next()) {
                cadeau=cadeauService.cadeau(rs.getInt("idCadeau"));
                user=getUser(rs.getString("username"));
                Recompense recompense = new Recompense(rs.getInt("idRecompense"),cadeau ,user);
                list.add(recompense);
            }

        } catch (SQLException ex) {
        }
        return list;

    }
@Override
   public void ajouterRecompense(String username,String typeRecomense){
       
       CadeauService cadeauService = new CadeauService() ; 
       Cadeau cadeau = new Cadeau();
       cadeau = cadeauService.cadeau(typeRecomense);
       
        try {

                String sql = "INSERT INTO recompense(username,idCadeau)VALUES(?,?);";

                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setInt(2,cadeau.getIdCadeau() );
               

                int rss = stmt.executeUpdate();

                if (rss < 0) {
                    System.out.println("Echec");
                } else {
                    System.out.println("Insert de bet avec succès");
                   
                }

            } catch (SQLException ex) {

            }
   
   }
   
   @Override
    public int NombreMesCadeau(String username) {
        int nombreBet = 0;
            String rq1 = " SELECT Count(idRecompense) FROM recompense where username='"+username+"';";
        try {
            
            

            Statement stmt1 = conn.createStatement();
            rs = stmt1.executeQuery(rq1);
            
            while (rs.next()) {
                System.out.println(rs.getInt(1));
                nombreBet = rs.getInt(1);
            }


        } catch (SQLException ex) {
            Logger.getLogger(ServiceBet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombreBet;
    }

    @Override
     public void deminuerJeton(String username,int nbJeton,int nbJetonCadeau) {
      
      int nb = nbJeton-nbJetonCadeau;  

        try {

            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("UPDATE  user  SET jeton='" + nb + "' WHERE username='" + username + "' ");
            if (rs < 0) {
                System.out.println("Echec");
            } else {
                System.out.println("Modification avec succès");
            }

        } catch (SQLException ex) {

        }

    }
     
     
       public List<Recompense> listRecompenseTrie() {

        List<Recompense> list = new ArrayList<Recompense>();
        CadeauService cadeauService = new CadeauService();
      
        
        try {
            String sql = "SELECT idRecompense,idCadeau,username,count(idRecompense) as cou FROM recompense GROUP BY username;";
            stmt = conn.prepareStatement(sql);
         
            rs = stmt.executeQuery();
            while (rs.next()) {
                 User user = new User();
       
                 Cadeau cadeau = new Cadeau();
                 cadeau=cadeauService.cadeau(rs.getInt("idCadeau"));
                  user=getUser(rs.getString("username"));
               
                Recompense recompense = new Recompense(rs.getInt("idRecompense"),cadeau ,user,rs.getInt("cou"));
                recompense.toString();
                list.add(recompense);
            }

        } catch (SQLException ex) {
        }
        return list;

    }

    
     
     
 

}
