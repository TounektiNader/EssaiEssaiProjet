/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Bet;
import Entity.Cadeau;
import Entity.Equipe;
import Entity.Partie;
import Entity.User;
import static Presentation.GestionRecompenseController.rootP;
import static Presentation.MatchAparierController.idPArtie;
import Services.PartieService;
import Services.ServiceBet;
import Services.ServiceUser;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import org.xml.sax.SAXException;

/**
 *
 * @author Nader
 */
public class ParierrrrController implements Initializable {

    private Label label;
    @FXML
    private TableView<Partie> table;
    @FXML
    private TableColumn<Partie, String> columDate;
    @FXML
    private TableColumn<Partie, Equipe> ColumHome;
    @FXML
    private TableColumn<Partie, Equipe> ColumAway;

    private ObservableList<Partie> data;

    private Button parier;
    @FXML
    private TableColumn<Partie, String> columnGroup;
    @FXML
    private JFXRadioButton home;
    @FXML
    private ToggleGroup toggle;
    @FXML
    private JFXRadioButton away;
    @FXML
    private JFXRadioButton eg;
    @FXML
    private JFXButton reset;
    @FXML
    private PieChart pieHome;
    @FXML
    private PieChart pieAway;
    @FXML
    private Label labelHome;
    @FXML
    private Label labelAway;
    public static String choix = "";
    @FXML
    private JFXButton sv;
    @FXML
    private ImageView rs;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Pane paneE;

    public static AnchorPane rootP;
    @FXML
    private ImageView imhome;
    @FXML
    private ImageView imAway;
    @FXML
    private Label nmbJeton;
    @FXML
    private JFXTextField username;

    @FXML
    public void userClickedOnTable() {

        Partie resultatSelected = table.getSelectionModel().getSelectedItem();
        PartieService partieService = new PartieService();

        Equipe e = partieService.getEquipe(resultatSelected.getHome().getIDEquipe());
        Equipe e2 = partieService.getEquipe(resultatSelected.getAway().getIDEquipe());

        home.setText(resultatSelected.getHome().getNomEquipe());//a changer apres nom 
        away.setText(resultatSelected.getAway().getNomEquipe());
        Image imHom = new Image(resultatSelected.getHome().getDrapeau());
        Image imAwa = new Image(resultatSelected.getAway().getDrapeau());
        imAway.setImage(imAwa);
        imhome.setImage(imHom);
        home.setDisable(false);
        away.setDisable(false);
        eg.setDisable(false);
        sv.setDisable(false);
        rs.setDisable(false);

        labelHome.setText("Home :  " + e.getNomEquipe());

        ObservableList<Data> list = FXCollections.observableArrayList(
                new PieChart.Data("MatchNull", e.getMatchNull()),
                new PieChart.Data("MatchGagner*", e.getMatchGagne()),
                new PieChart.Data("MatchPerdu", e.getMatchperdu()));
        pieHome.setData(list);
        labelAway.setText("Away :  " + e2.getNomEquipe());
        ObservableList<Data> list2 = FXCollections.observableArrayList(
                new PieChart.Data("MatchNull", e2.getMatchNull()),
                new PieChart.Data("MatchGagner*", e2.getMatchGagne()),
                new PieChart.Data("MatchPerdu", e2.getMatchperdu()));
        pieAway.setData(list2);
    }

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

        PartieService partieService = new PartieService();
        data = partieService.getPartieAparier();

        columDate.setCellValueFactory(new PropertyValueFactory<>("datematch"));
        columnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        ColumHome.setCellValueFactory(new PropertyValueFactory<>("nomEquipeHome"));
        ColumAway.setCellValueFactory(new PropertyValueFactory<>("nomEquipeAway"));
        table.setItems(null);
        table.setItems(data);

//        this.parier.setDisable(true);
        //  labes.setText("");
        toggle = new ToggleGroup();
        this.home.setToggleGroup(toggle);
        this.away.setToggleGroup(toggle);
        this.eg.setToggleGroup(toggle);
        home.setDisable(true);
        away.setDisable(true);
        eg.setDisable(true);
        sv.setDisable(true);
        rs.setDisable(true);

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

