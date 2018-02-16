/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Actualite;
import Entity.User;
import Services.ActualiteService;
import Services.ServiceUser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author gaelfameni
 */
public class modifierActualiteController implements Initializable {

    @FXML
    private TextArea description;
    @FXML
    private Button modifier;
    @FXML
    private Button parcoure;
    @FXML
    private ImageView img;
    @FXML
    private TextField titre;
    @FXML
    private Label url;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) {
        
        ServiceUser serivce= new ServiceUser ();
         User u = serivce.getUser("titou");
        
         try {
             Actualite a = new Actualite();
             ActualiteService as = new ActualiteService();
            a.setTitre(titre.getText());
            a.setTexte(description.getText());
             a.setImage(url.getText());
              a.setUser(u);
            System.out.println(a);
            as.modifierActualite(a);
           
            System.out.println(as.getClass());
            
        Stage stage = (Stage) modifier.getScene().getWindow();
            stage.close();
            Parent root = FXMLLoader.load(getClass().getResource("actuuser.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add("css/stylesheet1.css");
            Stage   primaryStage = new Stage();
            primaryStage.setTitle("Actualite");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(modifierActualiteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void parcourir(ActionEvent event) {
        
         
        Image image = new Image("file:/D:/cours/3A12/semestre2/pidev/projet java/RussiaRussia/RussiaRussia/src/presentation");
        img.setImage(image);
        
        //Image image = new Image("file:\\"+fil.toString());
        img.setImage(image);
        
        
Stage s = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);
       File fil= fileChooser.showOpenDialog(s);
       Image imag = new Image(fil.toURI().toString());
        img.setImage(imag);
       url.setText(fil.toURI().toString());
      
    
        
        
        
    }
    
}
