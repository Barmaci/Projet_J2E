<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.exemple.model.Role" %>
<%@ page import="com.exemple.model.Utilisateur" %>
<%!
    private static String h(Object o) {
        if (o == null) return "";
        return o.toString().replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    Utilisateur saisi = (Utilisateur) request.getAttribute("utilisateur");   // valeurs déjà saisies (en cas d'erreur)
    Role[] roles = (Role[]) request.getAttribute("roles");
    String erreur = (String) request.getAttribute("erreur");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouvel utilisateur</title>
    <link rel="stylesheet" href="<%= ctx %>/css/produit.css">
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="layout">
    <%@ include file="/WEB-INF/nav.jsp" %>
    <main class="content">
        <h1>Nouvel utilisateur</h1>

        <% if (erreur != null) { %>
        <p class="erreur"><%= h(erreur) %></p>
        <% } %>

        <form method="post" action="<%= ctx %>/utilisateur/nouveau" class="form-produit">

            <label for="login">Login *</label>
            <input type="text" id="login" name="login" required maxlength="255"
                   value="<%= saisi != null ? h(saisi.getLogin()) : "" %>">

            <label for="motDePasse">Mot de passe *</label>
            <input type="password" id="motDePasse" name="motDePasse" required maxlength="255">

            <label for="role">Rôle *</label>
            <select id="role" name="role" required>
                <option value="">-- Choisir --</option>
                <% for (Role r : roles) { %>
                <option value="<%= r.name() %>"
                        <%= (saisi != null && saisi.getRole() == r) ? "selected" : "" %>><%= r.name() %></option>
                <% } %>
            </select>

            <div class="actions">
                <button type="submit">Enregistrer</button>
                <a href="<%= ctx %>/utilisateur">Annuler</a>
            </div>
        </form>
    </main>
</div>
</body>
</html>
