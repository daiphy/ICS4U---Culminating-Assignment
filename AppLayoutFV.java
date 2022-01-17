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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
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

    // GridPane gridPane = new GridPane();
    
    public AppLayoutFV(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Finance finance = new Finance();
        window = primaryStage;
        cBIncFour = comboBoxIncome();
        cBIncFive = comboBoxIncome();
        cBExpFour = comboBoxExpense();
        cBExpFive = comboBoxExpense();

        sceneOne = showSceneOne(window);

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
                     checkInputtedFile(scene));
            } catch (Exception error) {
               warningT.setText("Action terminated.");
            }
         
        });   
        
        one = new Scene(importB, 1000, 500);

        return one;
    }

    public String checkInputtedFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser(); // allow user to input file
        File file = fileChooser.showOpenDialog(primaryStage);
        String fileName = file.getName();
        String filePath = file.getAbsolutePath();
  
        String warningText = " ";
  
        if (file != null) {
           // If inputted file is .csv file
           if ((fileName.substring(fileName.length() - 4, fileName.length())).equals(".csv")) {
              // reading csv file by adding elements to questions and answers arraylist
              try {
                //  read csv method
              } catch (Exception e) {
                 warningText = "Invalid, action terminated.";
              }
           }
           // Forces user to input .csv in order to go to continue
           else {
              warningText = "Please enter a .csv file.";
  
           }
        }
        return warningText;
     }

    public void newBudgetButton(){
        Button newBudget = new Button("NEW BUDGET");
        newBudget.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        newBudget.setOnAction(action ->{
            window.setScene(sceneThree); //WE NEED TO MAKE SCENE 3
        });
    }

    public Scene showSceneFourFive(Stage stage, String whichType, ComboBox cBInc, ComboBox cBExp){

        Scene fourFive;

        // initializing buttons, labels, comboBoxes
        Label titleL = new Label("Budgeting App");
        cBMonths = comboBoxMonths();

        Label typeL = new Label(whichType);
        Label incL = new Label("INCOME");
        Label expL = new Label("EXPENSE");
        
        Label catTL = catLabel();
        Label catBL = catLabel();
        Label amntTL = amntLabel();
        Label amntBL = amntLabel();
        
        Label hSpaceOne = spacing();
        Label hSpaceTwo = spacing();
        Label vSpace = spacing();
        Label rSpace = spacing();

        TextField amntTFT = amntT();
        TextField amntTFB = amntT();
        Text catAmnt = new Text("Category & Amount");
        Text cat = new Text();
        Text amnt = new Text();
        
        Button addTB = addButton(cBInc, amntTFT, cat, amnt);
        Button addBB = addButton(cBExp, amntTFB, cat, amnt);
        Button delTB = delButton(cBInc, amntTFT);
        Button delBB = delButton(cBExp, amntTFB);

        Button nextB = nextButton(stage);
        Button backB = backButton(stage);
        

        // MAKE IF STATEMENTS IN THE ADD/DEL BTN THAT THE RECTANGLE Y WILL CHANGE AS MORE THINGS ARE ADDED AND DELETED

        StackPane stackPaneT = showSPane();
        StackPane stackPaneB = showSPane();

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
    public StackPane showSPane(){
        StackPane sPane = new StackPane();

        Label rSpace = spacing();

        Rectangle rectangle = new Rectangle(100,150,900,y);
        rectangle.setFill(babyBlue);

        Text catAmnt = new Text("Category & Amount");
        Text cat = new Text();
        Text amnt = new Text();

        // stackPane.setMargin(catAmnt, new Insets(1, 1, 1, 1));

        VBox cATotal = new VBox(10);
        cATotal.getChildren().addAll(catAmnt, cat,amnt);

        HBox catAmntBox = new HBox(10);
        catAmntBox.getChildren().addAll(rSpace, cATotal);
        catAmntBox.setAlignment(Pos.TOP_LEFT);

        sPane.getChildren().addAll(rectangle, catAmntBox);

        return sPane;
    }
    public Label spacing(){
        Label space = new Label("                  ");
        return space;
    }
    
    public Label catLabel(){
        Label cLabel = new Label("Category : ");
        return cLabel;
    }
    public Label amntLabel(){
        Label aLabel = new Label("Amount : ");
        return aLabel;
    }
    public Button addButton(ComboBox cBTrans, TextField amountT, Text cat, Text amnt){
        Button addB = new Button("ADD");
        addB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        addB.setOnAction(action -> {
            String catIncome = (String) cBTrans.getValue();
            String showCatI = catIncome + " | ";
            String amntIncomeString = amountT.getText();
            String showAmntI = amntIncomeString + " | ";
            Double amntIncome = Double.parseDouble(amntIncomeString);
            System.out.println(catIncome + " : " + amntIncome);
            if(cBTrans.getValue() != null && amountT.getText() != null){
                // y += 20;
                emptyCat += showCatI;
                emptyAmnt += showAmntI;
                cat.setText("Category : " + emptyCat);
                amnt.setText("Amount : " + emptyAmnt);
            }
            // .add() to arraylist here Jane/Rachel
        });

        return addB;
    }
    public Button delButton(ComboBox cBTrans, TextField amountT){
        Button delB = new Button("DELETE");
        delB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        delB.setOnAction(action -> {
            String catIncome = (String) cBTrans.getValue();
            String amntIncomeString = amountT.getText();
            Double amntIncome = Double.parseDouble(amntIncomeString);
            System.out.println(catIncome + " : " + amntIncome);
            // .remove() to arraylist here Jane/Rachel
        });
        return delB;
    }
    public Button nextButton(Stage stage){
        Button nextB = new Button("NEXT");
        nextB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        nextB.setOnAction(action -> {
            // if(whichType.equals("Anticipated")){
                stage.setScene(sceneFive);
            // }
        });
        return nextB;
    }
    public Button backButton(Stage stage){
        Button backB = new Button("BACK");
        backB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        backB.setOnAction(action -> {
            // if(whichType.equals("Anticipated")){
                stage.setScene(sceneFour);
            // }
        });
        return backB;
    }
    public TextField amntT(){
        TextField amountT = new TextField("0.00");
        return amountT;
    }
    public ScrollPane showScrollPane(VBox vBoxContents){
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(vBoxContents);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color:transparent;");
        //scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);

        return scroll;
    }

    public ComboBox comboBoxMonths(){
        ComboBox months = new ComboBox();
        months.getItems().addAll(
                                 "January", "February", "March", "April", "May", "June",
                                 "July", "August", "September", "October", "November", "December"
                                );
        months.setPromptText("Select Month");
        months.setEditable(false);
        
        return months;
      }
      public ComboBox comboBoxIncome(){
        ComboBox incomeCat = new ComboBox();
        incomeCat.getItems().addAll(
                                    "Savings", "Paycheck", "Bonus",
                                    "Interest", "Allowance", "Other"
                                   );
        incomeCat.setPromptText("Select Category");
        incomeCat.setEditable(false);
        
        return incomeCat;
      }
      public ComboBox comboBoxExpense(){
        ComboBox expenseCat = new ComboBox();
        expenseCat.getItems().addAll(
                                     "Food", "Health", "Transportation", 
                                     "Utilies", "Personal","Other"
                                    );
        expenseCat.setPromptText("Select Category");
        expenseCat.setEditable(false);
        
        return expenseCat;
      }
}
