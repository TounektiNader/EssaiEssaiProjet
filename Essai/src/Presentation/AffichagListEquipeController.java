/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Equipe;
import Entity.User;
import static Presentation.ListJoueursController.rootP;
import Services.EquipeService;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Amouri Aziz
 */
public class AffichagListEquipeController implements Initializable {
    
    String path="";
        Stage mainStage;

    ObservableList lise;
    private TableColumn<Equipe, Integer> idEquipe;
    @FXML
    private TextField EN;
    @FXML
    private JFXButton Drapo;
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
    @FXML
    private ListView<Equipe> ListEquipe;
    @FXML
    public static AnchorPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private Pane paneE;
        @FXML
    private ImageView img;
        @FXML
    private Label url;
        
    private ObservableList<Equipe> data ;
    EquipeService es = new EquipeService();

    
         Random rd = new Random(); 
    public int n ; 
    final FileChooser fileChooser = new FileChooser();
    final   File fileSave = new File("C:\\wamp64\\www\\PI\\images");
    static Image image ; 
    public  static Stage stage ;
    public  String nomFichier ; 
    
    public static Stage getStage() 
    {
        return stage;
    }
    @FXML
    private JFXTextField username;

    

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
        
        
        
        String listcontient[]={"Europe","Afrique","Asie","Amerique du nord","Amerique du sud"};
        String listGroupe[]={"A","B","C","D","E","F","G","H"};
          //lise=FXCollections.observableArrayList();
        continant.getItems().addAll(listcontient);
        Groupa.getItems().addAll(listGroupe);

        List<Equipe> le = es.afficherEquipes();
        data = FXCollections.observableArrayList();
        le.stream().forEach((j)->{
            data.add(j);
                       
        });
        ListEquipe.setItems(data);
        ListEquipe.setCellFactory(new Callback<ListView<Equipe>, ListCell<Equipe>>() {
      @Override
       public ListCell<Equipe> call(ListView<Equipe> param) {
          return new ListCellEquipe();
      }
  });
        rootP = root;

