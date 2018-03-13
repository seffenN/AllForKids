/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.entities;

/**
 *
 * @author khaoula
 */
public class Quiz {
    private int id;
    private String nom_quiz;
    private String theme;
    private String categorie_age;
    private int time;
    private String description;
    private String image;
    private int total;
    private int flag;

    public Quiz(String nom_quiz, String theme, String categorie_age, int time, String description, String image, int total, int flag) {
        this.nom_quiz = nom_quiz;
        this.theme = theme;
        this.categorie_age = categorie_age;
        this.time = time;
        this.description = description;
        this.image = image;
        this.total = total;
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_quiz() {
        return nom_quiz;
    }

    public void setNom_quiz(String nom_quiz) {
        this.nom_quiz = nom_quiz;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCategorie_age() {
        return categorie_age;
    }

    public void setCategorie_age(String categorie_age) {
        this.categorie_age = categorie_age;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    
}
