// import java.io.IOException;
// import java.io.*;
// import java.io.File;
import java.util.ArrayList;

// import javafx.stage.FileChooser;

import javafx.application.Application;
// import javafx.collections.ObservableList;
// import javafx.scene.Node;
// import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.ScrollPane.ScrollBarPolicy;
// import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField; 
// import javafx.scene.layout.GridPane;
// import javafx.geometry.Insets;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
// import javafx.geometry.Insets;
import javafx.geometry.Pos; //For alignment
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.image.ImageView;

public class AppLayoutFV extends Application{

    // Global Variables
    Scene sceneOne, sceneTwo, sceneThree, sceneFour, sceneFive, sceneSix, sceneSeven;
    Stage window;
    Color babyBlue = Color.web("#C9DAF8");
    ImageView bee, bee2;

    //Initialize objects -> Trends/LayoutFeatures/Finance
    Trends trends = new Trends();
    public LayoutFeatures features = new LayoutFeatures(); 
    public Finance finance = new Finance();

      //Initialize arrays
        public String[] arr = new String[12];
        public ArrayList<String> incomeAntCatArr = new ArrayList<String>();
        public ArrayList<String> incomeAccCatArr = new ArrayList<String>();
        public ArrayList<String> incomeAntAmtArr = new ArrayList<String>();
        public ArrayList<String> incomeAccAmtArr = new ArrayList<String>();
        public ArrayList<String> expenseAntCatArr = new ArrayList<String>();
        public ArrayList<String> expenseAccCatArr = new ArrayList<String>();
        public ArrayList<String> expenseAntAmtArr = new ArrayList<String>();
        public ArrayList<String> expenseAccAmtArr = new ArrayList<String>();
    
    public AppLayoutFV(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        // set global variable
        // UH SINCE THIS IS GLOBAL ATTEMPT USING "WINDOW" AS THE STAGE INSTEAD OF RUNNING STAGE THROUGH PARAMS?
        window = primaryStage;

        ComboBox cBMonths = features.comboBoxMonths();
        
        // comboboxs for scene three and four (there's two of both inc and exp so that javafx wont think theres duplicate children)
        ComboBox cBIncThree = comboBoxIncome();
        ComboBox cBIncFour = comboBoxIncome();
        ComboBox cBExpThree = features.comboBoxExpense();
        ComboBox cBExpFour = features.comboBoxExpense();

        // setting the scene (initializing them)
        sceneOne = showSceneOne(window); // main Menu
        sceneTwo = showSceneTwo(window, cBMonths); //  new budget      
        sceneThree = showSceneThreeFour(window, "Anticipated", cBIncThree, cBExpThree, cBMonths); // plan
        sceneFour = showSceneThreeFour(window, "Actual", cBIncFour, cBExpFour, cBMonths); // transactions

        // set the first scene
        window.setScene(sceneOne);
        window.show();
        
    }
    //-------------------- SCENE ONE BELOW --------------------//
    public Scene showSceneOne (Stage scene){

        // initialize a scene to return
        Scene one;

        // initialize the warning text if the user doesn't enter a csv file it'll change in the gui
        Text warningT = new Text("");
        
        //Title, bees and instructions
        Label welcomeL = features.setFont("MAIN MENU", 25);
        welcomeL.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        
        bee = features.image();
        bee2 = features.image();
        
        Label introL = features.setFont("Click on one of the buttons below to visit a page", 12);
        
        //Buttons and formatting
        Button newBudgetB = goToSceneTwo(window, "NEW BUDGET"); //scene 2
        newBudgetB.setPrefWidth(150);
        
        Button transactionB = goToSceneFour(window, "TRANSACTION"); //scene 4
        transactionB.setPrefWidth(150);
        
        Button summaryB = features.yellowButton("SUMMARY"); //scene 5
        summaryB.setPrefWidth(150);
        
        Button trendsB = features.yellowButton("TRENDS"); //scene 7
        trendsB.setPrefWidth(150);
        
        Button planB = goToSceneThree(window, "PLAN");        
        planB.setPrefWidth(150);
        
        Button importB = features.yellowButton("IMPORT");
        importB.setPrefWidth(150);

        // set button actions
        importB.setOnAction(action ->{
            //file directory code here
            try {
               // if the user does not input .csv, the warning text will be shown
               warningT.setText(
                     finance.checkInputtedFile(scene));
            } catch (Exception error) {
               warningT.setText("Action terminated.");
            }
         
        });   
        

        // formatting using hbox, vbox and borderpane

        // title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, welcomeL, bee2);
        titleHB.setAlignment(Pos.CENTER);
        
        // left side buttons
        VBox left = new VBox(20);
        left.getChildren().addAll(newBudgetB, planB, summaryB);

        // right side buttons
        VBox right = new VBox(20);
        right.getChildren().addAll(importB, transactionB, trendsB);
        
        // hbox to combine left side and right side buttons
        HBox mainMenu = new HBox(20);
        mainMenu.getChildren().addAll(left, right);
        mainMenu.setAlignment(Pos.CENTER);
        
        // main screen to combine title, intro, menu, and warning text
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(titleHB, introL, mainMenu, warningT);
        mainScreen.setAlignment(Pos.CENTER);
        
        // calling the borderpane method
        BorderPane bPane = features.showBorder(mainScreen);        
        // add all the components to the scene
        one = new Scene(bPane, 1000, 500);
        
        return one;
    }    

