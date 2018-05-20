/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Actualite;
import Services.ActualiteService;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.nio.file.Files;
import java.nio.file.Path;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import static Presentation.ActuController.image;
import static Presentation.ActuController.stage;
import static Presentation.AjoutactualiteController.image;
import static Presentation.AjoutactualiteController.stage;
import Utils.XML;

/**
 * FXML Controller class
 *
 * @author gaelfameni
 */
public class MenuAdsmin implements Initializable {

    @FXML
    private Label actualités;
    
    @FXML
    private Button bt_ajouté;
    @FXML
    private ListView<Actualite> listview;
    private TextField titre;
    private TextArea description;
    @FXML
    private ImageView ph;
    @FXML
    private Label url;
    private Label photo2;
    private Button photo;

   private ObservableList<Actualite> data ;
    ActualiteService as = new ActualiteService();
    
    
    Random rd = new Random(); 
    public int n ; 
    final FileChooser fileChooser = new FileChooser();
    final   File fileSave = new File("C:\\wamp\\www\\java\\images");
    static ImageView image ; 
    public  static Stage stage ;
    public  String nomFichier ; 
    
    public static Stage getStage() 
    {
        return stage;
    }
    private Label nom2;
    private Label details2;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        String bip = "C:/Users/Nader/Documents/GitHub/EssaiEssaiProjet/Essai/src/Utils/AAA.mp3";
      Media hit = new Media(new File(bip).toURI().toString());
      MediaPlayer mediaPlayer = new MediaPlayer(hit);
      mediaPlayer.play();
        
        
        
        
       
         listview.setOnMouseClicked((MouseEvent event) -> {
        if (event.getClickCount() > 1) {
            Edition();
    }
});
        
          List<Actualite> la = as.lireActualite();
        data = FXCollections.observableArrayList();
        la.stream().forEach((j)->{
            data.add(j);
                       
        });
        listview.setItems(data);
        listview.setCellFactory(new Callback<ListView<Actualite>, ListCell<Actualite>>() {
      @Override
      public ListCell<Actualite> call(ListView<Actualite> param) {
          return new ListCellActualite();
      }
  });
        
        
    }    




    private void Delete(ActionEvent event) {
        ActualiteService as= new ActualiteService();
        
         int selectedIndex = listview.getSelectionModel().getSelectedIndex();
         Actualite selectedPerson = listview.getSelectionModel().getSelectedItem();
         Alert a = new Alert(Alert.AlertType.CONFIRMATION);
         
        if (selectedIndex >= 0) {
          //  serviceTable.getItems().remove(selectedIndex);
          as.supprimerActualite(selectedPerson);
            affichageprop(event);
        } else {
            // Nothing selected.
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Service Selected");
            alert.setContentText("Please select a experience in the table.");
            alert.showAndWait();
        }
        
    }

    private void Edit(ActionEvent event) {
        
         Actualite selectedPerson = listview.getSelectionModel().getSelectedItem();
        ActualiteService as = new ActualiteService();
        
        Actualite a = new Actualite();
        
        
        a.setIdactualite(selectedPerson.getIdactualite());
        a.setTitre(titre.getText());
        a.setTexte(description.getText());
        a.setUser(selectedPerson.getUser());
        
        
        
        try
        {
        as.modifierActualite(a);
        
         n=rd.nextInt(10000)+1;
                try 
                { 
                        File nomfichier = new File("C:/wamp/www/java/images/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(ph.getImage(),null), "png", nomfichier);
                        insertionBase(nomFichier+n);
                }
                catch (URISyntaxException ex) 
                {
                        Logger.getLogger(MenuAdsmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
                catch (MalformedURLException ex) 
                {
                        Logger.getLogger(MenuAdsmin.class.getName()).log(Level.SEVERE, null, ex);
                    }
        
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Actuaité modifiée");
            alert.setHeaderText("Atualité modifiée avec succès");
            alert.setContentText("Atualité "+titre.getText()+" modifiée avec succès");
            alert.showAndWait();
            titre.setVisible(false);
        description.setVisible(false);
        photo.setVisible(false);
        photo2.setVisible(false);
        description.setVisible(false);
        details2.setVisible(false);
        ph.setVisible(false);
            
        }
        catch (Exception e)
                {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Atualité non modifiée");
                alert.setHeaderText("Echec de la modification de l'Atualité");
                alert.setContentText("Echec de la modification de l'Atualité "+titre.getText());
                alert.showAndWait();
                }
       listview.refresh();
        
        
    }

    
     private void Edition() {
    
        if (listview.getSelectionModel().getSelectedItem() != null) 
        {
           nom2.setVisible(true);
        details2.setVisible(true);
        description.setVisible(true);
        titre.setVisible(true);
        photo.setVisible(true);
        
        photo2.setVisible(true);
       
        
        
        
  
    }
     }
        
        
    private void browser(ActionEvent event) {
       setExtFilters(fileChooser);
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) 
                {
                  url.setText(file.getAbsolutePath());
                  image = new ImageView(file.toURI().toString());
        ph.setFitHeight(200);
        ph.setPreserveRatio(true);
        //ph.setImage(image);
        ph.setSmooth(true);
        ph.setCache(true);
                    nomFichier = file.getName().substring(0,file.getName().indexOf(".")).replaceAll("\\s+"," ");
          System.out.println(nomFichier);
                }
    }
    
 private void affichageprop(ActionEvent event) {

  List<Actualite> la = as.lireActualite();
        data = FXCollections.observableArrayList();
        la.stream().forEach((j)->{
            data.add(j);
                       
        });
        listview.setItems(data);
        listview.setCellFactory(new Callback<ListView<Actualite>, ListCell<Actualite>>() {
      @Override
      public ListCell<Actualite> call(ListView<Actualite> param) {
          return new ListCellActualite();
      }
  });
        
      
     
    }
    
    
     public String upload(File file) throws FileNotFoundException, IOException {
        BufferedOutputStream stream = null;
        String globalPath="C:\\wamp\\www\\image";
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
            Logger.getLogger(AjoutactualiteController.class.getName()).log(Level.SEVERE, null, ex);
            return "error1";
        } catch (IOException ex) {
            Logger.getLogger(AjoutactualiteController.class.getName()).log(Level.SEVERE, null, ex);
            return "error2";
        }
    }
     
     
      
    public void insertionBase(String nomFile)throws URISyntaxException, MalformedURLException, IOException 
            {
                URLBuilder urlb = new URLBuilder("localhost");
                urlb.setConnectionType("http");
                urlb.addSubfolder("java");
                urlb.addSubfolder("insertionImageCadeau.PHP");
                urlb.addParameter("image","http://localhost/java/images/"+nomFile+".png");
                urlb.addParameter("titre",titre.getText());
        
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
    private void AddActu(ActionEvent event) throws IOException {
        
        Parent homePage = FXMLLoader.load(getClass().getResource("/Presentation/AddActualité.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
        
    }

    @FXML
    private void decon(ActionEvent event) throws IOException {
            XML x = new XML();
        x.Ecrire("0", "0", "0", "0", "0", "0", "0", 0, "0", "0");
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


