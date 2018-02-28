/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteCafe;
import Entity.EntiteResto;
import static Presentation.AddCafeController.rootP;
import Services.CafeService;
import Services.RestoService;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class Show_RestoController implements Initializable, MapComponentInitializedListener
{

    @FXML
    private Button bt_menu;
    @FXML
    private VBox mainContainer;
    @FXML
    private Label details;
    @FXML
    private Label cafe_name;
    @FXML
    private ImageView imgcafe;
    @FXML
    private Label nom2;
    @FXML
    private Label coord;
    @FXML
    private GoogleMapView mapview;
    
    private GoogleMap map;
    
    double longitude ;
    
    double latitude ;

   public static EntiteResto caffe ;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    
    public static AnchorPane rootP;

    public  void init(EntiteResto caf)
    {
        caffe = new EntiteResto();
   
        caffe=caf;
    }

    
    
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
    }    

    @FXML
    private void menu(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("MenuUser.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    EntiteResto v;
    
    void setVille(EntiteResto object) throws SQLException 
    {
       
        javafx.scene.image.Image im = new javafx.scene.image.Image(object.getPhoto());
        cafe_name.setText(object.getNom());
        nom2.setText(object.getNom());
        details.setText(object.getDetails());
        details.setMaxWidth(200);
        details.setWrapText(true);
        coord.setText(object.getPosition());
        imgcafe.setImage(im);
        ville = object;
        String[]latlong = object.getPosition().split(",");
        latitude = Double.parseDouble(latlong[0]);
        longitude = Double.parseDouble(latlong[1]);
        mapview.addMapInializedListener(this);
        System.out.println(latitude+" "+longitude);

        RestoService cs = new RestoService();
        List<EntiteResto> lscafes = cs.FindRestoVille(object.getNom());
        ObservableList<EntiteResto> lc = FXCollections.observableArrayList();
        lscafes.stream().forEach((j)->{
            lc.add(j);
        });

    }
    
     @Override
    public void mapInitialized() 
    {
        LatLong pos = new LatLong(latitude,longitude);

        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(latitude,longitude))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(12);
                   
        map = mapview.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(pos);
        
        
        
        Marker posMarker = new Marker(markerOptions1);
        
        
        map.addMarker( posMarker );
        
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>"+ville.getNom()+"</h2>"
                                +ville.getPosition()+"<br>");

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, posMarker);
    }
    
    EntiteResto ville = new EntiteResto();

    
}