    private void importContenu(ActionEvent event) {

        PartieService partieService = new PartieService();
        data = partieService.getPartieAparier();

        columDate.setCellValueFactory(new PropertyValueFactory<>("datematch"));
        columnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        ColumHome.setCellValueFactory(new PropertyValueFactory<>("nomHome"));
        ColumAway.setCellValueFactory(new PropertyValueFactory<>("nomAway"));
        table.setItems(null);
        table.setItems(data);
    }

    public void PariView(ActionEvent event) throws IOException {

        //access the controller and call a method
        Partie partie = table.getSelectionModel().getSelectedItem();

        ServiceBet serviceBet = new ServiceBet();
        String equipeHome = partie.getHome().getNomEquipe();
        String equipeAway = partie.getAway().getNomEquipe();

        PartieService partieService = new PartieService();

        if (serviceBet.jouerUnMatch(username.getText(), partie.getIdMatch())) {
            Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Vous n'avez pas le droit de jouer !!!!")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

            notificationbuilder.showError();

        } else {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Presentation/MatchAparier.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            MatchAparierController controller = loader.getController();
            controller.initData(table.getSelectionModel().getSelectedItem());
            Stage primaryStage = new Stage();
            primaryStage.setScene(tableViewScene);
            primaryStage.show();

        }

        //This line gets the Stage information
    }

    private void listesBon(ActionEvent event) {
        PartieService partieService = new PartieService();
        data = FXCollections.observableArrayList();

        Partie resultatSelected = table.getSelectionModel().getSelectedItem();
        Equipe e = partieService.getEquipe(resultatSelected.getHome().getIDEquipe());
        Equipe e2 = partieService.getEquipe(resultatSelected.getAway().getIDEquipe());

        ObservableList<Data> list = FXCollections.observableArrayList(
                new PieChart.Data("MatchNull", e.getMatchNull()),
                new PieChart.Data("MatchGagner*", e.getMatchGagne()),
                new PieChart.Data("MatchPerdu", e.getMatchperdu()));
        pieHome.setData(list);

        ObservableList<Data> list2 = FXCollections.observableArrayList(
                new PieChart.Data("MatchNull", e2.getMatchNull()),
                new PieChart.Data("MatchGagner*", e2.getMatchGagne()),
                new PieChart.Data("MatchPerdu", e2.getMatchperdu()));
        pieAway.setData(list2);
    }

    @FXML
    public void changedRadio() {
        if (this.toggle.getSelectedToggle().equals(this.home)) {
            //  labes.setText(home.getText());
            choix = home.getText();
        } else if (this.toggle.getSelectedToggle().equals(this.away)) {
            // labes.setText(away.getText());
            choix = away.getText();
        } else if (this.toggle.getSelectedToggle().equals(this.eg)) {
            // labes.setText(eg.getText());
            choix = eg.getText();
        }

    }

    @FXML
    private void parier(ActionEvent event) {

        Partie resultatSelected = table.getSelectionModel().getSelectedItem();
        ServiceBet serviceBet = new ServiceBet();

        PartieService partieService = new PartieService();
        if (choix.equals("")) {

            Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Vous deveez choisir un résultat!!!!")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

            notificationbuilder.showError();
        } else if (serviceBet.jouerUnMatch(username.getText(), resultatSelected.getIdMatch())) {
            Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Vous n'avez pas le droit de jouer !!!!")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

            notificationbuilder.showError();
        } else {

            int id = resultatSelected.getIdMatch();
            System.out.println(id);
            ServiceBet service = new ServiceBet();

            service.parier(id, username.getText(), choix);
            ServiceUser serviceUser = new ServiceUser();
            User u = serviceUser.getUser(username.getText());
            XML x = new XML();
            x.Ecrire(u.getUsername(), u.getNom(), u.getPrenom(), u.getMdp(), u.getRole(), u.getMail(), u.getStatus(), u.getJeton(), u.getNationalite(),u.getNum());

            nmbJeton.setText("" + u.getJeton());
            
             Notifications notificationbuilder = Notifications.create()
                    .title("Succès")
                    .text("Votre Pari a été bien enregistrer ! ")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

            notificationbuilder.showConfirm();

            choix = "";
        }
    }

    @FXML
    private void reset(ActionEvent event) {
        sv.setDisable(true);
        home.setDisable(true);
        away.setDisable(true);
        eg.setDisable(true);
        home.setText("Home");
        away.setText("Away");

    }

    @FXML
    private void ouvrirFiche(ActionEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MesParis.fxml"));

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
