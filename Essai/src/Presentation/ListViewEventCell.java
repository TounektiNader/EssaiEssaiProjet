/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import Entity.Partie;
import javafx.scene.control.ListCell;

/**
 *
 * @author user
 */
class ListViewEventCell extends ListCell<Partie> {

    public ListViewEventCell() {
    }
    
    @Override
    public void updateItem(Partie c, boolean empty)
    {
        super.updateItem(c,empty);
        if(c != null)
        {
            PartieItemController data = new PartieItemController();
            data.setPartie(c);
            setGraphic(data.getBox());
        }
    } 
    
}
