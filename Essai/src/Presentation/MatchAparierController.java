/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Bet;

import Entity.Partie;

import Services.PartieService;
import Services.ServiceBet;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class MatchAparierController implements Initializable {

    private Partie selectedPartie;
    @FXML
    private RadioButton home;
    @FXML
    private RadioButton away;
    private RadioButton nulll;
    private ToggleGroup toggle;
    @FXML
    private Label labes;
    @FXML
    private RadioButton eg;

    public String choix;

    public static int idPArtie ;
    public void initData(Partie partie) {
        selectedPartie = partie;
       idPArtie=partie.getIdMatch();
        
        PartieService partieService = new PartieService();
//        home.setText(partieService.getNomEquipe(selectedPartie.getHome()));//a changer apres nom 
//        away.setText(partieService.getNomEquipe(selectedPartie.getAway()));

        home.setText(selectedPartie.getHome().getNomEquipe());//a changer apres nom 
        away.setText(selectedPartie.getAway().getNomEquipe());

        // photo.setImage(selectedPerson.getImage());
    }

    @FXML
    public void changedRadio() {
        if (this.toggle.getSelectedToggle().equals(this.home)) {
            labes.setText(home.getText());
            choix = home.getText();
        } else if (this.toggle.getSelectedToggle().equals(this.away)) {
            labes.setText(away.getText());
            away.getText();
        } else if (this.toggle.getSelectedToggle().equals(this.eg)) {
            labes.setText(eg.getText());
            eg.getText();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        labes.setText("");
        toggle = new ToggleGroup();
        this.home.setToggleGroup(toggle);
        this.away.setToggleGroup(toggle);
        this.eg.setToggleGroup(toggle);

    }

    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
//        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/Presentation/FXMLDocument.fxml"));
//        Scene tableViewScene = new Scene(tableViewParent);
//
//        //This line gets the Stage information
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        
//        window.setScene(tableViewScene);
//        window.show();

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void parier(ActionEvent event) {
        ServiceBet service = new ServiceBet();
        //betService.parier(home.getText(), away.getText(),"nader", choix);
      //  servicee.parier("Brazil", "France", "nader", "France");
      //  servicee.parier(home.getText(), away.getText(), "titou", choix);
        
        service.parier(idPArtie, "titou", choix);
         final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
