package Services;

import DateStroge.MyConnection;
import Entity.Cadeau;
import Entity.EntiteStade;
import Entity.Equipe;
import Entity.Partie;
import Entity.Resultat;
import iService.iResultat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ResultatService implements iResultat {

    PreparedStatement stmt;
    Connection conn;
    ResultSet rs;
    private ObservableList<Resultat> data;

    public ResultatService() {
        conn = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void supprimerRsultat(int idPartie) {

        String sql = "Delete from resultat where idPartie=? ;";

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
    public void ajoutResultat(int idPArtie) {

        String sql = "INSERT INTO resultat(idPartie,butHome,butAway) VALUES(?,?,?);";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPArtie);

            stmt.setInt(2, 0);
            stmt.setInt(3, 0);

            stmt.executeUpdate();

        } catch (SQLException ex) {

        }
    }

    //modifieeeeeeeeeeeeeeeeeerrrrrrrrrrrrrrrrrrrrrrrrrrr
    @Override
    public void modifierResulltat(int idPartie, int butHome, int butAway) {

        PartieService partieService = new PartieService();

        ServiceBet serviceBet = new ServiceBet();

        try {

            String sql = "Update resultat SET butHome=?,butAway=? where idPartie=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(3, idPartie);
            stmt.setInt(1, butHome);
            stmt.setInt(2, butAway);

            int s = stmt.executeUpdate();
            if (s < 0) {
                System.out.println("Echec");
            } else {
                System.out.println(" Modification avec succès");
                partieService.updateEtatPartie(idPartie);
                serviceBet.updateJetonEtat(idPartie);
//               if(EquipeGagne(idPartie).getIDEquipe()==0){
//                     ChangerChampEquipe(EquipeGagne(idPartie), idPartie);}
//                
//                else{
//                      ChangerChampEquipe(EquipeGagne(idPartie));}
                if(partieService.DetailsPartie(idPartie).getTour().equals("16es de finale")){
                  verficationChangement(idPartie);}
            }
        } catch (SQLException ex) {

        }

    }

    @Override
    public List<Resultat> listResultat() {
        PartieService partieService = new PartieService();

        List<Resultat> list = new ArrayList<Resultat>();

        try {
            String sql = "select * from  resultat ;";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {

                Resultat resultat = new Resultat(rs.getInt("butHome"), rs.getInt("butAway"), partieService.DetailsPartie(rs.getInt("idPartie")));
                list.add(resultat);
            }

        } catch (SQLException ex) {
        }
        return list;

    }

    @Override
    public List<Resultat> listResultat(int idPartie) {

        List<Resultat> list = new ArrayList<Resultat>();
        PartieService partieService = new PartieService();

        try {
            String sql = "select * from  resultat where idPartie=? ;";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, idPartie);
            rs = stmt.executeQuery();
            while (rs.next()) {

                Resultat resultat = new Resultat(rs.getInt("butHome"), rs.getInt("butAway"), partieService.DetailsPartie(idPartie));
                list.add(resultat);
            }

        } catch (SQLException ex) {
        }
        return list;

    }

    @Override
    public ObservableList<Resultat> listResultats() {

        data = FXCollections.observableArrayList();
        PartieService partieService = new PartieService();
        try {
            String sql = "SELECT * FROM partie, resultat WHERE partie.id =resultat.idPartie ;";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {

                Resultat resultat = new Resultat(rs.getInt("butHome"), rs.getInt("butAway"), partieService.DetailsPartie(rs.getInt("idPartie")));
                data.add(resultat);
            }

        } catch (SQLException ex) {
        }
        return data;

    }
    
    
     public ObservableList<Resultat> listResultatsParGroupe(String groupe) {

        data = FXCollections.observableArrayList();
        PartieService partieService = new PartieService();
        try {
            String sql = "SELECT * FROM partie, resultat WHERE (partie.id =resultat.idPartie) and (partie.groupe='"+groupe+"') ;" ;
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {
                   if ((partieService.DetailsPartie(rs.getInt("idPartie"))).getEtatMatch().equals("Jouee")){
                Resultat resultat = new Resultat(rs.getInt("butHome"), rs.getInt("butAway"), partieService.DetailsPartie(rs.getInt("idPartie")));
                data.add(resultat);
            }else{
                   Resultat resultat = new Resultat(100, 100, partieService.DetailsPartie(rs.getInt("idPartie")));
                data.add(resultat);
                   }
            }
        } catch (SQLException ex) {
        }
        return data;

    }

    @Override
    public ObservableList<Resultat> listResultatsJoue() {

        data = FXCollections.observableArrayList();
        String joue = "Jouee";
        PartieService partieService = new PartieService();
        try {
            String sql = "SELECT * FROM resultat ;";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {

                if ((partieService.DetailsPartie(rs.getInt("idPartie"))).getEtatMatch().equals(joue)) {

                    Resultat resultat = new Resultat(rs.getInt("butHome"), rs.getInt("butAway"), partieService.DetailsPartie(rs.getInt("idPartie")));
                    data.add(resultat);
                }
            }
        } catch (SQLException ex) {
        }
        return data;

    }

    @Override
    public ObservableList<Resultat> listResultatsNonJoue() {
        data = FXCollections.observableArrayList();
        String joue = "PasEncore";
        PartieService partieService = new PartieService();
        try {
            String sql = "SELECT * FROM resultat ;";
            stmt = conn.prepareStatement(sql);

            rs = stmt.executeQuery();
            while (rs.next()) {

                if ((partieService.DetailsPartie(rs.getInt("idPartie"))).getEtatMatch().equals(joue)) {

                    Resultat resultat = new Resultat(rs.getInt("butHome"), rs.getInt("butAway"), partieService.DetailsPartie(rs.getInt("idPartie")));
                    data.add(resultat);
                }
            }
        } catch (SQLException ex) {
        }
        return data;

    }

    @Override
    public Equipe EquipeGagne(int idPartie) {

        Equipe equipeGagner = new Equipe();

        Equipe equipe = new Equipe();
        PartieService partieService = new PartieService();
        Partie partie = partieService.DetailsPartie(idPartie);
        try {
            String sql = "SELECT * FROM resultat WHERE idPartie=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int butHome = rs.getInt("butHome");
                int butAway = rs.getInt("butAway");
                if (butHome > butAway) {
                    equipeGagner = partie.getHome();

                } else if (butHome < butAway) {
                    equipeGagner = partie.getAway();

                } else {

                    equipeGagner = equipe;
                }

            }
        } catch (SQLException ex) {
        }
        return equipeGagner;

    }

    public Equipe EquipePerdu(int idPartie) {

        Equipe equipePerdu = new Equipe();

        Equipe equipe = new Equipe();
        PartieService partieService = new PartieService();
        Partie partie = partieService.DetailsPartie(idPartie);
        try {
            String sql = "SELECT * FROM resultat WHERE idPartie=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int butHome = rs.getInt("butHome");
                int butAway = rs.getInt("butAway");
                if (butHome < butAway) {
                    equipePerdu = partie.getHome();

                } else if (butHome > butAway) {
                    equipePerdu = partie.getAway();

                }

            }
        } catch (SQLException ex) {
        }
        return equipePerdu;

    }

    //a vérifierrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    public void verficationChangement(int idPartie) {

        Equipe equipeGagner = new Equipe();
        Equipe equipePerdu = new Equipe();
        Equipe equipe = new Equipe();
        PartieService partieService = new PartieService();
        Partie partie = partieService.DetailsPartie(idPartie);
        try {
            String sql = "SELECT * FROM resultat WHERE idPartie=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idPartie);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int butHome = rs.getInt("butHome");
                int butAway = rs.getInt("butAway");
                if (butHome > butAway) {
                    equipeGagner = partie.getHome();
                    equipePerdu = partie.getAway();

                    equipeGagner.setButMarque((equipeGagner.getButMarque() + butHome));
                    equipeGagner.setButEncaisse((equipeGagner.getButEncaisse()) + butAway);
                    equipeGagner.setMatchJouee((equipeGagner.getMatchJouee()) + 1);
                    equipeGagner.setMatchGagne((equipeGagner.getMatchGagne()) + 1);
                    equipeGagner.setNombrePoints((equipeGagner.getNombrePoints()) + 3);
                    ChangerChampEquipe(equipeGagner);
                    equipePerdu.setButMarque((equipePerdu.getButMarque()) + butAway);
                    equipePerdu.setButEncaisse((equipePerdu.getButEncaisse()) + butHome);
                    equipePerdu.setMatchJouee((equipePerdu.getMatchJouee()) + 1);
                    equipePerdu.setMatchperdu((equipePerdu.getMatchperdu()) + 1);
                    ChangerChampEquipe(equipePerdu);

                } else if (butHome < butAway) {
                    equipeGagner = partie.getAway();
                    equipePerdu = partie.getHome();

                    equipeGagner.setButMarque((equipeGagner.getButMarque()) + butAway);
                    equipeGagner.setButEncaisse((equipeGagner.getButEncaisse()) + butHome);
                    equipeGagner.setMatchJouee((equipeGagner.getMatchJouee()) + 1);
                    equipeGagner.setMatchGagne((equipeGagner.getMatchGagne()) + 1);
                    equipeGagner.setNombrePoints((equipeGagner.getNombrePoints()) + 3);
                    ChangerChampEquipe(equipeGagner);
                    equipePerdu.setButMarque((equipePerdu.getButMarque()) + butHome);
                    equipePerdu.setButEncaisse((equipePerdu.getButEncaisse()) + butAway);
                    equipePerdu.setMatchJouee((equipePerdu.getMatchJouee()) + 1);
                    equipePerdu.setMatchperdu((equipePerdu.getMatchperdu()) + 1);
                    ChangerChampEquipe(equipePerdu);
                } else {

                    equipe.setButEncaisse((equipe.getButEncaisse()) + butHome);
                    equipe.setButMarque((equipe.getButMarque()) + butAway);
                    equipe.setNombrePoints((equipe.getNombrePoints()) + 1);
                    equipe.setMatchNull((equipe.getMatchNull()) + 1);
                    equipe.setMatchJouee((equipe.getMatchJouee()) + 1);

                    ChangerChampEquipe(equipe, partie.getAway().getIDEquipe(), (partie.getAway().getNombrePoints()) + 1);
                    ChangerChampEquipe(equipe, partie.getHome().getIDEquipe(), (partie.getHome().getNombrePoints()) + 1);
                    //equipeGagner = equipe;
                }

            }
        } catch (SQLException ex) {
        }

    }

//modifeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee
    public void ChangerChampEquipe(Equipe equipe) {

        try {

            String sql = "Update equipe SET ButMarque=?,butEncaisse=?,MatchJouee=?,MatchNull=?,MatchGagne=?,MatchPerdu=?,NombrePoints=? where idEquipe=?;";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, equipe.getButMarque());
            stmt.setInt(2, equipe.getButEncaisse());
            stmt.setInt(3, equipe.getMatchJouee());
            stmt.setInt(4, equipe.getMatchNull());
            stmt.setInt(5, equipe.getMatchGagne());
            stmt.setInt(6, equipe.getMatchperdu());
            stmt.setInt(7, equipe.getNombrePoints());
            stmt.setInt(8, equipe.getIDEquipe());

            int s = stmt.executeUpdate();
            if (s < 0) {
                System.out.println("Echec");
            } else {
                System.out.println(" Modification avec succès");

            }
        } catch (SQLException ex) {

        }

    }

    public void ChangerChampEquipe(Equipe equipe, int idEquipe, int nbPoints) {

        try {

            String sql = "Update equipe SET ButMarque=?,butEncaisse=?,MatchJouee=?,MatchNull=?,MatchGagne=?,MatchPerdu=?,NombrePoints=? where idEquipe=?;";
            stmt = conn.prepareStatement(sql);

            stmt.setInt(1, equipe.getButMarque());
            stmt.setInt(2, equipe.getButEncaisse());
            stmt.setInt(3, equipe.getMatchJouee());
            stmt.setInt(4, equipe.getMatchNull());
            stmt.setInt(5, equipe.getMatchGagne());
            stmt.setInt(6, equipe.getMatchperdu());
            stmt.setInt(7, nbPoints);
            stmt.setInt(8, idEquipe);

            int s = stmt.executeUpdate();
            if (s < 0) {
                System.out.println("Echec");
            } else {
                System.out.println(" Modification avec succès");

            }
        } catch (SQLException ex) {

        }

    }

    public int nombreMatchTour16() {
        int nombreMatch = 0;

        try {
            String sql = "select count(id) from  partie where etatMatch=? and tour=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Jouee");
            stmt.setString(2, "16es de finale");

            rs = stmt.executeQuery();
            while (rs.next()) {
                nombreMatch = rs.getInt(1);

            }

        } catch (SQLException ex) {
        }
        return nombreMatch;

    }

    static boolean bolAB = true;

    public void affectationGroupeAB18Eme() {

        EquipeService equipeService = new EquipeService();
        PartieService partieService = new PartieService();
        ObservableList<Equipe> ListA = FXCollections.observableArrayList();
        ListA = equipeService.afficherEquipes("A");
        Collections.sort(ListA);

        ObservableList<Equipe> ListB = FXCollections.observableArrayList();
        ListB = equipeService.afficherEquipes("B");
        Collections.sort(ListB);

        if ((nombreMatchParGroupe("A") == 6) && ((nombreMatchParGroupe("B")) == 6) && bolAB) {
            EntiteStade stadeAB = new EntiteStade();
            stadeAB.setId(2);
            Partie partieA1 = new Partie("16:00", "8es de finale", "A1", stadeAB, ListA.get(0), ListB.get(1));
            Partie partieB1 = new Partie("20:00", "8es de finale", "B1", stadeAB, ListB.get(0), ListA.get(1));

            partieService.insertPartie(partieA1, "2018-06-30");
            ajoutResultat(partieService.geIdPartie(ListA.get(0).getIDEquipe(), ListB.get(1).getIDEquipe()));
            partieService.insertPartie(partieB1, "2018-06-30");
            ajoutResultat(partieService.geIdPartie(ListB.get(0).getIDEquipe(), ListA.get(1).getIDEquipe()));
            bolAB = false;

        }

    }
    static boolean bolCD = true;

    public void affectationGroupeCD8Eme() {

        EquipeService equipeService = new EquipeService();
        PartieService partieService = new PartieService();
        ObservableList<Equipe> ListC = FXCollections.observableArrayList();
        ListC = equipeService.afficherEquipes("C");
        Collections.sort(ListC);

        ObservableList<Equipe> ListD = FXCollections.observableArrayList();
        ListD = equipeService.afficherEquipes("D");
        Collections.sort(ListD);

        if ((nombreMatchParGroupe("C") == 6) && ((nombreMatchParGroupe("D")) == 6) && bolCD) {

            EntiteStade stadeCD = new EntiteStade();
            stadeCD.setId(2);
            Partie partieC1 = new Partie("16:00", "8es de finale", "C1", stadeCD, ListC.get(0), ListD.get(1));
            Partie partieD1 = new Partie("20:00", "8es de finale", "D1", stadeCD, ListD.get(0), ListC.get(1));
            partieService.insertPartie(partieC1, "2018-07-01");
            ajoutResultat(partieService.geIdPartie(ListC.get(0).getIDEquipe(), ListD.get(1).getIDEquipe()));
            partieService.insertPartie(partieD1, "2018-07-01");
            ajoutResultat(partieService.geIdPartie(ListD.get(0).getIDEquipe(), ListC.get(1).getIDEquipe()));
            bolCD = false;
        }

    }
    static boolean bolEF = true;

    public void affectationGroupeEF8Eme() {

        EquipeService equipeService = new EquipeService();
        PartieService partieService = new PartieService();
        ObservableList<Equipe> ListE = FXCollections.observableArrayList();
        ListE = equipeService.afficherEquipes("E");
        Collections.sort(ListE);

        ObservableList<Equipe> ListF = FXCollections.observableArrayList();
        ListF = equipeService.afficherEquipes("F");
        Collections.sort(ListF);

        if ((nombreMatchParGroupe("E") == 6) && ((nombreMatchParGroupe("F")) == 6) && bolEF) {

            EntiteStade stadeEF = new EntiteStade();
            stadeEF.setId(2);
            Partie partieE1 = new Partie("16:00", "8es de finale", "E1", stadeEF, ListE.get(0), ListF.get(1));
            Partie partieF1 = new Partie("20:00", "8es de finale", "F1", stadeEF, ListF.get(0), ListE.get(1));
            partieService.insertPartie(partieE1, "2018-07-02");
            ajoutResultat(partieService.geIdPartie(ListE.get(0).getIDEquipe(), ListF.get(1).getIDEquipe()));
            partieService.insertPartie(partieF1, "2018-07-02");
            ajoutResultat(partieService.geIdPartie(ListF.get(0).getIDEquipe(), ListE.get(1).getIDEquipe()));
            bolEF = false;
        }

    }
    static boolean bolGH = true;

    public void affectationGroupeGH8Eme() {

        EquipeService equipeService = new EquipeService();
        PartieService partieService = new PartieService();
        ObservableList<Equipe> ListG = FXCollections.observableArrayList();
        ListG = equipeService.afficherEquipes("G");
        Collections.sort(ListG);

        ObservableList<Equipe> ListH = FXCollections.observableArrayList();
        ListH = equipeService.afficherEquipes("H");
        Collections.sort(ListH);

        if ((nombreMatchParGroupe("G") == 6) && ((nombreMatchParGroupe("H")) == 6) && bolGH) {

            EntiteStade stadeGH = new EntiteStade();
            stadeGH.setId(2);
            Partie partieG1 = new Partie("16:00", "8es de finale", "G1", stadeGH, ListG.get(0), ListH.get(1));
            Partie partieH1 = new Partie("20:00", "8es de finale", "H1", stadeGH, ListH.get(0), ListG.get(1));
            partieService.insertPartie(partieG1, "2018-07-03");
            ajoutResultat(partieService.geIdPartie(ListG.get(0).getIDEquipe(), ListH.get(1).getIDEquipe()));
            partieService.insertPartie(partieH1, "2018-07-03");
            ajoutResultat(partieService.geIdPartie(ListH.get(0).getIDEquipe(), ListG.get(1).getIDEquipe()));
            bolGH = false;

        }

    }

    public int idPartie(String etiq) {

        int id = 0;

        try {
            String sql = "SELECT * from  partie  WHERE etiquette=? ";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, etiq);
            rs = stmt.executeQuery();

            while (rs.next()) {
                id = rs.getInt("id");

            }
        } catch (SQLException ex) {
        }
        return id;

    }
    static boolean bol41 = true;

    public void affectation4eme1() {

        PartieService partieService = new PartieService();
        Partie partieC1 = new Partie();
        Partie partieA1 = new Partie();
        Equipe equipeGaC1 = EquipeGagne((idPartie("C1")));
        Equipe equipeGaA1 = EquipeGagne((idPartie("A1")));

          if ((partieService.DetailsPartie(idPartie("C1")).getIdMatch() == 0) && (partieService.DetailsPartie(idPartie("E1")).getIdMatch() == 0)) {
            partieC1.setEtatMatch("PasEncore");
            partieA1.setEtatMatch("PasEncore");
        } else {
            partieC1 = partieService.DetailsPartie(idPartie("C1"));
            partieA1 = partieService.DetailsPartie(idPartie("A1"));

        
        if ((partieC1.getEtatMatch().equals("Jouee")) && (partieA1.getEtatMatch().equals("Jouee")) && bol41) {
            EntiteStade stadeGH = new EntiteStade();
            stadeGH.setId(2);
            Partie partieA11 = new Partie("16:00", "4es de finale", "A11", stadeGH, equipeGaA1, equipeGaC1);

            partieService.insertPartie(partieA11, "2018-07-06");
            ajoutResultat(partieService.geIdPartie(equipeGaA1.getIDEquipe(), equipeGaC1.getIDEquipe()));
            bol41 = false;
        }

    }}
    static boolean bol42 = true;

    public void affectation4eme2() {

        PartieService partieService = new PartieService();
        Partie partieE1 = new Partie();
        Partie partieG1 = new Partie();

        Equipe equipeGaE1 = EquipeGagne(idPartie("E1"));
        Equipe equipeGaG1 = EquipeGagne(idPartie("G1"));

        if ((partieService.DetailsPartie(idPartie("E1")).getIdMatch() == 0) && (partieService.DetailsPartie(idPartie("G1")).getIdMatch() == 0)) {
            partieE1.setEtatMatch("PasEncore");
            partieG1.setEtatMatch("PasEncore");
        } else {
            partieE1 = partieService.DetailsPartie(idPartie("E1"));
            partieG1 = partieService.DetailsPartie(idPartie("G1"));

        

        if ((partieE1.getEtatMatch().equals("Jouee")) && (partieG1.getEtatMatch().equals("Jouee")) && bol42) {

            EntiteStade stadeGH = new EntiteStade();
            stadeGH.setId(2);
            Partie partieA11 = new Partie("16:00", "4es de finale", "B11", stadeGH, equipeGaE1, equipeGaG1);

            partieService.insertPartie(partieA11, "2018-07-06");
            ajoutResultat(partieService.geIdPartie(equipeGaE1.getIDEquipe(), equipeGaG1.getIDEquipe()));
            bol42 = false;
        }
        }
    }
    static boolean bol43 = true;

    public void affectation4eme3() {

        PartieService partieService = new PartieService();
        Partie partieB1 = new Partie();
        Partie partieD1 = new Partie();
        Equipe equipeGaB1 = EquipeGagne(idPartie("B1"));
        Equipe equipeGaD1 = EquipeGagne(idPartie("D1"));

        if ((partieService.DetailsPartie(idPartie("B1")).getIdMatch() == 0) && (partieService.DetailsPartie(idPartie("D1")).getIdMatch() == 0)) {
            partieB1.setEtatMatch("PasEncore");
            partieD1.setEtatMatch("PasEncore");
        } else {
            partieB1 = partieService.DetailsPartie(idPartie("B1"));
            partieD1 = partieService.DetailsPartie(idPartie("D1"));

        

        if ((partieB1.getEtatMatch().equals("Jouee")) && (partieD1.getEtatMatch().equals("Jouee")) && bol43) {

            EntiteStade stadeGH = new EntiteStade();
            stadeGH.setId(2);
            Partie partieA11 = new Partie("16:00", "4es de finale", "C11", stadeGH, equipeGaB1, equipeGaD1);

            partieService.insertPartie(partieA11, "2018-07-07");
            ajoutResultat(partieService.geIdPartie(equipeGaB1.getIDEquipe(), equipeGaD1.getIDEquipe()));
            bol43 = false;
        }
        }
    }
    static boolean bol44 = true;

    public void affectation4eme4() {

        PartieService partieService = new PartieService();

        Partie partieF1 = new Partie();
        Partie partieH1 = new Partie();

        Equipe equipeGaF1 = EquipeGagne(idPartie("F1"));
        Equipe equipeGaH1 = EquipeGagne(idPartie("H1"));
        if ((partieService.DetailsPartie(idPartie("F1")).getIdMatch() == 0) && (partieService.DetailsPartie(idPartie("H1")).getIdMatch() == 0)) {
            partieF1.setEtatMatch("PasEncore");
            partieH1.setEtatMatch("PasEncore");
        } else {
            partieF1 = partieService.DetailsPartie(idPartie("F1"));
            partieH1 = partieService.DetailsPartie(idPartie("H1"));



        if ((partieF1.getEtatMatch().equals("Jouee")) && (partieH1.getEtatMatch().equals("Jouee")) && bol44) {

            EntiteStade stadeGH = new EntiteStade();
            stadeGH.setId(2);
            Partie partieA11 = new Partie("16:00", "4es de finale", "D11", stadeGH, equipeGaF1, equipeGaH1);

            partieService.insertPartie(partieA11, "2018-07-07");
            ajoutResultat(partieService.geIdPartie(equipeGaF1.getIDEquipe(), equipeGaH1.getIDEquipe()));
            bol44 = false;
        }
        }
    }

    public int nombreMatchTour4() {
        int nombreMatch = 0;

        try {
            String sql = "select count(id) from  partie where etatMatch=? and tour=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Jouee");
            stmt.setString(2, "4es de finale");

            rs = stmt.executeQuery();
            while (rs.next()) {
                nombreMatch = rs.getInt(1);

            }

        } catch (SQLException ex) {
        }
        return nombreMatch;

    }
    static boolean bol21 = true;

    public void affectation2eme1() {

        PartieService partieService = new PartieService();

        Partie partieA11 = new Partie();
        Partie partieB11 = new Partie();
        Equipe equipeGaA11 = EquipeGagne(idPartie("A11"));
        Equipe equipeGaB11 = EquipeGagne(idPartie("B11"));
    
          if ((partieService.DetailsPartie(idPartie("A11")).getIdMatch() == 0) || (partieService.DetailsPartie(idPartie("B11")).getIdMatch() == 0)) {
            partieA11.setEtatMatch("PasEncore");
            partieB11.setEtatMatch("PasEncore");
        } else {
            partieA11 = partieService.DetailsPartie(idPartie("A11"));
            partieB11 = partieService.DetailsPartie(idPartie("B11"));

        
        if ((partieA11.getEtatMatch().equals("Jouee")) && (partieB11.getEtatMatch().equals("Jouee")) && bol21) {

            EntiteStade stadeGH = new EntiteStade();
            stadeGH.setId(2);
            Partie partieA111 = new Partie("20:00", "Demi Finale", "A111", stadeGH, equipeGaA11, equipeGaB11);
            partieService.insertPartie(partieA111, "2018-07-10");
            ajoutResultat(partieService.geIdPartie(equipeGaA11.getIDEquipe(), equipeGaB11.getIDEquipe()));
            bol21 = false;
        }
          }
    }
    static boolean bol22 = true;

    public void affectation2eme2() {

        PartieService partieService = new PartieService();

        Partie partieC11 = new Partie();
        Partie partieD11 = new Partie();
        Equipe equipeGaA11 = EquipeGagne(idPartie("C11"));
        Equipe equipeGaB11 = EquipeGagne(idPartie("D11"));

          if ((partieService.DetailsPartie(idPartie("C11")).getIdMatch() == 0) || (partieService.DetailsPartie(idPartie("D11")).getIdMatch() == 0)) {
            partieC11.setEtatMatch("PasEncore");
            partieD11.setEtatMatch("PasEncore");
        } else {
            partieC11 = partieService.DetailsPartie(idPartie("C11"));
            partieD11 = partieService.DetailsPartie(idPartie("D11"));

        
        if ((partieC11.getEtatMatch().equals("Jouee")) && (partieD11.getEtatMatch().equals("Jouee")) && bol22) {

            EntiteStade stadeGH = new EntiteStade();
            stadeGH.setId(2);
            Partie partieB111 = new Partie("20:00", "Demi Finale", "B111", stadeGH, equipeGaA11, equipeGaB11);

            partieService.insertPartie(partieB111, "2018-07-11");
            ajoutResultat(partieService.geIdPartie(equipeGaA11.getIDEquipe(), equipeGaB11.getIDEquipe()));
            bol22 = false;
        }
          }
    }

    public int nombreMatchTour2() {
        int nombreMatch = 0;

        try {
            String sql = "select count(id) from  partie where etatMatch=? and tour=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Jouee");
            stmt.setString(2, "Demi Finale");

            rs = stmt.executeQuery();
            while (rs.next()) {
                nombreMatch = rs.getInt(1);

            }

        } catch (SQLException ex) {
        }
        return nombreMatch;

    }
    static boolean bolfinal = true;

    public void affectionfinal() {
        PartieService partieService = new PartieService();

        Partie partieA111 = new Partie();
        Partie partieB111 = new Partie();

        Equipe equipeGaA111 = EquipeGagne(idPartie("A111"));
        Equipe equipeGaB111 = EquipeGagne(idPartie("B111"));

        Equipe equipePer1 = EquipePerdu(idPartie("A111"));
        Equipe equipePer2 = EquipePerdu(idPartie("B111"));

          if ((partieService.DetailsPartie(idPartie("A111")).getIdMatch() == 0) || (partieService.DetailsPartie(idPartie("B1111")).getIdMatch() == 0)) {
            partieA111.setEtatMatch("PasEncore");
            partieB111.setEtatMatch("PasEncore");
        } else {
            partieA111 = partieService.DetailsPartie(idPartie("A111"));
            partieB111 = partieService.DetailsPartie(idPartie("B111"));

        
        
        if ((partieA111.getEtatMatch().equals("Jouee")) && (partieB111.getEtatMatch().equals("Jouee")) && bolfinal) {

            EntiteStade stadeGH = new EntiteStade();
            stadeGH.setId(2);
            Partie partieA1111 = new Partie("17:00", "Finale", "A1111", stadeGH, equipeGaA111, equipeGaB111);
            Partie partieB1111 = new Partie("16:00", "3eme Place", "B1111", stadeGH, equipePer1, equipePer2);

            partieService.insertPartie(partieA1111, "2018-07-15");
            ajoutResultat(partieService.geIdPartie(equipeGaA111.getIDEquipe(), equipeGaB111.getIDEquipe()));
            partieService.insertPartie(partieB1111, "2018-07-14");
            ajoutResultat(partieService.geIdPartie(equipePer1.getIDEquipe(), equipePer2.getIDEquipe()));
            bolfinal = false;
        }
    }
    }
    public int nombreMatchParGroupe(String groupe) {
        int nombreMatch = 0;

        try {
            String sql = "select count(id) from  partie where etatMatch=? and groupe=?;";
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, "Jouee");
            stmt.setString(2, groupe);

            rs = stmt.executeQuery();
            while (rs.next()) {
                nombreMatch = rs.getInt(1);

            }

        } catch (SQLException ex) {
        }
        return nombreMatch;

    }

    public static boolean isBolAB() {
        return bolAB;
    }

    public static void setBolAB(boolean bolAB) {
        ResultatService.bolAB = bolAB;
    }

    public static boolean isBolCD() {
        return bolCD;
    }

    public static void setBolCD(boolean bolCD) {
        ResultatService.bolCD = bolCD;
    }

    public static boolean isBolEF() {
        return bolEF;
    }

    public static void setBolEF(boolean bolEF) {
        ResultatService.bolEF = bolEF;
    }

    public static boolean isBolGH() {
        return bolGH;
    }

    public static void setBolGH(boolean bolGH) {
        ResultatService.bolGH = bolGH;
    }

    public static boolean isBol41() {
        return bol41;
    }

    public static void setBol41(boolean bol41) {
        ResultatService.bol41 = bol41;
    }

    public static boolean isBol42() {
        return bol42;
    }

    public static void setBol42(boolean bol42) {
        ResultatService.bol42 = bol42;
    }

    public static boolean isBol43() {
        return bol43;
    }

    public static void setBol43(boolean bol43) {
        ResultatService.bol43 = bol43;
    }

    public static boolean isBol44() {
        return bol44;
    }

    public static void setBol44(boolean bol44) {
        ResultatService.bol44 = bol44;
    }

    public static boolean isBol21() {
        return bol21;
    }

    public static void setBol21(boolean bol21) {
        ResultatService.bol21 = bol21;
    }

    public static boolean isBol22() {
        return bol22;
    }

    public static void setBol22(boolean bol22) {
        ResultatService.bol22 = bol22;
    }

    public static boolean isBolfinal() {
        return bolfinal;
    }

    public static void setBolfinal(boolean bolfinal) {
        ResultatService.bolfinal = bolfinal;
    }

}
