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
import Entity.Cadeau;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author elbrh
 */
public class EquipeService implements IEquipe{
    Connection conn ;
 private ObservableList<Equipe> data;
    public EquipeService() {
        this.conn = MyConnection.getInstance().getConnexion();
    }


    @Override

    public void ajouterEquipe(Equipe E){////////////////////////////////////////////////////////////////////////
        try {     
          
            
            String insert="Insert into equipe(NomEquipe,Entraineur,Continent,Drapeau,Groupe,ButMarque,butEncaisse,MatchJouee,MatchNull,MatchGagne,MatchPerdu,NombrePoints)"+"Values(?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement st=conn.prepareStatement(insert);
            st.setString(1,E.getNomEquipe());
            st.setString(2,E.getEntraineur());
            st.setString(3,E.getContinent());
            st.setString(4,E.getDrapeau());
            st.setString(5,E.getGroupe());
            st.setInt(6,E.getButMarque());
            st.setInt(7, E.getButEncaisse());
            st.setInt(8, E.getMatchJouee());
            st.setInt(9,E.getMatchNull());
            st.setInt(10,E.getMatchGagne());
            st.setInt(11,E.getMatchperdu());            
            st.setInt(12,E.getNombrePoints());            

            st.executeUpdate();
            System.out.println("Lequipe "+E.getNomEquipe()+"a ete ajouter");
            
        } catch (Exception e) {
            System.err.println("Echec");
        }
    }
     
    

    @Override
     public void afficherEquipe(Equipe E) {
        try {       
            String myQuery="SELECT * FROM Equipe";
            Statement stm=conn.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(myQuery);
            while(rs.next()){
               
                System.out.println("-----------");
                System.out.println("id:" +rs.getInt(1));
                System.out.println("NomEquipe:"+rs.getString(2) );
                System.out.println("Entraineur:" +rs.getString(3) );
                System.out.println("Continant:" +rs.getString(4));
                System.out.println("Drapeau:" +rs.getString(5) );
                System.out.println("groupe:" +rs.getString(6) );
            }}
        
            catch (Exception e) {
            System.err.println("Echec");
        }
}
     
    
 @Override
    public void supprimerEquipe(Equipe E) {////////////////////////////////////////////////////////////////////////
        try {
            Statement stmt=conn.createStatement();
          
            stmt.executeUpdate("Delete from Equipe where NomEquipe='"+E.getNomEquipe()+"'");
            System.out.println("Lequipe a ete suprimer");
            
        } catch (Exception e) {
            System.err.println("Echec");
        }
    
        
    }
    @Override
    public void ModifierEquipe(Equipe E) {////////////////////////////////////////////////////////////////////////
        try {
               String myQuery="UPDATE equipe Set NomEquipe='"+E.getNomEquipe()+"',Entraineur='"+E.getEntraineur()+"',Continent='"+E.getContinent()+"',Drapeau='"+E.getDrapeau()+"',Groupe='"+E.getGroupe()+"'where idEquipe='"+E.getIDEquipe()+"'";
           // String sql = "update equipe set NomEquipe=?,Entraineur=?,Contient
               
               
               Statement stm=conn.createStatement();
            stm.executeUpdate(myQuery);
        } catch (Exception e) {
            System.err.println("Echec");
        }
        
    }

    

