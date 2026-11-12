package com.exemple.dao;

import com.exemple.model.Produit;

import java.util.List;

public interface ProduitDaoInterface {

    public List<Produit> findAll(int page , int size);
    public Produit findOne(int id);
    public void save(Produit produit);
    public Produit update(Produit newProduit);
    public void delete(int id);
    public long count();
}
