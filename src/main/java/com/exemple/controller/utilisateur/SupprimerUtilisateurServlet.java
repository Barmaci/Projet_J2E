package com.exemple.controller.utilisateur;

import com.exemple.model.Utilisateur;
import com.exemple.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/utilisateur/supprimer")
public class SupprimerUtilisateurServlet extends HttpServlet {
    private UtilisateurService service;

    @Override
    public void init() throws ServletException {
        service = new UtilisateurService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utilisateur connecte = (Utilisateur) session.getAttribute("utilisateur");

        try {
            int id = Integer.parseInt(req.getParameter("id"));
            service.supprimer(id, connecte != null ? connecte.getId() : null);
        } catch (NumberFormatException e) {
            session.setAttribute("flashErreur", "Identifiant invalide");
        } catch (IllegalArgumentException e) {
            session.setAttribute("flashErreur", e.getMessage());   // règle de gestion refusée
        } catch (RuntimeException e) {
            session.setAttribute("flashErreur", "Suppression impossible");
        }

        resp.sendRedirect(req.getContextPath() + "/utilisateur");
    }
}
