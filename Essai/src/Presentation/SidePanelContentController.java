package Presentation;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SidePanelContentController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton equipesBt;
    @FXML
    private JFXButton exit1;
    @FXML
    private JFXButton gift;
    @FXML
    private JFXButton exit21;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    


    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void Actualite(ActionEvent event) throws IOException {
        
        
           Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MenuUserActu.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
        
    }

    @FXML
    private void recomm(ActionEvent event) throws IOException {
        
        
           Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MenuAdmin.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
        
    
    }
    
   

    @FXML
    private void matchGestion(ActionEvent event) throws IOException {
            
           Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/User.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
        
    }

    @FXML
    private void equipesG(ActionEvent event) throws IOException {
            
           Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/AffichageListEquipe.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
    }

    @FXML
    private void statBet(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Parierrrr.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
        
    }

    @FXML
    private void cadeauGestion(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/GestionRecompense.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
        
      
    }
    
}
