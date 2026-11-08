<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.exemple.model.Produit" %>
<%
    Produit produit = (Produit) request.getAttribute("produit");
    String erreur = (String) request.getAttribute("erreur");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Produit <%= produit.getReference() %>
    </title>
<%--    <link rel="stylesheet" href="<%= ctx %>/css/produit.css">--%>
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="layout">
    <%@ include file="/WEB-INF/nav.jsp" %>
    <main class="content">
        <h1><%= produit.getDesignation() %>
        </h1>

        <% if (erreur != null) { %>
        <p class="erreur"><%= erreur %></p>
        <% } %>

        <form method="post" action="<%= ctx %>/produit/editer/<%=produit.getId()%>" class="form-produit">
            <input type="hidden" name="action" value="modifier">
            <input type="hidden" name="id" value="<%= produit.getId() %>">

            <label for="reference">Référence</label>
            <input type="text" id="reference" name="reference" required
                   value="<%= produit.getReference() %>">

            <label for="designation">Désignation</label>
            <input type="text" id="designation" name="designation" required
                   value="<%= produit.getDesignation() %>">

            <label for="prixUnitaire">Prix unitaire</label>
            <input type="number" id="prixUnitaire" name="prixUnitaire" step="0.01" min="0" required
                   value="<%= produit.getPrixUnitaire() %>">

            <label for="quantiteStock">Quantité en stock</label>
            <input readonly type="number" id="quantiteStock" name="quantiteStock" min="0" required
                   value="<%= produit.getQuantiteStock() %>">

            <label for="seuilAlerte">Seuil d'alerte</label>
            <input type="number" id="seuilAlerte" name="seuilAlerte" min="0" required
                   value="<%= produit.getSeuilAlerte() %>">

            <div class="actions">
                <button type="submit">Enregistrer</button>
                <a href="<%= ctx %>/produit">Retour à la liste</a>
            </div>
        </form>
    </main>
</div>
</body>
</html>