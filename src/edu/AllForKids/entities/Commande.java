/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.entities;

import java.util.Date;

/**
 *
 * @author Narjes
 */
public class Commande {
    private int idCommande;
    private int idClient;
    private Date dateCommande;

    public Commande() {
    }

    public Commande(int idCommande, int idClient,Date dateCommande) {
        this.idCommande = idCommande;
        this.idClient = idClient;
        this.dateCommande=dateCommande;
                }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    
    
}
