package Services;

import DateStroge.MyConnection;
import Entity.EntiteStade;
import Entity.Equipe;
import Entity.Partie;
import Entity.Resultat;
import Entity.Stade;
import iService.iPartie;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PartieService implements iPartie {

    Connection conn;

    String nom = "";
    private ObservableList<Partie> data;

    public PartieService() {
        conn = MyConnection.getInstance().getConnexion();
    }

    @Override
    public String getNomEquipe(int id) {

        try {

            String sql = "Select nomEquipe from equipe where idEquipe=? ";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                nom = rs.getString("nomEquipe");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return nom;
    }

    @Override
    public int getidEquipe(String nom) {
        int id = 0;

        try {

            String sql = "Select idEquipe from equipe where nomEquipe=? ";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nom);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("idEquipe");

            }
        } catch (SQLException ex) {

        }

        return id;
    }

    @Override
    public Equipe getEquipe(int idEquipe) {
        Equipe equipe = new Equipe();

        try {

            String sql = "Select * from equipe where idEquipe=? ";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEquipe);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                equipe.setIDEquipe(rs.getInt("idEquipe"));
                equipe.setNomEquipe(rs.getString("NomEquipe"));
                equipe.setEntraineur(rs.getString("Entraineur"));
                equipe.setContinent(rs.getString("Continent"));
                equipe.setDrapeau("http://localhost/java/russia/"+rs.getString("Drapeau"));
                equipe.setGroupe(rs.getString("Groupe"));
                equipe.setButMarque(rs.getInt("ButMarque"));
                equipe.setButEncaisse(rs.getInt("butEncaisse"));
                equipe.setMatchJouee(rs.getInt("MatchJouee"));
                equipe.setMatchNull(rs.getInt("MatchNull"));
                equipe.setMatchGagne(rs.getInt("MatchGagne"));
                equipe.setMatchperdu(rs.getInt("MatchPerdu"));
                equipe.setNombrePoints(rs.getInt("NombrePoints"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return equipe;
    }

    public EntiteStade getStade(int idStade) {
        EntiteStade stade = new EntiteStade();

        try {

            String sql = "Select * from stades  where idstade=? ";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idStade);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                stade.setId(rs.getInt("idstade"));
                stade.setNom(rs.getString("nomstade"));

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return stade;
    }

    @Override
    public int getIdStade(String nomStade) {
        int idStade = 0;

        try {

            String sql = "Select * from stades  where nomstade=? ";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, nomStade);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                idStade = rs.getInt("idstade");

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return idStade;
    }

    @Override
    public ObservableList<Partie> getPartie() {

        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from partie ;");
            while (rs.next()) {

                data.add(new Partie(rs.getInt("id"), rs.getString("groupe"), rs.getDate("datePartie"), rs.getString("heurePartie"),
                        rs.getString("tour"), rs.getString("etatMatch"), rs.getString("etiquette"), getStade(rs.getInt("idstade")),
                        getEquipe(rs.getInt("home")), getEquipe(rs.getInt("away"))));
            }
        } catch (SQLException ex) {

        }

        return data;
    }

    @Override
    public ObservableList<Partie> getPartieAparier() {

        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from partie where etatMatch='PasEncore';");
            while (rs.next()) {

                data.add(new Partie(rs.getInt("id"), rs.getString("groupe"), rs.getDate("datePartie"), rs.getString("heurePartie"),
                        rs.getString("tour"), rs.getString("etatMatch"), rs.getString("etiquette"), getStade(rs.getInt("idstade")),
                        getEquipe(rs.getInt("home")), getEquipe(rs.getInt("away"))));
            }
        } catch (SQLException ex) {

        }

        return data;
    }

    @Override
    public void ajoutPartie(String groupe, String date, String heure, String tour, int idStade, int home, int away) {

        String sql = "INSERT INTO partie(groupe,datePartie,heurePartie,tour,etatMatch,etiquette,idStade,home,away) VALUES(?,?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, groupe);
            stmt.setString(2, date);
            stmt.setString(3, heure);
            stmt.setString(4, tour);
            stmt.setString(5, "PasEncore");
            stmt.setString(6, "");
            stmt.setInt(7, idStade);
            stmt.setInt(8, home);
            stmt.setInt(9, away);

            int rss = stmt.executeUpdate();

            if (rss < 0) {
                System.out.println("Echec");
            } else {
                System.out.println("Insert de bet avec succès");

            }

            // loadData();
        } catch (SQLException ex) {

        }

    }

    @Override

    public void insertPartie(Partie partie, String d) {

        String sql = "INSERT INTO partie(datePartie,heurePartie,tour,etatMatch,etiquette,idStade,home,away) VALUES(?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, d);
            stmt.setString(2, partie.getHeurePartie());
            stmt.setString(3, partie.getTour());
            stmt.setString(4, "PasEncore");
            stmt.setString(5, partie.getEtiquette());
            stmt.setInt(6, partie.getStade().getId());
            stmt.setInt(7, partie.getHome().getIDEquipe());
            stmt.setInt(8, partie.getAway().getIDEquipe());

            int rss = stmt.executeUpdate();

            if (rss < 0) {
                System.out.println("Echec");
            } else {
                System.out.println("Insert de bet avec succès");

            }

            // loadData();
        } catch (SQLException ex) {

        }

    }

    @Override
    public void supprimer(int idPartie) {

        String sql = "Delete from partie where id=? ;";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);
            int s = stmt.executeUpdate();
            if (s < 0) {
                System.out.println("Echec de suppression");
            } else {
                System.out.println("suppression avec succès");
            }

        } catch (SQLException ex) {

        }

    }

    @Override
    public int geIdHome(int idPartie) {

        int id = 0;

        try {

            String sql = "select * from partie where id=? ;";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("home");

            }
        } catch (SQLException ex) {

        }

        return id;
    }

    @Override
    public int geIdAway(int idPartie) {

        int id = 0;

        try {

            String sql = "select * from partie where id=? ;";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("away");

            }
        } catch (SQLException ex) {

        }

        return id;
    }

    @Override
    public ObservableList<Partie> partiegroupe(String groupe) {

        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from partie where groupe='" + groupe + "'");
            while (rs.next()) {

                data.add(new Partie(rs.getInt("id"), rs.getString("groupe"), rs.getDate("datePartie"), rs.getString("heurePartie"),
                        rs.getString("tour"), rs.getString("etatMatch"), rs.getString("etiquette"), getStade(rs.getInt("idStade")),
                        getEquipe(rs.getInt("home")), getEquipe(rs.getInt("away"))));

            }
        } catch (SQLException ex) {

        }

        return data;
    }

    @Override
    public ObservableList<Partie> partiegTour(String tour) {

        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from partie where tour='" + tour + "'");
            while (rs.next()) {

                data.add(new Partie(rs.getInt("id"), rs.getString("groupe"), rs.getDate("datePartie"), rs.getString("heurePartie"),
                        rs.getString("tour"), rs.getString("etatMatch"), rs.getString("etiquette"), getStade(rs.getInt("idStade")),
                        getEquipe(rs.getInt("home")), getEquipe(rs.getInt("away"))));

            }
        } catch (SQLException ex) {

        }

        return data;
    }

    @Override
    public ObservableList<Partie> partieEquipe(int id) {

        data = FXCollections.observableArrayList();

        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from partie where home='" + id + "' or away='" + id + "'");
            while (rs.next()) {

                data.add(new Partie(rs.getInt("id"), rs.getString("groupe"), rs.getDate("datePartie"), rs.getString("heurePartie"),
                        rs.getString("tour"), rs.getString("etatMatch"), rs.getString("etiquette"), getStade(rs.getInt("idStade")),
                        getEquipe(rs.getInt("home")), getEquipe(rs.getInt("away"))));

            }
        } catch (SQLException ex) {

        }

        return data;
    }

    @Override
    public Partie DetailsPartie(int idPartie) {
        Partie partie = new Partie();

        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from partie where id='" + idPartie + "'");
            while (rs.next()) {

                partie.setIdMatch(idPartie);
                partie.setGroup(rs.getString("groupe"));
                partie.setTour(rs.getString("tour"));
                partie.setHeurePartie(rs.getString("heurePartie"));
                partie.setDatematch(rs.getDate("datePartie"));
                partie.setEtatMatch(rs.getString("etatMatch"));
                partie.setEtiquette(rs.getString("etiquette"));
                partie.setStade(getStade(rs.getInt("idStade")));
                partie.setHome(getEquipe(rs.getInt("home")));
                partie.setAway(getEquipe(rs.getInt("away")));

            }
        } catch (SQLException ex) {

        }

        return partie;
    }

    @Override
    public void updateEtatPartie(int idPartie) {

        try {

            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("UPDATE  Partie  SET etatMatch='Jouee' WHERE id='" + idPartie + "' ");
            if (rs < 0) {
                System.out.println("Echec");
            } else {
                System.out.println("Modification avec succès");
            }

        } catch (SQLException ex) {

        }

    }

