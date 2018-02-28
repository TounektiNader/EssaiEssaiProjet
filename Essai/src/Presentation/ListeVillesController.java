/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.EntiteVille;
import static Presentation.AddCafeController.rootP;
import static Presentation.AddVilleController.image;
import static Presentation.AddVilleController.stage;
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
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
public class ListeVillesController implements Initializable {

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
    private TableView<EntiteVille> table_ville;
    @FXML
    private TableColumn<EntiteVille, String> nom;
    @FXML
    private TableColumn<EntiteVille, String> fondation;
    @FXML
    private TableColumn<EntiteVille, String> population;
    @FXML
    private TableColumn<EntiteVille, String> time;
    @FXML
    private TableColumn<EntiteVille, String> equipe;
    @FXML
    private TableColumn<EntiteVille, String> coord;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private TextField population1;
    @FXML
    private TextField time1;
    @FXML
    private Button photo;
    @FXML
    private TextField equipe1;
    @FXML
    private Button logo;
    @FXML
    private TextField coord1;
    @FXML
    private ImageView photoville;
    @FXML
    private ImageView photologo;
    @FXML
    private ImageView photoeq;
    @FXML
    private TextField nom1;
    @FXML
    private TextField fondation1;
    @FXML
    private Button logoequipe;
    @FXML
    private Label url;
    @FXML
    private Label url1;
    @FXML
    private Label url2;
    @FXML
    private Label nom2;
    @FXML
    private Label fondation2;
    @FXML
    private Label time2;
    @FXML
    private Label equipe2;
    @FXML
    private Label population2;
    @FXML
    private Label coords2;
    @FXML
    private Label photo2;
    @FXML
    private Label logo2;
    @FXML
    private Label logoeq2;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    
    public static AnchorPane rootP;
    
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


    /**
     * Initializes the controller class.
     */
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
        List<EntiteVille> lsvilles = cs.listVilles();
        ObservableList<EntiteVille> lc = FXCollections.observableArrayList();
        lsvilles.stream().forEach((j)->{
            lc.add(j);
        });
        table_ville.setItems(lc);
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        fondation.setCellValueFactory(new PropertyValueFactory<>("Fondation"));
        population.setCellValueFactory(new PropertyValueFactory<>("Population"));
        time.setCellValueFactory(new PropertyValueFactory<>("Timezone"));
        equipe.setCellValueFactory(new PropertyValueFactory<>("Equipelocale"));
        coord.setCellValueFactory(new PropertyValueFactory<>("Coordonnees"));
        
        nom2.setVisible(false);
        fondation2.setVisible(false);
        time2.setVisible(false);
        equipe2.setVisible(false);
        population2.setVisible(false);
        coords2.setVisible(false);
        photo2.setVisible(false);
        logo2.setVisible(false);
        logoeq2.setVisible(false);
        nom1.setVisible(false);
        fondation1.setVisible(false);
        time1.setVisible(false);
        equipe1.setVisible(false);
        population1.setVisible(false);
        coord1.setVisible(false);
        photo.setVisible(false);
        logo.setVisible(false);
        logoequipe.setVisible(false);
        
