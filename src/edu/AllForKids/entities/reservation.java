/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.AllForKids.entities;

import java.util.Date;

/**
 *
 * @author LENOVO
 */
public class reservation {
    private int id_reservation;
    private Object id_even;
    private Object id_client;
    private int nbre_ticket;
    private Date date_reservation;

    public reservation(int id_reservation, Object id_even, Object id_client, int nbre_ticket, Date date_reservation) {
        this.id_reservation = id_reservation;
        this.id_even = id_even;
        this.id_client = id_client;
        this.nbre_ticket = nbre_ticket;
        this.date_reservation = date_reservation;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public Object getId_even() {
        return id_even;
    }

    public void setId_even(Object id_even) {
        this.id_even = id_even;
    }

    public Object getId_client() {
        return id_client;
    }

    public void setId_client(Object id_client) {
        this.id_client = id_client;
    }

    public int getNbre_ticket() {
        return nbre_ticket;
    }

    public void setNbre_ticket(int nbre_ticket) {
        this.nbre_ticket = nbre_ticket;
    }

    public Date getDate_reservation() {
        return date_reservation;
    }

    public void setDate_reservation(Date date_reservation) {
        this.date_reservation = date_reservation;
    }
    
    
    
}
