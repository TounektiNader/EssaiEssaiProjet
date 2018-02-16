/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import java.util.List;
import Entity.Joueurs;

/**
 *
 * @author Amouri Aziz
 */
public interface IJoueur {
   public void ajouterJoueur(Joueurs J);
   public void supprimerJoueur(Joueurs J);
   public void ModifierJoueur(Joueurs J);
   public  List<Joueurs> affichersJoueurs();
       public List<Joueurs> affichersJoueurs(int IdEquipe);
    public Joueurs affichersJoueur(int Id);
}
