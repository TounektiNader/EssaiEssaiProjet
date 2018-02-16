/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Equipe;
import Entity.Partie;
import Entity.Resultat;
import Services.EquipeService;
import Services.PartieService;
import Utils.Pari;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class ClassementParGroupeController implements Initializable {

    @FXML
    private JFXButton tick;
    @FXML
    private JFXButton voi;
    @FXML
    private JFXButton tele;
    @FXML
    private JFXButton achat;
    @FXML
    private TabPane tablePane;
    @FXML
    private Tab tabA;
    @FXML
    private Tab tabB;
    @FXML
    private Tab tabC;
    @FXML
    private Tab tabD;
    @FXML
    private Tab tabE;
    @FXML
    private Tab tabF;
    @FXML
    private Tab tabG;
    @FXML
    private Tab tabH;
    @FXML
    private TableView<Equipe> table;
    @FXML
    private TableColumn<Equipe, String> nomEquipe;
    @FXML
    private TableColumn<Equipe, Integer> PointEquipe;

     private ObservableList<Equipe> data;
    @FXML
    private TableColumn<Equipe,Integer> matchJoue;
    @FXML
    private TableColumn<Equipe,Integer> matchGagne;
    @FXML
    private TableColumn<Equipe,Integer> matchNull;
    @FXML
    private TableColumn<Equipe,Integer> matchPerdu;
    @FXML
    private TableColumn<Equipe,Integer> butMarque;
    @FXML
    private TableColumn<Equipe,Integer> bueEncaisse;
    @FXML
    private TableView<Equipe> table1;
    @FXML
    private TableColumn<Equipe,Integer> nomEquipe1;
    @FXML
    private TableColumn<Equipe,Integer> matchJoue1;
    @FXML
    private TableColumn<Equipe,Integer> matchGagne1;
    @FXML
    private TableColumn<Equipe,Integer> matchNull1;
    @FXML
    private TableColumn<Equipe,Integer> matchPerdu1;
    @FXML
    private TableColumn<Equipe,Integer> butMarque1;
    @FXML
    private TableColumn<Equipe,Integer> bueEncaisse1;
    @FXML
    private TableColumn<Equipe,Integer> PointEquipe1;
    @FXML
    private TableView<Equipe> table2;
    @FXML
    private TableColumn<Equipe,String> nomEquipe2;
    @FXML
    private TableColumn<Equipe,Integer> matchJoue2;
    @FXML
    private TableColumn<Equipe,Integer> matchGagne2;
    @FXML
    private TableColumn<Equipe,Integer> matchNull2;
    @FXML
    private TableColumn<Equipe,Integer> matchPerdu2;
    @FXML
    private TableColumn<Equipe,Integer> butMarque2;
    @FXML
    private TableColumn<Equipe,Integer> bueEncaisse2;
    @FXML
    private TableColumn<Equipe,Integer> PointEquipe2;
    @FXML
    private TableView<Equipe> table3;
    @FXML
    private TableColumn<Equipe,String> nomEquipe3;
    @FXML
    private TableColumn<Equipe,Integer> matchJoue3;
    @FXML
    private TableColumn<Equipe,Integer> matchGagne3;
    @FXML
    private TableColumn<Equipe,Integer> matchNull3;
    @FXML
    private TableColumn<Equipe,Integer> matchPerdu3;
    @FXML
    private TableColumn<Equipe,Integer> butMarque3;
    @FXML
    private TableColumn<Equipe,Integer> bueEncaisse3;
    @FXML
    private TableColumn<Equipe,Integer> PointEquipe3;
    @FXML
    private TableView<Equipe> table4;
    @FXML
    private TableColumn<Equipe,String> nomEquipe4;
    @FXML
    private TableColumn<Equipe,Integer> matchJoue4;
    @FXML
    private TableColumn<Equipe,Integer> matchGagne4;
    @FXML
    private TableColumn<Equipe,Integer> matchNull4;
    @FXML
    private TableColumn<Equipe,Integer> matchPerdu4;
    @FXML
    private TableColumn<Equipe,Integer> butMarque4;
    @FXML
    private TableColumn<Equipe,Integer> bueEncaisse4;
    @FXML
    private TableColumn<Equipe,Integer> PointEquipe4;
    @FXML
    private TableView<Equipe> table43;
    @FXML
    private TableColumn<Equipe,String> nomEquipe43;
    @FXML
    private TableColumn<Equipe,Integer> matchJoue43;
    @FXML
    private TableColumn<Equipe,Integer> matchGagne43;
    @FXML
    private TableColumn<Equipe,Integer> matchNull43;
    @FXML
    private TableColumn<Equipe,Integer> matchPerdu43;
    @FXML
    private TableColumn<Equipe,Integer> butMarque43;
    @FXML
    private TableColumn<Equipe,Integer> bueEncaisse43;
    @FXML
    private TableColumn<Equipe,Integer> PointEquipe43;
    @FXML
    private TableView<Equipe> table41;
    @FXML
    private TableColumn<Equipe,String> nomEquipe41;
    @FXML
    private TableColumn<Equipe,Integer> matchJoue41;
    @FXML
    private TableColumn<Equipe,Integer> matchGagne41;
    @FXML
    private TableColumn<Equipe,Integer> matchNull41;
    @FXML
    private TableColumn<Equipe,Integer> matchPerdu41;
    @FXML
    private TableColumn<Equipe,Integer> butMarque41;
    @FXML
    private TableColumn<Equipe,Integer> bueEncaisse41;
    @FXML
    private TableColumn<Equipe,Integer> PointEquipe41;
    @FXML
    private TableView<Equipe> table42;
    @FXML
    private TableColumn<Equipe,String> nomEquipe42;
    @FXML
    private TableColumn<Equipe,Integer> matchJoue42;
    @FXML
    private TableColumn<Equipe,Integer> matchGagne42;
    @FXML
    private TableColumn<Equipe,Integer> matchNull42;
    @FXML
    private TableColumn<Equipe,Integer> matchPerdu42;
    @FXML
    private TableColumn<Equipe,Integer> butMarque42;
    @FXML
    private TableColumn<Equipe,Integer> bueEncaisse42;
    @FXML
    private TableColumn<Equipe,Integer> PointEquipe42;
    
    EquipeService equipeService = new EquipeService();
    @FXML
    private JFXListView<Label> listView;
        private ObservableList<Partie> dataPartie;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        remplirList("A");
        // TODO
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
    }

    @FXML
    private void listGroupA(Event event) {
    
  
    data=equipeService.afficherEquipes("A");
        
        Collections.sort(data);
         
        nomEquipe.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
        PointEquipe.setCellValueFactory(new PropertyValueFactory<>("NombrePoints"));
        butMarque.setCellValueFactory(new PropertyValueFactory<>("ButMarque"));
        bueEncaisse.setCellValueFactory(new PropertyValueFactory<>("butEncaisse"));
        matchJoue.setCellValueFactory(new PropertyValueFactory<>("MatchJouee"));
        matchGagne.setCellValueFactory(new PropertyValueFactory<>("MatchGagne"));
        matchPerdu.setCellValueFactory(new PropertyValueFactory<>("Matchperdu"));
        matchNull.setCellValueFactory(new PropertyValueFactory<>("MatchNull"));
        
        
        table.setItems(null);
        table.setItems(data);
        
        
      remplirList("A");
    }

    @FXML
    private void listGroupB(Event event) {
        
         data=equipeService.afficherEquipes("B");
        
        Collections.sort(data);
         
        nomEquipe1.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
        PointEquipe1.setCellValueFactory(new PropertyValueFactory<>("NombrePoints"));
        butMarque1.setCellValueFactory(new PropertyValueFactory<>("ButMarque"));
        bueEncaisse1.setCellValueFactory(new PropertyValueFactory<>("butEncaisse"));
        matchJoue1.setCellValueFactory(new PropertyValueFactory<>("MatchJouee"));
        matchGagne1.setCellValueFactory(new PropertyValueFactory<>("MatchGagne"));
        matchPerdu1.setCellValueFactory(new PropertyValueFactory<>("Matchperdu"));
        matchNull1.setCellValueFactory(new PropertyValueFactory<>("MatchNull"));
        
        
        
        remplirList("B");
        
    }

    @FXML
    private void listGroupC(Event event) {
          data=equipeService.afficherEquipes("C");
        
        Collections.sort(data);
         
        nomEquipe2.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
        PointEquipe2.setCellValueFactory(new PropertyValueFactory<>("NombrePoints"));
        butMarque2.setCellValueFactory(new PropertyValueFactory<>("ButMarque"));
        bueEncaisse2.setCellValueFactory(new PropertyValueFactory<>("butEncaisse"));
        matchJoue2.setCellValueFactory(new PropertyValueFactory<>("MatchJouee"));
        matchGagne2.setCellValueFactory(new PropertyValueFactory<>("MatchGagne"));
        matchPerdu2.setCellValueFactory(new PropertyValueFactory<>("Matchperdu"));
        matchNull2.setCellValueFactory(new PropertyValueFactory<>("MatchNull"));
        
        
        table2.setItems(null);
        table2.setItems(data);
         remplirList("C");
    }

    @FXML
    private void listGroupD(Event event) {
         data=equipeService.afficherEquipes("D");
        
        Collections.sort(data);
         
        nomEquipe3.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
        PointEquipe3.setCellValueFactory(new PropertyValueFactory<>("NombrePoints"));
        butMarque3.setCellValueFactory(new PropertyValueFactory<>("ButMarque"));
        bueEncaisse3.setCellValueFactory(new PropertyValueFactory<>("butEncaisse"));
        matchJoue3.setCellValueFactory(new PropertyValueFactory<>("MatchJouee"));
        matchGagne3.setCellValueFactory(new PropertyValueFactory<>("MatchGagne"));
        matchPerdu3.setCellValueFactory(new PropertyValueFactory<>("Matchperdu"));
        matchNull3.setCellValueFactory(new PropertyValueFactory<>("MatchNull"));
        
        
        table3.setItems(null);
        table3.setItems(data);
         remplirList("D");
    }

    @FXML
    private void listGroupE(Event event) {
        data=equipeService.afficherEquipes("E");
        
        Collections.sort(data);
         
        nomEquipe43.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
        PointEquipe43.setCellValueFactory(new PropertyValueFactory<>("NombrePoints"));
        butMarque43.setCellValueFactory(new PropertyValueFactory<>("ButMarque"));
        bueEncaisse43.setCellValueFactory(new PropertyValueFactory<>("butEncaisse"));
        matchJoue43.setCellValueFactory(new PropertyValueFactory<>("MatchJouee"));
        matchGagne43.setCellValueFactory(new PropertyValueFactory<>("MatchGagne"));
        matchPerdu43.setCellValueFactory(new PropertyValueFactory<>("Matchperdu"));
        matchNull43.setCellValueFactory(new PropertyValueFactory<>("MatchNull"));

        
        table43.setItems(null);
        table43.setItems(data);
         remplirList("E");
    }

    @FXML
    private void listGroupF(Event event) {
       
  
    data=equipeService.afficherEquipes("F");
        
        Collections.sort(data);
         
        nomEquipe4.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
        PointEquipe4.setCellValueFactory(new PropertyValueFactory<>("NombrePoints"));
        butMarque4.setCellValueFactory(new PropertyValueFactory<>("ButMarque"));
        bueEncaisse4.setCellValueFactory(new PropertyValueFactory<>("butEncaisse"));
        matchJoue4.setCellValueFactory(new PropertyValueFactory<>("MatchJouee"));
        matchGagne4.setCellValueFactory(new PropertyValueFactory<>("MatchGagne"));
        matchPerdu4.setCellValueFactory(new PropertyValueFactory<>("Matchperdu"));
        matchNull4.setCellValueFactory(new PropertyValueFactory<>("MatchNull"));
        
        
        table4.setItems(null);
        table4.setItems(data);
         remplirList("F");
    }

    @FXML
    private void listGroupG(Event event) {
        data=equipeService.afficherEquipes("G");
        
        Collections.sort(data);
         
        nomEquipe41.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
        PointEquipe41.setCellValueFactory(new PropertyValueFactory<>("NombrePoints"));
        butMarque41.setCellValueFactory(new PropertyValueFactory<>("ButMarque"));
        bueEncaisse41.setCellValueFactory(new PropertyValueFactory<>("butEncaisse"));
        matchJoue41.setCellValueFactory(new PropertyValueFactory<>("MatchJouee"));
        matchGagne41.setCellValueFactory(new PropertyValueFactory<>("MatchGagne"));
        matchPerdu41.setCellValueFactory(new PropertyValueFactory<>("Matchperdu"));
        matchNull41.setCellValueFactory(new PropertyValueFactory<>("MatchNull"));
        
        
        table41.setItems(null);
        table41.setItems(data);
         remplirList("G");
    }

    @FXML
    private void listGroupH(Event event) {
        data=equipeService.afficherEquipes("H");
        
        Collections.sort(data);
         
        nomEquipe42.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
        PointEquipe42.setCellValueFactory(new PropertyValueFactory<>("NombrePoints"));
        butMarque42.setCellValueFactory(new PropertyValueFactory<>("ButMarque"));
        bueEncaisse42.setCellValueFactory(new PropertyValueFactory<>("butEncaisse"));
        matchJoue42.setCellValueFactory(new PropertyValueFactory<>("MatchJouee"));
        matchGagne42.setCellValueFactory(new PropertyValueFactory<>("MatchGagne"));
        matchPerdu42.setCellValueFactory(new PropertyValueFactory<>("Matchperdu"));
        matchNull42.setCellValueFactory(new PropertyValueFactory<>("MatchNull"));
        
        
        table42.setItems(null);
        table42.setItems(data);
        remplirList("H");
    }
   public void remplirList(String groupe){
   
     listView.getItems().clear();
   Partie partie = new Partie();
        PartieService partieService = new PartieService();
        dataPartie = partieService.partiegroupe(groupe);
       
        
        for (int i = 0; i < dataPartie.size(); i++) {

            
                Date date  = dataPartie.get(i).getDatematch();
                Label labelDetail = new Label(date.toString());
                System.out.println(labelDetail);
                Label lbl = new Label(date.toString()+"  "+ dataPartie.get(i).getHome().getNomEquipe()+"   VS    "+dataPartie.get(i).getAway().getNomEquipe());
                
             
              
            listView.getItems().add(lbl);
         }
   } 
    
    
}

    