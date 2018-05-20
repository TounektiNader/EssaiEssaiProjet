/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Cadeau;
import Entity.Partie;
import Entity.Resultat;
import Entity.User;
import static Presentation.AccueilController.rootP;
import static Presentation.ResultatMatchAController.idPartie;
import Services.CadeauService;
import Services.PartieService;
import Services.ResultatService;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.sun.javafx.fxml.builder.URLBuilder;
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
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import org.controlsfx.control.Notifications;
import org.xml.sax.SAXException;

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
    private ImageView imageV;
    @FXML
    private JFXButton join;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane root;
    
     public static AnchorPane rootP;
    @FXML
    private Pane paneE;
    Random rd = new Random(); 
    public int n ; 
    final   File fileSave = new File("C:/Users/21650/Documents/GitHub/ValidationWeb/russia/web/uploads/images/");
    final   File fileSave2 = new File("C:/Users/21650/Documents/GitHub/EssaiEssaiProjet/Essai/src/img/");

    
    public  static Stage stage ;
     public  String nomFichier ; 
    public static Stage getStage() {
        return stage;
    }
 
    @FXML
    private JFXTextField username;
    @FXML
    private ImageView imageVV;

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
        
        comboCat.getItems().addAll(categorie);
        CadeauService cadeauService = new CadeauService();
        
        data = cadeauService.listCadeau();
        colCat.setCellValueFactory(new PropertyValueFactory("categorie"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colJeton.setCellValueFactory(new PropertyValueFactory<>("jeton"));
        
        table.setItems(null);
        table.setItems(data);
        
      remplierLabel();
        
      
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
                paneE.setVisible(true);
            } else {
                drawer.open();
                paneE.setVisible(false);
            }
        });
      
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
        Image im = new Image(resultatSelected.getImg());
        imageVV.setImage(im);
        imageV.setImage(im);
        
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
        //comboCat.setValue("");
       // String cat = txtCat.getText();
        String cat = comboCat.getSelectionModel().getSelectedItem();
        String type = txtType.getText();
        String ji = jeton.getText();
        
        
        if((cat.equals("Catégorie"))||(cat.equals("choissez Catégorie")) ||(type.equals(""))||(ji.equals(""))){
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
        
        n=rd.nextInt(10000)+1;
        
            
                    
                    try {
                          File nomfichier = new File("C:/Users/21650/Documents/GitHub/ValidationWeb/russia/web/uploads/images/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(imageV.getImage(),
                            null), "png", nomfichier);
                        File nomfichier2 = new File("C:/Users/21650/Documents/GitHub/EssaiEssaiProjet/Essai/src/img/" + nomFichier+n + ".png");
                        ImageIO.write(SwingFXUtils.fromFXImage(imageV.getImage(),
                            null), "png", nomfichier2);
                        insertionBase(nomFichier+n+".png");
                    } catch (URISyntaxException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                    Logger.getLogger(GestionRecompenseController.class.getName()).log(Level.SEVERE, null, ex);
                    }
            
        
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

    private void actualiser(ActionEvent event) {
        loadData();
    }

    @FXML
    private void joindre(ActionEvent event) 
    {
      
                setExtFilters(fileChooser);
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                  //labes.setText(file.getAbsolutePath());
                
                  image = new Image(file.toURI().toString());
       
         
        imageV.setFitHeight(400);
        imageV.setPreserveRatio(true);
        imageV.setImage(image);
        imageV.setSmooth(true);
        imageV.setCache(true);
       
          nomFichier = file.getName().substring(0,file.getName().indexOf("."));
          //String nomFichier2= nomFichier.substring(0, nomFichier.indexOf("."));
          
         
          System.out.println(nomFichier);

                }
            }
      
    
    public void insertionBase(String nomFile)throws URISyntaxException,
            MalformedURLException,
            IOException 
     {
         
           URLBuilder urlb = new URLBuilder("localhost");
        urlb.setConnectionType("http");
        urlb.addSubfolder("java");
        urlb.addSubfolder("insertionImageCadeau.php");
        urlb.addParameter("image", nomFile);
        urlb.addParameter("Type",txtType.getText());
        
        String url = urlb.getURL();
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

                if (reponse == HttpsURLConnection.HTTP_OK) {


                    InputStream is = conx.getInputStream();

                    BufferedReader br = new BufferedReader(new InputStreamReader(is));

                    String ligne = "", resultat = "";

                    while ((ligne = br.readLine()) != null) {

                        resultat += ligne;
                    }

    }
}  
       
      
    private void setExtFilters(FileChooser chooser) {
        chooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }

    private void afficheRecompense(ActionEvent event) throws IOException {
           Stage primaryStage= new Stage();
           Parent root = FXMLLoader.load(getClass().getResource("/Presentation/recompense.fxml"));
           
           Scene scene = new Scene(root);
      
           primaryStage.setScene(scene);
           primaryStage.show();
                   primaryStage.setResizable(false);
    final Node source = (Node) event.getSource();
    final Stage stage = (Stage) source.getScene().getWindow();
    stage.close();
    
    }

    @FXML
    private void RefrechAnchor(MouseEvent event) {
        loadData();
        comboCat.setValue("choissez Catégorie");
        txtType.setText("");
        jeton.setText("");
        
        comboCat.setDisable(false);
        txtType.setDisable(false);
        jeton.setDisable(false);
        
        
        save.setDisable(false);
        edit.setDisable(true);
        delete.setDisable(true);
        
        
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
    
    class URLBuilder {
    private StringBuilder folders, params;
    private String connType, host;

    void setConnectionType(String conn) {
        connType = conn;
    }

    URLBuilder(){
        folders = new StringBuilder();
        params = new StringBuilder();
    }

    URLBuilder(String host) {
        this();
        this.host = host;
    }

    void addSubfolder(String folder) {
        folders.append("/");
        folders.append(folder);
    }

    void addParameter(String parameter, String value) {
        if(params.toString().length() > 0){params.append("&");}
        params.append(parameter);
        params.append("=");
        params.append(value);
    }

    String getURL() throws URISyntaxException, MalformedURLException {
        URI uri = new URI(connType, host, folders.toString(),
                params.toString(), null);
        return uri.toURL().toString();
    }

    String getRelativeURL() throws URISyntaxException, MalformedURLException{
        URI uri = new URI(null, null, folders.toString(), params.toString(), null);
        return uri.toString();
    }
    }
    
}
