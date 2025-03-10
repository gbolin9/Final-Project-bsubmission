package com.example.modulesixprogram;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;


import static com.example.modulesixprogram.FormatToppings.ConvertPriced;
import static com.example.modulesixprogram.FormatToppings.ConvertToOneString;
import static com.example.modulesixprogram.Tax.salesTax;


public class MainPage extends Application {

    private static Stage stage;

    private Scene sceneMain;

    private static Scene sceneMenu;

    private static Scene sceneOrderInfo;

    private Scene sceneThankYou;

    static TextField nameField;

    static String userName = "Gary";

    static String password = "Gary";

    static String driver = "com.mysql.cj.jdbc.Driver";

    static String connection = "jdbc:mysql://localhost:3306/finalproject";

    static Statement stm = null;

    static Connection connectionb ;

    static CheckBox chSprinkles ;

    static CheckBox  chChocSyrup ;
    static CheckBox chSaltCaramel;
    static CheckBox chCookieCrumble;

    static int orderNumber;

    static PreparedStatement preparedStatement ;

    static String insertOrderID = "insert into orderid(orderID, custName, orderPrice)" +
            "value(?,?,?)";

    static String insertOrderInfo = "insert into orderinfo(orderID, flavor, container, topping, drink, price)" +
            "value(?,?,?,?,?,?)";
    static String updateName = "update orderid set custName = ? where orderID = ?" ;

    static String updatePrice = "update orderid set orderPrice = ? where orderID = ?" ;

    public static String getPriceSTM = "SELECT SUM(price) FROM orderinfo where orderID = ?";

    static String viewOrderInfo = "SELECT 'flavor', 'container', 'topping', 'drink', 'price'" +
            "FROM orderinfo where orderID = ?";

    static String flavor;

    static String drink;

    static String containera;

    static LinkedHashMap<String,Double> topping = new LinkedHashMap<>();

    static double toppingsPrice = 0;

    static String toppingS;

    static double orderPrice = 0;

    static Flavor flavors = new Flavor();

    static Container containers = new Container();

    static Drink drinks = new Drink();

    static Toppings toppings = new Toppings();

    static RadioButton cOne;

    static TextArea receiptArea;

    static DecimalFormat df = new DecimalFormat("#.00");

    static Button nexta;

    static Button addBtn;

    static Button confirmOrderBtn;

    static Button backBtn;


    public MainPage() {
    }


    @Override
    public void start(Stage primaryStage) throws SQLException {

        dataBase();
        //If No tables are created yet use the create Table to create them!!!!!

        //Generates a random 8 digit number for the order number
        long min = 10000000L;
        long max = 99999999L;
        orderNumber = (int) ((long) (Math.random() * (max - min + 1)) + min);


        //Inserts a new row into the orderid table with the generated id number
        preparedStatement = connectionb.prepareStatement(insertOrderID);
        preparedStatement.setInt(1, orderNumber);
        preparedStatement.setString(2, null);
        preparedStatement.setFloat(3,0);

        preparedStatement.executeUpdate();

        stage = primaryStage;
        stage.setTitle("IceCreamShop");

        sceneMain = createSceneMain();
        sceneMenu = createSceneMenu();
        sceneOrderInfo = createOrderInfoScene();
        sceneThankYou = createThankYouScene();
        stage.setScene(sceneMain);
        stage.show();

    }

    private Scene createSceneMain() {

        Font fontWelcome = Font.font("Times New Roman", FontWeight.BOLD, 24);
        Font fontMessage = Font.font("Times New Roman", FontPosture.ITALIC, 14);

        BorderPane pane = new BorderPane();

        pane.setPrefSize(700, 600);

        HBox hBox = new HBox();
        VBox vBox = new VBox();
        pane.setCenter(vBox);
        pane.setBottom(hBox);

        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);

        Label welcome = new Label("Welcome to the Ice Cream Shoppe.");
        Label messageTwo = new Label("May we help you find your perfect dessert?");


        welcome.setFont(fontWelcome);
        messageTwo.setFont(fontMessage);

        Button beginOrder = new Button("Click to Begin Order");


