/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import Entity.Bet;
import Entity.Partie;
import Entity.User;
import static Presentation.GestionRecompenseController.rootP;
import Services.ServiceBet;
import Services.ServiceUser;
import Utils.Combo;
import Utils.XML;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class StatPariController implements Initializable {

    @FXML
    private Text pourcentageGain;
    @FXML
    private Text nombreGain;
    @FXML
    private Text nombreTotal1;
    @FXML
    private Text pourcentagePerte;
    @FXML
    private Text nombrePerte;
    @FXML
    private Text nombreTotal2;
    @FXML
    private BarChart<String, Integer> chartComp;
    @FXML
    private PieChart chart;
    @FXML
    private JFXComboBox<String> comboUser;
    private ObservableList<User> dataUser;
    private ObservableList<Bet> data;
    private ObservableList<Bet> dataGain;
    private ObservableList<Bet> dataPerte;
    private Text pourcentagePerte1;
    @FXML
    private Text nombreEnCours;
    @FXML
    private Text nombreTotal3;
    @FXML
    private Text pourcentageTraite;
    @FXML
    private NumberAxis absis;
    @FXML
    private CategoryAxis Axis;
    @FXML
    private AnchorPane root;
    @FXML
    private Pane paneE;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private JFXHamburger hamburger;
    
      public static AnchorPane rootP;
    @FXML
    private JFXButton b1;
    @FXML
    private JFXButton b11;
    @FXML
    private JFXButton b12;
    @FXML
    private JFXButton b13;
    @FXML
    private JFXButton b15;
    @FXML
    private JFXButton b14;
    @FXML
    private JFXButton b151;
    @FXML
    private JFXTextField username;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         XML x= new XML();
         
          User user = new User ();
        try {
            user = x.lire();
            username.setText(user.getUsername());
        } catch (SAXException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        }
           
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

        ServiceBet serviceBet = new ServiceBet();
        Combo com = new Combo();
        comboUser.getItems().addAll(com.fillUserUser());
        nombreTotal1.setText("/" + serviceBet.NombreTotalBet());
        nombreTotal2.setText("/" + serviceBet.NombreTotalBet());
        nombreTotal3.setText("/" + serviceBet.NombreTotalBet());
        nombrePerte.setText("" + serviceBet.NombreBetPerte());
        nombreGain.setText("" + serviceBet.NombreBetGain());
        nombreEnCours.setText("" + serviceBet.NombreBetTraite());

        pourcentageGain.setText("" + calculPourcentage(serviceBet.NombreTotalBet(), serviceBet.NombreBetGain()) + "%");
        pourcentagePerte.setText("" + calculPourcentage(serviceBet.NombreTotalBet(), serviceBet.NombreBetPerte()) + "%");
        pourcentageTraite.setText("" + calculPourcentage(serviceBet.NombreTotalBet(), serviceBet.NombreBetTraite()) + "%");

        dataUser = serviceBet.GetUser();
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (User u : dataUser) {
            series.getData().add(new XYChart.Data<>(u.getUsername(), u.getJeton()));

        }
        chartComp.getData().add(series);
        
     

    }

    @FXML
    private void afficherChart(MouseEvent event) {

        ServiceBet serviceBet = new ServiceBet();

        data = serviceBet.listBet(comboUser.getSelectionModel().getSelectedItem());
        dataGain = serviceBet.listBetGain(comboUser.getSelectionModel().getSelectedItem());
        dataPerte = serviceBet.listBetPerte(comboUser.getSelectionModel().getSelectedItem());

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Gain", dataGain.size()),
                new PieChart.Data("Perte", dataPerte.size()));

        chart.setData(list);

    }

    public double calculPourcentage(int nombreTotal, int nombre) {

        double d = 0;

        d = (((double) nombre * 100) / nombreTotal);
        DecimalFormat df = new DecimalFormat("########.00");
        String str = df.format(d);
        d = Double.parseDouble(str.replace(',', '.'));

        System.out.println(d);

        return d;

    }

    @FXML
    private void pdff(ActionEvent event) throws IOException {
         Document doc = new Document();

        try {
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\Nader\\Desktop\\rapportMaha.pdf"));
                doc.open();

//                doc.add(new Paragraph("-------------"));
                Image img = Image.getInstance("C:\\Users\\Nader\\Desktop\\pg.jpg");
                img.scaleAbsoluteHeight(92);
                img.scaleAbsoluteWidth(600);
                img.setAlignment(Image.ALIGN_CENTER);
                doc.add(img);
                doc.add(new Paragraph("   "));
              
                doc.add(new Phrase("Statistiques Paris", FontFactory.getFont("Arial", 20, Font.BOLDITALIC)));
//                Paragraph p = new Paragraph("Statistiques Paris");
//                 p.setAlignment(Paragraph.ALIGN_CENTER);
//                p.setFont(,20,Font.BOLD);
//               
             
                doc.add(new Paragraph("   "));

                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                PdfPCell cell;

                cell = new PdfPCell(new Phrase("Username", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Nationalite", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase("Jeton", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Nb_Bet_Tot", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Nb_Bet_Gain", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                table.addCell(cell);
                
                cell = new PdfPCell(new Phrase("Nb_Bet_Perte", FontFactory.getFont("Comic Sans MS", 12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.DARK_GRAY);
                 table.addCell(cell);
                
                 ////////////////////////////////////////////////////////////////////////////
                ServiceBet serviceBet = new ServiceBet();
              List<Bet>  listBett = new ArrayList<Bet>();
                listBett = serviceBet.listBetParPersonne();
             
                for (Bet bet : listBett) {

                    cell = new PdfPCell(new Phrase(bet.getUser().getUsername(), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(bet.getUser().getNationalite(), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.GRAY);
                    table.addCell(cell);

                     cell = new PdfPCell(new Phrase(""+bet.getUser().getJeton(), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(cell);
                    
                    cell = new PdfPCell(new Phrase(""+bet.getNombreBetPersonne(), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.GRAY);
                    table.addCell(cell);
                      
                      cell = new PdfPCell(new Phrase(""+serviceBet.nombreBetGain(bet.getUser().getUsername()), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.WHITE);
                    table.addCell(cell);
                    
                      cell = new PdfPCell(new Phrase(""+serviceBet.nombreBetPerte(bet.getUser().getUsername()), FontFactory.getFont("Comic Sans MS", 12)));

                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setBackgroundColor(BaseColor.GRAY);
                    table.addCell(cell);
                }
                /////////////////////////////////////////////////////////////////////////////
      
                doc.add(table);
                doc.close();
                Desktop.getDesktop().open(new File("C:\\Users\\Nader\\Desktop\\rapport.pdf"));

            } catch (FileNotFoundException ex) {
                Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (DocumentException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        }
      

    }

    @FXML
    private void GererRecompense(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/GestionRecompense.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void StatRecompense(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/StatCadeau.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererMatch(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/User.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererResultat(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ResultatMatchA.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererRecom(ActionEvent event) throws IOException {
          Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MenuAdmin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererEquipe(ActionEvent event) throws IOException {
        Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/AffichageListEquipe.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererActu(ActionEvent event) throws IOException {
         Stage primaryStage= new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/listActualites.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
          primaryStage.setResizable(false);
        
        final Node source =(Node) event.getSource();
        final Stage stage= (Stage)source.getScene().getWindow();
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
