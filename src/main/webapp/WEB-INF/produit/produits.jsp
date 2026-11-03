<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.exemple.util.PageResult" %>
<%@ page import="com.exemple.model.Produit" %>
<%
    PageResult<Produit> result = (PageResult<Produit>) request.getAttribute("result");
    List<Produit> contenu = result.getContenu();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produits</title>
</head>
<body>
<h1>Liste des produits</h1>

<table border="1" cellpadding="6">
    <thead>
    <tr>
        <th>ID</th>
        <th>Référence</th>
        <th>Désignation</th>
        <th>Prix unitaire</th>
        <th>Stock</th>
        <th>Seuil d'alerte</th>
        <th>État</th>
    </tr>
    </thead>
    <tbody>
    <% if (contenu.isEmpty()) { %>
    <tr><td colspan="7">Aucun produit.</td></tr>
    <% } else {
        for (Produit p : contenu) {
            boolean alerte = p.getQuantiteStock() != null
                    && p.getSeuilAlerte() != null
                    && p.getQuantiteStock() <= p.getSeuilAlerte(); %>
    <tr>
        <td><%= p.getId() %></td>
        <td><%= p.getReference() %></td>
        <td><%= p.getDesignation() %></td>
        <td><%= p.getPrixUnitaire() %></td>
        <td><%= p.getQuantiteStock() %></td>
        <td><%= p.getSeuilAlerte() %></td>
        <td><%= alerte ? "Stock faible" : "OK" %></td>
    </tr>
    <%     }
    } %>
    </tbody>
</table>

<p>
    Page <%= result.getPage() %> / <%= result.getTotalPages() %>
    — <%= result.getTotalElements() %> produits
</p>

<% if (result.hasPrevious()) { %>
<a href="?page=<%= result.getPage() - 1 %>&size=<%= result.getSize() %>">« Précédent</a>
<% } %>
<% if (result.hasNext()) { %>
<a href="?page=<%= result.getPage() + 1 %>&size=<%= result.getSize() %>">Suivant »</a>
<% } %>
</body>
</html>