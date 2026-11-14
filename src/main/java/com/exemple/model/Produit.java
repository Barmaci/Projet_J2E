package com.exemple.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "produits")
public class Produit implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "reference", nullable = false, unique = true)
    private String reference;
    @Column(name = "designation", nullable = false)
    private String designation;
    @Column(name = "prix_unitaire", nullable = false)
    private Double prixUnitaire;
    @Column(name = "quantite_stock", nullable = false)
    private Integer quantiteStock;
    @Column(name = "seuil_alerte", nullable = false)
    private Integer seuilAlerte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

    @OneToMany(mappedBy = "produit", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<MouvementStock> mouvementStock = new ArrayList<>();

    public Produit() {
    }

    public Produit(String reference, String designation, Double prixUnitaire, Integer quantiteStock, Integer seuilAlerte) {
        this.reference = reference;
        this.designation = designation;
        this.prixUnitaire = prixUnitaire;
        this.quantiteStock = quantiteStock;
        this.seuilAlerte = seuilAlerte;
    }

    public List<MouvementStock> getMouvementStock() {
        return this.mouvementStock;
    }

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

    public Fournisseur getFournisseur(){
        return fournisseur;
    }

    public void setFournisseur(Fournisseur  fournisseur){
        this.fournisseur = fournisseur;
    }
}
