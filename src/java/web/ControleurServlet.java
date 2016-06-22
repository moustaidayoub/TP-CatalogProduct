/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import metier.CatMetier;
import metier.ICatMetier;
import metier.Produit;

/**
 *
 * @author Ayoub MOUSTAID
 */
public class ControleurServlet extends HttpServlet {

    private ICatMetier metier;

    @Override
    public void init() throws ServletException {
        metier = new CatMetier();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        ProduitModel produitModel = new ProduitModel();
        req.setAttribute("produitModel", produitModel);
        if (action != null) {
            if (action.equals("rechercher")) {
                String motCle = req.getParameter("motcle");
                System.out.println(motCle);
                produitModel.setMotCle(motCle);
                produitModel.setListProduits(metier.getProduitsMC(produitModel.getMotCle()));
                System.out.println(metier.getProduitsMC(produitModel.getMotCle()).size());
            } else if (action.equals("enregistrer")) {
                try {
                    Produit p = produitModel.getProduit();
                    p.setReference(req.getParameter("reference"));
                    p.setDesignation(req.getParameter("designation"));
                    p.setPrix(Double.parseDouble(req.getParameter("prix")));
                    p.setQuantite(Integer.parseInt(req.getParameter("quantite")));
                    produitModel.setMode(req.getParameter("mode"));
                    System.out.println(produitModel.getMode());
                    if (produitModel.getMode().equals("enregistrer")) {
                        metier.addProduit(produitModel.getProduit());
                    } else if (produitModel.getMode().equals("modifier")) {
                        metier.updateProduit(produitModel.getProduit());
                    }

                } catch (Exception ex) {
                    produitModel.setErreur(ex.getMessage());
                } finally {
                    produitModel.setListProduits(metier.getProduits());
                }
            } else if (action.equals("supprimer")) {
                String reference = req.getParameter("reference");
                metier.deleteProduit(reference);
                produitModel.setListProduits(metier.getProduits());
            } else if (action.equals("modifier")) {
                String reference = req.getParameter("reference");
                produitModel.setProduit(metier.getProduit(reference));
                produitModel.setListProduits(metier.getProduits());
                produitModel.setMode("modifier");
            }

        }

        req.getRequestDispatcher("ProduitsView.jsp").forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

}
