/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Equipe;
import Entity.Partie;
import Entity.Resultat;
import Entity.User;
import static Presentation.MesParisController.rootP;
import Services.EquipeService;
import Services.PartieService;
import Services.ResultatService;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class ArbreController implements Initializable {

    @FXML
    private Pane paneE;
    @FXML
    private Label D2;
    @FXML
    private Label C1;
    @FXML
    private Label A1;
    @FXML
    private Label B2;
    @FXML
    private Label E1;
    @FXML
    private Label F2;
    @FXML
    private Label G1;
    @FXML
    private Label H2;
    @FXML
    private Label B1;
    @FXML
    private Label A2;
    @FXML
    private Label D1;
    @FXML
    private Label C2;
    @FXML
    private Label F1;
    @FXML
    private Label E2;
    @FXML
    private Label H1;

    private ObservableList<Resultat> data;
    @FXML
    private Label G2;
    @FXML
    private Label C1D2;
    @FXML
    private Label A1B2;
    @FXML
    private Label E1F2;
    @FXML
    private Label G1H2;
    @FXML
    private Label B1A2;
    @FXML
    private Label D1C2;
    @FXML
    private Label F1E2;
    @FXML
    private Label H1G2;

    PartieService partieService = new PartieService();
    ResultatService rs = new ResultatService();
    @FXML
    private Label ED1;
    @FXML
    private Label ED2;
    @FXML
    private Label ED3;
    @FXML
    private Label ED4;
    @FXML
    private Label finaliste1;
    @FXML
    private Label finaliste2;
    @FXML
    private Label bronze1;
    @FXML
    private Label bronze2;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label nmbJeton;
    @FXML
    private JFXTextField username;
    @FXML
    private JFXHamburger hamburger;
         public static AnchorPane rootP;
    @FXML
    private Label labelChampion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          XML a = new XML();
        User u;
        try {
            u = a.lire();
            username.setText(u.getUsername());
            nmbJeton.setText("" + u.getJeton());
        } catch (SAXException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RecompenseController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        affectationGroupeAB18Eme();
        affectationGroupeCD18Eme();
        affectationGroupeEF18Eme();
        affectationGroupeGH18Eme();
        affectation4eme1();
        affectation4eme2();
        affectation4eme3();
        affectation4eme4();
        affectation2eme1();
        affectation2eme2();
        affectationFinal();
        //affcetationChampion();
        
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

    private void listesTickets(ActionEvent event) throws IOException {

        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ResultatMatchA.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();

    }


    public void affectationGroupeAB18Eme() {
        EquipeService equipeService = new EquipeService();
        ObservableList<Equipe> ListA = FXCollections.observableArrayList();
        ListA = equipeService.afficherEquipes("A");
        Collections.sort(ListA);

        ObservableList<Equipe> ListB = FXCollections.observableArrayList();
        ListB = equipeService.afficherEquipes("B");
        Collections.sort(ListB);

        if (rs.nombreMatchParGroupe("A") == 6) {
            A1.setText(ListA.get(0).getNomEquipe());
            Image img = new Image(ListA.get(0).getDrapeau());
            ImageView im = new ImageView(img);

            im.setFitHeight(10);
            im.setFitWidth(10);
            A1.setGraphic(im);

            A2.setText(ListA.get(1).getNomEquipe());
            Image img2 = new Image(ListA.get(1).getDrapeau());
            ImageView im2 = new ImageView(img2);

            im2.setFitHeight(10);
            im2.setFitWidth(10);
            A2.setGraphic(im2);
        }

        if (rs.nombreMatchParGroupe("B") == 6) {
            B1.setText(ListB.get(0).getNomEquipe());
            Image imgB = new Image(ListB.get(0).getDrapeau());
            ImageView imB = new ImageView(imgB);

            imB.setFitHeight(10);
            imB.setFitWidth(10);
            B1.setGraphic(imB);

            B2.setText(ListB.get(1).getNomEquipe());
            Image imgB2 = new Image(ListB.get(1).getDrapeau());
            ImageView imB2 = new ImageView(imgB2);

            imB2.setFitHeight(10);
            imB2.setFitWidth(10);
            B2.setGraphic(imB2);

        }
    }

    public void affectationGroupeCD18Eme() {
        EquipeService equipeService = new EquipeService();
        ObservableList<Equipe> ListC = FXCollections.observableArrayList();
        ListC = equipeService.afficherEquipes("C");
        Collections.sort(ListC);

        ObservableList<Equipe> ListD = FXCollections.observableArrayList();
        ListD = equipeService.afficherEquipes("D");
        Collections.sort(ListD);

        if (rs.nombreMatchParGroupe("C") == 6) {
            C1.setText(ListC.get(0).getNomEquipe());
            Image img = new Image(ListC.get(0).getDrapeau());
            ImageView im = new ImageView(img);

            im.setFitHeight(10);
            im.setFitWidth(10);
            C1.setGraphic(im);

            C2.setText(ListC.get(1).getNomEquipe());
            Image img2 = new Image(ListC.get(1).getDrapeau());
            ImageView im2 = new ImageView(img2);

            im2.setFitHeight(10);
            im2.setFitWidth(10);
            C2.setGraphic(im2);
        }

        if (rs.nombreMatchParGroupe("D") == 6) {
            D1.setText(ListD.get(0).getNomEquipe());
            Image imgB = new Image(ListD.get(0).getDrapeau());
            ImageView imB = new ImageView(imgB);

            imB.setFitHeight(10);
            imB.setFitWidth(10);
            D1.setGraphic(imB);

            D2.setText(ListD.get(1).getNomEquipe());
            Image imgB2 = new Image(ListD.get(1).getDrapeau());
            ImageView imB2 = new ImageView(imgB2);

            imB2.setFitHeight(10);
            imB2.setFitWidth(10);
            D2.setGraphic(imB2);

        }
    }

    public void affectationGroupeEF18Eme() {
        EquipeService equipeService = new EquipeService();
        ObservableList<Equipe> ListE = FXCollections.observableArrayList();
        ListE = equipeService.afficherEquipes("E");
        Collections.sort(ListE);

        ObservableList<Equipe> ListF = FXCollections.observableArrayList();
        ListF = equipeService.afficherEquipes("F");
        Collections.sort(ListF);

        if (rs.nombreMatchParGroupe("E") == 6) {
            E1.setText(ListE.get(0).getNomEquipe());
            Image img = new Image(ListE.get(0).getDrapeau());
            ImageView im = new ImageView(img);

            im.setFitHeight(10);
            im.setFitWidth(10);
            E1.setGraphic(im);

            E2.setText(ListE.get(1).getNomEquipe());
            Image img2 = new Image(ListE.get(1).getDrapeau());
            ImageView im2 = new ImageView(img2);

            im2.setFitHeight(10);
            im2.setFitWidth(10);
            E2.setGraphic(im2);
        }

        if (rs.nombreMatchParGroupe("F") == 6) {
            F1.setText(ListF.get(0).getNomEquipe());
            Image imgB = new Image(ListF.get(0).getDrapeau());
            ImageView imB = new ImageView(imgB);

            imB.setFitHeight(10);
            imB.setFitWidth(10);
            F1.setGraphic(imB);

            F2.setText(ListF.get(1).getNomEquipe());
            Image imgB2 = new Image(ListF.get(1).getDrapeau());
            ImageView imB2 = new ImageView(imgB2);

            imB2.setFitHeight(10);
            imB2.setFitWidth(10);
            F2.setGraphic(imB2);
        }
    }

    public void affectationGroupeGH18Eme() {
        EquipeService equipeService = new EquipeService();

        ObservableList<Equipe> ListG = FXCollections.observableArrayList();
        ListG = equipeService.afficherEquipes("G");
        Collections.sort(ListG);

        ObservableList<Equipe> ListH = FXCollections.observableArrayList();
        ListH = equipeService.afficherEquipes("H");
        Collections.sort(ListH);

        if (rs.nombreMatchParGroupe("G") == 6) {
            G1.setText(ListG.get(0).getNomEquipe());
            Image img = new Image(ListG.get(0).getDrapeau());
            ImageView im = new ImageView(img);

            im.setFitHeight(10);
            im.setFitWidth(10);
            G1.setGraphic(im);

            G2.setText(ListG.get(1).getNomEquipe());
            Image img2 = new Image(ListG.get(1).getDrapeau());
            ImageView im2 = new ImageView(img2);

            im2.setFitHeight(10);
            im2.setFitWidth(10);
            G2.setGraphic(im2);
        }

        if (rs.nombreMatchParGroupe("H") == 6) {
            H1.setText(ListH.get(0).getNomEquipe());
            Image imgB = new Image(ListH.get(0).getDrapeau());
            ImageView imB = new ImageView(imgB);

            imB.setFitHeight(10);
            imB.setFitWidth(10);
            H1.setGraphic(imB);

            H2.setText(ListH.get(1).getNomEquipe());
            Image imgB2 = new Image(ListH.get(1).getDrapeau());
            ImageView imB2 = new ImageView(imgB2);

            imB2.setFitHeight(10);
            imB2.setFitWidth(10);
            H2.setGraphic(imB2);
        }
    }

    public void affectation4eme1() {
        Partie partieC1 = new Partie();

        Equipe equipeGaC1 = rs.EquipeGagne((rs.idPartie("C1")));
        Equipe equipeGaA1 = rs.EquipeGagne((rs.idPartie("A1")));

        if (partieService.DetailsPartie(rs.idPartie("C1")).getIdMatch() == 0) {
            partieC1.setEtatMatch("PasEncore");
        } else {

            partieC1 = partieService.DetailsPartie(rs.idPartie("C1"));

            if (partieC1.getEtatMatch().equals("Jouee")) {

                C1D2.setText(equipeGaC1.getNomEquipe());
                Image imgB = new Image(equipeGaC1.getDrapeau());
                ImageView imB = new ImageView(imgB);

                imB.setFitHeight(10);
                imB.setFitWidth(10);
                C1D2.setGraphic(imB);
            }
        }

        Partie partieA1 = new Partie();

        if (partieService.DetailsPartie(rs.idPartie("A1")).getIdMatch() == 0) {
            partieA1.setEtatMatch("PasEncore");
        } else {
            partieA1 = partieService.DetailsPartie(rs.idPartie("A1"));

            if (partieA1.getEtatMatch().equals("Jouee")) {

                A1B2.setText(equipeGaA1.getNomEquipe());
                Image imgB2 = new Image(equipeGaA1.getDrapeau());
                ImageView imB2 = new ImageView(imgB2);

                imB2.setFitHeight(10);
                imB2.setFitWidth(10);
                A1B2.setGraphic(imB2);
            }

        }
    }

    public void affectation4eme2() {
        ResultatService rs = new ResultatService();

        Partie partieE1 = new Partie();

        if (partieService.DetailsPartie(rs.idPartie("E1")).getIdMatch() == 0) {
            partieE1.setEtatMatch("PasEncore");
        } else {
            partieE1 = partieService.DetailsPartie(rs.idPartie("E1"));
            if ((partieE1.getEtatMatch()).equals("Jouee")) {

                Equipe equipeGaE1 = rs.EquipeGagne((rs.idPartie("E1")));

                E1F2.setText(equipeGaE1.getNomEquipe());
                Image imgB = new Image(equipeGaE1.getDrapeau());
                ImageView imB = new ImageView(imgB);

                imB.setFitHeight(10);
                imB.setFitWidth(10);
                E1F2.setGraphic(imB);
            }
        }
        Partie partieG1 = new Partie();
        if (partieService.DetailsPartie(rs.idPartie("G1")).getIdMatch() == 0) {
            partieG1.setEtatMatch("PasEncore");
        } else {
            partieG1 = partieService.DetailsPartie(rs.idPartie("G1"));

            if (partieG1.getEtatMatch().equals("Jouee")) {
                Equipe equipeGaG1 = rs.EquipeGagne((rs.idPartie("G1")));

                G1H2.setText(equipeGaG1.getNomEquipe());
                Image imgB2 = new Image(equipeGaG1.getDrapeau());
                ImageView imB2 = new ImageView(imgB2);

                imB2.setFitHeight(10);
                imB2.setFitWidth(10);
                G1H2.setGraphic(imB2);

            }
        }

    }

    public void affectation4eme3() {
        ResultatService rs = new ResultatService();
        Partie partieB1 = new Partie();

        Equipe equipeGaD1 = rs.EquipeGagne((rs.idPartie("D1")));
        Equipe equipeGaB1 = rs.EquipeGagne((rs.idPartie("B1")));

        if (partieService.DetailsPartie(rs.idPartie("B1")).getIdMatch() == 0) {
            partieB1.setEtatMatch("PasEncore");
        } else {
            partieB1 = partieService.DetailsPartie(rs.idPartie("B1"));

            if (partieB1.getEtatMatch().equals("Jouee")) {

                B1A2.setText(equipeGaB1.getNomEquipe());
                Image imgB = new Image(equipeGaB1.getDrapeau());
                ImageView imB = new ImageView(imgB);

                imB.setFitHeight(10);
                imB.setFitWidth(10);
                B1A2.setGraphic(imB);
            }
        }

        Partie partieD1 = new Partie();
        partieD1.setEtatMatch("PasEncore");

        if (partieService.DetailsPartie(rs.idPartie("D1")).getIdMatch() == 0) {
            partieD1.setEtatMatch("PasEncore");
        } else {
            partieD1 = partieService.DetailsPartie(rs.idPartie("D1"));
            if (partieD1.getEtatMatch().equals("Jouee")) {

                D1C2.setText(equipeGaD1.getNomEquipe());
                Image imgB2 = new Image(equipeGaD1.getDrapeau());
                ImageView imB2 = new ImageView(imgB2);

                imB2.setFitHeight(10);
                imB2.setFitWidth(10);
                D1C2.setGraphic(imB2);
            }
        }
    }

    public void affectation4eme4() {
        ResultatService rs = new ResultatService();

        Partie partieF1 = new Partie();
        partieF1.setEtatMatch("PasEncore");

        Equipe equipeGaF1 = rs.EquipeGagne((rs.idPartie("F1")));
        Equipe equipeGaH1 = rs.EquipeGagne((rs.idPartie("H1")));

        if (partieService.DetailsPartie(rs.idPartie("F1")).getIdMatch() == 0) {
            partieF1.setEtatMatch("PasEncore");
        } else {
            partieF1 = partieService.DetailsPartie(rs.idPartie("F1"));
            if (partieF1.getEtatMatch().equals("Jouee")) {

                F1E2.setText(equipeGaF1.getNomEquipe());
                Image imgB = new Image(equipeGaF1.getDrapeau());
                ImageView imB = new ImageView(imgB);

                imB.setFitHeight(10);
                imB.setFitWidth(10);
                F1E2.setGraphic(imB);
            }
        }

        Partie partieH1 = new Partie();

        if (partieService.DetailsPartie(rs.idPartie("H1")).getIdMatch() == 0) {
            partieH1.setEtatMatch("PasEncore");
        } else {
            partieH1 = partieService.DetailsPartie(rs.idPartie("H1"));
            if (partieH1.getEtatMatch().equals("Jouee")) {

                H1G2.setText(equipeGaH1.getNomEquipe());
                Image imgB2 = new Image(equipeGaH1.getDrapeau());
                ImageView imB2 = new ImageView(imgB2);

                imB2.setFitHeight(10);
                imB2.setFitWidth(10);
                H1G2.setGraphic(imB2);
            }
        }
    }

    public void affectation2eme1() {
        Partie partieA11 = new Partie();

        Partie partieB11 = new Partie();

        Equipe equipeGaA11 = rs.EquipeGagne(rs.idPartie("A11"));
        Equipe equipeGaB11 = rs.EquipeGagne(rs.idPartie("B11"));

        if ((partieService.DetailsPartie(rs.idPartie("A11")).getIdMatch() == 0) || (partieService.DetailsPartie(rs.idPartie("B11")).getIdMatch() == 0)) {
            partieA11.setEtatMatch("PasEncore");
            partieB11.setEtatMatch("PasEncore");
        } else {
            partieB11 = partieService.DetailsPartie(rs.idPartie("B11"));
            partieA11 = partieService.DetailsPartie(rs.idPartie("A11"));

            if ((partieA11.getEtatMatch().equals("Jouee")) && (partieB11.getEtatMatch().equals("Jouee"))) {

                ED1.setText(equipeGaA11.getNomEquipe());
                Image imgB = new Image(equipeGaA11.getDrapeau());
                ImageView imB = new ImageView(imgB);

                imB.setFitHeight(10);
                imB.setFitWidth(10);
                ED1.setGraphic(imB);

                ED2.setText(equipeGaB11.getNomEquipe());
                Image img = new Image(equipeGaB11.getDrapeau());
                ImageView im = new ImageView(img);

                im.setFitHeight(10);
                im.setFitWidth(10);
                ED2.setGraphic(im);

            }
        }
    }

    public void affectation2eme2() {
        Partie partieC11 = new Partie();

        Partie partieD11 = new Partie();

        Equipe equipeGaA11 = rs.EquipeGagne(rs.idPartie("C11"));
        Equipe equipeGaB11 = rs.EquipeGagne(rs.idPartie("D11"));

        if ((partieService.DetailsPartie(rs.idPartie("C11")).getIdMatch() == 0) || (partieService.DetailsPartie(rs.idPartie("D11")).getIdMatch() == 0)) {
            partieC11.setEtatMatch("PasEncore");
            partieD11.setEtatMatch("PasEncore");
        } else {
            partieC11 = partieService.DetailsPartie(rs.idPartie("C11"));
            partieD11 = partieService.DetailsPartie(rs.idPartie("D11"));

            if ((partieC11.getEtatMatch().equals("Jouee")) && (partieD11.getEtatMatch().equals("Jouee"))) {
                ED3.setText(equipeGaA11.getNomEquipe());
                Image imgB = new Image(equipeGaA11.getDrapeau());
                ImageView imB = new ImageView(imgB);

                imB.setFitHeight(10);
                imB.setFitWidth(10);
                ED3.setGraphic(imB);

                ED4.setText(equipeGaB11.getNomEquipe());
                Image img = new Image(equipeGaB11.getDrapeau());
                ImageView im = new ImageView(img);

                im.setFitHeight(10);
                im.setFitWidth(10);
                ED4.setGraphic(im);

            }

        }
    }

    public void affectationFinal() {
        Partie partieA111 = new Partie();

        Partie partieB111 = new Partie();

        Equipe equipeGaA11 = rs.EquipeGagne(rs.idPartie("A111"));
        Equipe equipeGaB11 = rs.EquipeGagne(rs.idPartie("B111"));

        Equipe equipePer1 = rs.EquipePerdu(rs.idPartie("A111"));
        Equipe equipePer2 = rs.EquipePerdu(rs.idPartie("B111"));

        if ((partieService.DetailsPartie(rs.idPartie("A111")).getIdMatch() == 0) || (partieService.DetailsPartie(rs.idPartie("B111")).getIdMatch() == 0)) {
            partieA111.setEtatMatch("PasEncore");
            partieB111.setEtatMatch("PasEncore");
        } else {
            partieA111 = partieService.DetailsPartie(rs.idPartie("A111"));
            partieB111 = partieService.DetailsPartie(rs.idPartie("B111"));

            if ((partieA111.getEtatMatch().equals("Jouee")) && (partieB111.getEtatMatch().equals("Jouee"))) {

                finaliste1.setText(equipeGaA11.getNomEquipe());
                Image imgB = new Image(equipeGaA11.getDrapeau());
                ImageView imB = new ImageView(imgB);

                imB.setFitHeight(10);
                imB.setFitWidth(10);
                finaliste1.setGraphic(imB);

                finaliste2.setText(equipeGaB11.getNomEquipe());
                Image img = new Image(equipeGaB11.getDrapeau());
                ImageView im = new ImageView(img);

                im.setFitHeight(10);
                im.setFitWidth(10);
                finaliste2.setGraphic(im);

                bronze1.setText(equipePer1.getNomEquipe());
                Image imgP1 = new Image(equipePer1.getDrapeau());
                ImageView imP1 = new ImageView(imgP1);

                imP1.setFitHeight(10);
                imP1.setFitWidth(10);
                bronze1.setGraphic(imP1);

                bronze2.setText(equipePer2.getNomEquipe());
                Image imgP2 = new Image(equipePer2.getDrapeau());
                ImageView imP2 = new ImageView(imgP2);

                imP2.setFitHeight(10);
                imP2.setFitWidth(10);
                bronze2.setGraphic(imP2);

            }
        }
    }

    public void affcetationChampion(){
        
        Equipe equipeGaA111 = rs.EquipeGagne(rs.idPartie("A1111"));
        labelChampion.setText(equipeGaA111.getNomEquipe());
                Image imgB = new Image(equipeGaA111.getDrapeau());
                ImageView imB = new ImageView(imgB);
                
                imB.setFitHeight(10);
                imB.setFitWidth(10);
                labelChampion.setGraphic(imB);

        
    }
    @FXML
    private void MatchIntefce(ActionEvent event) throws IOException {
        
          Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProgrammeMatch.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void classementInterace(ActionEvent event) throws IOException {
        
          Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ClassementParGroupe.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);

        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void contact(MouseEvent event) throws IOException {
           Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ContactUS.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
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
}
