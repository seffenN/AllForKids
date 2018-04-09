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
public class Pediatre extends User{
     private String adresse;
    private String ville;

    public Pediatre(String adresse, String ville, int id, String username) {
        super(id, username);
        this.adresse = adresse;
        this.ville = ville;
    }

   

    public Pediatre(String adresse, String ville, int id) {
        super(id);
        this.adresse = adresse;
        this.ville = ville;
    }

    public Pediatre(int id) {
        
    }

    public Pediatre(String adresse) {
        this.adresse = adresse;
    }

    public Pediatre(String adresse, int id) {
        super(id);
        this.adresse = adresse;
    }

    public Pediatre() {
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
     public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    @Override
    public String toString() {
        return "Pediatre{" + "adresse=" + adresse + ", ville=" + ville + '}';
    }
    
}
