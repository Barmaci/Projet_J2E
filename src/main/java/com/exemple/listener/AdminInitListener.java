package com.exemple.listener;

import com.exemple.dao.UtilisateurDao;
import com.exemple.model.Role;
import com.exemple.model.Utilisateur;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AdminInitListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            UtilisateurDao dao = new UtilisateurDao();
            if (dao.count() == 0) {
                dao.save(new Utilisateur(null, "admin", "admin", Role.ADMIN));
                System.out.println("Compte admin créé (login : admin, mot de passe : admin)");
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
