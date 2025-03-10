package com.example.modulesixprogram;

import java.util.* ;

//Formats toppings in case there are multiple toppings added
public class FormatToppings{

    //Converts multiple topping strings in a hashmap into one string.
    public static String ConvertToOneString(LinkedHashMap<String,Double> p) {

        String toppingList = " ";

        //Iterates though each key in the map and adds them to a single string and returns the string.
       for (Map.Entry<String, Double> entry : p.entrySet()) {

        toppingList += entry.getKey() + ", ";


       }

        return toppingList ;
    }

    //Iterates through each value in a map and calculates the total price of the toppings and returns the value.
    public static double ConvertPriced(LinkedHashMap<String, Double> p){

        double price = 0.0;

        for (Map.Entry<String, Double> entry : p.entrySet()) {

            price += entry.getValue() ;


        }

        return price ;
    }


  
    
}

