package com.exemple.dao;

import com.exemple.model.Fournisseur;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class FournisseurDao implements FournisseurDaoInterface {

    @Override
    public List<Fournisseur> findAll(int page, int size) {
        int p = Math.max(page, 1);
        int s = size > 0 ? size : 10;

        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Fournisseur f ORDER BY f.id", Fournisseur.class)
                    .setFirstResult((p - 1) * s)
                    .setMaxResults(s)
                    .getResultList();
        }
    }

    @Override
    public Fournisseur findOne(int id) {
        try (Session session = HibernateUtil.getSession()) {
            return session.find(Fournisseur.class, id);
        }
    }

    @Override
    public void save(Fournisseur fournisseur) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.persist(fournisseur);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public Fournisseur update(Fournisseur newFournisseur) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Fournisseur existant = session.find(Fournisseur.class, newFournisseur.getId());
            if (existant == null) {
                tx.rollback();
                return null;
            }

            existant.setNom(newFournisseur.getNom());
            existant.setTelephone(newFournisseur.getTelephone());
            existant.setAdresse(newFournisseur.getAdresse());

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
            return session.createQuery("SELECT count(f) FROM Fournisseur f", Long.class)
                    .getSingleResult();
        }
    }
}
