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
public class User {

    private int id_user;
    private String nom;
    private String Email;
    private String sexe;
    private String mdp;
    private int enabled;

    public User(int id_user,String Email, String mdp,int enabled) {
 this.id_user=id_user;
        this.Email = Email;
       this.enabled=enabled;
      
        this.mdp = mdp;

    }

    public User() {
    }

    public String getMdp() {
        return mdp;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }


    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    String role;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
