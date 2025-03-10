package com.example.modulesixprogram;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import static com.example.modulesixprogram.MainPage.*;

public class CreateTables {
    static String userName = "Gary";

    static String password = "Gary";

    static String driver = "com.mysql.cj.jdbc.Driver";

    static String connection = "jdbc:mysql://localhost:3306/test2";

    static PreparedStatement preparedStatement;

    public static void main(String[] args) throws SQLException {
        dataBase();

        String createStm = "CREATE TABLE orderid(orderID INTEGER(8) not NULL,custNAME CHAR(40),orderPrice FLOAT, primary key(orderID))";

        preparedStatement = connectionb.prepareStatement(createStm);
        preparedStatement.executeUpdate();

        String createStma = "CREATE TABLE orderinfo(number INTEGER not NULL AUTO_INCREMENT,flavor CHAR(40),container CHAR(40)," +

                "topping CHAR(200),drink CHAR(40),orderID INTEGER(8) not NULL,price FLOAT(10), PRIMARY KEY (number), FOREIGN KEY (orderID)" +

                "REFERENCES orderid(orderID))";


        preparedStatement = connectionb.prepareStatement(createStma);
        preparedStatement.executeUpdate();


    }
}
