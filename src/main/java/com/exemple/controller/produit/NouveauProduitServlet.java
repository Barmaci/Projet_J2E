package com.exemple.controller.produit;

import com.exemple.model.Produit;
import com.exemple.service.ProduitService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/produit/nouveau")
public class NouveauProduitServlet extends HttpServlet {
    private ProduitService service;
    @Override
    public void init() throws ServletException {
        service = new ProduitService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/produit/nouveau_produit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Produit p = new Produit();
        String reference   = trim(req.getParameter("reference"));
        String designation = trim(req.getParameter("designation"));

        p.setReference(reference);
        p.setDesignation(designation);
        p.setPrixUnitaire(Double.parseDouble(req.getParameter("prixUnitaire")));
        p.setQuantiteStock(Integer.parseInt( req.getParameter("quantiteStock")));
        p.setSeuilAlerte(Integer.parseInt( req.getParameter("seuilAlerte")));

        service.creer(p);

        resp.sendRedirect(req.getContextPath() + "/produit");
    }

    private String trim(String s) { return s == null ? "" : s.trim(); }

}
