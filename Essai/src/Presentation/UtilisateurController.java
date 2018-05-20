/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import static Presentation.AdminController.rootP;
import Utils.XML;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class UtilisateurController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXTextField username;
    @FXML
    private Label jeton;
    public static AnchorPane rootP;
    @FXML
    private VBox vb;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        XML a = new XML();
        User u;
        try {
            u = a.lire();
            username.setText(u.getUsername());
            jeton.setText("" + u.getJeton());
        } catch (SAXException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        String iconten ="<iframe scrolling=\"auto\" allowtransparency=\"true\"   width=\"100%\" height=\"400px\" src=\"https://www.watchisup.fr/compte-a-rebours/embed/2018-06-16-00-00-10?backgroundcolor=rgba(255%2C255%2C255%2C.4)&color=\" frameborder=\"0\" allowfullscreen></iframe>";
        WebView webView = new WebView();
       
        WebEngine engine = webView.getEngine();
        
               
        engine.loadContent(iconten);
        vb.getChildren().addAll(webView);
    }

    @FXML
    private void updateProfil(ActionEvent event) throws IOException {
            Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/EditUser.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
        
        
    }

}
