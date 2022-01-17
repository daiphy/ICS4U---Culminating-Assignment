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
import javafx.geometry.Insets;
import javafx.geometry.Pos; //For alignment

public class AppLayout extends Application{

    // Global Variables
    Scene sceneFour, sceneFive, sceneSix, sceneSeven;
    Stage window;
    Color babyBlue = Color.web("#C9DAF8");
    ComboBox cBMonths, cBIncome, cBExpense;
    StackPane stackPane = new StackPane();
    String emptyCat = "";
    String emptyAmnt = "";
    int y = 150;

    // GridPane gridPane = new GridPane();
    
    public AppLayout(){

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Finance finance = new Finance();
        window = primaryStage;
        cBIncome = comboBoxIncome();
        cBExpense = comboBoxExpense();
        sceneFour = baseScene(window, "Anticipated", "Income", cBIncome);
        sceneFive = baseScene(window, "Anticipated", "Expense", cBExpense);
        sceneSix = baseScene(window, "Actual", "Income", cBIncome);
        sceneSeven = baseScene(window, "Actual", "Expense", cBExpense);

        window.setScene(sceneFour);
        window.show();
        
    }
    public Scene baseScene(Stage stage, String whichType, String trans, ComboBox cBTrans){

        Scene base;

        // initializing buttons, labels, comboBoxes
        Label titleL = new Label("Budgeting App");
        cBMonths = comboBoxMonths();

        Label typeL = new Label(whichType);
        Label transL = new Label(trans);
        Label categoryL = new Label("Category : ");
        Label amountL = new Label("Amount : ");
        Label hSpace = new Label("          ");
        Label vSpace = new Label("          ");
        Label rSpace = new Label("                  ");
        
        Button addB = new Button("ADD");
        Button deleteB = new Button("DELETE");
        Button nextB = new Button("Next Page");
        Button backB = new Button("Back");
        
        // cBIncome = comboBoxIncome();
        
        TextField amountT = new TextField("Input Amount");

        // MAKE IF STATEMENTS IN THE ADD/DEL BTN THAT THE RECTANGLE Y WILL CHANGE AS MORE THINGS ARE ADDED AND DELETED

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

        stackPane.getChildren().addAll(rectangle, catAmntBox);

        // Styling the Labels || Buttons
        titleL.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        typeL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        transL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        categoryL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        amountL.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        amountT.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        addB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        deleteB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        nextB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        backB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");

        // Button Actions
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
        deleteB.setOnAction(action -> {
            String catIncome = (String) cBIncome.getValue();
            String amntIncomeString = amountT.getText();
            Double amntIncome = Double.parseDouble(amntIncomeString);
            System.out.println(catIncome + " : " + amntIncome);
            // .remove() to arraylist here Jane/Rachel
        });
        nextB.setOnAction(action -> {
            // if (whichType.equals("Anticipated") && trans.equals("Income")){
                        // sceneFive = baseScene(window, "Anticipated", "Expense", cBIncome);

                // stage.setScene(sceneFive);
                // stage.show();

                if (whichType.equals("Anticipated") && trans.equals("Income")){
                    stage.setScene(sceneFive);
                    stage.show();
                }
                else if (whichType.equals("Anticipated") && trans.equals("Expense")){
                    stage.setScene(sceneSix);
                    stage.show();
                }
                else if (whichType.equals("Actual") && trans.equals("Income")){
                    stage.setScene(sceneSeven);
                    stage.show();
                }
            
        });
        backB.setOnAction(action -> {
            stage.setScene(sceneFour);
            stage.show();
        
        });

        VBox firstCol = new VBox(10);
        firstCol.getChildren().addAll(typeL, transL);

        HBox secondRow = new HBox(50);
        secondRow.getChildren().addAll(hSpace, firstCol);

        HBox thirdRow = new HBox(50);
        thirdRow.getChildren().addAll(categoryL, cBTrans, amountL, amountT, addB, deleteB);
        thirdRow.setAlignment(Pos.CENTER);

        HBox fourthRow = new HBox(10);
        fourthRow.getChildren().addAll(backB, nextB);
        fourthRow.setAlignment(Pos.BOTTOM_RIGHT);

        VBox mainScreen = new VBox(10);
        mainScreen.getChildren().addAll(vSpace, titleL, cBMonths, secondRow, thirdRow, stackPane, fourthRow);
        mainScreen.setAlignment(Pos.TOP_CENTER);

        base = new Scene(mainScreen, 1000, 500);

        return base;
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

        // putting it on the scene using gridpane to map it out
        // //Setting size for the pane  
        // gridPane.setMinSize(1000, 500); 
        // // Setting the padding (Border)
        // gridPane.setPadding(new Insets(10, 10, 10, 10));
        // // Setting the Grid alignment
        // gridPane.setAlignment(Pos.TOP_LEFT); 
        // //Setting the vertical and horizontal gaps between the columns 
        // gridPane.setHgap(5);
        // gridPane.setVgap(5);     
        // // Arranging all the nodes in the grid 
        // gridPane.add(titleL, 14, 0);
        // gridPane.add(typeL, 5, 1);
        // gridPane.add(cBMonths, 14, 1);
        // gridPane.add(transL, 5, 2);
        // gridPane.add(categoryL, 5, 3);
        // gridPane.add(cBIncome, 8, 3);
        // gridPane.add(amountL, 11, 3);
        // gridPane.add(amountT, 14, 3);
        // gridPane.add(addB, 17, 3);
        // gridPane.add(deleteB, 20, 3);
        // gridPane.add(nextB, 20, 4);
        // gridPane.add(backB, 17, 4);
        // if (whichType.equals("Anticipated") && trans.equals("Income")){
            // stage.setScene(sceneFive);
            // stage.show();
        // }
        // else if (whichType.equals("Anticipated") && trans.equals("Expense")){
        //     stage.setScene(sceneSix);
        //     stage.show();
        // }
        // else if (whichType.equals("Actual") && trans.equals("Income")){
        //     stage.setScene(sceneSeven);
        //     stage.show();
        // }
        // });
        // backB.setOnAction(action -> {
        // if (whichType.equals("Anticipated") && trans.equals("Expense")){
        //     stage.setScene(sceneFour);
        //     stage.show();
        // }
        // else if (whichType.equals("Actual") && trans.equals("Income")){
        //     stage.setScene(sceneFive);
        //     stage.show();
        // }
        // else if (whichType.equals("Actual") && trans.equals("Expense")){
        //     stage.setScene(sceneSix);
        //     stage.show();
        // }
}