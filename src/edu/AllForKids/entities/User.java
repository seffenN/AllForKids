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
public class User {

    private int id_user;
     private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private int enabled;
    private String salt;
    private String password;
    private Date last_login;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private String nom_image;
    private int Age;
    private String Sexe;
    private String adresse;
    private String Ville;
    private int nbrAnneeExp;
    private String Etat;
    private int numTe;

    public User() {
    }

    public User(String username, String email, String password, String roles, int Age, String Sexe, String adresse) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.Age = Age;
        this.Sexe = Sexe;
        this.adresse = adresse;
    }

    public User(String username, String email, String password, String nom_image, String roles, String Sexe) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nom_image = nom_image;

        this.roles = roles;
        this.Sexe = Sexe;
    }

    public User(int id, String username, String email, int enabled, String password, String roles, String nom_image) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enabled = enabled;
        this.password = password;
        this.roles = roles;
        this.nom_image = nom_image;

    }

    public User(String email, String password) {
        this.password = password;
        this.email = email;
    }

    public User(int id, String username, String email, String password, String roles, String Sexe) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.Sexe = Sexe;
    }

    public User(int id, String username, String email, String password, String roles, String nom_image, String Sexe, String adresse, int enabled) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.nom_image = nom_image;
        this.Sexe = Sexe;
        this.adresse = adresse;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return Ville;
    }

    public void setVille(String Ville) {
        this.Ville = Ville;
    }

    public int getNbrAnneeExp() {
        return nbrAnneeExp;
    }

    public void setNbrAnneeExp(int nbrAnneeExp) {
        this.nbrAnneeExp = nbrAnneeExp;
    }

    public String getEtat() {
        return Etat;
    }

    public void setEtat(String Etat) {
        this.Etat = Etat;
    }

    public int getNumTe() {
        return numTe;
    }

    public void setNumTe(int numTe) {
        this.numTe = numTe;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", last_login=" + last_login + ", confirmation_token=" + confirmation_token + ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", nom_image=" + nom_image + ", Age=" + Age + ", Sexe=" + Sexe + ", adresse=" + adresse + ", Ville=" + Ville + ", nbrAnneeExp=" + nbrAnneeExp + ", Etat=" + Etat + ", numTe=" + numTe + '}';
    }

}
