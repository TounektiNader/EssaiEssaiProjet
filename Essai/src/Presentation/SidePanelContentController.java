package Presentation;

import Entity.User;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

public class SidePanelContentController implements Initializable {

    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b2;
    @FXML
    private JFXButton b3;
    @FXML
    private JFXButton equipesBt;
    @FXML
    private JFXButton exit1;
    @FXML
    private JFXButton gift;
    @FXML
    private JFXButton exit21;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void Admin() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("Compte Admin");
        alert.showAndWait();
    }

    private void Utilisateur() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Informations");
        alert.setHeaderText("Informations");
        alert.setContentText("Compte Utilisateur");
        alert.showAndWait();
    }

    @FXML
    private void exit(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void Actualite(ActionEvent event) throws IOException, SAXException {

        XML a = new XML();
        User u;
        u = a.lire();
        if (u.getRole().equals("0")) {
            Stage primarya = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));
            Scene scene = new Scene(root);
            primarya.setScene(scene);
            primarya.show();
            primarya.setResizable(false);

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else if (u.getRole().equals("Admin")) {
            Admin();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MenuAdminn.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else if (u.getRole().equals("Utilisateur")) {
            Utilisateur();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/UserMenu.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        }

    }

    @FXML
    private void recomm(ActionEvent event) throws IOException, SAXException {

        XML a = new XML();
        User u;
        u = a.lire();
        if (u.getRole().equals("0")) {
            Stage primarya = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));
            Scene scene = new Scene(root);
            primarya.setScene(scene);
            primarya.show();
            primarya.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else if (u.getRole().equals("Admin")) {
            Admin();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MenuAdmin.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else if (u.getRole().equals("Utilisateur")) {
            Utilisateur();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MenuUser.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void matchGestion(ActionEvent event) throws IOException, SAXException {

        XML a = new XML();
        User u;
        u = a.lire();
        if (u.getRole().equals("0")) {
            Stage primarya = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProgrammeMatch.fxml"));
            Scene scene = new Scene(root);
            primarya.setScene(scene);
            primarya.show();
            primarya.setResizable(false);

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else if (u.getRole().equals("Admin")) {
            Admin();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/User.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else if (u.getRole().equals("Utilisateur")) {
            Stage primarya = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ProgrammeMatch.fxml"));
            Scene scene = new Scene(root);
            primarya.setScene(scene);
            primarya.show();
            primarya.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }

    }

    @FXML
    private void equipesG(ActionEvent event) throws IOException, SAXException {

        XML a = new XML();
        User u;
        u = a.lire();
        if (u.getRole().equals("0")) {
            Stage primarya = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ViewAffGroupe.fxml"));
            Scene scene = new Scene(root);
            primarya.setScene(scene);
            primarya.show();
            primarya.setResizable(false);

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else if (u.getRole().equals("Admin")) {
            Admin();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/AffichageListEquipe.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else if (u.getRole().equals("Utilisateur")) {
            Stage primarya = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ViewAffGroupe.fxml"));
            Scene scene = new Scene(root);
            primarya.setScene(scene);
            primarya.show();
            primarya.setResizable(false);

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        }
    }

    @FXML
    private void statBet(ActionEvent event) throws IOException, SAXException {

        XML a = new XML();
        User u;
        u = a.lire();
        if (u.getRole().equals("0")) {
            Stage primarya = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));
            Scene scene = new Scene(root);
            primarya.setScene(scene);
            primarya.show();
            primarya.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else if (u.getRole().equals("Admin")) {
            Admin();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/StatPari.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else if (u.getRole().equals("Utilisateur")) {
            Utilisateur();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Parierrrr.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        }

    }

    @FXML
    private void cadeauGestion(ActionEvent event) throws IOException, SAXException {

        XML a = new XML();
        User u;
        u = a.lire();
        if (u.getRole().equals("0")) {
            Stage primarya = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));
            Scene scene = new Scene(root);
            primarya.setScene(scene);
            primarya.show();
            primarya.setResizable(false);

            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();
        } else if (u.getRole().equals("Admin")) {
            Admin();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/GestionRecompense.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();

            primaryStage.setResizable(false);
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        } else if (u.getRole().equals("Utilisateur")) {
            Utilisateur();
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/Presentation/recompense.fxml"));

            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            final Node source = (Node) event.getSource();
            final Stage stage = (Stage) source.getScene().getWindow();
            stage.close();

        }

    }

    @FXML
    private void connecter(ActionEvent event) throws IOException {
        Stage primarya = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        Scene scene = new Scene(root);
        primarya.setScene(scene);
        primarya.show();
          primarya.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

}
