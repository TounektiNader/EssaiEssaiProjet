/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteVille;
import Services.VilleService;
import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.sun.prism.PhongMaterial.MapType;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


public class Ville1LocController implements Initializable, MapComponentInitializedListener {
    
    @FXML
    private Button button;
    
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    double longitude ;
    double latitude ;
    
    public void initialize(EntiteVille obj) 
    {
        ville = obj;
        String[]latlong = obj.getCoordonnees().split(",");
        latitude = Double.parseDouble(latlong[0]);
        longitude = Double.parseDouble(latlong[1]);
        mapView.addMapInializedListener(this);
        System.out.println(latitude+" "+longitude);
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
                   
        map = mapView.createMap(mapOptions);

        //Add markers to the map
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(pos);
        
        
        
        Marker posMarker = new Marker(markerOptions1);
        
        
        map.addMarker( posMarker );
        
        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>"+ville.getNom()+"</h2>"
                                +ville.getCoordonnees()+"<br>");

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        fredWilkeInfoWindow.open(map, posMarker);
    }   
    
      EntiteVille ville = new EntiteVille();
      

    @Override
    public void initialize(URL location, ResourceBundle resources) 
    {
        
    }
      
}