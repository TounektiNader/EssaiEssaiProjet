/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import Entity.Actualite;
import java.util.List;

/**
 *
 * @author gaelfameni
 */
public interface iActualite {
    
    public void ajouterActualite(Actualite a);
    public void supprimerActualite(Actualite a);
    public void modifierActualite(Actualite a);
    public Actualite recherheActualite(String titre);
    
     List<Actualite> lireActualite();
   
    
}
