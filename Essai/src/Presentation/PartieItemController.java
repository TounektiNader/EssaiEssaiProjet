/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import Entity.Partie;
import Services.PartieService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author user
 */
public class PartieItemController {

    @FXML
    private HBox Hbox;
    @FXML
    private HBox hbname;
    @FXML
    private Label tour;
    @FXML
    private HBox hbetat;
    @FXML
    private Label date;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton away;
    @FXML
     private VBox VBox;

        @FXML
     private ImageView imgHome;

            @FXML
     private ImageView imgAway ; 
            

        public PartieItemController() 
     {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/PartieItem.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
          //  throw new RuntimeException(e);
        }
    }
    /**
     * Initializes the controller class.
     */

        public void initialize() {
        // TODO
        }    
        
        
        Partie partie = new Partie();
       
    
    public void setPartie(Partie partiee)
    {
        this.partie = partiee ;
        if(partie != null)
        {
         tour.setText(partie.getTour());
         date.setText(partie.getDatematch().toString());
         home.setText(partie.getHome().getNomEquipe());
         away.setText(partie.getAway().getNomEquipe());      
         
         Image imHome = new Image(partie.getHome().getDrapeau());
         Image imAway = new Image(partie.getAway().getDrapeau());
         imgAway.setImage(imAway);
         
        imgHome.setImage(imHome);
         }
    }

//    public HBox getBox()
//    {
//        return Hbox;
//    }
//    
    
       public VBox getBox()
       {
           return VBox;
       }
 
}
