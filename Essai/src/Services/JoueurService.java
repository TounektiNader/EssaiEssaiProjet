/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entity.Equipe;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import DateStroge.MyConnection;
import iService.IJoueur;
import Entity.Joueurs;



import java.io.FileInputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Aziz Amouri
 */
public class JoueurService implements IJoueur{
   PreparedStatement stmt;
    Connection conn;
    ObservableList<Joueurs> data;
    ResultSet rs;

    public JoueurService() {
        this.conn = MyConnection.getInstance().getConnexion();
    }
    
@Override
    public void ajouterJoueur(Joueurs J,String path)/*--------------------------------------------------*/{
        
        try {
            System.out.println("Adding file now");
            Statement pstm=conn.createStatement();
            FileInputStream input = new FileInputStream("testing.xlsx");
            OPCPackage fs = null;
            try {
                fs = OPCPackage.open(input);
            } catch (InvalidFormatException ex) {
                Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
            }
            XSSFWorkbook wb;
            wb = new XSSFWorkbook(fs);
            XSSFSheet sheet = wb.getSheetAt(0);
            
            Row row;
            for(int i=0; i<=sheet.getLastRowNum(); i++){
                row = sheet.getRow(i);
                String NomJoueur = row.getCell(0).getStringCellValue();
                String PrenomJoueur = row.getCell(1).getStringCellValue();
                String Position= row.getCell(2).getStringCellValue();
                int Equipe = (int) row.getCell(3).getNumericCellValue();
                
               
                String insert="Insert into joueurs(nomJoueur,prenomJoueur,postion,idEquipe)"+"Values('"+NomJoueur+"','"+PrenomJoueur+"','"+Position+"','"+Equipe+"');";
                
                pstm.executeUpdate(insert);
                System.out.println("import rows "+i);
            }
            conn.commit();
            pstm.close();
            //conn.close();
            input.close();
            System.out.println("Success import excel to mysql table");
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException ex) {
           Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    
   
     
    

    @Override
    public void supprimerJoueur() /*--------------------------------------------------*/{
        try {
            Statement stmt=conn.createStatement();
            stmt.executeUpdate("DELETE  FROM `joueurs`");
            //System.out.println("Le joueuer a ete suprimer");
            
        } catch (Exception e) {
            System.err.println("Echec");
        }
    
        
    }

//    @Override
//    public void ModifierJoueur(Joueurs J)/*---------------------inutile-----------------------------*/ {
//        try {
//
//           Statement stmtt=conn.createStatement();
//            stmtt.executeUpdate("UPDATE Joueurs Set (nomJoueur='"+J.getNomJoueur()+",prenomJoueur='"+J.getPrenomJoueur()+"',imageJoueur='"+J.getImageJoueur()+"'Postion='"+J.getPosition()+"',idEquipe='"+J.getEquipe()+"') WHERE nomJoueur='"+J.getEquipe()+"'");
//        } catch (Exception e) {
//            System.err.println("Echec");
//        }
//        
//    }
    
    public Equipe getEtquipe(int idEquipe){
    Equipe equipe = new Equipe();

        try {

            String sql = "select * from equipe where idEquipe=?;";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1,idEquipe);

            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                equipe.setIDEquipe(rs.getInt("idEquipe"));
                equipe.setNomEquipe(rs.getString("NomEquipe"));
                equipe.setEntraineur(rs.getString("Entraineur"));
                 equipe.setContinent(rs.getString("Continent"));
                 equipe.setDrapeau(rs.getString("Drapeau"));
                 equipe.setGroupe(rs.getString("Groupe"));
                 equipe.setButMarque(rs.getInt("ButMarque"));
                 equipe.setButEncaisse(rs.getInt("butEncaisse"));
                 equipe.setMatchJouee(rs.getInt("MatchJouee"));
                 equipe.setMatchNull(rs.getInt("MatchNull"));
                 equipe.setMatchGagne(rs.getInt("MatchGagne"));
                 equipe.setMatchperdu(rs.getInt("MatchPerdu"));
                 equipe.setNombrePoints(rs.getInt("NombrePoints"));
                 
                 

            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return equipe;
    }
        
    
   /*@Override
    public  List<Joueurs> affichersJoueurs() {
        List<Joueurs>jou= new ArrayList<>();
        //data = FXCollections.observableArrayList();
        //Equipe equipe = getEtquipe(); 
        Joueurs jj=new Joueurs();
        String req="select j.nomJoueur,j.prenomJoueur,j.postion from equipe e, joueurs j where j.idEquipe=e.idEquipe";
        
        try {       
            Statement ste=conn.createStatement();
            
           ResultSet result= ste.executeQuery(req);
            
           while(result.next()){
              // jj.setNomEquipe(rs.getString("NomEquipe"));
               jj.setNomJoueur(rs.getString("nomJoueur"));
               jj.setPrenomJoueur(rs.getString("prenomJoueur"));
              // jj.setImageJoueur(rs.getString("imageJoueur"));
               jj.setPosition(rs.getString("pstion"));
               //data.addAll(jj);
               jou.add(jj);
           }
            
        } catch (SQLException e) {
            System.err.println("probleme"+e.getMessage());
        }
       //return data;
        return jou;
}
    
     
    public  ObservableList<Joueurs> affichersJoueur() {
        List<Joueurs>jou= new ArrayList<>();
         ObservableList<Joueurs> 
        Equipe equipe = getEtquipe(1); 
        Joueurs jj=new Joueurs();
        String req="select * from joueurs";
        
        try {       
            Statement ste=conn.createStatement();
            
           ResultSet result= ste.executeQuery(req);
            
           while(result.next()){
              // jj.setNomEquipe(rs.getString("NomEquipe"));
               jj.setNomJoueur(rs.getString("nomJoueur"));
               jj.setPrenomJoueur(rs.getString("prenomJoueur"));
               jj.setPosition(rs.getString("postion"));
               data.addAll(jj);
           }
            
        } catch (SQLException e) {
            System.err.println("probleme"+e.getMessage());
        }
       return data;
    
    @Override
    public EquipeFantasy get(int id) {
        Joueur E = new Joueur();
        try {
            String req = "SELECT * FROM joueur WHERE id=?";
            pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet res = pst.executeQuery();
            while (res.next()) {
                E.setId(id);
                E.setEquipe(new ServiceEquipe().get(res.getInt(2)));
                E.setNom(res.getString("nom"));
                E.setPrenom(res.getString("prenom"));
                E.setAge(res.getInt(5));
                E.setPoste(res.getString("poste"));
                E.setNumero(res.getInt(7));
                E.setClub(res.getString("club"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return E;
    }
}*/
  @Override
    public  ObservableList<Joueurs>  getJouurs(int id) {
      
       data = FXCollections.observableArrayList();
        EquipeService equipeService = new EquipeService();
         {
           try {
               String req = "SELECT * FROM joueurs WHERE  idEquipe=?";
               PreparedStatement pst = conn.prepareStatement(req);
               pst.setInt(1, id);
               ResultSet res = pst.executeQuery();
               while (res.next()) {
                   
                     Joueurs E = new Joueurs();
                   E.setIdjoueur(id);                 
                   E.setNomJoueur(res.getString("nomJoueur"));
                   E.setPrenomJoueur(res.getString("prenomJoueur"));
                   E.setPosition(res.getString("postion"));
                   E.setEquipe(equipeService.get(id));
                   data.add(E);
                   
               }
           } catch (SQLException ex) {
               Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
               System.out.println("aaaaaaaa");
           }
        } 
        
        return data;
    }
    
   @Override
   public  List<Joueurs> affichersJoueurs(){
       List<Joueurs> liste = new ArrayList<>();
        {
            try {
                String req = "SELECT * FROM joueurs";
                PreparedStatement pst = conn.prepareStatement(req);
                ResultSet res = pst.executeQuery();
                while (res.next()) {
                    Joueurs J = new Joueurs(res.getInt(1), res.getString(2), res.getString(3), res.getString(4),res.getString(5), new EquipeService().get(res.getInt(6)));
                    liste.add(J);
                }
            } catch (SQLException ex) {
                Logger.getLogger(JoueurService.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("xxxxxx");
            }
        }
        return liste;
      
    
}
   public ObservableList<Joueurs>showme()
   {
         data = FXCollections.observableArrayList();
        List<Joueurs> joueurs = new ArrayList<>();

        try {
            String myQuery = "SELECT * FROM joueurs";
            Statement stm = conn.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(myQuery);
            while (rs.next()) {
                Joueurs p = new Joueurs();
                p.setIdjoueur(rs.getInt("IdJoueur"));
                p.setNomJoueur(rs.getString("nomJoueur"));
                p.setPrenomJoueur(rs.getString("prenomJoueur"));
                p.setPosition(rs.getString("postion"));
                data.add(p);
            }
        } catch (Exception e) {
            System.err.println("Echec");
        }
        return data;
    
   }
}
