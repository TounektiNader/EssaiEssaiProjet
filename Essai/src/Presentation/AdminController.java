/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import DateStroge.MyConnection;
import Entity.Joueurs;
import Entity.Partie;
import Entity.RealTime;
import Entity.User;
import Services.EquipeService;
import Services.JoueurService;
import Services.PartieService;
import Services.ServiceRealTime;
import Services.ServiceUser;
import Utils.XML;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author hedih
 */
public class AdminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private ObservableList<User> data2;
     @FXML
    private TableView<User> table;
    @FXML
    private TableColumn<User, String> columnNom;
    @FXML
    private TableColumn<User, String> columnPrenom;
    @FXML
    private TableColumn<User, String> columnMdp;
    @FXML
    private TableColumn<User, String> columnStatus;
    @FXML
    private Label nom;
    @FXML
    private AnchorPane AnchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       XML x= new XML();
        User u=new User();
         try {
             u=x.lire();
         } catch (SAXException ex) {
             Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
         } catch (IOException ex) {
             Logger.getLogger(AdminController.class.getName()).log(Level.SEVERE, null, ex);
         }
           //--------------------------------------------------------------------------------------------------------------  ---------------------- u=x.lire();
        System.out.println(u.getUsername());
        nom.setText(u.getUsername());
        ServiceUser service = new ServiceUser();
        data2 =service.GetAdmin();
        columnNom.setCellValueFactory(new PropertyValueFactory("nom"));
        columnPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnMdp.setCellValueFactory(new PropertyValueFactory<>("mdp"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        table.setItems(null);
        table.setItems(data2);
        loadData();
    }    
    @FXML
    public void loadData() {
       ServiceUser Service = new ServiceUser();
        data2.addAll(Service.GetAdmin());
        if (!data2.isEmpty()) {
            data2.clear();
        }
        data2.addAll(Service.GetAdmin());
        table.setItems(data2);
    }  
    }    
    

