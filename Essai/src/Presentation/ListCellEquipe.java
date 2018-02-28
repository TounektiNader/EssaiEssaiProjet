/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Equipe;
import javafx.scene.control.ListCell;
import Presentation.EquipepropController;

/**
 *
 * @author Amouri Aziz
 */
public class ListCellEquipe extends ListCell<Equipe>{
    
    @Override
    public void updateItem(Equipe e,boolean empty){
        super.updateItem(e, empty);
        if(e!=null){
            EquipepropController data = new EquipepropController();
            data.setInfo(e);
            setGraphic(data.getBox());
        }
    }
    
}
