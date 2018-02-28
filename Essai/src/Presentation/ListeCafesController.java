/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Services.CafeService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import Entity.EntiteCafe;
import Entity.EntiteVille;
import static Presentation.AddCafeController.image;
import static Presentation.AddCafeController.rootP;
import static Presentation.AddCafeController.stage;
import Services.VilleService;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class ListeCafesController implements Initializable {

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
    private TableView<EntiteCafe> table_cafes;
    @FXML
    private TableColumn<EntiteCafe, String> nom;
    @FXML
    private TableColumn<EntiteCafe, String> details;
    @FXML
    private TableColumn<EntiteCafe, String> coord;
    @FXML
    private Button add;
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private TextField nom1;
    @FXML
    private TextField coord1;
    @FXML
    private TextField details1;
    @FXML
    private ImageView ph;
    @FXML
    private Button photo;
    @FXML
    private Label nom2;
    @FXML
    private Label details2;
    @FXML
    private Label coords2;
    @FXML
    private Label photo2;
    @FXML
    private Label ville2;
    @FXML
    private Label url;
    @FXML
    private ComboBox<String> combo;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label idc;
    
    public static AnchorPane rootP;
    
    String nomString =null;
    
    Random rd = new Random(); 
    public int n ; 
    final FileChooser fileChooser = new FileChooser();
    final   File fileSave = new File("C:\\xampp\\htdocs\\java\\images");
    static Image image ; 
    public  static Stage stage ;
    public  String nomFichier ; 

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
        CafeService cs = new CafeService();
        List<EntiteCafe> lscafes = cs.ListCafes();
        ObservableList<EntiteCafe> lc = FXCollections.observableArrayList();
        lscafes.stream().forEach((j)->{
            lc.add(j);
        });
        table_cafes.setItems(lc);
        nom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        details.setCellValueFactory(new PropertyValueFactory<>("Details"));
        coord.setCellValueFactory(new PropertyValueFactory<>("Position"));
        
        nom1.setVisible(false);
        details1.setVisible(false);
        coord1.setVisible(false);
        photo.setVisible(false);
        combo.setVisible(false);
        nom2.setVisible(false);
        details2.setVisible(false);
        coords2.setVisible(false);
        photo2.setVisible(false);
        ville2.setVisible(false);
        
        table_cafes.setOnMouseClicked((MouseEvent event) -> {
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
        if (details1.getText() == null || details1.getText().length() == 0) 
        {
            errorMessage += "Details invalides !\n"; 
        }
        if (coord1.getText() == null || coord1.getText().length() == 0) 
        {
            errorMessage += "Coordonnées invalides !\n"; 
        } 
        if (combo.getValue()== null || combo.getValue().length() == 0) 
        {
            errorMessage += "Ville invalide !\n"; 
        } 
        if (url.getText() == null || url.getText().length() == 0) 
        {
            errorMessage += "Image invalide !\n"; 
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
    private void Add(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("AddCafe.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    public void Edition() throws IOException, SQLException 
    {
        if (table_cafes.getSelectionModel().getSelectedItem() != null) 
        {
            nom1.setVisible(true);
            details1.setVisible(true);
            coord1.setVisible(true);
        EntiteCafe selectedPerson = table_cafes.getSelectionModel().getSelectedItem();
        nom1.setText(selectedPerson.getNom());
        details1.setText(selectedPerson.getDetails());
        coord1.setText(selectedPerson.getPosition());
        javafx.scene.image.Image im = new javafx.scene.image.Image(selectedPerson.getPhoto());
        ph.setImage(im);
        
        
        photo.setVisible(true);
        combo.setVisible(true);
        nom2.setVisible(true);
        details2.setVisible(true);
        coords2.setVisible(true);
        photo2.setVisible(true);
        ville2.setVisible(true);
        ph.setVisible(true);
        
        VilleService vs = new VilleService();
        List<String> lsv = vs.ReturnNames();
        ObservableList<String> ls = FXCollections.observableArrayList();
        lsv.stream().forEach((j)->{
            ls.add(j);
        });
        combo.setItems(ls);  
        }
    }
    
    @FXML
    private void Edit(ActionEvent event) throws SQLException, IOException
    {
        int selectedIndex = table_cafes.getSelectionModel().getSelectedIndex();
        EntiteCafe selectedPerson = table_cafes.getSelectionModel().getSelectedItem();
        
        if (selectedIndex >= 0)
        {
        CafeService cs = new CafeService();
        VilleService vs = new VilleService();
        EntiteCafe cafe = new EntiteCafe();
        int i = vs.ReturnId(combo.getValue());
        cafe.setId(selectedPerson.getId());
        System.out.println(i);
        if (isInputValid()) 
        {
        cafe.setNom(nom1.getText());
        cafe.setDetails(details1.getText());
        cafe.setPosition(coord1.getText());
        EntiteVille ev = new EntiteVille();
        ev.setIdville(i);
        cafe.setCafeVille(ev);
        try
        {
        cs.EditCafe(cafe);
        n=rd.nextInt(10000)+1;
                try 
                { 
                        File nomfichier = new File("C:/xampp/htdocs/java/images/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(ph.getImage(),null), "png", nomfichier);
                        insertionBase(nomFichier+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(AddCafeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(AddCafeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Café modifié");
            alert.setHeaderText("Café modifié avec succès");
            alert.setContentText("Café "+nom1.getText()+" modifié avec succès");
            alert.showAndWait();
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Café non modifié");
                alert.setHeaderText("Echec de la modification du café");
                alert.setContentText("Echec de la modification du café "+nom1.getText());
                alert.showAndWait();
                }
        Close(event);
        }
    }else{
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Pas de selection");
            a.setHeaderText("Veuillez selectionner un élément");
            a.setContentText("Element à modifier invalide");
            Optional<ButtonType> result = a.showAndWait();
        }
    }
            

    @FXML
    private void Delete(ActionEvent event) throws SQLException 
    {
        int selectedIndex = table_cafes.getSelectionModel().getSelectedIndex();
         EntiteCafe selectedPerson = table_cafes.getSelectionModel().getSelectedItem();

        if (selectedIndex >= 0)
        {
            Alert a = new Alert(Alert.AlertType.CONFIRMATION);
            a.setTitle("Confirmation");
            a.setHeaderText("Confirmer la suppression");
            a.setContentText("Etes vous sûr?");
            Optional<ButtonType> result = a.showAndWait();
            if (result.get() == ButtonType.OK) 
            {
            table_cafes.getItems().remove(selectedIndex);
            CafeService cs = new CafeService();
            cs.DeleteCafe(selectedPerson);
            }
        }
        else 
        {
            Alert a = new Alert(Alert.AlertType.WARNING);
            a.setTitle("Pas de selection");
            a.setHeaderText("Veuillez selectionner un élément");
            a.setContentText("Element à supprimer invalide");
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
        ph.setFitHeight(200);
        ph.setPreserveRatio(true);
        ph.setImage(image);
        ph.setSmooth(true);
        ph.setCache(true);
                    nomFichier = file.getName().substring(0,file.getName().indexOf(".")).replaceAll("\\s+"," ");
          System.out.println(nomFichier);
                }
    }
    
    public void insertionBase(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("java");
                urlb.addSubfolder("insertionImageCafe.PHP");
                urlb.addParameter("photocafe","http://localhost/java/images/"+nomFile+".png");
                urlb.addParameter("nomcafe",nom1.getText());
        
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
        Parent homePage = FXMLLoader.load(getClass().getResource("ListeCafes.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
}
