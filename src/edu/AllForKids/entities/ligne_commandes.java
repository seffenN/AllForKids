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
public class ligne_commandes {
      private int id_produit;
    private int id_commande;
    private float prix_commande;
    private int quantite;

    public ligne_commandes() {
    }

    public ligne_commandes(int id_produit, int id_commande, float prix_commande, int quantite) {
        this.id_produit = id_produit;
        this.id_commande = id_commande;
        this.prix_commande = prix_commande;
        this.quantite = quantite;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public float getPrix_commande() {
        return prix_commande;
    }

    public void setPrix_commande(float prix_commande) {
        this.prix_commande = prix_commande;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    
}
