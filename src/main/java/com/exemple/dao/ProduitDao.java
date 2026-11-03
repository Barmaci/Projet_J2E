package com.exemple.dao;

import com.exemple.model.Produit;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

// La classe tenant compte de gérer les produits
public class ProduitDao implements ProduitDaoInterface{
    public ProduitDao() {
    }

    // La methode responsable d'extraire tous les produits de la BD avec pagination
    @Override
    public List<Produit> findAll(int page, int size) {
        if (page < 1) page = 1;
        if (size <= 0) size = 10;

        int offset = ( page - 1) * size;
        List<Produit> produits = null;

        try ( Session session = HibernateUtil.getSession()){
            produits = session
                    .createQuery("FROM Produit p order by p.id", Produit.class)
                    .setFirstResult(offset)
                    .setMaxResults(page)
                    .list();
        } catch ( RuntimeException e){
            e.printStackTrace();
        }

        return produits;
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

    // Compter le nombre de produits dans la BD
    @Override
    public long count() {
        try (Session session = HibernateUtil.getSession()){
            return  session
                    .createQuery("SELECT count(p) from Produit p", Long.class)
                    .uniqueResult();
        }
    }
}
