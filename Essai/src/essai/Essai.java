/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package essai;

import Utils.XML;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Nader
 */
public class Essai extends Application {
        public static Boolean isSplashLoaded = false;
    @Override
    
    public void start(Stage stage) throws Exception {
         XML a= new  XML();
       a.Ecrire("0","0","0","0","0","0","0",0,"0","0");
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/Accueil.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { 
        launch(args);
    }
    
}
