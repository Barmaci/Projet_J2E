<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    String erreur = (String) request.getAttribute("erreur");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Connexion</title>
    <link rel="stylesheet" href="<%= ctx %>/css/produit.css">
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<main style="padding: 60px 20px;">
    <h1>📦 Gestion de stock</h1>

    <% if (erreur != null) { %>
    <p class="erreur"><%= erreur %></p>
    <% } %>

    <form method="post" action="<%= ctx %>/login" class="form-produit">
        <label for="login">Login</label>
        <input type="text" id="login" name="login" required autofocus>

        <label for="motDePasse">Mot de passe</label>
        <input type="password" id="motDePasse" name="motDePasse" required>

        <div class="actions">
            <button type="submit">Se connecter</button>
        </div>
    </form>
</main>
</body>
</html>
