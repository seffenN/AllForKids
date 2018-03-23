package edu.AllForKids.test;

import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.sql.SQLException;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seif BelHadjAli
 */
public class MyMain {
    public static void main(String[] args) throws SQLException, Exception {
        Produits p=new Produits(1,88F,"Robe",8,"image","vetements",true,"en attente");
        
         Produits p2=new Produits(88F,"AMINE",100,"image","vetements",false,"en attente");
         //CrudStore.insererProduit(p2);
      CrudStore c=new CrudStore();
      c.ModifierProduit(p2);
       
        //mafhemteh aleh loula maykrahesh
        //trah ab3athli entité w methode bizarre taw njarabha 3andi
        
        
        
    }
    
}
