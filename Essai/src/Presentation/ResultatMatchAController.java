package Presentation;

import Entity.Partie;
import Entity.Resultat;
import Entity.User;
import static Presentation.StatPariController.rootP;
import Services.PartieService;
import Services.ResultatService;
import Services.ServiceBet;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.xml.sax.SAXException;

public class ResultatMatchAController implements Initializable {

    @FXML
    private Button reset;
    @FXML
    private Button saveUser;
    @FXML
    private TableView<Resultat> table;
    @FXML
    private TableColumn<Resultat, Integer> columnId;
    @FXML
    private TableColumn<Resultat, String> columnHome;
    @FXML
    private TableColumn<Resultat, Integer> columnBH;
    @FXML
    private TableColumn<Resultat, String> colAway;
    @FXML
    private TableColumn<Resultat, Integer> columnBA;
    @FXML
    private TextField home;
    @FXML
    private ComboBox<Integer> CBH;
    @FXML
    private TextField away;
    @FXML
    private ComboBox<Integer> CBA;

    private ObservableList<Resultat> data;
    
    public static int idPartie;

    @FXML
    private AnchorPane root;
    
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    
      public static AnchorPane rootP;
    @FXML
    private VBox paneEE;
    
     static boolean  bolAB = true ;
     static boolean  bolCD = true ;
     static boolean  bolEF = true ;
     static boolean  bolGH = true ;
     
     static boolean  bol41 = true ;
     static boolean  bol42 = true ;
     static boolean  bol43 = true ;
     static boolean  bol44 = true ;
    
     static boolean  bol21 = true ;
     static boolean  bol22 = true ;
     
     static boolean  bolfinalise = true ;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton b4;
    @FXML
    private JFXButton b6;
    @FXML
    private JFXButton b5;
    @FXML
    private JFXButton b7;
    @FXML
    private JFXTextField username;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
           XML x= new XML();
         
          User user = new User ();
        try {
            user = x.lire();
            username.setText(user.getUsername());
        } catch (SAXException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Integer tableauEntier[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        CBH.getItems().addAll(tableauEntier);
        CBA.getItems().addAll(tableauEntier);

        ResultatService resultatService = new ResultatService();
        PartieService partieService = new PartieService();
        data = resultatService.listResultats();
        columnId.setCellValueFactory(new PropertyValueFactory("idPartie"));
        columnHome.setCellValueFactory(new PropertyValueFactory<>("nomEquipeHome"));
        colAway.setCellValueFactory(new PropertyValueFactory<>("nomEquipeAway"));
        columnBH.setCellValueFactory(new PropertyValueFactory<>("butHome"));
        columnBA.setCellValueFactory(new PropertyValueFactory<>("butAway"));
        table.setItems(null);
        table.setItems(data);
        
        
           
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
                paneEE.setVisible(true);
                
                b1.setVisible(true);
                b2.setVisible(true);
                b3.setVisible(true);
                b4.setVisible(true);
                b5.setVisible(true);
                b6.setVisible(true);
                b7.setVisible(true);
            } else {
                drawer.open();
                
                paneEE.setVisible(false);
                 b1.setVisible(false);
                b2.setVisible(false);
                b3.setVisible(false);
                b4.setVisible(false);
                b5.setVisible(false);
                b6.setVisible(false);
                b7.setVisible(false);
            }
        });
    }

    @FXML
    public void getSelectionItem() {

        Resultat resultatSelected = table.getSelectionModel().getSelectedItem();
        idPartie = resultatSelected.getPartie().getIdMatch();
        home.setText(resultatSelected.getNomEquipeHome());
        away.setText(resultatSelected.getNomEquipeAway());
        CBH.setValue(resultatSelected.getButHome());
        CBA.setValue(resultatSelected.getButAway());
        
    }

       
    @FXML
    public void updateResultat() {
        ResultatService resultatService = new ResultatService();

        PartieService partieService = new PartieService();
        if (idPartie==0) {
               Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Vous devez choisir une partie ")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //chercEquip.setFocusTraversable(true);
                          //  chercEquip.setText("");
                        
                          loadData();
                        }
                    });

            notificationbuilder.showError();
        } else {

            resultatService.modifierResulltat(idPartie, CBH.getValue(), CBA.getValue());
            home.setText("Home");
            away.setText("Away");
            CBA.setValue(null);
            CBH.setValue(null);
            
      
          if(resultatService.isBolAB()){
            resultatService.affectationGroupeAB18Eme();
                        
            }
          if(resultatService.isBolCD()){
            resultatService.affectationGroupeCD8Eme();
               
          }
          if(resultatService.isBolEF()){
          resultatService.affectationGroupeEF8Eme();
             
                      }  
          if(resultatService.isBolGH()){
          resultatService.affectationGroupeGH8Eme();
          // bolGH= false ;
          }
          
          if(resultatService.isBol41()){
            resultatService.affectation4eme1();
            // bol41=false ;
          }
          if(resultatService.isBol42()){
            resultatService.affectation4eme2();
          //   bol42=false ; 
          }
          if(resultatService.isBol43()){
            resultatService.affectation4eme3();
          //  bol43=false ; 
          }
          if(resultatService.isBol44()){
            resultatService.affectation4eme4();
          //  bol44=false ; 
          }
          if(resultatService.isBol21()){
            resultatService.affectation2eme1();
          }
         
          if(resultatService.isBol22()){ 
          resultatService.affectation2eme2();
          
          }
          if(resultatService.isBolfinal()){
            resultatService.affectionfinal();
          
          }
            
          //
            loadData();
        }

    }

    public void loadData() {
        ResultatService resultatService = new ResultatService();
        data.addAll(resultatService.listResultats());
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(resultatService.listResultats());
        table.setItems(data);
    }

    @FXML
    public void tousLesMatch() {
        loadData();
    }

    @FXML
    public void matchJouee() {
        ResultatService resultatService = new ResultatService();
        data.addAll(resultatService.listResultatsJoue());
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(resultatService.listResultatsJoue());
        table.setItems(data);

    }

    @FXML
    public void matchNonJoue() {
        ResultatService resultatService = new ResultatService();
        data.addAll(resultatService.listResultatsNonJoue());
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(resultatService.listResultatsNonJoue());
        table.setItems(data);

    }

    private void gestionPartie(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/User.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
  primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }

    @FXML
    private void refrechAnchor(MouseEvent event) {
        loadData();
//        chercEquip.setText("");
        home.setText("Home");
        away.setText("Away");

        CBA.setValue(0);
        CBH.setValue(0);
        table.getSelectionModel().select(null);
        idPartie=0;

    }

    private void interfaceProgramme(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProgrammeMatch.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
  primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void interfacearbe(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Arbre.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
  primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
       @FXML
    private void GererRecompense(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/GestionRecompense.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void StatRecompense(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/StatCadeau.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

   

    @FXML
    private void GererResultat(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/User.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererRecom(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MenuAdmin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererEquipe(ActionEvent event) throws IOException {
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/AffichageListEquipe.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererActu(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/listActualites.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void StatPari(ActionEvent event) throws IOException {
            Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/StatPari.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void decon(ActionEvent event) throws IOException {
          XML x =new XML();
        x.Ecrire("0","0","0","0","0","0","0", 0, "0","0");
     Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }


}
