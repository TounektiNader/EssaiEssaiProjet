package Presentation;

import Utils.XML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NaivgationDrawer extends Application {
    
    public static Boolean isSplashLoaded = false;
    
    @Override
    public void start(Stage stage) throws Exception {
        XML a= new  XML();
       a.Ecrire("0","0","0","0","0","0","0",0,"0","0");
        Parent root = FXMLLoader.load(getClass().getResource("Accueil.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Splash Screen");
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    
}
