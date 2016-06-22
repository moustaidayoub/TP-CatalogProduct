/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

import java.util.List;

/**
 *
 * @author Ayoub MOUSTAID
 */
public interface ICatMetier {
    void addProduit(Produit p);
    void updateProduit(Produit p);
    void deleteProduit(String reference);
    List<Produit> getProduits();
    List<Produit> getProduitsMC(String motCle);  
    Produit getProduit(String reference);
}
