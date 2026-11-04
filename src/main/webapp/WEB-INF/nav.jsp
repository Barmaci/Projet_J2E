<%@ page pageEncoding="UTF-8" %>
<%
    String sbCtx = request.getContextPath();
    String sbUri = (String) request.getAttribute("jakarta.servlet.forward.request_uri");
    if (sbUri == null) sbUri = request.getRequestURI();
    String sbPath = sbUri.substring(sbCtx.length());

    boolean sbHome        = sbPath.isEmpty() || sbPath.equals("/") || sbPath.startsWith("/dashboard");
    boolean sbProduit     = sbPath.startsWith("/produit");
    boolean sbFournisseur = sbPath.startsWith("/fournisseur");
    boolean sbMouvement   = sbPath.startsWith("/mouvement");
%>
<aside class="sidebar">
    <div class="sidebar-brand">📦 Gestion de stock</div>

    <nav class="sidebar-nav">
        <ul>
            <li><a href="<%= sbCtx %>/dashboard" class="<%= sbHome ? "active" : "" %>">🏠 Tableau de bord</a></li>
            <li><a href="<%= sbCtx %>/produit" class="<%= sbProduit ? "active" : "" %>">🛒 Produits</a></li>
            <li><a href="<%= sbCtx %>/fournisseur" class="<%= sbFournisseur ? "active" : "" %>">🚚 Fournisseurs</a></li>
            <li><a href="<%= sbCtx %>/mouvement" class="<%= sbMouvement ? "active" : "" %>">🔄 Mouvements de stock</a></li>
        </ul>
    </nav>

    <div class="sidebar-footer">© 2026 Projet J2E</div>
</aside>