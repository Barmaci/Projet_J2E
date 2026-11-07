<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.exemple.model.Fournisseur" %>
<%
    Fournisseur fournisseur = (Fournisseur) request.getAttribute("fournisseur");
    String erreur = (String) request.getAttribute("erreur");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fournisseur <%= fournisseur.getNom() %></title>
    <link rel="stylesheet" href="<%= ctx %>/css/produit.css">
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="layout">
    <%@ include file="/WEB-INF/nav.jsp" %>
    <main class="content">
        <h1><%= fournisseur.getNom() %></h1>

        <% if (erreur != null) { %>
        <p class="erreur"><%= erreur %></p>
        <% } %>

        <form method="post" action="<%= ctx %>/fournisseur/editer/<%= fournisseur.getId() %>" class="form-produit">
            <input type="hidden" name="id" value="<%= fournisseur.getId() %>">

            <label for="nom">Nom</label>
            <input type="text" id="nom" name="nom" required
                   value="<%= fournisseur.getNom() != null ? fournisseur.getNom() : "" %>">

            <label for="telephone">Téléphone</label>
            <input type="text" id="telephone" name="telephone"
                   value="<%= fournisseur.getTelephone() != null ? fournisseur.getTelephone() : "" %>">

            <label for="adresse">Adresse</label>
            <input type="text" id="adresse" name="adresse"
                   value="<%= fournisseur.getAdresse() != null ? fournisseur.getAdresse() : "" %>">

            <div class="actions">
                <button type="submit">Enregistrer</button>
                <a href="<%= ctx %>/fournisseur">Retour à la liste</a>
            </div>
        </form>
    </main>
</div>
</body>
</html>
