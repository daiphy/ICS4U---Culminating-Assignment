// import java.io.IOException;
// import java.io.*;
// import java.io.File;
// import java.util.Locale.Category;
// import javafx.stage.FileChooser;
// import javafx.scene.Group;
// import javafx.scene.Node;
// import javafx.scene.Parent;
// import javafx.scene.chart.Axis;
// import javafx.scene.chart.NumberAxis;
// import javafx.event.ActionEvent;
// import javafx.event.EventHandler;
// import javafx.scene.control.ScrollPane;
// import javafx.scene.control.ScrollPane.ScrollBarPolicy;
// import javafx.stage.Popup;
// import javafx.scene.layout.GridPane;
// import javafx.geometry.Insets;
// import javafx.geometry.Insets;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.*;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField; 
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
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
    Color darkBlue = Color.web("#9AB3DF");
    Color white = Color.web("#FFFFFF");
    Color yellow = Color.web("#f8f3c9");
    ImageView bee, bee2;

    //Initialize objects -> Trends/LayoutFeatures/Finance
    public Trends trends = new Trends();
    public LayoutFeatures features = new LayoutFeatures(); 
    public FinanceFV financeFV = new FinanceFV();
    public ComboBox cBMonths;
    public String month;
    public boolean clicked;
    public boolean setMonth;

    // comboboxs for scene three and four (there's two of both inc and exp so that javafx wont think theres duplicate children)
    //   ComboBox cBIncThree = features.comboBoxIncome();
    //   ComboBox cBIncFour = features.comboBoxIncome();
    //   ComboBox cBExpThree = features.comboBoxExpense();
    //   ComboBox cBExpFour = features.comboBoxExpense();

    //Initialize arrays
        // public String[] arr = new String[12];        
    
    public AppLayoutFV(){
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        // set global variable
        // UH SINCE THIS IS GLOBAL ATTEMPT USING "WINDOW" AS THE STAGE INSTEAD OF RUNNING STAGE THROUGH PARAMS?
        window = primaryStage;

        // setting the scene (initializing them)
        sceneOne = showSceneOne(window); // main Menu
        // sceneTwo = showSceneTwo(window, cBMonths); //  new budget      
        // sceneThree = showSceneThreeFour(window, "Anticipated", cBIncThree, cBExpThree, cBMonths); // plan
        // sceneFour = showSceneThreeFour(window, "Actual", cBIncFour, cBExpFour, cBMonths); // transactions
        // sceneFive = showSceneFive(window, cBMonths);
        // sceneSix = showSceneSix(window, cBMonths);
        
        // set the first scene
        window.setScene(sceneOne);
        window.show();
        
    }
    //-------------------- SCENE ONE BELOW --------------------//
    public Scene showSceneOne (Stage scene){
        Scene one; //Initialize a scene to return 

        //Initialize the warning text if the user doesn't enter a csv file it'll change in the gui
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
        
        Button summaryB = goToSceneFive(window, "SUMMARY"); //scene 5
        summaryB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        summaryB.setPrefWidth(150);
        
        Button trendsB = goToSceneSeven(window, "TRENDS"); //scene 7
        trendsB.setPrefWidth(150);
        
        Button planB = goToSceneThree(window, "PLAN");        
        planB.setPrefWidth(150);
        
        Button importB = features.yellowButton("IMPORT");
        importB.setPrefWidth(150);

        //Set button actions
        importB.setOnAction(action ->{
            //File directory code here
            try {
            //If the user does not input .csv, the warning text will be shown
            warningT.setText(financeFV.checkInputtedFile(scene, this.month));
            } catch (Exception error) {
            warningT.setText("Action terminated.");
            }
        });   

        //Title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, welcomeL, bee2);
        titleHB.setAlignment(Pos.CENTER);
        
        //Left side buttons
        VBox left = new VBox(20);
        left.getChildren().addAll(newBudgetB, planB, summaryB);

        //Right side buttons
        VBox right = new VBox(20);
        right.getChildren().addAll(importB, transactionB, trendsB);
        
        //Hbox to combine left side and right side buttons
        HBox mainMenu = new HBox(20);
        mainMenu.getChildren().addAll(left, right);
        mainMenu.setAlignment(Pos.CENTER);
        
        //Main screen to combine title, intro, menu, and warning text
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(titleHB, introL, mainMenu, warningT);
        mainScreen.setAlignment(Pos.CENTER);
        
        //Calling the borderpane method
        BorderPane bPane = features.showBorder(mainScreen);
        
        //Add all the components to the scene
        one = new Scene(bPane, 1000, 500);
        return one;
    }    

    //-------------------- SCENE TWO BELOW --------------------//
    public Scene showSceneTwo (Stage stage, ComboBox cBMonths){ 
        Scene two; //Initialize a scene to return 

        //Title, bees, instructions label and formatting
        Label newBudgetL = features.setFont("NEW BUDGET", 25);
        bee = features.image();
        bee2 = features.image();
        Label instructionsL = features.setFont("Type in your desired category and press add to include it.", 12); 
        Label instructionsL2 = features.setFont("If you would like to delete one, type in your category and press the delete button.", 12); 
        Label infoL = features.setFont("If you want default categories set for you, do not enter anything and just click confirm.", 12);

        //Asks the user which month they are budgeting for and provide a drop down menu 
        Label monthL = features.setFont("Month:", 12);        
        Text warningT = new Text("");

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
        Label incomeL = features.incomeLabel();
        Label expensesL = features.expenseLabel();
        
        Text incomeCatT = new Text(""); //This will depend on the categories (modify later)
        Text expensesCatT = new Text(""); 

        //Set the chosen month to selected month from combo box
        getMonth(cBMonths);
        showText(cBMonths, incomeCatT, expensesCatT, warningT, stage);

        /**
         * incomeCatT = showText(cBMonths, warningT, stage, trends.incomeCatList);
        expensesCatT = showText(cBMonths, warningT, stage, trends.expenseCatList);
         */

        //User selects either the add or delete button for income and expenses
        Button addIncCatB = features.yellowButton("ADD");
        addIncCatB.setOnAction(action ->{
            boolean check;       
            check = onlyLetters(incCatTF.getText()); //INPUT VALIDATION
            
            if(check){      
                warningT.setText("");                      
                showCategory(true, trends.incomeCatList, incCatTF, true, incomeCatT, expensesCatT); // arraylist is used so that they can keep adding categories          
            }
            else{
                warningT.setText("Please input a number. Do not include symbols or letters");                
            }
            
        });

        Button deleteIncCatB = features.yellowButton("DELETE");
        deleteIncCatB.setOnAction(action ->{
            showCategory(false, trends.incomeCatList, incCatTF, true, incomeCatT, expensesCatT);
        });

        Button addExpCatB = features.yellowButton("ADD");
        addExpCatB.setOnAction(action ->{
            boolean check;       
            check = onlyLetters(expCatTF.getText()); //INPUT VALIDATION
            
            if(check){      
                warningT.setText("");                      
                showCategory(true, trends.expenseCatList, expCatTF, false, incomeCatT, expensesCatT);                
            }
            else{
                warningT.setText("Please input a number. Do not include symbols or letters");                
            }
                                
        });

        Button deleteExpCatB = features.yellowButton("DELETE");
        deleteExpCatB.setOnAction(action ->{
            showCategory(false, trends.expenseCatList, expCatTF, false, incomeCatT, expensesCatT);
        });                           
                    
        //HBox gathers the components for income category's user input
        HBox incCatRow = new HBox(20);
        incCatRow.getChildren().addAll(incCatL, incCatTF, addIncCatB, deleteIncCatB);
        incCatRow.setAlignment(Pos.CENTER);
        
        //HBox gathers the components for expense category's user input
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
                
        //Buttons
        Button mainMenuB = goToSceneOne(window, "MAIN MENU");                
        Button nextPageB = features.yellowButton("NEXT");        
        nextPageB.setOnAction(action->{
            if(setMonth){
                clicked = true;
                trends.incomeCatList = trends.defaultCategories(trends.incomeCatList, trends.defaultInc);
                trends.expenseCatList = trends.defaultCategories(trends.expenseCatList, trends.defaultExp);            
                trends.income2D = trends.populateCat(trends.incomeCatList, trends.income2D, this.month);         //updates the 2d arrays with new categories (fixes the size too)
                trends.expense2D = trends.populateCat(trends.expenseCatList, trends.expense2D, this.month);                          
                sceneThree = showSceneThreeFour(window, "Anticipated"); // plan
                stage.setScene(sceneThree);
            }
            else{
                warningT.setText("Please choose a month.");
            }
        });
        
        //HBox to gather main menu button and next page button
        HBox sceneButtons = new HBox(20);
        sceneButtons.getChildren().addAll(mainMenuB, nextPageB);
        sceneButtons.setAlignment(Pos.BOTTOM_RIGHT);
        
        //Making the overall screen
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(titleHB, instructionsL, infoL, monthRow, incCatRow, expCatRow, catL, displayCat, warningT, sceneButtons);
        mainScreen.setAlignment(Pos.CENTER);
        
        //Call BorderPane method
        BorderPane bPane = features.showBorder(mainScreen);
        // bPane.setCenter(mainScreen);
    
        //Add a scroll wheel and put the components into the scene
        ScrollPane scroll = features.showScrollPane(bPane);
        two = new Scene(scroll, 1000, 500);
        return two;
    }
    
    //-------------------- SCENE THREE/FOUR BELOW --------------------//
    public Scene showSceneThreeFour(Stage stage, String whichType){
        System.out.println(this.month);
        Scene threeFour;  //Initialize a scene to return

        //Initialize titles, bees, buttons, labels and textfields
        Label titleL;
        if(whichType.equals("Anticipated")){
            titleL = features.setFont("Plan", 25);
        }
        else{
            titleL = features.setFont("Transactions", 25);
        }
        
        bee = features.image();
        bee2 = features.image();

        Label monthL = features.setFont(this.month, 18);

        Label typeL = features.setFont(whichType, 16); // anticipated or actual
        Label incL = features.incomeLabel();
        Label expL = features.expenseLabel();
        
        Label catTL = features.catLabel();
        Label catBL = features.catLabel();
        Label amntTL = features.amntLabel();
        Label amntBL = features.amntLabel();

        TextField amntTFT = features.amntT();   // user inputs inc amnt
        TextField amntTFB = features.amntT();   // user inputs exp amnt

        //Fillers to show their inputed amnt
        Text showIncCat = new Text();
        Text showIncAmt = new Text();
        Text showExpCat = new Text();
        Text showExpAmt = new Text();

        Text warningT = new Text("");
        warningT.setTextAlignment(TextAlignment.CENTER);

        // Arraylists
        ArrayList<String> incomeCatArr = new ArrayList<String>();
        ArrayList<String> incomeAmtArr = new ArrayList<String>(); 
        ArrayList<String> expenseCatArr = new ArrayList<String>();
        ArrayList<String> expenseAmtArr = new ArrayList<String>();

        Button addTB = features.yellowButton("ADD"); // TB (top half of the scene) -> income
        Button addBB = features.yellowButton("ADD"); // BB (bottom half of the scene) -> expense
        Button delTB = features.yellowButton("DELETE"); // TB (top half of the scene) -> income
        Button delBB = features.yellowButton("DELETE"); // BB (bottom half of the scene) -> expense        

        ComboBox cBIncThree = features.comboBoxIncome(trends.income2D);
        ComboBox cBIncFour = features.comboBoxIncome(trends.income2D);
        ComboBox cBExpThree = features.comboBoxExpense(trends.expense2D);
        ComboBox cBExpFour = features.comboBoxExpense(trends.expense2D);
        ComboBox cBInc;
        ComboBox cBExp;

        if(whichType.equalsIgnoreCase("ANTICIPATED")){
            cBInc = cBIncThree;
            cBExp = cBExpThree;
        }
        else{
            cBInc = cBIncFour;
            cBExp = cBExpFour;
        }

        addTB.setOnAction(action -> {     
            boolean check;       
            check = isNumeric(amntTFT.getText()); //INPUT VALIDATION
            
            if(check){      
                warningT.setText("");                      
                showUserInput(true , true, cBInc, amntTFT, showIncCat, showIncAmt, showExpCat, showExpAmt, incomeCatArr, incomeAmtArr);       
            }
            else{
                warningT.setText("Please input a number. Do not include symbols or letters");                
            }
                    
        });
        addBB.setOnAction(action -> {
            boolean check;       
            check = isNumeric(amntTFT.getText());    

            if(check){      
                warningT.setText("");       
                showUserInput(true, false, cBExp, amntTFB, showIncCat, showIncAmt, showExpCat, showExpAmt, expenseCatArr, expenseAmtArr);
            }
            else{
                warningT.setText("Please input a number. Do not include symbols or letters");                
            }
            
        });
        delTB.setOnAction(action -> {                                     
            showUserInput(false, true, cBInc, amntTFT, showIncCat, showIncAmt, showExpCat, showExpAmt, incomeCatArr, incomeAmtArr);
                    
        });
        delBB.setOnAction(action -> {                                       
            showUserInput(false, false, cBExp, amntTFB, showIncCat, showIncAmt, showExpCat, showExpAmt, expenseCatArr, expenseAmtArr);                                       
        });
                                
        
        // FORMATTING
        HBox lastRow = new HBox(10);
        //Conds for the button of scene 3 and scene 4 
        lastRow.setAlignment(Pos.BOTTOM_RIGHT);
        
        if (whichType.equalsIgnoreCase("Anticipated")){
            Button nextB = features.yellowButton("NEXT");
            nextB.setOnAction(action -> {  
            trends.income2D = trends.populate(incomeCatArr, incomeAmtArr, trends.income2D, trends.incomeCatList, 1, this.month);
            trends.expense2D = trends.populate(expenseCatArr, expenseAmtArr, trends.expense2D, trends.expenseCatList, 1, this.month); 

            //put into CSV
            financeFV.toCSV(trends.income2D, "income", this.month);
            financeFV.toCSV(trends.expense2D, "expense", this.month);

            cBMonths = features.comboBoxMonths();
            sceneFour = showSceneThreeFour(window, "Actual"); // transactions
            stage.setScene(sceneFour);
            });
            
            Button mainMenuB = goToSceneOne(stage, "MAIN MENU");
            lastRow.getChildren().addAll(mainMenuB, nextB);
        }
        else{
            Button nextB = features.yellowButton("NEXT");
            nextB.setOnAction(action -> {   
            trends.income2D = trends.populate(incomeCatArr, incomeAmtArr, trends.income2D, trends.incomeCatList, 2, this.month);
            trends.expense2D = trends.populate(expenseCatArr, expenseAmtArr, trends.expense2D, trends.expenseCatList, 2, this.month); 
            trends.populateDiff(trends.income2D);
            trends.populateDiff(trends.expense2D);

            //Put into CSV
            financeFV.toCSV(trends.income2D, "income", this.month);
            financeFV.toCSV(trends.expense2D, "expense", this.month);

            cBMonths = features.comboBoxMonths();
            sceneFive = showSceneFive(window, cBMonths);
            stage.setScene(sceneFive);
            });
            Button backB = goToSceneThree(stage, "BACK");
            Button mainMenuB = goToSceneOne(stage, "MAIN MENU");
            lastRow.getChildren().addAll(mainMenuB, backB, nextB);
        }

        //Stack panes to show the user inputs on top of the rectangles
        StackPane stackPaneT = features.showSPane(showIncCat, showIncAmt);
        StackPane stackPaneB = features.showSPane(showExpCat, showExpAmt);

        //Gathers the title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, titleL, bee2);
        titleHB.setAlignment(Pos.CENTER);

        //Plan vs transactions and income labels
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

        //Put it all together in the vbox
        VBox mainScreen = new VBox(10);
        mainScreen.getChildren().addAll(titleHB, monthL, warningT, secondRow, thirdRow, stackPaneT, fourthRow, fifthRow, stackPaneB, lastRow);
        mainScreen.setAlignment(Pos.TOP_CENTER);

        //Add a border
        BorderPane bPane = features.showBorder(mainScreen);

        //Add a scroll wheel
        ScrollPane scroll = features.showScrollPane(bPane);

        //Put the components into the scene
        threeFour = new Scene(scroll, 1000, 500);
        return threeFour; 
    }
    
    public boolean onlyLetters(String str) { //checks if the string only contains letters
        char[] chars = str.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isNumeric(String strNum){
        if(strNum == null){            
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;        
    }
    
    //-------------------- SCENE FIVE BELOW --------------------//
public Scene showSceneFive(Stage stage, ComboBox cBMonths){
    setMonth(cBMonths);
    //Set the chosen month to selected month from combo box and display data
    //for scene 5, to change the data shown
    cBMonths.setOnAction(action ->{
        this.month = (String)cBMonths.getValue();
        if(financeFV.checkForMonth(this.month).equals(":)")){
            System.out.println("NULL MONTH");
            Popup warning = showWarning();
            warning.show(stage);

        }
        else{
            financeFV.repopulate(this.month, trends.income2D, "income");
            financeFV.repopulate(this.month, trends.expense2D, "expense");
            sceneFive = showSceneFive(window, cBMonths);
            stage.setScene(sceneFive);
        }
        
    });
    
    //Initialize scene
    Scene five;
    System.out.println("scene 5");
    for(int i = 0; i < trends.income2D.length; i++){
        for(int j = 0; j < trends.income2D[0].length; j++){
            System.out.print(trends.income2D[i][j] + ", ");
        } 
        System.out.println();
    }  
    
    //Bees
    bee = features.image();
    bee2 = features.image();
    
    //HBox gathers the bees and combobox for title
    HBox titleHB = new HBox();
    titleHB.getChildren().addAll(bee, cBMonths, bee2);
    titleHB.setAlignment(Pos.CENTER);
    
    //Initialize labels and formatting
    Label labelIncome = features.incomeLabel();
    Label labelExpenses = features.expenseLabel();
    Label labelAnticipated = features.anticipatedLabel(); 
    Label labelAnticipated2 = features.anticipatedLabel(); 
    Label labelActual = features.actuaLabel(); 
    Label labelActual2 = features.actuaLabel();
    Label labelDiff = features.diffLabel(); 
    Label labelDiff2 = features.diffLabel(); 
    Label labelTotals = features.totaLabel();
    Label labelTotals2 = features.totaLabel();
    
    //Initialize label that act as line breaks (formatting)
    Label labelBlank = new Label ("");
    Label labelBlank2 = new Label ("");
    Label labelBlank3 = new Label ("");
    Label labelBlank4 = new Label ("");
    Label labelBlank5 = new Label ("");
    Label labelBlank6 = new Label ("");
    
    //Far left - Gathers the first column (counting left to right)
    VBox vBoxFarLeft = new VBox(10);
    vBoxFarLeft.getChildren().addAll(labelIncome, labelTotals);
    
    //Prints out the elements inside the income array
    for (int i = 1; i < trends.income2D.length; i++){
    Label labelFarLeft = new Label(trends.income2D[i][0]);
    labelFarLeft.setFont(Font.font("Verdana", 12));
    vBoxFarLeft.getChildren().add(labelFarLeft);
    }    
    
    //Second left - Gathers the second column 
    VBox vBoxSecondLeft = new VBox(10);
    vBoxSecondLeft.getChildren().addAll(labelAnticipated, labelBlank);
    
    //Prints out the elements inside the income array
    for (int i = 1; i < trends.income2D.length; i++){
    Label labelSecondLeft = new Label(trends.income2D[i][1]);
    labelSecondLeft.setFont(Font.font("Verdana", 12));
    vBoxSecondLeft.getChildren().add(labelSecondLeft);
    }   
    vBoxSecondLeft.setAlignment(Pos.TOP_CENTER);
    
    //Third left - Gathers the third column
    VBox vBoxThirdLeft = new VBox(10);
    vBoxThirdLeft.getChildren().addAll(labelActual, labelBlank2);
    
    //Prints out the elements inside the income array 
    for (int i = 1; i < trends.income2D.length; i++){
    Label labelThirdLeft = new Label(trends.income2D[i][2]);
    labelThirdLeft.setFont(Font.font("Verdana", 12));
    vBoxThirdLeft.getChildren().add(labelThirdLeft);
    }   
    vBoxThirdLeft.setAlignment(Pos.TOP_CENTER);
    
    //Fourth left - Gathers the fourth column
    VBox vBoxFourthLeft = new VBox(10);
    vBoxFourthLeft.getChildren().addAll(labelDiff, labelBlank3);
    
    //Prints out the elements inside the income array
    for (int i = 1; i < trends.income2D.length; i++){
    Label labelFourthLeft = new Label(trends.income2D[i][3]);
    labelFourthLeft.setFont(Font.font("Verdana", 12));
    vBoxFourthLeft.getChildren().add(labelFourthLeft);
    }   
    vBoxFourthLeft.setAlignment(Pos.TOP_CENTER);
    
    //Fourth right - Gathers the fifth column
    VBox vBoxFourthRight = new VBox(10);
    vBoxFourthRight.getChildren().addAll(labelExpenses, labelTotals2);
    
    //Prints out the elements inside expense array
    for (int i = 1; i < trends.expense2D.length; i++){
    Label labelFourthRight = new Label(trends.expense2D[i][0]);
    labelFourthRight.setFont(Font.font("Verdana", 12));
    vBoxFourthRight.getChildren().add(labelFourthRight);
    }
    
    //Third right - Gathers the sixth column
    VBox vBoxThirdRight = new VBox(10);
    vBoxThirdRight.getChildren().addAll(labelAnticipated2, labelBlank6);
    
    //Prints out the elements inside expense array
    for (int i = 1; i < trends.expense2D.length; i++){
    Label labelThirdRight = new Label(trends.expense2D[i][1]);
    labelThirdRight.setFont(Font.font("Verdana", 12));
    vBoxThirdRight.getChildren().add(labelThirdRight);
    }
    vBoxThirdRight.setAlignment(Pos.TOP_CENTER);
    
    //Second right - Gathers the seventh column 
    VBox vBoxSecondRight = new VBox(10);
    vBoxSecondRight.getChildren().addAll(labelActual2, labelBlank5);
    
    //Prints out the elements inside expense array
    for (int i = 1; i < trends.expense2D.length; i++){
    Label labelSecondRight = new Label(trends.expense2D[i][2]);
    labelSecondRight.setFont(Font.font("Verdana", 12));
    vBoxSecondRight.getChildren().add(labelSecondRight);
    }
    vBoxSecondRight.setAlignment(Pos.TOP_CENTER);
    
    //Far right - Gathers the eighth column
    VBox vBoxFarRight = new VBox(10);
    vBoxFarRight.getChildren().addAll(labelDiff2, labelBlank4);
    
    for (int i = 1; i < trends.expense2D.length; i++){
    Label labelFarRight = new Label(trends.expense2D[i][3]);
    labelFarRight.setFont(Font.font("Verdana", 12));
    vBoxFarRight.getChildren().add(labelFarRight);
    }  
    vBoxFarRight.setAlignment(Pos.TOP_CENTER);
    
    //Buttons
    Button backB = goToSceneThree(stage, "BACK"); //back to scene 3
    Button mainMenuB = goToSceneOne(window, "MAIN MENU");  
    Button nextPageB = goToSceneSix(window, "NEXT PAGE");
    
    //Gathers all the coloumns (vboxes) to create the left side of the summary table
    HBox leftTable = new HBox(20);
    leftTable.getChildren().addAll(vBoxFarLeft, vBoxSecondLeft, vBoxThirdLeft, vBoxFourthLeft);
    
    //Gathers all the coloumns (vboxes) to create the right side of the summary table
    HBox rightTable = new HBox(20);
    rightTable.getChildren().addAll(vBoxFourthRight, vBoxThirdRight, vBoxSecondRight, vBoxFarRight);
    
    //Gathers the left table and right table to make an entire table
    HBox hBoxMiddle = new HBox(20);
    hBoxMiddle.getChildren().addAll(leftTable, rightTable);
    hBoxMiddle.setAlignment(Pos.CENTER);
    
    //Gathers the buttons
    HBox buttonsHB = new HBox(10);
    buttonsHB.getChildren().addAll(backB, mainMenuB, nextPageB);
    buttonsHB.setAlignment(Pos.BOTTOM_RIGHT);
    
    //Combines the title, the entire table and the buttons
    VBox mainScreen = new VBox(10);
    mainScreen.getChildren().addAll(titleHB, hBoxMiddle, buttonsHB);
    mainScreen.setAlignment(Pos.CENTER);
    
    //Border pane, scroll pane and display scene
    BorderPane bPane = features.showBorder(mainScreen);
    ScrollPane sPane = features.showScrollPane(bPane);
    five = new Scene(sPane, 1000, 500);
    return five;
}

//-------------------- SCENE SIX BELOW --------------------//
public Scene showSceneSix(Stage scene, ComboBox cBMonths){
    Scene six;    
    DecimalFormat df = new DecimalFormat("######.##"); //Formatting 
    
    //Bees
    bee = features.image();
    bee2 = features.image();
    
    setMonth(cBMonths);
    //Set the chosen month to selected month from combo box and display data
    cBMonths.setOnAction(action ->{
        this.month = (String)cBMonths.getValue();
        if(financeFV.checkForMonth(this.month).equals(":)")){
            System.out.println("NULL MONTH");
            Popup warning = showWarning();
            warning.show(scene);

        }
        else{
            financeFV.repopulate(this.month, trends.income2D, "income");
            financeFV.repopulate(this.month, trends.expense2D, "expense");
            sceneSix = showSceneSix(window, cBMonths);
            scene.setScene(sceneSix);
        }
        
    });
    
    //Gather the bees and combobox to make the title section
    HBox titleHB = new HBox();
    titleHB.getChildren().addAll(bee, cBMonths, bee2);
    titleHB.setAlignment(Pos.CENTER);
    
    Label monthlyBudgetL = features.setFont("MONTHLY BUDGET", 16);
    
    //-------------------- SCENE SIX SECTION 1 BELOW --------------------//
    //Initialize double variables to store calculation results
    double sumAccIncome = trends.sumAccCalculator(trends.income2D); //Acc income value
    double endBalance = trends.sumAccCalculator(trends.income2D) - trends.sumAccCalculator(trends.expense2D); //Acc income - acc expense
    
    //Initialize rectangles for GUI
    Rectangle sumAccIncomeR, endBalanceR;
    
    //If the acc income or end balance is a negative number, the default minimum size for the rectangle (10) is used
    if (sumAccIncome < 0){
        sumAccIncomeR = new Rectangle (50, 10, babyBlue); //10 is the default min. rect size
    }
    else{
        sumAccIncomeR = new Rectangle (50, sumAccIncome + 10, babyBlue);
    }
    
    if(endBalance < 0){
        endBalanceR = new Rectangle (50, 10, darkBlue);
    }
    else{
        endBalanceR = new Rectangle (50, endBalance + 10, darkBlue);
    }
    
    //If the acc income or end balance is greater than 1500, the rectangles will be divided by 100 so they fit the screen
    if(sumAccIncome > 1500 || endBalance > 1500){
    sumAccIncomeR = new Rectangle (50, sumAccIncome/100, babyBlue);
    endBalanceR = new Rectangle (50, endBalance/100, darkBlue);
    }
    //If the acc income or end balance is greater than 100, the rectangles will be divided by 10 so they fit the screen
    else if(sumAccIncome > 100 || endBalance > 100){
    sumAccIncomeR = new Rectangle (50, sumAccIncome/10, babyBlue);
    endBalanceR = new Rectangle (50, endBalance/10, darkBlue);
    }
    
    //Initiate labels to display calculation results
    Label sumAccIncomeL = new Label("Sum of Actual Income");
    Label sumAccIncomeValueL = new Label("$" + String.valueOf(df.format(sumAccIncome)));

    Label endBalanceL = new Label("End Balance");
    Label endBalanceValueL = new Label("$" + String.valueOf(endBalance));
    
    //Gathers the acc income components
    VBox sumAccIncomeHB = new VBox(10);
    sumAccIncomeHB.getChildren().addAll(sumAccIncomeR, sumAccIncomeL, sumAccIncomeValueL);
    sumAccIncomeHB.setAlignment(Pos.BOTTOM_CENTER);
    
    //Gathers the end balance components
    VBox endBalanceHB = new VBox(10);
    endBalanceHB.getChildren().addAll(endBalanceR, endBalanceL, endBalanceValueL);
    endBalanceHB.setAlignment(Pos.BOTTOM_CENTER);
    
     //Gathers the acc income and end balance vboxes
    HBox summaryLeftHB = new HBox(50);
    summaryLeftHB.getChildren().addAll(sumAccIncomeHB, endBalanceHB);
    
    //-------------------- SCENE SIX SECTION 2 BELOW --------------------//
    //Initiate double variables to store calculation results
    Double percentSaved = (endBalance/(trends.sumAccCalculator(trends.income2D))) * 100;
    Double saved = endBalance;
    
    //Initiate labels to display calculations
    Label percentL = new Label("% " + String.valueOf(df.format(percentSaved)));
    Label savedL = new Label("$ " + String.valueOf(df.format(saved)));
    Label percentDescriptionL = new Label("");
    Label savedDescriptionL = new Label("");
    
    //Label output depends on if the user gained (positive #) or lost money (negative #)
    if (percentSaved > 0){
    percentDescriptionL = new Label("Increase in total savings");
    }
    else{
    percentDescriptionL = new Label("Decrease in total savings");
    }
    
    if (saved > 0){
    savedDescriptionL = new Label("Saved this month");
    }
    else{
    savedDescriptionL = new Label("Lost this month");
    }
    
    //Gathers all components
    VBox summaryRightHB = new VBox(10);
    summaryRightHB.getChildren().addAll(percentL, percentDescriptionL, savedL, savedDescriptionL);
    summaryRightHB.setAlignment(Pos.CENTER);  
    
    //Add a rectangle (blue background) to the back of the vbox
    StackPane summaryRightStack = new StackPane();
    summaryRightStack.getChildren().addAll(new Rectangle(300, 150, babyBlue), summaryRightHB);
    
    //-------------------- SCENE SIX SECTION 3 BELOW --------------------//            
    //Initiate double variables to store calculation results
    double sumAntIncome = trends.sumCalculator(trends.income2D);
    double sumAntExpense = trends.sumCalculator(trends.expense2D);
    double sumAccExpense = trends.sumAccCalculator(trends.expense2D);;
    //Note: sumAccIncome has been initialized earlier
    
    //Initiate labels
    Label incomeL = features.incomeLabel();
    Label expensesL = features.expenseLabel();
    Label antL = features.anticipatedLabel();
    Label accL = features.actuaLabel();
    Label antL2 = features.anticipatedLabel();
    Label accL2 = features.actuaLabel();
    
    //For spacing/formatting
    Label space = new Label(""); //For spacing
    Label space2 = new Label("");
    Label space3 = new Label(""); 
    Label space4 = new Label(""); 
    Label space5 = new Label("");
    Label space6 = new Label("");
    
    //Display values
    Label sumAntIncL = new Label("$" + String.valueOf(sumAntIncome));
    Label sumAccIncL = new Label("$" + String.valueOf(sumAccIncome));
    Label sumAntExpL = new Label("$" + String.valueOf(sumAntExpense));
    Label sumAccExpL = new Label("$" + String.valueOf(sumAccExpense));
    
    //Initialize rectangles
    Rectangle sumAntIncR, sumAccIncR, sumAntExpR, sumAccExpR;
    
    //If any of actual or anticipated income or expense is 0, a default rectangle size (10) is used 
    if (sumAntIncome < 0){
    sumAntIncR = new Rectangle(10, 20, white); //10 is default rect size
    }
    else{
    sumAntIncR = new Rectangle(sumAntIncome + 10, 20, white);
    }
    
    if(sumAccIncome < 0){
    sumAccIncR = new Rectangle(10, 20, white);
    }
    else{
    sumAccIncR = new Rectangle(sumAccIncome + 10, 20, white);
    }
    if(sumAntExpense < 0){
    sumAntExpR = new Rectangle(10, 20, white);
    }
    else{
    sumAntExpR = new Rectangle(sumAntExpense + 10, 20, white);
    }
    if(sumAccExpense < 0){
    sumAccExpR = new Rectangle(10, 20, white);
    }
    else{
    sumAccExpR = new Rectangle(sumAccExpense + 10, 20, white);
    }
    
    //If the values in antcipated and actual income or expenses are too big, all the rectangle
    //sizes will be shrunk proportionately
    if (sumAntIncome > 1500 || sumAccIncome > 1500 || sumAntExpense > 1500 || sumAccExpense > 1500){
    sumAntIncR = new Rectangle(sumAntIncome/100, 20, white);
    sumAccIncR = new Rectangle(sumAccIncome/100, 20, white);
    sumAntExpR = new Rectangle(sumAntExpense/100, 20, white);
    sumAccExpR = new Rectangle(sumAccExpense/100, 20, white);
    }
    else if (sumAntIncome > 100 || sumAccIncome > 100 || sumAntExpense > 100 || sumAccExpense > 100){
    sumAntIncR = new Rectangle(sumAntIncome/10, 20, white);
    sumAccIncR = new Rectangle(sumAccIncome/10, 20, white);
    sumAntExpR = new Rectangle(sumAntExpense/10, 20, white);
    sumAccExpR = new Rectangle(sumAccExpense/10, 20, white);
    }
    
    //Gathers the anticipated and actual labels for income
    VBox incAntAccHB = new VBox(20);
    incAntAccHB.getChildren().addAll(antL, accL);
    
    //Adds income header to the vbox above
    VBox incomeSummaryVB = new VBox(20);
    incomeSummaryVB.getChildren().addAll(incomeL, incAntAccHB);
    
    //Gathers the anticipated and actual values for income
    VBox incAntAccValueHB = new VBox(20);
    incAntAccValueHB.getChildren().addAll(space, sumAntIncL, sumAccIncL);
    
    //Gathers the income rectangles 
    VBox incRectVB = new VBox(20);
    incRectVB.getChildren().addAll(space2, sumAntIncR, sumAccIncR);
    
    //Gathers the anticipated and actual labels for expense
    VBox expAntAccHB = new VBox(20);
    expAntAccHB.getChildren().addAll(antL2, accL2);
    
    //Adds expense header to the vbox above
    VBox expenseSummaryVB = new VBox(20);
    expenseSummaryVB.getChildren().addAll(expensesL, expAntAccHB);
    
    //Gathers the anticipated and actual values for expense
    VBox expAntAccValueHB = new VBox(20);
    expAntAccValueHB.getChildren().addAll(space3, sumAntExpL, sumAccExpL);
    
    //Gathers the expense rectangles 
    VBox expRectVB = new VBox(20);
    expRectVB.getChildren().addAll(space4, sumAntExpR, sumAccExpR);
    
    //Gathers all rectangles
    HBox allRect = new HBox(20);
    allRect.getChildren().addAll(incomeSummaryVB, incAntAccValueHB, incRectVB, expenseSummaryVB, expAntAccValueHB, expRectVB);
    allRect.setAlignment(Pos.CENTER);
    
    //Gathers everything to make the whole section an entire entity
    VBox summaryBottomVB = new VBox(20);
    summaryBottomVB.getChildren().addAll(space5, allRect, space6);
    summaryBottomVB.setBackground(new Background(new BackgroundFill(darkBlue, CornerRadii.EMPTY, Insets.EMPTY)));
    
    //-------------------- SCENE SIX COMBINE ALL SECTIONS BELOW --------------------//
    //Initialize buttons
    Button mainMenuB = goToSceneOne(window, "MAIN MENU"); 
    Button backB = goToSceneFive(window, "BACK");
    
    //Gather the left and right sides (section 1 and 2)
    HBox mainScreenMiddle = new HBox(100);
    mainScreenMiddle.getChildren().addAll(summaryLeftHB, summaryRightStack);
    mainScreenMiddle.setAlignment(Pos.CENTER);
    
    //Gathers buttons
    HBox buttonsHB = new HBox(10);
    buttonsHB.getChildren().addAll(backB, mainMenuB);
    buttonsHB.setAlignment(Pos.BOTTOM_RIGHT);
    
    //Gathers all components for the screen
    VBox mainScreen = new VBox(10);
    mainScreen.getChildren().addAll(titleHB, monthlyBudgetL, mainScreenMiddle, summaryBottomVB, buttonsHB);
    mainScreen.setAlignment(Pos.CENTER);
    
    //Calling the borderpane method
    BorderPane bPane = features.showBorder(mainScreen);
    
    //Add a scroll wheel
    ScrollPane scroll = features.showScrollPane(bPane);

    //Add the components into the scene
    six = new Scene(scroll, 1000, 500);
    return six;
    }

    //-------------------- SCENE SEVEN BELOW --------------------//
    public Scene showSceneSeven(Stage stage, ComboBox cBMonths){ // u have to click plan and transactions first because thats what populates the 2d arr
        Scene seven; 
        
        setMonth(cBMonths);
        cBMonths.setOnAction(action ->{
            this.month = (String)cBMonths.getValue();
            if(financeFV.checkForMonth(this.month).equals(":)")){
                System.out.println("NULL MONTH");
                Popup warning = showWarning();
                warning.show(stage);
    
            }
            else{
                financeFV.repopulate(this.month, trends.income2D, "income");
                financeFV.repopulate(this.month, trends.expense2D, "expense");
                sceneSeven = showSceneSeven(window, cBMonths);
                stage.setScene(sceneSeven);
            }
            
        });
        
        //Initialize buttons
        Button main = goToSceneOne(stage, "MAIN MENU");
        //Button back = goToSceneSix(stage, "BACK");

        PieChart piechart = showPieChart(); //Initialize pie chart       
        LineChart<String,String> lineChart = showlLineChart(); //Initialize line chart
        
        //Gathers graphs
        HBox graphs = new HBox(10);
        graphs.getChildren().addAll(piechart , lineChart);
        
        //Gathers buttons
        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(main);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);
        
        //Gathers graphs and buttons to make the screen
        VBox mainScreen = new VBox(10);
        mainScreen.getChildren().addAll(cBMonths, graphs, buttons);
        mainScreen.setAlignment(Pos.CENTER);
        
        //Put the entire screen into borderpane and display
        BorderPane bPane = features.showBorder(mainScreen);
        seven = new Scene(bPane, 1000, 500);
        return seven;
    }

    // ---------------------------- GRAPHS ------------------------------ //
    // pie chart of actual income earnings
    public PieChart showPieChart(){
        ArrayList<PieChart.Data> categories = new ArrayList<PieChart.Data>();
        for(int i = 1; i < trends.income2D.length; i++){   
            double amount = Double.valueOf(trends.income2D[i][2])    ;
            if(amount != 0){                 
                categories.add(new PieChart.Data(trends.income2D[i][0], amount));
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(categories);
                    
        PieChart piechart = new PieChart(pieChartData);
        piechart.setTitle("Actual Income");
        return piechart;
    }
    // line chart of expenses (actual and anticipated)
    public LineChart<String,String> showlLineChart(){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Category");
        
        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Amount($)");
    
        LineChart<String,String> lineChart = new LineChart<String, String>(xAxis,yAxis);
    
        lineChart.setTitle("Expense Line Graph");
    
        // lineChart.setTitle("Expense Line Graph");
    
        XYChart.Series antSeries = new XYChart.Series();
        XYChart.Series accSeries = new XYChart.Series();
    
        antSeries.setName("Anticipated");
        accSeries.setName("Actual");
    
        for(int i = 1; i < trends.expense2D.length; i++) {
            for (int j = 0; j < 4; j++){
                antSeries.getData().add(new XYChart.Data(trends.expense2D[i][0], trends.expense2D[i][1]));
            }
        }
        for(int i = 1; i < trends.expense2D.length; i++) {
            for (int j = 0; j < 4; j++){
                accSeries.getData().add(new XYChart.Data(trends.expense2D[i][0], trends.expense2D[i][2]));
            }
        }
        
        lineChart.getData().addAll(antSeries, accSeries);

        return lineChart;
    }     

    // ---------------------- Button Methods --------------------------- //
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
            cBMonths = features.comboBoxMonths();
            sceneTwo = showSceneTwo(window, cBMonths); //  new budget     
            stage.setScene(sceneTwo);
        });
        return scene2B;
    }
    public Button goToSceneThree(Stage stage, String sceneName){
        Button scene3B = features.yellowButton(sceneName);
        scene3B.setOnAction(action -> {
            if(this.month != null && clicked == true){
                cBMonths = features.comboBoxMonths();
                sceneThree = showSceneThreeFour(window, "Anticipated"); // plan
                stage.setScene(sceneThree);
            }
        });
        return scene3B;
    }    
    public Button goToSceneFour(Stage stage, String sceneName){
        Button scene4B = features.yellowButton(sceneName);
        scene4B.setOnAction(action -> {
            if(this.month != null && clicked == true){
                cBMonths = features.comboBoxMonths();
                sceneFour = showSceneThreeFour(window, "Actual"); // transactions
                stage.setScene(sceneFour);
            }
        });
        return scene4B;
    }
    public Button goToSceneFive(Stage stage, String sceneName){
        Button scene5B = features.yellowButton(sceneName);
        scene5B.setOnAction(action -> {
            if(this.month != null && clicked == true || financeFV.imported == true){
                financeFV.repopulate(this.month, trends.income2D, "income");
                financeFV.repopulate(this.month, trends.expense2D, "expense");
                trends.populateDiff(trends.income2D);
                trends.populateDiff(trends.expense2D);
                cBMonths = features.comboBoxMonths();
                sceneFive = showSceneFive(window, cBMonths); // summary
                stage.setScene(sceneFive);
            }
        });
        return scene5B;
    }
    public Button goToSceneSix(Stage stage, String sceneName){
        Button scene6B = features.yellowButton(sceneName);
        scene6B.setOnAction(action -> {
            if(this.month != null && clicked == true || financeFV.imported == true){
                cBMonths = features.comboBoxMonths();
                sceneSix = showSceneSix(window, cBMonths); // trends
                stage.setScene(sceneSix);
            }
        });
        return scene6B;
    }
    public Button goToSceneSeven(Stage stage, String sceneName){
        Button scene7B = features.yellowButton(sceneName);
        scene7B.setOnAction(action -> {
            if(this.month != null && clicked == true || financeFV.imported == true){
                cBMonths = features.comboBoxMonths();
                sceneSeven = showSceneSeven(window, cBMonths); // trends
                stage.setScene(sceneSeven);
            }
        });
        return scene7B;
    }
    // ---------------------------------------------------------------- //

    //Month things
    public void getMonth(ComboBox cBMonths){
        cBMonths.setOnAction(action ->{
            this.month = (String)cBMonths.getValue();
        });
    }
    
    public Popup showWarning(){
      Popup warning = new Popup();
      Label warnLabel = features.setFont("Please select a month with \n previous data from your csv.", 15);
      warnLabel.setAlignment(Pos.CENTER);
      // warnLabel.setSkin(babyBlue);

      Button exit = new Button("X");
      exit.setOnAction(action -> {
        warning.hide();
      });
      exit.setAlignment(Pos.TOP_LEFT);

      HBox mainPopup = new HBox(warnLabel, exit);
      // mainPopup.setAlignment(Pos.TOP_CENTER);
      mainPopup.setBackground(new Background(new BackgroundFill(yellow, CornerRadii.EMPTY, Insets.EMPTY)));


      warning.getContent().addAll(mainPopup);
      return warning;
    }

    //Set default combobox month name display
    public void setMonth(ComboBox cBMonths){
        if(this.month == null){
            this.month = trends.income2D[0][0].substring(7,trends.income2D[0][0].length());
        }
        for(int i = 0; i < trends.monthNames.length; i++){
            if(trends.monthNames[i].equals(this.month)){
                cBMonths.getSelectionModel().select(i);
            }
        }
    }

    // ---------------------------------------------------------------- //

    public void showText(ComboBox cBMonths, Text incomeCatT, Text expenseCatT, Text warningT, Stage stage){
        cBMonths.setOnAction(action ->{
            this.month = (String)cBMonths.getValue();
            warningT.setText("month has been clicked");
            setMonth = true;
            financeFV.fileName = "income.csv";
            String startCoords = financeFV.checkForMonth(this.month);
            if(startCoords.equals(":)")){
                System.out.println("list has been cleared");
                trends.incomeCatList.clear();
                trends.expenseCatList.clear();
                sceneTwo = showSceneTwo(window, cBMonths);
                stage.setScene(sceneTwo);
            }
            else{
                trends.incomeCatList.clear();
                trends.expenseCatList.clear();
                financeFV.repopulate(this.month, trends.income2D, "income"); 
                financeFV.repopulate(this.month, trends.expense2D, "expense");
                for(int i = 1; i < trends.income2D.length; i++){        
                    trends.incomeCatList.add(trends.income2D[i][0]);
                }
                for(int i = 1; i < trends.expense2D.length; i++){        
                    trends.expenseCatList.add(trends.expense2D[i][0]);
                }
            }
            
            // makes incomeCatT into what is in the arraylist
            String incStringCat, expStringCat;
            String incPrintCat = "";
            String expPrintCat = "";

          
            
            if(trends.incomeCatList.isEmpty() == false || trends.expenseCatList.isEmpty() == false){
                System.out.println("in is empty");
                for(int i = 0; i < trends.incomeCatList.size(); i ++){
                    incStringCat = trends.incomeCatList.get(i) + "\n";      
                    System.out.println("String cat is: " + incStringCat)      ;
                    incPrintCat += incStringCat;            
                    System.out.println(incPrintCat);
                    
                    incomeCatT.setText("Categories : \n" + incPrintCat);
                }
                for(int i = 0; i < trends.expenseCatList.size(); i ++){
                    expStringCat = trends.expenseCatList.get(i) + "\n";      
                    System.out.println("String cat is: " + expStringCat)      ;
                    expPrintCat += expStringCat;            
                    System.out.println(expPrintCat);
                    
                    expenseCatT.setText("Categories : \n" + expPrintCat);
                }
            }
        });
    }
    // test

    // for new budget
    public void showCategory(boolean add, ArrayList<String> categoryArrList, TextField catTF, boolean income, Text incomeCatT, Text expensesCatT){
        String stringCat;
        String printCat = "";
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
            stringCat = categoryArrList.get(i) + "\n";      
            System.out.println("String cat is: " + stringCat)      ;
            printCat += stringCat;            
            System.out.println(printCat);
            if(income == true){
                incomeCatT.setText("Categories : \n" + printCat);
            }
            else {
                expensesCatT.setText("Categories : \n" + printCat);
            }
        }
        System.out.println("arraylist is: " + categoryArrList);
    }

    public void showUserInput(boolean add, boolean income, ComboBox comboBox, TextField amntTF, Text catInc, Text amtInc, Text catExp, Text amtExp, ArrayList<String> catArr, ArrayList<String> amtArr){
        // initialize strings
        String temp = (String) comboBox.getValue();
        String tempText = amntTF.getText();
        String stringCat, stringAmt;
        String printCat = "";
        String printAmt = "";
        
        // conds if it is the add btn or del btn
        if (add == true){
            catArr.add(temp);    //JANE can i jus put temp?
            amtArr.add(tempText);                // and like tempText here?
            
        }
        else{
            // for(int i = 0; i < CatArr.size(); i++){
                if( catArr.contains(temp) && amtArr.contains(tempText)){
                    catArr.remove(temp);
                    amtArr.remove(tempText);     
                }
            // }
            System.out.println(catArr + " : " + amtArr);      // for testing DELETE LTR
        }

        // conds if scene 3 or scene 4
        for(int i = 0; i < catArr.size(); i ++){
            // set string as the array element
            stringCat = catArr.get(i) + "\n";
            stringAmt = amtArr.get(i) + "\n";
            // concanate
            printCat += stringCat;
            printAmt += stringAmt;
        }
        System.out.println(catArr + " : " + amtArr);  // testing DELETE LTR
        
        // if the user deletes all inputs set it to default labels 
        if(catArr.size() == 0 || catArr.size() == 0){
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
            catInc.setText("Categories : \n" + printCat);
            amtInc.setText("Amount : \n" + printAmt);
            System.out.println("inc cat : " + printCat + " inc amt : " + printAmt);     //testng DELETE LTR

        }
        else {
            catExp.setText("Categories : \n" + printCat);
            amtExp.setText("Amount : \n" + printAmt);
            System.out.println("exp cat : " + printCat + " exp amt : " + printAmt);     //testng DELETE LTR
        }
        System.out.println("Button pressed"); // testing delete later
    }    
}
