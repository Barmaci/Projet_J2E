package com.exemple.service;

import com.exemple.dao.MouvementStockDao;
import com.exemple.model.MouvementStock;
import com.exemple.model.Produit;
import com.exemple.model.TypeMouvementStock;

import java.time.LocalDateTime;
import java.util.List;

public class MouvementStockService {
    private final MouvementStockDao mouvementStockDao = new MouvementStockDao();
    private final ProduitService produitService = new ProduitService();

    public void enregistrerMouvement( Integer pId, TypeMouvementStock type, String cmt, int q){
        Produit p = produitService.listerProduit(pId);

        int delta = type == TypeMouvementStock.ENTREE ? q : -q;

        MouvementStock mv = new MouvementStock();
        mv.setType(type);
        mv.setCommentaire(cmt);
        mv.setQuantite(q);
        mv.setDate(LocalDateTime.now());

        mouvementStockDao.save(mv , pId, delta);

    }

    public List<MouvementStock> listerParProduit(int produitId){
        return mouvementStockDao.findAllForProduit(produitId);
    }


}
