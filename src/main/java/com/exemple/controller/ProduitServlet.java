package com.exemple.controller;

import com.exemple.dao.ProduitDao;
import com.exemple.model.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/produit")
public class ProduitServlet extends HttpServlet {
    private ProduitDao produitDao;

    @Override
    public void init() throws ServletException {
        produitDao = new ProduitDao();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Produit produit = new Produit(
                "P01",
                "Matiere Premeiere",
                120.22,
                14000,
                500
        );

        produitDao.save(produit);
    }
}
