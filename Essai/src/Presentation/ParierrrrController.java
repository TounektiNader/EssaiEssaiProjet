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
import static Presentation.MatchAparierController.idPArtie;
import Services.PartieService;
import Services.ServiceBet;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 *
 * @author Nader
 */
public class ParierrrrController implements Initializable {

    private Label label;
    @FXML
    private Button btAffiche;
    @FXML
    private TableView<Partie> table;
    @FXML
    private TableColumn<Partie, String> columDate;
    @FXML
    private TableColumn<Partie, Equipe> ColumHome;
    @FXML
    private TableColumn<Partie, Equipe> ColumAway;

    private ObservableList<Partie> data;

    @FXML
    private Button parier;
    @FXML
    private TableColumn<Partie, String> columnGroup;
    @FXML
    private JFXButton tick;
    @FXML
    private JFXButton voi;
    @FXML
    private JFXButton tele;
    @FXML
    private JFXButton achat;
    @FXML
    private Label nombreTel;
    @FXML
    private Label nombreBon;
    @FXML
    private Label nombreTicket;
    @FXML
    private Label nombreVoiture;
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
    public static String choix="";
    @FXML
    private JFXButton sv;
    @FXML
    private ImageView rs;

    @FXML
    public void userClickedOnTable() {
        
        
           Partie resultatSelected = table.getSelectionModel().getSelectedItem();
         PartieService partieService = new PartieService();
                
    Equipe e = partieService.getEquipe(resultatSelected.getHome().getIDEquipe());
    Equipe e2 = partieService.getEquipe(resultatSelected.getAway().getIDEquipe());
    
        home.setText(resultatSelected.getHome().getNomEquipe());//a changer apres nom 
        away.setText(resultatSelected.getAway().getNomEquipe());
          home.setDisable(false);
        away.setDisable(false);
        eg.setDisable(false);
        sv.setDisable(false);
        rs.setDisable(false);
      
          labelHome.setText("Home :  "+e.getNomEquipe());
        
          ObservableList<Data>  list = FXCollections.observableArrayList(
            new PieChart.Data("MatchNull",e.getMatchNull()),
            new PieChart.Data("MatchGagner*",e.getMatchGagne()),
            new PieChart.Data("MatchPerdu",e.getMatchperdu()));
            pieHome.setData(list);
            labelAway.setText("Away :  "+e2.getNomEquipe());
               ObservableList<Data>  list2 = FXCollections.observableArrayList(
            new PieChart.Data("MatchNull",e2.getMatchNull()),
            new PieChart.Data("MatchGagner*",e2.getMatchGagne()),
            new PieChart.Data("MatchPerdu",e2.getMatchperdu()));
            pieAway.setData(list2);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        PartieService partieService = new PartieService();
        data = partieService.getPartie();
       
        
        columDate.setCellValueFactory(new PropertyValueFactory<>("datematch"));
        columnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        ColumHome.setCellValueFactory(new PropertyValueFactory<>("nomEquipeHome"));
        ColumAway.setCellValueFactory(new PropertyValueFactory<>("nomEquipeAway"));
        table.setItems(null);
        table.setItems(data);
        
        this.parier.setDisable(true);
        
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

    }

    @FXML
    private void importContenu(ActionEvent event) {

        PartieService partieService = new PartieService();
        data = partieService.getPartie();

        columDate.setCellValueFactory(new PropertyValueFactory<>("datematch"));
        columnGroup.setCellValueFactory(new PropertyValueFactory<>("group"));
        ColumHome.setCellValueFactory(new PropertyValueFactory<>("nomHome"));
        ColumAway.setCellValueFactory(new PropertyValueFactory<>("nomAway"));
        table.setItems(null);
        table.setItems(data);
    }

    @FXML
    public void PariView(ActionEvent event) throws IOException {

        //access the controller and call a method
        Partie partie = table.getSelectionModel().getSelectedItem();

        ServiceBet serviceBet = new ServiceBet();
         String equipeHome = partie.getHome().getNomEquipe();
         String equipeAway = partie.getAway().getNomEquipe();
         
         PartieService partieService = new PartieService();
         
        if (serviceBet.jouerUnMatch("titou",partie.getIdMatch() )) {
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

    @FXML
    private void listesTickets(ActionEvent event) {
    }

    @FXML
    private void listesVoitures(ActionEvent event) {
    }

    @FXML
    private void listesTelephones(ActionEvent event) {
    }

    @FXML
    private void listesBon(ActionEvent event) {
        PartieService partieService = new PartieService();
                data = FXCollections.observableArrayList();
//    Equipe e = partieService.getEquipe(1);
//        
//     
//        
//          ObservableList<Data>  list = FXCollections.observableArrayList(
//            new PieChart.Data("MatchNull",e.getMatchNull()),
//            new PieChart.Data("MatchGagner*",e.getMatchGagne()),
//            new PieChart.Data("MatchPerdu",e.getMatchperdu()));
//            pieHome.setData(list);
Partie resultatSelected = table.getSelectionModel().getSelectedItem();
     Equipe e = partieService.getEquipe(resultatSelected.getHome().getIDEquipe());
    Equipe e2 = partieService.getEquipe(resultatSelected.getAway().getIDEquipe());
        
     
        
          ObservableList<Data>  list = FXCollections.observableArrayList(
            new PieChart.Data("MatchNull",e.getMatchNull()),
            new PieChart.Data("MatchGagner*",e.getMatchGagne()),
            new PieChart.Data("MatchPerdu",e.getMatchperdu()));
            pieHome.setData(list);
            
               ObservableList<Data>  list2 = FXCollections.observableArrayList(
            new PieChart.Data("MatchNull",e2.getMatchNull()),
            new PieChart.Data("MatchGagner*",e2.getMatchGagne()),
            new PieChart.Data("MatchPerdu",e2.getMatchperdu()));
            pieAway.setData(list2);
    }

     @FXML
    public void changedRadio() {
        if (this.toggle.getSelectedToggle().equals(this.home)) {
          //  labes.setText(home.getText());
            choix = home.getText();
        } else if (this.toggle.getSelectedToggle().equals(this.away)) {
           // labes.setText(away.getText());
            choix=away.getText();
        } else if (this.toggle.getSelectedToggle().equals(this.eg)) {
           // labes.setText(eg.getText());
            choix=eg.getText();
        }

    }

    @FXML
    private void parier(ActionEvent event) {
        
         Partie resultatSelected = table.getSelectionModel().getSelectedItem();
         ServiceBet serviceBet = new ServiceBet();
        
         
         PartieService partieService = new PartieService();
         if(choix.equals("")){
         
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
             
            notificationbuilder.showError();}
         
         else{
        if (serviceBet.jouerUnMatch("titou",resultatSelected.getIdMatch() )) {
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
        }else{
         
         
         
     int id=    resultatSelected.getIdMatch();
        System.out.println(id);
          ServiceBet service = new ServiceBet();
             
          service.parier(id, "titou", choix);
          choix="";
    }
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
   

    
  
  

}
