/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package iService;

import Entity.User;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author hedih
 */
public interface iServicesUser {
    public void ajouterUser(User u);
    public void modifierUser(User u);
    public void SupprimerUser(String pseudo);
    public User afficherUser(String pseudo,String mdp);
    public User rechercheUser(String pseudo);
    public void validerCompte(String code,User u);
    public User getUser(String pseudo) ;
    public ObservableList<User> GetAdmin();
   
}
