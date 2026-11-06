package com.exemple.dao;

import com.exemple.model.Fournisseur;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

// La classe tenant compte de gérer les fournisseurs
public class FournisseurDao implements FournisseurDaoInterface {
    public FournisseurDao() {
    }

    // La methode responsable d'extraire tous les fournisseurs de la BD avec pagination
    @Override
    public List<Fournisseur> findAll(int page, int size) {
        if (page < 1) page = 1;
        if (size <= 0) size = 10;

        int offset = (page - 1) * size;
        List<Fournisseur> fournisseurs = null;

        try (Session session = HibernateUtil.getSession()) {
            fournisseurs = session
                    .createQuery("FROM Fournisseur f order by f.id", Fournisseur.class)
                    .setFirstResult(offset)
                    .setMaxResults(page)
                    .list();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }

        return fournisseurs;
    }

    // La methode responsable de créer un fournisseur
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

    // Compter le nombre de fournisseurs dans la BD
    @Override
    public long count() {
        try (Session session = HibernateUtil.getSession()) {
            return session
                    .createQuery("SELECT count(f) from Fournisseur f", Long.class)
                    .uniqueResult();
        }
    }
}
