<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.exemple.model.Produit" %>
<%@ page import="com.exemple.model.MouvementStock" %>
<%@ page import="com.exemple.model.TypeMouvementStock" %>
<%!
    // échappe le HTML (remplace c:out, protège contre le XSS)
    private static String h(Object o) {
        if (o == null) return "";
        return o.toString()
                .replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
%>
<%
    Produit produit = (Produit) request.getAttribute("produit");
    @SuppressWarnings("unchecked")
    List<MouvementStock> mouvements = (List<MouvementStock>) request.getAttribute("mouvements");
    TypeMouvementStock[] types = (TypeMouvementStock[]) request.getAttribute("types");
    String erreur = (String) request.getAttribute("erreur");
    boolean stockFaible = produit.getQuantiteStock() <= produit.getSeuilAlerte();
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <title>Mouvements de stock - <%= h(produit.getDesignation()) %>
    </title>
    <%--    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/mouvement.css"/>--%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css"/>
</head>
<body>
<%@ include file="/WEB-INF/nav.jsp" %>
<main class="content">
    <div class="container">
        <a class="retour" href="<%= request.getContextPath() %>/produit">&larr; Retour aux produits</a>

        <!-- Infos du produit -->
        <div class="card">
            <h2><%= h(produit.getDesignation()) %>
            </h2>
            <div class="infos">
                <div class="info"><span>Référence</span><strong><%= h(produit.getReference()) %>
                </strong></div>
                <div class="info"><span>Prix unitaire</span><strong><%= produit.getPrixUnitaire() %>
                </strong></div>
                <div class="info">
                    <span>Stock actuel</span>
                    <strong class="<%= stockFaible ? "stock-faible" : "" %>"><%= produit.getQuantiteStock() %>
                    </strong>
                </div>
                <div class="info"><span>Seuil d'alerte</span><strong><%= produit.getSeuilAlerte() %>
                </strong></div>
            </div>
        </div>

        <!-- Erreur -->
        <% if (erreur != null && !erreur.isEmpty()) { %>
        <div class="erreur"><%= h(erreur) %>
        </div>
        <% } %>

        <!-- Formulaire -->
        <div class="card">
            <h3>Nouveau mouvement</h3>
            <form class="form-mvt" action="<%= request.getContextPath() %>/mouvements" method="post">
                <input type="hidden" name="produitId" value="<%= produit.getId() %>">

                <div>
                    <label for="type">Type</label>
                    <select id="type" name="type" required>
                        <% for (TypeMouvementStock t : types) { %>
                        <option value="<%= t %>"><%= t %>
                        </option>
                        <% } %>
                    </select>
                </div>
                <div>
                    <label for="quantite">Quantité</label>
                    <input id="quantite" type="number" name="quantite" min="1" required>
                </div>
                <div>
                    <label for="commentaire">Commentaire</label>
                    <input id="commentaire" type="text" name="commentaire" maxlength="255">
                </div>
                <button class="btn" type="submit">Enregistrer</button>
            </form>
        </div>

        <!-- Historique -->
        <div class="card">
            <h3>Historique des mouvements</h3>
            <table>
                <thead>
                <tr>
                    <th>Date</th>
                    <th>Type</th>
                    <th>Quantité</th>
                    <th>Commentaire</th>
                    <th>Effectuer par</th>
                </tr>
                </thead>
                <tbody>
                <% if (mouvements == null || mouvements.isEmpty()) { %>
                <tr>
                    <td colspan="4" class="vide">Aucun mouvement enregistré.</td>
                </tr>
                <% } else {
                    for (MouvementStock m : mouvements) { %>
                <tr>
                    <td><%= m.getDate() %>
                    </td>
                    <td><span class="badge <%= m.getType() %>"><%= m.getType() %></span></td>
                    <td><%= m.getQuantite() %>
                    </td>
                    <td><%= h(m.getCommentaire()) %>
                    </td>
                    <td>
                        <%= m.getUtilisateur().getLogin()%>
                    </td>
                </tr>
                <% }
                } %>
                </tbody>
            </table>
        </div>
    </div>
</main>
</body>
</html>