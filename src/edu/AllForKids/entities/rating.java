/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.AllForKids.entities;

/**
 *
 * @author LENOVO
 */
public class rating {
 
    private int id;
    private int rating;
    private int idUser;
    private int idEvenement;

    public rating(int id, int rating, int idUser, int idEvenement) {
        this.id = id;
        this.rating = rating;
        this.idUser = idUser;
        this.idEvenement = idEvenement;
    }

    public rating() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    @Override
    public String toString() {
        return "rating{" + "id=" + id + ", rating=" + rating + ", idUser=" + idUser + ", idEvenement=" + idEvenement + '}';
    }
    
    
    
}
