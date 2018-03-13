/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Narjes
 */
public class CrudStore {
    static MyConnexion con=MyConnexion.getInstance();
    public static void insererProduit(Produits p ) throws SQLException{
        String req="INSERT INTO `produits`(`id`, `Prix`, `Nom`, `Quantite`, `image`, `Categorie`, `Disponible`, `etat`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ste = con.getConnection().prepareStatement(req);
            ste.setInt(1,p.getId());
            ste.setFloat(2,p.getPrix());
            ste.setString(3,p.getNom());            
            ste.setInt(4,p.getQuantite());
            ste.setString(5,p.getImage());           
            ste.setString(6,p.getCategorie());
            ste.setBoolean(7,p.isDisponible());
            ste.setString(8,p.getEtat());

            ste.executeUpdate();
        }
    public static void selectProduit(){
            
            try {
                String req = "SELECT * FROM `produits`";
                PreparedStatement ste = con.getConnection().prepareStatement(req);
                ResultSet rs = ste.executeQuery();
                while (rs.next())
                {     
                    int id= rs.getInt("id");
                    float prix= rs.getFloat("Prix");
                    String nom = rs.getString("Nom");
                    int quantite= rs.getInt("Quantite");
                    String img = rs.getString("image");
                    String categorie = rs.getString("Categorie");
                    Boolean disponible = rs.getBoolean("Disponible");
                    String etat = rs.getString("etat");
                            
                    // print the results
                    System.out.format("%s, %s, %s, %s, %s, %s, %s, %s\n",id,prix,nom,quantite,img,categorie,disponible,etat);
                }
            }catch (SQLException e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
            }
public static void DeleteProduit(int id){
       
        try {
            String req= "DELETE FROM `produits`  WHERE id=? ";
            PreparedStatement ste = con.getConnection().prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    
    
    
}         
