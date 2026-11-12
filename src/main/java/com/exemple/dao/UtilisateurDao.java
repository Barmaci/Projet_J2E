package com.exemple.dao;

import com.exemple.model.Utilisateur;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UtilisateurDao implements UtilisateurDaoInterface {

    @Override
    public List<Utilisateur> findAll(int page, int size) {
        int p = Math.max(page, 1);
        int s = size > 0 ? size : 10;

        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Utilisateur u ORDER BY u.id", Utilisateur.class)
                    .setFirstResult((p - 1) * s)
                    .setMaxResults(s)
                    .getResultList();
        }
    }

    @Override
    public Utilisateur findOne(int id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.find(Utilisateur.class, id);
        }
    }

    @Override
    public Utilisateur findByLogin(String login) {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Utilisateur u WHERE u.login = :login", Utilisateur.class)
                    .setParameter("login", login)
                    .uniqueResult();
        }
    }

    @Override
    public void save(Utilisateur utilisateur) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.persist(utilisateur);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public Utilisateur update(Utilisateur newUtilisateur) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Utilisateur existant = session.find(Utilisateur.class, newUtilisateur.getId());
            if (existant == null) {
                tx.rollback();
                return null;
            }

            existant.setLogin(newUtilisateur.getLogin());
            existant.setMotDePasse(newUtilisateur.getMotDePasse());
            existant.setRole(newUtilisateur.getRole());

            tx.commit();
            return existant;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public long count() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("SELECT count(u) FROM Utilisateur u", Long.class)
                    .getSingleResult();
        }
    }
}
