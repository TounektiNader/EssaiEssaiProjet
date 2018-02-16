/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import Services.ServiceUser;
import Utils.mail;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hedih
 */
public class MotdepasseController implements Initializable {
  
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField pseudo;
    @FXML
    private void oublie(ActionEvent event) throws IOException
    {
        ServiceUser u= new ServiceUser();
        User U;
        U=u.rechercheUser(pseudo.getText());
        mail m=new mail();
        
        m.send(U.getMail(),"Mot de passe",U.getMdp());
        Stage SecondStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        SecondStage.setScene(scene);
        SecondStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();   
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
