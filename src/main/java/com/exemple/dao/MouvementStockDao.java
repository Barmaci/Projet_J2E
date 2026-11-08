package com.exemple.dao;

import com.exemple.model.MouvementStock;
import com.exemple.model.Produit;
import com.exemple.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MouvementStockDao implements MouvementStockDaoInterface {

    @Override
    public void save(MouvementStock mouvementStock, Integer produitId, int deltaQuantite) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction tx = session.beginTransaction();
            try {
                Produit p = session.find(Produit.class, produitId);

                int nouveauStock = p.getQuantiteStock() + deltaQuantite;
                if (nouveauStock < 0) throw new RuntimeException("Stock insuffisant");

                p.setQuantiteStock(nouveauStock);
                mouvementStock.setProduit(p);

                session.persist(mouvementStock);
                tx.commit();
            } catch (RuntimeException e) {
                if (tx.isActive()) tx.rollback();
                throw e;
            }
        }
    }

    @Override
    public List<MouvementStock> findAllForProduit(int pId) {
        try (Session session = HibernateUtil.getSession()) {
            String hql = "SELECT mv FROM MouvementStock mv WHERE mv.produit.id = :id ORDER BY mv.date DESC";

            return session.createQuery(hql, MouvementStock.class)
                    .setParameter("id", pId)
                    .getResultList();

        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
