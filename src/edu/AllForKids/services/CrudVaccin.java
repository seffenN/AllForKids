/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;


import edu.AllForKids.entities.Vaccin;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author IRONSIDE
 */
public class CrudVaccin {
    
     Connection connexion = MyConnexion.getInstance().getConnection();

     public void ajouterVaccin(Vaccin p) {
        try {
            System.out.println(p.toString());
                   
            String query = "INSERT INTO `vaccin`(`nom`, `age`, `matricule`, `description`) VALUES" +""
                    + "( '"+p.getNomVaccin()+"','"+p.getAge()+"','"+p.getMaladies()+"','"+p.getDescription()+"' )";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    
   
     }
      public void modifierVaccin(Vaccin p) {
        try {
            System.out.println(p.toString());
                   
            String query = "UPDATE `vaccin` SET `nom`='"+p.getNomVaccin()+"',`age`='"+p.getAge()+"',`matricule`='"+p.getMaladies()+"',`description`='"+p.getDescription()+"'"
                    +  "WHERE `id`="+p.getId()+"";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println("Echec d'ajout");
        }
    }
    
    
    
    //Suppression Vaccin
    public void supprimerVaccin(int id) {
         try {
            String query = "DELETE FROM `vaccin` "
                   + "WHERE id="+id+"";
           
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de suppression");
        }
    }
    
    public List<Vaccin> listofvaccins() {
        try {
            String query = "SELECT * FROM vaccin";
            ArrayList Resultat = new ArrayList<Vaccin>();
            Statement stm= connexion.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next())
            {
            Vaccin e=new Vaccin(rs.getInt("id"), rs.getString("nom"), rs.getString("age"), rs.getString("matricule"), rs.getString("description"));
            Resultat.add(e);
            }
            System.out.println("operation effectuee");
            return Resultat;
        } catch (SQLException ex) {
            System.out.println("Echec" +ex.getMessage());
            return null;
        } 
    }
       public List<Vaccin> listofvaccinsbyage(int ag) {
        try {
            String query = "SELECT * FROM `vaccin`"+ "WHERE `age` < "+ag+"";
            System.out.println("SQL     "+query);
            ArrayList Resultat = new ArrayList<Vaccin>();
            Statement stm= connexion.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next())
            {
            Vaccin e=new Vaccin(rs.getInt("id"), rs.getString("nom"), rs.getString("age"), rs.getString("matricule"), rs.getString("description"));
            Resultat.add(e);
            }
            System.out.println("operation effectuee");
            return Resultat;
        } catch (SQLException ex) {
            System.out.println("Echec"+ex.getMessage());
            return null;
        } 
    }
}
