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
import javafx.geometry.Pos;
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

    public String month;            // the name of the month that the user wants to look at 
    public boolean clicked;
    public boolean setMonth;        // display of th
    public boolean noChange = true; // for scene 2: checks if their categories have been finalized in the csv or not

    // Display elements
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
    
    public AppLayoutFV(){ // Constructor Method
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
                
        window = primaryStage;

        // setting the main menu (initial) scene
        sceneOne = showSceneOne(window); // main Menu
        
        // set the first scene
        window.setScene(sceneOne);
        window.show();
        
    }
    //-------------------- SCENE ONE BELOW --------------------//
    /**
     * Creates scene one: holds all the components of scene one
     * @param stage contains all the scenes 
     * @return scene one
     */
    public Scene showSceneOne (Stage stage){

        // initialize a scene to return
        Scene one;

        // initialize the warning text if the user doesn't enter a csv file it'll change in the gui
        Text warningOne = new Text("");
        
        //Title, bees and instructions
        Label welcomeL = features.setFont("MAIN MENU", 25);
        welcomeL.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        
        bee = features.image();
        bee2 = features.image();
        
        Label introL = features.setFont("Click on one of the buttons below to visit a page", 12);
        
        //Buttons and formatting
        Button newBudgetB = goToSceneTwo(window, "SELECT MONTH");       // When this button is clicked, it changes the scene to scene 2
        newBudgetB.setPrefWidth(150);
                
        Button summaryB = goToSceneFive(window, "SUMMARY", warningOne); // When this button is clicked, it changes the scene to scene 5        
        summaryB.setPrefWidth(150);
        
        Button trendsB = goToSceneSeven(window, "TRENDS", warningOne);  // When this button is clicked, it changes the scene to scene 7
        trendsB.setPrefWidth(150);        
        
        Button importB = features.yellowButton("IMPORT");               // Creates a yellow button called import
        importB.setPrefWidth(150);

        // set import button actions
        importB.setOnAction(action ->{            
            try {            
                warningOne.setText(financeFV.checkInputtedFile(stage, this.month)); // Uses FinanceFV's method to check for correct file input
            } catch (Exception error) {
                warningOne.setText("Action terminated.");                           // Let's the user know that their action has been terminated
            }        
        });                   

        // LAYOUTS AND ALIGNMENT OF GUI OBJECTS
        // title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, welcomeL, bee2);
        titleHB.setAlignment(Pos.CENTER);
        
        // left side buttons (column wise)
        VBox left = new VBox(20);
        left.getChildren().addAll(newBudgetB, summaryB);

        // right side buttons (column wise)
        VBox right = new VBox(20);
        right.getChildren().addAll(importB, trendsB);
        
        // hbox to combine left side and right side buttons
        HBox mainMenu = new HBox(20);
        mainMenu.getChildren().addAll(left, right);
        mainMenu.setAlignment(Pos.CENTER);
        
        // main screen to combine title, intro, menu, and warning text
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(titleHB, introL, mainMenu, warningOne);
        mainScreen.setAlignment(Pos.CENTER);
        
        // calling the borderpane method
        BorderPane bPane = features.showBorder(mainScreen);
        
        // add all the components to the scene
        one = new Scene(bPane, 1000, 500);
        
        return one;
    }    

    //-------------------- SCENE TWO BELOW --------------------//
    /**
     * Creates scene one: holds all the components of scene one
     * @param stage contains all the scenes 
     * @param cBMonths combo box that has all the months in a year
     * @return scene two 
     */
    public Scene showSceneTwo (Stage stage, ComboBox cBMonths){
        Scene two;  // initialize a scene to return

        //Title, bees, instructions label and formatting
        Label newBudgetL = features.setFont("SELECT MONTH", 25);

        bee = features.image();
        bee2 = features.image();
        
        Label instructionsL = features.setFont("Type in your desired category and press add to include it.", 12);         
        Label infoL = features.setFont("If you want default categories, do not add any categories.", 12);

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
        
        // Displays the user's entries 
        Text incomeCatT = new Text("");  
        Text expensesCatT = new Text(""); 

        //VBox and labels for default categories
        VBox defaultIncVB = new VBox(15);
        VBox defaultExpVB = new VBox(15);
        Label defaultIncHeaderL = new Label("Default Income Categories");
        defaultIncHeaderL.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        Label defaultExpHeaderL = new Label("Default Expense Categories");
        defaultExpHeaderL.setFont(Font.font("Verdana", FontWeight.BOLD, 15));

        //Displays default income categories 
        defaultIncVB.getChildren().add(defaultIncHeaderL);
        for (int i = 1; i < trends.defaultInc.length; i++){
            Label defaultIncL = new Label(trends.defaultInc[i]);
            defaultIncL.setFont(Font.font("Verdana", 12));
            defaultIncVB.getChildren().add(defaultIncL);
        }   

        //Displays default expenses categories 
        for (int i = 1; i < trends.defaultExp.length; i++){
            Label defaultExpL = new Label(trends.defaultExp[i]);
            defaultExpL.setFont(Font.font("Verdana", 12));
            defaultExpVB.getChildren().add(defaultExpL);
        }   
        defaultIncVB.getChildren().add(defaultExpHeaderL);

        //Vbox gathers the two default category sections together
        VBox defaultVB = new VBox(20);
        defaultVB.getChildren().addAll(defaultIncVB, defaultExpVB);

        //Set the chosen month to selected month from combo box
        getMonth(cBMonths);
        showTextTwo(cBMonths, incomeCatT, expensesCatT, warningT, stage); // Displays the text that is already stored (in the blue rectangles)

        //User selects either the add or delete button for income and expenses
        Button addIncCatB = features.yellowButton("ADD");        
        addIncCatB.setOnAction(action ->{                               // action for the add income category button
            boolean check;                           // boolean for input validation 
            check = onlyLetters(incCatTF.getText()); //INPUT VALIDATION - checks if its only letters and not symbols/numbers
            
            if(check && noChange){          // if their input is valid and the data has not been finalized in the csv yet
                warningT.setText("");                      
                showCategory(true, trends.incomeCatList, incCatTF, true, incomeCatT, expensesCatT); // arraylist is used so that they can keep adding categories          
            }
            else if(noChange == false){     // if their data has been finalized in the csv  
                warningT.setText("Sorry your categories have been finalized");  // this lets user know that they cannot change finalized categories
            }
            else{   // if their input is invalid
                warningT.setText("Please input a number. Do not include symbols or letters");                
            }            
        });

        Button deleteIncCatB = features.yellowButton("DELETE");
        deleteIncCatB.setOnAction(action ->{                               // action for the delete income category button
            if(noChange){               //  if the data has not been finalized in the csv yet
                showCategory(false, trends.incomeCatList, incCatTF, true, incomeCatT, expensesCatT);
            }
            else {                      // if their data has been finalized in the csv
                warningT.setText("Sorry your categories have been finalized");
            }            
        });

        Button addExpCatB = features.yellowButton("ADD");
        addExpCatB.setOnAction(action ->{                               // action for the add expense category button
            boolean check;       
            check = onlyLetters(expCatTF.getText()); //INPUT VALIDATION - checks if its only letters and not symbols/numbers
            
            if(check && noChange){          // if their input is valid and the data has not been finalized in the csv yet
                warningT.setText("");                      
                showCategory(true, trends.expenseCatList, expCatTF, false, incomeCatT, expensesCatT);                
            }
            else if(noChange == false){     // if their data has been finalized in the csv  
                warningT.setText("Sorry your categories have been finalized");
            }
            else{   // if their input is invalid
                warningT.setText("Please input a number. Do not include symbols or letters");                
            }                                
        });

        Button deleteExpCatB = features.yellowButton("DELETE");
        deleteExpCatB.setOnAction(action ->{                               // action for the delete expense category button
            if(noChange){           // if the data has not been finalized in the csv yet
                showCategory(false, trends.expenseCatList, expCatTF, false, incomeCatT, expensesCatT);
            }
            else {
                warningT.setText("Sorry your categories have been finalized");
            }            
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
                
        // Buttons
        Button mainMenuB = goToSceneOne(window, "MAIN MENU");    // when clicked, scene changes to scene one             
        Button nextPageB = features.yellowButton("NEXT");        
        nextPageB.setOnAction(action->{                          // action for going to next page button
            if(setMonth){              // if the user has clicked a month in the month combo box                        
                clicked = true;        // if the user has clicked the next button
                trends.incomeCatList = trends.defaultCategories(trends.incomeCatList, trends.defaultInc);       // checks if the user's entries arraylist is empty, that means they want default categories
                trends.expenseCatList = trends.defaultCategories(trends.expenseCatList, trends.defaultExp);            
                trends.income2D = trends.populateCat(trends.incomeCatList, trends.income2D, this.month);        //updates the 2d arrays with new categories from the user's entries arraylists
                trends.expense2D = trends.populateCat(trends.expenseCatList, trends.expense2D, this.month);                          
                // arrUpdate(trends.incomeCatList, trends.income2D, 0);                                            // 
                // arrUpdate(trends.expenseCatList, trends.expense2D, 0);
                sceneThree = showSceneThreeFour(window, "Anticipated");                                         // changes the scene to scene three
                stage.setScene(sceneThree);
            }
            else{   // when the user has NOT chosen a month in the month combo box yet
                warningT.setText("Please choose a month.");
            }
            
        });
        
        //HBox to gather main menu button and next page button
        HBox sceneButtons = new HBox(20);
        sceneButtons.getChildren().addAll(mainMenuB, nextPageB);
        sceneButtons.setAlignment(Pos.BOTTOM_RIGHT);
        
        VBox middleScreen = new VBox(20);
        middleScreen.getChildren().addAll(instructionsL, infoL, monthRow, incCatRow, expCatRow, catL, displayCat, warningT, sceneButtons);
        middleScreen.setAlignment(Pos.CENTER);
        
        HBox mainMiddleScreen = new HBox(20);
        mainMiddleScreen.getChildren().addAll(middleScreen, defaultVB);
        mainMiddleScreen.setAlignment(Pos.CENTER);


        //Making the overall screen
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(titleHB, mainMiddleScreen);
        mainScreen.setAlignment(Pos.CENTER);
        
        // call border mthd
        BorderPane bPane = features.showBorder(mainScreen);
        // bPane.setCenter(mainScreen);
    
        // add a scroll wheel
        ScrollPane scroll = features.showScrollPane(bPane);
        
        // put the components into the scene
        two = new Scene(scroll, 1000, 500);
        
        return two;
    }
    
    //-------------------- SCENE THREE/FOUR BELOW --------------------//
    public Scene showSceneThreeFour(Stage stage, String whichType){
        System.out.println(this.month);
        Scene threeFour;  // initialize a scene to return

        Label titleL;

        //initialize itles, bees, buttons, labels
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

        // Label spaceOne = features.spacing();
        // Label spaceTwo = features.spacing();

        TextField amntTFT = features.amntT();   // user inputs inc amnt
        TextField amntTFB = features.amntT();   // user inputs exp amnt

        // fillers to show their inputed amnt
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
            arrUpdate(incomeCatArr, trends.income2D, 0);
            arrUpdate(incomeAmtArr, trends.income2D, 1);
            arrUpdate(expenseCatArr, trends.expense2D, 0);
            arrUpdate(expenseAmtArr, trends.expense2D, 1);
            System.out.println("incomeCatArr is " + incomeCatArr);
            System.out.println("incomeAmtArr is " + incomeAmtArr);   
            System.out.println("expenseCatArr is " + expenseCatArr);
            System.out.println("expenseAmtArr is " + expenseAmtArr);      
            showDataThreeFour(incomeCatArr, incomeAmtArr, showIncCat, showIncAmt);
            showDataThreeFour(expenseCatArr, expenseAmtArr, showExpCat, showExpAmt);
        }
        else{
            cBInc = cBIncFour;
            cBExp = cBExpFour;
        }

        addTB.setOnAction(action -> {     
            boolean check;     
            boolean validTF;              
            validTF = onlyLetters((String) cBInc.getValue());
            check = isNumeric(amntTFT.getText()); //INPUT VALIDATION
            
            if(check && validTF){      
                warningT.setText("");                      
                showUserInput(true , true, cBInc, amntTFT, showIncCat, showIncAmt, showExpCat, showExpAmt, incomeCatArr, incomeAmtArr);       
            }
            else{
                warningT.setText("Please input a number. Do not include symbols or letters");                
            }
                    
        });
        addBB.setOnAction(action -> {
            boolean check;       
            boolean validTF;              
            validTF = onlyLetters((String) cBExp.getValue());
            check = isNumeric(amntTFB.getText());    

            if(check && validTF){      
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
        // conds for the btn of scene 3 and scene 4 
        lastRow.setAlignment(Pos.BOTTOM_RIGHT);
        
        if (whichType.equalsIgnoreCase("Anticipated")){            
            
            Button nextB = features.yellowButton("NEXT");
            nextB.setOnAction(action -> {  
                trends.income2D = trends.populate(incomeCatArr, incomeAmtArr, trends.income2D, trends.incomeCatList, 1, this.month);
                trends.expense2D = trends.populate(expenseCatArr, expenseAmtArr, trends.expense2D, trends.expenseCatList, 1, this.month); 
                System.out.println("CHECKING");
                for(int i = 0; i < trends.income2D.length; i++){
                    for(int j = 0; j < trends.income2D[0].length; j++){
                        System.out.print(trends.income2D[i][j] + ", ");
                        
                    } 
                    System.out.println();
                }
                //put into CSV
                System.out.println("going into toCSV");
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

            //put into CSV
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

        // // stack panes to show the user inputs on top of the rectangles
        StackPane stackPaneT = features.showSPane(showIncCat, showIncAmt);
        StackPane stackPaneB = features.showSPane(showExpCat, showExpAmt);

        //Gathers the title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, titleL, bee2);
        titleHB.setAlignment(Pos.CENTER);

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

        // put it all together in the vbox
        VBox mainScreen = new VBox(10);
        mainScreen.getChildren().addAll(titleHB, monthL, warningT, secondRow, thirdRow, stackPaneT, fourthRow, fifthRow, stackPaneB, lastRow);
        mainScreen.setAlignment(Pos.TOP_CENTER);

        // add a border
        BorderPane bPane = features.showBorder(mainScreen);

        // add a scroll wheel
        ScrollPane scroll = features.showScrollPane(bPane);

        // put the components into the scene
        threeFour = new Scene(scroll, 1000, 500);

        return threeFour; 
    }
    public boolean onlyLetters(String str) { //checks if the string only contains letters
        
        if(str == null || str.equals("")){
            return false;
        }
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

             
    Scene five;
    //Titles, bees, buttons, labels, comboBoxes
    //Label titleL = new Label("Budgeting App");
    System.out.println("scene 5");
    for(int i = 0; i < trends.income2D.length; i++){
        for(int j = 0; j < trends.income2D[0].length; j++){
            System.out.print(trends.income2D[i][j] + ", ");
            
        } 
        System.out.println();
    }  
    bee = features.image();
    bee2 = features.image();
    
    HBox titleHB = new HBox();
    titleHB.getChildren().addAll(bee, cBMonths, bee2);
    titleHB.setAlignment(Pos.CENTER);
    
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
    
    //Label - Act as line break
    //NOTE: REPLACE LABEL BLANKS WITH ACC CALC. VALUES
    Label labelBlank = new Label ("");
    Label labelBlank2 = new Label ("");
    Label labelBlank3 = new Label ("");
    Label labelBlank4 = new Label ("");
    Label labelBlank5 = new Label ("");
    Label labelBlank6 = new Label ("");

    Text warningT5 = new Text("");
    
    //Far left
    VBox vBoxFarLeft = new VBox(10);
    vBoxFarLeft.getChildren().addAll(labelIncome, labelTotals);
    
    //Prints out the elements inside labelCatArr on the far left side
    for (int i = 1; i < trends.income2D.length; i++){
        Label labelFarLeft = new Label(trends.income2D[i][0]);
        labelFarLeft.setFont(Font.font("Verdana", 12));
        vBoxFarLeft.getChildren().add(labelFarLeft);
    }    
    
    //Second left
    VBox vBoxSecondLeft = new VBox(10);
    vBoxSecondLeft.getChildren().addAll(labelAnticipated, labelBlank);
    vBoxSecondLeft.setAlignment(Pos.TOP_CENTER);
    
    for (int i = 1; i < trends.income2D.length; i++){
        if(!trends.income2D[i][0].equals("")){
            Label labelSecondLeft = new Label(trends.income2D[i][1]);
            labelSecondLeft.setFont(Font.font("Verdana", 12));
            vBoxSecondLeft.getChildren().add(labelSecondLeft);
        }        
    }   
    
    //Third left
    VBox vBoxThirdLeft = new VBox(10);
    vBoxThirdLeft.getChildren().addAll(labelActual, labelBlank2);
    vBoxThirdLeft.setAlignment(Pos.TOP_CENTER);
    
    for (int i = 1; i < trends.income2D.length; i++){
        if(!trends.income2D[i][0].equals("")){
        Label labelThirdLeft = new Label(trends.income2D[i][2]);
        labelThirdLeft.setFont(Font.font("Verdana", 12));
        vBoxThirdLeft.getChildren().add(labelThirdLeft);
        }
    }   
    
    //Fourth left
    VBox vBoxFourthLeft = new VBox(10);
    vBoxFourthLeft.getChildren().addAll(labelDiff, labelBlank3);
    vBoxFourthLeft.setAlignment(Pos.TOP_CENTER);
    
    for (int i = 1; i < trends.income2D.length; i++){
        if(!trends.income2D[i][0].equals("")){
        Label labelFourthLeft = new Label(trends.income2D[i][3]);
        labelFourthLeft.setFont(Font.font("Verdana", 12));
        vBoxFourthLeft.getChildren().add(labelFourthLeft);
        }
    }   
    
    HBox leftTable = new HBox(20);
    leftTable.getChildren().addAll(vBoxFarLeft, vBoxSecondLeft, vBoxThirdLeft, vBoxFourthLeft);
    
    //Fourth right
    VBox vBoxFourthRight = new VBox(10);
    vBoxFourthRight.getChildren().addAll(labelExpenses, labelTotals2);
    
    //Prints out the elements inside labelCatArr on the far left side
    for (int i = 1; i < trends.expense2D.length; i++){
        if(!trends.income2D[i][0].equals("")){
        Label labelFourthRight = new Label(trends.expense2D[i][0]);
        labelFourthRight.setFont(Font.font("Verdana", 12));
        vBoxFourthRight.getChildren().add(labelFourthRight);
        }
    }
    
    //Third right
    VBox vBoxThirdRight = new VBox(10);
    vBoxThirdRight.getChildren().addAll(labelAnticipated2, labelBlank6);
    vBoxThirdRight.setAlignment(Pos.TOP_CENTER);
    
    for (int i = 1; i < trends.expense2D.length; i++){
        if(!trends.expense2D[i][0].equals("")){
        Label labelThirdRight = new Label(trends.expense2D[i][1]);
        labelThirdRight.setFont(Font.font("Verdana", 12));
        vBoxThirdRight.getChildren().add(labelThirdRight);
        }
    }
    
    //Second right
    VBox vBoxSecondRight = new VBox(10);
    vBoxSecondRight.getChildren().addAll(labelActual2, labelBlank5);
    vBoxSecondRight.setAlignment(Pos.TOP_CENTER);
    
    for (int i = 1; i < trends.expense2D.length; i++){
        if(!trends.expense2D[i][0].equals("")){
            Label labelSecondRight = new Label(trends.expense2D[i][2]);
            labelSecondRight.setFont(Font.font("Verdana", 12));
            vBoxSecondRight.getChildren().add(labelSecondRight);
        }
    }
    
    //Far right
    VBox vBoxFarRight = new VBox(10);
    vBoxFarRight.getChildren().addAll(labelDiff2, labelBlank4);
    vBoxFarRight.setAlignment(Pos.TOP_CENTER);
    
    for (int i = 1; i < trends.expense2D.length; i++){
        if(!trends.expense2D[i][0].equals("")){
            Label labelFarRight = new Label(trends.expense2D[i][3]);
            labelFarRight.setFont(Font.font("Verdana", 12));
            vBoxFarRight.getChildren().add(labelFarRight);
        }
    }   
    
    //Buttons
    Button backB = goToSceneFour(stage, "BACK"); //back to scene 3
    if (this.month == null && clicked == false){
        warningT5.setText("Cannot go back, until you make a new budget");        
        System.out.println("month is: " + this.month + " clicked is " + clicked + " imported is " + financeFV.fullImport);
    }
    Button mainMenuB = goToSceneOne(window, "MAIN MENU");  
    Button nextPageB = goToSceneSix(window, "NEXT PAGE");
    
    HBox rightTable = new HBox(20);
    rightTable.getChildren().addAll(vBoxFourthRight, vBoxThirdRight, vBoxSecondRight, vBoxFarRight);
    
    HBox hBoxMiddle = new HBox(20);
    hBoxMiddle.getChildren().addAll(leftTable, rightTable);
    hBoxMiddle.setAlignment(Pos.CENTER);
    
    HBox buttonsHB = new HBox(10);
    buttonsHB.getChildren().addAll(warningT5, backB, mainMenuB, nextPageB);
    buttonsHB.setAlignment(Pos.BOTTOM_RIGHT);
    
    VBox mainScreen = new VBox(10);
    mainScreen.getChildren().addAll(titleHB, hBoxMiddle, buttonsHB);
    mainScreen.setAlignment(Pos.CENTER);
    
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
    double sumAccIncome = trends.sumAccCalculator(trends.income2D); //ADD VALUES BY PASSING IT INTO THIS METHOD
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
    
    //If the acc income or end balance is greater than a certain value (100, 1500, 10000, 100000),
    //the rectangles will be divided by 100 so they fit the screen
    if (sumAccIncome > 100000 || endBalance > 100000){
      sumAccIncomeR = new Rectangle (50, sumAccIncome/10000, babyBlue);
      endBalanceR = new Rectangle (50, endBalance/10000, darkBlue);
    }
    else if (sumAccIncome > 10000 || endBalance > 10000){
      sumAccIncomeR = new Rectangle (50, sumAccIncome/1000, babyBlue);
      endBalanceR = new Rectangle (50, endBalance/1000, darkBlue);
    }
    else if(sumAccIncome > 1500 || endBalance > 1500){
      sumAccIncomeR = new Rectangle (50, sumAccIncome/100, babyBlue);
      endBalanceR = new Rectangle (50, endBalance/100, darkBlue);
    }
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
    
    //StackPane summaryLeftStack = new StackPane();
    //summaryLeftStack.getChildren().addAll(new Rectangle(300, 150, babyBlue), summaryLeftHB);
    
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
    
    //If the values in anticpated and actual income or expenses are too big, all the rectangle
    //sizes will be shrunk proportionately. For ex, when the values are greater than 100, 1500, 10000, 100000
    if (sumAntIncome > 100000 || sumAccIncome > 100000 || sumAntExpense > 100000 || sumAccExpense > 100000){
      sumAntIncR = new Rectangle(sumAntIncome/10000, 20, white);
      sumAccIncR = new Rectangle(sumAccIncome/10000, 20, white);
      sumAntExpR = new Rectangle(sumAntExpense/10000, 20, white);
      sumAccExpR = new Rectangle(sumAccExpense/10000, 20, white);
    }
    else if (sumAntIncome > 10000 || sumAccIncome > 10000 || sumAntExpense > 10000 || sumAccExpense > 10000){
      sumAntIncR = new Rectangle(sumAntIncome/1000, 20, white);
      sumAccIncR = new Rectangle(sumAccIncome/1000, 20, white);
      sumAntExpR = new Rectangle(sumAntExpense/1000, 20, white);
      sumAccExpR = new Rectangle(sumAccExpense/1000, 20, white);
    }
    else if (sumAntIncome > 1500 || sumAccIncome > 1500 || sumAntExpense > 1500 || sumAccExpense > 1500){
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
    
    //Spacing to be used for below in HBox, allRect
    Label spacing = new Label("");
    Label spacing2 = new Label("");
    
    //Gathers all rectangles
    HBox allRect = new HBox(20);
    allRect.getChildren().addAll(spacing, incomeSummaryVB, incAntAccValueHB, incRectVB, expenseSummaryVB, expAntAccValueHB, expRectVB, spacing2);
    allRect.setAlignment(Pos.CENTER);
    
    //Gathers everything to make the whole section an entire entity
    VBox summaryBottomVB = new VBox(20);
    summaryBottomVB.getChildren().addAll(space5, allRect, space6);
    summaryBottomVB.setBackground(new Background(new BackgroundFill(darkBlue, CornerRadii.EMPTY, Insets.EMPTY)));
    
    //-------------------- SCENE SIX COMBINE ALL SECTIONS BELOW --------------------//
    //Initialize buttons
    Text filler = new Text("");
    Button mainMenuB = goToSceneOne(window, "MAIN MENU"); 
    Button backB = goToSceneFive(window, "BACK", filler);
    
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

        Button main = goToSceneOne(stage, "MAIN MENU");
        //Button back = goToSceneSix(stage, "BACK");

        PieChart piechart = showPieChart();        

        LineChart<String,String> lineChart = showlLineChart();

        HBox graphs = new HBox(10);
        graphs.getChildren().addAll(piechart , lineChart);

        HBox buttons = new HBox(10);
        buttons.getChildren().addAll(main);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);

        VBox mainScreen = new VBox(10);
        mainScreen.getChildren().addAll(cBMonths, graphs, buttons);
        mainScreen.setAlignment(Pos.CENTER);

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
    public Button goToSceneFive(Stage stage, String sceneName, Text warningT){
        Button scene5B = features.yellowButton(sceneName);
        scene5B.setOnAction(action -> {
            // financeFV.fullImport = financeFV.checkImport();
            System.out.println("month is: " + this.month + " clicked is " + clicked + " imported is " + financeFV.fullImport);
            if((financeFV.fullImport == true) || (this.month != null && clicked == true)){
                // if(this.month != null && clicked == true){
                    System.out.println("going to scene 5");
                    financeFV.repopulate(this.month, trends.income2D, "income");
                    financeFV.repopulate(this.month, trends.expense2D, "expense");
                    trends.populateDiff(trends.income2D);
                    trends.populateDiff(trends.expense2D);
                    cBMonths = features.comboBoxMonths();
                    sceneFive = showSceneFive(window, cBMonths); // summary
                    stage.setScene(sceneFive);
                    warningT.setText(" ");
                // }
            }
                                            
            else{
                noData(warningT);
            }
        });
        return scene5B;
    }
    public Button goToSceneSix(Stage stage, String sceneName){
        Button scene6B = features.yellowButton(sceneName);        
        scene6B.setOnAction(action -> {            
            if(this.month != null && (clicked == true || financeFV.fullImport == true)){
                cBMonths = features.comboBoxMonths();
                sceneSix = showSceneSix(window, cBMonths); // trends
                stage.setScene(sceneSix);
            }
        });
        return scene6B;
    }
    public Button goToSceneSeven(Stage stage, String sceneName, Text warningT){
        Button scene7B = features.yellowButton(sceneName);        
        scene7B.setOnAction(action -> {            
            if(this.month != null && (clicked == true || financeFV.fullImport == true)){
                cBMonths = features.comboBoxMonths();
                sceneSeven = showSceneSeven(window, cBMonths); // trends
                stage.setScene(sceneSeven);
                warningT.setText(" ");
            }
            else{
                noData(warningT);
            }
        });
        return scene7B;
    }
    public void noData(Text warningT){
        // buttonB.setOnAction(action ->{            
            if (this.month == null && clicked == false || financeFV.fullImport == false){
                    warningT.setText("Please make a new budget first or import csvs");
                    System.out.println("no data is working");
                    System.out.println("month is: " + this.month + " clicked is " + clicked + " imported is " + financeFV.fullImport);
            }                    
        // });
    }
    // ---------------------------------------------------------------- //

    //month things
    public void getMonth(ComboBox cBMonths){
        cBMonths.setOnAction(action ->{
            this.month = (String)cBMonths.getValue();
        });
    }
    
    public Popup showWarning(){
      Popup warning = new Popup();
      Label warnLabel = features.setFont("Please select a month with \n previous data from your csv.", 15);
    //   warnLabel.setAlignment(Pos.BOTTOM_CENTER);
      // warnLabel.setSkin(babyBlue);

      Button exit = new Button("X");
      exit.setOnAction(action -> {
        warning.hide();
      });

      HBox mainPopup = new HBox(warnLabel, exit);
      mainPopup.setBackground(new Background(new BackgroundFill(yellow, CornerRadii.EMPTY, Insets.EMPTY)));

      warning.getContent().addAll(mainPopup);

      return warning;
    }

    //set default combobox month name display
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
    public ArrayList<String> arrUpdate(ArrayList<String> arrList, String[][] twoDArr, int col){        
        String startCoords = financeFV.checkForMonth(this.month);
        if(!startCoords.equals(":)")){
            financeFV.repopulate(this.month, trends.income2D, "income"); // repopulates the 2d array
            financeFV.repopulate(this.month, trends.expense2D, "expense");
            for(int i = 1; i < trends.income2D.length; i++){        
                arrList.add(twoDArr[i][col]);                           // populates the arraylist that we print out with what's already in the 2d array
            }
            
        }
        return arrList;
    }
    public void showDataThreeFour(ArrayList<String> catArr, ArrayList<String> amtArr, Text showCat, Text showAmt){
        // initialize strings                        
        String stringCat, stringAmt;
        String printCat = "";
        String printAmt = "";
    

        // conds if scene 3 or scene 4
        for(int i = 0; i < catArr.size(); i ++){
            // set string as the array element
            if(!catArr.get(i).equals("")){
                stringCat = catArr.get(i) + "\n";
                stringAmt = amtArr.get(i) + "\n";
                // concanate
                printCat += stringCat;
                printAmt += stringAmt;
            }
            
        }
        System.out.println(catArr + " : " + amtArr);  // testing DELETE LT
        
        // conds if it is income or exp 
        // set text to the user inputs
        showCat.setText("Categories : \n" + printCat);
        showAmt.setText("Amount : \n" + printAmt);
        System.out.println("inc cat : " + printCat + " inc amt : " + printAmt);     //testng DELETE LTR
                                
        
    }

    public void showTextTwo(ComboBox cBMonths, Text incomeCatT, Text expenseCatT, Text warningT, Stage stage){
        cBMonths.setOnAction(action ->{
            this.month = (String)cBMonths.getValue();            
            setMonth = true;
            financeFV.fileName = "income.csv";
            String startCoords = financeFV.checkForMonth(this.month);
            if(startCoords.equals(":)")){                               // is not in the csv yet
                System.out.println("list has been cleared");
                trends.incomeCatList.clear();
                trends.expenseCatList.clear();
                noChange = true;
                sceneTwo = showSceneTwo(window, cBMonths);
                stage.setScene(sceneTwo);
            }
            else{
                System.out.println("there is no change");
                noChange = false;
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