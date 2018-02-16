/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import Entity.Equipe;
import Entity.Joueurs;
import java.util.List;
import javafx.collections.ObservableList;
/**
 *
 * @author elbrh
 */
public interface IEquipe {
   public void ajouterEquipe(Equipe E );
   public void supprimerEquipe(Equipe E);
   public void ModifierEquipe(Equipe E);
   public void afficherEquipe(Equipe E);
   public ObservableList <Equipe> afficherEquipes();
   public int rechercheEquipe(String equipe);

    
    
    
    
}