//    @Override
//    public void ajoutPartieTour(String tour, String groupe, String date, String heure, int home, int away) {
//
//        //Date date = new Date(dateMatch.getValue().toEpochDay());
//        //Date date = new Date();
//        String sql = "INSERT INTO partie(tour,groupe,datePartie,heurePartie,home,away) VALUES(?,?,?,?,?,?);";
//        try {
//            PreparedStatement stmt = conn.prepareStatement(sql);
//            stmt.setString(1, tour);
//            stmt.setString(2, groupe);
//
//            stmt.setString(3, date);
//            stmt.setString(4, heure);
//
//            stmt.setInt(5, home);
//            stmt.setInt(6, away);
//
//            int rss = stmt.executeUpdate();
//
//            if (rss < 0) {
//                System.out.println("Echec");
//            } else {
//                System.out.println("Insert de bet avec succès");
//
//            }
//
//        } catch (SQLException ex) {
//
//        }
//
//    }
    @Override
    public void updatPartie(Partie partie, String d) {

        try {

            Statement statement = conn.createStatement();
            int rs = statement.executeUpdate("UPDATE  partie SET home='" + partie.getHome().getIDEquipe() + "',away='" + partie.getAway().getIDEquipe() + "',datePartie='" + d + "',heurePartie='" + partie.getHeurePartie() + "',tour='" + partie.getTour() + "',groupe='" + partie.getGroup() + "',etiquette='" + partie.getEtiquette() + "',idStade='" + partie.getStade().getId() + "' where id='" + partie.getIdMatch() + "'");
            if (rs < 0) {
                System.out.println("Echec");
            } else {
                System.out.println("Modification avec succès");
            }

        } catch (SQLException ex) {

        }

    }

    @Override
    public Equipe getEquipe(String nomEquipe) {
        Equipe p = new Equipe();
        try {
            String myQuery = "SELECT * FROM equipe where NomEquipe='" + nomEquipe + "';";
            Statement stm = conn.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(myQuery);

            while (rs.next()) {

                p.setIDEquipe(rs.getInt("idEquipe"));
                p.setNomEquipe(rs.getString("NomEquipe"));
                p.setEntraineur(rs.getString("Entraineur"));
                p.setContinent(rs.getString("Continent"));
                p.setDrapeau("http://localhost/java/russia/"+rs.getString("Drapeau"));
                p.setGroupe(rs.getString("Groupe"));
                p.setMatchJouee(rs.getInt("MatchJouee"));
                p.setMatchperdu(rs.getInt("MatchPerdu"));
                p.setMatchNull(rs.getInt("MatchNull"));
                p.setMatchGagne(rs.getInt("MatchGagne"));
                p.setButMarque(rs.getInt("ButMarque"));
                p.setButEncaisse(rs.getInt("butEncaisse"));
                p.setNombrePoints(rs.getInt("NombrePoints"));

            }
        } catch (Exception e) {
            System.err.println("Echec");
        }
        return p;
    }

    @Override
    public int geIdPartie(int idHome, int idAway) {

        int id = 0;

        try {

            String sql = "select * from partie where home=? and away=? ;";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idHome);
            stmt.setInt(2, idAway);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");

            }
        } catch (SQLException ex) {

        }

        return id;
    }

    @Override
    public ObservableList<Partie> partiegJouee() {
        String joue = "Jouee";
        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from partie where etatMatch='" + joue + "'");
            while (rs.next()) {

                data.add(new Partie(rs.getInt("id"), rs.getString("groupe"), rs.getDate("datePartie"), rs.getString("heurePartie"),
                        rs.getString("tour"), rs.getString("etatMatch"), rs.getString("etiquette"), getStade(rs.getInt("idStade")),
                        getEquipe(rs.getInt("home")), getEquipe(rs.getInt("away"))));

            }
        } catch (SQLException ex) {

        }

        return data;
    }

    @Override
    public ObservableList<Partie> partiegNonJouee() {
        String joue = "PasEncore";
        data = FXCollections.observableArrayList();
        try {
            ResultSet rs = conn.createStatement().executeQuery("select * from partie where etatMatch='" + joue + "'");
            while (rs.next()) {

                data.add(new Partie(rs.getInt("id"), rs.getString("groupe"), rs.getDate("datePartie"), rs.getString("heurePartie"),
                        rs.getString("tour"), rs.getString("etatMatch"), rs.getString("etiquette"), getStade(rs.getInt("idStade")),
                        getEquipe(rs.getInt("home")), getEquipe(rs.getInt("away"))));

            }
        } catch (SQLException ex) {

        }

        return data;
    }

}
