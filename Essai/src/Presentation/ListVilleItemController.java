/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteVille;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class ListVilleItemController {

        @FXML
    private HBox Hbox;
    @FXML
    private Label nom;
    @FXML
    private ImageView img;

    public ListVilleItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListVilleItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
    /**
     * Initializes the controller class.
     */
    public void initialize() {
        // TODO
    }    
    
    
    public void setVille(EntiteVille v){
        if(v != null ){
            nom.setText(v.getNom());
            img.setImage(new Image(v.getLogoville()));
        }
    }
    
        public HBox getBox()
    {
        return Hbox;
    }
}
