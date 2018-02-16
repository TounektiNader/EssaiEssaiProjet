/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import DateStroge.MyConnection;
import Entity.Actualite;
import Services.ActualiteService;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Entity;

/**
 * FXML Controller class
 *
 * @author gaelfameni
 */
public class listActualitesController implements Initializable {

    @FXML
    private VBox mainContainer;
    @FXML
    private TableView<Actualite> table_actualite;
    @FXML
    private TableColumn<Actualite, String> titre;
    @FXML
    private TableColumn<Actualite, String> img;
    @FXML
    private TableColumn<Actualite, String> description;
    @FXML
    private Button supprimer;
    @FXML
    private Button bt_modifier;
    @FXML
    private Button bt_nouveau;
    @FXML
    private Button retour;
    ActualiteService as = new ActualiteService();
    ObservableList<Actualite> data;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<Actualite> la = as.lireActualite();
        data = FXCollections.observableArrayList();
        System.out.println("ggiiiirrrrr");

        la.stream().forEach((j) -> {
            data.add(j);
        });
       table_actualite.setItems(data);
        titre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        img.setCellValueFactory( new PropertyValueFactory<>("Images"));
        description.setCellValueFactory(new PropertyValueFactory<>("texte"));
    }    

    @FXML
    private void delete(ActionEvent event) {
   
        int selectedIndex = table_actualite.getSelectionModel().getSelectedIndex();
         Actualite selectedPerson = table_actualite.getSelectionModel().getSelectedItem();
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         
        if (selectedIndex >= 0) {
            table_actualite.getItems().remove(selectedIndex);
          ActualiteService as = new ActualiteService();
            as.supprimerActualite(selectedPerson);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nothing selected");
            alert.setHeaderText("No news selected");
            alert.setContentText("Please select a news from the table.");
            alert.showAndWait();
        }
    }
        
    

    @FXML
    private void update(ActionEvent event) {
      
        
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
        
          Parent homePage = FXMLLoader.load(getClass().getResource("MenuUser.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
          Parent homePage = FXMLLoader.load(getClass().getResource("modifierActualite.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    }
    

