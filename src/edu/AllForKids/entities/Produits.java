/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.entities;

/**
 *
 * @author Narjes
 */
public class Produits {
    private int id;
    private String nom;
    private float prix;
    private int quantite;
    private String etat;
    private boolean disponible;
    private String categorie;
    public String image;
      public Produits() {
    }

    public Produits(int id, float prix,String nom, int quantite, String image,String categorie, boolean disponible,String etat ) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantite = quantite;
        this.etat = etat;
        this.disponible = disponible;
        this.categorie = categorie;
        this.image = image;
    }

   
    

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public float getPrix() {
        return prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public String getEtat() {
        return etat;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public String getCategorie() {
        return categorie;
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

    public void setDiponibilité(boolean disponible) {
        this.disponible= disponible;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }
    
    
}
