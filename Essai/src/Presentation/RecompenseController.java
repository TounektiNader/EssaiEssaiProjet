/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Cadeau;
import Entity.Promo;
import Entity.Recompense;
import Entity.User;
import static Presentation.GestionRecompenseController.rootP;
import Services.CadeauService;
import Services.PromoService;
import Services.RecompenseService;
import Services.ServiceUser;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;

import java.net.URL;
import java.util.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import org.xml.sax.SAXException;

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

    private final double IMG_WIDTH = 600;
    private final double IMG_HEIGHT = 337;

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
    private Label nombreCadeau;
    @FXML
    private ListView<Label> list;
    @FXML
    private HBox hboxList;
    @FXML
    private ImageView imgageViewStat;
    public static Image imgage;
    @FXML
    private AnchorPane root;
    @FXML
    private Pane paneE;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    public static AnchorPane rootP;
    @FXML
    private Label nmbJeton;
    @FXML
    private JFXTextField username;
    @FXML
    private TextField coupon;

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

        CadeauService cadeauService = new CadeauService();
        Cadeau cadeau = cadeauService.cadeau("Golf 7");
        type.setText(cadeau.getType());
        jeton.setText("" + cadeau.getJeton());

        int nombreVoitur = cadeauService.listCadeau("Voiture").size();
        nombreVoiture.setText("" + nombreVoitur);
        int nombreTe = cadeauService.listCadeau("Telephone").size();
        nombreTel.setText("" + nombreTe);
        int nombreBo = cadeauService.listCadeau("Bon_Achat").size();
        nombreBon.setText("" + nombreBo);
        int nombreTick = cadeauService.listCadeau("Ticket").size();
        this.nombreTicket.setText("" + nombreTick);
        RecompenseService recompenseService = new RecompenseService();
        int nombreCadea = recompenseService.NombreMesCadeau(username.getText());
        //System.out.println(nombreCadea);
        nombreCadeau.setText("" + nombreCadea);