    //-------------------- SCENE TWO BELOW --------------------//
    public Scene showSceneTwo (Stage scene, ComboBox cBMonths){
        Scene two;  // initialize a scene to return

        //Title, bees, instructions label and formatting
        Label newBudgetL = features.setFont("NEW BUDGET", 25);

        bee = features.image();
        bee2 = features.image();
        
        Label instructionsL = features.setFont("Type in your desired category and press add to include it. If you would like to delete one, type in your category and press the delete button.", 12); 
        
        //Asks the user which month they are budgeting for and provide a drop down menu 
        Label monthL = features.setFont("Month:", 12);
        cBMonths = features.comboBoxMonths();
        
        //HBox gathers the month input and income categories input as they are in the same row
        HBox monthRow = new HBox(20);
        monthRow.getChildren().addAll(monthL, cBMonths);
        monthRow.setAlignment(Pos.CENTER);
        
        //Asks the user to type in their category for income and expenses
        Label incCatL = features.setFont("Type in your category for income:", 12);
        TextField incCatTF = new TextField();
        
        Label expCatL = features.setFont("Type in your category for expense:", 12);
        TextField expCatTF = new TextField();
        
        

        //Labels for displaying the user's categories
        Label catL = features.setFont("CATEGORIES", 12);
        Label incomeL = features.setFont("INCOME", 12);
        Label expensesL = features.setFont ("EXPENSES", 12);
        
        Text incomeCatT = new Text("*User input*"); //This will depend on the categories (modify later)
        Text expensesCatT = new Text("*User input*");

        //User selects either the add or delete button for income and expenses
        Button addIncCatB = features.yellowButton("ADD");
        addIncCatB.setOnAction(action ->{
            showCategory(true, trends.incomeCat, incCatTF, true, incomeCatT, expensesCatT);                        
        });

        Button deleteIncCatB = features.yellowButton("DELETE");
        deleteIncCatB.setOnAction(action ->{
            showCategory(false, trends.incomeCat, incCatTF, true, incomeCatT, expensesCatT);
        });

        Button addExpCatB = features.yellowButton("ADD");
        addExpCatB.setOnAction(action ->{
            showCategory(true, trends.expenseCat, expCatTF, false, incomeCatT, expensesCatT);                        
        });

        Button deleteExpCatB = features.yellowButton("DELETE");
        deleteExpCatB.setOnAction(action ->{
            showCategory(false, trends.expenseCat, expCatTF, false, incomeCatT, expensesCatT);
        });           

        Button confirm = features.yellowButton("CONFIRM");
        confirm.setOnAction(action->{
            trends.income2D = new String[trends.incomeCat.size()+1][4];            
            trends.populateCat(trends.incomeCat, trends.income2D);            
        });

                       
        //HBox
        HBox incCatRow = new HBox(20);
        incCatRow.getChildren().addAll(incCatL, incCatTF, addIncCatB, deleteIncCatB);
        incCatRow.setAlignment(Pos.CENTER);
        
        HBox expCatRow = new HBox(20);
        expCatRow.getChildren().addAll(expCatL, expCatTF, addExpCatB, deleteExpCatB);
        expCatRow.setAlignment(Pos.CENTER);        
        
        //Gathers the title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, newBudgetL, bee2);
        titleHB.setAlignment(Pos.CENTER);
        
        //Displays the user's categories
        VBox incomeCatLayout = new VBox(20);
        incomeCatLayout.getChildren().addAll(incomeL, incomeCatT);
        
        VBox expenseCatLayout = new VBox(20);
        expenseCatLayout.getChildren().addAll(expensesL, expensesCatT);
        
        //Gathers the two vBoxes above so they are side by side 
        HBox displayCat = new HBox(200);
        displayCat.getChildren().addAll(incomeCatLayout, expenseCatLayout);
        displayCat.setAlignment(Pos.CENTER);
        displayCat.setBackground(new Background(new BackgroundFill(babyBlue, CornerRadii.EMPTY, Insets.EMPTY)));
                
        //NOTE: Buttons need action, move formatting to its own button methods
        Button mainMenuB = goToSceneOne(window, "MAIN MENU");        
        
        Button nextPageB = goToSceneThree(window, "NEXT PAGE");        
        
        //HBox to gather main menu button and next page button
        HBox sceneButtons = new HBox(20);
        sceneButtons.getChildren().addAll(confirm, mainMenuB, nextPageB);
        sceneButtons.setAlignment(Pos.CENTER_RIGHT);
        
        //Making the overall screen
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(titleHB, instructionsL, monthRow, incCatRow, expCatRow, catL, displayCat, sceneButtons);
        mainScreen.setAlignment(Pos.CENTER);
        
        // call border mthd
        BorderPane bPaneTwo = features.showBorder(mainScreen);
        // putting everything into the scene
        two = new Scene(bPaneTwo, 1000, 500);
        
        return two;
    }
    public ComboBox comboBoxIncome(){        
            
        for(int i = 0; i < trends.income2D.length; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(trends.income2D[i][j] + ", ");
                
            } 
            System.out.println();
        }
        String temp = trends.income2D[1][0];
        ComboBox incomeCat = new ComboBox();        
        // if(income2D[1][0]  null){
            incomeCat.getItems().addAll(
                temp 
            
            // "Savings", "Paycheck", "Bonus",
                // "Interest", "Allowance", "Other"
               );
        
