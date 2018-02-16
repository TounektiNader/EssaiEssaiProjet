/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Bet;
import Entity.Partie;
import Services.ServiceBet;
import Utils.Pari;
import com.jfoenix.controls.JFXListView;
import java.io.FileInputStream;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class MesParisController implements Initializable {

    @FXML
    private Pane pan;
    @FXML
    private JFXListView<Label> list;

    private ObservableList<Bet> data;
    @FXML
    private Label labes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceBet service = new ServiceBet();
        Pari pari = new Pari();
        data = service.listBet("titou");
        int idPartie = 0;
        
        for (int i = 0; i <= data.size(); i++) {

            try {                
                
                idPartie = data.get(i).getPartie().getIdMatch();
                Label labelDetail = new Label(pari.matchDateTour(idPartie));
                System.out.println(labelDetail);
                Label lbl = new Label(pari.matchDateTour(idPartie)+"            "+pari.matchParier(idPartie));
                System.out.println(pari.matchParier(idPartie));
                if(data.get(i).getEtat().equals("Perte")){
                Image img = new Image("/images/error.png");
                ImageView im = new ImageView(img);

                im.setFitHeight(30);
                im.setFitWidth(30);
                lbl.setGraphic(im);
                }
                else if(data.get(i).getEtat().equals("Gain")){
                    Image img = new Image("/images/success.png");
                ImageView im = new ImageView(img);

                im.setFitHeight(30);
                im.setFitWidth(30);
                lbl.setGraphic(im);
                }
              
            list.getItems().add(lbl);
            } catch (Exception ex) {
            }
        }
    }
 
   

    @FXML
    private void imprime(MouseEvent event) {
//          PrintReport printReport= new PrintReport();
  //  printReport.showReport();

    }
 

}
