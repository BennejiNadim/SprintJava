/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Djemaiel
 */
public class ConnexionBD2 {

    private static String url = "jdbc:mysql://localhost:3306/agora1";
    private static String usr = "root";
    private static String pwd = "";
    private static ConnexionBD2 cbd;
    private static Connection cnx;

    public static Connection getCnx() {
        return cnx;
    }

    private ConnexionBD2() {
        try {
            // TODO code application logic here

            cnx = DriverManager.getConnection(url, usr, pwd);
            System.out.println("connexion etabli!");
        } catch (SQLException ex) {
            System.out.println("erreur" + ex.getMessage());
        }
    }

    public static ConnexionBD2 getinstance() {
        if (cbd == null) {
            cbd = new ConnexionBD2();
        }
        return cbd;
    }

}
