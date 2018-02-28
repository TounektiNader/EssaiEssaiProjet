/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Bet;
import Entity.Partie;
import Entity.User;
import static Presentation.GestionRecompenseController.rootP;
import Services.ServiceBet;
import Utils.Pari;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class MesParisController implements Initializable {

    @FXML
    private Pane pan;
    @FXML
    private JFXListView<Label> list;

    private ObservableList<Bet> data;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;

      public static AnchorPane rootP;
      
    @FXML
    private Pane paneE;
    @FXML
    private JFXButton ouvrirBet;
    @FXML
    private Label nmbJeton;
    @FXML
    private JFXTextField username;
         
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
            nmbJeton.setText("" + u.getJeton());
        } catch (SAXException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        ServiceBet service = new ServiceBet();
        Pari pari = new Pari();
        data = service.listBet(username.getText());
        int idPartie = 0;
        
        for (int i = 0; i <= data.size(); i++) {

            try {                
                
                idPartie = data.get(i).getPartie().getIdMatch();
                Label labelDetail = new Label(pari.matchDateTour(idPartie));
                System.out.println(labelDetail);
                Label lbl = new Label(pari.matchDateTour(idPartie)+"            "+pari.matchParier(idPartie));
                System.out.println(pari.matchParier(idPartie));
                if(data.get(i).getEtat().equals("Perte")){
                Image img = new Image("/images/error.png");
                ImageView im = new ImageView(img);

                im.setFitHeight(30);
                im.setFitWidth(30);
                lbl.setGraphic(im);
                }
                else if(data.get(i).getEtat().equals("Gain")){
                    Image img = new Image("/images/success.png");
                ImageView im = new ImageView(img);

                im.setFitHeight(30);
                im.setFitWidth(30);
                lbl.setGraphic(im);
                }else{
                   Image img = new Image("/images/errorr.png");
                ImageView im = new ImageView(img);

                im.setFitHeight(30);
                im.setFitWidth(30);
                lbl.setGraphic(im);
                
                
                }
              
            list.getItems().add(lbl);
            } catch (Exception ex) {
            }
        
            
            
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
                paneE.setVisible(true);
            } else {
                drawer.open();
                paneE.setVisible(false);
            }
        });
   
 
    }

    @FXML
    private void ouvrirFiche(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Parierrrr.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

  

    
}
