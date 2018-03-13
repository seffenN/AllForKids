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
public class Reponse {
    private int id;
    private int id_quest_id;
    private String libelle;
    private int verif;
    private int point;
    private int flag;

    public int getId_quest_id() {
        return id_quest_id;
    }

    public void setId_quest_id(int id_quest_id) {
        this.id_quest_id = id_quest_id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public int getVerif() {
        return verif;
    }

    public void setVerif(int verif) {
        this.verif = verif;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Reponse(int id_quest_id, String libelle, int verif, int point, int flag) {
        this.id_quest_id = id_quest_id;
        this.libelle = libelle;
        this.verif = verif;
        this.point = point;
        this.flag = flag;
    }
    
    
    
}
