/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.User;
import Utils.XML;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
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
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author 21650
 */
public class RealTimeController implements Initializable {

    @FXML
    private ImageView imagehome;
    @FXML
    private ImageView imageaway;
    @FXML
    private Label score;
    @FXML
    private Label home;
    @FXML
    private Label away;
    @FXML
    private Label time;
    @FXML
    ListView<String> list = new ListView<String>();
    ObservableList<String> data = FXCollections.observableArrayList();
    @FXML
    private Pane pan;
    @FXML
    private Pane paneE;
    @FXML
    private Label nmbJeton;
    @FXML
    private JFXTextField username;

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
            Logger.getLogger(RealTimeController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RealTimeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Thread daemonThread;
        daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    while (true) {

                        Document doc;
                        try {

                            doc = Jsoup.connect("https://www.sportytrader.com/resultat-direct/eibar-villarreal-cf-2409768/").get();
                            //doc.getElementsByClass("title-header-sport-event-awayteam").eachText());
                            // .eachText().forEach(e->score.setText(e));
                            //System.out.println(doc.getElementsByClass("chart-label-header").eachText().size());
                            //System.out.println(doc.getElementsByClass("chart-label-header").eachText().get(0));
                            //
                            //Element image = doc.select("img").first();
//String url = image.absUrl("src");

                            String url = doc.select("div.header-match-logo-team1 img").attr("src");
                            String url2 = doc.select("div.header-match-logo-team2 img").attr("src");
                            Image i = new Image(url);
                            Image i2 = new Image(url2);
                            imagehome.setImage(i);
                            imageaway.setImage(i2);

                            list.setItems(data);

                            Thread.sleep(10000);

                            Platform.runLater(() -> {
                                data.removeAll();
                                list.getItems().clear();
                                doc.getElementsByClass("title-header-sport-event-awayteam").eachText().forEach(e -> away.setText(e));
                                doc.getElementsByClass("title-header-sport-event-hometeam").eachText().forEach(e -> home.setText(e));
                                doc.getElementsByClass("match-progress-event ").eachText().forEach(e -> data.add(e));
                                doc.getElementsByClass("chart-label-header").eachText().forEach(e -> time.setText(e));
                                doc.getElementsByClass("chart-number-header text-center").eachText().forEach(e -> score.setText(e));

                            });

                        } catch (IOException ex) {
                            Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(RealTimeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } finally {
                    System.out.println("Fin demon");
                }
            }
        }, "Demon");
        daemonThread.setDaemon(true);
        daemonThread.start();

    }

    @FXML
    private void deco(ActionEvent event) throws IOException {
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

    @FXML
    private void MatchIntefce(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProgrammeMatch.fxml"));

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
