/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Actualite;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author gaelfameni
 */
public class ActupropController implements Initializable {

    
    
    @FXML
    private Label titre;
    @FXML
    private Label description;
    @FXML
    private VBox vbox;
    @FXML
    private ImageView imge;

    public ActupropController() {
                
                
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("actuprop.fxml"));
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
    
     public void setInfo(Actualite string)
    {
        titre.setText(string.getTitre());
        titre.setMaxWidth(200);
        titre.setWrapText(true);
        description.setMaxWidth(200);
        description.setWrapText(true);
        
        description.setText(string.getTexte());
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
       Image image = new Image(string.getImage());
       System.out.println(string.getImage());
            imge.setImage(image);
//        date.setText(formattedString);
//
    }

    public VBox getVBox()
    {
        return vbox;
    }
}
