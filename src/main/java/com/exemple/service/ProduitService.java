package com.exemple.service;

import com.exemple.dao.ProduitDao;
import com.exemple.model.Produit;
import com.exemple.model.TypeMouvementStock;
import com.exemple.util.PageResult;

import java.util.List;

public class ProduitService {
    private final ProduitDao produitDao = new ProduitDao();

    public PageResult<Produit> lister(int page, int size) {
        List<Produit> contenu = produitDao.findAll(page, size);
        long total = produitDao.count();

        return new PageResult<>(contenu, page, size, total);
    }

    public Produit listerProduit(int id) {
        return produitDao.findOne(id);
    }

    public Produit modifier(Produit produit) {
        return produitDao.update(produit);
    }

    public void creer(Produit p) {
        final MouvementStockService service = new MouvementStockService();

        produitDao.save(p);
        service.enregistrerMouvement(p.getId(), TypeMouvementStock.ENTREE, "Stock initiale", p.getQuantiteStock());

    }

    public void supprimer(Integer id){
        produitDao.delete(id);
    }
}
