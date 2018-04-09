/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Enfant;
import edu.AllForKids.entities.User;
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
public class CrudEnfant {
       Connection connexion = MyConnexion.getInstance().getConnection();
   
    
    
    
    // Ajout Enfant
    public void ajouterEnfant(Enfant p) {
        try {
            System.out.println(p.toString());
            
            String query = "INSERT INTO `enfant`(`nom`, `prenom`, `DateNaissance`, `id_parent_id`) VALUES" +""
                    + "( '"+p.getNom()+"','"+p.getPrenom()+"','"+p.getDateNaissance()+"','"+p.getIdParent()+"' )";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Ajout effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    //Modification enfant
    public void modifierEnfant(Enfant p) {
        try {
            System.out.println(p.toString());
                   
            String query = "UPDATE `enfant` SET `nom`='"+p.getNom()+"',`prenom`='"+p.getPrenom()+"',`datenaissance`='"+p.getDateNaissance()+"',`id_Parent_id`='"+p.getIdParent()+"'"
                    +  "WHERE `id`="+p.getId()+"";
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Modification effectué");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
    
    //Suppression Enfant
    public void supprimerEnfant(int id) {
         try {
            String query = "DELETE FROM `enfant` "
                   + "WHERE id="+id+"";
           
             Statement stm= connexion.createStatement();
            stm.executeUpdate(query);
            System.out.println("Suppression effectué");
        } catch (SQLException ex) {
            System.out.println("Echec de suppression");
        }
    }
    public List<Enfant> listofkids() {
        try {
            String query = "SELECT * FROM `enfant`" ;
            ArrayList Resultat = new ArrayList<Enfant>();
            Statement stm= connexion.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next())
            {
            Enfant e=new Enfant(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),""+rs.getDate("DateNaissance"),rs.getInt("id_parent_id"));
            Resultat.add(e);
            }
            System.out.println("operation effectuee");
            return Resultat;
        } catch (SQLException ex) {
            System.out.println("Echec");
            return null;
        } 
    }
    
    public List<Enfant> listEnfantByParentId(User u) {
        try {
            String query = "SELECT * FROM `enfant`"+" WHERE id_parent_id="+u.getId()+"" ;
            ArrayList Resultat = new ArrayList<Enfant>();
            Statement stm= connexion.createStatement();
            ResultSet rs=stm.executeQuery(query);
            while (rs.next())
            {
            Enfant e=new Enfant(rs.getInt("id"),rs.getString("nom"),rs.getString("prenom"),""+rs.getDate("DateNaissance"),rs.getInt("id_parent_id"));
            Resultat.add(e);
            }
            System.out.println("operation effectuee");
            return Resultat;
        } catch (SQLException ex) {
            System.out.println("Echec");
            return null;
        } 
    }
    
    
    
    
}
