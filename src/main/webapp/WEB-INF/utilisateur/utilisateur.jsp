<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    Utilisateur utilisateur = (Utilisateur) request.getAttribute("utilisateur");
    Role[] roles = (Role[]) request.getAttribute("roles");
    String erreur = (String) request.getAttribute("erreur");
    String ctx = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Utilisateur <%= h(utilisateur.getLogin()) %></title>
    <link rel="stylesheet" href="<%= ctx %>/css/produit.css">
    <link rel="stylesheet" href="<%= ctx %>/css/style.css">
</head>
<body>
<div class="layout">
    <%@ include file="/WEB-INF/nav.jsp" %>
    <main class="content">
        <h1><%= h(utilisateur.getLogin()) %></h1>

        <% if (erreur != null) { %>
        <p class="erreur"><%= h(erreur) %></p>
        <% } %>

        <form method="post" action="<%= ctx %>/utilisateur/editer/<%= utilisateur.getId() %>" class="form-produit">
            <input type="hidden" name="id" value="<%= utilisateur.getId() %>">

            <label for="login">Login</label>
            <input type="text" id="login" name="login" required maxlength="255"
                   value="<%= h(utilisateur.getLogin()) %>">

            <label for="motDePasse">Mot de passe</label>
            <input type="password" id="motDePasse" name="motDePasse" maxlength="255"
                   placeholder="Laisser vide pour conserver l'ancien">

            <label for="role">Rôle</label>
            <select id="role" name="role" required>
                <% for (Role r : roles) { %>
                <option value="<%= r.name() %>"
                        <%= utilisateur.getRole() == r ? "selected" : "" %>><%= r.name() %></option>
                <% } %>
            </select>

            <div class="actions">
                <button type="submit">Enregistrer</button>
                <a href="<%= ctx %>/utilisateur">Retour à la liste</a>
            </div>
        </form>
    </main>
</div>
</body>
</html>
