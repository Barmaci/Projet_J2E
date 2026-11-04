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
            return session.find(Produit.class, id);
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
    public Produit update(Produit newProduit) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSession()) {
            tx = session.beginTransaction();

            Produit existant = session.find(Produit.class, newProduit.getId());
            if (existant == null) {
                tx.rollback();
                return null;
            }

            existant.setReference(newProduit.getReference());
            existant.setDesignation(newProduit.getDesignation());
            existant.setPrixUnitaire(newProduit.getPrixUnitaire());
            existant.setQuantiteStock(newProduit.getQuantiteStock());
            existant.setSeuilAlerte(newProduit.getSeuilAlerte());

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
            return session.createQuery("SELECT count(p) FROM Produit p", Long.class)
                    .getSingleResult();
        }
    }
}