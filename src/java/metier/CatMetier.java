/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import dao.CatDAO;
import dao.ICatDAO;
import dao.SingletonConnection;
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
public class CatMetier implements ICatMetier{
    private ICatDAO dao;
    
    
public CatMetier(){
    dao=new CatDAO();
}

    @Override
    public void addProduit(Produit p) {
        dao.addProduit(p);
 }

    @Override
    public void updateProduit(Produit p) {
      dao.updateProduit(p);
    }

    @Override
    public void deleteProduit(String reference) {
dao.deleteProduit(reference);
    }

    @Override
    public List<Produit> getProduits() {
return dao.getProduits();
    }

    @Override
    public List<Produit> getProduitsMC(String motCle) { 
return dao.getProduitsMC(motCle);
    }
    
    @Override
    public Produit getProduit(String reference) {
return dao.getProduit(reference);
    }
    
}