        try {
            VBox box = FXMLLoader.load(getClass().getResource("SidePanelContent.fxml"));
            drawer.setSidePane(box);
        } catch (IOException ex) {
          //  Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
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
    
    
    public void afficher()
    {
        
        List<Equipe> le = es.afficherEquipes();
        data = FXCollections.observableArrayList();
        le.stream().forEach((j)->{
            data.add(j);
                       
        });
        ListEquipe.setItems(data);
        ListEquipe.setCellFactory(new Callback<ListView<Equipe>, ListCell<Equipe>>() {
      @Override
       public ListCell<Equipe> call(ListView<Equipe> param) {
          return new ListCellEquipe();
      }
  });
    }
    
        @FXML
    private void Browse(ActionEvent event) throws MalformedURLException {
        
        
        FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(img.getScene().getWindow());
        if (file != null) {
            Image image = new Image(file.toURI().toString(), 388 , 309, true, true);
            img.imageProperty().unbind();
            img.setImage(image);
            
        }
        path = file.toURI().toURL().toString();
        /*
         setExtFilters(fileChooser);
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) 
                {
                  url.setText(file.getAbsolutePath());
                  image = new Image(file.toURI().toString());
        img.setFitHeight(200);
        img.setPreserveRatio(true);
        img.setImage(image);
        img.setSmooth(true);
        img.setCache(true);
                    nomFichier = file.getName().substring(0,file.getName().indexOf(".")).replaceAll("\\s+"," ");
          System.out.println(nomFichier);
                }*/
    }

    
    @FXML
        private void valider(ActionEvent event) throws IOException {
            
        EquipeService ES=new EquipeService();
       Equipe Eqi=new Equipe();
       Eqi.setNomEquipe(EN.getText());
       Eqi.setEntraineur(entrain.getText());
       //Eqi.setDrapeau(Drapo.getText());
       Eqi.setContinent(continant.getSelectionModel().getSelectedItem());
       Eqi.setGroupe(Groupa.getSelectionModel().getSelectedItem());
       Eqi.setMatchJouee(0);
       Eqi.setMatchGagne(0);
       Eqi.setMatchperdu(0);
       Eqi.setMatchNull(0);
       Eqi.setButEncaisse(0);
       Eqi.setButMarque(0);
       Eqi.setNombrePoints(0);
       Eqi.setDrapeau(path);
        System.out.println("-----------------------");
        if ((EN.getText() == null)||(entrain.getText()==null)||(continant.getSelectionModel().getSelectedItem()==null)||(Groupa.getSelectionModel().getSelectedItem()==null)) {
                    System.out.println("/////////////////");

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Acune Sélection");
            alert.setHeaderText("Aucune Equipe");
            alert.setContentText("Veuillez ajouter une equipe");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.initOwner(mainStage);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Ajout equipe");
            alert.setContentText("Veuillez Confirmer l'ajout");
            alert.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    ES.ajouterEquipe(Eqi);
                }
            });
        }
            //close(event);
        Parent homePage = FXMLLoader.load(getClass().getResource("AffichageListEquipe.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
        
        }
        
    @FXML
        public void modifierequipe(ActionEvent event) throws IOException{
      

            Equipe e1= ListEquipe.getSelectionModel().getSelectedItem();
          
           e1.setNomEquipe(EN.getText());
           e1.setEntraineur(entrain.getText());
            e1.setDrapeau(path);
            e1.setContinent(continant.getSelectionModel().getSelectedItem());
           e1.setGroupe(Groupa.getSelectionModel().getSelectedItem());
         
            es.ModifierEquipe(e1);
   
            afficher();
           // view();*/
        }

    @FXML
    private void supprimer(ActionEvent event) throws IOException {
            Equipe E = ListEquipe.getSelectionModel().getSelectedItem();
            es.supprimerEquipe(E);
            afficher();
           
            
            
            
    }

    @FXML
    private void tableCliked(MouseEvent event) {
        EquipeService eq= new EquipeService();
        Equipe equipeSelected = ListEquipe.getSelectionModel().getSelectedItem();
       EN.setText(equipeSelected.getNomEquipe());
        entrain.setText(equipeSelected.getEntraineur());
        Image image = new Image(equipeSelected.getDrapeau());
        img.setImage(image);
        
        continant.setValue(equipeSelected.getContinent());
        Groupa.setValue(equipeSelected.getGroupe());
        
    }


         private void close(ActionEvent event) throws IOException
    {
    
    Parent homePage = FXMLLoader.load(getClass().getResource("AffichageListEquipe.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    
    }
 
         
    public void insertionBase(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("java");
                urlb.addSubfolder("insertionImageCadeau.PHP");
                urlb.addParameter("Drapeau","http://localhost/PI/images/"+nomFile+".png");
                urlb.addParameter("NomEquipe",EN.getText());
        
                String url = urlb.getURL();
                this.url.setText(url);
                System.out.println(url);
        
        URL URl_Serveur = new URL(url);
                HttpURLConnection conx = (HttpURLConnection) URl_Serveur.openConnection();
                conx.setRequestMethod("POST");
                conx.setDoOutput(true);
                OutputStream os = conx.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));

 
      writer.flush();
                writer.close();
                conx.connect();


                int reponse = conx.getResponseCode();

                if (reponse == HttpsURLConnection.HTTP_OK) 
                {
                    InputStream is = conx.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String ligne = "", resultat = "";

                    while ((ligne = br.readLine()) != null) 
                    {

                        resultat += ligne;
                    }

    }
                
}  
    
    private void setExtFilters(FileChooser chooser) 
    {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }
    
   


   

    @FXML
    private void interfaceJoueur(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ListJoueurs.fxml"));
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

    class URLBuilder 
    {
    private StringBuilder folders, params;
    private String connType, host;

    void setConnectionType(String conn) 
    {
        connType = conn;
    }

    URLBuilder()
    {
        folders = new StringBuilder();
        params = new StringBuilder();
    }

    URLBuilder(String host) 
    {
        this();
        this.host = host;
    }

    void addSubfolder(String folder) 
    {
        folders.append("/");
        folders.append(folder);
    }

    void addParameter(String parameter, String value)
    {
        if(params.toString().length() > 0){params.append("&");}
        params.append(parameter);
        params.append("=");
        params.append(value);
    }

    String getURL() throws URISyntaxException, MalformedURLException
    {
        URI uri = new URI(connType, host, folders.toString(),
                params.toString(), null);
        return uri.toURL().toString();
    }

    String getRelativeURL() throws URISyntaxException, MalformedURLException
    {
        URI uri = new URI(null, null, folders.toString(), params.toString(), null);
        return uri.toString();
    }
    }
}
