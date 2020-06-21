/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entite.Employe;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Array;
import util.ConnexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Wael
 */
public class GestionEmploye {

    private final Connection cnx;
    GestionClient gc = new GestionClient();
    public ObservableList<Employe> Elist = FXCollections.observableArrayList();

    public GestionEmploye() {
        this.cnx = ConnexionBD.getinstance().getCnx();

    }

    public void ajouterEmploye(Employe c) {
        Statement st;
        
        try {
            st = cnx.createStatement();
            String req = "INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES (NULL, '" + c.getLogin()+ "', '" + c.getLogin()+ "', '"+ c.getEmail()+ "', '"+ c.getEmail()+ "', '1', NULL, '"+ c.getMdp()+ "', NULL, NULL, NULL, 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Services.GestionEmploye.ajouterEmploye() Login Dupliquee");
        }
        updateEmployeList();

    }

   

    public void modifierEmploye(String login, Employe e) {
        PreparedStatement pt;
        gc.modifierClient(login, e);
        updateEmployeList();

    }

    public void supprimerEmploye(String login) {
        gc.supprimerClient(login);
        updateEmployeList();
    }
    
    public boolean verifyEmploye(String login) {
        return gc.verifyClient(login);

    }
    
    public void updateEmployeList(){
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from fos_user where roles = 'a:1:{i:0;s:10:\"ROLE_ADMIN\";}'");
            ResultSet rs = pt.executeQuery();
            Elist.clear();
            while (rs.next()) {
                String pwrd=rs.getString("password");
                if (pwrd.contains("{")) {
            pwrd = pwrd.substring(0, pwrd.indexOf("{"));
        } 
                Employe f = new Employe(rs.getString(1), rs.getString(1), rs.getString(1), pwrd, rs.getString(3), "0","Admin",new BigDecimal(BigInteger.ZERO));
                
                System.out.println(f);
                
                Elist.add(f);
               
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionEmploye.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Employe getEmploye(String login){
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from fos_user where username='"+login+"'");
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                
                return new Employe(login, login, login, rs.getString("password"), rs.getString("email"), "0", "Admin", new BigDecimal(0));
            }
        } catch (SQLException ex) {
            System.out.println("Services.GestionEmploye.afficherEmploye() "+ex.getMessage());
        }
        return new Employe();
    }

    public Employe getFOS(String login) {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from fos_user where username='"+login+"'");
            ResultSet rs = pt.executeQuery();
            if (rs.next()) {
                String poste;
                if(rs.getString("roles").length()<10)
                    poste="client";
                else
                    poste="admin";
                return new Employe(login, login, login, rs.getString("password"), rs.getString("email"), "0", poste, new BigDecimal(0));
            }
        } catch (SQLException ex) {
            System.out.println("Services.GestionEmploye.afficherEmploye() "+ex.getMessage());
        }
        return new Employe();
    }
}
