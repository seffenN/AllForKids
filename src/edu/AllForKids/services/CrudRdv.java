/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;


import edu.AllForKids.entities.Rdv;
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
     public void ajouterRdv(Rdv p) {
        try {
            System.out.println(p.toString());
                   
            String query = "INSERT INTO `rdv`(`date`, `idenfant`, `idpediatre`,`vaccin_id`) VALUES" +""
                    + "( '"+p.getDate()+"','"+p.getIdEnfant()+"','"+p.getIdPediatre()+"','"+p.getIdVaccin()+"')";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }
    
}
