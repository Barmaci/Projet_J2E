package com.exemple.service;

import com.exemple.dao.FournisseurDao;
import com.exemple.model.Fournisseur;
import com.exemple.util.PageResult;

import java.util.List;

public class FournisseurService {
    private final FournisseurDao fournisseurDao = new FournisseurDao();

    public PageResult<Fournisseur> lister(int page, int size) {
        List<Fournisseur> contenu = fournisseurDao.findAll(page, size);
        long total = fournisseurDao.count();

        return new PageResult<>(contenu, page, size, total);
    }
}
