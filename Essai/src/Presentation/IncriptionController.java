/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hedih
 */
public class IncriptionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
   @FXML
   private TextField nom;
   @FXML 
   private TextField prenom;
   @FXML
   private TextField pseudo;
   @FXML
   private TextField mdp;
   @FXML
   private ChoiceBox<String> role;
   @FXML
   private ComboBox<String> nationalite;
   @FXML
   private TextField email;
   
   @FXML
   public void inscription(ActionEvent event) throws IOException
   {
      
    User u = new User(nom.getText(),prenom.getText(),pseudo.getText(),mdp.getText(),role.getSelectionModel().getSelectedItem(),email.getText(),"false",20,nationalite.getSelectionModel().getSelectedItem());
    ServiceUser SU = new ServiceUser();
    SU.ajouterUser(u);
    XML x= new XML();
    x.Ecrire(pseudo.getText(), nom.getText(),prenom.getText(),mdp.getText(),role.getSelectionModel().getSelectedItem(),email.getText(),"false",20,nationalite.getSelectionModel().getSelectedItem());
    if(role.getSelectionModel().getSelectedItem().equals("Admin"))
    {
        System.out.println("admin");
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Authentification.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close(); 
    }
    else if(role.getSelectionModel().getSelectedItem().equals("Utilisateur"))
    {
       Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Valider.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();  
    }
   
   }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         role.getItems().add("Utilisateur");
         role.getItems().add("Admin");
         nationalite.getItems().add("Tunisie");
         nationalite.getItems().add("France");
    }    
    
}
