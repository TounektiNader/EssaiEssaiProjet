package Presentation;

import Entity.EntiteVille;
import static Presentation.AddCafeController.image;
import static Presentation.AddCafeController.stage;
import static Presentation.AddStadeController.rootP;
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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class AddVilleController implements Initializable {

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
    private ImageView photoville;
    @FXML
    private ImageView photologo;
    @FXML
    private ImageView photoeq;
    @FXML
    private TextField nom;
    @FXML
    private TextField fondation;
    @FXML
    private Button valider;
    @FXML
    private Button close;
    @FXML
    private TextField population;
    @FXML
    private TextField time;
    @FXML
    private Button photo;
    @FXML
    private TextField equipe;
    @FXML
    private Button logo;
    @FXML
    private TextField coord;
    @FXML
    private Button logoequipe;
    @FXML
    private Label url;
    @FXML
    private Label url1;
    @FXML
    private Label url2;
    
    Random rd = new Random(); 
    public int n ; 
    public int n1 ; 
    public int n2 ; 
    final FileChooser fileChooser = new FileChooser();
    final FileChooser fileChooser1 = new FileChooser();
    final FileChooser fileChooser2 = new FileChooser();
    final   File fileSave = new File("C:\\xampp\\htdocs\\java\\images");
    static Image image ; 
    static Image image1 ; 
    static Image image2 ; 
    public  static Stage stage ;
    public  String nomFichier ; 
    public  String nomFichier1 ; 
    public  String nomFichier2 ; 
    
    public static Stage getStage() 
    {
        return stage;
    }
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private AnchorPane root;
    
    public static AnchorPane rootP;
    
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
        if (time.getText() == null || time.getText().length() == 0) 
        {
            errorMessage += "Timezone invalide !\n"; 
        } 
        if (equipe.getText()== null || equipe.getText().length() == 0) 
        {
            errorMessage += "Equipe invalide !\n"; 
        } 
        if (population.getText() == null || population.getText().length() == 0) 
        {
            errorMessage += "Population invalides !\n"; 
        } 
        if (coord.getText()== null || coord.getText().length() == 0) 
        {
            errorMessage += "Coordonnées invalides !\n"; 
        } 
        if (url.getText()== null || url.getText().length() == 0) 
        {
            errorMessage += "Photo invalide !\n"; 
        } 
        if (url1.getText()== null || url.getText().length() == 0) 
        {
            errorMessage += "Logo ville invalide !\n"; 
        } 
        if (url2.getText()== null || url2.getText().length() == 0) 
        {
            errorMessage += "Logo équipe invalide !\n"; 
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

    @FXML
    private void Addville(ActionEvent event) throws SQLException, IOException 
    {
        VilleService cs = new VilleService();
        EntiteVille cafe = new EntiteVille();
        if (isInputValid()) 
        {
        cafe.setNom(nom.getText());
        cafe.setFondation(fondation.getText());
        cafe.setPopulation(population.getText());
        cafe.setTimezone(time.getText());
        cafe.setCoordonnees(coord.getText());
        cafe.setEquipelocale(equipe.getText());
//        cafe.setPhotoville(url.getText());
//        cafe.setLogoville(url1.getText());
//        cafe.setLogoequipe(url2.getText());
        
        try
        {
        cs.AddVille(cafe);
        n=rd.nextInt(10000)+1;
        n1=rd.nextInt(10000)+1;
        n2=rd.nextInt(10000)+1;
        try 
                { 
                        File nomfichier = new File("C:/xampp/htdocs/java/images/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(photoville.getImage(),null), "png", nomfichier);
                        insertionBase(nomFichier+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(AddVilleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(AddVilleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        try 
                { 
                        File nomfichier1 = new File("C:/xampp/htdocs/java/images/" + nomFichier1+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(photologo.getImage(),null), "png", nomfichier1);
                        insertionBase1(nomFichier1+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(AddVilleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(AddVilleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        try 
                { 
                        File nomfichier2 = new File("C:/xampp/htdocs/java/images/" + nomFichier2+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(photoeq.getImage(),null), "png", nomfichier2);
                        insertionBase2(nomFichier2+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(AddVilleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(AddVilleController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Ville ajoutée");
            alert.setHeaderText("Ville ajoutée avec succès");
            alert.setContentText("Ville "+nom.getText()+" ajoutée avec succès");
            alert.showAndWait();
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ville non ajoutée");
                alert.setHeaderText("Echec de l'insertion de la ville");
                alert.setContentText("Echec de l'insertion de la ville "+nom.getText());
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
        photoville.setFitHeight(150);
        photoville.setPreserveRatio(true);
        photoville.setImage(image);
        photoville.setSmooth(true);
        photoville.setCache(true);
                    nomFichier = file.getName().substring(0,file.getName().indexOf(".")).replaceAll("\\s+"," ");
          System.out.println(nomFichier);
                }
    }
    
    @FXML
    private void Browse1()
    {
        setExtFilters(fileChooser);
                File file = fileChooser1.showOpenDialog(stage);
                if (file != null) 
                {
                  url1.setText(file.getAbsolutePath());
                  image1 = new Image(file.toURI().toString());
        photologo.setFitHeight(150);
        photologo.setPreserveRatio(true);
        photologo.setImage(image1);
        photologo.setSmooth(true);
        photologo.setCache(true);
                    nomFichier1 = file.getName().substring(0,file.getName().indexOf(".")).replaceAll("\\s+"," ");
          System.out.println(nomFichier1);
                }
    }
    
    @FXML
    private void Browse2()
    {
        setExtFilters(fileChooser);
                File file = fileChooser2.showOpenDialog(stage);
                if (file != null) 
                {
                  url2.setText(file.getAbsolutePath());
                  image2 = new Image(file.toURI().toString());
        photoeq.setFitHeight(150);
        photoeq.setPreserveRatio(true);
        photoeq.setImage(image2);
        photoeq.setSmooth(true);
        photoeq.setCache(true);
                    nomFichier2 = file.getName().substring(0,file.getName().indexOf(".")).replaceAll("\\s+"," ");
          System.out.println(nomFichier2);
                }
    }
    
    public void insertionBase(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("java");
                urlb.addSubfolder("insertionImageVille.PHP");
                urlb.addParameter("photoville","http://localhost/java/images/"+nomFile+".png");
                urlb.addParameter("nomville",nom.getText());
        
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
    public void insertionBase1(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("java");
                urlb.addSubfolder("insertionImageVille1.PHP");
                urlb.addParameter("logoville","http://localhost/java/images/"+nomFile+".png");
                urlb.addParameter("nomville",nom.getText());
        
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
    public void insertionBase2(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("java");
                urlb.addSubfolder("insertionImageVille2.PHP");
                urlb.addParameter("logoequipe","http://localhost/java/images/"+nomFile+".png");
                urlb.addParameter("nomville",nom.getText());
        
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
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeVilles.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
}
