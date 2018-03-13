/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.allforkids.entities;

/**
 *
 * @author Narjes
 */
public class Produit {
    private int id;
    private String nom;
    private float prix;
    private int quantite;
    private String etat;
    private boolean diponibilité;
    private String categorie;

    public Produit() {
    }

    public Produit(int id, String nom, float prix, int quantite, String etat, boolean diponibilité, String categorie) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.etat = etat;
        this.diponibilité = diponibilité;
        this.categorie = categorie;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setDiponibilité(boolean diponibilité) {
        this.diponibilité = diponibilité;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    
}
