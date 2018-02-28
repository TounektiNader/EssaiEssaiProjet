/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Recompense;
import Entity.User;
import static Presentation.StatPariController.rootP;
import Services.CadeauService;
import Services.RecompenseService;
import Services.ServiceBet;
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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class StatCadeauController implements Initializable {

    @FXML
    private Text pourcentageVoit;
    @FXML
    private Text nombreVoitures;
    @FXML
    private Text nombreTotal1;
    @FXML
    private Text pourcentageTicket;
    @FXML
    private Text nombreTotal2;
    @FXML
    private Text pourcentageBon;
    @FXML
    private Text nombreTotal3;
    @FXML
    private Text pourcentageTele;
    @FXML
    private Text nombrreTel;
    @FXML
    private Text nombreTotal4;
    @FXML
    private BarChart<String,Integer> chartComp;
    @FXML
    private NumberAxis absis;
    @FXML
    private CategoryAxis Axis;
    @FXML
    private PieChart chart;
    @FXML
    private JFXComboBox<String> comboUser;
    @FXML
    private Text nombreTicket;
    @FXML
    private Text nombreBon;
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
    private JFXButton b1;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXButton b12;
    @FXML
    private JFXButton b13;
    @FXML
    private JFXButton b15;
    @FXML
    private JFXButton b14;
    @FXML
    private JFXButton b151;
    @FXML
    private JFXTextField username;
    
    private ObservableList<User> dataUser;
    
    

    /**
     * Initializes the controller class.
     */
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
   // username.setText(value);
   
         Combo com = new Combo();
        comboUser.getItems().addAll(com.fillUserUser());
        
        
        CadeauService cadeauService = new CadeauService();

        int totalCadeau = cadeauService.listCadeau().size();

        int nombreVoitur = cadeauService.listCadeau("Voiture").size();
        nombreVoitures.setText("" + nombreVoitur);

        int nombreTe = cadeauService.listCadeau("Telephone").size();
        nombrreTel.setText("" + nombreTe);
        int nombreBo = cadeauService.listCadeau("Bon_Achat").size();
        nombreBon.setText("" + nombreBo);

        int nombreTick = cadeauService.listCadeau("Ticket").size();
        nombreTicket.setText("" + nombreTick);

        nombreTotal1.setText("/" + totalCadeau);
        nombreTotal2.setText("/" + totalCadeau);
        nombreTotal3.setText("/" + totalCadeau);
        nombreTotal4.setText("/" + totalCadeau);

        pourcentageVoit.setText("" + calculPourcentage(totalCadeau, nombreVoitur));
        pourcentageBon.setText("" + calculPourcentage(totalCadeau, nombreBo));
        pourcentageTele.setText("" + calculPourcentage(totalCadeau, nombreTe));
        pourcentageTicket.setText("" + calculPourcentage(totalCadeau, nombreTick));
        
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
        
        ServiceBet serviceBet = new ServiceBet();
        
         dataUser = serviceBet.GetUser();
         
           XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (User u : dataUser) {
            int nombreCadeau = cadeauService.nombreRecompenseParPersonne(u.getUsername());
            series.getData().add(new XYChart.Data<>(u.getUsername(), nombreCadeau));

        }
        chartComp.getData().add(series);
        

    }

    @FXML
    private void afficherChart(MouseEvent event) {
        
        
        int nbTickets = 0;
        int nbVoitures = 0;
        int nbBon_Achat = 0;
        int nbTelephone = 0;
        
         CadeauService cadeauService = new CadeauService();
         RecompenseService recompenseService = new RecompenseService();
       List<Recompense>recompenses = new ArrayList<Recompense>();
       recompenses = recompenseService.listRecompense(comboUser.getSelectionModel().getSelectedItem());
      
       for(Recompense r : recompenses){
           
           if(r.getCadeau().getCategorie().equals("Ticket")){
               nbTickets = nbTickets+1;
           }else
           if(r.getCadeau().getCategorie().equals("Voiture")){
               nbVoitures = nbVoitures+1;
           }
           if(r.getCadeau().getCategorie().equals("Bon_Achat")){
               nbBon_Achat = nbBon_Achat+1;
           }
           if(r.getCadeau().getCategorie().equals("Telephone")){
               nbTelephone = nbTelephone+1;
           }
          
           
       
       }

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Tickets", nbTickets),
                new PieChart.Data("Voiture", nbVoitures),
                new PieChart.Data("Bon_Achat",nbBon_Achat),
                new PieChart.Data("Telephone",nbTelephone));

        chart.setData(list);
    }

    public double calculPourcentage(int nombreTotal, int nombre) {
        double d = 0;

        d = (((double) nombre * 100) / nombreTotal);
        DecimalFormat df = new DecimalFormat("########.00");
        String str = df.format(d);
        d = Double.parseDouble(str.replace(',', '.'));

        System.out.println(d);

        return d;

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
    private void GererMatch(ActionEvent event) throws IOException {
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
