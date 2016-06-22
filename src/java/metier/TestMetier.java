/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metier;

/**
 *
 * @author Ayoub MOUSTAID
 */
public class TestMetier {
    public static void main(String[] args) {
        ICatMetier metier=new CatMetier();
        /*metier.addProduit(new Produit("P1","DES1",100,10));
        tier.addProduit(new Produit("P2","DES2",200,20));
        metier.addProduit(new Produit("P3","DES3",300,30));
        metier.updateProduit(new Produit("P3","DES33",3000,30));*/
        System.out.println(metier.getProduits().size());
        //metier.deleteProduit("P1");
        System.out.println(metier.getProduit("P").getDesignation());
         //System.out.println(metier.getProduits().get(1).getDesignation());
    }
}
