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

@WebServlet("/utilisateur/editer/*")
public class EditUtilisateurServlet extends HttpServlet {
    private UtilisateurService service;

    @Override
    public void init() throws ServletException {
        service = new UtilisateurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getPathInfo().substring(1)); // enlevé le "/"

        Utilisateur utilisateur = service.listerUtilisateur(id);
        if (utilisateur == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        utilisateur.setMotDePasse(null); // on n'envoie jamais le hash à la page

        req.setAttribute("utilisateur", utilisateur);
        req.setAttribute("roles", Role.values());
        req.getRequestDispatcher("/WEB-INF/utilisateur/utilisateur.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Utilisateur u = new Utilisateur();
        u.setId(Integer.parseInt(req.getParameter("id")));
        u.setLogin(trim(req.getParameter("login")));
        u.setMotDePasse(req.getParameter("motDePasse")); // vide = on garde l'ancien

        try {
            u.setRole(parseRole(req.getParameter("role")));

            if (service.modifier(u) == null) {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
                return;
            }
            resp.sendRedirect(req.getContextPath() + "/utilisateur/editer/" + u.getId());

        } catch (IllegalArgumentException e) {
            u.setMotDePasse(null);
            req.setAttribute("erreur", e.getMessage());
            req.setAttribute("utilisateur", u);
            req.setAttribute("roles", Role.values());
            req.getRequestDispatcher("/WEB-INF/utilisateur/utilisateur.jsp").forward(req, resp);
        }
    }

    private Role parseRole(String s) {
        if (s == null || s.isBlank()) return null;
        return Role.valueOf(s);
    }

    private String trim(String s) { return s == null ? "" : s.trim(); }
}
