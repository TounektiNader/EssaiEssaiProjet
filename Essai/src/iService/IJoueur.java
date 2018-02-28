/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import java.util.List;
import Entity.Joueurs;
import javafx.collections.ObservableList;

/**
 *
 * @author Amouri Aziz
 */
public interface IJoueur {
    
    public void supprimerJoueur() ;
    public void ajouterJoueur(Joueurs J,String path);
    

  

    public List<Joueurs> affichersJoueurs();

   // public List<Joueurs> affichersJoueurs(int IdEquipe);

  //  public Joueurs affichersJoueur(int Id);

    public ObservableList<Joueurs> getJouurs(int id);
}
