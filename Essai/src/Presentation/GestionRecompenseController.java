/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Cadeau;
import Entity.Partie;
import Entity.Resultat;
import static Presentation.ResultatMatchAController.idPartie;
import Services.CadeauService;
import Services.PartieService;
import Services.ResultatService;
import com.jfoenix.controls.JFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class GestionRecompenseController implements Initializable {
    
      final FileChooser fileChooser = new FileChooser();
        static Image image ; 
    
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
    private Label nombreVoiture;
    @FXML
    private Label nombreBon;
    @FXML
    private Label nombreTicket;
    @FXML
    private TableView<Cadeau> table;
    @FXML
    private TableColumn<Cadeau, String> colCat;
    @FXML
    private TableColumn<Cadeau, String> colType;
    @FXML
    private TableColumn<Cadeau, Integer> colJeton;
    @FXML
    private TextField jeton;
    @FXML
    private TextField txtType;
    
    private ObservableList<Cadeau> data;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton edit;
    @FXML
    private ImageView imagEdit;
    @FXML
    private ComboBox<String> comboCat;
    
    String categorie[] = {"Voiture","Ticket","Telephone","Bon_Achat"};
    @FXML
    private Label labes;
    @FXML
    private ImageView imageV;
    @FXML
    private JFXButton join;
    @FXML
    private JFXButton tele1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboCat.getItems().addAll(categorie);
        CadeauService cadeauService = new CadeauService();
        
        data = cadeauService.listCadeau();
        colCat.setCellValueFactory(new PropertyValueFactory("categorie"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colJeton.setCellValueFactory(new PropertyValueFactory<>("jeton"));
        
        table.setItems(null);
        table.setItems(data);
        
      remplierLabel();
        
    }
    
    public void remplierLabel(){
    CadeauService cadeauService = new CadeauService();
       int nombreVoitur = cadeauService.listCadeau("Voiture").size();
        nombreVoiture.setText("" + nombreVoitur);
        int nombreTe = cadeauService.listCadeau("Telephone").size();
        nombreTel.setText("" + nombreTe);
        int nombreBo = cadeauService.listCadeau("Bon_Achat").size();
        nombreBon.setText("" + nombreBo);
        int nombreTick = cadeauService.listCadeau("Ticket").size();
        this.nombreTicket.setText("" + nombreTick);
    }
    @FXML
    public void getSelectionItem() {
        
        comboCat.setDisable(true);
        jeton.setDisable(true);
        txtType.setDisable(true);
        
        Cadeau resultatSelected = table.getSelectionModel().getSelectedItem();
        comboCat.setValue(resultatSelected.getCategorie());       
        txtType.setText(resultatSelected.getType());
        jeton.setText("" + resultatSelected.getJeton());
        
        save.setDisable(true);
        delete.setDisable(false);
        edit.setDisable(false);
        
    }
    
    @FXML
    private void listesTickets(ActionEvent event) {
        CadeauService cadeauService = new CadeauService() ; 
        
       data=cadeauService.listCadeau("Ticket");
       table.setItems(null);
        table.setItems(data);
    }
    
    @FXML
    private void listesVoitures(ActionEvent event) {
      CadeauService cadeauService = new CadeauService() ; 
        
       data=cadeauService.listCadeau("Voiture");
       table.setItems(null);
        table.setItems(data);
    }
    
    @FXML
    private void listesTelephones(ActionEvent event) {
      CadeauService cadeauService = new CadeauService() ; 
        
       data=cadeauService.listCadeau("Telephone");
       table.setItems(null);
        table.setItems(data);
    }
    
    @FXML
    private void listesBon(ActionEvent event) {
          CadeauService cadeauService = new CadeauService() ; 
        
       data=cadeauService.listCadeau("Bon_Achat");
       table.setItems(null);
        table.setItems(data);
    }
    
    public void loadData() {
        CadeauService cadeauService = new CadeauService();
        
        data.addAll(cadeauService.listCadeau());
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(cadeauService.listCadeau());
        table.setItems(data);
    }
    
    @FXML
    private void enregister(ActionEvent event) {
        comboCat.setValue("");
       // String cat = txtCat.getText();
        String cat = comboCat.getSelectionModel().getSelectedItem();
        String type = txtType.getText();
        String ji = jeton.getText();
        
        
        if((cat.equals(""))||(type.equals(""))||(ji.equals(""))){
             Notifications notificationbuilder = Notifications.create()
                    .title("Alerte")
                    .text("Vous devez remplir tous les champs!!!!")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                             
                              
                        }
                    });

            notificationbuilder.showError();
        
        }else{
            int jet = Integer.parseInt(ji);
        CadeauService cadeauService = new CadeauService();
        cadeauService.ajoutCadeau(cat, type, jet, type);
        
        
        loadData();
        remplierLabel();}
    }
    
    @FXML
    private void supprimer(ActionEvent event) {
        Cadeau resultatSelected = table.getSelectionModel().getSelectedItem();
        
        CadeauService cadeauService = new CadeauService();
        cadeauService.supprimer(resultatSelected.getIdCadeau());
        loadData();
    }
    
    @FXML
    private void update(ActionEvent event) {
        
        comboCat.setDisable(false);
        jeton.setDisable(false);
        txtType.setDisable(false);
        
          Cadeau resultatSelected = table.getSelectionModel().getSelectedItem();
        edit.setText("Save");
        
        Image img = new Image("/images/save.png");
        imagEdit.setImage(img);
        delete.setDisable(true);
         String ji = jeton.getText();
        int jet = Integer.parseInt(ji);
        
        CadeauService cadeauService = new CadeauService();
        cadeauService.modifierCadeau(resultatSelected.getIdCadeau(),  comboCat.getSelectionModel().getSelectedItem(), txtType.getText(),jet,"qsdqsdqs");
        loadData();
        
    }

    @FXML
    private void actualiser(ActionEvent event) {
        loadData();
    }

    @FXML
    private void joindre(ActionEvent event) {
        Stage stage =(Stage) join.getScene().getWindow();
         setExtFilters(fileChooser);
        File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                  labes.setText(file.getAbsolutePath());
                  image = new Image(file.toURI().toString());
       
         
        imageV.setFitHeight(400);
        imageV.setPreserveRatio(true);
        imageV.setImage(image);
        imageV.setSmooth(true);
        imageV.setCache(true);
       
        
    }
    }
      
    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    @FXML
    private void afficheRecompense(ActionEvent event) throws IOException {
           Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/recompense.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
                 
    final Node source = (Node) event.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    
    }
    
}
