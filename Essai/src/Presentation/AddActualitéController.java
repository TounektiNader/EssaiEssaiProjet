/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Actualite;
import Entity.User;
import Services.ActualiteService;
import Services.ServiceUser;
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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import static Presentation.AjoutactualiteController.image;
import static Presentation.AjoutactualiteController.stage;
import Utils.XML;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author gaelfameni
 */
public class AddActualitéController implements Initializable {

    @FXML
    private Button bt_menu;
    @FXML
    private VBox mainContainer;
    @FXML
    private TextArea details;
    @FXML
    private Button valider;
    @FXML
    private Button fermer;
    @FXML
    private Button photo;
    @FXML
    private ImageView ph;
    @FXML
    private Label url;
    @FXML
    private TextField titre;
    @FXML
    private ImageView img;
    
    
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    public EventHandler<KeyEvent> numeric_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[0-9.]")){ 
                if(txt_TextField.getText().contains(".") && e.getCharacter().matches("[.]")){
                    e.consume();
                }else if(txt_TextField.getText().length() == 0 && e.getCharacter().matches("[.]")){
                    e.consume(); 
                }
            }else{
                e.consume();
            }
        }
    };
} 
    
    
    public EventHandler<KeyEvent> letter_Validation(final Integer max_Lengh) {
    return new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent e) {
            TextField txt_TextField = (TextField) e.getSource();                
            if (txt_TextField.getText().length() >= max_Lengh) {                    
                e.consume();
            }
            if(e.getCharacter().matches("[A-Za-z]")){ 
            }else{
                e.consume();
            }
        }
    };
}    

    @FXML
    private void menu(ActionEvent event) throws IOException {
        
        Parent homePage = FXMLLoader.load(getClass().getResource("/Presentation/UserMenu.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }


    @FXML
    private void Close(ActionEvent event) throws IOException {
        
        Parent homePage = FXMLLoader.load(getClass().getResource("/Presentation/MenuAdminn.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }

    @FXML
    private void Browse(ActionEvent event) {
        
        
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
                }
    }

    @FXML
    private void Addactualité(ActionEvent event) {
         XML a = new XML();
        User u= new User();
        try {
            u = a.lire();
           
        } catch (SAXException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//         ServiceUser serivce= new ServiceUser ();
//         User u = serivce.afficherUser("fameni");
                
       
        
         ActualiteService as= new ActualiteService();
        Actualite actualite= new Actualite();
        
        if (isInputValid())
        {
        actualite.setTitre(titre.getText());
        actualite.setTexte(details.getText());
        //a.setImage(url.getText());
        
        titre.setMaxWidth(200);
        details.setMaxWidth(200);
        details.setWrapText(true);
       
         
           actualite.setUser(u);
             as.ajouterActualite(actualite);
          try
        {   
        
          n=rd.nextInt(10000)+1;
                try 
                { 
                        File nomfichier = new File("C:/xampp/htdocs/java/images/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(img.getImage(),null), "png", nomfichier);
                        insertionBase(nomFichier+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(AddActualitéController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(AddActualitéController.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
      
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Atualité ajoutée");
            alert.setHeaderText("Atualité ajoutée avec succès");
            alert.setContentText("Atualité "+titre.getText()+" ajout avec succès");
            alert.showAndWait();
            //close(event);
        }
        catch (Exception e)
                {
//                Alert alert = new Alert(Alert.AlertType.ERROR);
//                alert.setTitle("Actualté non ajoutée");
//                alert.setHeaderText("Echec de l'insertion de l'Atualité");
//                alert.setContentText("Echec de l'insertion de l'Atualité "+titre.getText());
//                alert.showAndWait();
                }
        }
           else 
        {
            // Show the error message.
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Champs Invalides");
            alert.setHeaderText("Veuillez remplir les champs");
            alert.setContentText("champ vide");
            alert.showAndWait();

           
        }
         
    }
    
    
//     private void close(ActionEvent event) throws IOException
//    {
//    
//    Parent homePage = FXMLLoader.load(getClass().getResource("UserMenu.fxml"));
//
//        Scene homePage_scene = new Scene(homePage);
//
//        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//
//        app_stage.setScene(homePage_scene);
//
//        app_stage.show();
//    
//    }
     
public void insertionBase(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("java");
                urlb.addSubfolder("insertionActualite.php");
                urlb.addParameter("image","http://localhost/java/images/"+nomFile+".png");
                urlb.addParameter("type",titre.getText());
        
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
    
    
    private boolean isInputValid() 
    {
        String errorMessage = "";

        if (titre.getText() == null || titre.getText().length() == 0) 
        {
            errorMessage += "titre invalide !\n"; 
        }
       
        if (details.getText() == null || details.getText().length() == 0) 
        {
            errorMessage += "details invalide"; 
        } 
        
        if (url.getText()== null || url.getText().length() == 0) 
        {
            errorMessage += "Photo invalide !\n"; 
        } 
          
        if (errorMessage.length() == 0) 
        {
            return true;
    
        }
        else 
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
}
    

