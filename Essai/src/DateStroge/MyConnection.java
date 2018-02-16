/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DateStroge;

import java.sql.*;

/**
 *
 * @author Nader
 */
public class MyConnection {

    private static MyConnection instance = null;
    final static String url = "jdbc:mysql://localhost:3306/russia";
    final static String username = "root";
    final static String password = "";
    public Connection connexion;


    /*  public static Connection connectDb() {

        String url = "jdbc:mysql://localhost:3306/work";
        String username = "root";
        String password = "";
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion établie");
            return conn;
        } catch (SQLException e) {
            System.out.println("Connexion Failed");
            return null;
        }
    }*/
    private MyConnection() {
        try {
            connexion = DriverManager.getConnection(url, username, password);
            System.out.println("Connexion établie");

        } catch (SQLException e) {
            System.out.println("Connexion Failed");

        }

    }

    public static MyConnection getInstance() {
        if (instance == null) {
            instance = new MyConnection();
        }

        return instance;
    }

    public Connection getConnexion() {
        return connexion;
    }

  

}
