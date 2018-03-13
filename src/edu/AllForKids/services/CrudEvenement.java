package edu.AllForKids.services;


import edu.AllForKids.entities.categorie;
import edu.AllForKids.entities.evenement;
import edu.AllForKids.utils.MyConnexion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Amine
 */
public class CrudEvenement {

    Connection cnx = MyConnexion.getInstance().getConnection();
    
    public void ajouterPersonne(categorie c){
        try {
            String requete = "INSERT INTO personne (nomCategorie) VALUES (?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNomCategorie());
            pst.executeUpdate();
            System.out.println("Categorie ajoutée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public List<categorie> afficherCategorie(){
         List<categorie> myList = new ArrayList<categorie>();
        try {
           
            String requete2 = "SELECT * FROM categorie";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete2);
            
            while(rs.next()){
                categorie p = new categorie();
                p.setNomCategorie(rs.getString(2));
                myList.add(p);
            }
            
        } catch (SQLException ex) {
                System.err.println(ex.getMessage());
        }
        return myList;
    }
    
   
    
    public void modifierCategorie(categorie c,int id){
        try {
            String requete = "UPDATE  personne SET nom=?,prenom=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(3,id);
            pst.setString(1, c.getNomCategorie());
            pst.executeUpdate();
            System.out.println("Personne supprimée");
            
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
