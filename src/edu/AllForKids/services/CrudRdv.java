/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import Entity.Rdv;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author IRONSIDE
 */
public class CrudRdv {
    Connection connexion = MyConnexion.getInstance().getConnection();
     // Ajout Rendez vous
    public void ajouterVaccin(Rdv p) {
        try {
            System.out.println(p.toString());
                   
            String query = "INSERT INTO `rdv`(`date`, `heure`, `idEnfant`, `idPediatre`) VALUES" +""
                    + "( '"+p.getDate()+"','"+p.getHeure()+"',"+p.getIdEnfant()+",'"+p.getIdPediatre()+" )";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }
    
}
