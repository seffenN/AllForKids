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
public class Rdv {
    
    int id;
    String date;
    String heure;
    int idEnfant;
    int idPediatre;

    public Rdv(int id, String date, String heure, String pediatre, int idPediatre, int idEnfant) {
        this.id = id;
        this.date = date;
        this.heure = heure;
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

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
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
    
    @Override
    public String toString() {
        return "Rendez vous{" + "date=" + date + ", heure=" + heure +  ", idPediatre=" + idPediatre + ",idEnfant=" + idEnfant + '}';
    }
    
}
