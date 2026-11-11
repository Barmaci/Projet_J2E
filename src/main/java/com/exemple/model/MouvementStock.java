package com.exemple.model;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "mouvement_stock")
public class MouvementStock implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private TypeMouvementStock type;

    @Column(name = "quantite", nullable = false)
    private Integer quantite;

    @Column(name = "date_creation", nullable = false)
    private LocalDateTime date;

    @Column(name = "commentaire", nullable = true)
    private String commentaire;

    @ManyToOne(fetch = FetchType.LAZY) // Lazy fetch permet de ne pas fetcher les mouvement de stock d'un produit lorsque on fetch un produit`
    @JoinColumn(name = "produit_id")
    private Produit produit;

    public MouvementStock() {
    }

    public MouvementStock(Integer id, TypeMouvementStock type, Integer quantite, LocalDateTime date, String commentaire) {
        this.id = id;
        this.type = type;
        this.quantite = quantite;
        this.date = date;
        this.commentaire = commentaire;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TypeMouvementStock getType() {
        return type;
    }

    public void setType(TypeMouvementStock type) {
        this.type = type;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Produit getProduit() {
        return this.produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }
}
