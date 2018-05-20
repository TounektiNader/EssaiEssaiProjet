/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DateStroge.MyConnection;
import Entity.Bet;
import Entity.Equipe;
import Entity.Partie;
import Entity.User;
import iService.iBet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ServiceBet implements iBet {

    PreparedStatement stmt, stmt1;
    Connection conn;
    ResultSet rs, rss;
    private ObservableList<Bet> data;
    private ObservableList<User> dataUser;

    public ServiceBet() {
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
                 user.setNationalite(rs.getString("nationalite"));

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return user;
    }

    @Override
    public void modifierBet(String etat, String username, Partie partie) {

        try {

            String sql = "update bet set etat=? where username=? and idPartie=? ;";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, etat);
            stmt.setString(2, username);
            stmt.setInt(3, partie.getIdMatch());

            int s = stmt.executeUpdate();
            if (s < 0) {
                System.out.println("Echec");
            } else {
                System.out.println(" Modification avec succès");
            }
        } catch (SQLException ex) {

        }

    }

    @Override
    public List<Bet> chercherBet(int idPartie) {

        PartieService partieService = new PartieService();
        Partie partie = new Partie();
        partie = partieService.DetailsPartie(idPartie);

        List<Bet> listBet = new ArrayList<Bet>();

        try {

            String sql = "select * from  bet where idPartie=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);

            rs = stmt.executeQuery();
            while (rs.next()) {

                Bet bet = new Bet(rs.getInt("valeur"), rs.getString("etat"), getUser(rs.getString("username")), partie);

                listBet.add(bet);
            }
        } catch (SQLException ex) {

        }
        return listBet;

    }

    @Override
    public ObservableList<Bet> listBet(String username) {
        PartieService partieService = new PartieService();
        User user = new User();
        user = getUser(username);

        data = FXCollections.observableArrayList();

        try {
            String sql = "select * from  bet where username=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();
            while (rs.next()) {

                Bet bet = new Bet(rs.getInt("valeur"), rs.getString("etat"), user, partieService.DetailsPartie(rs.getInt("idPartie")));
                data.add(bet);
            }

        } catch (SQLException ex) {
        }
        return data;

    }

    @Override
    public List<Bet> listBet() {
        PartieService partieService = new PartieService();

        List<Bet> list = new ArrayList<Bet>();

        try {

            String sql = "select * from  bet ";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {

                Bet bet = new Bet(rs.getInt("valeur"), rs.getString("etat"), getUser(rs.getString("username")), partieService.DetailsPartie(rs.getInt("idPartie")));
                list.add(bet);

            }

        } catch (SQLException ex) {

        }
        return list;

    }

