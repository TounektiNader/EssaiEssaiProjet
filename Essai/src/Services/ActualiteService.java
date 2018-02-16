/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DateStroge.MyConnection;
import Entity.Actualite;
import Entity.User;
import iService.iActualite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gaelfameni
 */
public class ActualiteService implements iActualite {

    private Connection connection;
    private PreparedStatement ps;

    public ActualiteService() {
        connection = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void ajouterActualite(Actualite a) {

        try {
            String insert = "INSERT INTO actualite (titre, texte, image,username) VALUES (?,?,?,?)";
            PreparedStatement statement;
            statement = connection.prepareStatement(insert);

            statement.setString(1, a.getTitre());
            statement.setString(2, a.getTexte());
            statement.setString(3, a.getImage());
            statement.setString(4, a.getUser().getUsername());
            statement.executeUpdate();
//        Statement statement = cnx.createStatement();
//        statement.executeQuery(insert);
            System.out.println("Added !!");
        } catch (SQLException ex) {
            Logger.getLogger(ActualiteService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerActualite(Actualite a) {

        try {
            String delete = "DELETE FROM actualite WHERE titre = ?";
            PreparedStatement statement3;
            statement3 = connection.prepareStatement(delete);
            statement3.setString(1, a.getTitre());
            statement3.executeUpdate();
            System.out.println("Deleted !!");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    @Override
    public void modifierActualite(Actualite a) {
        try {
            String update = "UPDATE actualite SET titre = ?, texte= ?, image=? WHERE username = ?";
            PreparedStatement statement2;
            statement2 = connection.prepareStatement(update);
            statement2.setString(4, a.getUser().getUsername());
            statement2.setString(1, a.getTitre());
            statement2.setString(2, a.getTexte());
            statement2.setString(3, a.getImage());
            statement2.executeUpdate();
            System.out.println("Updated !!");
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }

    }

    @Override
    public Actualite recherheActualite(String titre) {

        Actualite a = new Actualite();
        
        User user = new User();
        ServiceUser service = new ServiceUser();
        try {
            String select = "SELECT * FROM actualite WHERE titre = '" + titre + "'";
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
                
                user = service.getUser(result.getString("username"));
                a.setIdactualite(result.getInt("idactualite"));
                a.setTitre(result.getString("titre"));
                a.setTexte(result.getString("texte"));
                a.setImage(result.getString("Image"));
                a.setUser(user);
                
            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return a;

    }

    @Override
    public List<Actualite> lireActualite() {
        List<Actualite> la = new ArrayList<>();
          User user = new User();
        ServiceUser service = new ServiceUser();
           Actualite a = new Actualite();
        try {
            String select = "SELECT * FROM actualite";
            Statement statement1 = connection.createStatement();
            ResultSet result = statement1.executeQuery(select);

            while (result.next()) {
              user = service.getUser(result.getString("username"));

                a.setIdactualite(result.getInt("idactualite"));
                a.setTitre(result.getString("titre"));
                a.setTexte(result.getString("texte"));
                a.setImage(result.getString("image"));
                a.setUser(user);

                la.add(a);

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return la;
    }

}
