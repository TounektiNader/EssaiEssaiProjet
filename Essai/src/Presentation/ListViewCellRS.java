/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Partie;
import Entity.Resultat;
import javafx.scene.control.ListCell;

/**
 *
 * @author Nader
 */
public class ListViewCellRS extends ListCell<Resultat>
{
     public ListViewCellRS()
     {
     }
    
   @Override
    public void updateItem(Resultat c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            ClassementItemController data = new ClassementItemController();
            if(c.getButHome()==100){
            data.setResultatMatchNonJouee(c);
             setGraphic(data.getBox());
            }else{
            data.setResultat(c);
            setGraphic(data.getBox());}
        }
    }    
}
