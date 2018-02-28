/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Equipe;
import Entity.Joueurs;
import Entity.User;
import static Presentation.ListJoueursController.rootP;
import Services.EquipeService;
import Services.JoueurService;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Amouri Aziz
 */
public class ViewAffGroupeController implements Initializable {
    
    private String valeur;
    List<Equipe> le = new ArrayList<>();
    JoueurService SJF = new JoueurService();
    public  Equipe selectedEquipe;
    private TableView<Equipe> TabEquipe;
    //ObservableList<Equipe>TEG;
    List<Joueurs>OJV;
    private ObservableList<Joueurs> OLJ2 = FXCollections.observableArrayList();
   /* private TableColumn<Equipe, String> NE;
    private TableColumn<Equipe, String> Drap;
    private TableColumn<Equipe, String> Ent;
    private TableColumn<Equipe, String> Conti;
    private TableColumn<Equipe, Integer> MJ;
    private TableColumn<Equipe, Integer> MG;
    private TableColumn<Equipe, Integer> MP;
    private TableColumn<Equipe, Integer> MN;
    private TableColumn<Equipe, Integer> BE;
    private TableColumn<Equipe, Integer> BM;
    private TableColumn<Equipe, Integer> PTS;*/
    @FXML
    private JFXComboBox<String> Groupe;
    private JFXButton view;
    @FXML
    private ListView<Label> ListEquipe;
    /*private JFXButton VPlayers;
    private TableView<Joueurs> TabJoueur;
    private TableColumn<Joueurs, String> Nom = new TableColumn<>("NomJoueur");
    private TableColumn<Joueurs, String> Prenom = new TableColumn<>("PrenomJoueur");
    private TableColumn<Joueurs, String> Position = new TableColumn<>("Position");*/
    @FXML
   public static AnchorPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Pane paneE;
    private ObservableList<Equipe> data ;
    private List<Equipe> MyTeamList = new ArrayList<>();
    EquipeService es = new EquipeService();
    @FXML
    private ListView<Label> JoueurListView;
    @FXML
    private PieChart pie;
    @FXML
    private JFXButton stat;
    @FXML
    private Label nmbJeton;
    @FXML
    private JFXTextField username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         XML a = new XML();
        User u;
        try {
            u = a.lire();
            username.setText(u.getUsername());
            nmbJeton.setText("" + u.getJeton());
        } catch (SAXException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        
        String listGroupe[]={"A","B","C","D","E","F","G","H"};    
         Groupe.getItems().addAll(listGroupe);
        //VPlayers.setOnAction(this::afficher);
        valeur = Groupe.getSelectionModel().getSelectedItem();
        /*Nom.setCellValueFactory(new PropertyValueFactory<>("NomJoueur"));
        Prenom.setCellValueFactory(new PropertyValueFactory<>("PrenomJoueur"));
        Position.setCellValueFactory(new PropertyValueFactory<>("Position"));*/
        


        MyTeamList=es.afficherGroupeList(valeur);
        data = FXCollections.observableArrayList();
        MyTeamList.stream().forEach((equipe)->{
            data.add(equipe);
        });
        
         le = es.afficherGroupe(valeur);

        
         rootP = root;
                 try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
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
        
    }
        

   @FXML
    private void View(ActionEvent event) throws IOException {
           ListEquipe.getItems().clear();
  String valeur = Groupe.getSelectionModel().getSelectedItem();
 List<Equipe> le = es.afficherGroupe(valeur);
        data = FXCollections.observableArrayList();
        le.stream().forEach((equipe)->{
            
            Label lbl = new Label(
                             "Nom equipe :"+equipe.getNomEquipe()+ 
                             "\n Entraineur :"+equipe.getEntraineur()+
                             "\n Continent :"+equipe.getContinent()+
                             "\n MatchJouee :"+equipe.getMatchJouee()+
                             "\n MatchNull :"+equipe.getMatchNull()+
                             "\n MatchPerdu :"+equipe.getMatchperdu()+
                             "\n But encaisse :"+equipe.getButEncaisse()+
                             "\n But marque :"+equipe.getButMarque()+
                             "\n MatchGagnee :"+equipe.getMatchGagne()+""
                             
            );
            
            String img=equipe.getDrapeau();
            Image image=new Image(img,150,100,true,true);
            
            lbl.setPrefSize(500, 100);
            
                
                ImageView imageView = new ImageView();
                imageView.imageProperty().unbind();
                imageView.setImage(image);
                imageView.setFitWidth(100);
                imageView.setFitHeight(80);
                ListEquipe.getItems().add(lbl);
                lbl.setGraphic(imageView);
            
            data.add(equipe);
  
        });
        //close(event);
      
    }    

    private void close(ActionEvent event) throws IOException
    {
    
    Parent homePage = FXMLLoader.load(getClass().getResource("ViewAffGroupe.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    
    }

    
    
     public String upload(File file) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath="C:/wamp64/www/PI/images";
        String localPath="localhost";
        String fileName = file.getName();
        fileName=fileName.replace(" ", "_");
        try {
            Path p = file.toPath();
            
            byte[] bytes = Files.readAllBytes(p);
    
            File dir = new File(globalPath);
            if (!dir.exists())
                dir.mkdirs();
            // Create the file on server
            File serverFile = new File(dir.getAbsolutePath()+File.separator + fileName);
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));
            stream.write(bytes);
            stream.close();
            return localPath+"/"+fileName;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ViewAffGroupeController.class.getName()).log(Level.SEVERE, null, ex);
            return "error1";
        } catch (IOException ex) {
            Logger.getLogger(ViewAffGroupeController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        }
    }

    @FXML
    private void afficherJoueurs(MouseEvent event) throws IOException {
        
        JoueurListView.getItems().clear();
        int index = ListEquipe.getSelectionModel().getSelectedIndex();
        selectedEquipe = data.get(index) ;
        
        System.out.println(selectedEquipe.getNomEquipe());
        
        
        OLJ2 = FXCollections.observableArrayList();
        OLJ2= SJF.getJouurs(selectedEquipe.getIDEquipe()) ;
        OLJ2.stream().forEach((joueur)->{
            
            Label lbl = new Label(
                             "Nom :"+joueur.getNomJoueur()+ 
                             "\n prenom :"+joueur.getPrenomJoueur()+
                             "\n Continent :"+joueur.getPosition()+""
            );
            
            
                JoueurListView.getItems().add(lbl);
                
            
                       
        });
        //close(null);
    }
    

    @FXML
    private void statistics(ActionEvent event) {
         
        int index = ListEquipe.getSelectionModel().getSelectedIndex();
         
        selectedEquipe = data.get(index) ;
        
        double moyGagne = (double)(selectedEquipe.getMatchGagne()*100)/selectedEquipe.getMatchJouee();
        double moyPerte = (double)(selectedEquipe.getMatchperdu()*100)/selectedEquipe.getMatchJouee();
        double moyNull = (double)(selectedEquipe.getMatchNull()*100)/selectedEquipe.getMatchJouee();
        
        
        
        ObservableList<Data>list = FXCollections.observableArrayList(
       new PieChart.Data("Match Gagner",moyGagne),
       new PieChart.Data("Match Null",moyPerte),
       new PieChart.Data("Match Perdu",moyNull));
       pie.setData(list);
       

}  

    @FXML
    private void contact(MouseEvent event) throws IOException {
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ContactUS.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
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

