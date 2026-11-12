<%@ page pageEncoding="UTF-8" %>
<%@ page import="com.exemple.model.Role" %>
<%@ page import="com.exemple.model.Utilisateur" %>
<%
    String sbCtx = request.getContextPath();
    String sbUri = (String) request.getAttribute("jakarta.servlet.forward.request_uri");
    if (sbUri == null) sbUri = request.getRequestURI();
    String sbPath = sbUri.substring(sbCtx.length());

    boolean sbHome        = sbPath.isEmpty() || sbPath.equals("/") || sbPath.startsWith("/dashboard");
    boolean sbProduit     = sbPath.startsWith("/produit");
    boolean sbFournisseur = sbPath.startsWith("/fournisseur");
    boolean sbMouvement   = sbPath.startsWith("/mouvement");
    boolean sbUtilisateur = sbPath.startsWith("/utilisateur");

    Utilisateur sbUser = (Utilisateur) request.getSession().getAttribute("utilisateur");
    boolean sbAdmin = sbUser != null && sbUser.getRole() == Role.ADMIN;
%>
<aside class="sidebar">
    <div class="sidebar-brand">📦 Gestion de stock</div>

    <nav class="sidebar-nav">
        <ul>
            <li><a href="<%= sbCtx %>/produit" class="<%= sbProduit ? "active" : "" %>">🛒 Produits</a></li>
            <% if (sbAdmin) { %>
            <li><a href="<%= sbCtx %>/fournisseur" class="<%= sbFournisseur ? "active" : "" %>">🚚 Fournisseurs</a></li>
            <li><a href="<%= sbCtx %>/utilisateur" class="<%= sbUtilisateur ? "active" : "" %>">👤 Utilisateurs</a></li>
            <% } %>
            <li><a href="<%= sbCtx %>/logout">🚪 Déconnexion</a></li>
        </ul>
    </nav>

    <div class="sidebar-footer">
        <% if (sbUser != null) { %>Connecté : <%= sbUser.getLogin() %> (<%= sbUser.getRole() %>)<br><% } %>
        © 2026 Projet J2E
    </div>
</aside>
