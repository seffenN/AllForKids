/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.AllForKids.entities;

import java.util.Date;


/**
 *
 * @author LENOVO
 */
public class evenement {
    private int id_even;
    private String titre;
    private Object Categorie_id;
    private String nom_image;
    private Date date_debut;
    private String description;
    private int ticket_disponible	;
    private Float Tarif;

    public evenement() {
    }
    
    
        public evenement(int id_even, String titre, int Categorie_id, String nom_image, Date date_debut, String description, int ticket_disponible, Float Tarif) {
        this.id_even = id_even;
        this.titre = titre;
        this.Categorie_id = Categorie_id;
        this.nom_image = nom_image;
        this.date_debut = date_debut;
        this.description = description;
        this.ticket_disponible = ticket_disponible;
        this.Tarif = Tarif;
    }
    
    
    public int getId_even() {
        return id_even;
    }

    public void setId_even(int id_even) {
        this.id_even = id_even;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Object getCategorie_id() {
        return Categorie_id;
    }

    public void setCategorie_id(Object Categorie_id) {
        this.Categorie_id = Categorie_id;
    }

   

    public void setCategorie_id(int Categorie_id) {
        this.Categorie_id = Categorie_id;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public Date getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(Date date_debut) {
        this.date_debut = date_debut;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTicket_disponible() {
        return ticket_disponible;
    }

    public void setTicket_disponible(int ticket_disponible) {
        this.ticket_disponible = ticket_disponible;
    }

    public Float getTarif() {
        return Tarif;
    }

    public void setTarif(Float Tarif) {
        this.Tarif = Tarif;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id_even=" + id_even + ", titre=" + titre + ", Categorie_id=" + Categorie_id + ", nom_image=" + nom_image + ", date_debut=" + date_debut + ", description=" + description + ", ticket_disponible=" + ticket_disponible + ", Tarif=" + Tarif + '}';
    }

    
    
   
    
    
}
