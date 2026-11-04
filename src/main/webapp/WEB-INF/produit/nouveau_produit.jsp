<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouveau produit</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/produit.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<h1>Nouveau produit</h1>
<div class="layout">
    <%@ include file="/WEB-INF/nav.jsp" %>
    <main class="content">
        <form method="post"
              action="<%= request.getContextPath()%>/produit/nouveau"
              class="form-produit"
              novalidate>

            <div class="champ">
                <label for="reference">Référence *</label>
                <input type="text" id="reference" name="reference"
                       required maxlength="50"
                       placeholder="ex. REF-001">
            </div>

            <div class="champ">
                <label for="designation">Désignation *</label>
                <input type="text" id="designation" name="designation"
                       required maxlength="255"
                       placeholder="ex. Clavier mécanique">
            </div>

            <div class="champ">
                <label for="prixUnitaire">Prix unitaire (€) *</label>
                <input type="number" id="prixUnitaire" name="prixUnitaire"
                       required
                       min="0" step="0.01" placeholder="0.00">
            </div>

            <div class="champ">
                <label for="quantiteStock">Quantité en stock</label>
                <input type="number" id="quantiteStock" name="quantiteStock"
                       min="0" step="1"
                       placeholder="0">
            </div>

            <div class="champ">
                <label for="seuilAlerte">Seuil d'alerte</label>
                <input type="number" id="seuilAlerte" name="seuilAlerte"
                       min="0" step="1"
                       placeholder="0">
            </div>

            <div class="actions">
                <button type="submit">Enregistrer</button>
                <a href="<%=request.getContextPath()%>/produit">Annuler</a>
            </div>
        </form>
    </main>
</div>

</body>
</html>