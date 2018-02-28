/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteVille;
import javafx.scene.control.ListCell;

/**
 *
 * @author Ouss'Hr
 */
public class ListCellVille extends  ListCell<EntiteVille>{
    
         @Override
    public void updateItem(EntiteVille p, boolean empty)
    {
        super.updateItem(p,empty);
        if(p != null)
        {
            ListVilleItemController data = new ListVilleItemController();
            data.setVille(p);
            setGraphic(data.getBox());
        }
    }

    
}
