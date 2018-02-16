/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import iService.IEquipe;
import Entity.Equipe;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jdk.nashorn.internal.ir.CatchNode;
import DateStroge.MyConnection;
import iService.IJoueur;
import Entity.Joueurs;
/**
 *
 * @author elbrh
 */
public class JoueurService implements IJoueur{
   PreparedStatement stmt;
    Connection conn;
    ResultSet rs;

    public JoueurService() {
        this.conn = MyConnection.getInstance().getConnexion();
    }
    
@Override
    public void ajouterJoueur(Joueurs J){
        try {
            Equipe e = null;
            Scanner Js = new Scanner(System.in);
            
            
            System.out.println("Veuillez Saisir une Nom  : ");
            String Nom = Js.next();
            System.out.println("Veuillez Saisir un Prenom : ");
            String Prenom=Js.next();
            System.out.println("Veuillez Saisir une image : ");
            String Image=Js.next();
            System.out.println("Veuillez saisir la position du joueur");
            String position=Js.next();
            System.out.println("Veuillez saisir l'equipe");
            Object Equipe=Js.next();
            
            String insert="Insert into joueurs(NomJoueur,PrenomJoueur,image,Position,Equipe)"+"Values(?,?,?,?,?);";
            PreparedStatement st=conn.prepareStatement(insert);
            st.setString(1,Nom);
            st.setString(2,Prenom);
            st.setString(3,Image);
            st.setString(4,position);
            //st.setString(5,Equipe);
            st.executeUpdate();
            System.out.println("Le joueur "+J.getNomJoueur()+"a ete ajouter");
            
        } catch (Exception e) {
            System.err.println("Echec");
        }
    }
    
   
     
    

    @Override
    public void supprimerJoueur(Joueurs J) {
        try {
            Statement stmt=conn.createStatement();
            System.out.println("Veuillez entree le nom du joueur à supprimée");
           Scanner sc = new Scanner(System.in);
            String donnee=sc.nextLine();
          
            stmt.executeUpdate("Delete from Joueurs where NomJoueur='"+donnee+"'");
            System.out.println("Le joueuer a ete suprimer");
            
        } catch (Exception e) {
            System.err.println("Echec");
        }
    
        
    }

    @Override
    public void ModifierJoueur(Joueurs J) {
        try {
           System.out.println("Veuillez entree le nom du joueur a modifier");
           Scanner sc = new Scanner(System.in);
           String nom=sc.nextLine();
           String prenom=sc.nextLine();
           String position=sc.nextLine();
           Object equipe=sc.nextLine();
           Statement stmtt=conn.createStatement();
            stmtt.executeUpdate("UPDATE Joueurs Set (NomJoueur='"+nom+",PrenomJoueur='"+prenom+"',Position='"+position+"',Equipe='"+equipe+"') WHERE NomJoueur='"+nom+"'");
        } catch (Exception e) {
            System.err.println("Echec");
        }
        
    }
    
    public Equipe getEtquipe(int idEquipe){
    Equipe equipe = new Equipe();

        try {

            String sql = "Select * from equipe where idEquipe=? ";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idEquipe);

            ResultSet rs = stmt.executeQuery();

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
        
    @Override
         public  List<Joueurs> affichersJoueurs() {
         List<Joueurs>jou= new ArrayList<>();
        Equipe equipe = new Equipe();
              
         String req="select* from  joueurs";
        
        try {       
            Statement ste=conn.createStatement();
            
           ResultSet result= ste.executeQuery(req);
            
           while(result.next()){
               
               int IdJoueur=result.getInt("IdJoueur");
               String NomJoueur=result.getString("nomJoueur");
               String PrenomJoueur=result.getString("prenomJoueur");
               String ImageJoueur=result.getString("imageJoueur");
               String Position=result.getString("postion");
              equipe=getEtquipe(rs.getInt("idEquipe"));
               
               
               
               jou.add(new Joueurs(IdJoueur,NomJoueur,PrenomJoueur,ImageJoueur,Position,equipe));
           }
            
        } catch (SQLException e) {
            System.err.println("probleme"+e.getMessage());
        }
       return jou;
}

       @Override
    public List<Joueurs> affichersJoueurs(int IdEquipe) {
        List<Joueurs>jou= new ArrayList<>();
              Equipe equipe=new Equipe();
         String req="select* from  joueurs where idEquipe='"+IdEquipe+"'";
        
        try {       
            Statement ste=conn.createStatement();
            
           ResultSet result= ste.executeQuery(req);
            
           while(result.next()){
               
               int IdJoueur=result.getInt("IdJoueur");
               String NomJoueur=result.getString("nomJoueur");
               String PrenomJoueur=result.getString("prenomJoueur");
               String ImageJoueur=result.getString("imageJoueur");
               String Position=result.getString("postion");
              
               
               
               
               jou.add(new Joueurs(IdJoueur,NomJoueur,PrenomJoueur,ImageJoueur,Position,null));
           }
            
        } catch (SQLException e) {
            System.err.println("probleme"+e.getMessage());
        }
       return jou;
    }

    @Override
    public Joueurs affichersJoueur(int Id) {
      Joueurs jou;
            
         String req="select* from  joueurs where Idjoueur='"+Id+"'";
        
        try {       
            Statement ste=conn.createStatement();
            
           ResultSet result= ste.executeQuery(req);
            
           while(result.next()){
               
               int IdJoueur=result.getInt("IdJoueur");
               String NomJoueur=result.getString("nomJoueur");
               String PrenomJoueur=result.getString("prenomJoueur");
               String ImageJoueur=result.getString("imageJoueur");
               String Position=result.getString("postion");
              
               
               
               
               jou = new Joueurs(IdJoueur,NomJoueur,PrenomJoueur,ImageJoueur,Position,null);
               return jou;
           }
            
        } catch (SQLException e) {
            System.err.println("probleme"+e.getMessage());
        }
       return null;
    }

    


    
}
