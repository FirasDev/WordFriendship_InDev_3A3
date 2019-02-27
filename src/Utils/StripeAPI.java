/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Customer;
import com.stripe.model.Token;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author DongFeng
 */
public class StripeAPI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws StripeException {
        Stripe.apiKey="sk_test_flb9vUYyiwC85Wx2ONpANeYf";
  
      /*  Map<String, Object> customerParams = new HashMap<String, Object>();
        customerParams.put("email", "test@gmail.com");
        Customer newCustomer = Customer.create(customerParams);
        System.out.println(newCustomer.getId());*/
        
        
        Customer a =Customer.retrieve("cus_EabQCFUrV5J5qW");
       Map<String, Object> cardParams = new HashMap<String, Object>();
        cardParams.put("number", "4242424242424242");
        cardParams.put("exp_month", "11");
        cardParams.put("exp_year", "2022");
        cardParams.put("cvc", "123");
        
        Map<String, Object> tokenParams = new HashMap<String, Object>();
        tokenParams.put("card", cardParams);
        
        Token token = Token.create(tokenParams);
        
        Map<String, Object> source = new HashMap<String, Object>();
        source.put("source", token.getId());
        
        a.getSources().create(source);
        
        Map<String, Object> chargeParams = new HashMap<String, Object>();
        chargeParams.put("amount", "500");
        chargeParams.put("currency", "usd");
        chargeParams.put("customer", a.getId());
       
        
        Charge.create(chargeParams);
        
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(a));
        
        
     
    }
    
}
