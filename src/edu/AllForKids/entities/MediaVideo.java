/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.entities;

import java.util.Date;

/**
 *
 * @author khaoula
 */
public class MediaVideo {
    private int id;
    private String titre;
    private String type;

    @Override
    public String toString() {
        return "MediaVideo{" + "id=" + id + ", titre=" + titre + ", type=" + type + ", identif=" + identif + ", date=" + date + ", theme=" + theme + ", categorieAge=" + categorieAge + ", description=" + description + ", flag=" + flag + '}';
    }
    private String identif;
    private Date date;
    private String theme;
    private String categorieAge;
    private  String description;
    private int flag;

    public MediaVideo(String type,String titre, String identif,  String theme, String categorieAge, String description) {
        this.type = type;
        this.identif = identif;
        
        this.theme = theme;
        this.categorieAge = categorieAge;
        this.description = description;
        this.titre=titre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public MediaVideo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIdentif() {
        return identif;
    }

    public void setIdentif(String identif) {
        this.identif = identif;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getCategorieAge() {
        return categorieAge;
    }

    public void setCategorieAge(String categorieAge) {
        this.categorieAge = categorieAge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
    
    
    
}
