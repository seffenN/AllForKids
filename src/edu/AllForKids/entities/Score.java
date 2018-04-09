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
public class Score {
    
    private int id;
    private double score;
    private int id_quiz_id;
    private int id_enfant;
    private Date datesaisie;

    public Date getDatesaisie() {
        return datesaisie;
    }

    public void setDatesaisie(Date datesaisie) {
        this.datesaisie = datesaisie;
    }

    public Score(double score, int id_quiz_id, int id_enfant) {
        this.score = score;
        this.id_quiz_id = id_quiz_id;
        this.id_enfant = id_enfant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId_quiz_id() {
        return id_quiz_id;
    }

    public void setId_quiz_id(int id_quiz_id) {
        this.id_quiz_id = id_quiz_id;
    }

    public int getId_enfant() {
        return id_enfant;
    }

    public void setId_enfant(int id_enfant) {
        this.id_enfant = id_enfant;
    }
    
    
    
    
}
