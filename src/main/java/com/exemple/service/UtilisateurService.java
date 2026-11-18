package com.exemple.service;

import com.exemple.dao.UtilisateurDao;
import com.exemple.model.Role;
import com.exemple.model.Utilisateur;
import com.exemple.util.PageResult;

import java.util.List;

public class UtilisateurService {
    private final UtilisateurDao utilisateurDao = new UtilisateurDao();

    public PageResult<Utilisateur> lister(int page, int size) {
        List<Utilisateur> contenu = utilisateurDao.findAll(page, size);
        long total = utilisateurDao.count();

        return new PageResult<>(contenu, page, size, total);
    }

    public Utilisateur listerUtilisateur(int id) {
        return utilisateurDao.findOne(id);
    }

    public void creer(Utilisateur u) {
        verifier(u);

        if (u.getMotDePasse() == null || u.getMotDePasse().isBlank()) {
            throw new IllegalArgumentException("Le mot de passe est obligatoire");
        }
        if (utilisateurDao.findByLogin(u.getLogin()) != null) {
            throw new IllegalArgumentException("Ce login existe déjà");
        }

        utilisateurDao.save(u);
    }

    public Utilisateur modifier(Utilisateur u) {
        verifier(u);

        Utilisateur existant = utilisateurDao.findOne(u.getId());
        if (existant == null) {
            return null;
        }

        Utilisateur autre = utilisateurDao.findByLogin(u.getLogin());
        if (autre != null && !autre.getId().equals(u.getId())) {
            throw new IllegalArgumentException("Ce login existe déjà");
        }

        // Mot de passe laissé vide = on garde l'ancien
        if (u.getMotDePasse() == null || u.getMotDePasse().isBlank()) {
            u.setMotDePasse(existant.getMotDePasse());
        }

        return utilisateurDao.update(u);
    }

    // Supprime un utilisateur. idConnecte = id de la personne qui fait la demande.
    // Renvoie true si supprimé, false si l'utilisateur n'existait pas.
    public boolean supprimer(int id, Integer idConnecte) {
        if (idConnecte != null && idConnecte == id) {
            throw new IllegalArgumentException("Vous ne pouvez pas supprimer votre propre compte");
        }

        Utilisateur cible = utilisateurDao.findOne(id);
        if (cible == null) {
            return false;
        }

        if (cible.getRole() == Role.ADMIN && utilisateurDao.countByRole(Role.ADMIN) <= 1) {
            throw new IllegalArgumentException("Impossible de supprimer le dernier administrateur");
        }

        return utilisateurDao.delete(id);
    }

    // Connexion : renvoie l'utilisateur si le login et le mot de passe sont bons, sinon null
    public Utilisateur authentifier(String login, String motDePasse) {
        if (login == null || login.isBlank() || motDePasse == null) {
            return null;
        }

        Utilisateur u = utilisateurDao.findByLogin(login.trim());
        if (u == null || !motDePasse.equals(u.getMotDePasse())) {
            return null;
        }

        u.setMotDePasse(null); // on ne garde pas le mot de passe en session
        return u;
    }

    private void verifier(Utilisateur u) {
        if (u.getLogin() == null || u.getLogin().isBlank()) {
            throw new IllegalArgumentException("Le login est obligatoire");
        }
        if (u.getRole() == null) {
            throw new IllegalArgumentException("Le rôle est obligatoire");
        }
    }
}
