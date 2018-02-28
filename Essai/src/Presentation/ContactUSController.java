package Presentation;

import Entity.User;
import Services.ServiceUser;
import Utils.XML;
import Utils.mail;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.xml.sax.SAXException;

public class ContactUSController  extends Application implements Initializable {

    @FXML
    private JFXTextField pseudo;
    @FXML
    private JFXTextField pseudo1;
    @FXML
    private JFXTextField pseudo11;
    @FXML
    private JFXTextArea txt;
    @FXML
    private Hyperlink twitter;
    @FXML
    private Hyperlink link;
      
    private void Vide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("L'un des champs est vide");
        alert.showAndWait();
    }
     
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Hyperlink facebook;
  
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;

    public static AnchorPane rootP;

    @Override
    public void initialize(URL url, ResourceBundle rb)  {
        
         XML a = new XML();
        User u;
        try {
            u=a.lire();
            if(u.getUsername().equals("0"))
        {
            
        }
            else
            {
                pseudo.setText(u.getUsername());
                pseudo1.setText(u.getMail());
            }
        } catch (SAXException ex) {
            Logger.getLogger(ContactUSController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ContactUSController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
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
       
        facebook.setOnAction(new EventHandler<ActionEvent>() {
 
    @Override
    public void handle(ActionEvent event) {
        getHostServices().showDocument("https://www.facebook.com/Russia2018-153869445327756/");
    }
});
        
        twitter.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                getHostServices().showDocument("https://twitter.com/EspritRussia");
             }
         });
        
               link.setOnAction(new EventHandler<ActionEvent>() {
             @Override
             public void handle(ActionEvent event) {
                 getHostServices().showDocument("https://www.linkedin.com/in/russia-russia");
             }
         });
        
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        mail x=new mail();
        x.send("russia.2018.esprit@gmail.com",pseudo11.getText(),"Ce mail est de la part: "+pseudo.getText()+"    Correspand à :  "+pseudo1.getText()+" qui souhaite "+txt.getText());
         if(x.isVerif()){
         
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Contact US");
        alert.setContentText("Votre message a été bien envoyer ");
        alert.showAndWait();
        
         Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Utilisateur.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

         }
         
         else{
             
             
             Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("ERROR");
        alert.setHeaderText("Contact US");
        alert.setContentText("Votre mail n'a pas été pris en charge ");
        alert.showAndWait();
             
             
         
         }
    }
   
}
    



