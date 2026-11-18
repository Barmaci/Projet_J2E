package com.exemple.model;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="fournisseurs")
public class Fournisseur implements Serializable{
    //attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String telephone;
    private String adresse;

    @OneToMany(mappedBy = "fournisseur", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Produit> produits = new ArrayList<>();

    //Constructeurs
    public Fournisseur() {
    }

    public Fournisseur(Integer id, String nom, String telephone, String adresse) {
        this.id = id;
        this.nom = nom;
        this.telephone = telephone;
        this.adresse = adresse;
    }

    //getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public List<Produit> getProduits(){
        return produits;
    }
}
