/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Partie;
import Entity.Resultat;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nader
 */
public class ClassementItemController {

    @FXML
    private VBox VBox;

    @FXML
    private HBox Hbox;

    @FXML
    private HBox hbname;

    private Label tour;

    @FXML
    private HBox hbetat;

    @FXML
    private Label date;

    @FXML
    private ImageView imgHome;

    @FXML
    private JFXButton home;

    @FXML
    private ImageView imgAway;

    @FXML
    private JFXButton away;
    @FXML
    private Label groupe;

    @FXML
    private JFXButton butHome;

    @FXML
    private JFXButton butAway;

    public ClassementItemController() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Presentation/ClassementItem.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            //  throw new RuntimeException(e);
        }

    }

    public void initialize() {
        // TODO
    }

    Resultat resultat = new Resultat();

    public void setResultat(Resultat rs) {
        this.resultat = rs;
        if (resultat != null) {
          

                groupe.setText("Groupe " +rs.getPartie().getGroup());
                date.setText(rs.getPartie().getDatematch().toString());
                away.setText(rs.getNomEquipeAway());
                home.setText(rs.getNomEquipeHome());

                butHome.setText("" + rs.getButHome());
                butAway.setText("" + rs.getButAway());
                Image imHome = new Image(rs.getPartie().getHome().getDrapeau());
                Image imAway = new Image(rs.getPartie().getAway().getDrapeau());

                imgAway.setImage(imAway);
                imgHome.setImage(imHome);
            

        }
    }

     public void setResultatMatchNonJouee(Resultat rs) {
        this.resultat = rs;
        if (resultat != null) {
          

                groupe.setText("Groupe " +rs.getPartie().getGroup());
                date.setText(rs.getPartie().getDatematch().toString());
                away.setText(rs.getNomEquipeAway());
                home.setText(rs.getNomEquipeHome());

                butHome.setText("?" );
                butAway.setText("?");
                Image imHome = new Image(rs.getPartie().getHome().getDrapeau());
                Image imAway = new Image(rs.getPartie().getAway().getDrapeau());

                imgAway.setImage(imAway);
                imgHome.setImage(imHome);
            

        }
    }
//    public HBox getBox()
//    {
//        return Hbox;
//    }
//    
    public VBox getBox() {
        return VBox;
    }

}
