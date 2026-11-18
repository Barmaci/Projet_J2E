package com.exemple.controller.fournisseur;

import com.exemple.service.FournisseurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/fournisseur/supprimer")
public class SupprimerFournisseurServlet extends HttpServlet {
    private FournisseurService service;

    @Override
    public void init() throws ServletException {
        service = new FournisseurService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            service.supprimer(id);
        } catch (NumberFormatException e) {
            session.setAttribute("flashErreur", "Identifiant invalide");
        } catch (RuntimeException e) {
            session.setAttribute("flashErreur", "Suppression impossible : ce fournisseur est peut-être encore utilisé");
        }

        resp.sendRedirect(req.getContextPath() + "/fournisseur");
    }
}
