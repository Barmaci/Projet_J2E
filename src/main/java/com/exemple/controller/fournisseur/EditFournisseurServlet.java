package com.exemple.controller.fournisseur;

import com.exemple.model.Fournisseur;
import com.exemple.service.FournisseurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/fournisseur/editer/*")
public class EditFournisseurServlet extends HttpServlet {
    private FournisseurService service;

    @Override
    public void init() throws ServletException {
        service = new FournisseurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1)); // enlevé le "/"

        Fournisseur fournisseur = service.listerFournisseur(id);

        req.setAttribute("fournisseur", fournisseur);
        req.getRequestDispatcher("/WEB-INF/fournisseur/fournisseur.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom       = req.getParameter("nom");
        String telephone = req.getParameter("telephone");
        String adresse   = req.getParameter("adresse");

        Fournisseur f = new Fournisseur();
        f.setId(Integer.parseInt(req.getParameter("id")));
        f.setNom(nom);
        f.setTelephone(telephone);
        f.setAdresse(adresse);

        service.modifier(f);
        resp.sendRedirect(req.getContextPath() + "/fournisseur/editer/" + f.getId());
    }
}
