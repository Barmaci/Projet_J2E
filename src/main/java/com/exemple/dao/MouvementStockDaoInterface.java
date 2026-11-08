package com.exemple.dao;

import com.exemple.model.MouvementStock;
import com.exemple.model.Produit;

import java.util.List;

public interface MouvementStockDaoInterface {
    public void save(MouvementStock mouvementStock , Integer produitId, int deltaQuantite);
    public List<MouvementStock> findAllForProduit(int pId);
}
