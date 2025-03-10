package com.example.modulesixprogram;

//Creates an object for a food item
public class Food {

    private String name ;
    private double price;


    //Creates a food object
    public Food(String name, double price) {
        this.name = name;
        this.price = price;
    }

    //Returns name
    public String getName() {
        return name ;
    }

    //Returns price of object
    public double getPrice() {
        return price ;
    }

    //Allows the user to change the name of an object
    public void setName(String newName) {
        this.name = newName ;
    }
    
    //Allows the user to change the price of the object
    public void setPrice(double newPrice) {
        this.price = newPrice ;
    }

    //Returns a string equivalent of the object
    @Override
    public String toString(){
    return ("The item " + this.name + " costs $ " + this.price);
    }
}