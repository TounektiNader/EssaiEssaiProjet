/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteCafe;
import Entity.EntiteHotel;
import Entity.EntiteResto;
import Entity.EntiteStade;
import Entity.EntiteVille;
import Services.CafeService;
import Services.HotelService;
import Services.RestoService;
import Services.StadeService;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class ShowVille5Controller implements Initializable {

    @FXML
    private Label city_name;
    @FXML
    private ImageView logoequipe;
    @FXML
    private ImageView logoville;
    @FXML
    private Button bt_menu;
    @FXML
    private VBox mainContainer;
    @FXML
    private ImageView imgville;
    @FXML
    private Label fondation;
    @FXML
    private Label population;
    @FXML
    private Label time;
    @FXML
    private Label equipe;
    @FXML
    private Button local;
    @FXML
    private TableView<EntiteHotel> table_hotels;
    @FXML
    private TableColumn<EntiteHotel, String> nomh;
    @FXML
    private TableColumn<EntiteHotel, String> detailsh;
    @FXML
    private TableColumn<EntiteHotel, String> coordh;
    @FXML
    private TableView<EntiteCafe> table_cafes;
    @FXML
    private TableColumn<EntiteCafe, String> nomc;
    @FXML
    private TableColumn<EntiteCafe, String> detailsc;
    @FXML
    private TableColumn<EntiteCafe, String> coordc;
    @FXML
    private TableView<EntiteResto> table_restos;
    @FXML
    private TableColumn<EntiteResto, String> nomr;
    @FXML
    private TableColumn<EntiteResto, String> detailsr;
    @FXML
    private TableColumn<EntiteResto, String> coordr;
    @FXML
    private TableView<EntiteStade> table_stades;
    @FXML
    private TableColumn<EntiteStade, String> noms;
    @FXML
    private TableColumn<EntiteStade, String> fondations;
    @FXML
    private TableColumn<EntiteStade, String> capacites;
    @FXML
    private TableColumn<EntiteStade, String> equipes;
    @FXML
    private TableColumn<EntiteStade, String> coords;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
        VilleService cs = new VilleService();
        EntiteVille lsvilles = cs.FindVille("Nijni Novgorod");
        
        
        city_name.setText(lsvilles.getNom());
        fondation.setText(lsvilles.getFondation());
        population.setText(lsvilles.getPopulation());
        time.setText(lsvilles.getTimezone());
        equipe.setText(lsvilles.getEquipelocale());
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }
        
        try 
        {
        CafeService cs = new CafeService();
        List<EntiteCafe> lscafes = cs.FindCafeVille("Nijni Novgorod");
        ObservableList<EntiteCafe> lc = FXCollections.observableArrayList();
        lscafes.stream().forEach((j)->{
            lc.add(j);
        });
        table_cafes.setItems(lc);
        nomc.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        detailsc.setCellValueFactory(new PropertyValueFactory<>("Details"));
        coordc.setCellValueFactory(new PropertyValueFactory<>("Position"));
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }
        
        try 
        {
        HotelService cs = new HotelService();
        List<EntiteHotel> lshotels = cs.FindHotelVille("Nijni Novgorod");
        ObservableList<EntiteHotel> lc = FXCollections.observableArrayList();
        lshotels.stream().forEach((j)->{
            lc.add(j);
        });
        table_hotels.setItems(lc);
        nomh.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        detailsh.setCellValueFactory(new PropertyValueFactory<>("Details"));
        coordh.setCellValueFactory(new PropertyValueFactory<>("Position"));
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }
        
        try 
        {
        RestoService cs = new RestoService();
        List<EntiteResto> lsrestos = cs.FindRestoVille("Nijni Novgorod");
        ObservableList<EntiteResto> lc = FXCollections.observableArrayList();
        lsrestos.stream().forEach((j)->{
            lc.add(j);
        });
        table_restos.setItems(lc);
        nomr.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        detailsr.setCellValueFactory(new PropertyValueFactory<>("Details"));
        coordr.setCellValueFactory(new PropertyValueFactory<>("Position"));
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }
        
        try 
        {
            StadeService cs = new StadeService();
        List<EntiteStade> lsstades = cs.FindStadeVille("Iekaterinbourg");
        ObservableList<EntiteStade> lc = FXCollections.observableArrayList();
        lsstades.stream().forEach((j)->{
            lc.add(j);
        });
        table_stades.setItems(lc);
        noms.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        fondations.setCellValueFactory(new PropertyValueFactory<>("Fondation"));
        capacites.setCellValueFactory(new PropertyValueFactory<>("Capacite"));
        equipes.setCellValueFactory(new PropertyValueFactory<>("Equipelocale"));
        coords.setCellValueFactory(new PropertyValueFactory<>("Position"));
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }
    }    

    @FXML
    private void menu(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("MenuUser.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    @FXML
    private void Localiser(ActionEvent event) {
    }
    
}
