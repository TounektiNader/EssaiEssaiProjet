package Services;

import DateStroge.MyConnection;
import Entity.Cadeau;
import Entity.Equipe;
import Entity.Partie;
import Entity.Resultat;
import iService.iResultat;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResultatService implements iResultat {

    PreparedStatement stmt;
    Connection conn;
    ResultSet rs;
    private ObservableList<Resultat> data;

    public ResultatService() {
        conn = MyConnection.getInstance().getConnexion();
    }

    @Override
    public void ajoutResultat(int idPArtie) {

        String sql = "INSERT INTO resulltat(idPartie,butHome,butAway) VALUES(?,?,?);";
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
                verficationChangement(idPartie);
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
                }
            }

        } catch (SQLException ex) {
        }
        return data;

    }
    
//modifiereeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee

//    @Override
//    public Equipe EquipeGagne(int idPartie) {
//
//        Equipe equipeGagner = new Equipe();
//        Equipe equipePerdu = new Equipe();
//        Equipe equipe = new Equipe();
//        PartieService partieService = new PartieService();
//        Partie partie = partieService.DetailsPartie(idPartie);
//        try {
//            String sql = "SELECT * FROM resultat WHERE idPartie=? ";
//            stmt = conn.prepareStatement(sql);
//            stmt.setInt(1, idPartie);
//            rs = stmt.executeQuery();
//
//            while (rs.next()) {
//                int butHome = rs.getInt("butHome");
//                int butAway = rs.getInt("butAway");
//                if (butHome > butAway) {
//                    equipeGagner = partie.getHome();
//                    equipePerdu = partie.getAway();
//
//                
//
//                } else if (butHome < butAway) {
//                    equipeGagner = partie.getAway();
//                     } else {
//                      
//                    equipe.setButEncaisse((equipe.getButEncaisse() + butHome));
//                    equipe.setButMarque((equipe.getButMarque() + butAway));
//                    equipe.setNombrePoints((equipe.getNombrePoints() + 1));
//                    equipe.setMatchNull((equipe.getMatchNull() + 1));
//                    equipe.setMatchJouee((equipe.getMatchJouee() + 1));
//
//                    ChangerChampEquipe(equipe,partie.getAway().getIDEquipe());
//                    ChangerChampEquipe(equipe,partie.getHome().getIDEquipe());
//                    equipeGagner=equipe;
//                }
//
//            }
//        } catch (SQLException ex) {
//        }
//        return equipeGagner;
//
//    }

    
    //A vérifierrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr
    
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
                      
                  
                    equipeGagner=equipe;
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
                    equipeGagner.setButEncaisse((equipeGagner.getButEncaisse() + butAway));
                    equipeGagner.setMatchJouee((equipeGagner.getMatchJouee() + 1));
                    equipeGagner.setMatchGagne((equipeGagner.getMatchGagne() + 1));
                    equipeGagner.setNombrePoints((equipeGagner.getNombrePoints() + 3));
                    ChangerChampEquipe(equipeGagner);
                    equipePerdu.setButMarque((equipePerdu.getButMarque() + butAway));
                    equipePerdu.setButEncaisse((equipePerdu.getButEncaisse() + butHome));
                    equipePerdu.setMatchJouee(equipePerdu.getMatchJouee() + 1);
                    equipePerdu.setMatchperdu(equipePerdu.getMatchperdu() + 1);
                    ChangerChampEquipe(equipePerdu);

                } else if (butHome < butAway) {
                    equipeGagner = partie.getAway();
                    equipePerdu = partie.getHome();

                    equipeGagner.setButMarque((equipeGagner.getButMarque() + butAway));
                    equipeGagner.setButEncaisse((equipeGagner.getButEncaisse() + butHome));
                    equipeGagner.setMatchJouee((equipeGagner.getMatchJouee() + 1));
                    equipeGagner.setMatchGagne((equipeGagner.getMatchGagne() + 1));
                    equipeGagner.setNombrePoints((equipeGagner.getNombrePoints() + 3));
                    ChangerChampEquipe(equipeGagner);
                    equipePerdu.setButMarque((equipePerdu.getButMarque() + butHome));
                    equipePerdu.setButEncaisse((equipePerdu.getButEncaisse() + butAway));
                    equipePerdu.setMatchJouee(equipePerdu.getMatchJouee() + 1);
                    equipePerdu.setMatchperdu(equipePerdu.getMatchperdu() + 1);
                    ChangerChampEquipe(equipePerdu);
                } else {
                      
                    equipe.setButEncaisse((equipe.getButEncaisse() + butHome));
                    equipe.setButMarque((equipe.getButMarque() + butAway));
                    equipe.setNombrePoints((equipe.getNombrePoints() + 1));
                    equipe.setMatchNull((equipe.getMatchNull() + 1));
                    equipe.setMatchJouee((equipe.getMatchJouee() + 1));

                    ChangerChampEquipe(equipe,partie.getAway().getIDEquipe());
                    ChangerChampEquipe(equipe,partie.getHome().getIDEquipe());
                    equipeGagner=equipe;
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
    
     public void ChangerChampEquipe(Equipe equipe,int idEquipe) {

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
}
