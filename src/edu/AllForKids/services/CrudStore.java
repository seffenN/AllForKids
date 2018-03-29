/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Narjes
 */
public class CrudStore {
    Connection con;

    public CrudStore() {
    con=MyConnexion.getInstance().getConnection();
    }
    
    
    
    
    public  void insererProduit(Produits p ) throws SQLException{
        String req="INSERT INTO `produits`(`id`, `Prix`, `Nom`, `Quantite`, `image`, `Categorie`, `Disponible`, `etat`) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement ste = con.prepareStatement(req);
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
    public  List<Produits> selectProduit(){
        List<Produits> produits=new ArrayList<Produits>();
            
            try {
                String req = "SELECT * FROM `produits`";
                PreparedStatement ste = con.prepareStatement(req);
                ResultSet rs = ste.executeQuery();
                while (rs.next())
                {     Produits p=new Produits(rs.getInt("id"),rs.getFloat("Prix"),rs.getString("Nom"),rs.getInt("Quantite"),rs.getString("image"),
                                     rs.getString("Categorie"),rs.getBoolean("Disponible"),rs.getString("etat") );
                    
                            
                   produits.add(p);
                }
            }catch (SQLException e)
            {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
            }
              return produits;
    }
            
public  void DeleteProduit(Produits p){
       
        try {
            String req= "DELETE FROM `produits`  WHERE id=? ";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, p.getId());
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CrudStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
       //Update person
             public  void ModifierProduit(Produits p) throws Exception{
                    
                        //System.out.println();
             
            String req= "UPDATE produits SET Prix=?,Nom=?,Quantite=?,image=?,Categorie=?,Disponible=?,etat=? WHERE id=?";
                 PreparedStatement ste = con.prepareStatement(req);
           ste.setString(2,p.getNom());  //chemou table store wala produits produits fel base //saker trah netbeans w 3a
           ste.setFloat(1,p.getPrix());
            ste.setString(4,p.getImage());
              ste.setString(5,p.getCategorie());
            ste.setInt(3,p.getQuantite());
            ste.setBoolean(6,p.isDisponible());
                        
          ste.setString(7,p.getEtat());
            ste.setInt(8,p.getId());
              
            ste.executeUpdate();
            
            
            
            
          /*  int rowsInserted =ste.executeUpdate(req);
             if (rowsInserted > 0) {
            System.out.println(" Garderie modifié avec succées  ");}
            } catch (SQLException ex) {
            System.err.println(ex.getMessage());*/
        
             }
             public Produits findById(int id) {
		String req="SELECT * FROM Produits WHERE id='"+id+"'" ;
        ResultSet rs;
        Produits p=null;
		try {
			rs = con.createStatement().executeQuery(req);
		
       
        while(rs.next()){
           p=new Produits();
            
           p.setId(rs.getInt("id"));
           p.setPrix(rs.getFloat("Prix"));
           p.setNom(rs.getString("Nom"));
            p.setQuantite(rs.getInt("Quantite"));
            p.setImage(rs.getString("image"));           
           p.setCategorie(rs.getString("Categorie"));
           p.setDiponibilité(rs.getBoolean("Disponible"));
           p.setEtat(rs.getString("etat"));

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return p;
    
		
	}
	 
       
    
    
}         
