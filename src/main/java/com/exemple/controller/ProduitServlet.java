package com.exemple.controller;

import com.exemple.dao.ProduitDao;
import com.exemple.model.Produit;
import com.exemple.service.ProduitService;
import com.exemple.util.PageResult;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
    private ProduitService service;

    @Override
    public void init() throws ServletException {
        service = new ProduitService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = parseIntOrDefault(req.getParameter("page"), 1);
        int size = parseIntOrDefault(req.getParameter("size"), 10);

        PageResult<Produit> result = service.lister(page, size);

        req.setAttribute("result", result);
        req.getRequestDispatcher("/WEB-INF/produit/produits.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    // Methode qui retourne la valeur int du string s si c'est un nbr ou la valeur def
    private int parseIntOrDefault(String s, int def) {
        try { return Integer.parseInt(s); } catch (Exception e) { return def; }
    }
}
