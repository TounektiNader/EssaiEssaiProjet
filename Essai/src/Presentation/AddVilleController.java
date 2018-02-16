/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteVille;
import Services.VilleService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class AddVilleController implements Initializable {

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
    private ImageView photoville;
    @FXML
    private ImageView photologo;
    @FXML
    private ImageView photoeq;
    @FXML
    private TextField nom;
    @FXML
    private TextField fondation;
    @FXML
    private Button valider;
    @FXML
    private Button close;
    @FXML
    private TextField population;
    @FXML
    private TextField time;
    @FXML
    private Button photo;
    @FXML
    private TextField equipe;
    @FXML
    private Button logo;
    @FXML
    private TextField coord;
    @FXML
    private Button logoequipe;
    @FXML
    private Label url;
    @FXML
    private Label url1;
    @FXML
    private Label url2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void Addville(ActionEvent event) throws SQLException, IOException 
    {
        VilleService cs = new VilleService();
        EntiteVille cafe = new EntiteVille();
        
        cafe.setNom(nom.getText());
        cafe.setFondation(fondation.getText());
        cafe.setPopulation(population.getText());
        cafe.setTimezone(time.getText());
        cafe.setCoordonnees(coord.getText());
        cafe.setEquipelocale(equipe.getText());
        cafe.setPhotoville(url.getText());
        cafe.setLogoville(url1.getText());
        cafe.setLogoequipe(url2.getText());
        
        try
        {
        cs.AddVille(cafe);
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ville ajoutée");
            alert.setHeaderText("Ville ajoutée avec succès");
            alert.setContentText("Ville "+nom.getText()+" ajoutée avec succès");
            alert.showAndWait();
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ville non ajoutée");
                alert.setHeaderText("Echec de l'insertion de la ville");
                alert.setContentText("Echec de l'insertion de la ville "+nom.getText());
                alert.showAndWait();
                }
        Close(event);
    }
    
    @FXML
    private void Browse()
    {
        Image image = new Image("file:/C:");
        photoville.setImage(image);
        
        Stage s = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Parcourir");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);
       File fil= fileChooser.showOpenDialog(s);
       Image imag = new Image(fil.toURI().toString());
        photoville.setImage(imag);
        url.setText(fil.toURI().toString());
    }
    
    @FXML
    private void Browse1()
    {
        Image image = new Image("file:/C:");
        photologo.setImage(image);
        
        Stage s = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Parcourir");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);
       File fil= fileChooser.showOpenDialog(s);
       Image imag = new Image(fil.toURI().toString());
        photologo.setImage(imag);
        url1.setText(fil.toURI().toString());
    }
    
    @FXML
    private void Browse2()
    {
        Image image = new Image("file:/C:");
        photoeq.setImage(image);
        
        Stage s = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Parcourir");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);
       File fil= fileChooser.showOpenDialog(s);
       Image imag = new Image(fil.toURI().toString());
        photoeq.setImage(imag);
        url2.setText(fil.toURI().toString());
    }

    @FXML
    private void Close(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeVilles.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
}
