package com.example.modulesixprogram;

import java.util.HashMap ;


public class ToppingMenu {

    HashMap<String,Double> toppingMenu = new HashMap() ;

    public ToppingMenu() {

        this.toppingMenu = new HashMap<>() ;
    }

    public void addTopping(String key, Double value) {
        toppingMenu.put(key,value) ;
    }

    public void removeTopping(String toppingToRemove) {
        toppingMenu.remove(toppingToRemove);


    }

    public double getPrice(String topping) {

        double price = toppingMenu.get(topping) ;

        return price ;
    }

    public void changePrice(String topping, double newPrice) {

        toppingMenu.replace(topping, newPrice) ;
    }

    public int menuSize() {
        return toppingMenu.size() ;
    }

    public boolean containsTopping(String topping) {
        return toppingMenu.containsKey(topping) ;
    }

    public boolean isEmpty() {
        return toppingMenu.isEmpty() ;
    }

    @Override
    public String toString() {
        return toppingMenu.toString();
    }

    
}