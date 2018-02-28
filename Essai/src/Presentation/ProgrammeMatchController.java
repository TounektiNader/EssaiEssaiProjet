package Presentation;

import Entity.Partie;
import Entity.User;
import static Presentation.ClassementParGroupeController.rootP;
import Services.PartieService;
import Services.ResultatService;
import Utils.Combo;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ProgrammeMatchController {

    private ObservableList<Partie> data;
    @FXML
    private AnchorPane root;
    @FXML
    private ListView<Partie> programmeList;
    @FXML
    private Pane paneE;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Label nmbJeton;
    @FXML
    private JFXTextField username;
    
    public static AnchorPane rootP;
    @FXML
    private JFXComboBox<String> comboGroupe;
    @FXML
    private JFXComboBox<String> comboTour;
    @FXML
    private JFXComboBox<String> comboEquipe;

    /**
     * Initializes the controller class.
     */
    public void initialize() throws SAXException, IOException {

        XML a = new XML();
        User u;
        u = a.lire();
        username.setText(u.getUsername());
        nmbJeton.setText("" + u.getJeton());

        Combo comb = new Combo();
        comboGroupe.getItems().addAll(comb.fillGroupe());
        comboTour.getItems().addAll(comb.fillTour());
        comboEquipe.getItems().addAll(comb.fillEquipe());

        // TODO
        PartieService partieService = new PartieService();
        data = partieService.getPartie();

        System.out.println(data.size());

        programmeList.setItems(data);
        programmeList.setCellFactory(new Callback<ListView<Partie>, ListCell<Partie>>() {
            @Override
            public ListCell<Partie> call(ListView<Partie> param) {
                return new ListViewEventCell();
            }
        });

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

        comboGroupe.setOnAction((event) -> {
            

            data = partieService.partiegroupe(comboGroupe.getSelectionModel().getSelectedItem());

            System.out.println(data.size());

            programmeList.setItems(data);
            programmeList.setCellFactory(new Callback<ListView<Partie>, ListCell<Partie>>() {
                @Override
                public ListCell<Partie> call(ListView<Partie> param) {
                    return new ListViewEventCell();
                }
            });

        });
        
        comboTour.setOnAction((event) -> {

            data = partieService.partiegTour(comboTour.getSelectionModel().getSelectedItem());

            System.out.println(data.size());

            programmeList.setItems(data);
            programmeList.setCellFactory(new Callback<ListView<Partie>, ListCell<Partie>>() {
                @Override
                public ListCell<Partie> call(ListView<Partie> param) {
                    return new ListViewEventCell();
                }
            });

        });
        
        comboEquipe.setOnAction((event) -> {

           

        int idEquipe = partieService.getidEquipe(comboEquipe.getSelectionModel().getSelectedItem());
        data = partieService.partieEquipe(idEquipe);

        System.out.println(data.size());

        programmeList.setItems(data);
        programmeList.setCellFactory(new Callback<ListView<Partie>, ListCell<Partie>>() {
            @Override
            public ListCell<Partie> call(ListView<Partie> param) {
                return new ListViewEventCell();
            }
        });

        }); 
    }

    @FXML
    private void tousLesMatch(MouseEvent event) {

        PartieService partieService = new PartieService();
        data = partieService.getPartie();

        System.out.println(data.size());

        programmeList.setItems(data);
        programmeList.setCellFactory(new Callback<ListView<Partie>, ListCell<Partie>>() {
            @Override
            public ListCell<Partie> call(ListView<Partie> param) {
                return new ListViewEventCell();
            }
        });
    }

    @FXML
    private void matchJouee(MouseEvent event) {
        PartieService partieService = new PartieService();
        data = partieService.partiegJouee();

        System.out.println(data.size());

        programmeList.setItems(data);
        programmeList.setCellFactory(new Callback<ListView<Partie>, ListCell<Partie>>() {
            @Override
            public ListCell<Partie> call(ListView<Partie> param) {
                return new ListViewEventCell();
            }
        });

    }

    @FXML
    private void matchNonJoue(MouseEvent event) {

        PartieService partieService = new PartieService();
        data = partieService.partiegNonJouee();

        System.out.println(data.size());

        programmeList.setItems(data);
        programmeList.setCellFactory(new Callback<ListView<Partie>, ListCell<Partie>>() {
            @Override
            public ListCell<Partie> call(ListView<Partie> param) {
                return new ListViewEventCell();
            }
        });
    }

    @FXML
    private void refrechAnchor(MouseEvent event) {

        PartieService partieService = new PartieService();
        data = partieService.getPartie();

        System.out.println(data.size());

        programmeList.setItems(data);
        programmeList.setCellFactory(new Callback<ListView<Partie>, ListCell<Partie>>() {
            @Override
            public ListCell<Partie> call(ListView<Partie> param) {
                return new ListViewEventCell();
            }
        });

    }



    @FXML
    private void classement(ActionEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ClassementParGroupe.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void QualificationIntef(ActionEvent event) throws IOException {
        
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
    private void contact(MouseEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ContactUS.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void decon(ActionEvent event) throws IOException {
         XML x = new XML();
        x.Ecrire("0", "0", "0", "0", "0", "0", "0", 0, "0", "0");

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void getSelected(MouseEvent event) throws IOException {
            Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/RealTime.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
