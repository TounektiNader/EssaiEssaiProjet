/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DateStroge.MyConnection;
import iService.iServicesCode;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hedih
 */
public class ServiceCode implements iServicesCode {
Connection connexion;
    private int code;
    public ServiceCode() {
        connexion = MyConnection.getInstance().getConnexion();
    }




    @Override
    public void ajouterCode(String code) {
        try {
            String query = "INSERT INTO code (code) "
                    + "values ( '"+code+"')";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }  
    }

    @Override
    public void supprimerCode(String code) {
        try {
            String query = "DELETE FROM code WHERE code='"+code+"'";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        } 
    }

    @Override
    public String rechercheCode(String code) {
     try {
            
            String query = "Select * FROM code WHERE code='"+code+"'";
            Statement stm= connexion.createStatement();
            ResultSet resultat=stm.executeQuery(query);
            while ( resultat.next() ) {
              
    String code2 = resultat.getObject(2).toString();
    

    return code2;
            }
            
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
       return null;
    }    
    }
   
