package Entity;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Partie {

    private int idMatch;
    private String group;
    private Date datematch;
    private String HeurePartie;
 
    private String tour ;
    private String etatMatch ;
    private String etiquette ;
    private EntiteStade stade ; 
    private Equipe home;
    private Equipe away;
    
    private String nomEquipeHome ;
    private String nomEquipeAway;
     private String nomStade;
 
    
    public Partie() {
    }

    public Partie(int idMatch, String group, Date datematch, String HeurePartie, String tour, String etatMatch, String etiquette, EntiteStade stade, Equipe home, Equipe away) {
        this.idMatch = idMatch;
        this.group = group;
        this.datematch = datematch;
        this.HeurePartie = HeurePartie;
        this.tour = tour;
        this.etatMatch = etatMatch;
        this.etiquette = etiquette;
        this.stade = stade;
        this.home = home;
        this.away = away;
    }
   
    public Partie(String group, Date datematch, String HeurePartie, String tour, String etatMatch, String etiquette, EntiteStade stade, Equipe home, Equipe away) {
        this.group = group;
        this.datematch = datematch;
        this.HeurePartie = HeurePartie;
        this.tour = tour;
        this.etatMatch = etatMatch;
        this.etiquette = etiquette;
        this.stade = stade;
        this.home = home;
        this.away = away;
    }
    
       public Partie( String HeurePartie, String tour,String etiquette, EntiteStade stade, Equipe home, Equipe away) {
        this.HeurePartie = HeurePartie;
        this.tour = tour;
        this.etiquette = etiquette;
        this.stade = stade;
        this.home = home;
        this.away = away;
    }
    
    
     
    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Date getDatematch() {
        return datematch;
    }

    public void setDatematch(Date datematch) {
        this.datematch = datematch;
    }

    public String getHeurePartie() {
        return HeurePartie;
    }

    public void setHeurePartie(String HeurePartie) {
        this.HeurePartie = HeurePartie;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getEtatMatch() {
        return etatMatch;
    }

    public void setEtatMatch(String etatMatch) {
        this.etatMatch = etatMatch;
    }

    public String getEtiquette() {
        return etiquette;
    }

    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }

    public EntiteStade getStade() {
        return stade;
    }

    public void setStade(EntiteStade stade) {
        this.stade = stade;
    }

    public Equipe getHome() {
        return home;
    }

    public void setHome(Equipe home) {
        this.home = home;
    }

    public Equipe getAway() {
        return away;
    }

    public void setAway(Equipe away) {
        this.away = away;
    }

    public String getNomEquipeHome() {
        return  this.home.getNomEquipe();
    }

    public void setNomEquipeHome() {
        this.nomEquipeHome = this.home.getNomEquipe();
    }

    public String getNomEquipeAway() {
        return this.away.getNomEquipe();
    }

    public void setNomEquipeAway() {
        this.nomEquipeAway = this.away.getNomEquipe();
    }

    public String getNomStade() {
        return this.stade.getNom();
    }

    public void setNomStade(){
        this.nomStade = this.stade.getNom();
    }

    
  

   
    
  
    
}
