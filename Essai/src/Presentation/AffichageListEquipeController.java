/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;


import Entity.Equipe;
import Services.EquipeService;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Amouri Aziz
 */
public class AffichageListEquipeController implements Initializable {
    
    @FXML
    private TableColumn<Equipe,String> NE;
    @FXML
    private TableColumn<Equipe,String> E;
    @FXML
    private TableColumn<Equipe,String> Drap;
    @FXML
    private TableColumn<Equipe,String> Conti;
    @FXML
    private TableColumn<Equipe,String> grop;
    @FXML
    private TableColumn<Equipe,Integer> MJ;
    @FXML
    private TableColumn<Equipe,Integer> MG;
    @FXML
    private TableColumn<Equipe,Integer> MP;
    @FXML
    private TableColumn<Equipe,Integer> MN;
    @FXML
    private TableColumn<Equipe,Integer> BE;
    @FXML
    private TableColumn<Equipe,Integer> BM;
    @FXML
    private TableColumn<Equipe,Integer> PTS;
    @FXML
    private TableView<Equipe> tabEquipe;
    ObservableList lise;
    @FXML
    private TableColumn<Equipe, Integer> idEquipe;
    @FXML
    private TextField EN;
    @FXML
    private TextField Drapo;
    @FXML
    private TextField JM;
    @FXML
    private ComboBox<String> Groupa;
    @FXML
    private ComboBox<String> continant;
    @FXML
    private TextField entrain;
    @FXML
    private Button Ajout;
    @FXML
    private Button modif;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String listcontient[]={"Europe","Afrique","Asie","Amerique du nord","Amerique du sud"};
        String listGroupe[]={"A","B","C","D","E","F","G","H"};
          //lise=FXCollections.observableArrayList();
        continant.getItems().addAll(listcontient);
        Groupa.getItems().addAll(listGroupe);
         EquipeService es= new EquipeService();
         lise=es.afficherEquipes();
         idEquipe.setCellValueFactory(new PropertyValueFactory("IDEquipe"));
         NE.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
         E.setCellValueFactory(new PropertyValueFactory("Entraineur"));
         Drap.setCellValueFactory(new PropertyValueFactory("Drapeau"));
         Conti.setCellValueFactory(new PropertyValueFactory("Continent"));
         grop.setCellValueFactory(new PropertyValueFactory("Groupe"));
         MJ.setCellValueFactory(new PropertyValueFactory("Matchjouee"));
         MG.setCellValueFactory(new PropertyValueFactory("MatchNull"));
         MP.setCellValueFactory(new PropertyValueFactory("MatchPerdu"));
         MN.setCellValueFactory(new PropertyValueFactory("MatchNull"));
         BE.setCellValueFactory(new PropertyValueFactory("butEncaisse"));
         BM.setCellValueFactory(new PropertyValueFactory("ButMarque"));
         PTS.setCellValueFactory(new PropertyValueFactory("NombrePoints"));
         tabEquipe.setItems(null);
         tabEquipe.setItems(lise);
    }    
    
    @FXML
        private void valider(ActionEvent event) {
        EquipeService ES=new EquipeService();
       Equipe Eqi=new Equipe();
       Eqi.setNomEquipe(EN.getText());
       Eqi.setEntraineur(entrain.getText());
       Eqi.setDrapeau(Drapo.getText());
       Eqi.setContinent(continant.getSelectionModel().getSelectedItem());
       Eqi.setGroupe(Groupa.getSelectionModel().getSelectedItem());
       Eqi.setMatchJouee(0);
       Eqi.setMatchGagne(0);
       Eqi.setMatchperdu(0);
       Eqi.setMatchNull(0);
       Eqi.setButEncaisse(0);
       Eqi.setButMarque(0);
       Eqi.setNombrePoints(0);
       ES.ajouterEquipe(Eqi);

        view();
        }
        
    @FXML
        public void modifierequipe(ActionEvent event){
            Equipe e1= tabEquipe.getSelectionModel().getSelectedItem();
           
           e1.setNomEquipe(EN.getText());
           e1.setEntraineur(entrain.getText());
           e1.setDrapeau(Drapo.getText());
           e1.setContinent(continant.getSelectionModel().getSelectedItem());
           e1.setGroupe(Groupa.getSelectionModel().getSelectedItem());
           
            
            EquipeService es= new EquipeService();
           // Equipe e2 = new Equipe():
           // e2.setIDEquipe(IDEquipe);
            es.ModifierEquipe(e1);
   
        tabEquipe.refresh();
           // view();
        }

    @FXML
    private void supprimer(ActionEvent event) {
        Equipe e1= tabEquipe.getSelectionModel().getSelectedItem();
                EquipeService eq= new EquipeService();

        eq.supprimerEquipe(e1);
        
        
        
  
  
        view();
    }

    @FXML
    private void tableCliked(MouseEvent event) {
        EquipeService eq= new EquipeService();
        Equipe equipeSelected = tabEquipe.getSelectionModel().getSelectedItem();
        EN.setText(equipeSelected.getNomEquipe());
        entrain.setText(equipeSelected.getEntraineur());
        Drapo.setText(equipeSelected.getDrapeau());
        continant.setValue(equipeSelected.getContinent());
        Groupa.setValue(equipeSelected.getGroupe());
        
    }
    
    public void view()
    {
                EquipeService ES= new EquipeService();

                lise=ES.afficherEquipes();
         idEquipe.setCellValueFactory(new PropertyValueFactory("IDEquipe"));
         NE.setCellValueFactory(new PropertyValueFactory("NomEquipe"));
         E.setCellValueFactory(new PropertyValueFactory("Entraineur"));
         Drap.setCellValueFactory(new PropertyValueFactory("Drapeau"));
         Conti.setCellValueFactory(new PropertyValueFactory("Continent"));
         grop.setCellValueFactory(new PropertyValueFactory("Groupe"));
         MJ.setCellValueFactory(new PropertyValueFactory("MatchJouee"));
         MG.setCellValueFactory(new PropertyValueFactory("MatchNull"));
         MP.setCellValueFactory(new PropertyValueFactory("Matchperdu"));
         MN.setCellValueFactory(new PropertyValueFactory("MatchNull"));
         BE.setCellValueFactory(new PropertyValueFactory("butEncaisse"));
         BM.setCellValueFactory(new PropertyValueFactory("ButMarque"));
         PTS.setCellValueFactory(new PropertyValueFactory("NombrePoints"));
         tabEquipe.setItems(null);
         tabEquipe.setItems(lise);
        
    }

    /**
     *
     */
    
}
