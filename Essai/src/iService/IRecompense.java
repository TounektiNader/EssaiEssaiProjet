/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import Entity.Recompense;
import Entity.User;
import java.util.List;

/**
 *
 * @author Nader
 */
public interface IRecompense {
     public User getUser(String username);
      public List<Recompense> listRecompense();
       public List<Recompense> listRecompense(String username);
       public void ajouterRecompense(String username,String typeRecomense);
        public int NombreMesCadeau(String username);
    
}
