/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Services.ServiceRealTime;
import java.awt.image.Kernel;
import java.io.IOException;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author hedih
 */

public class LiveController implements Initializable  {
@FXML
private Label text;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
TimerTask timerTask = new testlive();

Timer timer = new Timer(true);

timer.scheduleAtFixedRate(timerTask, 0,3);

System.out.println("Lancement execution");
try {

Thread.sleep(1);

} catch (InterruptedException e) {

e.printStackTrace();

}
}       
 }
    



    
