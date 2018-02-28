/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Equipe;
import Entity.Joueurs;
import Entity.User;
import Services.EquipeService;
import Services.JoueurService;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.xml.sax.SAXException;


/**
 * FXML Controller class
 *
 * @author Amouri Aziz
 */
public class ListJoueursController implements Initializable {
    @FXML
    private TableView<Joueurs> TabJoueur;
    
        ObservableList lisj;
    @FXML
    private TableColumn<Joueurs, Integer> idjou;
    @FXML
    private TableColumn<Joueurs, String> Namej;
    @FXML
    private TableColumn<Joueurs, String> prenomj;
    @FXML
    private TableColumn<Joueurs, String> posij;
   
    //@FXML
   // private Button Image;
    private TextField look;
    private JFXButton see;
   // @FXML
    //private Label url;
    @FXML
    private AnchorPane root;
    @FXML
    private Label url;
    @FXML
    private Pane paneE;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
     public static AnchorPane rootP;
    @FXML
    private JFXButton joindre;
    @FXML
    private JFXTextField username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

               XML x= new XML();
         
          User user = new User ();
        try {
            user = x.lire();
            username.setText(user.getUsername());
        } catch (SAXException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       ObservableList<Joueurs> lisj = FXCollections.observableArrayList();
         JoueurService js= new JoueurService();
         Equipe eq=new Equipe();
        // e.setNomEquipe(look.getText());
         //String valeur =look.getText();
         lisj= js.showme();
         idjou.setCellValueFactory(new PropertyValueFactory("Idjoueur"));
         Namej.setCellValueFactory(new PropertyValueFactory("NomJoueur"));
         prenomj.setCellValueFactory(new PropertyValueFactory("PrenomJoueur"));
         posij.setCellValueFactory(new PropertyValueFactory("Position"));
        
         
         TabJoueur.setItems(null);
         TabJoueur.setItems(lisj);
         TabJoueur.refresh();
        
         rootP = root;

        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
          //  Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
                paneE.setVisible(true);
            } else {
                drawer.open();
                paneE.setVisible(false);
            }
        });
   
 
    }    
    
        private void valider(ActionEvent event) throws IOException {
       JoueurService JS=new JoueurService();
       JS.supprimerJoueur();
       Joueurs J=new Joueurs();
       Stage s = new Stage();
      /*  FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
    new FileChooser.ExtensionFilter("xlsx", "*.xlsx"));
       File fil= fileChooser.showOpenDialog(s);*/
//       JS.supprimerJoueur();
//       TabJoueur.refresh();
      
     //  JS.ajouterJoueur(J);
       this.initialize(null, null);
       // close(event);
        //view();
       //TabJoueur.refresh();
        }
        
    
    public void view(ActionEvent event)
    {
         JoueurService js= new JoueurService();
         Equipe e=new Equipe();
         e.setNomEquipe(look.getText());
         //int valeur =look.();
         lisj= js.showme();
         idjou.setCellValueFactory(new PropertyValueFactory("Idjoueur"));
         Namej.setCellValueFactory(new PropertyValueFactory("NomJoueur"));
         prenomj.setCellValueFactory(new PropertyValueFactory("PrenomJoueur"));
         //imJ.setCellValueFactory(new PropertyValueFactory("ImageJoueur"));
         posij.setCellValueFactory(new PropertyValueFactory("Position"));
         //equipej.setCellValueFactory(new PropertyValueFactory("equipe"));
         see.setOnAction(this::view);
         TabJoueur.setItems(null);
         TabJoueur.setItems(lisj);
         
        
    }

         private void close(ActionEvent event) throws IOException
    {
    
    Parent homePage = FXMLLoader.load(getClass().getResource("ListJoueurs.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    
    }


    @FXML
    private void Browse(ActionEvent event) throws MalformedURLException {
         
          FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(drawer.getScene().getWindow());
        if (file != null) {
            
            
           String path = file.toURI().toURL().toString();
           
            JoueurService JS=new JoueurService();
       JS.supprimerJoueur();
       Joueurs J=new Joueurs();
            
        Stage s = new Stage();
    
      
       JS.ajouterJoueur(J,path);
       this.initialize(null, null);
        }
    }

    @FXML
    private void interfaceEquipe(ActionEvent event) throws IOException {
           Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/AffichageListEquipe.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void decon(ActionEvent event) throws IOException {
        
           XML x =new XML();
        x.Ecrire("0","0","0","0","0","0","0", 0, "0","0");
     Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}

