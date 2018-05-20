/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Services.CafeService;
import Services.HotelService;
import Services.RestoService;
import Services.StadeService;
import Services.VilleService;
import Utils.XML;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class MenuAdminController implements Initializable {

    @FXML
    private VBox mainContainer;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Button bt_menu;
    @FXML
    private Button hotels;
    @FXML
    private Button cafes;
    @FXML
    private Button restos;
    @FXML
    private Button stades;
    @FXML
    private Button villes;
    
    public static AnchorPane rootP;
    @FXML
    private Label v1;
    @FXML
    private Label v2;
    @FXML
    private Label v3;
    @FXML
    private Label v4;
    @FXML
    private Label v5;
    @FXML
    private BarChart<String, Number> chrt;
    
    final static String Villes = "Villes";
    final static String Stades = "Stades";
    final static String Cafes = "Cafes";
    final static String Hotels = "Hotels";
    final static String Restaurants = "Restaurants";

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
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
            } else {
                drawer.open();
            }
        });
        
        try 
        {
            CafeService cs = new CafeService();
            HotelService hs = new HotelService();
            RestoService rs = new RestoService();
            StadeService ss = new StadeService();
            VilleService vs = new VilleService();
            
            v1.setText(""+vs.Nmbr());
            v2.setText(""+ss.Nmbr());
            v3.setText(""+cs.Nmbr());
            v4.setText(""+hs.Nmbr());
            v5.setText(""+rs.Nmbr());
        } 
        catch (SQLException e) 
        {
            System.out.println(e.getMessage());
        }
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        
        chrt.setTitle("Statistiques");
//        xAxis.setLabel("Country");       
//        yAxis.setLabel("Value");
        
        int v10 = Integer.parseInt(v1.getText());
        int v11 = Integer.parseInt(v2.getText());
        int v12 = Integer.parseInt(v3.getText());
        int v13 = Integer.parseInt(v4.getText());
        int v14 = Integer.parseInt(v5.getText());
        
        XYChart.Series series1 = new XYChart.Series();
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        series1.setName(date); 
        
        
        series1.getData().add(new XYChart.Data(Villes, v10));
        series1.getData().add(new XYChart.Data(Stades, v11));
        series1.getData().add(new XYChart.Data(Cafes, v12));
        series1.getData().add(new XYChart.Data(Hotels, v13));
        series1.getData().add(new XYChart.Data(Restaurants, v14));   
        
        chrt.getData().addAll(series1);
        chrt.lookupAll(".default-color0.chart-bar")
            .forEach(n -> n.setStyle("-fx-bar-fill: #83a1fc;"));
        
//        Timeline tl = new Timeline();
//        tl.getKeyFrames().add(
//        new KeyFrame(Duration.millis(500), 
//        new EventHandler<ActionEvent>() 
//        {
//        @Override public void handle(ActionEvent actionEvent) 
//        {
//            for (XYChart.Series<String, Number> series : chrt.getData()) 
//            {
//                for (XYChart.Data<String, Number> data : series.getData()) 
//                {
//                    data.setYValue(Math.random() * 1000);
//                }
//            }
//        }
//        }
//        ));
//        tl.setCycleCount(Animation.INDEFINITE);
//        tl.setAutoReverse(true);
//        tl.play();
        
    }    

    @FXML
    private void menu(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("MenuAdmin.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void hotels(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeHotels.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void cafes(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeCafes.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void restos(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeRestos.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void stades(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeStades.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void villes(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeVilles.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    private void Listrestos(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeRestos.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);
        System.out.println("resto");
        app_stage.showAndWait();
    }

    private void Listvilles(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeVilles.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    private void Liststades(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeStades.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    private void Listhotels(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeHotels.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    private void Listcafes(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeCafes.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
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
    
}
