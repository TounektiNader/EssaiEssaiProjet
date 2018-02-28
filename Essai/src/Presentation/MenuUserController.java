/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteVille;
import static Presentation.AddCafeController.rootP;
import Services.VilleService;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class MenuUserController implements Initializable {
    
    @FXML
    private ListView<EntiteVille> ListView;
    @FXML
    private Label title;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    
    public static AnchorPane rootP;
    @FXML
    private MediaView pag;
    
    private static final String MEDIA_URL = "/img/vid.mp4";
    
    private MediaPlayer MediaPlayer;
    @FXML
    private ProgressBar prgrs;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        System.out.println(url.toString());
        System.out.println(this.getClass().getResource(MEDIA_URL).toExternalForm());
        MediaPlayer = new MediaPlayer(new Media(this.getClass().getResource(MEDIA_URL).toExternalForm()));
        MediaPlayer.setAutoPlay(true);
        pag.setMediaPlayer(MediaPlayer);
        pag.setFitWidth(700);
        pag.setFitHeight(500);
        
        
        rootP = root;

        try {
            VBox box = FXMLLoader.load(getClass().getResource("/Presentation/SidePanelContent.fxml"));
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
        
        try 
        {
        VilleService cs = new VilleService();
        List<EntiteVille> lscafes = cs.listVilles();
        ObservableList<EntiteVille> lc = FXCollections.observableArrayList();
        lscafes.stream().forEach((j)->{
            lc.add(j);
        });
        ListView.setItems(lc);
        ListView.setCellFactory(new Callback<ListView<EntiteVille>, ListCell<EntiteVille>>() {
            @Override
            public ListCell<EntiteVille> call(ListView<EntiteVille> param) {
                return  new ListCellVille();
            }
        });
        ListView.getSelectionModel().selectedItemProperty().addListener((ObservableList,oldvalue,newvalue)->{

                showVilleDetails(newvalue);

        });
        

        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }

    }    

    private void Showville1(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("/Presentation/ShowVille1.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    private void showVilleDetails(EntiteVille object){
        
        System.out.println(object.toString());
        try {
            FXMLLoader homePage = new FXMLLoader();;
            homePage.setLocation(getClass().getResource("/Presentation/ShowVille1.fxml"));
            Parent root = (Parent) homePage.load();
            
              ShowVille1Controller controller = homePage.getController();
            System.out.println(controller.toString());
            
            controller.setVille(object);
            
             Scene homePage_scene = new Scene(root);
            // Stage stage = new Stage();
            // stage.setScene(homePage_scene);
            // stage.show();
             Stage s = (Stage) ( ListView.getScene().getWindow());
             s.setScene(homePage_scene);
             s.show();
             
        } catch (IOException ex) {
            Logger.getLogger(MenuUserController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

}


