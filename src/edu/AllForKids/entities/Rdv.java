/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.entities;

/**
 *
 * @author IRONSIDE
 */
public class Rdv {
    
     int id;
    String date;
    int idEnfant;
    int idPediatre;
    int idVaccin;

    public Rdv() {
    }

    public Rdv(int id, String date, int idEnfant, int idPediatre, int idVaccin) {
        this.id = id;
        this.date = date;
        this.idEnfant = idEnfant;
        this.idPediatre = idPediatre;
        this.idVaccin = idVaccin;
    }
    
    

    public Rdv(int id, String date, String pediatre, int idPediatre, int idEnfant) {
        this.id = id;
        this.date = date;
        
        this.idEnfant = idEnfant;
        this.idPediatre = idPediatre;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

   

   

    public int getIdEnfant() {
        return idEnfant;
    }

    public void setIdEnfant(int idEnfant) {
        this.idEnfant = idEnfant;
    }

    public int getIdPediatre() {
        return idPediatre;
    }

    public void setIdPediatre(int idPediatre) {
        this.idPediatre = idPediatre;
    }

    public int getIdVaccin() {
        return idVaccin;
    }

    public void setIdVaccin(int idVaccin) {
        this.idVaccin = idVaccin;
    }
    
    
    @Override
    public String toString() {
        return "Rendez vous{" + "date=" + date +   ", idPediatre=" + idPediatre + ",idEnfant=" + idEnfant + '}';
    }
    
}
