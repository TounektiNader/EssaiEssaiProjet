/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import DateStroge.MyConnection;
import iService.iServicesRealTime;
import Entity.RealTime;
import Entity.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author hedih
 */
public class ServiceRealTime implements iServicesRealTime {
Connection connexion;
    public ServiceRealTime() {
        connexion = MyConnection.getInstance().getConnexion();
    }
    @Override
    public void ajouterRealTime(RealTime real) {
        try {
            System.out.println(real.getPartie().getIdMatch()+" action "+real.getJoueur().getIdjoueur()+"");
            String query = "INSERT INTO realtime (actions,temps,idJoueur,idPartie) "
                    + "values ( '"+real.getActions()+"','"+real.getTemps()+"','"+real.getJoueur().getIdjoueur()+"','"+real.getPartie().getIdMatch()+"')";
            Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            

            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }

    @Override
    public String AfficherAction(int IdPartie) {
        try {
             
            String query = "SELECT * FROM realtime WHERE temps=(SELECT MAX(temps) FROM realtime where idPartie='"+IdPartie+"')";
            Statement stm= connexion.createStatement();
            ResultSet resultat = stm.executeQuery(query);
            while ( resultat.next() ) {
              
    String Action = resultat.getObject(3).toString();
                System.out.println(Action);
   return Action;
            }
            
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
       return null;
    }
    
}
