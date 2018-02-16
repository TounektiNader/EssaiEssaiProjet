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
    public void ajoutResultat(int idPArtie);

    public void modifierResulltat(int idPartie, int butHome, int butAway);

    public List<Resultat> listResultat();

    public List<Resultat> listResultat(int idPartie);

    public ObservableList<Resultat> listResultats();

    public ObservableList<Resultat> listResultatsJoue();

    public ObservableList<Resultat> listResultatsNonJoue();
    public Equipe EquipeGagne(int idPartie);
}