//   
    @Override
    public int getNombreJeton(String userName) {
        int nbJeton = 0;
        try {

            String sql = "Select jeton from user where username=? ";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userName);

            rs = stmt.executeQuery();

            while (rs.next()) {
                nbJeton = rs.getInt("jeton");

            }

        } catch (SQLException ex) {

        }
        return nbJeton;
    }

    @Override
    public void deminuerJeton(String username) {

        int nbJetonn = (getNombreJeton(username) - 1);

        try {

            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("UPDATE  user  SET jeton='" + nbJetonn + "' WHERE username='" + username + "' ");
            if (rs < 0) {
                System.out.println("Echec");
            } else {
                System.out.println("Modification avec succès");
            }

        } catch (SQLException ex) {

        }

    }

    @Override
    public void AugmenterJeton(String username) {

        int nbJetonn = (getNombreJeton(username) + 2);

        try {
             String sql = "Update user SET jeton=? where username=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, nbJetonn);
            stmt.setString(2, username);

            
             int s = stmt.executeUpdate();
            if (s < 0) {
                System.out.println("Echec");
            } else {
                System.out.println(" Modification avec succès");
           
            }
       

//            while (rs.next()) {
//                nbJeton = rs.getInt("jeton");
//                    
//            Statement statement = conn.createStatement();
//            int rs = statement.executeUpdate("UPDATE  user  SET jeton='" + nbJetonn + "' WHERE username='" + username + "' ");
//            if (rs < 0) {
//                System.out.println("Echec");
//            } else {
//                System.out.println("Modification avec succès");
//            }

        } catch (SQLException ex) {

        }

    }

    @Override
    public int getIdPartiee(int idEquipe1, int idEquipe2) {
        int idPartie = 0;
        try {

            String sql = "Select id from Partie where home=? and away=? ";

            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEquipe1);
            stmt.setInt(2, idEquipe2);

            rs = stmt.executeQuery();

            while (rs.next()) {
                idPartie = rs.getInt("id");

            }
        } catch (SQLException ex) {

        }
        return idPartie;
    }

    @Override
    public int getIdEquipe(String nomEquipe) {
        int idEquipe1 = 0;

        try {

            String sql = "Select idEquipe from equipe where nomEquipe=? ";

            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomEquipe);

            rs = stmt.executeQuery();

            while (rs.next()) {
                idEquipe1 = rs.getInt("idEquipe");

            }
        } catch (SQLException ex) {

        }

        return idEquipe1;

    }

    @Override
    public Boolean jouerUnMatch(String username, int idPartie) {
        Boolean test = false;
        String sql = "Select * from bet where username=? and idPartie=?";

        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, idPartie);
            rs = stmt.executeQuery();
            if (rs.next()) {
                test = true;
            } else {
                test = false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Bet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return test;
    }

    @Override

    public void updateEtatBetGain(int idPartie) {

        try {

            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("UPDATE  bet  SET etat='Gain' WHERE idPartie='" + idPartie + "' ");
            if (rs < 0) {
                System.out.println("Echec");
            } else {
                System.out.println("Modification avec succès");
            }

        } catch (SQLException ex) {

        }

    }

    @Override
    public void updateEtatBetPerte(int idPartie) {

        try {

            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("UPDATE  bet  SET etat='Perte' WHERE idPartie='" + idPartie + "' ");
            if (rs < 0) {
                System.out.println("Echec");
            } else {
                System.out.println("Modification avec succès");
            }

        } catch (SQLException ex) {

        }

    }

    @Override
    public Boolean comparaisonValeurResultat(String usernaeme, int idPartie) {
        Boolean test = false;
        ResultatService resultatService = new ResultatService();
        Equipe equipeGagne = resultatService.EquipeGagne(idPartie);

        String sql = "SELECT * FROM bet WHERE idPartie=? and username=? ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);
            stmt.setString(2, usernaeme);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int idEquipeValeur = rs.getInt("valeur");
                if (idEquipeValeur == equipeGagne.getIDEquipe()) {
                    test = true;
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return test;
    }

    @Override

    public void updateJetonEtat(int idPartie) {
        Boolean test = false;
        ResultatService resultatService = new ResultatService();
        Equipe equipeGagne = resultatService.EquipeGagne(idPartie);

        String sql = "SELECT * FROM bet WHERE idPartie=?  ";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);

            rs = stmt.executeQuery();

            while (rs.next()) {

                int idEquipeValeur = rs.getInt("valeur");
                if (idEquipeValeur == equipeGagne.getIDEquipe()) {
                    AugmenterJeton(rs.getString("username"));
                    updateEtatBetGain(idPartie);
                } else {
                    updateEtatBetPerte(idPartie);
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void parier(int idPartie, String username, String choix) {
        PartieService partieService = new PartieService();
        int idEquipeChoix = getIdEquipe(choix);

        Partie partie = partieService.DetailsPartie(idPartie);
        User user = getUser(username);

        int jetonUsername = getNombreJeton(username);

        if (jetonUsername >= 1) {
            try {

                String sql = "INSERT INTO bet(username,valeur,etat,idPartie)VALUES(?,?,?,?);";

                stmt = conn.prepareStatement(sql);
                stmt.setString(1, username);
                stmt.setInt(2, idEquipeChoix);
                stmt.setString(3, "Traite");
                stmt.setInt(4, idPartie);

                int rss = stmt.executeUpdate();

                if (rss < 0) {
                    System.out.println("Echec");
                } else {
                    System.out.println("Insert de bet avec succès");
                    deminuerJeton(username);
                }

            } catch (SQLException ex) {

            }

        } else {
            System.out.println("Vous n'avez plus de jeton ");
        }
    }

//    
//    @Override
//    public void parierr() {
//
//        int idEquipeHome = getIdEquipe("Brazil");
//        int idEquipeAway = getIdEquipe("Argentine");
//        int idPartie = getIdPartiee(idEquipeHome, idEquipeAway);
//
//        int jetonUsername = getNombreJeton("nader");
//
//        if (jetonUsername >= 1) {
//            try {
//
//                String sql = "INSERT INTO bet(username,valeur,etat,idPartie) VALUES(?,?,?,?);";
//
//                stmt = conn.prepareStatement(sql);
//                stmt.setString(1, "nader");
//                stmt.setInt(2, 13);
//                stmt.setString(3, "Encours");
//                stmt.setInt(4, idPartie);
//
//                int rss = stmt.executeUpdate();
//
//                if (rss < 0) {
//                    System.out.println("Echec");
//                } else {
//                    System.out.println("Insert de bet avec succès");
//                    deminuerJeton("nader");
//                }
//
//            } catch (SQLException ex) {
//                Logger.getLogger(Bet.class.getName()).log(Level.SEVERE, null, ex);
//            }
//
//        } else {
//            System.out.println("Vous n'avez plus de jeton ");
//        }
//    }
    @Override
    public ObservableList<Bet> listBetGain(String username) {
        PartieService partieService = new PartieService();
        User user = new User();
        user = getUser(username);

        data = FXCollections.observableArrayList();

        try {
            String sql = "select * from  bet where username=? and etat=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, "Gain");

            rs = stmt.executeQuery();
            while (rs.next()) {

                Bet bet = new Bet(rs.getInt("valeur"), rs.getString("etat"), user, partieService.DetailsPartie(rs.getInt("idPartie")));
                data.add(bet);
            }

        } catch (SQLException ex) {
        }
        return data;

    }

    @Override
    public ObservableList<Bet> listBetPerte(String username) {
        PartieService partieService = new PartieService();
        User user = new User();
        user = getUser(username);

        data = FXCollections.observableArrayList();

        try {
            String sql = "select * from  bet where username=? and etat=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, "Perte");

            rs = stmt.executeQuery();
            while (rs.next()) {

                Bet bet = new Bet(rs.getInt("valeur"), rs.getString("etat"), user, partieService.DetailsPartie(rs.getInt("idPartie")));
                data.add(bet);
            }

        } catch (SQLException ex) {
        }
        return data;

    }

    @Override
    public int NombreTotalBet() {
        int nombreBet = 0;
            String rq1 = " SELECT Count(idBet) FROM bet;";
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
    public int NombreBetGain() {
        int nombreBet = 0;
            String rq1 = " SELECT Count(idBet) FROM bet where etat='Gain';";
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
    public int NombreBetPerte() {
        int nombreBet = 0;
            String rq1 = " SELECT Count(idBet) FROM bet where etat='Perte';";
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
    public int NombreBetTraite() {
        int nombreBet = 0;
            String rq1 = " SELECT Count(idBet) FROM bet where etat='Traite';";
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
     public ObservableList<User> GetUser() {
       dataUser = FXCollections.observableArrayList();
             
         String req="select * from user where role='Utilisateur'";
        
        try {   
            Statement stm = conn.createStatement();
            
            
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
                
              
               
               
               
               dataUser.add(new User(pseudo,nom,prenom,mdp,role,mail,status,jeton,nationalite));
           }
            
        } catch (SQLException e) {
            System.err.println("probleme"+e.getMessage());
        }
       return dataUser;
    }
    
     
         @Override
    public List<Bet> listBetParPersonne() {
        PartieService partieService = new PartieService();

        List<Bet> list = new ArrayList<Bet>();

        try {

            String sql = " SELECT username,COUNT(idBet) from bet GROUP BY username ";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {

                Bet bet = new Bet(getUser(rs.getString("username")),rs.getInt(2));
                list.add(bet);

            }

        } catch (SQLException ex) {

        }
        return list;

    }

      @Override
    public int nombreBetPerte(String username) {
       int   nombreBetPerte=0;
       

        data = FXCollections.observableArrayList();

        try {
            String sql = "select count(idBet) from  bet where username=? and etat=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, "Perte");

            rs = stmt.executeQuery();
            while (rs.next()) {
            nombreBetPerte = rs.getInt(1);
                
            }

        } catch (SQLException ex) {
        }
        return nombreBetPerte;

    }
    
    @Override
    public int nombreBetGain(String username) {
       int   nombreBetPerte=0;
       

        data = FXCollections.observableArrayList();

        try {
            String sql = "select count(idBet) from  bet where username=? and etat=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, "Gain");

            rs = stmt.executeQuery();
            while (rs.next()) {
            nombreBetPerte = rs.getInt(1);
                
            }

        } catch (SQLException ex) {
        }
        return nombreBetPerte;

    }
}
