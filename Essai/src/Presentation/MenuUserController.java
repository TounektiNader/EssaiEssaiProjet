/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Services.VilleService;
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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ouss'Hr
 */
public class MenuUserController implements Initializable {

    @FXML
    private VBox mainContainer;
    @FXML
    private Button v1;
    @FXML
    private ImageView v1View;
    @FXML
    private Button v2;
    @FXML
    private ImageView v2View;
    @FXML
    private Button v3;
    @FXML
    private ImageView v3View;
    @FXML
    private Button v4;
    @FXML
    private ImageView v4View;
    @FXML
    private Button v5;
    @FXML
    private ImageView v5View;
    @FXML
    private Button v6;
    @FXML
    private ImageView v6View;
    @FXML
    private Button v7;
    @FXML
    private ImageView v7View;
    @FXML
    private Button v8;
    @FXML
    private ImageView v8View;
    @FXML
    private Button v9;
    @FXML
    private ImageView v9View;
    @FXML
    private Button v10;
    @FXML
    private ImageView v10View;
    @FXML
    private Button v11;
    @FXML
    private ImageView v11View;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label l4;
    @FXML
    private Label l5;
    @FXML
    private Label l6;
    @FXML
    private Label l7;
    @FXML
    private Label l8;
    @FXML
    private Label l9;
    @FXML
    private Label l10;
    @FXML
    private Label l11;

    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        try 
        {
        VilleService cs = new VilleService();
        List<String> lscafes = cs.ReturnNames();
        ObservableList<String> lc = FXCollections.observableArrayList();
        lscafes.stream().forEach((j)->{
            lc.add(j);
        });
        l1.setText(lc.get(0));
        l2.setText(lc.get(1));
        l3.setText(lc.get(2));
        l4.setText(lc.get(3));
        l5.setText(lc.get(4));
        l6.setText(lc.get(5));
        l7.setText(lc.get(6));
        l8.setText(lc.get(7));
        l9.setText(lc.get(8));
        l10.setText(lc.get(9));
        l11.setText(lc.get(10));
        }
        catch (SQLException e) 
        {
               System.err.println("SQLException: "+e.getMessage()); 
        }

    }    

    @FXML
    private void Showville1(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille1.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville2(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille2.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville3(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille3.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville4(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille4.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville5(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille5.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville6(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille6.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville7(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille7.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville8(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille8.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville9(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille9.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville10(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille10.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
    @FXML
    private void Showville11(ActionEvent event) throws IOException 
    {
        Parent homePage = FXMLLoader.load(getClass().getResource("ShowVille11.fxml"));

        Scene homePage_scene = new Scene(homePage);

        Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        app_stage.setScene(homePage_scene);

        app_stage.show();
    }
    
}
