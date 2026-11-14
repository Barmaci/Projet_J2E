package com.exemple.dao;

import com.exemple.model.Produit;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class ProduitDao implements ProduitDaoInterface {

    @Override
    public List<Produit> findAll(int page, int size) {
        int p = Math.max(page, 1);
        int s = size > 0 ? size : 10;

        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("FROM Produit p ORDER BY p.id", Produit.class)
                    .setFirstResult((p - 1) * s)
                    .setMaxResults(s)
                    .getResultList();
        }
    }

    @Override
    public Produit findOne(int id) {
        try (Session session = HibernateUtil.getSession()) {
            Produit p = session.find(Produit.class, id);
            if (p != null && p.getFournisseur() != null) {
                HibernateUtil.initialize(p.getFournisseur());
            }
            return p;
        }
    }

    @Override
    public void save(Produit produit) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.persist(produit);
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public Produit update(Produit produit) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            Produit merged = session.merge(produit);
            tx.commit();
            return merged;

        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public void delete(int id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();
            session.createQuery("DELETE  FROM Produit p where p.id = :pid")
                    .setParameter("pid", id)
                    .executeUpdate();
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null && tx.isActive()) tx.rollback();
            throw e;
        }
    }

    @Override
    public long count() {
        try (Session session = HibernateUtil.getSession()) {
            return session.createQuery("SELECT count(p) FROM Produit p", Long.class)
                    .getSingleResult();
        }
    }
}