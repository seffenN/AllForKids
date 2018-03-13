package edu.AllForKids.utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Seif BelHadjAli
 */
public class MyConnexion {

    private static MyConnexion instance;
    String url = "jdbc:mysql://localhost:3306/allforkidsdb";
    String login = "root";
    String pwd = "";
    Connection cnx;

    private MyConnexion() {
        try {
            cnx = DriverManager.getConnection(url, login, pwd);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            Logger.getLogger(MyConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getConnection(){
        return cnx;
    }

    public static MyConnexion getInstance() {
        if (instance == null) {
            instance = new MyConnexion();
        }
        return instance;
    }
}
