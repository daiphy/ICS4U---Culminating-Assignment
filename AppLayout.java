import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos; //For alignment
import java.util.ArrayList;

public class AppLayout extends Application {
  
  //Initialize window and scene
  Stage window;
  Scene scene1, scene2, scene3;
  
  //Initialize arrays
  public String[] arr = new String[12];
  public ArrayList<String> incomeCatArr = new ArrayList<String>();
  public ArrayList<String> incomeAmtArr = new ArrayList<String>();
  public ArrayList<String> expenseCatArr = new ArrayList<String>();
  public ArrayList<String> expenseAmtArr = new ArrayList<String>();
  
  //Initialize Trends
  Trends trends = new Trends();
  
  //Initialize combo boxes
  ComboBox cBMonths;
  ComboBox cBIncome, cBIncome2, cBIncome3, cBIncome4, cBIncome5, cBIncome6, cBIncome7, cBIncome8, cBIncome9, cBIncome10, cBIncome11, cBIncome12, cBIncome13, cBIncome14, cBIncome15; 
  ComboBox cBExpense, cBExpense2, cBExpense3, cBExpense4, cBExpense5, cBExpense6, cBExpense7, cBExpense8, cBExpense9, cBExpense10, cBExpense11, cBExpense12, cBExpense13, cBExpense14, cBExpense15;
  
