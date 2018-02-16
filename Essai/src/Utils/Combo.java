/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import DateStroge.MyConnection;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;


/**
 *
 * @author Nader
 */

public class Combo {
     PreparedStatement stmt;
    Connection conn;
    ResultSet rs;
      private ObservableList<String> data;
      
    public Combo(){
         conn = MyConnection.getInstance().getConnexion();
    
    }
   
    public List<String> fillGroupe(){
        List<String> list = new ArrayList<String>();
        String sql ="select * from groupe";
    try{
        
       stmt=conn.prepareStatement(sql);
       rs=stmt.executeQuery();
       while(rs.next()){
           String grou = rs.getString("type");
           list.add(grou);
       }
    }catch (SQLException ex) {

        }
    return list ; 
    }
    
     public List<String> fillTour(){
        List<String> list = new ArrayList<String>();
        String sql ="select * from tour";
    try{
        
       stmt=conn.prepareStatement(sql);
       rs=stmt.executeQuery();
       while(rs.next()){
           String tour = rs.getString("type");
           list.add(tour);
       }
    }catch (SQLException ex) {

        }
    return list ; 
    }
    
      public List<String> fillEquipe(){
        List<String> list = new ArrayList<String>();
        String sql ="select * from equipe";
    try{
        
       stmt=conn.prepareStatement(sql);
       rs=stmt.executeQuery();
       while(rs.next()){
           String equipe = rs.getString("nomEquipe");
           list.add(equipe);
       }
    }catch (SQLException ex) {

        }
    return list ; 
    }
      
        public List<String> fillStade(){
        List<String> list = new ArrayList<String>();
        String sql ="select * from stades";
    try{
        
       stmt=conn.prepareStatement(sql);
       rs=stmt.executeQuery();
       while(rs.next()){
           String nomStade = rs.getString("nomstade");
           list.add(nomStade);
       }
    }catch (SQLException ex) {

        }
    return list ; 
    }
      
      
}
