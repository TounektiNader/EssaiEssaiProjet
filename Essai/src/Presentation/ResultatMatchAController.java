package Presentation;

import Entity.Partie;
import Entity.Resultat;
import Services.PartieService;
import Services.ResultatService;
import Services.ServiceBet;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ResultatMatchAController implements Initializable {

    @FXML
    private Button reset;
    @FXML
    private Button saveUser;
    @FXML
    private TableView<Resultat> table;
    @FXML
    private TableColumn<Resultat, Integer> columnId;
    @FXML
    private TableColumn<Resultat, String> columnHome;
    @FXML
    private TableColumn<Resultat, Integer> columnBH;
    @FXML
    private TableColumn<Resultat, String> colAway;
    @FXML
    private TableColumn<Resultat, Integer> columnBA;
    @FXML
    private TextField home;
    @FXML
    private ComboBox<Integer> CBH;
    @FXML
    private TextField away;
    @FXML
    private ComboBox<Integer> CBA;

    private ObservableList<Resultat> data;
    public static int idPartie;
    @FXML
    private JFXButton tele;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Integer tableauEntier[] = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
       
        CBH.getItems().addAll(tableauEntier);
        CBA.getItems().addAll(tableauEntier);

        
        ResultatService resultatService = new ResultatService();
        PartieService partieService = new PartieService();
        data = resultatService.listResultats();
        columnId.setCellValueFactory(new PropertyValueFactory("idPartie"));
        columnHome.setCellValueFactory(new PropertyValueFactory<>("nomEquipeHome"));
        colAway.setCellValueFactory(new PropertyValueFactory<>("nomEquipeAway"));
        columnBH.setCellValueFactory(new PropertyValueFactory<>("butHome"));
        columnBA.setCellValueFactory(new PropertyValueFactory<>("butAway"));
        table.setItems(null);
        table.setItems(data);
    }

    @FXML
    public void getSelectionItem() {

        Resultat resultatSelected = table.getSelectionModel().getSelectedItem();
        idPartie = resultatSelected.getPartie().getIdMatch();
        home.setText(resultatSelected.getNomEquipeHome());
        away.setText(resultatSelected.getNomEquipeAway());
        CBH.setValue(resultatSelected.getButHome());
        CBA.setValue(resultatSelected.getButAway());

    }

    @FXML
    public void updateResultat() {
        ResultatService resultatService = new ResultatService();

        PartieService partieService = new PartieService();
        

        resultatService.modifierResulltat(idPartie, CBH.getValue(), CBA.getValue());
            //
        loadData();

    }

    public void loadData() {
        ResultatService resultatService = new ResultatService();
        data.addAll(resultatService.listResultats());
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(resultatService.listResultats());
        table.setItems(data);
    }

    @FXML
    public void tousLesMatch() {
        loadData();
    }

    @FXML
    public void matchJouee() {
        ResultatService resultatService = new ResultatService();
        data.addAll(resultatService.listResultatsJoue());
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(resultatService.listResultatsJoue());
        table.setItems(data);

    }

    @FXML
    public void matchNonJoue() {
        ResultatService resultatService = new ResultatService();
        data.addAll(resultatService.listResultatsNonJoue());
        if (!data.isEmpty()) {
            data.clear();
        }
        data.addAll(resultatService.listResultatsNonJoue());
        table.setItems(data);

    }

    @FXML
    private void gestionPartie(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/User.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
                 
    final Node source = (Node) event.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    
    }

  
}
