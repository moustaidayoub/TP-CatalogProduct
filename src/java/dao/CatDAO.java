/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import metier.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ayoub MOUSTAID
 */
public class CatDAO implements ICatDAO {

    private Connection cnx = SingletonConnection.getConnection();

    @Override
    public void addProduit(Produit p) {
        try {
            PreparedStatement ps = cnx.prepareStatement("insert into produit values(?,?,?,?)");
            ps.setString(1, p.getReference());
            ps.setString(2, p.getDesignation());
            ps.setDouble(3, p.getPrix());
            ps.setInt(4, p.getQuantite());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CatMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateProduit(Produit p) {
        try {
            PreparedStatement ps = cnx.prepareStatement("update produit set DESIGNATION=?,PRIX=?,QUANTITE=? where REF_PRODUIT=?");
            ps.setString(1, p.getDesignation());
            ps.setDouble(2, p.getPrix());
            ps.setInt(3, p.getQuantite());
            ps.setString(4, p.getReference());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CatMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void deleteProduit(String reference) {
        try {
            PreparedStatement ps = cnx.prepareStatement("delete from produit where REF_PRODUIT=?");
            ps.setString(1, reference);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CatMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Produit> getProduits() {
        List<Produit> list = new ArrayList();
        Produit p;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from produit");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = new Produit();
                p.setReference(rs.getString("REF_PRODUIT"));
                p.setDesignation(rs.getString("DESIGNATION"));
                p.setPrix(rs.getDouble("PRIX"));
                p.setQuantite(rs.getInt("QUANTITE"));
                list.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CatMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public List<Produit> getProduitsMC(String motCle) {
        List<Produit> list = new ArrayList();
        Produit p;
        try {
            PreparedStatement ps = cnx.prepareStatement("select * from produit where DESIGNATION like ?");
            ps.setString(1, "%" + motCle + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p = new Produit();
                p.setReference(rs.getString("REF_PRODUIT"));
                p.setDesignation(rs.getString("DESIGNATION"));
                p.setPrix(rs.getDouble("PRIX"));
                p.setQuantite(rs.getInt("QUANTITE"));
                list.add(p);
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CatMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Produit getProduit(String reference) {
        Produit p = new Produit();

        try {
            PreparedStatement ps = cnx.prepareStatement("select * from produit where REF_PRODUIT=?");
            ps.setString(1, reference);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                p.setReference(rs.getString("REF_PRODUIT"));
                p.setDesignation(rs.getString("DESIGNATION"));
                p.setPrix(rs.getDouble("PRIX"));
                p.setQuantite(rs.getInt("QUANTITE"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CatMetier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
