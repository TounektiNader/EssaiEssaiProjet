package Presentation;

import Entity.User;
import Services.ServiceUser;
import Utils.XML;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.File;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import org.xml.sax.SAXException;

public class EditUserController implements Initializable {
    
    private void Vide() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("L'un des champs est vide");
        alert.showAndWait();
    }
    @FXML
    private ImageView image2;
    @FXML
    private JFXDrawer drawer;
    
    @FXML
    private JFXHamburger hamburger;
    
    @FXML
    private AnchorPane root;

    public static AnchorPane rootP;
    @FXML
private PasswordField pass_hidden;
    @FXML
private TextField pass_text;
    @FXML
private TextField pseudo;
    @FXML
private TextField nom;
    @FXML
private TextField prenom;
    @FXML
private TextField email;
@FXML
private CheckBox pass_toggle;
@FXML
private Button btn_start_stop;

/**
 * Controls the visibility of the Password field
 * @param event
 */
@FXML
public void togglevisiblePassword(ActionEvent event) {
    if (pass_toggle.isSelected()) {
        pass_text.setText(pass_hidden.getText());
        pass_text.setVisible(true);
        pass_hidden.setVisible(false);
        return;
    }
    pass_hidden.setText(pass_text.getText());
    pass_hidden.setVisible(true);
    pass_text.setVisible(false);
}
private String passwordValue() {
    return pass_toggle.isSelected()?
       pass_text.getText(): pass_hidden.getText();
}
 @FXML
   public void Modifier(ActionEvent event) throws SAXException, IOException
   {
       User u;
       XML x= new XML();
       u=x.lire();
       u.setMail(email.getText());
       u.setNom(nom.getText());
       u.setPrenom(prenom.getText());
       u.setMdp(passwordValue());
       x.Ecrire(u.getUsername(), u.getNom(), u.getPrenom(),u.getMdp(),u.getRole(),u.getMail(),u.getStatus(), u.getJeton(),u.getNationalite(),u.getNum());
       ServiceUser SU= new ServiceUser();
       SU.modifierUser(u);
       Stage primarya= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));
        Scene scene = new Scene(root);
        primarya.setScene(scene);
        primarya.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       pass_text.setVisible(false);
                 XML x = new XML();
         User u;
        try {
            u=x.lire();
                if(u.getNationalite().equals("Tunisien"))
                {
                Image i= new Image("/images/tn.png");
                image2.setImage(i);
                }
                if(u.getNationalite().equals("Français"))
                {
                Image i= new Image("/images/fr.png");
                image2.setImage(i);
                }
                pseudo.setText(u.getUsername()); 
                nom.setText(u.getNom()); 
                prenom.setText(u.getPrenom()); 
                pass_hidden.setText(u.getMdp()); 
                email.setText(u.getMail()); 
            } catch (SAXException ex) {
            Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EditUserController.class.getName()).log(Level.SEVERE, null, ex);
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
    }   

   
}
    



