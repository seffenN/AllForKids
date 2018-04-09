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
public class Lesson {
       public int id;
        public String nomLesson;
    public String brochure;
    public String categorieAge;
    public String Theme;
    public Date date;
    public int flag;

    public Lesson(String nomLesson,String brochure, String categorieAge, String Theme) {
        this.brochure = brochure;
        this.categorieAge = categorieAge;
        this.Theme = Theme;
        this.nomLesson=nomLesson;
        
    }

    public String getNomLesson() {
        return nomLesson;
    }

    public void setNomLesson(String nomLesson) {
        this.nomLesson = nomLesson;
    }

    public Lesson() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrochure() {
        return brochure;
    }

    public void setBrochure(String brochure) {
        this.brochure = brochure;
    }

    public String getCategorieAge() {
        return categorieAge;
    }

    public void setCategorieAge(String categorieAge) {
        this.categorieAge = categorieAge;
    }

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String Theme) {
        this.Theme = Theme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Lesson{" + "id=" + id + ", nomLesson=" + nomLesson + ", brochure=" + brochure + ", categorieAge=" + categorieAge + ", Theme=" + Theme + ", date=" + date + ", flag=" + flag + '}';
    }
    
    
}
