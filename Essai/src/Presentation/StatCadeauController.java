/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Entity.Recompense;
import Entity.User;
import Services.CadeauService;
import Services.RecompenseService;
import Services.ServiceBet;
import Utils.Combo;
import Utils.XML;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.controlsfx.control.Notifications;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author Nader
 */
public class StatCadeauController implements Initializable {

    @FXML
    private Text pourcentageVoit;
    @FXML
    private Text nombreVoitures;
    @FXML
    private Text nombreTotal1;
    @FXML
    private Text pourcentageTicket;
    @FXML
    private Text nombreTotal2;
    @FXML
    private Text pourcentageBon;
    @FXML
    private Text nombreTotal3;
    @FXML
    private Text pourcentageTele;
    @FXML
    private Text nombrreTel;
    @FXML
    private Text nombreTotal4;
    @FXML
    private BarChart<String, Integer> chartComp;
    @FXML
    private NumberAxis absis;
    @FXML
    private CategoryAxis Axis;
    @FXML
    private PieChart chart;
    @FXML
    private JFXComboBox<String> comboUser;
    @FXML
    private Text nombreTicket;
    @FXML
    private Text nombreBon;
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

    private ObservableList<User> dataUser;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        XML x = new XML();

        User user = new User();
        try {
            user = x.lire();
            username.setText(user.getUsername());
        } catch (SAXException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StatPariController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // username.setText(value);

        Combo com = new Combo();
        comboUser.getItems().addAll(com.fillUserUser());

        CadeauService cadeauService = new CadeauService();

        int totalCadeau = cadeauService.listCadeau().size();

        int nombreVoitur = cadeauService.listCadeau("Voiture").size();
        nombreVoitures.setText("" + nombreVoitur);

        int nombreTe = cadeauService.listCadeau("Telephone").size();
        nombrreTel.setText("" + nombreTe);
        int nombreBo = cadeauService.listCadeau("Bon_Achat").size();
        nombreBon.setText("" + nombreBo);

        int nombreTick = cadeauService.listCadeau("Ticket").size();
        nombreTicket.setText("" + nombreTick);

        nombreTotal1.setText("/" + totalCadeau);
        nombreTotal2.setText("/" + totalCadeau);
        nombreTotal3.setText("/" + totalCadeau);
        nombreTotal4.setText("/" + totalCadeau);

        pourcentageVoit.setText("" + calculPourcentage(totalCadeau, nombreVoitur));
        pourcentageBon.setText("" + calculPourcentage(totalCadeau, nombreBo));
        pourcentageTele.setText("" + calculPourcentage(totalCadeau, nombreTe));
        pourcentageTicket.setText("" + calculPourcentage(totalCadeau, nombreTick));

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

        dataUser = serviceBet.GetUser();

        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (User u : dataUser) {
            int nombreCadeau = cadeauService.nombreRecompenseParPersonne(u.getUsername());
            series.getData().add(new XYChart.Data<>(u.getUsername(), nombreCadeau));

        }
        chartComp.getData().add(series);

    }

