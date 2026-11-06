package com.exemple.dao;

import com.exemple.model.Fournisseur;

import java.util.List;

public interface FournisseurDaoInterface {
    public List<Fournisseur> findAll(int page , int size);
    public void save(Fournisseur fournisseur);
    public long count();
}
