package iService;

import Entity.Bet;
import Entity.Partie;
import Entity.User;
import java.util.List;
import javafx.collections.ObservableList;

public interface iBet {

    public User getUser(String username);

    public void modifierBet(String etat, String username, Partie partie);

    public List<Bet> chercherBet(int idPartie);

    public ObservableList<Bet> listBet(String username);

    public List<Bet> listBet();

    public int getNombreJeton(String userName);

    public void deminuerJeton(String username);

    public void AugmenterJeton(String username);

    public int getIdPartiee(int idEquipe1, int idEquipe2);

    public int getIdEquipe(String nomEquipe);

    public Boolean jouerUnMatch(String username, int idPartie);

    public void updateEtatBetGain(int idPartie);

    public void updateEtatBetPerte(int idPartie);

    public Boolean comparaisonValeurResultat(String usernaeme, int idPartie);

    public void updateJetonEtat(int idPartie);

    public void parier(int idPartie, String username, String choix);

    public ObservableList<Bet> listBetPerte(String username);

    public ObservableList<Bet> listBetGain(String username);

    public int NombreTotalBet();

    public int NombreBetPerte();

    public int NombreBetGain();

    public int NombreBetTraite();
    
    public List<Bet> listBetParPersonne();
     public int nombreBetPerte(String username);
     public int nombreBetGain(String username);
}
