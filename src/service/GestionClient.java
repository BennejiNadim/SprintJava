/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entite.Client;
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
public class GestionClient {
    
    private final Connection cnx;
    public ObservableList<Client> Clist = FXCollections.observableArrayList();

    public GestionClient() {
        this.cnx = ConnexionBD.getinstance().getCnx();
    }

    public void ajouterClient (Client c) {
        Statement st;
        try {
            st = cnx.createStatement();
            String req = "INSERT INTO `fos_user` (`id`, `username`, `username_canonical`, `email`, `email_canonical`, `enabled`, `salt`, `password`, `last_login`, `confirmation_token`, `password_requested_at`, `roles`) VALUES (NULL, '" + c.getLogin()+ "', '" + c.getLogin()+ "', '"+ c.getEmail()+ "', '"+ c.getEmail()+ "', '1', NULL, '"+ c.getMdp()+ "', NULL, NULL, NULL, 'a:0:{}')";
            st.executeUpdate(req);
        } catch (SQLException ex) {
            System.out.println("Services.GestionClient.ajouterClient(): Login dupliquee");
        }
        updateClientList();

    }


    public void modifierClient(String login,Client c) {
        PreparedStatement pt;
        try {
            //UPDATE `fos_user` SET `username` = ?, `username_canonical` = ?, `email` = ?, `email_canonical` = ?, `password` = ? WHERE `fos_user`.`username` = ?; 
            pt = cnx.prepareStatement("UPDATE `fos_user` SET `username` = ?, `username_canonical` = ?, `email` = ?, `email_canonical` = ?, `password` = ? WHERE `fos_user`.`id` = ?");
            pt.setString(1, c.getLogin());
            pt.setString(2, c.getLogin());
            pt.setString(3, c.getEmail());
            pt.setString(4, c.getEmail());
            pt.setString(5, c.getMdp());
            pt.setString(6, login);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateClientList();

    }

    public void supprimerClient(String login) {
        PreparedStatement pt;
        try {
            pt = cnx.prepareStatement("delete from fos_user where username = ?");
            pt.setString(1, login);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        updateClientList();
    }
    
    public boolean verifyClient(String login) {
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from fos_user where username = '"+login+"'");
            ResultSet rs = pt.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(GestionClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }
    
    public void updateClientList(){
        try {
            PreparedStatement pt = cnx.prepareStatement("select * from fos_user where roles = 'a:0:{}'");
            ResultSet rs = pt.executeQuery();
            Clist.clear();
            while (rs.next()) {
                String pwrd=rs.getString("password");
                if (pwrd.contains("{")) {
            pwrd = pwrd.substring(0, pwrd.indexOf("{"));
        } 
                
                Client f = new Client(rs.getString(1), rs.getString(1), rs.getString(1), pwrd, rs.getString(3), "0");
                
                
                Clist.add(f);
               
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(GestionFacture.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
