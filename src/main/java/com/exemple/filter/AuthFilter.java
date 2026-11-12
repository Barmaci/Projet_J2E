package com.exemple.filter;

import com.exemple.model.Role;
import com.exemple.model.Utilisateur;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getRequestURI().substring(req.getContextPath().length());

        // 1. Pages accessibles sans connexion
        if (path.equals("/login") || path.startsWith("/css/")) {
            chain.doFilter(request, response);
            return;
        }

        // 2. Il faut être connecté
        HttpSession session = req.getSession(false);
        Utilisateur utilisateur = (session == null) ? null : (Utilisateur) session.getAttribute("utilisateur");

        if (utilisateur == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // 3. Fournisseurs et utilisateurs : réservés à l'admin
        boolean zoneAdmin = commence(path, "/fournisseur") || commence(path, "/utilisateur");
        if (zoneAdmin && utilisateur.getRole() != Role.ADMIN) {
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, "Accès réservé à l'administrateur");
            return;
        }

        chain.doFilter(request, response);
    }

    // vrai pour "/fournisseur", "/fournisseur/nouveau"... mais pas pour "/fournisseurXYZ"
    private boolean commence(String path, String base) {
        return path.equals(base) || path.startsWith(base + "/");
    }
}
