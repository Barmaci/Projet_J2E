<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.exemple.util.PageResult" %>
<%@ page import="com.exemple.model.Fournisseur" %>
<%
    PageResult<Fournisseur> result = (PageResult<Fournisseur>) request.getAttribute("result");
    List<Fournisseur> contenu = result.getContenu();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Fournisseurs</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/produit.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="layout">

    <%@ include file="/WEB-INF/nav.jsp" %>
    <main class="content">
        <div class="produits-top-div card">
            <h1>Liste des fournisseurs</h1>
            <div>
                <a href="<%=request.getContextPath()%>/fournisseur/nouveau" class="aButton">Nouveau fournisseur</a>
            </div>
        </div>
        <div >

            <table>
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Téléphone</th>
                    <th>Adresse</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <% if (contenu.isEmpty()) { %>
                <tr>
                    <td colspan="5">Aucun fournisseur.</td>
                </tr>
                <% } else {
                    for (Fournisseur f : contenu) { %>
                <tr>
                    <td><%= f.getId() %></td>
                    <td><%= f.getNom() %></td>
                    <td><%= f.getTelephone() %></td>
                    <td><%= f.getAdresse() %></td>
                    <td>
                        <a class="aButton" href="<%= request.getContextPath()%>/fournisseur/editer/<%=f.getId()%>">Editer</a>
                    </td>
                </tr>
                <% }
                } %>
                </tbody>
            </table>

            <p class="info-page">
                Page <%= result.getPage() %> / <%= result.getTotalPages() %>
                — <%= result.getTotalElements() %> fournisseurs
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
