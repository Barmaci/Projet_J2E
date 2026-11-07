<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Nouveau fournisseur</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/produit.css">
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>
<div class="layout">
    <%@ include file="/WEB-INF/nav.jsp" %>
    <main class="content">
        <h1>Nouveau fournisseur</h1>
        <form method="post"
              action="<%= request.getContextPath()%>/fournisseur/nouveau"
              class="form-produit"
              novalidate>

            <div class="champ">
                <label for="nom">Nom *</label>
                <input type="text" id="nom" name="nom"
                       required maxlength="255"
                       placeholder="ex. Société Atlas">
            </div>

            <div class="champ">
                <label for="telephone">Téléphone</label>
                <input type="text" id="telephone" name="telephone"
                       maxlength="30"
                       placeholder="ex. 0600000000">
            </div>

            <div class="champ">
                <label for="adresse">Adresse</label>
                <input type="text" id="adresse" name="adresse"
                       maxlength="255"
                       placeholder="ex. 12 rue Mohammed V, Marrakech">
            </div>

            <div class="actions">
                <button type="submit">Enregistrer</button>
                <a href="<%=request.getContextPath()%>/fournisseur">Annuler</a>
            </div>
        </form>
    </main>
</div>

</body>
</html>
