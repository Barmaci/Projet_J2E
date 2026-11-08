package com.exemple.controller.mouvement;

import com.exemple.model.TypeMouvementStock;
import com.exemple.service.MouvementStockService;
import com.exemple.service.ProduitService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/mouvements")
public class MouvementServlet extends HttpServlet {
        private final ProduitService produitService = new ProduitService();
        private final MouvementStockService mouvementService = new MouvementStockService();

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            int produitId = Integer.parseInt(req.getParameter("produitId"));

            // On doit aussi récupérer les donnees du produit
            req.setAttribute("produit", produitService.listerProduit(produitId));
            req.setAttribute("mouvements", mouvementService.listerParProduit(produitId));
            req.setAttribute("types", TypeMouvementStock.values());
            req.getRequestDispatcher("/WEB-INF/produit/mouvements.jsp").forward(req, resp);
        }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int produitId = Integer.valueOf(req.getParameter("produitId"));
        String type = req.getParameter("type");
        String commentaire = req.getParameter("commentaire");
        int quantite = Integer.parseInt(req.getParameter("quantite"));

        System.out.println(produitId);
        TypeMouvementStock t = type.equalsIgnoreCase("entree")?
                TypeMouvementStock.ENTREE : TypeMouvementStock.SORTIE;
        mouvementService.enregistrerMouvement(produitId,t , commentaire, quantite );

        resp.sendRedirect(req.getContextPath() + "/mouvements?produitId=" + produitId);

    }
}