        // else{
            System.out.println("entering their categories");

            // for(int i = 1; i < income2D.length; i++){                
                    // incomeCat.getItems().addAll(
                    //     income2D[i][0]
                    // );
                
            // }
            
        // }
        
        incomeCat.setPromptText("Select Category");
        incomeCat.setEditable(false);
        
        return incomeCat;
    }
    //-------------------- SCENE THREE/FOUR BELOW --------------------//
    public Scene showSceneThreeFour(Stage stage, String whichType, ComboBox cBInc, ComboBox cBExp, ComboBox cBMonths){

        Scene threeFour;  // initialize a scene to return

        //initialize itles, bees, buttons, labels
        Label titleL = features.setFont("Budgeting App", 25);
        
        bee = features.image();
        bee2 = features.image();

        Label typeL = features.setFont(whichType, 12); // anticipated or actual
        Label incL = features.setFont("INCOME", 12);
        Label expL = features.setFont("EXPENSE", 12);
        
        Label catTL = features.catLabel();
        Label catBL = features.catLabel();
        Label amntTL = features.amntLabel();
        Label amntBL = features.amntLabel();

        TextField amntTFT = features.amntT();   // user inputs inc amnt
        TextField amntTFB = features.amntT();   // user inputs exp amnt

        // fillers to show their inputed amnt
        Text showIncCat = new Text();
        Text showIncAmt = new Text();
        Text showExpCat = new Text();
        Text showExpAmt = new Text();

        Button addTB = features.yellowButton("ADD"); // TB (top half of the scene) -> income
        Button addBB = features.yellowButton("ADD"); // BB (bottom half of the scene) -> expense
        Button delTB = features.yellowButton("DELETE"); // TB (top half of the scene) -> income
        Button delBB = features.yellowButton("DELETE"); // BB (bottom half of the scene) -> expense
        
        // button actions
        addTB.setOnAction(action -> {
            for(int i = 0; i < trends.income2D.length; i++){
                for(int j = 0; j < 2; j++){
                    System.out.print(trends.income2D[i][j] + ", ");
                    
                } 
                System.out.println();
            }
            System.out.println("reading array");
            for(int i = 0; i < trends.income2D.length; i++){
                for(int j = 0; j < 2; j++){
                    System.out.print(trends.income2D[i][j] + ", ");
                    
                } 
                System.out.println();
            }
            showUserInput(whichType, true , true, cBInc, amntTFT, showIncCat, showIncAmt, showExpCat, showExpAmt, incomeAntCatArr, incomeAntAmtArr, incomeAccCatArr, incomeAccAmtArr);            
        });
        addBB.setOnAction(action -> {
            showUserInput(whichType,true, false, cBExp, amntTFB, showIncCat, showIncAmt, showExpCat, showExpAmt, expenseAntCatArr, expenseAntAmtArr, expenseAccCatArr, expenseAccAmtArr);
        });
        delTB.setOnAction(action -> {
            showUserInput(whichType, false, true, cBInc, amntTFT, showIncCat, showIncAmt, showExpCat, showExpAmt, incomeAntCatArr, incomeAntAmtArr, incomeAccCatArr, incomeAccAmtArr);
        });
        delBB.setOnAction(action -> {
            showUserInput(whichType, false, false, cBExp, amntTFB, showIncCat, showIncAmt, showExpCat, showExpAmt, expenseAntCatArr, expenseAntAmtArr, expenseAccCatArr, expenseAccAmtArr);
        });

        // FORMATTING
        HBox lastRow = new HBox(10);
        // conds for the btn of scene 3 and scene 4 
        lastRow.setAlignment(Pos.BOTTOM_RIGHT);
        if (whichType.equalsIgnoreCase("Anticipated")){
            Button nextB = goToSceneFour(stage, "NEXT");
            Button mainMenuB = goToSceneOne(stage, "MAIN MENU");
            lastRow.getChildren().addAll(mainMenuB, nextB);
        }
        else{
            Button nextB = goToSceneFour(stage, "NEXT");
            Button backB = goToSceneThree(stage, "BACK");
            Button mainMenuB = goToSceneOne(stage, "MAIN MENU");
            lastRow.getChildren().addAll(mainMenuB, backB, nextB);
        }

        // stack panes to show the user inputs on top of the rectangles
        StackPane stackPaneT = features.showSPane(showIncCat, showIncAmt);
        StackPane stackPaneB = features.showSPane(showExpCat, showExpAmt);

        // plan vs transactions and income lbls
        VBox firstCol = new VBox(10);
        firstCol.getChildren().addAll(typeL, incL);

        HBox secondRow = new HBox(50);
        secondRow.getChildren().addAll(firstCol);

        HBox thirdRow = new HBox(50);
        thirdRow.getChildren().addAll(catTL, cBInc, amntTL, amntTFT, addTB, delTB);
        thirdRow.setAlignment(Pos.CENTER);

        HBox fourthRow = new HBox(50);
        fourthRow.getChildren().addAll(expL);

        HBox fifthRow = new HBox(50);
        fifthRow.getChildren().addAll(catBL, cBExp, amntBL, amntTFB, addBB, delBB);
        fifthRow.setAlignment(Pos.CENTER);

        //Gathers the title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, titleL, bee2);
        titleHB.setAlignment(Pos.CENTER);

        // put it all together in the vbox
        VBox mainScreen = new VBox(10);
        mainScreen.getChildren().addAll(titleHB, cBMonths, secondRow, thirdRow, stackPaneT, fourthRow, fifthRow, stackPaneB, lastRow);
        mainScreen.setAlignment(Pos.TOP_CENTER);

        // add a border
        BorderPane bPane = features.showBorder(mainScreen);

        // add a scroll wheel
        ScrollPane scroll = features.showScrollPane(bPane);

        // put the components into the scene
        threeFour = new Scene(scroll, 1000, 500);

        return threeFour; 
    }

    // Button Methods
    public Button goToSceneOne(Stage stage, String sceneName){
        Button scene1B = features.yellowButton(sceneName);
        scene1B.setOnAction(action -> {
                stage.setScene(sceneOne);
        });
        return scene1B;
    }
    public Button goToSceneTwo(Stage stage, String sceneName){
        Button scene2B = features.yellowButton(sceneName);
        scene2B.setOnAction(action -> {
                stage.setScene(sceneTwo);
        });
        return scene2B;
    }
    public Button goToSceneThree(Stage stage, String sceneName){
        Button scene3B = features.yellowButton(sceneName);
        scene3B.setOnAction(action -> {
                stage.setScene(sceneThree);
        });
        return scene3B;
    }    
    public Button goToSceneFour(Stage stage, String sceneName){
        Button scene4B = features.yellowButton(sceneName);
        scene4B.setOnAction(action -> {
                stage.setScene(sceneFour);
        });
        return scene4B;
    }
    public Button goToSceneFive(Stage stage, String sceneName){
        Button scene5B = features.yellowButton(sceneName);
        scene5B.setOnAction(action -> {
                stage.setScene(sceneFive);
        });
        return scene5B;
    }

    public void showCategory(boolean add, ArrayList<String> categoryArrList, TextField catTF, boolean income, Text incomeCatT, Text expensesCatT){
        String stringCat;
        String printCat = "";
        String temp = catTF.getText();
        if(add == true){
            categoryArrList.add(catTF.getText());
        }
        else{
            if(categoryArrList.contains(catTF.getText())){
                System.out.println("going thru delete");
                categoryArrList.remove(catTF.getText());
            }    
        }
        
        if(categoryArrList.size() == 0){
            if(income == true){
                System.out.println("reset");
                incomeCatT.setText("Categories : ");
            }
            else{
                expensesCatT.setText("Categories : ");
            }
        }

        for(int i = 0; i < categoryArrList.size(); i ++){
            stringCat = categoryArrList.get(i) + " | ";      
            System.out.println("String cat is: " + stringCat)      ;
            printCat += stringCat;            
            System.out.println(printCat);
            if(income == true){
                incomeCatT.setText("Categories : " + printCat);
            }
            else {
                expensesCatT.setText("Categories : " + printCat);
            }
        }
        System.out.println("arraylist is: " + categoryArrList);
    }

    public void showUserInput(String whichType, boolean add, boolean income, ComboBox comboBox, TextField amntTF, Text catInc, Text amtInc, Text catExp, Text amtExp, ArrayList<String> AntCatArr, ArrayList<String> AntAmtArr, ArrayList<String> AccCatArr, ArrayList<String> AccAmtArr){
        // initialize strings
        String temp = (String) comboBox.getValue();
        String tempText = amntTF.getText();
        String stringCat, stringAmt;
        String printCat = "";
        String printAmt = "";
        
        // conds if it is the add btn or del btn
        if (add == true){
            // add the contents to the arr
            if(whichType.equalsIgnoreCase("ANTICIPATED")){
                AntCatArr.add((String) comboBox.getValue());    //JANE can i jus put temp?
                AntAmtArr.add(amntTF.getText());                // and like tempText here?
            }
            else{
                AccCatArr.add((String) comboBox.getValue());
                AccAmtArr.add(amntTF.getText());
            }
        }
        else{
            if(whichType.equalsIgnoreCase("ANTICIPATED")){
                // for(int i = 0; i < AntCatArr.size(); i++){
                    if( AntCatArr.contains(temp) && AntAmtArr.contains(tempText)){
                        AntCatArr.remove(temp);
                        AntAmtArr.remove(tempText);     
                    }
                // }
                System.out.println(AntCatArr + " : " + AntAmtArr);      // for testing DELETE LTR
            }
            else{
                // for(int i = 0; i < AccCatArr.size(); i++){
                    
                    if( AccCatArr.contains(temp) && AccAmtArr.contains(tempText)){
                        AccCatArr.remove(temp);
                        AccAmtArr.remove(tempText);     
                    }
                // }
                System.out.println(AccCatArr + " : " + AccAmtArr);      // for testing DELETE LTR
            }
        }

        // conds if scene 3 or scene 4
        if (whichType.equalsIgnoreCase("ANTICIPATED")){
            for(int i = 0; i < AntCatArr.size(); i ++){
                // set string as the array element
                stringCat = AntCatArr.get(i) + " | ";
                stringAmt = AntAmtArr.get(i) + " | ";
                // concanate
                printCat += stringCat;
                printAmt += stringAmt;
            }
            System.out.println(AntCatArr + " : " + AntAmtArr);  // testing DELETE LTR
        }
        else{
            System.out.println(AccCatArr + " : " + AccAmtArr);  //testng DELETE LTR
            for(int i = 0; i < AccCatArr.size(); i ++){
                stringCat = AccCatArr.get(i) + " | ";   //set string as arr element
                stringAmt = AccAmtArr.get(i) + " | ";
                printCat += stringCat;      // concanate
                printAmt += stringAmt;
            }
        }
        
        // if the user deletes all inputs set it to default labels 
        if(AntCatArr.size() == 0 || AccCatArr.size() == 0){
            if(income == true){
                catInc.setText("Categories : ");
                amtInc.setText("Amount : ");
                System.out.println("inc empty");        //testng DELETE LTR
            }
            else{
                catExp.setText("Categories : ");
                amtExp.setText("Amount : ");   
                System.out.println("exp empty");   //testng DELETE LTR
            }
        }

        // conds if it is income or exp
        // set text to the user inputs
        if(income == true){
            catInc.setText("Categories : " + printCat);
            amtInc.setText("Amount : " + printAmt);
            System.out.println("inc cat : " + printCat + " inc amt : " + printAmt);     //testng DELETE LTR

        }
        else {
            catExp.setText("Categories : " + printCat);
            amtExp.setText("Amount : " + printAmt);
            System.out.println("exp cat : " + printCat + " exp amt : " + printAmt);     //testng DELETE LTR
        }
        System.out.println("Button pressed"); // testing delete later
    }
    

}