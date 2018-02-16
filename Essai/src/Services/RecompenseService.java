/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DateStroge.MyConnection;
import Entity.Cadeau;
import Entity.Recompense;
import Entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class RecompenseService {
    PreparedStatement stmt;
    Connection conn;
    ResultSet rs;

    public RecompenseService() {
        conn = MyConnection.getInstance().getConnexion();

    }
    
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


}
