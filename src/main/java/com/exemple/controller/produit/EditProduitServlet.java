package com.exemple.controller.produit;

import com.exemple.model.Produit;
import com.exemple.service.ProduitService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/produit/editer/*")
public class EditProduitServlet extends HttpServlet {
    private ProduitService service;
    @Override
    public void init() throws ServletException {
        service = new ProduitService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1)); // enlevé le "/

        Produit produit = service.listerProduit( id);

        req.setAttribute("produit", produit);
        req.getRequestDispatcher("/WEB-INF/produit/produit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String reference   = req.getParameter("reference");
        String designation = req.getParameter("designation");
        String prixStr     = req.getParameter("prixUnitaire");
        String seuilStr    = req.getParameter("seuilAlerte");

        Produit p = service.listerProduit(Integer.parseInt(req.getParameter("id")));

        p.setReference(reference);
        p.setDesignation(designation);
        p.setPrixUnitaire(Double.parseDouble(prixStr));
        p.setSeuilAlerte(Integer.parseInt(seuilStr));

        service.modifier(p);
        resp.sendRedirect(req.getContextPath() + "/produit/editer/" + p.getId());
    }
}
