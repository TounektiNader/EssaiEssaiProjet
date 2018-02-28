/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Actualite;
import java.awt.List;
import javafx.scene.control.ListCell;

/**
 *
 * @author gaelfameni
 */
public class ListCellActualite extends ListCell<Actualite>{
   @Override
    public void updateItem(Actualite a,boolean empty){
        super.updateItem(a, empty);
        if(a!=null){
            ActupropController data = new ActupropController();
            
            data.setInfo(a);
            setGraphic(data.getVBox());
        }
    }
    
}
