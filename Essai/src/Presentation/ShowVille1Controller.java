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
import static Presentation.AddCafeController.rootP;
import Services.CafeService;
import Services.HotelService;
import Services.RestoService;
import Services.StadeService;
import Services.VilleService;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import static javax.swing.Spring.height;
import static javax.swing.Spring.width;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class ShowVille1Controller implements Initializable {

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
    @FXML
    private AnchorPane root;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    
    public static AnchorPane rootP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        rootP = root;

        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
            }
        });
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
    
    EntiteVille v = new EntiteVille();

    @FXML
    private void Localiser(ActionEvent event) throws IOException 
    {
        try 
        {
            FXMLLoader homePage = new FXMLLoader();
            homePage.setLocation(getClass().getResource("Ville1Loc.fxml"));
            Parent root = (Parent) homePage.load();
            
            Ville1LocController controller = homePage.getController();
            System.out.println(controller.toString());
            
            controller.initialize(v);            
            Scene homePage_scene = new Scene(root);
            Stage s = new Stage();
            s.setScene(homePage_scene);
            s.show();
             
        }
        catch (IOException ex) 
        {
            Logger.getLogger(ShowVille1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setVille(EntiteVille object) {
        v= object;
        javafx.scene.image.Image im = new javafx.scene.image.Image(object.getPhotoville());
        javafx.scene.image.Image im1 = new javafx.scene.image.Image(object.getLogoville());
        javafx.scene.image.Image im2 = new javafx.scene.image.Image(object.getLogoequipe());
        imgville.setImage(im);
        logoville.setImage(im1);
        logoequipe.setImage(im2);
        city_name.setText(object.getNom());
        fondation.setText(object.getFondation());
        population.setText(object.getPopulation());
        time.setText(object.getTimezone());
        equipe.setText(object.getEquipelocale());
        String path = object.getPhotoville();
       
        try 
        {
        CafeService cs = new CafeService();
        List<EntiteCafe> lscafes = cs.FindCafeVille(object.getNom());
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
        List<EntiteHotel> lshotels = cs.FindHotelVille(object.getNom());
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
        List<EntiteResto> lsrestos = cs.FindRestoVille(object.getNom());
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
        List<EntiteStade> lsstades = cs.FindStadeVille(object.getNom());
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
    private void cliked(MouseEvent event) throws IOException, SQLException 
    {
        EntiteCafe cafe = new EntiteCafe(); 
        cafe= table_cafes.getSelectionModel().getSelectedItem();
        
            FXMLLoader homePage = new FXMLLoader();
            homePage.setLocation(getClass().getResource("Show_Cafe.fxml"));
            Parent root = (Parent) homePage.load();
            Show_CafeController showControlelr = homePage.getController();
            System.out.println(showControlelr.toString());
            showControlelr.setVille(cafe);            
            Scene homePage_scene = new Scene(root);
            Stage s = (Stage) (table_cafes.getScene().getWindow());
            s.setScene(homePage_scene);
            s.show();
    }
    
    @FXML
    private void cliked1(MouseEvent event) throws IOException, SQLException 
    {
        EntiteHotel cafe = new EntiteHotel(); 
        cafe= table_hotels.getSelectionModel().getSelectedItem();
        
            FXMLLoader homePage = new FXMLLoader();
            homePage.setLocation(getClass().getResource("Show_Hotel.fxml"));
            Parent root = (Parent) homePage.load();
            Show_HotelController showControlelr = homePage.getController();
            System.out.println(showControlelr.toString());
            showControlelr.setVille(cafe);            
            Scene homePage_scene = new Scene(root);
            Stage s = (Stage) (table_hotels.getScene().getWindow());
            s.setScene(homePage_scene);
            s.show();
    }
    
    @FXML
    private void cliked2(MouseEvent event) throws IOException, SQLException 
    {
        EntiteResto cafe = new EntiteResto(); 
        cafe= table_restos.getSelectionModel().getSelectedItem();
        
            FXMLLoader homePage = new FXMLLoader();
            homePage.setLocation(getClass().getResource("Show_Resto.fxml"));
            Parent root = (Parent) homePage.load();
            Show_RestoController showControlelr = homePage.getController();
            System.out.println(showControlelr.toString());
            showControlelr.setVille(cafe);            
            Scene homePage_scene = new Scene(root);
            Stage s = (Stage) (table_restos.getScene().getWindow());
            s.setScene(homePage_scene);
            s.show();
    }
    
    @FXML
    private void cliked3(MouseEvent event) throws IOException, SQLException 
    {
        EntiteStade cafe = new EntiteStade(); 
        cafe= table_stades.getSelectionModel().getSelectedItem();
        
            FXMLLoader homePage = new FXMLLoader();
            homePage.setLocation(getClass().getResource("Show_Stade.fxml"));
            Parent root = (Parent) homePage.load();
            Show_StadeController showControlelr = homePage.getController();
            System.out.println(showControlelr.toString());
            showControlelr.setVille(cafe);            
            Scene homePage_scene = new Scene(root);
            Stage s = (Stage) (table_restos.getScene().getWindow());
            s.setScene(homePage_scene);
            s.show();
    }

}
