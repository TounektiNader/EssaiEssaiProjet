/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import Entity.Equipe;
import Entity.Resultat;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author Nader
 */
public interface iResultat {

    //bech na7iha emba3ed 
    public void supprimerRsultat(int idPartie);

    public void ajoutResultat(int idPArtie);

    public void modifierResulltat(int idPartie, int butHome, int butAway);

    public List<Resultat> listResultat();

    public List<Resultat> listResultat(int idPartie);

    public ObservableList<Resultat> listResultats();

    public ObservableList<Resultat> listResultatsJoue();

    public ObservableList<Resultat> listResultatsNonJoue();

    public ObservableList<Resultat> listResultatsParGroupe(String groupe);

    public Equipe EquipeGagne(int idPartie);

    public int nombreMatchParGroupe(String groupe);

    public void affectionfinal();

    public void affectation2eme2();

    public void affectation2eme1();

    public int idPartie(String etiq);

    public void affectationGroupeGH8Eme();

    public void affectationGroupeEF8Eme();

    public void affectationGroupeCD8Eme();

    public void affectationGroupeAB18Eme();

    public void ChangerChampEquipe(Equipe equipe, int idEquipe, int nbPoints);

    public void ChangerChampEquipe(Equipe equipe);

    public void verficationChangement(int idPartie);

    public Equipe EquipePerdu(int idPartie);

    public void affectation4eme1();

    public void affectation4eme2();

    public void affectation4eme3();

    public void affectation4eme4();
}