        table_ville.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() > 1) {
            try {
                Edition();
            } catch (IOException ex) {
                Logger.getLogger(ListeCafesController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ListeCafesController.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
});
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }
    } 
    
    private boolean isInputValid() 
    {
        String errorMessage = "";

        if (nom1.getText() == null || nom1.getText().length() == 0) 
        {
            errorMessage += "Nom invalide !\n"; 
        }
        if (fondation1.getText() == null || fondation1.getText().length() == 0 || fondation1.getLength()!=4) 
        {
            errorMessage += "Fondation invalide !\n"; 
        }
        if (time1.getText() == null || time1.getText().length() == 0) 
        {
            errorMessage += "Timezone invalide !\n"; 
        } 
        if (equipe1.getText()== null || equipe1.getText().length() == 0) 
        {
            errorMessage += "Equipe invalide !\n"; 
        } 
        if (population1.getText() == null || population1.getText().length() == 0) 
        {
            errorMessage += "Population invalides !\n"; 
        } 
        if (coord1.getText()== null || coord1.getText().length() == 0) 
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
    
    public void Edition() throws IOException, SQLException 
    {
        if (table_ville.getSelectionModel().getSelectedItem() != null) 
        {
            
        nom2.setVisible(true);
        fondation2.setVisible(true);
        time2.setVisible(true);
        equipe2.setVisible(true);
        population2.setVisible(true);
        coords2.setVisible(true);
        photo2.setVisible(true);
        logo2.setVisible(true);
        logoeq2.setVisible(true);
        nom1.setVisible(true);
        fondation1.setVisible(true);
        time1.setVisible(true);
        equipe1.setVisible(true);
        population1.setVisible(true);
        coord1.setVisible(true);
        photo.setVisible(true);
        logo.setVisible(true);
        logoequipe.setVisible(true);
        
        EntiteVille selectedPerson = table_ville.getSelectionModel().getSelectedItem();
        nom1.setText(selectedPerson.getNom());
        fondation1.setText(selectedPerson.getFondation());
        coord1.setText(selectedPerson.getCoordonnees());
        population1.setText(selectedPerson.getPopulation());
        equipe1.setText(selectedPerson.getEquipelocale());
        time1.setText(selectedPerson.getTimezone());
        javafx.scene.image.Image im1 = new javafx.scene.image.Image(selectedPerson.getPhotoville());
        photoville.setImage(im1);
        javafx.scene.image.Image im2 = new javafx.scene.image.Image(selectedPerson.getLogoville());
        photologo.setImage(im2);
        javafx.scene.image.Image im3 = new javafx.scene.image.Image(selectedPerson.getLogoequipe());
        photoeq.setImage(im3);
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
    private void Add(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("AddVille.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void Edit(ActionEvent event) throws SQLException, IOException 
    {
        int selectedIndex = table_ville.getSelectionModel().getSelectedIndex();
        EntiteVille selectedPerson = table_ville.getSelectionModel().getSelectedItem();
        
        if (selectedIndex >= 0)
        {
        VilleService cs = new VilleService();
        EntiteVille cafe = new EntiteVille();
        cafe.setIdville(selectedPerson.getId());
        if (isInputValid()) 
        {
        cafe.setNom(nom1.getText());
        cafe.setFondation(fondation1.getText());
        cafe.setPopulation(coord1.getText());
        cafe.setCoordonnees(coord1.getText());
        cafe.setEquipelocale(equipe1.getText());
        cafe.setTimezone(time1.getText());

        try
        {
        cs.EditVille(cafe);
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
            alert.setTitle("Ville modifiée");
            alert.setHeaderText("Ville modifiée avec succès");
            alert.setContentText("Ville "+nom1.getText()+" modifiée avec succès");
            alert.showAndWait();
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ville non modifié");
                alert.setHeaderText("Echec de la modification de la ville");
                alert.setContentText("Echec de la modification de la ville "+nom1.getText());
                alert.showAndWait();
                }
        Close(event);
        }
    }
        else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Pas de selection");
            a.setHeaderText("Veuillez selectionner un élément");
            a.setContentText("Element à modifier invalide");
            Optional<ButtonType> result = a.showAndWait();
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
        photoville.setFitHeight(80);
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
        photologo.setFitHeight(80);
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
        photoeq.setFitHeight(80);
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
                urlb.addParameter("nomville",nom1.getText());
        
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
                urlb.addParameter("nomville",nom1.getText());
        
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
                urlb.addParameter("nomville",nom1.getText());
        
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

    private void Close(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeVilles.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void Delete(ActionEvent event) throws SQLException 
    {
         int selectedIndex = table_ville.getSelectionModel().getSelectedIndex();
         EntiteVille selectedPerson = table_ville.getSelectionModel().getSelectedItem();
        if (selectedIndex >= 0) 
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            a.setHeaderText("Confirmer la suppression");
            a.setContentText("Etes vous sûr?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
            table_ville.getItems().remove(selectedIndex);
          VilleService cs = new VilleService();
            cs.DeleteVille(selectedPerson);
            }
        } else {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Pas de selection");
            a.setHeaderText("Veuillez selectionner un élément");
            a.setContentText("Element à supprimer invalide");
            Optional<ButtonType> result = a.showAndWait();
        }
    }
    
}
