package com.exemple.controller.utilisateur;

import com.exemple.model.Role;
import com.exemple.model.Utilisateur;
import com.exemple.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/utilisateur/nouveau")
public class NouveauUtilisateurServlet extends HttpServlet {
    private UtilisateurService service;

    @Override
    public void init() throws ServletException {
        service = new UtilisateurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/utilisateur/nouveau_utilisateur.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur u = new Utilisateur();
        u.setLogin(trim(req.getParameter("login")));
        u.setMotDePasse(req.getParameter("motDePasse"));

        try {
            u.setRole(parseRole(req.getParameter("role")));
            service.creer(u);
            resp.sendRedirect(req.getContextPath() + "/utilisateur");
        } catch (IllegalArgumentException e) {
            u.setMotDePasse(null);
            req.setAttribute("erreur", e.getMessage());
            req.setAttribute("utilisateur", u);
            req.setAttribute("roles", Role.values());
            req.getRequestDispatcher("/WEB-INF/utilisateur/nouveau_utilisateur.jsp").forward(req, resp);
        }
    }

    private Role parseRole(String s) {
        if (s == null || s.isBlank()) return null;
        return Role.valueOf(s);
    }

    private String trim(String s) { return s == null ? "" : s.trim(); }
}