    @Override
     public ObservableList <Equipe> afficherEquipes() {////////////////////////////////////////////////////////////////////////
         data = FXCollections.observableArrayList();
         List<Equipe>equipes= new ArrayList<>();
        
        try {       
            String myQuery="SELECT * FROM equipe";
            Statement stm=conn.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(myQuery);
            while(rs.next()){
                Equipe p = new Equipe();
                p.setIDEquipe(rs.getInt("idEquipe"));
                p.setNomEquipe(rs.getString("NomEquipe"));
                p.setEntraineur(rs.getString("Entraineur"));
                p.setContinent(rs.getString("Continent"));
                p.setDrapeau(rs.getString("Drapeau"));
                p.setGroupe(rs.getString("Groupe"));
                p.setButMarque(rs.getInt("ButMarque"));
                p.setButEncaisse(rs.getInt("butEncaisse"));
                p.setMatchJouee(rs.getInt("MatchJouee"));
                p.setMatchNull(rs.getInt("MatchNull"));
                p.setMatchGagne(rs.getInt("MatchGagne"));
                p.setMatchperdu(rs.getInt("MatchPerdu"));            
                p.setNombrePoints(rs.getInt("NombrePoints"));
                
                
               data.add(p);
            }}
        
            catch (Exception e) {
            System.err.println("Echec");
        }
        return data;
}
    
     
      
    public ObservableList<Equipe> afficherEquipes(String groupe) {
         List<Equipe>equipes= new ArrayList<>();
          data = FXCollections.observableArrayList();
         
        try {       
            String myQuery="SELECT * FROM equipe where Groupe='"+groupe+"';";
            Statement stm=conn.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(myQuery);
            while(rs.next()){
                Equipe p = new Equipe();
                p.setIDEquipe(rs.getInt("idEquipe"));
                p.setNomEquipe(rs.getString("NomEquipe"));
                p.setEntraineur(rs.getString("Entraineur"));
                p.setContinent(rs.getString("Continent"));
                p.setDrapeau(rs.getString("Drapeau"));
                p.setGroupe(rs.getString("Groupe"));
                p.setMatchJouee(rs.getInt("MatchJouee"));
                p.setMatchperdu(rs.getInt("MatchPerdu"));
                p.setMatchNull(rs.getInt("MatchNull"));
                p.setMatchGagne(rs.getInt("MatchGagne"));
                p.setButMarque(rs.getInt("ButMarque"));
               p.setButEncaisse(rs.getInt("butEncaisse"));
               p.setNombrePoints(rs.getInt("NombrePoints"));
                data.add(p);
            }}
        
            catch (Exception e) {
            System.err.println("Echec");
        }
        return data;
}
    
          public ObservableList <Equipe> afficherGroupe(String groupe) {////////////////////////////////////////////////////////////////////////
         List<Equipe>group= new ArrayList<>();
         data = FXCollections.observableArrayList();
         Equipe E = new Equipe();
        try {       
            String myQuery=("SELECT * FROM Equipe WHERE Groupe='"+groupe+"'");
             Statement stm=conn.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(myQuery);
            while(rs.next()){
                E.setNomEquipe(rs.getString("NomEquipe"));
                E.setEntraineur(rs.getString("Entraineur"));
                E.setContinent(rs.getString("Continant"));
                E.setDrapeau(rs.getString("Drapeau"));
                E.setGroupe(rs.getString("Groupe"));
                E.setButMarque(rs.getInt("ButMarque"));
                E.setButEncaisse(rs.getInt("butEncaisse"));
                E.setMatchJouee(rs.getInt("MatchJouee"));
                E.setMatchNull(rs.getInt("MatchNull"));
                E.setMatchGagne(rs.getInt("MatchGagne"));
                E.setMatchperdu(rs.getInt("MatchPerdu"));            
                E.setNombrePoints(rs.getInt("NombrePoints"));
                data.add(E);
     
         }} catch (Exception e) {
            System.err.println("Echec");
        }
      return data;
}

    
    
     @Override
    public int rechercheEquipe(String equipe) {
         try {       
            String myQuery="SELECT idEquipe FROM Equipe where NomEquipe='"+equipe+"' ";
            Statement stm=conn.createStatement();
            ResultSet rs;
            rs = stm.executeQuery(myQuery);
            while(rs.next()){
               
                
              return rs.getInt(1);
                
            }}
        
            catch (Exception e) {
            System.err.println("Echec");
        }
    
    
    
   return 0; 
}
     
     
    
    
     
}
