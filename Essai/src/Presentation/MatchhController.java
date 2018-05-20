/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Cadeau;
import Entity.EntiteStade;
import Entity.Partie;
import Entity.Stade;
import Entity.User;
import static Presentation.GestionRecompenseController.rootP;
import Services.PartieService;
import Services.ResultatService;
import Services.StadeService;

import Utils.Combo;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.TemporalQueries.localDate;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.Notifications;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class MatchhController implements Initializable {

    @FXML
    private TextField tour;
    @FXML
    private ComboBox group;
    @FXML
    private DatePicker dateMatch;
    @FXML
    private TextField heureMatch;
    @FXML
    private ComboBox home;
    @FXML
    private ComboBox away;
    @FXML
    private Button reset;

    @FXML
    private TableView<Partie> table;
    @FXML
    private TableColumn<Partie, Integer> columnId;
    @FXML
    private TableColumn<Partie, String> columnTour;
    @FXML
    private TableColumn<Partie, String> columnGroup;
    @FXML
    private TableColumn<Partie, String> colDOB;
    @FXML
    private TableColumn<Partie, String> columnHeure;
    @FXML
    private TableColumn<Partie, Integer> columnHome;
    @FXML
    private TableColumn<Partie, Integer> columnAway;

    private ObservableList<Partie> data;
    @FXML
    private TableColumn<Partie, String> colEdit;
    @FXML
    private ComboBox<String> stade;

    @FXML
    private ImageView imageReset;
    @FXML
    private JFXButton btSave;
    @FXML
    private ImageView imageSave;
    @FXML
    private VBox vBoxAjout;
    @FXML
    private JFXButton save;
    @FXML
    static public Label labelLAbel;
    @FXML
    private JFXComboBox<String> comboCherche;

    public static Partie partieSelected = new Partie();
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
    private JFXTextField username;
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
    private ImageView imRec;
    @FXML
    private ImageView imStatPari;
    @FXML
    private ImageView imMatch;
    @FXML
    private ImageView imEquip;
    @FXML
    private ImageView imRecomm;
    @FXML
    private ImageView imActu;
    
    @FXML
    private ImageView imStatReco;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        XML x = new XML();

        User user = new User();
        try {
            user = x.lire();
            username.setText(user.getUsername());
        } catch (SAXException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String tableauEtiquette[] = {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2", "E1", "E2"};
        Combo com = new Combo();
        comboCherche.getItems().addAll(com.fillEquipe());
        group.getItems().addAll(com.fillGroupe());
        home.getItems().addAll(com.fillEquipe());
        away.getItems().addAll(com.fillEquipe());
        stade.getItems().addAll(com.fillStade());

        PartieService partieService = new PartieService();
        data = partieService.getPartie();

        columnId.setCellValueFactory(new PropertyValueFactory("idMatch"));
        columnTour.setCellValueFactory(new PropertyValueFactory("tour"));
        columnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        colDOB.setCellValueFactory(new PropertyValueFactory<>("datematch"));
        columnHeure.setCellValueFactory(new PropertyValueFactory<>("HeurePartie"));
        columnHome.setCellValueFactory(new PropertyValueFactory<>("nomEquipeHome"));
        columnAway.setCellValueFactory(new PropertyValueFactory<>("nomEquipeAway"));
        colEdit.setCellValueFactory(new PropertyValueFactory<>("nomStade"));
        table.setItems(null);
        table.setItems(data);
        loadData();
        vBoxAjout.setVisible(false);
        
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
                b1.setVisible(true);
                b2.setVisible(true);
                b3.setVisible(true);
                b4.setVisible(true);
                b5.setVisible(true);
                b6.setVisible(true);
                b7.setVisible(true);
                
                
                imActu.setVisible(true);
                imEquip.setVisible(true);
                imMatch.setVisible(true);
                imRec.setVisible(true);
                imRecomm.setVisible(true);
                imStatPari.setVisible(true);
                imStatReco.setVisible(true);
                
                
            } else {
                
                drawer.open();
                paneE.setVisible(false);
                b1.setVisible(false);
                b2.setVisible(false);
                b3.setVisible(false);
                b4.setVisible(false);
                b5.setVisible(false);
                b6.setVisible(false);
                b7.setVisible(false);
                
                
                 imActu.setVisible(false);
                imEquip.setVisible(false);
                imMatch.setVisible(false);
                imRec.setVisible(false);
                imRecomm.setVisible(false);
                imStatPari.setVisible(false);
                imStatReco.setVisible(false);
            }
        });

    }

    @FXML
    public void ajoutPartie() throws SQLException {
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);
        b7.setVisible(false);
        
          imActu.setVisible(false);
                imEquip.setVisible(false);
                imMatch.setVisible(false);
                imRec.setVisible(false);
                imRecomm.setVisible(false);
                imStatPari.setVisible(false);
                imStatReco.setVisible(false);

        ResultatService resultatService = new ResultatService();

        System.out.println(partieSelected.getIdMatch());
        //partieSelected = table.getSelectionModel().getSelectedItem();
        if (partieSelected.getIdMatch() == 0) {
        if((home.getSelectionModel().getSelectedItem().equals("Choissez Equipe"))||(away.getSelectionModel().getSelectedItem().equals("Choissez Equipe"))||(group.getSelectionModel().getSelectedItem().equals("Choissez Groupe"))||heureMatch.getText().equals("Heure Match")||(stade.getSelectionModel().getSelectedItem().equals("Choissez Stade")))    
        {
           Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Attention vous devez Remplir tous les champs !! ")
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
            if(home.getSelectionModel().getSelectedItem().equals(away.getSelectionModel().getSelectedItem())){
            
                   Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Attention vous avez Choissi même équipes !! ")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                        }
                    });

            notificationbuilder.showError();
                
            }else{
            System.out.println(partieSelected.getIdMatch());
            //   labelLAbel.setText("Ajout Match");
            LocalDate date = dateMatch.getValue();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String d = date.format(formatter);
            System.out.println(d);

            String comTour = tour.getText();
            String comGroup = group.getSelectionModel().getSelectedItem().toString();
            String comAway = away.getSelectionModel().getSelectedItem().toString();
            String comHome = home.getSelectionModel().getSelectedItem().toString();
            String comStade = stade.getSelectionModel().getSelectedItem().toString();

            PartieService partieService = new PartieService();

            partieService.ajoutPartie(comGroup, d, heureMatch.getText(), comTour, partieService.getIdStade(comStade), partieService.getidEquipe(comHome), partieService.getidEquipe(comAway));
            int id = partieService.geIdPartie(partieService.getidEquipe(comHome), partieService.getidEquipe(comAway));
            System.out.println(id);
            resultatService.ajoutResultat(id);
            loadData();

            vBoxAjout.setVisible(false);
            table.getSelectionModel().select(null);

            b1.setVisible(true);
            b2.setVisible(true);
            b3.setVisible(true);
            b4.setVisible(true);
            b5.setVisible(true);
            b6.setVisible(true);
            b7.setVisible(true);
            
            
                imActu.setVisible(true);
                imEquip.setVisible(true);
                imMatch.setVisible(true);
                imRec.setVisible(true);
                imRecomm.setVisible(true);
                imStatPari.setVisible(true);
                imStatReco.setVisible(true);
            }
        } } else {
//   labelLAbel.setText("Modifier Match");
//          Date d = new SimpleDateFormat("yyyy-MM-dd").parse(dateLocal.toString());
//Date date = Date.from(instant);
//Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            PartieService partieService = new PartieService();
     StadeService stadeService = new StadeService();
            LocalDate date = dateMatch.getValue();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String d = date.format(formatter);
//        System.out.println(d);

            partieSelected.setTour(tour.getText());
            partieSelected.setGroup(group.getSelectionModel().getSelectedItem().toString());
            partieSelected.setAway(partieService.getEquipe(away.getSelectionModel().getSelectedItem().toString()));
            partieSelected.setHome(partieService.getEquipe(home.getSelectionModel().getSelectedItem().toString()));
        partieSelected.setStade(stadeService.FindStade(this.stade.getSelectionModel().getSelectedItem().toString()));

            partieSelected.setHeurePartie(heureMatch.getText());

            partieService.updatPartie(partieSelected, d);
            vBoxAjout.setVisible(false);
            table.refresh();
            table.getSelectionModel().select(null);

            partieSelected = new Partie();

            b1.setVisible(true);
            b2.setVisible(true);
            b3.setVisible(true);
            b4.setVisible(true);
            b5.setVisible(true);
            b6.setVisible(true);
            b7.setVisible(true);
            
            
                imActu.setVisible(true);
                imEquip.setVisible(true);
                imMatch.setVisible(true);
                imRec.setVisible(true);
                imRecomm.setVisible(true);
                imStatPari.setVisible(true);
                imStatReco.setVisible(true);
        }
    }

    public void loadData() {
        PartieService partieService = new PartieService();
        data.addAll(partieService.getPartie());
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(partieService.getPartie());
        table.setItems(data);
    }

    @FXML
    private void reset(ActionEvent event) {
        // partieSelected = table.getSelectionModel().getSelectedItem();
        if (partieSelected.getIdMatch() == 0) {

            home.setValue("Choissez Equipe");
            away.setValue("Choissez Equipe");
            group.setValue("Choissez Groupe");
            heureMatch.setText("Heure Match");

            stade.setValue("Choissez Stade");
            dateMatch.setValue(LocalDate.now());

        } else {

            btSave.setDisable(false);
            tour.setDisable(false);
            group.setDisable(false);
            dateMatch.setDisable(false);
            heureMatch.setDisable(false);
            stade.setDisable(false);

            away.setDisable(false);
            home.setDisable(false);

            Image image = new Image("/images/reset.png");
            imageReset.setImage(image);
            reset.setText("Reset");
            partieSelected = new Partie();
        }
    }

    @FXML
    private void deleteUsers(ActionEvent event) {
        List<Partie> Partie = table.getSelectionModel().getSelectedItems();
        Partie partie = table.getSelectionModel().getSelectedItem();
        int id = partie.getIdMatch();
        PartieService ps = new PartieService();
        ResultatService rs = new ResultatService();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(null);
        alert.setContentText("êtes vous sur de supprimer ce match?");
        Optional<ButtonType> action = alert.showAndWait();

        if (action.get() == ButtonType.OK) {
            rs.supprimerRsultat(id);
            ps.supprimer(id);

        }

        loadData();
        vBoxAjout.setVisible(false);

    }

    @FXML
    private void RechercheEquipe(ActionEvent event) {

        PartieService ps = new PartieService();
        //  int id = ps.getidEquipe(chercEquip.getText());
        int id = ps.getidEquipe(comboCherche.getSelectionModel().getSelectedItem());

        if (id == 0) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//		alert.setTitle("Confirmation Dialog");
//		alert.setHeaderText(null);
//                alert.setContentText("Vos devez remplir un champ valide");
//		Optional<ButtonType> action = alert.showAndWait();
//		
//		if(action.get() == ButtonType.OK){ 

            Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Vous devez remplir un champ valide")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            //chercEquip.setFocusTraversable(true);
                            //  chercEquip.setText("");
                            comboCherche.setValue("choisir Equipe");
                            loadData();
                        }
                    });

            notificationbuilder.showError();
        } else {
            data = ps.partieEquipe(id);
            columnId.setCellValueFactory(new PropertyValueFactory("idMatch"));
            columnTour.setCellValueFactory(new PropertyValueFactory("tour"));
            columnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
            colDOB.setCellValueFactory(new PropertyValueFactory<>("datematch"));
            columnHeure.setCellValueFactory(new PropertyValueFactory<>("HeurePartie"));
            columnHome.setCellValueFactory(new PropertyValueFactory<>("nomEquipeHome"));
            columnAway.setCellValueFactory(new PropertyValueFactory<>("nomEquipeAway"));
            colEdit.setCellValueFactory(new PropertyValueFactory<>("nomStade"));
            table.setItems(null);
            table.setItems(data);
        }

    }

    @FXML
    public void getSelectionItem() {

        partieSelected = table.getSelectionModel().getSelectedItem();
        System.out.println(partieSelected.getIdMatch());
        vBoxAjout.setVisible(true);
        tour.setDisable(true);
        group.setDisable(true);
        dateMatch.setDisable(true);
        heureMatch.setDisable(true);
        stade.setDisable(true);

        away.setDisable(true);
        home.setDisable(true);

        Image image = new Image("/images/edit.png");
        imageReset.setImage(image);
        reset.setText("Update");
        btSave.setDisable(true);

        tour.setText(partieSelected.getTour());
        away.setValue(partieSelected.getAway().getNomEquipe());
        home.setValue(partieSelected.getHome().getNomEquipe());

        heureMatch.setText(partieSelected.getHeurePartie());
        group.setValue(partieSelected.getGroup());
        stade.setValue(partieSelected.getStade().getNom());
        dateMatch.setValue(convertDatetoLocalDate(partieSelected.getDatematch()));

        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);
        b7.setVisible(false);
        
          imActu.setVisible(false);
                imEquip.setVisible(false);
                imMatch.setVisible(false);
                imRec.setVisible(false);
                imRecomm.setVisible(false);
                imStatPari.setVisible(false);
                imStatReco.setVisible(false);
    }

    public LocalDate convertDatetoLocalDate(Date date) {
        return date.toLocalDate();
    }

    @FXML
    private void enregister(ActionEvent event) {
        vBoxAjout.setVisible(true);
        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);
        b7.setVisible(false);
        
          imActu.setVisible(false);
                imEquip.setVisible(false);
                imMatch.setVisible(false);
                imRec.setVisible(false);
                imRecomm.setVisible(false);
                imStatPari.setVisible(false);
                imStatReco.setVisible(false);
                
        if (partieSelected.getIdMatch() == 0) {

            home.setValue("Choissez Equipe");
            away.setValue("Choissez Equipe");
            group.setValue("Choissez Groupe");
            heureMatch.setText("Heure Match");

            stade.setValue("Choissez Stade");
            dateMatch.setValue(LocalDate.now());

        }

    }

    @FXML
    private void refrechAnchor(MouseEvent event) {
        loadData();
//        chercEquip.setText("");
        partieSelected = new Partie();

        vBoxAjout.setVisible(false);
        comboCherche.setValue("Equipes");
        b1.setVisible(true);
        b2.setVisible(true);
        b3.setVisible(true);
        b4.setVisible(true);
        b5.setVisible(true);
        b6.setVisible(true);
        b7.setVisible(true);
        
               imActu.setVisible(true);
                imEquip.setVisible(true);
                imMatch.setVisible(true);
                imRec.setVisible(true);
                imRecomm.setVisible(true);
                imStatPari.setVisible(true);
                imStatReco.setVisible(true);
         
        
        
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
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ResultatMatchA.fxml"));
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

}
