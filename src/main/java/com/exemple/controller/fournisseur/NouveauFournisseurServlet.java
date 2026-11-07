package com.exemple.controller.fournisseur;

import com.exemple.model.Fournisseur;
import com.exemple.service.FournisseurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/fournisseur/nouveau")
public class NouveauFournisseurServlet extends HttpServlet {
    private FournisseurService service;

    @Override
    public void init() throws ServletException {
        service = new FournisseurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/fournisseur/nouveau_fournisseur.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Fournisseur f = new Fournisseur();

        f.setNom(trim(req.getParameter("nom")));
        f.setTelephone(trim(req.getParameter("telephone")));
        f.setAdresse(trim(req.getParameter("adresse")));

        service.creer(f);

        resp.sendRedirect(req.getContextPath() + "/fournisseur");
    }

    private String trim(String s) { return s == null ? "" : s.trim(); }
}
