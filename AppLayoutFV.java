// import java.io.IOException;
// import java.io.*;
import java.io.File;
import java.util.ArrayList;

import javafx.stage.FileChooser;

import javafx.application.Application;
// import javafx.collections.ObservableList;
// import javafx.scene.Node;
// import javafx.scene.Parent;
import javafx.scene.Scene;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
import javafx.scene.control.Label;
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
// import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
// import javafx.geometry.Insets;
import javafx.geometry.Pos; //For alignment
import javafx.geometry.Insets;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class AppLayoutFV extends Application{

    // Global Variables
    Scene sceneOne, sceneTwo, sceneThree, sceneFour, sceneFive, sceneSix, sceneSeven;
    Stage window;
    Color babyBlue = Color.web("#C9DAF8");
    ComboBox cBMonths, cBIncFour, cBIncFive, cBExpFour, cBExpFive;
    // StackPane stackPane = new StackPane();
    String emptyCat = "";
    String emptyAmnt = "";
    int y = 150;
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
        
        //Initialize Trends
        Trends trends = new Trends();

    
    public AppLayoutFV(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        

        window = primaryStage;
        cBIncFour = features.comboBoxIncome();
        cBIncFive = features.comboBoxIncome();
        cBExpFour = features.comboBoxExpense(); 
        cBExpFive = features.comboBoxExpense();

        sceneOne = showSceneOne(window);
        sceneTwo = showSceneTwo(window);
        sceneThree = showSceneThree(window, cBMonths);
        sceneFour = showSceneFourFive(window, "Anticipated", cBIncFour, cBExpFour);
        sceneFive = showSceneFourFive(window, "Actual", cBIncFive, cBExpFive);
        // sceneFive = baseScene(window, "Anticipated", "Expense", cBExpense);
        // sceneSix = baseScene(window, "Actual", "Income", cBIncome);
        // sceneSeven = baseScene(window, "Actual", "Expense", cBExpense);

        window.setScene(sceneOne);
        window.show();
        
    }
    public Scene showSceneOne (Stage scene){
        Scene one;

        Text warningT = new Text("");
        // Initializing buttons and labels
        Label welcomeL = new Label("MAIN MENU");
        welcomeL.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        Label introL = new Label("Click on one of the buttons below to visit the page");
        introL.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        Button summaryB = new Button("SUMMARY");
        summaryB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        Button trendsB = new Button("TRENDS");
        trendsB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        Button transactionB = new Button("TRANSACTIONS");
        transactionB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        Button planB = new Button("PLAN");
        planB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        Button newBudgetB = newBudgetButton();
        newBudgetB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        Button importB = new Button("IMPORT");
        importB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
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

        VBox left = new VBox(20);
        left.getChildren().addAll(welcomeL, newBudgetB, transactionB, summaryB);

        VBox right = new VBox(20);
        right.getChildren().addAll(planB, trendsB, importB);

        HBox mainScreen = new HBox(20);        
        mainScreen.getChildren().addAll(left, right);
        mainScreen.setAlignment(Pos.CENTER);
        
        one = new Scene(mainScreen, 1000, 500);
        
        return one;
    }    
    public Button newBudgetButton(){
        Button newBudget = new Button("NEW BUDGET");
        newBudget.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        newBudget.setOnAction(action ->{
            window.setScene(sceneThree); //WE NEED TO MAKE SCENE 3
        });
        return newBudget;
    }
    public Scene showSceneTwo (Stage scene){
        Scene two;

        // Initializing buttons and labels
        Label welcomeL = new Label("WELCOME");
        Label questionL = new Label("SCENE 2");
        Button importB = new Button("IMPORT");
        
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(welcomeL, questionL, importB);
        mainScreen.setAlignment(Pos.CENTER);
        
        two = new Scene(mainScreen, 1000, 500);
        
        return two;
    }   
    
    public Scene showSceneThree (Stage scene, ComboBox cBMonths){
        Scene three;

        //Title and instructions for scene 3
        Label newBudgetL = new Label("NEW BUDGET");
        Label instructionsL = new Label("Please answer the questions. Click the income or outcome button for whichever you want your category to be under."); 
        Label instructions2L = new Label("Type in your desired category then press add to include it. If you would like to delete one, type in your category and press the delete button");
        Label instructions3L = new Label("Please ensure that the number of categories entered is the same as your answer to the questions.");
        
        //VBox gathers the title and instructions together
        VBox instructions = new VBox(5);
        instructions.getChildren().addAll(newBudgetL, instructionsL, instructions2L, instructions3L);
        instructions.setAlignment(Pos.CENTER);
        
        //Asks the user which month they are budgeting for and provide a drop down menu 
        Label monthL = new Label("Month:");
        cBMonths = features.comboBoxMonths();
        
        //Asks user how many categories they will have for income
        Label howManyIncomeCatL = new Label("How many categories for income?");
        TextField incomeCatTF = new TextField();
        
        //HBox gathers the month input and income categories input as they are in the same row
        HBox monthRow = new HBox(20);
        monthRow.getChildren().addAll(monthL, cBMonths, howManyIncomeCatL, incomeCatTF);
        monthRow.setAlignment(Pos.CENTER);
        
        //User selects income or expenses button
        Button incomeB = new Button("INCOME");
        Button expensesB = new Button("EXPENSES");
        
        //Asks user how many categories they will have for expenses
        Label howManyExpensesCatL = new Label("How many categories for expenses?");
        TextField expensesCatTF = new TextField();
        
        //HBox gathers the income button, expenses button, and expenses categories input as they are in the same row
        HBox incExpRow = new HBox(20);
        incExpRow.getChildren().addAll(incomeB, expensesB, howManyExpensesCatL, expensesCatTF);
        incExpRow.setAlignment(Pos.CENTER);
        
        //Asks the user to type in their category
        Label typeCatL = new Label("Please type in your desired category:");
        TextField catTF = new TextField();
        
        //User selects either the add or delete button
        Button addB = new Button("ADD");
        Button deleteB = new Button("DELETE");
                       
        //HBox
        HBox typeCatRow = new HBox(20);
        typeCatRow.getChildren().addAll(typeCatL, catTF, addB, deleteB);
        typeCatRow.setAlignment(Pos.CENTER);
        
        //Labels for displaying the user's categories
        Label catL = new Label("CATEGORIES");
        Label incomeL = new Label("INCOME");
        Label expensesL = new Label ("EXPENSES");
        
        Label incomeCatL = new Label("*User input*"); //This will depend on the categories (modify later)
        Label expensesCatL = new Label("*User input*");
        
        //Displays the user's categories
        VBox incomeCat = new VBox(20);
        incomeCat.getChildren().addAll(incomeL, incomeCatL);
        
        VBox expenseCat = new VBox(20);
        expenseCat.getChildren().addAll(expensesL, expensesCatL);
        
        //Gathers the two vBoxes above so they are side by side 
        HBox displayCat = new HBox(150);
        displayCat.getChildren().addAll(incomeCat, expenseCat);
        displayCat.setAlignment(Pos.CENTER);
        displayCat.setBackground(new Background(new BackgroundFill(babyBlue, CornerRadii.EMPTY, Insets.EMPTY)));
                
        //NOTE: Buttons need action, move formatting to its own button methods
        Button mainMenuB = new Button("MAIN MENU");
        mainMenuB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        
        Button nextPageB = new Button("NEXT PAGE");
        nextPageB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        
        //HBox to gather main menu button and next page button
        HBox sceneButtons = new HBox();
        sceneButtons.getChildren().addAll(mainMenuB, nextPageB);
        sceneButtons.setAlignment(Pos.CENTER_RIGHT);
        
        //Making the overall screen
        VBox centerScreen = new VBox(20);        
        centerScreen.getChildren().addAll(instructions, monthRow, incExpRow, typeCatRow, catL, displayCat, sceneButtons);
        centerScreen.setAlignment(Pos.CENTER);
        
        //Fillers
        Label fillerL = new Label();
        Label filler2L = new Label();
        
        HBox mainScreen = new HBox(20);
        mainScreen.getChildren().addAll(fillerL, centerScreen, filler2L);
        mainScreen.setAlignment(Pos.CENTER);
        
        three = new Scene(mainScreen, 1000, 500);
        
        return three;
    }
    
    public Scene showSceneFourFive(Stage stage, String whichType, ComboBox cBInc, ComboBox cBExp){

        Scene fourFive;

        // initializing buttons, labels, comboBoxes
        Label titleL = new Label("Budgeting App");
        cBMonths = features.comboBoxMonths();

        Label typeL = new Label(whichType); // anticipated or actual
        Label incL = new Label("INCOME");
        Label expL = new Label("EXPENSE");
        
        Label catTL = features.catLabel();
        Label catBL = features.catLabel();
        Label amntTL = features.amntLabel();
        Label amntBL = features.amntLabel();
        
        Label hSpaceOne = features.spacing();
        Label hSpaceTwo = features.spacing();
        Label vSpace = features.spacing();
        Label rSpace = features.spacing();

        TextField amntTFT = features.amntT();
        TextField amntTFB = features.amntT();
        Text catAmnt = new Text("Category & Amount");
        Text cat = new Text();
        Text amnt = new Text();
        
        // NOTE FOR DAIPHY : HI ME YOU CAN PROLLY METHODIZE THIS TOO LOL U DUMB DUMB!! YOU CAN PROLLY ALSO PUT IT IN AS LAYOUT FEATURE BUT THATS A 01/18/2022 PROBLEM LOL
        // UNTIL 280 U CAN PROLLY ALSO METHODIZE THE DEL STUFF SINCE IT'S SAME CODE DIF VARIABLES !!

        Button addTB = features.addButton(cBInc, amntTFT, cat, amnt); //income
        addTB.setOnAction(action -> {
            if(whichType.equalsIgnoreCase("ANTICIPATED")){
                incomeAntCatArr.add((String) cBInc.getValue());
                incomeAntAmtArr.add(amntTFT.getText());
                System.out.println(incomeAntCatArr + " : " + incomeAntAmtArr);  // testing
            }
            else{
                incomeAccCatArr.add((String) cBInc.getValue());
                incomeAccAmtArr.add(amntTFT.getText());
                System.out.println(incomeAccCatArr + " : " + incomeAccAmtArr);  //testng
            }
            System.out.println("Button pressed");
            
        });

        Button addBB = features.addButton(cBExp, amntTFB, cat, amnt); // expense
        addBB.setOnAction(action -> {
            if(whichType.equalsIgnoreCase("ANTICIPATED")){
                expenseAntCatArr.add((String) cBExp.getValue());
                expenseAntAmtArr.add(amntTFB.getText());
                System.out.println(expenseAntCatArr + " : " + expenseAntAmtArr);      //testing
            }
            else{
                expenseAccCatArr.add((String) cBExp.getValue());
                expenseAccAmtArr.add(amntTFB.getText());
                System.out.println(expenseAccCatArr + " : " + expenseAccAmtArr);      //testing
            }
        });
        
        // TB (top half of the scene) -> income
        Button delTB = features.delButton(cBInc, amntTFT);
        delTB.setOnAction(action -> {
            String temp = (String) cBInc.getValue();
            String tempText = amntTFT.getText();
            
            if(whichType.equalsIgnoreCase("ANTICIPATED")){
                for(int i = 0; i < incomeAntCatArr.size(); i++){
                    if( incomeAntCatArr.get(i).equals(temp) && incomeAntAmtArr.get(i).equals(tempText)){
                        incomeAntCatArr.remove(i);
                        incomeAntAmtArr.remove(i);     
                    }
                }
                System.out.println(incomeAntCatArr + " : " + incomeAntAmtArr);      // for testing

            }
            else{
                for(int i = 0; i < incomeAccCatArr.size(); i++){
                    if( incomeAccCatArr.get(i).equals(temp) && incomeAccAmtArr.get(i).equals(tempText)){
                        incomeAccCatArr.remove(i);
                        incomeAccAmtArr.remove(i);     
                    }
                }
                System.out.println(incomeAccCatArr + " : " + incomeAccAmtArr);        // for testing
            }
        });
        // BB (bottom half of the scene) -> expenses
        Button delBB = features.delButton(cBExp, amntTFB);
        delBB.setOnAction(action -> {
            String temp = (String) cBExp.getValue();
            String tempText = amntTFB.getText();
            
            if(whichType.equalsIgnoreCase("ANTICIPATED")){
                for(int i = 0; i < expenseAntCatArr.size(); i++){
                    if( expenseAntCatArr.get(i).equals(temp) && expenseAntAmtArr.get(i).equals(tempText)){
                        expenseAntCatArr.remove(i);
                        expenseAntAmtArr.remove(i);     
                    }
                }
                System.out.println(expenseAntCatArr + " : " + expenseAntAmtArr);      // for testing

            }
            else{
                for(int i = 0; i < expenseAccCatArr.size(); i++){
                    if( expenseAccCatArr.get(i).equals(temp) && expenseAccAmtArr.get(i).equals(tempText)){
                        expenseAccCatArr.remove(i);
                        expenseAccAmtArr.remove(i);     
                    }
                }
                System.out.println(expenseAccCatArr + " : " + expenseAccAmtArr);        // for testing
            }
        });


        Button nextB = goToSceneFive(stage, "NEXT");
        Button backB = goToSceneFour(stage, "BACK");
        

        // MAKE IF STATEMENTS IN THE ADD/DEL BTN THAT THE RECTANGLE Y WILL CHANGE AS MORE THINGS ARE ADDED AND DELETED

        StackPane stackPaneT = features.showSPane();
        StackPane stackPaneB = features.showSPane();

        // Rectangle rectangle = new Rectangle(100,150,900,y);
        // rectangle.setFill(babyBlue);

        // stackPane.setMargin(catAmnt, new Insets(1, 1, 1, 1));

        VBox cATotal = new VBox(10);
        cATotal.getChildren().addAll(catAmnt, cat,amnt);

        HBox catAmntBox = new HBox(10);
        catAmntBox.getChildren().addAll(rSpace, cATotal);
        catAmntBox.setAlignment(Pos.TOP_LEFT);

        // stackPane.getChildren().addAll(rectangle, catAmntBox);

        // Styling the Labels || Buttons
        titleL.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        typeL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // transL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // categoryL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // amountL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // amountT.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // addB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        // deleteB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        // nextB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        // backB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");

        // // Button Actions
        // addB.setOnAction(action -> {
        //     String catIncome = (String) cBTrans.getValue();
        //     String showCatI = catIncome + " | ";
        //     String amntIncomeString = amountT.getText();
        //     String showAmntI = amntIncomeString + " | ";
        //     Double amntIncome = Double.parseDouble(amntIncomeString);
        //     System.out.println(catIncome + " : " + amntIncome);
        //     if(cBTrans.getValue() != null && amountT.getText() != null){
        //         // y += 20;
        //         emptyCat += showCatI;
        //         emptyAmnt += showAmntI;
        //         cat.setText("Category : " + emptyCat);
        //         amnt.setText("Amount : " + emptyAmnt);
        //     }
        //     // .add() to arraylist here Jane/Rachel
        // });
        // deleteB.setOnAction(action -> {
        //     String catIncome = (String) cBIncome.getValue();
        //     String amntIncomeString = amountT.getText();
        //     Double amntIncome = Double.parseDouble(amntIncomeString);
        //     System.out.println(catIncome + " : " + amntIncome);
        //     // .remove() to arraylist here Jane/Rachel
        // });
        // nextB.setOnAction(action -> {
        //     // if (whichType.equals("Anticipated") && trans.equals("Income")){
        //                 // sceneFive = baseScene(window, "Anticipated", "Expense", cBIncome);

        //         // stage.setScene(sceneFive);
        //         // stage.show();

        //         if (whichType.equals("Anticipated") && trans.equals("Income")){
        //             stage.setScene(sceneFive);
        //             stage.show();
        //         }
        //         else if (whichType.equals("Anticipated") && trans.equals("Expense")){
        //             stage.setScene(sceneSix);
        //             stage.show();
        //         }
        //         else if (whichType.equals("Actual") && trans.equals("Income")){
        //             stage.setScene(sceneSeven);
        //             stage.show();
        //         }
            
        // });
        // backB.setOnAction(action -> {
        //     stage.setScene(sceneFour);
        //     stage.show();
        
        // });

        VBox firstCol = new VBox(10);
        firstCol.getChildren().addAll(typeL, incL);

        HBox secondRow = new HBox(50);
        secondRow.getChildren().addAll(hSpaceOne, firstCol);

        HBox thirdRow = new HBox(50);
        thirdRow.getChildren().addAll(catTL, cBInc, amntTL, amntTFT, addTB, delTB);
        thirdRow.setAlignment(Pos.CENTER);

        HBox fourthRow = new HBox(50);
        fourthRow.getChildren().addAll(hSpaceTwo, expL);

        HBox fifthRow = new HBox(50);
        fifthRow.getChildren().addAll(catBL, cBExp, amntBL, amntTFB, addBB, delBB);
        fifthRow.setAlignment(Pos.CENTER);

        HBox lastRow = new HBox(10);
        lastRow.getChildren().addAll(backB, nextB);
        lastRow.setAlignment(Pos.BOTTOM_RIGHT);

        VBox mainScreen = new VBox(10);
        mainScreen.getChildren().addAll(vSpace, titleL, cBMonths, secondRow, thirdRow, stackPaneT, fourthRow, fifthRow, stackPaneB, lastRow);
        mainScreen.setAlignment(Pos.TOP_CENTER);

        // ScrollPane scroll = showScrollPane(mainScreen);

        // VBox fullScreen = new VBox(0);
        // fullScreen.getChildren().addAll(mainScreen, scroll);
        // fullScreen.setAlignment(Pos.CENTER_RIGHT);

        fourFive = new Scene(mainScreen, 1000, 500);

        return fourFive; 
    }
    public Button goToSceneFive(Stage stage, String sceneName){
        Button scene5B = new Button(sceneName);
        scene5B.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        scene5B.setOnAction(action -> {
            // if(whichType.equals("Anticipated")){
                stage.setScene(sceneFive);
            // }
        });
        return scene5B;
    }
    public Button goToSceneFour(Stage stage, String sceneName){
        Button scene4B = new Button(sceneName);
        scene4B.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        scene4B.setOnAction(action -> {
            // if(whichType.equals("Anticipated")){
                stage.setScene(sceneFour);
            // }
        });
        return scene4B;
    }
}