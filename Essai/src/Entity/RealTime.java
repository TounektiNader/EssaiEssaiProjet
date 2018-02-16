/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;
import java.util.List;
import  Entity.Partie;

/**
 *
 * @author hedih
 */
public class RealTime {
    
private int idReal;
private String actions;
private String temps;
private Partie partie ; 
private Joueurs joueur ;

public RealTime(){}

    public RealTime(int idReal, String actions, String temps, Partie partie, Joueurs joueur) {
        this.idReal = idReal;
        this.actions = actions;
        this.temps = temps;
        this.partie = partie;
        this.joueur = joueur;
    }

    public RealTime(String actions, String temps, Partie partie, Joueurs joueur) {
        this.actions = actions;
        this.temps = temps;
        this.partie = partie;
        this.joueur = joueur;
    }

    public int getIdReal() {
        return idReal;
    }

    public void setIdReal(int idReal) {
        this.idReal = idReal;
    }

    public String getActions() {
        return actions;
    }

    public void setActions(String actions) {
        this.actions = actions;
    }

    public String getTemps() {
        return temps;
    }

    public void setTemps(String temps) {
        this.temps = temps;
    }

    public Partie getPartie() {
        return partie;
    }

    public void setPartie(Partie partie) {
        this.partie = partie;
    }

    public Joueurs getJoueur() {
        return joueur;
    }

    public void setJoueur(Joueurs joueur) {
        this.joueur = joueur;
    }



    
}
