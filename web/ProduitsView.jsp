<%-- 
    Document   : ProduitsView
    Created on : Apr 29, 2016, 11:41:57 AM
    Author     : Ayoub MOUSTAID
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Catalogue Produits</title>
        <link rel="StyleSheet" type="text/css" href="css/style.css"/>
        <script type="text/javascript">
            function confirmer(lien)
            {
                var r = confirm('etes-vous sure de vouloir supprimer ce produit ?');
                if (r == true)
                    document.location = lien;
            }

        </script>
    </head>
    <body>
        <form action="controleur" method="post">
            <div>${produitModel.erreur}</div>
            <table>
                <tr>
                    <td>Mot clé : </td>
                    <td><input type="text" name="motcle" value="${produitModel.motCle}"/></td>
                    <td><input type="submit" value="rechercher" name="action"/></td>
                </tr>
            </table>
            <input type="hidden" value="${produitModel.mode}" name="mode"/>
            <table>
                <tr>
                    <td>Réference : </td>
                    <td>
                        <c:if test="${produitModel.mode=='enregistrer'}">
                            <input type="text" name="reference" value="${produitModel.produit.reference}"/>
                        </c:if>
                        <c:if test="${produitModel.mode=='modifier'}">
                            ${produitModel.produit.reference}<input type="hidden" name="reference" value="${produitModel.produit.reference}"/>
                        </c:if>
                    </td>
                </tr>
                <tr>
                    <td>Désignation : </td>
                    <td><input type="text" name="designation" value="${produitModel.produit.designation}" /></td>
                </tr>
                <tr>
                    <td>Prix : </td>
                    <td><input type="text" name="prix" value="${produitModel.produit.prix}"/></td>
                </tr>
                <tr>
                    <td>Quantité : </td>
                    <td><input type="text" name="quantite" value="${produitModel.produit.quantite}"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="enregistrer" name="action" /></td>
                </tr>
            </table>
            <table class="table2">
                <tr>
                    <th>Réference </td>
                    <th>Désignation </td>
                    <th>Prix </td>
                    <th>Quantite</td>
                </tr>
                <c:forEach items="${produitModel.listProduits}" var="i">
                    <tr>
                        <td>${i.reference} </td>
                        <td>${i.designation} </td>
                        <td>${i.prix} </td>
                        <td>${i.quantite} </td>
                        <td><a href="controleur?action=modifier&&reference=${i.reference}">Modifier</a></td>
                        <td><a href="javascript:confirmer('controleur?action=supprimer&&reference=${i.reference}')">Supprimer</a></td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </body>
</html>
