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
import javafx.geometry.Insets;
import javafx.geometry.Pos; //For alignment

public class AppLayoutFV extends Application{

    //-------------------- GLOBAL VARIABLES --------------------//
    Scene sceneFour, sceneFive, sceneSix, sceneSeven;
    Stage window;
    Color babyBlue = Color.web("#C9DAF8");
    ComboBox cBMonths, cBIncFour, cBIncFive, cBExpFour, cBExpFive;
    // StackPane stackPane = new StackPane();
    String emptyCat = "";
    String emptyAmnt = "";
    int y = 150;
    public LayoutFeatures features = new LayoutFeatures(); 

    // GridPane gridPane = new GridPane();
    
    //-------------------- CONSTRUCTOR --------------------//
    public AppLayoutFV(){

    }

    //-------------------- RUN THE GUI --------------------//
    @Override
    public void start(Stage primaryStage) throws Exception{
        Finance finance = new Finance();
        window = primaryStage;
        cBIncFour = features.comboBoxIncome();
        cBIncFive = features.comboBoxIncome();
        cBExpFour = features.comboBoxExpense(); 
        cBExpFive = features.comboBoxExpense();

        sceneFour = baseScene(window, "Anticipated", cBIncFour, cBExpFour);
        sceneFive = baseScene(window, "Actual", cBIncFive, cBExpFive);
        // sceneFive = baseScene(window, "Anticipated", "Expense", cBExpense);
        // sceneSix = baseScene(window, "Actual", "Income", cBIncome);
        // sceneSeven = baseScene(window, "Actual", "Expense", cBExpense);

        window.setScene(sceneFour);
        window.show();
        
    }

    //-------------------- SCENE THREE/FOUR --------------------//
    public Scene baseScene(Stage stage, String whichType, ComboBox cBInc, ComboBox cBExp){

        Scene base;

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

        base = new Scene(mainScreen, 1000, 500);

        return base; 
    }
}