    @FXML
    private void afficherChart(MouseEvent event) {

        int nbTickets = 0;
        int nbVoitures = 0;
        int nbBon_Achat = 0;
        int nbTelephone = 0;

        CadeauService cadeauService = new CadeauService();
        RecompenseService recompenseService = new RecompenseService();
        List<Recompense> recompenses = new ArrayList<Recompense>();
        recompenses = recompenseService.listRecompense(comboUser.getSelectionModel().getSelectedItem());

        for (Recompense r : recompenses) {

            if (r.getCadeau().getCategorie().equals("Ticket")) {
                nbTickets = nbTickets + 1;
            } else if (r.getCadeau().getCategorie().equals("Voiture")) {
                nbVoitures = nbVoitures + 1;
            }
            if (r.getCadeau().getCategorie().equals("Bon_Achat")) {
                nbBon_Achat = nbBon_Achat + 1;
            }
            if (r.getCadeau().getCategorie().equals("Telephone")) {
                nbTelephone = nbTelephone + 1;
            }

        }

        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(
                new PieChart.Data("Tickets", nbTickets),
                new PieChart.Data("Voiture", nbVoitures),
                new PieChart.Data("Bon_Achat", nbBon_Achat),
                new PieChart.Data("Telephone", nbTelephone));

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
    private void GererRecompense(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/GestionRecompense.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void StatRecompense(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/StatPari.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererMatch(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/User.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererResultat(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/ResultatMatchA.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererRecom(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/MenuAdmin.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererEquipe(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/AffichageListEquipe.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void GererActu(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/Presentation/listActualites.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        final Node source = (Node) event.getSource();
        final Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void excelll(ActionEvent event) throws IOException, SQLException {

        Node source = (Node) event.getSource();

        FileChooser chooser = new FileChooser();

        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Excel Files(*.xls)", "*.xls");
        chooser.getExtensionFilters().add(filter);

        File file = chooser.showSaveDialog(source.getScene().getWindow());
        if (file != null) {
            exporterExcel(file);

        }
    }

    public void exporterExcel(File file) throws SQLException, IOException {
        try {

            RecompenseService rec = new RecompenseService();
            FileOutputStream fileOut;
            fileOut = new FileOutputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook();
            HSSFSheet workSheet = workbook.createSheet("Feuille Recompense");
            HSSFRow row1 = workSheet.createRow((short) 0);
            workSheet.setZoom(130);

            Header header = workSheet.getHeader();
            header.setCenter("Votre Fichier Excel Concertant les Recompenses");

            Footer footer = workSheet.getFooter();
            footer.setCenter("Russia 2018");

            row1.setHeightInPoints(30);

            createCellTitre(workbook, row1, 0, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "Username");
            //createCellTitre(workbook, row1, 1, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "Type Cadeau ");
            createCellTitre(workbook, row1, 1, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "Nombre des cadeaux");
            createCellTitre(workbook, row1, 2, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "Nombre de jetons");
             createCellTitre(workbook, row1, 3, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "Condition");
               

            HSSFRow row2;

            //List<Recompense> listRecompenses = new ArrayList<Recompense>();
            //listRecompenses.addAll(rec.listRecompenseTrie());
            for (int i = 0; i < rec.listRecompenseTrie().size(); i++) {
                int a = rec.listRecompenseTrie().size();

                row2 = workSheet.createRow((short) i + 1);
                // row2.setRowNum(i);
                workSheet.setColumnWidth(0, 4000);
                workSheet.setColumnWidth(1, 5000);
                workSheet.setColumnWidth(2, 7000);
                workSheet.setColumnWidth(3, 6000);
              //  workSheet.setColumnWidth(4, 6000);

                createCellData(workbook, row2, 0, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "" + rec.listRecompenseTrie().get(i).getUser().getUsername());
               // createCellData(workbook, row2, 1, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "" + rec.listRecompenseTrie().get(i).getCadeau().getType());
                createCellData(workbook, row2, 1, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "" + rec.listRecompenseTrie().get(i).getCount());
                createCellData(workbook, row2, 2, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, "" + rec.listRecompenseTrie().get(i).getUser().getJeton());
                 row2.createCell(3).setCellFormula("IF("+rec.listRecompenseTrie().get(i).getUser().getJeton()+">20,\" Solde de Jeton Gagant \",\"Solde de Jeton Perte\")");
//" =IF(D2>20;\" Solde de Jeton Gagant \";\"Solde de Jeton Perte\")"
            }
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
            Notifications notificationbuilder = Notifications.create()
                    .title("Excel")
                    .text("Le fichier a été généré avec succes!")
                    .graphic(null)
                    .position(Pos.CENTER)
                    .onAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            try {
                                Desktop.getDesktop().open(file);
                            } catch (IOException ex) {
                                Logger.getLogger(StatCadeauController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });

            notificationbuilder.showConfirm();

        } catch (IOException e) {
        }
    }

    private static void createCellData(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign, String nomCol) {
        Cell cell = row.createCell(column);
        cell.setCellValue(nomCol);
        CellStyle style = wb.createCellStyle();
        style.setAlignment(halign);
        style.setVerticalAlignment(valign);
        cell.setCellStyle(style);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLUE.getIndex());
        cell.setCellStyle(style);

    }

    private static void createCellTitre(Workbook wb, Row row, int column, HorizontalAlignment halign, VerticalAlignment valign, String nomCol) {
        Cell cell = row.createCell(column);
        cell.setCellValue(nomCol);

        Font font = wb.createFont();
        font.setFontHeightInPoints((short) 11);
        font.setColor((short)40);
        font.setFontName("Lucida Fax");
       // font.setItalic(true);
        font.setBold(true);

        CellStyle style = wb.createCellStyle();
       

//appliquer le style à la cellule 3
        

        style.setAlignment(halign);
        style.setVerticalAlignment(valign);

        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.GREEN.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLUE.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());

        style.setFont(font);
        cell.setCellStyle(style);

    }

    @FXML
    private void decon(ActionEvent event) throws IOException {
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
}
