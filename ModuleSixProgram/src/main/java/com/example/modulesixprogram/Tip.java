package com.example.modulesixprogram;

//Class for calculating the tip
public class Tip {

    private double tipAmount ;

    private double postTipPrice ;

    //Adds a tip to a double pre tip amount.
    public double addTip(double preTipAmount) {

        postTipPrice = preTipAmount + tipAmount ;

        return postTipPrice ;
    }
    
}