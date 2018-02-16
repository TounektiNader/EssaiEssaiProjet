/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteStade;
import Services.StadeService;
import Services.VilleService;
import java.io.File;
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
import javafx.scene.control.ComboBox;
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
public class AddStadeController implements Initializable {

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
    private ImageView ph;
    @FXML
    private TextField nom;
    @FXML
    private TextField fondation;
    @FXML
    private Button valider;
    @FXML
    private Button close;
    @FXML
    private TextField capacite;
    @FXML
    private TextField equipe;
    @FXML
    private Button photo;
    @FXML
    private TextField coord;
    @FXML
    private Label url;
    @FXML
    private ComboBox<String> combo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
        VilleService cs = new VilleService();
        List<String> lscafes = cs.ReturnNames();
        ObservableList<String> lc = FXCollections.observableArrayList();
        lscafes.stream().forEach((j)->{
            lc.add(j);
        });
        combo.setItems(lc);
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
    private void Addstade(ActionEvent event) throws IOException, SQLException 
    {
        StadeService cs = new StadeService();
        VilleService vs = new VilleService();
        EntiteStade cafe = new EntiteStade();
        int i = vs.ReturnId(combo.getValue());
        System.out.println(i);
        cafe.setNom(nom.getText());
        cafe.setFondation(fondation.getText());
        cafe.setPosition(coord.getText());
        cafe.setCapacite(capacite.getText());
        cafe.setEquipelocale(equipe.getText());
        cafe.setPhoto(url.getText());
        
        cafe.getStadeVille().setIdville(i);
        
        try
        {
        cs.AddSatde(cafe);
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Stade ajouté");
            alert.setHeaderText("Stade ajouté avec succès");
            alert.setContentText("Stade "+nom.getText()+" ajouté avec succès");
            alert.showAndWait();
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Stade non ajouté");
                alert.setHeaderText("Echec de l'insertion du stade");
                alert.setContentText("Echec de l'insertion du stade "+nom.getText());
                alert.showAndWait();
                }
        Close(event);
    }
    
    @FXML
    private void Browse()
    {
        Image image = new Image("file:/C:");
        ph.setImage(image);
        
        Stage s = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Parcourir");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
    new FileChooser.ExtensionFilter("PNG", "*.png")
);
       File fil= fileChooser.showOpenDialog(s);
       Image imag = new Image(fil.toURI().toString());
        ph.setImage(imag);
        url.setText(fil.toURI().toString());
    }

    @FXML
    private void Close(ActionEvent event) throws IOException
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeStades.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
}
