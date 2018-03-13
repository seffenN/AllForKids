/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author IRONSIDE
 */
public class Enfant {
    
    int id;
    String nom;
    String prenom;
    String dateNaissance;
    int idParent;

    public Enfant(int id, String nom, String prenom, String dateNaissance, int idParent) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.idParent = idParent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public int getIdParent() {
        return idParent;
    }

    public void setIdParent(int idParent) {
        this.idParent = idParent;
    }
    
    
     @Override
    public String toString() {
        return "Enfant{" + "nom=" + nom + ", prenom=" + prenom +  ", date de naissance=" + dateNaissance + ",idParent=" + idParent + '}';
    }
}
