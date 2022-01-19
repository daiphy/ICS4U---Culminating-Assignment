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
import javafx.scene.layout.BorderPane;

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

        sceneOne = showSceneOne(window); // main Menu
        sceneTwo = showSceneTwo(window, cBMonths); //  new budget      
        sceneThree = showSceneThreeFour(window, "Anticipated", cBIncFour, cBExpFour);
        sceneFour = showSceneThreeFour(window, "Actual", cBIncFive, cBExpFive);
        // sceneFive = baseScene(window, "Anticipated", "Expense", cBExpense);
        // sceneSix = baseScene(window, "Actual", "Income", cBIncome);
        // sceneSeven = baseScene(window, "Actual", "Expense", cBExpense);

        window.setScene(sceneOne);
        window.show();
        
    }
    //-------------------- SCENE ONE BELOW --------------------//
    public Scene showSceneOne (Stage scene){
        Scene one;

        Text warningT = new Text("");
        
        // Initializing buttons and labels
        Label welcomeL = new Label("MAIN MENU");
        Label introL = new Label("Click on one of the buttons below to visit a page");
        
        Button summaryB = new Button("SUMMARY"); // scene 5
        summaryB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        
        Button trendsB = new Button("TRENDS"); //scene 7
        trendsB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        
        Button transactionB = goToSceneFour(window, "TRANSACTION"); // scene four
        Button planB = goToSceneThree(window, "PLAN");        
        Button newBudgetB = goToSceneTwo(window, "NEW BUDGET");        
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
        left.getChildren().addAll(welcomeL, introL, newBudgetB, transactionB, summaryB);

        VBox right = new VBox(20);
        right.getChildren().addAll(planB, trendsB, importB);

        HBox mainScreen = new HBox(20);        
        mainScreen.getChildren().addAll(left, right);
        mainScreen.setAlignment(Pos.CENTER);
        
        one = new Scene(mainScreen, 1000, 500);
        
        return one;
    }    

    //-------------------- SCENE TWO BELOW --------------------//
    public Scene showSceneTwo (Stage scene, ComboBox cBMonths){
        Scene two;

        //Title label, instructions label and formatting
        Label newBudgetL = new Label("NEW BUDGET");
        newBudgetL.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        
        Label instructionsL = new Label("Type in your desired category and press add to include it. If you would like to delete one, type in your category and press the delete button."); 
        
        //Asks the user which month they are budgeting for and provide a drop down menu 
        Label monthL = new Label("Month:");
        cBMonths = features.comboBoxMonths();
        
        //HBox gathers the month input and income categories input as they are in the same row
        HBox monthRow = new HBox(20);
        monthRow.getChildren().addAll(monthL, cBMonths);
        monthRow.setAlignment(Pos.CENTER);
        
        //Asks the user to type in their category for income and expenses
        Label incCatL = new Label("Type in your category for income:");
        TextField incCatTF = new TextField();
        
        Label expCatL = new Label("Type in your category for expense:");
        TextField expCatTF = new TextField();
        
        //User selects either the add or delete button for income and expenses
        Button addIncCatB = new Button("ADD");
        Button deleteIncCatB = new Button("DELETE");
        
        Button addExpCatB = new Button("ADD");
        Button deleteExpCatB = new Button("DELETE");
                       
        //HBox
        HBox incCatRow = new HBox(20);
        incCatRow.getChildren().addAll(incCatL, incCatTF, addIncCatB, deleteIncCatB);
        incCatRow.setAlignment(Pos.CENTER);
        
        HBox expCatRow = new HBox(20);
        expCatRow.getChildren().addAll(expCatL, expCatTF, addExpCatB, deleteExpCatB);
        expCatRow.setAlignment(Pos.CENTER);
        
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
        HBox displayCat = new HBox(200);
        displayCat.getChildren().addAll(incomeCat, expenseCat);
        displayCat.setAlignment(Pos.CENTER);
        displayCat.setBackground(new Background(new BackgroundFill(babyBlue, CornerRadii.EMPTY, Insets.EMPTY)));
                
        //NOTE: Buttons need action, move formatting to its own button methods
        Button mainMenuB = goToSceneOne(window, "MAIN MENU");        
        
        Button nextPageB = goToSceneThree(window, "NEXT PAGE");        
        
        //HBox to gather main menu button and next page button
        HBox sceneButtons = new HBox(20);
        sceneButtons.getChildren().addAll(mainMenuB, nextPageB);
        sceneButtons.setAlignment(Pos.CENTER_RIGHT);
        
        //Making the overall screen
        VBox mainScreen = new VBox(20);        
        mainScreen.getChildren().addAll(newBudgetL, instructionsL, monthRow, incCatRow, expCatRow, catL, displayCat, sceneButtons);
        mainScreen.setAlignment(Pos.CENTER);
        
        //Fillers
        //Label fillerL = new Label();
        //Label filler2L = new Label();
        
        //HBox mainScreen = new HBox(20);
        //mainScreen.getChildren().addAll(fillerL, centerScreen, filler2L);
        //mainScreen.setAlignment(Pos.CENTER);
        
        BorderPane bPaneTwo = showBorder(mainScreen);
        two = new Scene(bPaneTwo, 1000, 500);
        
        return two;
    }
    
    //-------------------- SCENE THREE/FOUR BELOW --------------------//
    public Scene showSceneThreeFour(Stage stage, String whichType, ComboBox cBInc, ComboBox cBExp){

        Scene threeFour;

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
        Label space = features.spacing();

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
        // UM IDRK HOW YOU WOULD METHODIZE IT THO BC IF U RETURN ARRLIST U CAN ONLY RETURN ONE AND IF IT'S VOID DO YOU NEED ARR LIST? WILL IT GO THROUGH?

        Button addTB = features.addButton(cBInc, amntTFT, cat, amnt); //income
        addTB.setOnAction(action -> {
            addToArr(whichType, cBInc, amntTFT, incomeAntCatArr, incomeAntAmtArr, incomeAccCatArr, incomeAccAmtArr);
            
        });

        Button addBB = features.addButton(cBExp, amntTFB, cat, amnt); // expense
        addBB.setOnAction(action -> {
            addToArr(whichType, cBExp, amntTFB, expenseAntCatArr, expenseAntAmtArr, expenseAccCatArr, expenseAccAmtArr);
            // if(whichType.equalsIgnoreCase("ANTICIPATED")){
            //     expenseAntCatArr.add((String) cBExp.getValue());
            //     expenseAntAmtArr.add(amntTFB.getText());
            //     System.out.println(expenseAntCatArr + " : " + expenseAntAmtArr);      //testing
            // }
            // else{
            //     expenseAccCatArr.add((String) cBExp.getValue());
            //     expenseAccAmtArr.add(amntTFB.getText());
            //     System.out.println(expenseAccCatArr + " : " + expenseAccAmtArr);      //testing
            // }
        });
        
        // TB (top half of the scene) -> income
        Button delTB = features.delButton(cBInc, amntTFT);
        delTB.setOnAction(action -> {
            removeFromArr(whichType, cBInc, amntTFT, incomeAntCatArr, incomeAntAmtArr, incomeAccCatArr, incomeAccAmtArr);
            // String temp = (String) cBInc.getValue();
            // String tempText = amntTFT.getText();
            
            // if(whichType.equalsIgnoreCase("ANTICIPATED")){
            //     for(int i = 0; i < incomeAntCatArr.size(); i++){
            //         if( incomeAntCatArr.get(i).equals(temp) && incomeAntAmtArr.get(i).equals(tempText)){
            //             incomeAntCatArr.remove(i);
            //             incomeAntAmtArr.remove(i);     
            //         }
            //     }
            //     System.out.println(incomeAntCatArr + " : " + incomeAntAmtArr);      // for testing

            // }
            // else{
            //     for(int i = 0; i < incomeAccCatArr.size(); i++){
            //         if( incomeAccCatArr.get(i).equals(temp) && incomeAccAmtArr.get(i).equals(tempText)){
            //             incomeAccCatArr.remove(i);
            //             incomeAccAmtArr.remove(i);     
            //         }
            //     }
            //     System.out.println(incomeAccCatArr + " : " + incomeAccAmtArr);        // for testing
            // }
        });
        // BB (bottom half of the scene) -> expenses
        Button delBB = features.delButton(cBExp, amntTFB);
        delBB.setOnAction(action -> {
            removeFromArr(whichType, cBExp, amntTFB, expenseAntCatArr, expenseAntAmtArr, expenseAccCatArr, expenseAccAmtArr);
            // String temp = (String) cBExp.getValue();
            // String tempText = amntTFB.getText();
            
            // if(whichType.equalsIgnoreCase("ANTICIPATED")){
            //     for(int i = 0; i < expenseAntCatArr.size(); i++){
            //         if( expenseAntCatArr.get(i).equals(temp) && expenseAntAmtArr.get(i).equals(tempText)){
            //             expenseAntCatArr.remove(i);
            //             expenseAntAmtArr.remove(i);     
            //         }
            //     }
            //     System.out.println(expenseAntCatArr + " : " + expenseAntAmtArr);      // for testing

            // }
            // else{
            //     for(int i = 0; i < expenseAccCatArr.size(); i++){
            //         if( expenseAccCatArr.get(i).equals(temp) && expenseAccAmtArr.get(i).equals(tempText)){
            //             expenseAccCatArr.remove(i);
            //             expenseAccAmtArr.remove(i);     
            //         }
            //     }
            //     System.out.println(expenseAccCatArr + " : " + expenseAccAmtArr);        // for testing
            // }
        });

        HBox lastRow = new HBox(10);
        
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

        // Styling the Labels || Buttons
        titleL.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        typeL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // Label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        // button.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");


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

        

        VBox mainScreen = new VBox(10);
        mainScreen.getChildren().addAll(titleL, cBMonths, secondRow, thirdRow, stackPaneT, fourthRow, fifthRow, stackPaneB, lastRow);
        mainScreen.setAlignment(Pos.TOP_CENTER);

        BorderPane bPane = showBorder(mainScreen);

        // ScrollPane scroll = showScrollPane(mainScreen);

        // VBox fullScreen = new VBox(0);
        // fullScreen.getChildren().addAll(mainScreen, scroll);
        // fullScreen.setAlignment(Pos.CENTER_RIGHT);

        threeFour = new Scene(bPane, 1000, 500);

        return threeFour; 
    }

    // Button Methods
    public Button goToSceneOne(Stage stage, String sceneName){
        Button scene1B = new Button(sceneName);
        scene1B.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        scene1B.setOnAction(action -> {
            // if(whichType.equals("Anticipated")){
                stage.setScene(sceneOne);
            // }
        });
        return scene1B;
    }
    public Button goToSceneTwo(Stage stage, String sceneName){
        Button scene2B = new Button(sceneName);
        scene2B.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        scene2B.setOnAction(action -> {
            // if(whichType.equals("Anticipated")){
                stage.setScene(sceneTwo);
            // }
        });
        return scene2B;
    }
    public Button goToSceneThree(Stage stage, String sceneName){
        Button scene3B = new Button(sceneName);
        scene3B.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        scene3B.setOnAction(action -> {
            // if(whichType.equals("Anticipated")){
                stage.setScene(sceneThree);
            // }
        });
        return scene3B;
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
    public BorderPane showBorder(VBox center){
        BorderPane bPane = new BorderPane();

        Label topSpace = features.spacing();
        Label bottomSpace = features.spacing();
        Label leftSpace = features.spacing();
        Label rightSpace = features.spacing();

        // setting the borders
        bPane.setTop(topSpace);
        bPane.setBottom(bottomSpace);
        bPane.setLeft(leftSpace);
        bPane.setRight(rightSpace);
        bPane.setCenter(center);

        return bPane;
    }
    public void addToArr(String whichType, ComboBox comboBox, TextField amntTF, ArrayList<String> AntCatArr, ArrayList<String> AntAmtArr, ArrayList<String> AccCatArr, ArrayList<String> AccAmtArr){
        if(whichType.equalsIgnoreCase("ANTICIPATED")){
            AntCatArr.add((String) comboBox.getValue());
            AntAmtArr.add(amntTF.getText());
            System.out.println(AntCatArr + " : " + AntAmtArr);  // testing
        }
        else{
            AccCatArr.add((String) comboBox.getValue());
            AccAmtArr.add(amntTF.getText());
            System.out.println(AccCatArr + " : " + AccAmtArr);  //testng
        }
        System.out.println("Button pressed");
    }
    public void removeFromArr(String whichType, ComboBox comboBox, TextField amntTF, ArrayList<String> AntCatArr, ArrayList<String> AntAmtArr, ArrayList<String> AccCatArr, ArrayList<String> AccAmtArr){
        String temp = (String) comboBox.getValue();
        String tempText = amntTF.getText();
            
        if(whichType.equalsIgnoreCase("ANTICIPATED")){
            for(int i = 0; i < AntCatArr.size(); i++){
                if( AntCatArr.get(i).equals(temp) && AntAmtArr.get(i).equals(tempText)){
                    AntCatArr.remove(i);
                    AntAmtArr.remove(i);     
                }
            }
            System.out.println(AntCatArr + " : " + AntAmtArr);      // for testing

        }
        else{
            for(int i = 0; i < AccCatArr.size(); i++){
                if( AccCatArr.get(i).equals(temp) && AccAmtArr.get(i).equals(tempText)){
                    AccCatArr.remove(i);
                    AccAmtArr.remove(i);     
                }
            }
            System.out.println(AccCatArr + " : " + AccAmtArr);        // for testing
        }
        System.out.println("Button pressed");
    }

    // scenes are objects that contains the buttons and those would interact w the scene themself
    // paint
    // make it its own custom object and put the buttons outside of a scene
    // pane -> google the pane
    // you can put different panes into one scene

}