        hBox.getChildren().add(beginOrder);
        vBox.getChildren().addAll(welcome, messageTwo);

        beginOrder.setOnAction(e -> changeScene(sceneMenu));

        sceneMain = new Scene(pane);

        return sceneMain;

    }

    private Scene createSceneMenu() {

        //Creates menu for respective items
        flavors.addItem("Vanilla", 0.5);
        flavors.addItem("Chocolate", 0.5);
        flavors.addItem("Chocolate/Vanilla Swirl", 0.75);

        containers.addItem("Regular Cone", 1.00);
        containers.addItem("Bowl", 1.10);
        containers.addItem("Waffle Cone", 2.00);

        drinks.addItem("Water", 0.0);
        drinks.addItem("Root Beer", 1.25);
        drinks.addItem("Lemonade", 1.0);

        toppings.addItem("Sprinkles", .1);
        toppings.addItem("Chocolate Syrup", .25);
        toppings.addItem("Salted Caramel", .35);
        toppings.addItem("Cookie Crumble", .4);

        BorderPane menuPane = new BorderPane();
        menuPane.setPrefSize(700, 600);


        //Creates a header for the page
        VBox headerBox = new VBox();

        menuPane.setTop(headerBox);
        headerBox.setAlignment(Pos.CENTER);

        //Creates label for the header and formats the font.
        Font fontHeader = Font.font("Times New Roman", FontWeight.BOLD, 24);
        Label headerLbl = new Label("Menu");
        headerLbl.setFont(fontHeader);

        headerBox.getChildren().add(headerLbl);

        //Creates a pane for the menu items
        GridPane radioButtonsPane = new GridPane();

        radioButtonsPane.setHgap(10);
        radioButtonsPane.setVgap(10);

        menuPane.setCenter(radioButtonsPane);

        //Creates Radio Buttons

        //Radio Buttons for the flavors
        RadioButton fOne = new RadioButton("Vanilla: $0.50");
        RadioButton fTwo = new RadioButton("Chocolate: $0.50");
        RadioButton fThree = new RadioButton("Chocolate/Vanilla Swirl: $0.75");
        ToggleGroup flavors = new ToggleGroup();
        fOne.setToggleGroup(flavors);
        fTwo.setToggleGroup(flavors);
        fThree.setToggleGroup(flavors);
        fOne.setSelected(true);


        //Creates action event for flavor radio buttons
        fOne.setOnAction(e-> {
            if (fOne.isSelected()){
                flavor = "Vanilla" ;

            }
        });

        fTwo.setOnAction(e-> {
            if (fTwo.isSelected()){
                flavor = "Chocolate" ;
            }
        });


        fThree.setOnAction(e-> {
            if (fThree.isSelected()){
                flavor = "Chocolate/Vanilla Swirl" ;
            }
        });

        //Creates radio buttons for the containers
        cOne = new RadioButton("Regular Cone: $1.00");
        RadioButton cTwo = new RadioButton("Bowl: $1.10");
        RadioButton cThree = new RadioButton("Waffle Cone: $2.00");
        ToggleGroup containers = new ToggleGroup();
        cOne.setToggleGroup(containers);
        cTwo.setToggleGroup(containers);
        cThree.setToggleGroup(containers);
        cOne.setSelected(true);

        //Creates action event for container radio buttons
        cOne.setOnAction(e-> {
            if (cOne.isSelected()){
                containera = "Regular Cone";
            }
        });

        cTwo.setOnAction(e-> {
            if (cTwo.isSelected()){
                containera = "Bowl" ;

            }
        });

        cThree.setOnAction(e-> {
            if (cThree.isSelected()){
                containera = "Waffle Cone" ;
            }
        });



        //Creates a checkbox for the toppings
        chSprinkles = new CheckBox("Sprinkles: $0.10");
        chChocSyrup = new CheckBox("Chocolate Syrup: $0.25");
        chSaltCaramel = new CheckBox("Salted Caramel: $0.35");
        chCookieCrumble = new CheckBox("Cookie Crumble: $0.40");


        //Creates radio buttons for the drinks
        RadioButton dOne = new RadioButton("Water: $0.00");
        RadioButton dTwo = new RadioButton("Lemonade: $1.00");
        RadioButton dThree = new RadioButton("Root Beer: $1.25");
        ToggleGroup drinks = new ToggleGroup();
        dOne.setToggleGroup(drinks);
        dTwo.setToggleGroup(drinks);
        dThree.setToggleGroup(drinks);
        dOne.setSelected(true);

        //Creates an action event for when a drink is selected
        dOne.setOnAction(e-> {
            if (dOne.isSelected()){
                drink = "Water" ;
            }
        });
        dTwo.setOnAction(e-> {
            if (dTwo.isSelected()){
                drink = "Lemonade" ;
            }
        });
        dThree.setOnAction(e-> {
            if (dThree.isSelected()){
                drink = "Root Beer" ;
            }
        });

        //Adds a button to add to order
        addBtn = new Button("Add to Order");


        //Places the button on the pane
        radioButtonsPane.add(new Label("Flavor"), 0, 0);
        radioButtonsPane.add(fOne, 0, 1);
        radioButtonsPane.add(fTwo, 0, 2);
        radioButtonsPane.add(fThree, 0, 3);
        radioButtonsPane.add(new Label("Container"), 1, 0);
        radioButtonsPane.add(cOne, 1, 1);
        radioButtonsPane.add(cTwo, 1, 2);
        radioButtonsPane.add(cThree, 1, 3);
        radioButtonsPane.add(new Label("Toppings"), 3, 0);
        radioButtonsPane.add(chSprinkles, 3, 1);
        radioButtonsPane.add(chChocSyrup, 3, 2);
        radioButtonsPane.add(chSaltCaramel, 3, 3);
        radioButtonsPane.add(chCookieCrumble, 3, 4);
        radioButtonsPane.add(new Label("Drinks"), 4, 0);
        radioButtonsPane.add(dOne, 4, 1);
        radioButtonsPane.add(dTwo, 4, 2);
        radioButtonsPane.add(dThree, 4, 3);
        radioButtonsPane.add(addBtn, 4, 5);


        //Creates an HBox for buttons
        GridPane nameAndButtons = new GridPane();

        nameAndButtons.setHgap(10);
        nameAndButtons.setVgap(10);

        menuPane.setBottom(nameAndButtons);

        nameAndButtons.setAlignment(Pos.CENTER);

        nexta = new Button("Next");

        Button back = new Button("Back");

        Button submitName = new Button(("SubmitName"));

        //Creates text field for name entry
        Label nameLbl = new Label("Enter Name:");
        nameField = new TextField();
        GridPane.setValignment(nameLbl, VPos.TOP);
        GridPane.setValignment(nameField, VPos.TOP);


        //Disables enter name button unless there is text written in the name field
        submitName.setDisable(true);

        nameField.textProperty().addListener(((observableValue, oldValue, newValue) ->
                submitName.setDisable((newValue.trim().isEmpty()))));



        //Creates action events for button clicks
        nexta.setOnAction(e -> {
            try {
                addPrice();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        nexta.setDisable(true);



        back.setOnAction(e -> changeScene(sceneMain));

        addBtn.setOnAction(e -> {
            try {
                addToOrder();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        addBtn.setDisable(true);

        submitName.setOnAction(e -> {
            try {
                AddName();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }) ;

        nameAndButtons.add(nameLbl, 0, 0);
        nameAndButtons.add(nameField, 1, 0);
        nameAndButtons.add(submitName, 2, 0);

        nameAndButtons.add(back, 0, 2);
        nameAndButtons.add(nexta, 1, 2);


        sceneMenu = new Scene(menuPane);

        return sceneMenu;

    }

    public static void changeScene(Scene scene) {
        stage.setScene(scene);
    }



    private Scene createOrderInfoScene() throws SQLException {
        BorderPane orderInfoPane = new BorderPane();

        orderInfoPane.setPrefSize(700, 600);

        //Creates a header for the page and formats it
        HBox headerBox = new HBox();
        orderInfoPane.setTop((headerBox));
        headerBox.setAlignment(Pos.CENTER);

        //Creates label for the header and formats the font.
        Font fontHeader = Font.font("Times New Roman", FontWeight.BOLD, 24);
        Label headerLbl = new Label("Order Info");
        headerLbl.setFont(fontHeader);

        headerBox.getChildren().add(headerLbl);

        //Creates a Pane for the order info and formats it.
        HBox orderInfoGrid = new HBox();
        orderInfoPane.setCenter(orderInfoGrid);
        orderInfoGrid.setAlignment(Pos.CENTER);


        //Creates nodes for the grid

        receiptArea = new TextArea();


        //Format TextArea
        receiptArea.setPrefColumnCount(40);
        receiptArea.setPrefRowCount(8);


        //Place nodes into the grid pane

        orderInfoGrid.getChildren().add(receiptArea);


        //Creates pane for buttons
        HBox buttons = new HBox(10);

        //Formats the HBox
        orderInfoPane.setBottom(buttons);
        buttons.setAlignment(Pos.CENTER);
        buttons.setPadding(new Insets(15, 15, 0, 15));

        //Creates buttons for pane

        backBtn = new Button("Restart Order");
        Button seeOrderBtn = new Button("Check Order");
        confirmOrderBtn = new Button("Confirm Order");

        confirmOrderBtn.setDisable(true);
        backBtn.setDisable(true);

        //Places buttons in the pane
        buttons.getChildren().addAll(backBtn, seeOrderBtn, confirmOrderBtn);

        //Creates action events for button clicks
        backBtn.setOnAction(e -> {
            try {
                clearOrder();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        confirmOrderBtn.setOnAction(e -> changeScene(sceneThankYou));

        seeOrderBtn.setOnAction(actionEvent -> {
            try {
                ViewOrderInfo();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });

        sceneOrderInfo = new Scene(orderInfoPane);
        return sceneOrderInfo;

    }

    private Scene createThankYouScene() {
        BorderPane thankYouPane = new BorderPane();
        thankYouPane.setPrefSize(700, 600);

        //Creates a Header for the page
        VBox header = new VBox();
        thankYouPane.setTop((header));
        header.setAlignment(Pos.CENTER);

        //Creates label for the header and formats the font.
        Font fontHeader = Font.font("Times New Roman", FontWeight.BOLD, 24);
        Label headerLbl = new Label("Thank You For Ordering");
        headerLbl.setFont(fontHeader);

        header.getChildren().add(headerLbl);

        //Creates an VBox for closing information
        VBox finalInfo = new VBox();
        thankYouPane.setCenter(finalInfo);
        finalInfo.setAlignment(Pos.CENTER);

        //Creates nodes for final information
        Label timeFrameLbl = new Label("Your order will be ready in: 30 - 45 minutes\n Thank you for orderings\n We appreciate your business\n We hope you enjoy your treats");


        //Places the nodes into the HBox
        finalInfo.getChildren().addAll(timeFrameLbl);


        sceneThankYou = new Scene(thankYouPane);
        return sceneThankYou;
    }

    //Connects to the database
    static void dataBase() throws SQLException {

        try {
            Class.forName(driver);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);

        }
        try {
            connectionb = DriverManager.getConnection
                    (connection, userName, password);

        } catch (SQLException e) {
            throw new RuntimeException(e);

        }


    }

    //Updates name in database in the current order
    static void AddName() throws SQLException {

        preparedStatement = connectionb.prepareStatement(updateName);

        preparedStatement.setString(1, nameField.getText());
        preparedStatement.setInt(2,orderNumber);

        preparedStatement.executeUpdate();

        addBtn.setDisable(false);

    }

    //Brings up the complete order info and activates the clear order and confirm order buttons
    static void ViewOrderInfo() throws SQLException {

        StringBuilder s= new StringBuilder(" ");

        double finalPrice ;

        double preTaxAmount;

        double taxAmount ;

        String querya = "SELECT * FROM orderid where orderid = " + orderNumber;

        Statement stma = connectionb.createStatement();

        ResultSet results2 = stma.executeQuery(querya);

        results2.next();
        s.append("Order Number: ").append(results2.getString("orderID")).append("\n");
        s.append("Order Name: ").append(results2.getString("custName")).append("\n");


        String query = "SELECT *" +
                "FROM orderinfo where orderID = " + orderNumber;

        Statement stm = connectionb.createStatement();

        ResultSet results = stm.executeQuery(query);


        while (results.next()) {
           String f = (results.getString("flavor"));
           String c = (results.getString("container"));
           String t = (results.getString("topping"));
           String d = (results.getString("drink"));
           String p = (results.getString("price"));
           s.append(f).append(" ").append(c).append(" ").append(t).append(" ").append(d).append(" ").append(p).append("\n");
           System.out.println(f);
        }
        s.append("==================================================================================================").append("\n");
        finalPrice = Double.parseDouble(results2.getString("orderPrice"));
        taxAmount = finalPrice * salesTax ;
        preTaxAmount = finalPrice - taxAmount;



        s.append("Pre-tax Amount: $").append(df.format(preTaxAmount)).append("\n");
        s.append("Tax: $").append(df.format(taxAmount)).append("\n");
        s.append("Total: $").append(df.format(finalPrice));


        confirmOrderBtn.setDisable(false);
        backBtn.setDisable(false);


        receiptArea.setText(s.toString());

    }

    //Adds total order price to to table and switches to order info scene
    static void addPrice() throws SQLException {
        double x;
        double postTaxPrice;

        preparedStatement = connectionb.prepareStatement(getPriceSTM);
        preparedStatement.setInt(1,orderNumber);
        ResultSet viewResults = preparedStatement.executeQuery();

        viewResults.next();
        x = viewResults.getDouble(1);
        postTaxPrice = Tax.calculateTax(x);

        preparedStatement = connectionb.prepareStatement(updatePrice);

        preparedStatement.setDouble(1, postTaxPrice);
        preparedStatement.setInt(2,orderNumber);

        preparedStatement.executeUpdate();

        nexta.setDisable(true);

        changeScene(sceneOrderInfo);



        }

    //adds the current selected items to the order
    static void addToOrder() throws SQLException {

        //Adds the choice of sprinkles to the order
        if (chSprinkles.isSelected()) {
            topping.put("Sprinkles",.1);
        }
        if (chChocSyrup.isSelected()) {
            topping.put("Chocolate Syrup",.25);
        }
        if (chSaltCaramel.isSelected()) {
            topping.put("Salted Caramel",.35);
            toppingsPrice += .35;
        }
        if (chCookieCrumble.isSelected()) {
            topping.put("Cookie Crumble",.4);
            toppingsPrice += .40;
        }

        if (cOne.isSelected()){
            containera = "Regular Cone";
        }



        //Convert toppings to one string and gets the totalprice of the current set of toppings
        toppingS = ConvertToOneString(topping);
        double currentToppingPrice = ConvertPriced(topping);

        //Receives the price of the selected categories, returns a price, and then adds up the price.
        double flavorPrice = flavors.getPrice(flavor);
        double containerPrice = containers.getPrice(containera);
        double drinkPrice = drinks.getPrice(drink);
        orderPrice += flavorPrice + + drinkPrice +containerPrice + currentToppingPrice;

        preparedStatement = connectionb.prepareStatement(insertOrderInfo);

        preparedStatement.setInt(1, orderNumber);
        preparedStatement.setString(2, flavor);
        preparedStatement.setString(3, containera);
        preparedStatement.setString(4, toppingS);
        preparedStatement.setString(5,drink);
        preparedStatement.setDouble(6,orderPrice);

        preparedStatement.executeUpdate();

        //Resets the toppings
        topping.clear();
        toppingsPrice = 0;
        orderPrice = 0;

        if (nameField.getText().isEmpty() == false) {
            nexta.setDisable(false);
        }
    }

    //Clears order and returns back to menu screen
    static void clearOrder() throws SQLException {

        String deleteStm = "DELETE FROM orderinfo where orderid = " + orderNumber;

        preparedStatement = connectionb.prepareStatement(deleteStm);
        preparedStatement.executeUpdate();



        receiptArea.setText(" ");
        nameField.setText("");
        backBtn.setDisable(true);
        confirmOrderBtn.setDisable(true);
        changeScene(sceneMenu);
    }


    public static void main(String[] args) {
        launch(args);
    }
}

