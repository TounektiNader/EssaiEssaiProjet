package Presentation;

import Entity.User;
import Services.ServiceUser;
import Utils.XML;
import Utils.mail;
import com.jfoenix.controls.JFXComboBox;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.xml.sax.SAXException;

public class MotdepasseController implements Initializable {
    
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
        alert.setContentText("Ce Pseudo n'existe pas");
        alert.showAndWait();
    }
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXTextField pseudo;
    
    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private AnchorPane root;

    public static AnchorPane rootP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
                          
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
   public void valider(ActionEvent event) throws IOException, SAXException
   {
       ServiceUser u= new ServiceUser();
        User U;
        if(pseudo.getText().isEmpty())
        {
            Vide();
        }
        else
        {
         U=u.rechercheUser(pseudo.getText());
         if(U.getStatus()==null)
         {
             Vide2();
         }
         else
         {
           mail m=new mail();
        
        m.send(U.getMail(),"Mot de passe",U.getMdp());
        Stage SecondStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene scene = new Scene(root);
        SecondStage.setScene(scene);
        SecondStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();  
         }
        
            
        }
        
   }
}
    