//  remplirGrid("voiture");
        ImageView img1 = new ImageView("images/4.png");
        ImageView img2 = new ImageView("images/1.png");
        ImageView img3 = new ImageView("images/2.png");
        ImageView img4 = new ImageView("images/3.png");

        hBox.getChildren().addAll(img1, img2, img3, img4);

        startAnimation(hBox);

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

    public void remplirGrid(String typeGrid) {

        CadeauService cadeauService = new CadeauService();
        List<Cadeau> cadeau = new ArrayList<Cadeau>();
        cadeau.addAll(cadeauService.listCadeau(typeGrid));
        int j = 0;
        for (int i = 0; i < cadeau.size(); i++) {

            Label labelType = new Label();
            labelType.setText(cadeau.get(i).getType());
            Image img = new Image("img/"+cadeau.get(i).getImg());
            ImageView im = new ImageView(img);
            im.setFitHeight(30);
            im.setFitWidth(30);
            labelType.setGraphic(im);

            Label labelJeton = new Label();
            labelJeton.setText("Nombre de Jeton :  " + "" + cadeau.get(i).getJeton());

            Button bt = new Button();
            bt.setText("Parier");

            int nomJeton = cadeau.get(i).getJeton();
            String nomType = cadeau.get(i).getType();
           imgage = new Image("img/"+cadeau.get(i).getImg());
            bt.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    type.setText(nomType);
                    jeton.setText("" + nomJeton);

                    imgageViewStat.setImage(imgage);

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
        grid.getChildren().clear();
        remplirGrid("Telephone");
        hBox.setVisible(false);
        hboxList.setVisible(false);
        
    }

    @FXML
    private void listesTickets(ActionEvent event) {
        grid.getChildren().clear();
        remplirGrid("Ticket");
        hBox.setVisible(false);
        hboxList.setVisible(false);
        
        
        
    }

    @FXML
    private void listesBon(ActionEvent event) {
        grid.getChildren().clear();
        remplirGrid("Bon_Achat");
        hBox.setVisible(false);
        hboxList.setVisible(false);
      
       
    }
//     public int total()
//    {
//        int total=0;
//        RecompenseService recompenseService = new RecompenseService();
//        List<Recompense> listRecompense2 = new ArrayList<Recompense>();
//        listRecompense2.addAll(recompenseService.listRecompense(username.getText()));
//
//        for (int i = 0; i <= listRecompense2.size(); i++) 
//        {
//            total=total+(listRecompense2.get(i).getCadeau().getJeton());
//        }
//        return total;
//    }

    @FXML
    private void SupprimerCoupon(ActionEvent event) {
       PromoService x=new PromoService();
       Promo y=new Promo();
       y=x.Recherche(coupon.getText());
       if(y.getCoupon().equals(coupon.getText()))
       {
           try {
               XML m= new XML();
               User u= new User();
               u=m.lire();
               u.setJeton(u.getJeton()+((100*y.getPromotion())/100));
               m.Ecrire(u.getUsername(),u.getNom(),u.getPrenom(),u.getMdp(),u.getRole(),u.getMail(),u.getStatus(),u.getJeton(),u.getNationalite(),u.getNum());
               nmbJeton.setText(""+u.getJeton());
               ServiceUser h=new ServiceUser();
               h.modifierUser(u);
               x.DeletePromo(coupon.getText());
               Notifications notificationbuilder = Notifications.create()
                       .title("Succès")
                       .text("Coupon avec succès")
                       .graphic(null)
                       .position(Pos.CENTER)
                       .onAction(new EventHandler<ActionEvent>() {
                           @Override
                           public void handle(ActionEvent event) {
                               
                           }
                       });
               
               notificationbuilder.showConfirm();
               coupon.clear();
           } catch (SAXException ex) {
               Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
           } catch (IOException ex) {
               Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
           }
     
       }
       else
       {
           Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Ce code n'existe pas")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

            notificationbuilder.showError();
       coupon.clear();    
       }
      
       
    }

    @FXML
    private void listesVoitures(ActionEvent event) {
        grid.getChildren().clear();
        remplirGrid("Voiture");
        hBox.setVisible(false);
        hboxList.setVisible(false);
    }
       @FXML
    private void listMesCadea(ActionEvent event) {
        grid.getChildren().clear();
        hBox.setVisible(false);
        hboxList.setVisible(true);

        list.getItems().clear();
        RecompenseService recompenseService = new RecompenseService();
        List<Recompense> listRecompense = new ArrayList<Recompense>();
        listRecompense.addAll(recompenseService.listRecompense(username.getText()));

        for (int i = 0; i <= listRecompense.size(); i++) {

            try {

                Label lbl = new Label(listRecompense.get(i).getCadeau().getType() + "            " + listRecompense.get(i).getCadeau().getCategorie()+ "            " +listRecompense.get(i).getCadeau().getJeton());

                Image img = new Image("img/"+listRecompense.get(i).getCadeau().getImg());
                ImageView im = new ImageView(img);

                im.setFitHeight(30);
                im.setFitWidth(30);
                lbl.setGraphic(im);
                list.getItems().add(lbl);
            } catch (Exception ex) {
            }

        }

//         int j = 0;
//        for (int i =0;i<listRecompense.size();i++){
//             
//              Label labelType = new Label();
//            labelType.setText(listRecompense.get(i).getCadeau().getType());
//            Image img = new Image("/images/error.png");
//            ImageView im = new ImageView(img);
//            im.setFitHeight(30);
//            im.setFitWidth(30);
//            labelType.setGraphic(im);
//
//            Label labelJeton = new Label();
//            labelJeton.setText("Nombre de Jeton :  " + "" + listRecompense.get(i).getCadeau().getCategorie());
//
//           
//            
//            grid.setConstraints(labelType, i, j);
//            grid.setConstraints(labelJeton, i, j + 1);
//           
//            grid.getChildren().addAll(labelType,labelJeton);
//            j = i;
//
//
//            
//        }
    }

    @FXML
    private void confirmer(ActionEvent event) {
        
        hboxList.setVisible(false);
        RecompenseService recompenseService = new RecompenseService();
        CadeauService cadeauService = new CadeauService();
        User user = new User();
        user = recompenseService.getUser(username.getText());
        int nombreJetonNecessaire = Integer.parseInt(jeton.getText());
        if (user.getJeton() < nombreJetonNecessaire) {

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
        } else {

            
            recompenseService.ajouterRecompense(username.getText(), type.getText());
            int nombreCadea = recompenseService.NombreMesCadeau(username.getText());
            //System.out.println(nombreCadea);
            nombreCadeau.setText("" + nombreCadea);
           recompenseService.deminuerJeton(username.getText(),Integer.parseInt(nmbJeton.getText()),Integer.parseInt(jeton.getText()));
           
            ServiceUser serviceUser = new ServiceUser();
            User u=serviceUser.getUser(username.getText());
           XML x = new XML();
           x.Ecrire(u.getUsername(),u.getNom(), u.getPrenom(), u.getMdp(), u.getRole(), u.getMail(),u.getStatus(), u.getJeton(), u.getNationalite(),u.getNum());
                
           int jetonActuelle = (Integer.parseInt(nmbJeton.getText())- Integer.parseInt(jeton.getText()));
           
           nmbJeton.setText(""+jetonActuelle);
            Notifications notificationbuilder = Notifications.create()
                    .title("Succès")
                    .text("Recompense avec succès ")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

            notificationbuilder.showConfirm();
        }

    }
//
//    private void afficheGestion(ActionEvent event) throws IOException {
//        Stage primaryStage = new Stage();
//        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/GestionRecompense.fxml"));
//
//        Scene scene = new Scene(root);
//
//        primaryStage.setScene(scene);
//        primaryStage.show();
//
//        final Node source = (Node) event.getSource();
//        final Stage stage = (Stage) source.getScene().getWindow();
//        stage.close();
//
//    }

}
