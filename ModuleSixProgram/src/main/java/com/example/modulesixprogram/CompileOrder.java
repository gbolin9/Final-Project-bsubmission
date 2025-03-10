package com.example.modulesixprogram;

//Addss up an individual order item
public class CompileOrder {

    private String topping;

    private String container; 

    private String flavor; 

    private String drink ;

    private double totalPrice ;

    //Creates a singular object for an ice cream order
    public CompileOrder(String flavor, String container, String topping, String drink, double totalPrice){

        this.flavor = flavor;

        this.container = container;

        this.topping = topping ;

        this.drink = drink ;

        this.totalPrice = totalPrice ;
    }

    //returns flavor of item
    public String getFlavor() {
        return flavor ;
    }

    //returns container name for item
    public String getContainer() {
        return container ;
    }

    //returns toppings name for item
    public String getTopping() {
        return topping ;
    }

    //returns drink name for item
    public String getDrink() {
        return drink ;
    }

    //returns total price of item
    public double getTotalPrice() {

        return totalPrice ;
    }

    //Sets a new flavor of the object
    public void setFlavor(String newFlavor) {

        flavor = newFlavor ;
        
    }

    //Sets a new container of the object
    public void setContainer(String newContainer) {

        container = newContainer ;
        
    }

    //Sets the new topping for the oject
    public void setTopping(String newTopping) {

        topping = newTopping ;
        
    }

    //Sets a new drink for the object
    public void setDrink(String newDrink) {

        drink = newDrink ;
        
    }

    //Sets a new price for the object
    public void setTotalPrice(double newTotalPrice) {

        totalPrice= newTotalPrice ;
        
    }

    //Creates a string version of the total item.
    @Override
    public String toString() {

        return "Flavor: " + flavor + " \nContainer: " + container + " \nTopping(s): " + topping + "\nDrink: " + drink + "\nPrice: " + totalPrice ;

    }




    
}