package Services;

import DateStroge.MyConnection;
import Entity.Bet;
import Entity.Cadeau;
import Entity.Resultat;
import iService.iCadeau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import org.controlsfx.control.Notifications;

public class CadeauService implements iCadeau {

    PreparedStatement stmt;
    Connection conn;
    ResultSet rs;
    private ObservableList<Cadeau> data;

    public CadeauService() {
        conn = MyConnection.getInstance().getConnexion();

    }

    @Override
    public ObservableList<Cadeau> listCadeau() {

        data = FXCollections.observableArrayList();
        try {
            String sql = "select * from  cadeau;";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Cadeau cadeau = new Cadeau(rs.getInt("id"), rs.getString("Categorie"), rs.getString("Type"), rs.getInt("jeton"), rs.getString("image"));
                data.add(cadeau);
            }

        } catch (SQLException ex) {
        }
        return data;

    }

    @Override
    public ObservableList<Cadeau> listCadeau(String categorie) {

        data = FXCollections.observableArrayList();

        try {
            String sql = "select * from  cadeau where Categorie=? ;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, categorie);

            rs = stmt.executeQuery();
            while (rs.next()) {
<<<<<<< HEAD
                Cadeau cadeau = new Cadeau(rs.getInt("id"),rs.getString("Categorie") ,rs.getString("Type"), rs.getInt("jeton"), rs.getString("image"));
=======
                Cadeau cadeau = new Cadeau(rs.getInt("idCadeau"), rs.getString("Catégorie"), rs.getString("Type"), rs.getInt("jeton"), rs.getString("image"));
>>>>>>> ccc2bdf2691f6bff288eace049d6e5cb64e0c432
                data.add(cadeau);
            }

        } catch (SQLException ex) {
        }
        return data;

    }

    @Override
    public Cadeau cadeau(String type) {

        Cadeau cadeau = new Cadeau();
        try {

            String sql = "select * from  cadeau where Type=? ;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);

            rs = stmt.executeQuery();
            while (rs.next()) {

                cadeau.setIdCadeau(rs.getInt("id"));
                cadeau.setType(type);
                cadeau.setCategorie(rs.getString("Categorie"));
                cadeau.setImg(rs.getString("image"));
                cadeau.setJeton(rs.getInt("jeton"));

            }

        } catch (SQLException ex) {
        }
        return cadeau;

    }

    @Override
    public List<Cadeau> listCadeau(int jeton) {

        List<Cadeau> list = new ArrayList<Cadeau>();

        try {
            String sql = "select * from  cadeau where jeton>=? ;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, jeton);

            rs = stmt.executeQuery();
            while (rs.next()) {
                Cadeau cadeau = new Cadeau(rs.getInt("id"), rs.getString("Categorie"), rs.getString("Type"), rs.getInt("jeton"), rs.getString("image"));
                list.add(cadeau);
            }

        } catch (SQLException ex) {
        }
        return list;

    }

    @Override
    public void ajoutCadeau(String categorie, String type, int jeton, String image) {

        String sql = "INSERT INTO cadeau(Categorie,Type,jeton,image) VALUES(?,?,?,?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, categorie);

            stmt.setString(2, type);
            stmt.setInt(3, jeton);
            stmt.setString(4, image);
            stmt.executeUpdate();

        } catch (SQLException ex) {

        }

    }

    @Override
    public void supprimer(int idCadeau) {

        String sql = "Delete from cadeau where id=? ;";

        try {
           Statement statement = conn.createStatement();
             int rs = statement.executeUpdate("Delete from cadeau where idCadeau='"+idCadeau+"';");
           
             if (rs <= 0) {
                System.out.println("Echec de suppression");
                Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Echec de suppression Cadeau associé à un utilisateur")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                             
                              
                        }
                    });

            notificationbuilder.showError();
          
            } else {
                System.out.println("suppression avec succès");
            }

        } catch (SQLException ex) {
            System.out.println("Echec de suppression");
                Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Echec de suppression Cadeau associé à un utilisateur")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                             
                              
                        }
                    });

            notificationbuilder.showError();

        }

    }

    @Override
    public void modifierCadeau(int idCadeau, String categorie, String type, int jeton) {

        try {

<<<<<<< HEAD
            String sql = "Upadate cadeau SET Categorie=?,Type=?,jeton=?,image=?) where id=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(5, idCadeau);
            stmt.setString(1, categorie);
            stmt.setString(2, type);
            stmt.setInt(3, jeton);
            stmt.setString(4, image);

            int s = stmt.executeUpdate();
            if (s < 0) {
=======
         //   String sql = "Update cadeau SET Catégorie=?,Type=?,jeton=?) where idCadeau=?;";
            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("UPDATE  cadeau SET Catégorie='" + categorie+ "',Type='" + type+"',jeton='" + jeton+ "' where idCadeau='" +idCadeau+ "'");
          
            if (rs < 0) {
>>>>>>> ccc2bdf2691f6bff288eace049d6e5cb64e0c432
                System.out.println("Echec");
            } else {
                System.out.println(" Modification avec succès");
            }
        } catch (SQLException ex) {

        }

    }

    @Override
    public Cadeau cadeau(int idCadeau) {

        Cadeau cadeau = new Cadeau();
        try {

            String sql = "select * from  cadeau where id=? ;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idCadeau);

            rs = stmt.executeQuery();
            while (rs.next()) {

                cadeau.setIdCadeau(rs.getInt("id"));
                cadeau.setType(rs.getString("Type"));
                cadeau.setCategorie(rs.getString("Categorie"));
                cadeau.setImg(rs.getString("image"));
                cadeau.setJeton(rs.getInt("jeton"));

            }

        } catch (SQLException ex) {
        }
        return cadeau;

    }

    @Override
    public int nombreRecompenseParPersonne(String username) {
        int nombreRecompense = 0;

        data = FXCollections.observableArrayList();

        try {
            String sql = "select count(idRecompense) from  recompense where username=? ;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);

            rs = stmt.executeQuery();
            while (rs.next()) {
                nombreRecompense = rs.getInt(1);
                System.out.println(nombreRecompense);

            }

        } catch (SQLException ex) {
        }
        return nombreRecompense;

    }

    @Override
    public String categorieCadeau(String idCadeau) {
        String cat = "";

        try {

<<<<<<< HEAD
            String sql = " SELECT Catégorie from cadeau where id='"+idCadeau+"' ;";
=======
            String sql = " SELECT Catégorie from cadeau where idCadeau='" + idCadeau + "' ;";
>>>>>>> ccc2bdf2691f6bff288eace049d6e5cb64e0c432
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {

<<<<<<< HEAD
              cat = rs.getString("Categorie");
=======
                cat = rs.getString("Catégorie");
>>>>>>> ccc2bdf2691f6bff288eace049d6e5cb64e0c432

            }

        } catch (SQLException ex) {

        }
        return cat;

    }

}
