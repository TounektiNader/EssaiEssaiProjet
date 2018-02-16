/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import Services.ServiceRealTime;
import Services.ServiceUser;
import Utils.XML;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;




/**
 *
 * @author Nader
 */
public class AuthentificationController implements Initializable {
    private void Validation() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("Validé votre Compte");
        alert.showAndWait();
    }  
    private void Vide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("L'un des champs est vide");
        alert.showAndWait();
    }  
    @FXML
    private TextField pseudo;
    @FXML
    private TextField mdp;
    @FXML
    private void connexion(ActionEvent event) throws IOException {
      ServiceUser u= new ServiceUser();
      User s=new User();
      
      String login = pseudo.getText();
      String mot =mdp.getText();
      if(login.isEmpty() || mot.isEmpty())
      {
          Vide();
      }
      else
      {
      s=u.afficherUser(login,mot);

        if("true".equals(s.getStatus()))
        {
            if("Admin".equals(s.getRole()))
            {
                System.out.println(s.getRole());
            XML x= new XML();
        x.Ecrire(s.getUsername(),s.getNom(),s.getPrenom(),s.getMdp(),s.getMail(),s.getStatus(),s.getStatus(),s.getJeton(),s.getNationalite());
        Stage primarya= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene scene = new Scene(root);
        primarya.setScene(scene);
        primarya.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
            }
            else
            {
             XML x= new XML();
            x.Ecrire(s.getUsername(),s.getNom(),s.getPrenom(),s.getMdp(),s.getMail(),s.getStatus(),s.getStatus(),s.getJeton(),s.getNationalite());
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("AccueilUtilisateur.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();   
            }
        }
        
        else
        {
            Validation();
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
          
      
    }
    @FXML
    private void inscription(ActionEvent event) throws IOException
    {
       Stage SecondStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Incription.fxml"));
        Scene scene = new Scene(root);
        SecondStage.setScene(scene);
        SecondStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();   
    }
    @FXML
    private void oublier(ActionEvent event) throws IOException
    {
       Stage SecondStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("motdepasse.fxml"));
        Scene scene = new Scene(root);
        SecondStage.setScene(scene);
        SecondStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
