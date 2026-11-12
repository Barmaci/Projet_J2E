<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.exemple.util.PageResult" %>
<%@ page import="com.exemple.model.Utilisateur" %>
<%!
    private static String h(Object o) {
        if (o == null) return "";
        return o.toString().replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;");
    }
%>
<%
    PageResult<Utilisateur> result = (PageResult<Utilisateur>) request.getAttribute("result");
    List<Utilisateur> contenu = result.getContenu();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Utilisateurs</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/produit.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="layout">

    <%@ include file="/WEB-INF/nav.jsp" %>
    <main class="content">
        <div class="produits-top-div">
            <h1>Liste des utilisateurs</h1>
            <div>
                <a href="<%=request.getContextPath()%>/utilisateur/nouveau" class="aButton">Nouvel utilisateur</a>
            </div>
        </div>
        <div>

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Login</th>
                    <th>Rôle</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <% if (contenu.isEmpty()) { %>
                <tr>
                    <td colspan="4">Aucun utilisateur.</td>
                </tr>
                <% } else {
                    for (Utilisateur u : contenu) { %>
                <tr>
                    <td><%= u.getId() %></td>
                    <td><%= h(u.getLogin()) %></td>
                    <td><%= u.getRole() %></td>
                    <td>
                        <a href="<%= request.getContextPath()%>/utilisateur/editer/<%=u.getId()%>">Editer</a>
                    </td>
                </tr>
                <% }
                } %>
                </tbody>
            </table>

            <p class="info-page">
                Page <%= result.getPage() %> / <%= result.getTotalPages() %>
                — <%= result.getTotalElements() %> utilisateurs
            </p>

            <div class="pagination">
                <% if (result.hasPrevious()) { %>
                <a href="?page=<%= result.getPage() - 1 %>&size=<%= result.getSize() %>">« Précédent</a>
                <% } %>
                <% if (result.hasNext()) { %>
                <a href="?page=<%= result.getPage() + 1 %>&size=<%= result.getSize() %>">Suivant »</a>
                <% } %>
            </div>
        </div>
    </main>

</div>

</body>
</html>
