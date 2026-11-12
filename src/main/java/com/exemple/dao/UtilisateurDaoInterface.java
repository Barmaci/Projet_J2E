package com.exemple.dao;

import com.exemple.model.Utilisateur;

import java.util.List;

public interface UtilisateurDaoInterface {

    public List<Utilisateur> findAll(int page, int size);
    public Utilisateur findOne(int id);
    public Utilisateur findByLogin(String login);
    public void save(Utilisateur utilisateur);
    public Utilisateur update(Utilisateur newUtilisateur);
    public long count();
}
