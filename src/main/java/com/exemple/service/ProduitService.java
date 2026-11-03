package com.exemple.service;

import com.exemple.dao.ProduitDao;
import com.exemple.model.Produit;
import com.exemple.util.PageResult;

import java.util.List;

public class ProduitService {
    private final ProduitDao produitDao = new ProduitDao();

    public PageResult<Produit> lister( int page , int size){
        List<Produit> contenu = produitDao.findAll(page , size);
        long total = produitDao.count();

        return new PageResult<>(contenu, page, size, total);
    }
}
