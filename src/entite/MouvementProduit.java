/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;
import javafx.scene.control.CheckBox;

/**
 *
 * @author ASUS
 */
public class MouvementProduit {

    public MouvementProduit() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public enum src_dest{
        supplier,
        stock,
        magasin,
        client
    
    }

    private int id_mouvement_Produit;
    private int id_produit;
    private int id_facture;
    private src_dest source;
    private src_dest destination;
    private int quantité;
    private java.sql.Date date;

    public String getNom_produit() {
        return nom_produit;
    }

    public void setNom_produit(String nom_produit) {
        this.nom_produit = nom_produit;
    }
    private String nom_produit;
    private CheckBox ch_box;
    

    public MouvementProduit(int id_mouvement_Produit, int id_facture, src_dest source, src_dest destination, int quantité, java.sql.Date date,int id_produit) {
        this.id_mouvement_Produit = id_mouvement_Produit;
        this.id_facture = id_facture;
        this.source = source;
        this.destination = destination;
        this.quantité = quantité;
        this.date = date;
        this.id_produit=id_produit;
        this.ch_box=new CheckBox();
    }
    public MouvementProduit(int id_mouvement_Produit, int id_facture, src_dest source, src_dest destination, int quantité, java.sql.Date date,String nom_produit) {
        this.id_mouvement_Produit = id_mouvement_Produit;
        this.id_facture = id_facture;
        this.source = source;
        this.destination = destination;
        this.quantité = quantité;
        this.date = date;
        this.nom_produit=nom_produit;
        this.ch_box=new CheckBox();
    }

    public src_dest getSource() {
        return source;
    }

    public src_dest getDestination() {
        return destination;
    }

    public int getQuantité() {
        return quantité;
    }

    public java.sql.Date getDate() {
        return date;
    }

    public void setSource(src_dest source) {
        this.source = source;
    }

    public void setDestination(src_dest destination) {
        this.destination = destination;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public int getId_mouvement_Produit() {
        return id_mouvement_Produit;
    }

    public int getId_facture() {
        return id_facture;
    }

    public int getId_produit() {
        return id_produit;
    }

    public CheckBox getCh_box() {
        return ch_box;
    }

    public void setCh_box(CheckBox ch_box) {
        this.ch_box = ch_box;
    }

    public void setId_facture(int id_facture) {
        this.id_facture = id_facture;
    }

    @Override
    public String toString() {
        return "MouvementProduit{" + "id_mouvement_Produit=" + id_mouvement_Produit + ", id_produit=" + id_produit + ", id_facture=" + id_facture + ", source=" + source + ", destination=" + destination + ", quantit\u00e9=" + quantité + ", date=" + date + ", nom_produit=" + nom_produit + ", ch_box=" + ch_box + '}';
    }
    
}
