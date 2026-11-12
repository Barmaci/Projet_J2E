package com.exemple.controller.auth;

import com.exemple.model.Utilisateur;
import com.exemple.service.UtilisateurService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private UtilisateurService service;

    @Override
    public void init() throws ServletException {
        service = new UtilisateurService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String motDePasse = req.getParameter("motDePasse");

        Utilisateur u = service.authentifier(login, motDePasse);

        if (u == null) {
            req.setAttribute("erreur", "Login ou mot de passe incorrect");
            req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            return;
        }

        // On repart d'une session neuve (évite la fixation de session)
        HttpSession ancienne = req.getSession(false);
        if (ancienne != null) ancienne.invalidate();

        HttpSession session = req.getSession(true);
        session.setAttribute("utilisateur", u);

        resp.sendRedirect(req.getContextPath() + "/produit");
    }
}
