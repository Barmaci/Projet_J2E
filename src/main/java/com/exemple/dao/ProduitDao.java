package com.exemple.dao;

import com.exemple.model.Produit;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

// La classe tenant compte de gérer les produits

public class ProduitDao implements ProduitDaoInterface{
    public ProduitDao() {
    }

    // La methode responsable de créer un produit
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

}
