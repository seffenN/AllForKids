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
    public static void main(String[] args) throws SQLException {
        Produits p=new Produits(1,88F,"Robe",50,"image","vetements",true,"en attente");
       // CrudStore.insererProduit(p);
        CrudStore.selectProduit();
        
        
        
        
    }
    
}
