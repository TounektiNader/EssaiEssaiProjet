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

public class InscriptionController implements Initializable {
    
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
    private JFXTextField pseudo;
    @FXML
    private JFXPasswordField mdp;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField prenom;
    @FXML
    private JFXTextField num;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXComboBox<String> role;
    @FXML
    private JFXComboBox<String> nationalite;
    @FXML
    private AnchorPane root;

    public static AnchorPane rootP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        role.getItems().add("Utilisateur");
         role.getItems().add("Admin");
         nationalite.getItems().add("Tunisie");
         nationalite.getItems().add("Egypte");
         nationalite.getItems().add("Maroc");
         nationalite.getItems().add("Sénégal");
         nationalite.getItems().add("Nigeria");
         nationalite.getItems().add("Arabie Saoudite");
         nationalite.getItems().add("Australie");
         nationalite.getItems().add("Japon");
         nationalite.getItems().add("République de Corée");
         nationalite.getItems().add("RI Iran");
         nationalite.getItems().add("Costa Rica");
         nationalite.getItems().add("Mexique");
         nationalite.getItems().add("Panama");
          nationalite.getItems().add("Argentine");
           nationalite.getItems().add("Brésil"); 
            nationalite.getItems().add("Colombie");
             nationalite.getItems().add("Pérou");
              nationalite.getItems().add("Uruguay");
               nationalite.getItems().add("Allemagne");
                nationalite.getItems().add("Angleterre");
                 nationalite.getItems().add("Belgique");
                  nationalite.getItems().add("Croatie");
                   nationalite.getItems().add("Danemark");
                    nationalite.getItems().add("Espagne");
                     nationalite.getItems().add("France");
                      nationalite.getItems().add("Islande");
                       nationalite.getItems().add("Pologne");
                        nationalite.getItems().add("Portugal");
                         nationalite.getItems().add("Russie");
                          nationalite.getItems().add("Serbie");
                           nationalite.getItems().add("Suède");
                            nationalite.getItems().add("Suisse");
                          
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
    }
   @FXML
   public void inscription(ActionEvent event) throws IOException
   {
      if(nom.getText().isEmpty() || prenom.getText().isEmpty() || mdp.getText().isEmpty() || role.getSelectionModel().getSelectedItem().isEmpty() || email.getText().isEmpty() || nationalite.getSelectionModel().getSelectedItem().isEmpty() )
      {
       Vide();   
      }
      else
      {
         User u = new User(nom.getText(),prenom.getText(),pseudo.getText(),mdp.getText(),role.getSelectionModel().getSelectedItem(),email.getText(),"false",20,nationalite.getSelectionModel().getSelectedItem(),num.getText());
    ServiceUser SU = new ServiceUser();
    SU.ajouterUser(u);
    XML x= new XML();
    x.Ecrire(pseudo.getText(), nom.getText(),prenom.getText(),mdp.getText(),role.getSelectionModel().getSelectedItem(),email.getText(),"false",20,nationalite.getSelectionModel().getSelectedItem(),num.getText());
    if(role.getSelectionModel().getSelectedItem().equals("Admin"))
    {
        System.out.println("admin");
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close(); 
    }
    else if(role.getSelectionModel().getSelectedItem().equals("Utilisateur"))
    {
       Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Valider.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();  
    }  
      }
   
   
   }
}
    



