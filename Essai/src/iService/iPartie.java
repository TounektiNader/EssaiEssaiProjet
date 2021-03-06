package iService;

import Entity.EntiteStade;
import Entity.Equipe;
import Entity.Partie;
import Entity.Stade;
import java.sql.Date;
import javafx.collections.ObservableList;

public interface iPartie {

    public String getNomEquipe(int id);

    public int getidEquipe(String nom);

    public Equipe getEquipe(int idEquipe);

    public EntiteStade getStade(int idStade);

    public ObservableList<Partie> getPartie();

    public void ajoutPartie(String groupe, String date, String heure, String tour, int idStade, int home, int away);

    public void supprimer(int idPartie);

    public int geIdHome(int idPartie);

    public int geIdAway(int idPartie);

    public ObservableList<Partie> partiegroupe(String groupe);

    public ObservableList<Partie> partieEquipe(int id);

    public Partie DetailsPartie(int idPartie);

    public void updateEtatPartie(int idPartie);

    public int getIdStade(String nomStade);

    public int geIdPartie(int idHome, int idAway);

    public ObservableList<Partie> partiegTour(String tour);

    public ObservableList<Partie> partiegNonJouee();

    public ObservableList<Partie> partiegJouee();

    public Equipe getEquipe(String nomEquipe);

    public void updatPartie(Partie partie, String d);

    public void insertPartie(Partie partie, String d);

    public ObservableList<Partie> getPartieAparier();

}
