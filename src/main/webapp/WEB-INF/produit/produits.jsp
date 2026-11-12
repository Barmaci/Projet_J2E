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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="layout">

    <%@ include file="/WEB-INF/nav.jsp" %>

    <main class="content">
        <div class="card">

            <div class="produits-top-div">
                <h1>Liste des produits</h1>
                <div>
                    <a href="<%=request.getContextPath()%>/produit/nouveau" class="aButton">Nouveau produit</a>
                </div>
            </div>
        </div>

        <div>
            <table class="table-cards">
                <thead>
                <tr>
                    <th>ID</th>
                    <th>Référence</th>
                    <th>Désignation</th>
                    <th>Prix unitaire</th>
                    <th>Stock</th>
                    <th>Seuil d'alerte</th>
                    <th>État</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <% if (contenu.isEmpty()) { %>
                <tr class="ligne-vide">
                    <td colspan="8">Aucun produit.</td>
                </tr>
                <%
                } else {
                    for (Produit p : contenu) {
                        boolean alerte =
                                p.getQuantiteStock() != null
                                        && p.getSeuilAlerte() != null
                                        && p.getQuantiteStock() <= p.getSeuilAlerte();
                %>
                <tr class="<%= alerte ? "alerte" : "" %>">
                    <td><%= p.getId() %>
                    </td>
                    <td><%= p.getReference() %>
                    </td>
                    <td><%= p.getDesignation() %>
                    </td>
                    <td><%= p.getPrixUnitaire() %>
                    </td>
                    <td><%= p.getQuantiteStock() %>
                    </td>
                    <td><%= p.getSeuilAlerte() %>
                    </td>
                    <td class="<%= alerte ? "etat-faible" : "etat-ok" %>">
                        <%= alerte ? "Stock faible" : "Stock ok" %>
                    </td>
                    <td class="actions-cell">
                            <div class="actions-wrapper">
                                <a class="aButton " href="<%= request.getContextPath() %>/produit/editer/<%= p.getId() %>">Editer</a>
                                <a class="aButton btn-mv-stock" href="<%= request.getContextPath() %>/mouvements?produitId=<%= p.getId() %>">Mv stock</a>

                                <form action="<%= request.getContextPath() %>/produit" method="POST">
                                    <input name="method" value="delete" hidden/>
                                    <input name="produitId" value="<%=p.getId()%>" hidden/>
                                    <button class="aButton btn-delete" type="submit">Supprimer</button>
                                </form>
                            </div>
                    </td>
                </tr>
                <% }
                } %>
                </tbody>
            </table>

            <p class="info-page">
                Page <%= result.getPage() %> / <%= result.getTotalPages() %>
                — <%= result.getTotalElements() %> produits
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