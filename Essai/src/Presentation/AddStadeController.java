/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteStade;
import Entity.EntiteVille;
import static Presentation.AddCafeController.image;
import static Presentation.AddCafeController.rootP;
import static Presentation.AddCafeController.stage;
import Services.StadeService;
import Services.VilleService;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
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
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

public class AddStadeController implements Initializable {

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
    @FXML
    private VBox mainContainer;
    @FXML
    private ImageView ph;
    @FXML
    private TextField nom;
    @FXML
    private TextField fondation;
    @FXML
    private Button valider;
    @FXML
    private Button close;
    @FXML
    private TextField capacite;
    @FXML
    private TextField equipe;
    @FXML
    private Button photo;
    @FXML
    private TextField coord;
    @FXML
    private Label url;
    @FXML
    private ComboBox<String> combo;
    
    Random rd = new Random(); 
    public int n ; 
    final FileChooser fileChooser = new FileChooser();
    final   File fileSave = new File("C:\\xampp\\htdocs\\java\\images");
    static Image image ; 
    public  static Stage stage ;
    public  String nomFichier ; 
    
    public static Stage getStage() 
    {
        return stage;
    }
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane root;
    
    public static AnchorPane rootP;
    @FXML
    private JFXDrawer drawer;
    
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
        VilleService cs = new VilleService();
        List<String> lscafes = cs.ReturnNames();
        ObservableList<String> lc = FXCollections.observableArrayList();
        lscafes.stream().forEach((j)->{
            lc.add(j);
        });
        combo.setItems(lc);
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }
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
    
    private boolean isInputValid() 
    {
        String errorMessage = "";

        if (nom.getText() == null || nom.getText().length() == 0) 
        {
            errorMessage += "Nom invalide !\n"; 
        }
        if (fondation.getText() == null || fondation.getText().length() == 0 || fondation.getLength()!=4) 
        {
            errorMessage += "Fondation invalide !\n"; 
        }
        if (equipe.getText() == null || equipe.getText().length() == 0) 
        {
            errorMessage += "Equipe invalide !\n"; 
        } 
        if (capacite.getText()== null || capacite.getText().length() == 0) 
        {
            errorMessage += "Capacité invalide !\n"; 
        } 
        if (coord.getText() == null || coord.getText().length() == 0) 
        {
            errorMessage += "Coordonnées invalides !\n"; 
        } 
        if (url.getText()== null || url.getText().length() == 0) 
        {
            errorMessage += "Image invalide !\n"; 
        } 
        if (combo.getValue()== null || combo.getValue().length() == 0) 
        {
            errorMessage += "Ville invalide !\n"; 
        } 
        if (errorMessage.length() == 0) 
        {
            return true;
        } else 
        {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs Invalides");
            alert.setHeaderText("Veuillez remplir les champs");
            alert.setContentText(errorMessage);
            alert.showAndWait();

            return false;
        }
    }    

    @FXML
    private void Addstade(ActionEvent event) throws IOException, SQLException 
    {
        StadeService cs = new StadeService();
        VilleService vs = new VilleService();
        EntiteStade cafe = new EntiteStade();
        int i = vs.ReturnId(combo.getValue());
        System.out.println(i);
        if (isInputValid()) 
        {
        cafe.setNom(nom.getText());
        cafe.setFondation(fondation.getText());
        cafe.setPosition(coord.getText());
        cafe.setCapacite(capacite.getText());
        cafe.setEquipelocale(equipe.getText());
        cafe.setPhoto(url.getText());
        EntiteVille ev = new EntiteVille();
        ev.setIdville(i);
        cafe.setStadeVille(ev);
        
        try
        {
        cs.AddSatde(cafe);
        n=rd.nextInt(10000)+1;
                try 
                { 
                        File nomfichier = new File("C:/xampp/htdocs/java/images/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(ph.getImage(),null), "png", nomfichier);
                        insertionBase(nomFichier+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(AddStadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(AddStadeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Stade ajouté");
            alert.setHeaderText("Stade ajouté avec succès");
            alert.setContentText("Stade "+nom.getText()+" ajouté avec succès");
            alert.showAndWait();
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Stade non ajouté");
                alert.setHeaderText("Echec de l'insertion du stade");
                alert.setContentText("Echec de l'insertion du stade "+nom.getText());
                alert.showAndWait();
                }
        Close(event);
        }
    }
    
    @FXML
    private void Browse()
    {
        
        setExtFilters(fileChooser);
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) 
                {
                  url.setText(file.getAbsolutePath());
                  image = new Image(file.toURI().toString());
        ph.setFitHeight(400);
        ph.setPreserveRatio(true);
        ph.setImage(image);
        ph.setSmooth(true);
        ph.setCache(true);
                    nomFichier = file.getName().substring(0,file.getName().indexOf("."));
          System.out.println(nomFichier);
                }
    }
    
    public void insertionBase(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("java");
                urlb.addSubfolder("insertionImageStade.PHP");
                urlb.addParameter("photostade","http://localhost/java/images/"+nomFile+".png");
                urlb.addParameter("nomstade",nom.getText());
        
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

    @FXML
    private void Close(ActionEvent event) throws IOException
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeStades.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
}
