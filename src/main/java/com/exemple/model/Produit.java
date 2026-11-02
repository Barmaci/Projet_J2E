package com.exemple.model;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "produits")
public class Produit implements Serializable {
//attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String reference;
    private String designation;
    private Double prixUnitaire;
    private Integer quantiteStock;
    private Integer seuilAlerte;
//Constructors
    public Produit() {
    }
    public  Produit ( String reference, String designation, Double prixUnitaire, Integer quantiteStock, Integer seuilAlerte){
        this.reference = reference;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.quantiteStock = quantiteStock;
        this.seuilAlerte = seuilAlerte;
    }
// getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(Integer quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public Integer getSeuilAlerte() {
        return seuilAlerte;
    }

    public void setSeuilAlerte(Integer seuilAlerte) {
        this.seuilAlerte = seuilAlerte;
    }

}
