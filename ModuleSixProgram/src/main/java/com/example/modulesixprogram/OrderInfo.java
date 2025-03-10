package com.example.modulesixprogram;

//Class to hold values of the order
public class OrderInfo {

    private String customerName ;

    private int orderNumber ;

    private double price = 0.0;

    //Creates an object of the order
    public OrderInfo(String customerName, int orderNumber, double price) {

        this.customerName = customerName ;

        this.orderNumber = orderNumber ;

        this.price = price ;


    }

    //Returns the customer name of the object
    public String getCustomerName() {
        return customerName ;
    }

    //returns order number of the object
    public int getorderNumber() {

        return orderNumber;
    }

    //returns the price of the object
    public double getOrderPrice() {

        return price ;
    }

    //Allows the user to change the name for the object
    public void setCustomerName(String name) {

        customerName = name;
    }

    //Changes the order number of the order
    public void setOrderNumber(int number) {

        orderNumber = number;
    }

    //Changes the price of the object
    public void setOrderPrice(double orderPrice) {

        price = orderPrice;
    }

    //Adds a double value to the order
    public double addToPrice(double newItemPrice) {

        price += newItemPrice ;

        return price ;
    }

    //Provides a string equivalent of the order
    @Override
    public String toString() {

        return "Customer: " + customerName + " \nOrder Number: " + orderNumber + " \nPrice = " + price ;
    }


    
}