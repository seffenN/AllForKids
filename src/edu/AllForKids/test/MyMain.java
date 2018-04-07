package edu.AllForKids.test;

import com.stripe.Stripe;
import com.stripe.net.RequestOptions;
import edu.AllForKids.entities.Produits;
import edu.AllForKids.services.CrudStore;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
        /*Produits p = new Produits(1, 88F, "Robe", 8, "image", "vetements", true, "en attente");

        Produits p2 = new Produits(88F, "AMINE", 100, "image", "vetements", false, "en attente");
        //CrudStore.insererProduit(p2);
        CrudStore c = new CrudStore();
        c.ModifierProduit(p2);
        ///////////////////

        Stripe.apiKey = "sk_test_BQokikJOvBiI2HlWgH4olfQ2";

        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", 2000);
        chargeParams.put("currency", "usd");
        chargeParams.put("description", "Charge for william.wilson@example.com");
        chargeParams.put("source", "tok_visa");
// ^ obtained with Stripe.js

        RequestOptions options = RequestOptions
                .builder()
                .setIdempotencyKey("nMajISGAfK3YtQbR")
                .build();

        //charge.create(chargeParams, options);*/
        

    }

}
