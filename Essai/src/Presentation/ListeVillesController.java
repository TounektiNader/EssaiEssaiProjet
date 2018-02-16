/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteVille;
import Services.VilleService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class ListeVillesController implements Initializable {

    @FXML
    private Button bt_menu;
    @FXML
    private Button hotels;
    @FXML
    private Button cafes;
    @FXML
    private Button restos;
    @FXML
    private Button stades;
    @FXML
    private Button villes;
    @FXML
    private VBox mainContainer;
    @FXML
    private TableView<EntiteVille> table_ville;
    @FXML
    private TableColumn<EntiteVille, String> nom;
    @FXML
    private TableColumn<EntiteVille, String> fondation;
    @FXML
    private TableColumn<EntiteVille, String> population;
    @FXML
    private TableColumn<EntiteVille, String> time;
    @FXML
    private TableColumn<EntiteVille, String> equipe;
    @FXML
    private TableColumn<EntiteVille, String> coord;
    @FXML
    private Label wcount;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
       try 
        {
            VilleService cs = new VilleService();
        List<EntiteVille> lsvilles = cs.listVilles();
        ObservableList<EntiteVille> lc = FXCollections.observableArrayList();
        lsvilles.stream().forEach((j)->{
            lc.add(j);
        });
        table_ville.setItems(lc);
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        fondation.setCellValueFactory(new PropertyValueFactory<>("Fondation"));
        population.setCellValueFactory(new PropertyValueFactory<>("Population"));
        time.setCellValueFactory(new PropertyValueFactory<>("Timezone"));
        equipe.setCellValueFactory(new PropertyValueFactory<>("Equipelocale"));
        coord.setCellValueFactory(new PropertyValueFactory<>("Coordonnees"));
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }
    }    

    @FXML
    private void menu(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void hotels(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeHotels.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void cafes(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeCafes.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void restos(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeRestos.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void stades(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeStades.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void villes(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeVilles.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void Add(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("AddVille.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void Edit(ActionEvent event) {
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException 
    {
        int selectedIndex = table_ville.getSelectionModel().getSelectedIndex();
         EntiteVille selectedPerson = table_ville.getSelectionModel().getSelectedItem();
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         
        if (selectedIndex >= 0) {
            table_ville.getItems().remove(selectedIndex);
          VilleService cs = new VilleService();
            cs.DeleteVille(selectedPerson);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Nothing selected");
            alert.setHeaderText("No city selected");
            alert.setContentText("Please select a city from the table.");
            alert.showAndWait();
        }
    }
    
}
