package com.exemple.dao;

import com.exemple.model.Produit;

import java.util.List;

public interface ProduitDaoInterface {

    public List<Produit> findAll(int page , int size);
    public void save(Produit produit);
    public long count();
}
