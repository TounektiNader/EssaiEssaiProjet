package Entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Bet {

    private int idBet;
    private int valeurr;
    private String etat;
    private User user;
    private Partie partie;

    public Bet() {
    }

    public Bet(int idBet, int valeurr, String etat, User user, Partie partie) {
        this.idBet = idBet;
        this.valeurr = valeurr;
        this.etat = etat;
        this.user = user;
        this.partie = partie;
    }

    public Bet(int valeurr, String etat, User user, Partie partie) {
        this.valeurr = valeurr;
        this.etat = etat;
        this.user = user;
        this.partie = partie;
    }

    public int getIdBet() {
        return idBet;
    }

    public void setIdBet(int idBet) {
        this.idBet = idBet;
    }

    public int getValeurr() {
        return valeurr;
    }

    public void setValeurr(int valeurr) {
        this.valeurr = valeurr;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }
    
   
}
