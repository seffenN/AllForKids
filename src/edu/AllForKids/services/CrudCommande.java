/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.AllForKids.services;

import edu.AllForKids.entities.Commande;
import edu.AllForKids.entities.ligne_commandes;
import edu.AllForKids.utils.MyConnexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Narjes
 */
public class CrudCommande {
    Connection con;

    public CrudCommande() {
         con=MyConnexion.getInstance().getConnection();
    }
     public void AjouterCommande(Commande cmd) {
        
        java.util.Date date_commande = cmd.getDateCommande();
        java.sql.Date sqlDate_commande = new java.sql.Date(date_commande.getTime());
        
        try
        {   
            String req = "insert into commande(`dateCommande`,`idClient`) "+" values (?,?)"; 
            PreparedStatement  preparedStmt = con.prepareStatement(req);
            //PreparedStatement preparedStmt = db.getConnexion().prepareStatement(req);
           
            preparedStmt.setDate(1, sqlDate_commande);
            preparedStmt.setInt(2, cmd.getIdClient());
            preparedStmt.executeUpdate();
            System.out.println("ajout commande effectuer avec succes");
        }
        catch(SQLException add)
        {
            System.err.println("probleme d'ajout de commande "+add.getSQLState());
        }
    }
      public Commande ConsulterListe_Commandes() throws SQLException {
   
        Commande cmd=new Commande();
          String req= "select * from commande  ";
          Statement ste = con.createStatement();
          ResultSet s = ste.executeQuery(req);
          while(s.next())
          {  
             
              cmd.setIdCommande(s.getInt(1));
              cmd.setIdClient(s.getInt(2));
              cmd.setDateCommande(s.getDate(3));
              
                
          }
        return cmd;
      }
      
      //System.out.println(tabE.toString());
      
    
       public void AjouterLigne_Commande(ligne_commandes lc) {
        
        try
        {   
            String sql="Insert into ligne_commandes (idProduit,idCommande,PrixTotal,nbrArticle) values (?,?,?,?)";
                PreparedStatement preparedStmt = con.prepareStatement(sql);
                preparedStmt.setInt(1, lc.getId_produit());
                preparedStmt.setInt(2, lc.getId_commande());
                preparedStmt.setFloat(3, lc.getPrix_commande());
                preparedStmt.setInt(4, lc.getQuantite());
                preparedStmt.executeUpdate();
                System.err.println(preparedStmt.toString());
            System.out.println("ajout ligne commande effectuer avec succes");
        }
        catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
       public void DecrementerStock(int id,int nbrArticle) throws SQLException
       {
         String req= "UPDATE produits SET Quantite=Quantite-? WHERE id="+id;

            PreparedStatement preparedStmt = con.prepareStatement(req);
            preparedStmt.setInt(1, nbrArticle);
              preparedStmt.executeUpdate();
           
       }
       
    
}
