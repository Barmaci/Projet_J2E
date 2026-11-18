package com.exemple.controller.produit;

import com.exemple.model.Fournisseur;
import com.exemple.model.Produit;
import com.exemple.model.Utilisateur;
import com.exemple.service.FournisseurService;
import com.exemple.service.ProduitService;
import com.exemple.util.PageResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/produit/nouveau")
public class NouveauProduitServlet extends HttpServlet {
    private ProduitService service;
    private FournisseurService fournisseurService;

    @Override
    public void init() throws ServletException {
        service = new ProduitService();
        fournisseurService = new FournisseurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PageResult<Fournisseur> fs = fournisseurService.lister(1, 100);
        req.setAttribute("fournisseurs", fs.getContenu());
        req.getRequestDispatcher("/WEB-INF/produit/nouveau_produit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur utilisateur = (Utilisateur) req.getSession().getAttribute("utilisateur");
        if (utilisateur == null) throw  new RuntimeException("Unauthorized");

        Produit p = new Produit();
        String reference   = trim(req.getParameter("reference"));
        String designation = trim(req.getParameter("designation"));
        int fId = Integer.parseInt(req.getParameter("fournisseur"));

        p.setReference(reference);
        p.setDesignation(designation);
        p.setPrixUnitaire(Double.parseDouble(req.getParameter("prixUnitaire")));
        p.setQuantiteStock(Integer.parseInt( req.getParameter("quantiteStock")));
        p.setSeuilAlerte(Integer.parseInt( req.getParameter("seuilAlerte")));
        p.setFournisseur(fournisseurService.listerFournisseur(fId));

        service.creer(p, utilisateur);

        resp.sendRedirect(req.getContextPath() + "/produit");
    }

    private String trim(String s) { return s == null ? "" : s.trim(); }

}
