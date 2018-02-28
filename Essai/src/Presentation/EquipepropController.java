/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import Entity.Equipe;
import java.io.IOException;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Amouri Aziz
 */
public class EquipepropController implements Initializable {
    @FXML
    private ImageView Drapeau;
    @FXML
    private Label NEquipe;
    @FXML
    private Label group;
    @FXML
    private Label trainer;
    @FXML
    private HBox hbox;
    @FXML
    private Label Continant;
    
    @FXML 
    private ImageView imageDrapeau ; 
    
    
        public EquipepropController() {
                
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Equipeprop.fxml"));
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    

    
    
      public void setInfo(Equipe e)
    {
        NEquipe.setText(e.getNomEquipe());
        group.setText(e.getGroupe());
        trainer.setText(e.getEntraineur());
        Continant.setText(e.getContinent());
        Image image = new Image(e.getDrapeau());
        imageDrapeau.setImage(image);
        
                 
     /*Image image = new Image(e.getDrapeau());
       System.out.println(e.getDrapeau());
            Drapeau.setImage(image);*/
//        date.setText(formattedString);
//
    }

    public HBox getBox()
    {
        return hbox;
    }
}
