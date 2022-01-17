// import java.io.IOException;
// import java.io.*;
import java.io.File;
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

        // Initializing buttons and labels
        Label welcomeL = new Label("WELCOME");
        Label questionL = new Label("Would you like to start a new budget or import a file?");
        Button importB = new Button("IMPORT");
        Text warningT = new Text("");

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
        Button newBudgetB = newBudgetButton();
        
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(welcomeL, questionL, importB, newBudgetB, warningT);
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

        Label typeL = new Label(whichType);
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
        
        Button addTB = features.addButton(cBInc, amntTFT, cat, amnt);
        Button addBB = features.addButton(cBExp, amntTFB, cat, amnt);
        Button delTB = features.delButton(cBInc, amntTFT);
        Button delBB = features.delButton(cBExp, amntTFB);

        Button nextB = features.nextButton(stage);
        Button backB = features.backButton(stage);
        

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
}
