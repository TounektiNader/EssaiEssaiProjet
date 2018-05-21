package Presentation;

import Entity.User;
import Services.PromoService;
import Services.ServiceUser;
import Utils.XML;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.xml.sax.SAXException;

public class AccueilController implements Initializable  {

    @FXML
    private AnchorPane auth;
    @FXML
    private Label jeton;
    @FXML
    private JFXTextField username;
    @FXML
    private Pane panee;
    private void ValidationUser() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("Votre Compte n'est pas Validé");
        alert.showAndWait();
    }
     private void ValidationAdmin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("Votre Compte N'est pas validé par un administratuer");
        alert.showAndWait();
    }  
    private void Vide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("L'un des champs est vide");
        alert.showAndWait();
    }
     private void Vide2() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("Il y a une erreur dans l'authentification");
        alert.showAndWait();
    }
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField pseudo;
    @FXML
    private JFXPasswordField mdp;
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private AnchorPane root;

    public static AnchorPane rootP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
         /*if (!NaivgationDrawer.isSplashLoaded) {
            loadSplashScreen();
        }*/
        panee.setVisible(false);
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
   public void connexion(ActionEvent event) throws IOException, SAXException
   {
      ServiceUser u= new ServiceUser();
      User s=new User();
      
      String login = pseudo.getText();
      String mot =mdp.getText();
      if(login.isEmpty() || mot.isEmpty())
      {
          Vide();
         
      }
     
      else
      {
      s=u.afficherUser(login,mot);
      if(s.getStatus()== null)
      {
          Vide2();
      }
      else
      {
     
        if("status".equals(s.getStatus()))
        {
           s.setRole("Utilisateur");
                        System.out.println(s.getRole());

            if("Admin".equals(s.getRole()))
            {
                System.out.println(s.getRole());
            XML x= new XML();
        x.Ecrire(s.getUsername(),s.getNom(),s.getPrenom(),s.getMdp(),s.getRole(),s.getMail(),s.getStatus(),s.getJeton(),s.getNationalite(),s.getNum());
               
       Stage primarya= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Admin.fxml"));
        Scene scene = new Scene(root);
        primarya.setScene(scene);
        primarya.show();
          primarya.setResizable(false);
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
            }
            else
            {
                PromoService promo=new PromoService();
                promo.ajouterPromo();
                panee.setVisible(true);
             XML x= new XML();
            x.Ecrire(s.getUsername(),s.getNom(),s.getPrenom(),s.getMdp(),s.getRole(),s.getMail(),s.getStatus(),s.getJeton(),s.getNationalite(),s.getNum());
            Stage primarya= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Utilisateur.fxml"));
        Scene scene = new Scene(root);
        primarya.setScene(scene);
        primarya.show();
          primarya.setResizable(false);
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
            }
        }
        
        else if("false".equals(s.getStatus()) &&("Utilisateur".equals(s.getRole())))
        {
            ValidationUser();
           Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Valider.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
        } 
        else if("false".equals(s.getStatus()) &&("Admin".equals(s.getRole())))
        {
            ValidationAdmin();
           
        }   
      }
            
   }
   }
    @FXML
    private void inscription(ActionEvent event) throws IOException
    {
       Stage SecondStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Inscription.fxml"));
        Scene scene = new Scene(root);
        SecondStage.setScene(scene);
        SecondStage.show();
          SecondStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();   
    }
    @FXML
    private void oublier(ActionEvent event) throws IOException
    {
       Stage SecondStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("motdepasse.fxml"));
        Scene scene = new Scene(root);
        SecondStage.setScene(scene);
        SecondStage.show();
          SecondStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();   
    }
    
    
    
    private void loadSplashScreen() {
        try {
            NaivgationDrawer.isSplashLoaded = true;
            
            StackPane pane = FXMLLoader.load(getClass().getResource(("SplashFXML.fxml")));
            root.getChildren().setAll(pane);

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(3), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(3), pane);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });

            fadeOut.setOnFinished((e) -> {
                try {
                    AnchorPane parentContent = FXMLLoader.load(getClass().getResource(("Accueil.fxml")));
                    root.getChildren().setAll(parentContent);

                } catch (IOException ex) {
                    Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
    



