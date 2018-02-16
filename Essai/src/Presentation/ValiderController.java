package Presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Entity.User;
import Services.ServiceUser;
import Utils.XML;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author hedih
 */
public class ValiderController implements Initializable {
     @FXML
     private TextField code;
    /**
     * Initializes the controller class.
     */
    @FXML
     private void Valider(ActionEvent event) throws SAXException, IOException{
         ServiceUser s= new ServiceUser();
         XML x=new XML();      
         User u;
         
         u=x.lire();
         System.out.println(u.getNom());
         s.validerCompte(code.getText(),u);
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
     }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
     }
     
    

