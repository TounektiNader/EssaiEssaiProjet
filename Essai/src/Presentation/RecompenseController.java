/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Cadeau;
import Entity.Recompense;
import Entity.User;
import Services.CadeauService;
import Services.RecompenseService;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class RecompenseController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField jeton;
    @FXML
    private GridPane grid;

    private final double IMG_WIDTH = 579;
    private final double IMG_HEIGHT = 269;

    private final int NUM_OF_IMGS = 4;
    private final int SLIDE_FREQ = 4;
    @FXML
    private HBox hBox;
    @FXML
    private Label nombreTel;
    @FXML
    private Label nombreVoiture;
    @FXML
    private Label nombreBon;
    @FXML
    private Label nombreTicket;
    @FXML
    private JFXButton tick;
    @FXML
    private JFXButton voi;
    @FXML
    private JFXButton tele;
    @FXML
    private JFXButton achat;
    @FXML
    private JFXButton mesCadeaux;
    @FXML
    private JFXButton tele1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CadeauService cadeauService = new CadeauService();
        Cadeau cadeau = cadeauService.cadeau("Golf 7");
        type.setText(cadeau.getType());
        jeton.setText("" + cadeau.getJeton());

        int nombreVoitur = cadeauService.listCadeau("voiture").size();
        nombreVoiture.setText("" + nombreVoitur);
        int nombreTe = cadeauService.listCadeau("Telephone").size();
        nombreTel.setText("" + nombreTe);
        int nombreBo = cadeauService.listCadeau("Bon").size();
        nombreBon.setText("" + nombreBo);
        int nombreTick = cadeauService.listCadeau("Ticket").size();
        this.nombreTicket.setText("" + nombreTick);
//  remplirGrid("voiture");

        ImageView img1 = new ImageView("images/voiture.png");
        ImageView img2 = new ImageView("images/voiture.png");
        ImageView img3 = new ImageView("images/voiture.png");
        ImageView img4 = new ImageView("images/voiture.png");

        hBox.getChildren().addAll(img1, img2, img3, img4);

        startAnimation(hBox);
    }

    public void remplirGrid(String typeGrid) {

        CadeauService cadeauService = new CadeauService();
        List<Cadeau> cadeau = new ArrayList<Cadeau>();
        cadeau.addAll(cadeauService.listCadeau(typeGrid));
        int j = 0;
        for (int i = 0; i < cadeau.size(); i++) {

            Label labelType = new Label();
            labelType.setText(cadeau.get(i).getType());
            Image img = new Image("/images/error.png");
            ImageView im = new ImageView(img);
            im.setFitHeight(30);
            im.setFitWidth(30);
            labelType.setGraphic(im);

            Label labelJeton = new Label();
            labelJeton.setText("Nombre de Jeton :  " + "" + cadeau.get(i).getJeton());

            Button bt = new Button();
            bt.setText("Parier");
            
            int nomJeton =cadeau.get(i).getJeton();
            String nomType=cadeau.get(i).getType();
            
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                   type.setText(nomType);
                   jeton.setText(""+nomJeton);
                }
            });

            grid.setConstraints(labelType, i, j);
            grid.setConstraints(labelJeton, i, j + 1);
            grid.setConstraints(bt, i, j + 2);
            grid.getChildren().addAll(labelType, labelJeton, bt);
            j = i;

        }

    }

    public void startAnimation(final HBox hbox) {
        //error occured on (ActionEvent t) line
        //slide action
        EventHandler<ActionEvent> slideAction = (ActionEvent t) -> {
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1.5), hbox);
            trans.setByX(-IMG_WIDTH);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };
        //eventHandler
        EventHandler<ActionEvent> resetAction = (ActionEvent t) -> {
            TranslateTransition trans = new TranslateTransition(Duration.seconds(1), hbox);
            trans.setByX((NUM_OF_IMGS - 1) * IMG_WIDTH);
            trans.setInterpolator(Interpolator.EASE_BOTH);
            trans.play();
        };

        List<KeyFrame> keyFrames = new ArrayList<>();
        for (int i = 1; i <= NUM_OF_IMGS; i++) {
            if (i == NUM_OF_IMGS) {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), resetAction));
            } else {
                keyFrames.add(new KeyFrame(Duration.seconds(i * SLIDE_FREQ), slideAction));
            }
        }
//add timeLine
        Timeline anim = new Timeline(keyFrames.toArray(new KeyFrame[NUM_OF_IMGS]));

        anim.setCycleCount(Timeline.INDEFINITE);
        anim.playFromStart();
    }

    @FXML
    private void listesTelephones(ActionEvent event) {
       
        remplirGrid("Telephone");
         hBox.setVisible(false);
    }

    @FXML
    private void listesTickets(ActionEvent event) {
       
        remplirGrid("Ticket");

        hBox.setVisible(false);
    }

    @FXML
    private void listesBon(ActionEvent event) {

        remplirGrid("Bon");
        hBox.setVisible(false);
    }

    @FXML
    private void listesVoitures(ActionEvent event) {

        remplirGrid("voiture");
        hBox.setVisible(false);
    }

    
    @FXML
    private void listMesCadea(ActionEvent event) {
        
         hBox.setVisible(false);
         
        RecompenseService recompenseService = new RecompenseService();
        List<Recompense> listRecompense= new ArrayList<Recompense>();
         listRecompense.addAll(recompenseService.listRecompense("titou"));
        int j = 0;
        for (int i =0;i<listRecompense.size();i++){
             
              Label labelType = new Label();
            labelType.setText(listRecompense.get(i).getCadeau().getType());
            Image img = new Image("/images/error.png");
            ImageView im = new ImageView(img);
            im.setFitHeight(30);
            im.setFitWidth(30);
            labelType.setGraphic(im);

            Label labelJeton = new Label();
            labelJeton.setText("Nombre de Jeton :  " + "" + listRecompense.get(i).getCadeau().getCategorie());

           
            
            grid.setConstraints(labelType, i, j);
            grid.setConstraints(labelJeton, i, j + 1);
           
            grid.getChildren().addAll(labelType,labelJeton);
            j = i;


            
        }
        
    }

    @FXML
    private void confirmer(ActionEvent event) {
      
        RecompenseService recompenseService = new RecompenseService();
        CadeauService cadeauService = new CadeauService();
        User user = new User();
        user = recompenseService.getUser("titou");
        int nombreJetonNecessaire = Integer.parseInt(jeton.getText());
        if(user.getJeton()<nombreJetonNecessaire){
            
            Notifications notificationbuilder = Notifications.create()
            .title("Alerte")
            .text("Vous n'avez assez de jeton pour avoir ce cadeau ")
            .graphic(null)
            
            .position(Pos.CENTER)
            .onAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
              
  
        
        }    
    });
     
    
     notificationbuilder.showError();
                  }
        else{
        
        recompenseService.ajouterRecompense("titou", type.getText());
        }
        
    }

    @FXML
    private void afficheGestion(ActionEvent event) throws IOException {
           Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/GestionRecompense.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
                 
    final Node source = (Node) event.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    
    }
    
    

}
