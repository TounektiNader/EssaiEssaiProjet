/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import DateStroge.MyConnection;
import Entity.Joueurs;
import Entity.Partie;
import Entity.RealTime;
import Services.EquipeService;
import Services.JoueurService;
import Services.PartieService;
import Services.ServiceRealTime;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hedih
 */
public class liveMatchController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<Partie> table;
    @FXML
    private ComboBox<String> equipe;
    @FXML
    private ComboBox<String> joueurs;
    @FXML
    private TableColumn<Partie, Integer> columnId;
    @FXML
    private TableColumn<Partie, Integer> columnHome;
    @FXML
    private TableColumn<Partie, Integer> columnAway;

    private ObservableList<Partie> data;
    @FXML
    private AnchorPane AnchorPane;
    @FXML
    private TextField temps;
    @FXML
    private TextArea action;
    @FXML
    public void get(){
    
    Partie partie = table.getSelectionModel().getSelectedItem();
      
        int idPartie=partie.getIdMatch();
       
//        equipe.getItems().remove(partie.getAway().getNomEquipe());
//        equipe.getItems().remove(partie.getHome().getNomEquipe());

        PartieService partieService = new PartieService();
        Partie p= new Partie();
        p=partieService.DetailsPartie(idPartie);
        
        equipe.getItems().add(p.getNomEquipeHome());
        equipe.getItems().add(p.getNomEquipeAway());
        
      
       
    }
    @FXML
    private void AfficherJoueur()
    {
       String eq=equipe.getSelectionModel().getSelectedItem();
        JoueurService j= new JoueurService();
        EquipeService e= new EquipeService();
          List<Joueurs>jou= new ArrayList<>();
      
       jou= j.affichersJoueurs(e.rechercheEquipe(eq));
        for (Joueurs v : jou)
        {
            joueurs.getItems().add(v.getIdjoueur()+" "+v.getNomJoueur());
            
        }
       /*equipe.getSelectionModel().clearSelection();
       equipe.getItems().clear();*/
       
    }
    @FXML
    private void Enregistrer()
    {
       ServiceRealTime r= new ServiceRealTime();
       JoueurService j= new JoueurService();
      String id=joueurs.getSelectionModel().getSelectedItem().substring(0,1);
 
      int id2=Integer.parseInt(id);
        System.out.println(action.getText().toString());

       RealTime rt= new RealTime(action.getText(),temps.getText(),table.getSelectionModel().getSelectedItem(),j.affichersJoueur(id2));
       r.ajouterRealTime(rt);
       equipe.getSelectionModel().clearSelection();
       equipe.getItems().clear();
       joueurs.getSelectionModel().clearSelection();
       joueurs.getItems().clear();
       
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       PartieService partieService = new PartieService();
        data = partieService.getPartie();
        columnId.setCellValueFactory(new PropertyValueFactory("idMatch"));
        columnHome.setCellValueFactory(new PropertyValueFactory<>("nomEquipeHome"));
        columnAway.setCellValueFactory(new PropertyValueFactory<>("nomEquipeAway"));
        table.setItems(null);
        table.setItems(data);
        loadData();
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
   
}
