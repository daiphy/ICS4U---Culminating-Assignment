/**
 * Daiphy, Hilary, Jane, Rachel
 * Teacher: Mr. Ho
 * ICS4U Culminating Project
 * January 24, 2022
 * AppLayoutFV.java
 */

//********** IMPORTS **********//
import java.text.DecimalFormat;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.chart.*;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
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

//********** CLASS **********//
public class AppLayoutFV extends Application{

    //-------------------- GLOBAL VARIABLES --------------------//
    Stage window;
    private Scene sceneOne, sceneTwo, sceneThree, sceneFour, sceneFive, sceneSix, sceneSeven;
    private ImageView bee, bee2;
    public ComboBox<String> cBMonths;
    public String month;
    public boolean clicked;
    public boolean monthSet;
    public boolean noChange = true;

    //Initialize objects -> Trends/LayoutFeatures/Finance
    public Trends trends = new Trends();
    public LayoutFeatures features = new LayoutFeatures(); 
    public FinanceFV financeFV = new FinanceFV();

    //-------------------- CONSTRUCTOR --------------------// 
    public AppLayoutFV(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        
        // set global variable
        // UH SINCE THIS IS GLOBAL ATTEMPT USING "WINDOW" AS THE STAGE INSTEAD OF RUNNING STAGE THROUGH PARAMS?
        window = primaryStage;

        // calling the first scene
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
    public Scene showSceneOne (Stage scene){

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
        Button newBudgetB = goToSceneTwo(window, "SELECT MONTH", warningOne); //scene 2
        newBudgetB.setPrefWidth(150);
        
        //Button summaryB = features.yellowButton("SUMMARY"); //scene 5
        Button summaryB = goToSceneFive(window, "SUMMARY", warningOne); //scene 5
        summaryB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        summaryB.setPrefWidth(150);
        
        Button trendsB = goToSceneSeven(window, "TRENDS", warningOne); //scene 7
        trendsB.setPrefWidth(150);
        
        Button importB = features.yellowButton("IMPORT");
        importB.setPrefWidth(150);

        // set button actions
        importB.setOnAction(action ->{
            try {
            // if the user does not input .csv, the warning text will be shown
            warningOne.setText(financeFV.checkInputtedFile(scene, this.month));
            } catch (Exception error) {
                warningOne.setText("Action terminated.");
            }
        
        });   
        
        // LAYOUT AND ALIGNMENT OF GUI OBJECTS
        // formatting using hbox, vbox and borderpane
        
        // title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, welcomeL, bee2);
        titleHB.setAlignment(Pos.CENTER);
        
        // left side buttons
        VBox left = new VBox(20);
        left.getChildren().addAll(newBudgetB, summaryB);

        // right side buttons
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
     * Creates scene two: holds all the components of scene two
     * @param stage contains all the scenes 
     * @param cBMonths combo box that has all the months in a year
     * @param warningT adjusts warning text on the first scene     
     * @return scene two 
     */
    public Scene showSceneTwo (Stage stage, ComboBox<String>cBMonths, Text warningOne){
        Scene two;  // initialize a scene to return

        //Title, bees, instructions label and formatting
        Label newBudgetL = features.setFont("SELECT MONTH", 25);

        bee = features.image();
        bee2 = features.image();
        
        Label instructionsL = features.setFont("Type in your desired category and press ADD to include it.", 12); 
        Label instructionsL2 = features.setFont("If you would like to delete one, type in your category and press the DELETE button.", 12); 
        Label infoL = features.setFont("If you want default categories, do not add any categories and click on the NEXT button.", 12);

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
        Label incomeL = features.setFont("INCOME", 15);
        Label expensesL = features.setFont("EXPENSE", 15);
        
        // Display the user's entries
        Text incomeCatT = new Text(""); //This will depend on the categories (modify later)
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
        for (int i = 0; i < trends.defaultInc.length; i++){
            Label defaultIncL = new Label(trends.defaultInc[i]);
            defaultIncL.setFont(Font.font("Verdana", 12));
            defaultIncVB.getChildren().add(defaultIncL);
        }   

        //Displays default expenses categories 
        for (int i = 0; i < trends.defaultExp.length; i++){
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
        showTextTwo(cBMonths, incomeCatT, expensesCatT, stage);

        //User selects either the add or delete button for income and expenses
        Button addIncCatB = features.yellowButton("ADD");  
        addIncCatB.setOnAction(action ->{                               // action for the add income category button
            boolean check;                           // boolean for input validation 
            check = features.onlyLetters(incCatTF.getText()); //INPUT VALIDATION - checks if its only letters and not symbols/numbers
            
            if(check && noChange){          // if their input is valid and the data has not been finalized in the csv yet
                warningT.setText("");                      
                features.showCategory(true, trends.incomeCatList, incCatTF, true, incomeCatT, expensesCatT); // arraylist is used so that they can keep adding categories          
            }
            else if(noChange == false){     // if their data has been finalized in the csv  
                warningT.setText("Sorry your categories have been finalized");  // this lets user know that they cannot change finalized categories
            }
            else{   // if their input is invalid
                warningT.setText("Please input a letters ONLY. Do not include symbols, spaces or numbers");                
            }            
        });      
      
        Button deleteIncCatB = features.yellowButton("DELETE");
        deleteIncCatB.setOnAction(action ->{                               // action for the delete income category button
            if(noChange){               //  if the data has not been finalized in the csv yet
                features.showCategory(false, trends.incomeCatList, incCatTF, true, incomeCatT, expensesCatT);
            }
            else {                      // if their data has been finalized in the csv
                warningT.setText("Sorry your categories have been finalized");
            }            
        });

        Button addExpCatB = features.yellowButton("ADD");
        addExpCatB.setOnAction(action ->{                               // action for the add expense category button
            boolean check;       
            check = features.onlyLetters(expCatTF.getText()); //INPUT VALIDATION - checks if its only letters and not symbols/numbers
            
            if(check && noChange){          // if their input is valid and the data has not been finalized in the csv yet
                warningT.setText("");                      
                features.showCategory(true, trends.expenseCatList, expCatTF, false, incomeCatT, expensesCatT);                
            }
            else if(noChange == false){     // if their data has been finalized in the csv  
                warningT.setText("Sorry your categories have been finalized");
            }
            else{   // if their input is invalid
                warningT.setText("Please input a letters ONLY. Do not include symbols, spaces or numbers");               
            }                                
        });

        Button deleteExpCatB = features.yellowButton("DELETE");
        deleteExpCatB.setOnAction(action ->{                               // action for the delete expense category button
            if(noChange){           // if the data has not been finalized in the csv yet
                features.showCategory(false, trends.expenseCatList, expCatTF, false, incomeCatT, expensesCatT);
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
        displayCat.setBackground(new Background(new BackgroundFill(features.babyBlue, CornerRadii.EMPTY, Insets.EMPTY)));
                
        // Buttons
        Button mainMenuB = goToSceneOne(window, "MAIN MENU", warningOne);    // when clicked, scene changes to scene one             
        Button nextPageB = features.yellowButton("NEXT");        
        nextPageB.setOnAction(action->{                          // action for going to next page button
            if(monthSet){              // if the user has clicked a month in the month combo box                        
                clicked = true;        // if the user has clicked the next button
                
                trends.incomeCatList = trends.defaultCategories(trends.incomeCatList, trends.defaultInc);       // checks if the user's entries arraylist is empty, that means they want default categories
                trends.expenseCatList = trends.defaultCategories(trends.expenseCatList, trends.defaultExp); 

                trends.income2D = trends.populateCat(trends.incomeCatList, trends.income2D, this.month);        //updates the 2d arrays with new categories from the user's entries arraylists
                trends.expense2D = trends.populateCat(trends.expenseCatList, trends.expense2D, this.month); 

                trends.income2D = trends.refreshArr(trends.income2D);                                           // refreshes the 2D array to 0.0 (everything except the categories)
                trends.expense2D = trends.refreshArr(trends.expense2D);

                sceneThree = showSceneThreeFour(window, "Anticipated", warningOne);                                         // changes the scene to scene three
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
        middleScreen.getChildren().addAll(instructionsL, instructionsL2, infoL, monthRow, incCatRow, expCatRow, catL, displayCat, warningT, sceneButtons);
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
    
        // add a scroll wheel
        ScrollPane scroll = features.showScrollPane(bPane);
        
        // put the components into the scene
        two = new Scene(scroll, 1000, 500);
        
        return two;
    }  
    //-------------------- SCENE THREE/FOUR BELOW --------------------//
    /**
     * Creates scene three and four: holds all the components
     * Since scene three and four have the same layout, we were able to methodize the scenes
     * @param stage contains all the scenes
     * @param whichType String of whether we are working in the anticipated (scene 3) or actual (scene 4)
     * @param warningT adjusts warning text on the first scene
     * @return scene three or scene three
     */
    public Scene showSceneThreeFour(Stage stage, String whichType, Text warningOne){

        Scene threeFour;  // initialize a scene to return

        Label titleL;   // initialize titles

        //initialize itles, bees, buttons, labels
        //Depending on the scene, the title will be different
        //Scene 3 will be "Anticipated" and scene 4 will be "Plan"
        if(whichType.equals("Anticipated")){
            titleL = features.setFont("Plan", 25);
        }
        else{
            titleL = features.setFont("Transactions", 25);
        }
        
        // bees
        bee = features.image();
        bee2 = features.image();

        // initialize labels and formatting. labels will display header/titles
        Label monthL = features.setFont(this.month, 18);
        Label typeL = features.setFont(whichType, 16); // anticipated or actual
        Label incL = features.setFont("INCOME", 15);
        Label expL = features.setFont("EXPENSE", 15);
        Label catTL = features.setFont("Category : ", 12);
        Label catBL = features.setFont("Category : ", 12);
        Label amntTL = features.setFont("Amount : ", 12);
        Label amntBL = features.setFont("Amount : ", 12);

        // text fields for user input amounts
        TextField amntTFT = features.amntT();   // user inputs inc amnt
        TextField amntTFB = features.amntT();   // user inputs exp amnt

        // fillers to show their inputed amnt
        Text showIncCat = new Text();
        Text showIncAmt = new Text();
        Text showExpCat = new Text();
        Text showExpAmt = new Text();

        // warning text for when the user forgets to input something importan
        Text warningT = new Text("");
        warningT.setTextAlignment(TextAlignment.CENTER);

        // Arraylists
        ArrayList<String> incomeCatArr = new ArrayList<String>();
        ArrayList<String> incomeAmtArr = new ArrayList<String>(); 
        ArrayList<String> expenseCatArr = new ArrayList<String>();
        ArrayList<String> expenseAmtArr = new ArrayList<String>();

        // buttons that allow the user to add or delete certain inputs
        Button addTB = features.yellowButton("ADD"); // TB (top half of the scene) -> income
        Button addBB = features.yellowButton("ADD"); // BB (bottom half of the scene) -> expense
        Button delTB = features.yellowButton("DELETE"); // TB (top half of the scene) -> income
        Button delBB = features.yellowButton("DELETE"); // BB (bottom half of the scene) -> expense        

        // comboboxes display the categories the user has for income and expenses
        ComboBox<String> cBInc = features.comboBoxIncome(trends.income2D);
        ComboBox<String> cBExp = features.comboBoxExpense(trends.expense2D);

        // conditions if user is on scene 3
        if(whichType.equalsIgnoreCase("ANTICIPATED")){
            arrUpdate(incomeCatArr, trends.income2D, 0, this.month);
            arrUpdate(incomeAmtArr, trends.income2D, 1, this.month);
            arrUpdate(expenseCatArr, trends.expense2D, 0, this.month);
            arrUpdate(expenseAmtArr, trends.expense2D, 1, this.month);    
        }
        // conditions if user scene 4 ("actual" scene)
        else{
            arrUpdate(incomeCatArr, trends.income2D, 0, this.month);
            arrUpdate(incomeAmtArr, trends.income2D, 2, this.month);
            arrUpdate(expenseCatArr, trends.expense2D, 0, this.month);
            arrUpdate(expenseAmtArr, trends.expense2D, 2, this.month);     
        }
        features.showDataThreeFour(incomeCatArr, incomeAmtArr, showIncCat, showIncAmt);
        features.showDataThreeFour(expenseCatArr, expenseAmtArr, showExpCat, showExpAmt);

        // actions for when the user clicks "add" to an input
        addTB.setOnAction(action -> {     
            boolean check;     
            boolean validTF;              
            validTF = features.onlyLetters((String) cBInc.getValue());
            check = LayoutFeatures.isNumeric(amntTFT.getText()); //INPUT VALIDATION
            
            // validates user inputs if they put something invalid like letters
            if(check && validTF){      
                warningT.setText("");                      
                features.showUserInput(true , true, cBInc, amntTFT, showIncCat, showIncAmt, showExpCat, showExpAmt, incomeCatArr, incomeAmtArr);       
            }
            else if(validTF){
                warningT.setText("Please input a number. Do not include symbols, spaces, or letters");   
            }
            else{
                warningT.setText("Please select a category");                  
            }         
        });
        // actions for when the user clicks "add" to add an input
        addBB.setOnAction(action -> {
            boolean check;       
            boolean validTF;              
            validTF = features.onlyLetters((String) cBExp.getValue());
            check = LayoutFeatures.isNumeric(amntTFB.getText());    

            // validates user inputs if they put something invalid like letters
            if(check && validTF){      
                warningT.setText("");       
                features.showUserInput(true, false, cBExp, amntTFB, showIncCat, showIncAmt, showExpCat, showExpAmt, expenseCatArr, expenseAmtArr);
            }
            else if(validTF){
                warningT.setText("Please input a number. Do not include symbols, spaces, or letters");   
            }
            else{
                warningT.setText("Please select a category");                  
            }         
            
        });
        // actions for when the user clicks "delete" to delete an input
        delTB.setOnAction(action -> {                                     
            features.showUserInput(false, true, cBInc, amntTFT, showIncCat, showIncAmt, showExpCat, showExpAmt, incomeCatArr, incomeAmtArr);
                    
        });
        delBB.setOnAction(action -> {                                       
            features.showUserInput(false, false, cBExp, amntTFB, showIncCat, showIncAmt, showExpCat, showExpAmt, expenseCatArr, expenseAmtArr);                                       
        });

                
        // FORMATTING
        HBox lastRow = new HBox(10);
        // conds for the btn of scene 3 and scene 4 
        lastRow.setAlignment(Pos.BOTTOM_RIGHT);
        
        // conds for scene 3
        if (whichType.equalsIgnoreCase("Anticipated")){            
            //Initialize button to go to next scene and add action. Action starts the calculations and populating arrays
            Button nextB = features.yellowButton("NEXT");
            nextB.setOnAction(action -> {     
                trends.income2D = trends.populate(incomeCatArr, incomeAmtArr, trends.income2D, trends.incomeCatList, 1, this.month);        // populates the anticipated column of the 2d array
                trends.expense2D = trends.populate(expenseCatArr, expenseAmtArr, trends.expense2D, trends.expenseCatList, 1, this.month); 
                //put into CSV
                financeFV.toCSV(trends.income2D, "income", this.month);              
                financeFV.toCSV(trends.expense2D, "expense", this.month);
                
                // set scene for the correct page
                cBMonths = features.comboBoxMonths();
                sceneFour = showSceneThreeFour(window, "Actual", warningOne); // transactions
                stage.setScene(sceneFour);
            });
            // buttons to go back to the main menu
            Button mainMenuB = goToSceneOne(stage, "MAIN MENU", warningOne);
            lastRow.getChildren().addAll(mainMenuB, nextB); //gather buttons
        }
        else{   //conds for scene 4
            // initialize button to go to next scene and add action. action starts the calculations and populating arrays
            Button nextB = features.yellowButton("NEXT");
            nextB.setOnAction(action -> {   
                trends.income2D = trends.populate(incomeCatArr, incomeAmtArr, trends.income2D, trends.incomeCatList, 2, this.month);
                trends.expense2D = trends.populate(expenseCatArr, expenseAmtArr, trends.expense2D, trends.expenseCatList, 2, this.month); 
             
                trends.populateDiff(trends.income2D, "income");
                trends.populateDiff(trends.expense2D, "expense");
           
                //put into CSV
                financeFV.toCSV(trends.income2D, "income", this.month);
                financeFV.toCSV(trends.expense2D, "expense", this.month);

                // set scene for the correct page
                cBMonths = features.comboBoxMonths();
                sceneFive = showSceneFive(window, cBMonths);
                stage.setScene(sceneFive);
            });

            // go (TO GO BACK AND MAIN MENU)
            Button backB = goToSceneThree(stage, "BACK", warningOne);
            Button mainMenuB = goToSceneOne(stage, "MAIN MENU", warningOne);
            lastRow.getChildren().addAll(mainMenuB, backB, nextB); // gathers buttons
        }

        // stack panes to show the user inputs on top of the rectangles
        StackPane stackPaneT = features.showSPane(showIncCat, showIncAmt);
        StackPane stackPaneB = features.showSPane(showExpCat, showExpAmt);

        //Gathers the title and bees
        HBox titleHB = new HBox(20);
        titleHB.getChildren().addAll(bee, titleL, bee2);
        titleHB.setAlignment(Pos.CENTER);

        // plan vs transactions and income lbls
        VBox firstCol = new VBox(10);
        firstCol.getChildren().addAll(typeL, incL);

        // gathers the components in second row
        HBox secondRow = new HBox(50);
        secondRow.getChildren().addAll(firstCol);

        // gathers the components in third row
        HBox thirdRow = new HBox(50);
        thirdRow.getChildren().addAll(catTL, cBInc, amntTL, amntTFT, addTB, delTB);
        thirdRow.setAlignment(Pos.CENTER);

        // gather the components in fourth row 
        HBox fourthRow = new HBox(50);
        fourthRow.getChildren().addAll(expL);

        // gather the components in fifth row
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
    //-------------------- SCENE FIVE BELOW --------------------//
    /**
     * 
     * @param stage contains all the scenes
     * @param cBMonths combo box that has all the months in a year
     * @return scene five
     */
    public Scene showSceneFive(Stage stage, ComboBox<String>cBMonths){

    features.setMonth(cBMonths, this.month, trends.income2D);
    //Set the chosen month to selected month from combo box and display data
    //for scene 5, to change the data shown
    cBMonths.setOnAction(action ->{
        this.month = (String)cBMonths.getValue();
        if(financeFV.checkForMonth(this.month).equals(":)")){
            Popup warning = features.showWarning();
            warning.show(stage);
        }
        else{
            financeFV.repopulate(this.month, trends.income2D, "income");
            financeFV.repopulate(this.month, trends.expense2D, "expense");
            sceneFive = showSceneFive(window, cBMonths);
            stage.setScene(sceneFive);
        }
        
    });

             
    //Initialize scene five     
    Scene five;
    
    //Bees 
    bee = features.image();
    bee2 = features.image();
    
    //HBox gathers the bees and combobox for title
    HBox titleHB = new HBox();
    titleHB.getChildren().addAll(bee, cBMonths, bee2);
    titleHB.setAlignment(Pos.CENTER);
    
    //Initialize labels and formatting
    Label labelIncome = features.setFont("INCOME", 15);
    Label labelExpenses = features.setFont("EXPENSE", 15);
    Label labelAnticipated = features.setFont("ANTICIPATED", 15); 
    Label labelAnticipated2 = features.setFont("ANTICIPATED", 15); 
    Label labelActual = features.setFont("ACTUAL", 15); 
    Label labelActual2 = features.setFont("ACTUAL", 15);
    Label labelDiff = features.setFont("DIFFERENCE", 15); 
    Label labelDiff2 = features.setFont("DIFFERENCE", 15); 
    Label labelTotals = features.setFont("TOTAL", 15);
    Label labelTotals2 = features.setFont("TOTAL", 15);
    
    //Initialize label that act as line breaks (formatting)
    Label labelBlank = new Label ("");
    Label labelBlank2 = new Label ("");
    Label labelBlank3 = new Label ("");
    Label labelBlank4 = new Label ("");
    Label labelBlank5 = new Label ("");
    Label labelBlank6 = new Label ("");

    Text warningT5 = new Text("");
    
    //Far left - Gathers the first column (counting left to right)
    VBox vBoxFarLeft = new VBox(10);
    vBoxFarLeft.getChildren().addAll(labelIncome, labelTotals);
    
    //Prints out the elements inside income arr on the far left side
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
        if(!trends.income2D[i][0].equals("")){
            Label labelSecondLeft = new Label(trends.income2D[i][1]);
            labelSecondLeft.setFont(Font.font("Verdana", 12));
            vBoxSecondLeft.getChildren().add(labelSecondLeft);
        }        
    }   
    vBoxSecondLeft.setAlignment(Pos.TOP_CENTER);

    //Third left - Gathers the third column 
    VBox vBoxThirdLeft = new VBox(10);
    vBoxThirdLeft.getChildren().addAll(labelActual, labelBlank2);

    //Prints out the elements inside the income array 
    for (int i = 1; i < trends.income2D.length; i++){
        if(!trends.income2D[i][0].equals("")){
            Label labelThirdLeft = new Label(trends.income2D[i][2]);
            labelThirdLeft.setFont(Font.font("Verdana", 12));
            vBoxThirdLeft.getChildren().add(labelThirdLeft);
        }
    }   
    vBoxThirdLeft.setAlignment(Pos.TOP_CENTER);

    //Fourth left - Gathers the fourth column
    VBox vBoxFourthLeft = new VBox(10);
    vBoxFourthLeft.getChildren().addAll(labelDiff, labelBlank3);

    //Prints out the elements inside the income array
    for (int i = 1; i < trends.income2D.length; i++){
        if(!trends.income2D[i][0].equals("")){
            Label labelFourthLeft = new Label(trends.income2D[i][3]);
            labelFourthLeft.setFont(Font.font("Verdana", 12));
            vBoxFourthLeft.getChildren().add(labelFourthLeft);
        }
    }   
    vBoxFourthLeft.setAlignment(Pos.TOP_CENTER);

    //Fourth right - Gathers the fifth column 
    VBox vBoxFourthRight = new VBox(10);
    vBoxFourthRight.getChildren().addAll(labelExpenses, labelTotals2);

    //Prints out the elements inside expense array
    for (int i = 1; i < trends.expense2D.length; i++){
        if(!trends.income2D[i][0].equals("")){
            Label labelFourthRight = new Label(trends.expense2D[i][0]);
            labelFourthRight.setFont(Font.font("Verdana", 12));
            vBoxFourthRight.getChildren().add(labelFourthRight);
        }
    }

    //Third right - Gathers the sixth column
    VBox vBoxThirdRight = new VBox(10);
    vBoxThirdRight.getChildren().addAll(labelAnticipated2, labelBlank6);

    //Prints out the elements inside expense array
    for (int i = 1; i < trends.expense2D.length; i++){
        if(!trends.expense2D[i][0].equals("")){
            Label labelThirdRight = new Label(trends.expense2D[i][1]);
            labelThirdRight.setFont(Font.font("Verdana", 12));
            vBoxThirdRight.getChildren().add(labelThirdRight);
        }
    }
    vBoxThirdRight.setAlignment(Pos.TOP_CENTER);

    //Second right - Gathers the seventh column 
    VBox vBoxSecondRight = new VBox(10);
    vBoxSecondRight.getChildren().addAll(labelActual2, labelBlank5);

    //Prints out the elements inside expense array
    for (int i = 1; i < trends.expense2D.length; i++){
        if(!trends.expense2D[i][0].equals("")){
            Label labelSecondRight = new Label(trends.expense2D[i][2]);
            labelSecondRight.setFont(Font.font("Verdana", 12));
            vBoxSecondRight.getChildren().add(labelSecondRight);
        }
    }
    vBoxSecondRight.setAlignment(Pos.TOP_CENTER);

    //Far right - Gathers the eighth column
    VBox vBoxFarRight = new VBox(10);
    vBoxFarRight.getChildren().addAll(labelDiff2, labelBlank4);

    //Prints out the elements inside expense array
    for (int i = 1; i < trends.expense2D.length; i++){
        if(!trends.expense2D[i][0].equals("")){
            Label labelFarRight = new Label(trends.expense2D[i][3]);
            labelFarRight.setFont(Font.font("Verdana", 12));
            vBoxFarRight.getChildren().add(labelFarRight);
        }
    }   
    vBoxFarRight.setAlignment(Pos.TOP_CENTER);

    //Buttons
    Button backB = goToSceneFour(stage, "BACK", warningT5); //back to scene 3
    Button mainMenuB = goToSceneOne(window, "MAIN MENU", warningT5);  
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
    buttonsHB.getChildren().addAll(warningT5, backB, mainMenuB, nextPageB);
    buttonsHB.setAlignment(Pos.BOTTOM_RIGHT);

    //Combines the title, the entire table and the buttons
    VBox mainScreen = new VBox(10);
    mainScreen.getChildren().addAll(titleHB, hBoxMiddle, buttonsHB);
    mainScreen.setAlignment(Pos.CENTER);

    //Border pane
    BorderPane bPane = features.showBorder(mainScreen);

    //Scroll pane and display scene
    ScrollPane sPane = features.showScrollPane(bPane);
    five = new Scene(sPane, 1000, 500);
    return five;
}
    //-------------------- SCENE SIX BELOW --------------------//
    /**
     * Creates scene six: holds all of the components
     * @param scene contains all the scenes
     * @param cBMonths combo box that has all the months in a year
     * @return scene six
     */
    public Scene showSceneSix(Stage scene, ComboBox<String>cBMonths){
    Scene six;    

    DecimalFormat df = new DecimalFormat("######.##");

    bee = features.image();
    bee2 = features.image();

    features.setMonth(cBMonths, this.month, trends.income2D);
    //Set the chosen month to selected month from combo box and display data
    cBMonths.setOnAction(action ->{
        this.month = (String)cBMonths.getValue();
        if(financeFV.checkForMonth(this.month).equals(":)")){
            Popup warning = features.showWarning();
            warning.show(scene);

        }
        else{
            financeFV.repopulate(this.month, trends.income2D, "income");
            financeFV.repopulate(this.month, trends.expense2D, "expense");
            sceneSix = showSceneSix(window, cBMonths);
            scene.setScene(sceneSix);
        }
        
    });
    
    HBox titleHB = new HBox();
    titleHB.getChildren().addAll(bee, cBMonths, bee2);
    titleHB.setAlignment(Pos.CENTER);
    
    Label monthlyBudgetL = features.setFont("MONTHLY BUDGET", 16);
    
    //-------------------- SCENE SIX SECTION 1 BELOW --------------------//
    //Initialize double variables to store calculation results
    double sumAccIncome = trends.sumCalculator(trends.income2D, 2); //ADD VALUES BY PASSING IT INTO THIS METHOD
    double endBalance = trends.sumCalculator(trends.income2D, 2) - trends.sumCalculator(trends.expense2D, 2); //Acc income - acc expense
    //Initialize rectangles for GUI
    Rectangle sumAccIncomeR, endBalanceR;
    
    //If the acc income or end balance is a negative number, the default minimum size for the rectangle (10) is used
    if (sumAccIncome < 0){
        sumAccIncomeR = new Rectangle (50, 10, features.babyBlue); //10 is the default min. rect size
    }
    else{
        sumAccIncomeR = new Rectangle (50, sumAccIncome + 10, features.babyBlue);
    }
    
    if(endBalance < 0){
        endBalanceR = new Rectangle (50, 10, features.darkBlue);
    }
    else{
        endBalanceR = new Rectangle (50, endBalance + 10, features.darkBlue);
    }
    
    //If the acc income or end balance is greater than a certain value (100, 1500, 10000, 100000),
    //the rectangles will be divided by 100 so they fit the screen
    if (sumAccIncome > 100000 || endBalance > 100000){
      sumAccIncomeR = new Rectangle (50, sumAccIncome/10000, features.babyBlue);
      endBalanceR = new Rectangle (50, endBalance/10000, features.darkBlue);
    }
    else if (sumAccIncome > 10000 || endBalance > 10000){
      sumAccIncomeR = new Rectangle (50, sumAccIncome/1000, features.babyBlue);
      endBalanceR = new Rectangle (50, endBalance/1000, features.darkBlue);
    }
    else if(sumAccIncome > 1500 || endBalance > 1500){
      sumAccIncomeR = new Rectangle (50, sumAccIncome/100, features.babyBlue);
      endBalanceR = new Rectangle (50, endBalance/100, features.darkBlue);
    }
    else if(sumAccIncome > 100 || endBalance > 100){
      sumAccIncomeR = new Rectangle (50, sumAccIncome/10, features.babyBlue);
      endBalanceR = new Rectangle (50, endBalance/10, features.darkBlue);
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
    Double percentSaved = (  endBalance / (trends.sumCalculator(trends.income2D, 2) )  ) * 100;
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
    summaryRightStack.getChildren().addAll(new Rectangle(300, 150, features.babyBlue), summaryRightHB);
    
    //-------------------- SCENE SIX SECTION 3 BELOW --------------------//            
     // Initiate double variables to store calculation results
     double sumAntIncome = trends.sumCalculator(trends.income2D, 1);
     double sumAntExpense = trends.sumCalculator(trends.expense2D, 1);
     double sumAccExpense = trends.sumCalculator(trends.expense2D, 2);
    //Note: sumAccIncome has been initialized earlier
    
    //Initiate labels
    Label incomeL = features.setFont("INCOME", 15);
    Label expensesL = features.setFont("EXPENSE", 15);
    Label antL = features.setFont("ANTICIPATED", 15);
    Label accL = features.setFont("ACTUAL", 15);
    Label antL2 = features.setFont("ANTICIPATED", 15);
    Label accL2 = features.setFont("ACTUAL", 15);
    
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
        sumAntIncR = new Rectangle(10, 20, features.white); //10 is default rect size
    }
    else{
        sumAntIncR = new Rectangle(sumAntIncome + 10, 20, features.white);
    }
    
    if(sumAccIncome < 0){
        sumAccIncR = new Rectangle(10, 20, features.white);
    }
    else{
    sumAccIncR = new Rectangle(sumAccIncome + 10, 20, features.white);
    }
    if(sumAntExpense < 0){
    sumAntExpR = new Rectangle(10, 20, features.white);
    }
    else{
    sumAntExpR = new Rectangle(sumAntExpense + 10, 20, features.white);
    }
    if(sumAccExpense < 0){
    sumAccExpR = new Rectangle(10, 20, features.white);
    }
    else{
    sumAccExpR = new Rectangle(sumAccExpense + 10, 20, features.white);
    }
    
    //If the values in anticpated and actual income or expenses are too big, all the rectangle
    //sizes will be shrunk proportionately. For ex, when the values are greater than 100, 1500, 10000, 100000
    if (sumAntIncome > 100000 || sumAccIncome > 100000 || sumAntExpense > 100000 || sumAccExpense > 100000){
      sumAntIncR = new Rectangle(sumAntIncome/10000, 20, features.white);
      sumAccIncR = new Rectangle(sumAccIncome/10000, 20, features.white);
      sumAntExpR = new Rectangle(sumAntExpense/10000, 20, features.white);
      sumAccExpR = new Rectangle(sumAccExpense/10000, 20, features.white);
    }
    else if (sumAntIncome > 10000 || sumAccIncome > 10000 || sumAntExpense > 10000 || sumAccExpense > 10000){
      sumAntIncR = new Rectangle(sumAntIncome/1000, 20, features.white);
      sumAccIncR = new Rectangle(sumAccIncome/1000, 20, features.white);
      sumAntExpR = new Rectangle(sumAntExpense/1000, 20, features.white);
      sumAccExpR = new Rectangle(sumAccExpense/1000, 20, features.white);
    }
    else if (sumAntIncome > 1500 || sumAccIncome > 1500 || sumAntExpense > 1500 || sumAccExpense > 1500){
      sumAntIncR = new Rectangle(sumAntIncome/100, 20, features.white);
      sumAccIncR = new Rectangle(sumAccIncome/100, 20, features.white);
      sumAntExpR = new Rectangle(sumAntExpense/100, 20, features.white);
      sumAccExpR = new Rectangle(sumAccExpense/100, 20, features.white);
    }
    else if (sumAntIncome > 100 || sumAccIncome > 100 || sumAntExpense > 100 || sumAccExpense > 100){
    sumAntIncR = new Rectangle(sumAntIncome/10, 20, features.white);
    sumAccIncR = new Rectangle(sumAccIncome/10, 20, features.white);
    sumAntExpR = new Rectangle(sumAntExpense/10, 20, features.white);
    sumAccExpR = new Rectangle(sumAccExpense/10, 20, features.white);
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
    summaryBottomVB.setBackground(new Background(new BackgroundFill(features.darkBlue, CornerRadii.EMPTY, Insets.EMPTY)));
    
    //-------------------- SCENE SIX COMBINE ALL SECTIONS BELOW --------------------//
    //Initialize buttons
    Text filler = new Text("");
    Button mainMenuB = goToSceneOne(window, "MAIN MENU", filler); 
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
    /**
     * 
     * @param stage contains all the scenes
     * @param cBMonths combo box that has all the months in a year
     * @return scene seven
     */
    public Scene showSceneSeven(Stage stage, ComboBox<String>cBMonths){ // u have to click plan and transactions first because thats what populates the 2d arr
        //Initialize scene 7  
        Scene seven; 
        
        //Checks for selected month and displays the proper month and data
        features.setMonth(cBMonths, this.month, trends.income2D);

        cBMonths.setOnAction(action ->{
            this.month = (String)cBMonths.getValue();
            if(financeFV.checkForMonth(this.month).equals(":)")){
                Popup warning = features.showWarning();
                warning.show(stage);
    
            }
            else{
                financeFV.repopulate(this.month, trends.income2D, "income");
                financeFV.repopulate(this.month, trends.expense2D, "expense");
                sceneSeven = showSceneSeven(window, cBMonths);
                stage.setScene(sceneSeven);
            }
            
        });

        // initialize buttons
        Button main = goToSceneOne(stage, "MAIN MENU", null);
        
        // initilze graphs
        PieChart piechart = features.showPieChart(trends.income2D);        
        LineChart<String,String> lineChart = features.showlLineChart(trends.expense2D);

        HBox graphs = new HBox(10);     // gather graphs
        graphs.getChildren().addAll(piechart , lineChart);

        HBox buttons = new HBox(10);        // gather buttons
        buttons.getChildren().addAll(main);
        buttons.setAlignment(Pos.BOTTOM_RIGHT);

        VBox mainScreen = new VBox(10);     // gather GRAPHS AND BUTTONS
        mainScreen.getChildren().addAll(cBMonths, graphs, buttons);
        mainScreen.setAlignment(Pos.CENTER);

        // puts into borderpane and then displays
        BorderPane bPane = features.showBorder(mainScreen);

        seven = new Scene(bPane, 1000, 500);
        return seven;
    }
    // ---------------------- GOTOSCENE BUTTONS --------------------------- //
    /**
     * button that goes to scene one (main menu) when user clicks it
     * @param stage the stage where scenes are set
     * @param sceneName the name of the button
     * @param warningT adjusts warning text on the first scene
     * @return the button
     */
    public Button goToSceneOne(Stage stage, String sceneName, Text warningT){
        Button scene1B = features.yellowButton(sceneName);
        scene1B.setOnAction(action -> {
                warningT.setText(" ");
                stage.setScene(sceneOne);
        });
        return scene1B;
    }
    /**
     * button that goes to scene two (select month) when user clicks it
     * calls on the second scene method
     * @param stage the stage where scenes are set
     * @param sceneName the name of the button
     * @param warningT adjusts warning text on the first scene
     * @return the button
     */
    public Button goToSceneTwo(Stage stage, String sceneName, Text warning){
        Button scene2B = features.yellowButton(sceneName);
        scene2B.setOnAction(action -> {
            cBMonths = features.comboBoxMonths();
            sceneTwo = showSceneTwo(window, cBMonths, warning); //  new budget     
            stage.setScene(sceneTwo);
        });
        return scene2B;
    }
    /**
     * button that goes to scene three (plan) when user clicks it
     * calls on the threefour scene method
     * @param stage the stage where scenes are set
     * @param sceneName the name of the button
     * @param warningT adjusts warning text on the first scene
     * @return the button
     */
    public Button goToSceneThree(Stage stage, String sceneName, Text warning){
        Button scene3B = features.yellowButton(sceneName);
        scene3B.setOnAction(action -> {
            if(this.month != null && clicked == true){
                cBMonths = features.comboBoxMonths();
                sceneThree = showSceneThreeFour(window, "Anticipated", warning); // plan
                stage.setScene(sceneThree);
            }
        });
        return scene3B;
    }    
    /**
     * button that goes to scene four (transaction) when user clicks it
     * calls on the threefour scene method
     * @param stage the stage where scenes are set
     * @param sceneName the name of the button
     * @param warningT adjusts warning text on the first scene
     * @return the button
     */
    public Button goToSceneFour(Stage stage, String sceneName, Text warning){
        Button scene4B = features.yellowButton(sceneName);
        scene4B.setOnAction(action -> {            
            if(this.month != null && clicked == true){
                cBMonths = features.comboBoxMonths();
                sceneFour = showSceneThreeFour(window, "Actual", warning); // transactions
                stage.setScene(sceneFour);
            }
        });
        return scene4B;
    }
    /**
     * button that goes to scene five (summary a) when user clicks it
     * changes the 2d arrays to the data that the user inputted
     * inputs the difference into the 2d array
     * calls on the five scene method
     * @param stage the stage where scenes are set
     * @param sceneName the name of the button
     * @param warntingT the outputted Text warning text 
     * @return the button
     */
    public Button goToSceneFive(Stage stage, String sceneName, Text warningT){
        Button scene5B = features.yellowButton(sceneName);
        scene5B.setOnAction(action -> {
            // financeFV.fullImport = financeFV.checkImport();
            // conds if user has not imported or selected a month (started a budget)
            if(this.month != null && clicked == true ||financeFV.fullImport == true){
                //repopulates the data to the from the user inputted scene 3/4
                financeFV.repopulate(this.month, trends.income2D, "income");        
                financeFV.repopulate(this.month, trends.expense2D, "expense");
                trends.populateDiff(trends.income2D, "income");   // inputs the differences into the 2d array
                trends.populateDiff(trends.expense2D, "expense");
                cBMonths = features.comboBoxMonths();
                sceneFive = showSceneFive(window, cBMonths); // summary
                stage.setScene(sceneFive);
                warningT.setText(" ");
            }
            else{
                features.noData(warningT, this.month, clicked);            
            }
        });
        return scene5B;
    }
        /**
     * button that goes to scene five (summary b) when user clicks it
     * calls on the six scene method
     * @param stage the stage where scenes are set
     * @param sceneName the name of the button
     * @return the button
     */
    public Button goToSceneSix(Stage stage, String sceneName){
        Button scene6B = features.yellowButton(sceneName);        
        scene6B.setOnAction(action -> {            
            // conds if user has not imported or selected a month (started a budget)
            if(this.month != null && (clicked == true || financeFV.fullImport == true)){
                cBMonths = features.comboBoxMonths();
                sceneSix = showSceneSix(window, cBMonths); // trends
                stage.setScene(sceneSix);
            }
        });
        return scene6B;
    }
    /**
     * button that goes to scene seven (trends) when user clicks it
     * calls on the seven scene method
     * @param stage the stage where scenes are set
     * @param sceneName the name of the button
     * @param warntingT the outputted Text warning text 
     * @return the button
     */
    public Button goToSceneSeven(Stage stage, String sceneName, Text warningT){
        Button scene7B = features.yellowButton(sceneName);        
        scene7B.setOnAction(action -> {    
            // conds if user has not imported or selected a month (started a budget)
            if(this.month != null && (clicked == true || financeFV.fullImport == true)){
                cBMonths = features.comboBoxMonths();
                sceneSeven = showSceneSeven(window, cBMonths); // trends
                stage.setScene(sceneSeven);
                warningT.setText(" ");
            }
            else{
                features.noData(warningT, this.month, clicked);     // warning text updates
            }
        });
        return scene7B;
    }
    // ---------------------------------------------------------------- //
    /**
     * displays the user's entries onto scene two
     * @param cBMonths the combo box that holds all the months a user can select
     * @param incomeCatT gui text object for the income categories
     * @param expenseCatT gui text object for the expense categories    
     * @param stage holds all the the scenes
     */
    public void showTextTwo(ComboBox<String> cBMonths, Text incomeCatT, Text expenseCatT, Stage stage){
        cBMonths.setOnAction(action ->{
            this.month = (String)cBMonths.getValue();   // get the month that the user has selected
            monthSet = true;        // now we know that they have chosen a month
            financeFV.fileName = "income.csv";
            String startCoords = financeFV.checkForMonth(this.month);       //checks the month exists in the csv or not
            Text filler = new Text(" ");
            if(startCoords.equals(":)")){                               // smiley face means that it is not in the csv yet
                // clears the arraylists
                trends.incomeCatList.clear();       
                trends.expenseCatList.clear();
                noChange = true;
                sceneTwo = showSceneTwo(window, cBMonths, filler);
                stage.setScene(sceneTwo);
            }
            else{
                noChange = false;
                trends.incomeCatList.clear();
                trends.expenseCatList.clear();
                // repopulates the 2D arrays with the data in the csv
                financeFV.repopulate(this.month, trends.income2D, "income"); 
                financeFV.repopulate(this.month, trends.expense2D, "expense");
                // adds to the entries arraylist
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

            // if the user has entered stuff, then dispkay it in the GUI
            if(trends.incomeCatList.isEmpty() == false || trends.expenseCatList.isEmpty() == false){
                for(int i = 0; i < trends.incomeCatList.size(); i ++){
                    incStringCat = trends.incomeCatList.get(i) + "\n";      
                    incPrintCat += incStringCat;            
                    
                    incomeCatT.setText("Categories : \n" + incPrintCat);
                }
                for(int i = 0; i < trends.expenseCatList.size(); i ++){
                    expStringCat = trends.expenseCatList.get(i) + "\n";      
                    expPrintCat += expStringCat;            
                    
                    expenseCatT.setText("Categories : \n" + expPrintCat);
                }
            }
        });
    }

    /**
     * Sets the global variable month with what month the user clicked in the combo box
     */
    public void getMonth(ComboBox<String> cBMonths){
        cBMonths.setOnAction(action ->{
            this.month = (String)cBMonths.getValue();
        });
    }
    /**
     * updates the 2D arrays with what is in the CSV according to the month that the user wants to see
     * and updates the user's entries arraylists
     * @param arrList the arraylist that holds the user's entries
     * @param twoDArr 2D array that stores data
     * @param col the index number of the column we need in the 2D array
     * @param month the month that the user has selected
     * @return updated arraylist of user entries
     */
    public void arrUpdate(ArrayList<String> arrList, String[][] twoDArr, int col, String month){        
        String startCoords = financeFV.checkForMonth(month);
        if(!startCoords.equals(":)")){
            financeFV.repopulate(month, trends.income2D, "income");     // repopulates the 2d array
            financeFV.repopulate(month, trends.expense2D, "expense");
            System.out.println("going thru arrupdate");
            for(int i = 1; i < trends.income2D.length; i++){        
                arrList.add(twoDArr[i][col]);                           // populates the arraylist that we print out with what's already in the 2d array
            }
            
        }
        // return arrList;
    }

}