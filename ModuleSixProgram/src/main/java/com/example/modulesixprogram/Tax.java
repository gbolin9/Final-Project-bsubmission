package com.example.modulesixprogram;

//Applies sales tax to order
public class Tax {

    static final double salesTax = 0.07 ; //Sales tax in Indiana

    private static double postTaxAmount = 0.0 ;

    //Method applies tax to pre tax amount and then returns the post tax total
    public static double calculateTax(double preTaxAmount) {

        double tax = salesTax * preTaxAmount ;

        postTaxAmount = preTaxAmount + tax ;

        return postTaxAmount ;

        


    }

    
    
}