  //-------------------- CONSTRUCTOR METHOD --------------------//
  public AppLayout(){
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception{    
    window = primaryStage;
    showSceneOne(window);
  }
  //-------------------- SCENE ONE BELOW --------------------//
  public void showSceneOne(Stage stage){
    //Labels and formatting - Title and Headers
    Label title = new Label("Budgeting App");
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
    
    Label selectMonth = new Label("Please Select a Month!");
    selectMonth.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    
    Label labelAntInc = new Label ("Anticipated Income");
    labelAntInc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    
    Label labelAntExp = new Label ("Anticipated Expenses");
    labelAntExp.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    
    Label labelCategory = new Label ("Category"); 
    labelCategory.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    Label labelCategory2 = new Label ("Category");
    labelCategory2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    Label labelAntAm = new Label ("Anticipated Amount");
    labelAntAm.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    Label labelAntAm2 = new Label ("Anticipated Amount");
    labelAntAm2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    //Labels - Act as line breaks
    Label labelBlank = new Label ("");
    Label labelBlank2 = new Label ("");
    
    //Combo box - Drop down menu for months
    cBMonths = comboBoxMonths();
    
    //Initializing buttons
    Button buttonConfirm = new Button("Confirm"); 
    buttonConfirm.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    
    Button buttonScene2 = new Button("Go to Scene 2");
    buttonScene2.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    
    //Text Fields
    TextField leftField = new TextField();
    TextField leftField2 = new TextField();
    TextField leftField3 = new TextField();
    TextField leftField4 = new TextField();
    TextField leftField5 = new TextField();
    TextField leftField6 = new TextField();
    
    TextField rightField = new TextField();
    TextField rightField2 = new TextField();
    TextField rightField3 = new TextField();
    TextField rightField4 = new TextField();
    TextField rightField5 = new TextField();
    TextField rightField6 = new TextField();
    
    //Setting styles of text fields
    leftField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Add action for buttonScene2
    buttonScene2.setOnAction(action -> {
      showSceneTwo(window, "Actual");
    });
    
    //Add action for buttonConfirm
    buttonConfirm.setOnAction(e -> {                 
      arr[0] = (leftField.getText());  
      arr[1] = (leftField2.getText());  
      arr[2] = (leftField3.getText());  
      arr[3] = (leftField4.getText());  
      arr[4] = (leftField5.getText());  
      arr[5] = (leftField6.getText());  
      arr[6] = (rightField.getText());  
      arr[7] = (rightField2.getText());  
      arr[8] = (rightField3.getText());  
      arr[9] = (rightField4.getText());  
      arr[10] = (rightField5.getText());  
      arr[11] = (rightField6.getText());  
      //populating arrays 
      trends.populate(arr, trends.income2D, trends.expense2D);
      
      selectMonth.setText(cBMonths.getValue() + " selected");
    });
    
    //Layout - Vertical column on the far left
    VBox farLeft = new VBox(15);
    farLeft.getChildren().addAll(labelAntInc);
    farLeft.getChildren().addAll(labelCategory);
    
    //Layout - Vertical column on the left
    VBox left = new VBox(10);
    left.getChildren().addAll(labelBlank, labelAntAm);
    
    //Layout - Vertical column on the right
    VBox right = new VBox(15);
    right.getChildren().addAll(labelAntExp);
    right.getChildren().addAll(labelCategory2);
    
    //Layout - Vertical column on the far right
    VBox farRight = new VBox(10);
    farRight.getChildren().addAll(labelBlank2, labelAntAm2);
    
    //Array containing the labels of the categories
    String[] labelCatArr = new String[]{"Savings", "Paycheck", "Bonus", "Interest", "Allowance", "Others"};    
    
    //Prints out the elements inside labelCatArr on the far left side
    for (int i = 0; i < labelCatArr.length; i++){
      Label labelCat = new Label(labelCatArr[i]);
      labelCat.setFont(Font.font("Verdana", 12));
      farLeft.getChildren().add(labelCat);
    }
    
    left.getChildren().addAll(leftField, leftField2, leftField3, leftField4, leftField5, leftField6); 
    
    //Prints out the elements inside labelCatArr on the right side
    for (int i = 0; i < labelCatArr.length; i++){
      Label labelCat = new Label(labelCatArr[i]);
      labelCat.setFont(Font.font("Verdana", 12));
      right.getChildren().add(labelCat);
    }
    
    farRight.getChildren().addAll(rightField, rightField2, rightField3, rightField4, rightField5, rightField6);
    
    //Layout - Gathers the vertical columns on the far left and left together
    HBox hBoxLeft = new HBox(50);
    hBoxLeft.getChildren().addAll(farLeft, left);
    
    //Layout - Gathers the vertical columns on the right and far right together
    HBox hBoxRight = new HBox(50);
    hBoxRight.getChildren().addAll(right, farRight);
    
    //Layout - Top of the GUI: Title and Images (*ADD IMAGES HERE*)
    HBox hBoxTop = new HBox(50);
    hBoxTop.getChildren().addAll(title);
    hBoxTop.setAlignment(Pos.CENTER);
    
    //Layout - Under the Title: Select month instructions
    HBox monthMessage = new HBox(50);
    monthMessage.getChildren().addAll(selectMonth);
    monthMessage.setAlignment(Pos.CENTER);
    
    //Layout - Under the title: Combo box for months
    HBox comboBox = new HBox(50);
    comboBox.getChildren().addAll(cBMonths);
    comboBox.setAlignment(Pos.CENTER);
    
    //Layout - Middle of the GUI: Gather all the columns together
    HBox hBoxMiddle = new HBox(50);
    hBoxMiddle.getChildren().addAll(hBoxLeft, hBoxRight);
    hBoxMiddle.setAlignment(Pos.CENTER);
    
    //Layout - Bottom of the GUI: Buttons
    HBox hBoxBottom = new HBox(50);
    hBoxBottom.getChildren().addAll(buttonConfirm, buttonScene2);
    hBoxBottom.setAlignment(Pos.CENTER);
    
    //Layout - Entire GUI: Gathers the top, middle and bottom to make the entire GUI
    VBox mainScreen = new VBox(25);
    mainScreen.getChildren().addAll(hBoxTop, monthMessage, cBMonths, hBoxMiddle, hBoxBottom);
    mainScreen.setAlignment(Pos.CENTER);
    
    //Display the entire GUI
    scene1 = new Scene(mainScreen, 1000, 500);    
    
    stage.setScene(scene1);
    stage.setTitle("Let's Talk Finance");
    stage.show();
  }
  
  //-------------------- SCENE TWO BELOW --------------------//
  public void showSceneTwo(Stage stage, String type){
    cBIncome = comboBoxIncome();
    cBIncome2 = comboBoxIncome();
    cBIncome3 = comboBoxIncome();
    cBIncome4 = comboBoxIncome();
    cBIncome5 = comboBoxIncome();
    cBIncome6 = comboBoxIncome();
    cBIncome7 = comboBoxIncome();
    cBIncome8 = comboBoxIncome();
    cBIncome9 = comboBoxIncome();
    cBIncome10 = comboBoxIncome();
    cBIncome11 = comboBoxIncome();
    cBIncome12 = comboBoxIncome();
    cBIncome13 = comboBoxIncome();
    cBIncome14 = comboBoxIncome();
    cBIncome15 = comboBoxIncome();
    
    cBExpense = comboBoxExpense();
    cBExpense2 = comboBoxExpense();
    cBExpense3 = comboBoxExpense();
    cBExpense4 = comboBoxExpense();
    cBExpense5 = comboBoxExpense();
    cBExpense6 = comboBoxExpense();
    cBExpense7 = comboBoxExpense();
    cBExpense8 = comboBoxExpense();
    cBExpense9 = comboBoxExpense();
    cBExpense10 = comboBoxExpense();
    cBExpense11 = comboBoxExpense();
    cBExpense12 = comboBoxExpense();
    cBExpense13 = comboBoxExpense();
    cBExpense14 = comboBoxExpense();
    cBExpense15 = comboBoxExpense();
    
    //Labels and formatting - Title and Headers
    Label title = new Label("Budgeting App");
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
    
    Label labelAntInc = new Label (type + " Income");
    labelAntInc.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    
    Label labelAntExp = new Label (type + " Expenses");
    labelAntExp.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    
    Label labelCategory = new Label ("Category"); 
    labelCategory.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    Label labelCategory2 = new Label ("Category");
    labelCategory2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    Label labelAntAm = new Label (type + " Amount");
    labelAntAm.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    Label labelAntAm2 = new Label (type + " Amount");
    labelAntAm2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    //Labels - Act as line breaks
    Label labelBlank = new Label ("");
    Label labelBlank2 = new Label ("");        
    
    //Text Fields
    TextField leftField = new TextField();
    TextField leftField2 = new TextField();
    TextField leftField3 = new TextField();
    TextField leftField4 = new TextField();
    TextField leftField5 = new TextField();
    TextField leftField6 = new TextField();
    TextField leftField7 = new TextField();
    TextField leftField8 = new TextField();
    TextField leftField9 = new TextField();
    TextField leftField10 = new TextField();
    TextField leftField11 = new TextField();
    TextField leftField12 = new TextField();
    TextField leftField13 = new TextField();
    TextField leftField14 = new TextField();
    TextField leftField15 = new TextField();
    
    TextField rightField = new TextField();
    TextField rightField2 = new TextField();
    TextField rightField3 = new TextField();
    TextField rightField4 = new TextField();
    TextField rightField5 = new TextField();
    TextField rightField6 = new TextField();
    TextField rightField7 = new TextField();
    TextField rightField8 = new TextField();
    TextField rightField9 = new TextField();
    TextField rightField10 = new TextField();
    TextField rightField11 = new TextField();
    TextField rightField12 = new TextField();
    TextField rightField13 = new TextField();
    TextField rightField14 = new TextField();
    TextField rightField15 = new TextField();
    
    //setting textfield styles
    leftField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField7.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField8.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField9.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField10.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField11.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField12.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField13.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField14.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    leftField15.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField7.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField8.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField9.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField10.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField11.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField12.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField13.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField14.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    rightField15.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Initializing buttons
    Button buttonConfirm = new Button("Confirm"); 
    buttonConfirm.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    
    Button buttonNextPage = new Button("Next page");
    buttonNextPage.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    
    //Button - To scene 3
    buttonNextPage.setOnAction(action -> {
      showSceneThree(window, "Actual");
      trends.populateDiff(trends.income2D);
      trends.populateDiff(trends.expense2D);
    });
    
    //Button - Back to scene 1
    Button buttonScene2 = new Button("Back");
    buttonScene2.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    buttonScene2.setOnAction(action -> {
      window.setScene(scene1);
    });
    
    buttonConfirm.setOnAction(action ->{
      incomeCatArr.add((String) cBIncome.getValue());
      incomeCatArr.add((String) cBIncome2.getValue());
      incomeCatArr.add((String) cBIncome3.getValue());
      incomeCatArr.add((String) cBIncome4.getValue());
      incomeCatArr.add((String) cBIncome5.getValue());
      incomeCatArr.add((String) cBIncome6.getValue());
      incomeCatArr.add((String) cBIncome7.getValue());
      incomeCatArr.add((String) cBIncome8.getValue());
      incomeCatArr.add((String) cBIncome9.getValue());
      incomeCatArr.add((String) cBIncome10.getValue());
      incomeCatArr.add((String) cBIncome11.getValue());
      incomeCatArr.add((String) cBIncome12.getValue());
      incomeCatArr.add((String) cBIncome13.getValue());
      incomeCatArr.add((String) cBIncome14.getValue());
      incomeCatArr.add((String) cBIncome15.getValue());
      incomeAmtArr.add(leftField.getText());
      incomeAmtArr.add(leftField2.getText());
      incomeAmtArr.add(leftField3.getText());
      incomeAmtArr.add(leftField4.getText());
      incomeAmtArr.add(leftField5.getText());
      incomeAmtArr.add(leftField6.getText());
      incomeAmtArr.add(leftField7.getText());
      incomeAmtArr.add(leftField8.getText());
      incomeAmtArr.add(leftField9.getText());
      incomeAmtArr.add(leftField10.getText());
      incomeAmtArr.add(leftField11.getText());
      incomeAmtArr.add(leftField12.getText());
      incomeAmtArr.add(leftField13.getText());
      incomeAmtArr.add(leftField14.getText());
      incomeAmtArr.add(leftField15.getText());
      
      expenseCatArr.add((String) cBExpense.getValue());
      expenseCatArr.add((String) cBExpense2.getValue());
      expenseCatArr.add((String) cBExpense3.getValue());
      expenseCatArr.add((String) cBExpense4.getValue());
      expenseCatArr.add((String) cBExpense5.getValue());
      expenseCatArr.add((String) cBExpense6.getValue());
      expenseCatArr.add((String) cBExpense7.getValue());
      expenseCatArr.add((String) cBExpense8.getValue());
      expenseCatArr.add((String) cBExpense9.getValue());
      expenseCatArr.add((String) cBExpense10.getValue());
      expenseCatArr.add((String) cBExpense11.getValue());
      expenseCatArr.add((String) cBExpense12.getValue());
      expenseCatArr.add((String) cBExpense13.getValue());
      expenseCatArr.add((String) cBExpense14.getValue());
      expenseCatArr.add((String) cBExpense15.getValue());
      expenseAmtArr.add(rightField.getText());
      expenseAmtArr.add(rightField2.getText());
      expenseAmtArr.add(rightField3.getText());
      expenseAmtArr.add(rightField4.getText());
      expenseAmtArr.add(rightField5.getText());
      expenseAmtArr.add(rightField6.getText());
      expenseAmtArr.add(rightField7.getText());
      expenseAmtArr.add(rightField8.getText());
      expenseAmtArr.add(rightField9.getText());
      expenseAmtArr.add(rightField10.getText());
      expenseAmtArr.add(rightField11.getText());
      expenseAmtArr.add(rightField12.getText());
      expenseAmtArr.add(rightField13.getText());
      expenseAmtArr.add(rightField14.getText());
      expenseAmtArr.add(rightField15.getText());
      
      trends.populateActual(incomeCatArr, incomeAmtArr, trends.income2D, trends.incomeCat);
        trends.populateActual(expenseCatArr, expenseAmtArr, trends.expense2D, trends.expenseCat);
    });
    
    //Add action for buttonScene2
    // buttonScene2.setOnAction(e -> window.setScene(scene2));
    
    //Layout - Vertical column on the far left
    VBox farLeft = new VBox(10);
    farLeft.getChildren().addAll(labelAntInc);
    farLeft.getChildren().addAll(labelCategory);
    
    //Layout - Vertical column on the left
    VBox left = new VBox(12);
    left.getChildren().addAll(labelBlank, labelAntAm);
    
    //Layout - Vertical column on the right
    VBox right = new VBox(10);
    right.getChildren().addAll(labelAntExp);
    right.getChildren().addAll(labelCategory2);
    
    //Layout - Vertical column on the far right
    VBox farRight = new VBox(12);
    farRight.getChildren().addAll(labelBlank2, labelAntAm2);
    
    /*For loop not working with combobox
     //Labels - For left column        
     for (int i = 0; i < 6; i++)
     {
     Label label = new Label("Label");
     label.setFont(Font.font("Verdana", 12));
     farLeft.getChildren().add(label);
     }
     */
    
    farLeft.getChildren().addAll(cBIncome, cBIncome2, cBIncome3, cBIncome4, cBIncome5, cBIncome6, cBIncome7, cBIncome8, cBIncome9, cBIncome10, cBIncome11, cBIncome12, cBIncome13, cBIncome14, cBIncome15);
    
    left.getChildren().addAll(leftField, leftField2, leftField3, leftField4, leftField5, leftField6, leftField7, leftField8, leftField9, leftField10, leftField11, leftField12, leftField13, leftField14, leftField15); 
    
    /* For loop not working with combobox
     //Labels - For right column
     for (int i = 0; i < 6; i++)
     {
     Label label2 = new Label("Label");
     label2.setFont(Font.font("Verdana", 12)); 
     right.getChildren().add(label2);
     }
     */
    
    right.getChildren().addAll(cBExpense, cBExpense2, cBExpense3, cBExpense4, cBExpense5, cBExpense6, cBExpense7, cBExpense8, cBExpense9, cBExpense10, cBExpense11, cBExpense12, cBExpense13, cBExpense14, cBExpense15);
    
    farRight.getChildren().addAll(rightField, rightField2, rightField3, rightField4, rightField5, rightField6, rightField7, rightField8, rightField9, rightField10, rightField11, rightField12, rightField13, rightField14, rightField15);
    
    //Layout - Gathers the vertical columns on the far left and left together
    HBox hBoxLeft = new HBox(50);
    hBoxLeft.getChildren().addAll(farLeft, left);
    
    //Layout - Gathers the vertical columns on the right and far right together
    HBox hBoxRight = new HBox(50);
    hBoxRight.getChildren().addAll(right, farRight);
    
    //Layout - Top of the GUI: Title and Images (*ADD IMAGES HERE*)
    HBox hBoxTop = new HBox(50);
    hBoxTop.getChildren().addAll(title);
    hBoxTop.setAlignment(Pos.CENTER);
    
    //Layout - Middle of the GUI: Gather all the columns together
    HBox hBoxMiddle = new HBox(50);
    hBoxMiddle.getChildren().addAll(hBoxLeft, hBoxRight);
    hBoxMiddle.setAlignment(Pos.CENTER);
    
    //Layout - Bottom of the GUI: Buttons
    HBox hBoxBottom = new HBox(50);
    hBoxBottom.getChildren().addAll(buttonScene2, buttonConfirm, buttonNextPage);
    hBoxBottom.setAlignment(Pos.CENTER);
    
    //Layout - Vertical scrollbar on the far right
    ScrollPane scroll = new ScrollPane();
    scroll.setContent(hBoxMiddle);
    scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
    //scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    
    //Layout - centering ths middle
    HBox center = new HBox();
    center.getChildren().add(scroll);
    center.setAlignment(Pos.CENTER);
    
    //Layout - Entire GUI
    BorderPane mainScreen = new BorderPane();
    mainScreen.setTop(hBoxTop);
    mainScreen.setCenter(center);
    mainScreen.setBottom(hBoxBottom);
    
    //Display the entire GUI
    // scene1 = new Scene(mainScreen, 1000, 500);
    
    // accScene = showAccScene(stage, type);
    
    scene2 = new Scene(mainScreen, 1000, 500);
    
    window.setScene(scene2);
    
    // return accScene;
  }
  
  //-------------------- SCENE THREE BELOW --------------------//
  public void showSceneThree(Stage stage, String type){
    //Labels and formatting - Title and Headers
    Label title = new Label("Budgeting App");    
    Label labelIncome = new Label ("Income");
    Label labelExpenses = new Label ("Expenses");
    Label labelAnticipated = new Label ("Anticipated"); 
    Label labelAnticipated2 = new Label ("Anticipated"); 
    Label labelActual = new Label ("Actual"); 
    Label labelActual2 = new Label ("Actual");
    Label labelDiff = new Label ("Difference"); 
    Label labelDiff2 = new Label ("Difference"); 
    Label labelTotals = new Label ("Totals");
    Label labelTotals2 = new Label ("Totals"); 
    
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
    labelIncome.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    labelExpenses.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
    labelAnticipated.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    labelAnticipated2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    labelActual.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    labelActual2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    labelDiff.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    labelDiff2.setFont(Font.font("Verdana", FontWeight.BOLD, 12)); 
    labelTotals.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    labelTotals2.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
    
    //Label - Act as line break
    //NOTE: REPLACE LABEL BLANKS WITH ACC CALC. VALUES
    Label labelBlank = new Label ("");
    Label labelBlank2 = new Label ("");
    Label labelBlank3 = new Label ("");
    Label labelBlank4 = new Label ("");
    Label labelBlank5 = new Label ("");
    Label labelBlank6 = new Label ("");
    
    //Labels - For the table
    Label labelFarLeft = new Label();
    Label labelFarLeft2 = new Label();
    Label labelFarLeft3 = new Label();
    Label labelFarLeft4 = new Label();
    Label labelFarLeft5 = new Label();
    Label labelFarLeft6 = new Label();
    
    Label labelSecondLeft = new Label();
    Label labelSecondLeft2 = new Label();
    Label labelSecondLeft3 = new Label();
    Label labelSecondLeft4 = new Label();
    Label labelSecondLeft5 = new Label();
    Label labelSecondLeft6 = new Label();
    
    Label labelThirdLeft = new Label();
    Label labelThirdLeft2= new Label();
    Label labelThirdLeft3 = new Label();
    Label labelThirdLeft4 = new Label();
    Label labelThirdLeft5 = new Label();
    Label labelThirdLeft6 = new Label();
    
    Label labelFourthLeft = new Label();
    Label labelFourthLeft2 = new Label();
    Label labelFourthLeft3 = new Label();
    Label labelFourthLeft4 = new Label();
    Label labelFourthLeft5 = new Label();
    Label labelFourthLeft6 = new Label();
    
    //Text fields - For left column 
    labelFarLeft = new Label("");
    labelFarLeft2 = new Label("");
    labelFarLeft3 = new Label("");
    labelFarLeft4 = new Label("");
    labelFarLeft5 = new Label("");
    labelFarLeft6 = new Label("");
    
    labelFarLeft.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarLeft2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarLeft3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarLeft4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarLeft5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarLeft6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Text fields - For second left column 
    labelSecondLeft = new Label("");    
    labelSecondLeft2 = new Label("");
    labelSecondLeft3 = new Label("");
    labelSecondLeft4 = new Label("");
    labelSecondLeft5 = new Label("");
    labelSecondLeft6 = new Label("");
    
    labelSecondLeft.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondLeft2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondLeft3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondLeft4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondLeft5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondLeft6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Text fields - For third left column 
    labelThirdLeft = new Label("");
    labelThirdLeft2 = new Label("");
    labelThirdLeft3 = new Label("");
    labelThirdLeft4 = new Label("");
    labelThirdLeft5 = new Label("");
    labelThirdLeft6 = new Label("");
    
    labelThirdLeft.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdLeft2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdLeft3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdLeft4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdLeft5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdLeft6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Text fields - For fourth left column 
    labelFourthLeft = new Label("");    
    labelFourthLeft2 = new Label("");
    labelFourthLeft3 = new Label("");
    labelFourthLeft4 = new Label("");
    labelFourthLeft5 = new Label("");
    labelFourthLeft6 = new Label("");
    
    labelFourthLeft.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthLeft2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthLeft3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthLeft4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthLeft5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthLeft6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Text Fields
    Label labelFarRight = new Label();
    Label labelFarRight2 = new Label();
    Label labelFarRight3 = new Label();
    Label labelFarRight4 = new Label();
    Label labelFarRight5 = new Label();
    Label labelFarRight6 = new Label();
    
    Label labelSecondRight = new Label();
    Label labelSecondRight2 = new Label();
    Label labelSecondRight3 = new Label();
    Label labelSecondRight4 = new Label();
    Label labelSecondRight5 = new Label();
    Label labelSecondRight6 = new Label();
    
    Label labelThirdRight = new Label();
    Label labelThirdRight2 = new Label();
    Label labelThirdRight3 = new Label();
    Label labelThirdRight4 = new Label();
    Label labelThirdRight5 = new Label();
    Label labelThirdRight6 = new Label();
    
    Label labelFourthRight = new Label();
    Label labelFourthRight2 = new Label();
    Label labelFourthRight3 = new Label();
    Label labelFourthRight4 = new Label();
    Label labelFourthRight5 = new Label();
    Label labelFourthRight6 = new Label();
    
    //Text fields - For right column 
    labelFarRight = new Label("");
    labelFarRight2 = new Label("");
    labelFarRight3 = new Label("");
    labelFarRight4 = new Label("");
    labelFarRight5 = new Label("");
    labelFarRight6 = new Label("");
    
    labelFarRight.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarRight2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarRight3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarRight4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarRight5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFarRight6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Text fields - For second right column 
    labelSecondRight = new Label("");    
    labelSecondRight2 = new Label("");
    labelSecondRight3 = new Label("");
    labelSecondRight4 = new Label("");
    labelSecondRight5 = new Label("");
    labelSecondRight6 = new Label("");
    
    labelSecondRight.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondRight2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondRight3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondRight4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondRight5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelSecondRight6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Text fields - For third right column 
    labelThirdRight = new Label("");
    labelThirdRight2 = new Label("");
    labelThirdRight3 = new Label("");
    labelThirdRight4 = new Label("");
    labelThirdRight5 = new Label("");
    labelThirdRight6 = new Label("");
    
    labelThirdRight.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdRight2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdRight3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdRight4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdRight5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelThirdRight6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Text fields - For fourth right column 
    labelFourthRight = new Label("");
    labelFourthRight2 = new Label("");
    labelFourthRight3 = new Label("");
    labelFourthRight4 = new Label("");
    labelFourthRight5 = new Label("");
    labelFourthRight6 = new Label("");
    
    labelFourthRight.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthRight2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthRight3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthRight4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthRight5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    labelFourthRight6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    //Back button - goes back to scene 2
    Button buttonScene2 = new Button("Back");
    buttonScene2.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    buttonScene2.setOnAction(action -> {
      window.setScene(scene2);
    });
    
    //Layout
    VBox vBoxFarLeft = new VBox(10);
    vBoxFarLeft.getChildren().addAll(labelIncome, labelTotals, labelFarLeft, labelFarLeft2, labelFarLeft3, labelFarLeft4, labelFarLeft5, labelFarLeft6);
    
    VBox vBoxSecondLeft = new VBox(10);
    vBoxSecondLeft.getChildren().addAll(labelAnticipated, labelBlank, labelSecondLeft, labelSecondLeft2, labelSecondLeft3, labelSecondLeft4, labelSecondLeft5, labelSecondLeft6);
    
    VBox vBoxThirdLeft = new VBox(10);
    vBoxThirdLeft.getChildren().addAll(labelActual, labelBlank2, labelThirdLeft, labelThirdLeft2, labelThirdLeft3, labelThirdLeft4, labelThirdLeft5, labelThirdLeft6);
    
    VBox vBoxFourthLeft = new VBox(10);
    vBoxFourthLeft.getChildren().addAll(labelDiff, labelBlank3, labelFourthLeft, labelFourthLeft2, labelFourthLeft3, labelFourthLeft4, labelFourthLeft5, labelFourthLeft6);
    
    HBox leftTable = new HBox(10);
    leftTable.getChildren().addAll(vBoxFarLeft, vBoxSecondLeft, vBoxThirdLeft, vBoxFourthLeft);
    
    VBox vBoxFarRight = new VBox(10);
    vBoxFarRight.getChildren().addAll(labelDiff2, labelBlank4, labelFarRight, labelFarRight2, labelFarRight3, labelFarRight4, labelFarRight5, labelFarRight6);
    
    VBox vBoxSecondRight = new VBox(10);
    vBoxSecondRight.getChildren().addAll(labelActual2, labelBlank5, labelSecondRight, labelSecondRight2, labelSecondRight3, labelSecondRight4, labelSecondRight5, labelSecondRight6);
    
    VBox vBoxThirdRight = new VBox(10);
    vBoxThirdRight.getChildren().addAll(labelAnticipated2, labelBlank6, labelThirdRight, labelThirdRight2, labelThirdRight3, labelThirdRight4, labelThirdRight5, labelThirdRight6);
    
    VBox vBoxFourthRight = new VBox(10);
    vBoxFourthRight.getChildren().addAll(labelExpenses, labelTotals2, labelFourthRight, labelFourthRight2, labelFourthRight3, labelFourthRight4, labelFourthRight5, labelFourthRight6);
    
    HBox rightTable = new HBox(10);
    rightTable.getChildren().addAll(vBoxFourthRight, vBoxThirdRight, vBoxSecondRight, vBoxFarRight);
    
    HBox hBoxMiddle = new HBox(20);
    hBoxMiddle.getChildren().addAll(leftTable, rightTable);
    hBoxMiddle.setAlignment(Pos.CENTER);
    
    //Layout - Entire GUI: Gathers the top, middle and bottom to make the entire GUI
    VBox mainScreen = new VBox(25);
    mainScreen.getChildren().addAll(title, hBoxMiddle, buttonScene2);
    mainScreen.setAlignment(Pos.CENTER);
    
    scene3 = new Scene(mainScreen, 1000, 500);
    
    window.setScene(scene3);
  }
  
  //Doc strings here 
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
  
  // some extra stuff to figure out later
  // Popup emptyMonth = new Popup();
  // Label errorMessage = new Label("Please choose a Month before continuing!");
  // if (cBMonths.getValue() == null){
  //     emptyMonth.getContent().add(errorMessage);
  // }    
  
}