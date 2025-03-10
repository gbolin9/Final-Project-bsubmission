package com.example.modulesixprogram;

import java.util.HashMap ;

//Provide an abstract class for the food menus
abstract class IceCreamMenu {

    //Creates a hashmap for selection of items
    HashMap<String,Double> menu = new HashMap<>() ;

    //Creates a hashmap for menu items
    public IceCreamMenu() {

        this.menu = new HashMap<>() ;
    }

    //Adds a value to the map
    public void addItem(String key, Double value) {
        menu.put(key,value) ;
    }

    //Removes an item from the map
    public void removeItem(String itemToRemove) {
        menu.remove(itemToRemove);


    }

    //Returns the price for a specific item
    public double getPrice(String item) {

        double price = menu.get(item) ;

        return price ;
    }

    //Allows the user to change the price of an item.
    public void changePrice(String item, double newPrice) {

        menu.replace(item, newPrice) ;
    }

    //Abstract method for returning a category
    abstract String getCategory();


    //returns the size of the map
    public int menuSize() {
        return menu.size() ;
    }

    //Returns a boolean value if an item appears in the map
    public boolean containsItem(String item) {
        return menu.containsKey(item) ;
    }

    //Returns a boolean value if the map is empty
    public boolean isEmpty() {
        return menu.isEmpty() ;
    }

    //Returns a String equivalent of the map.
    @Override
    public String toString() {
        return menu.toString();
    }

    
}