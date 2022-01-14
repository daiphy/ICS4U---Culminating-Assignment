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
import javafx.scene.layout.HBox;
import javafx.geometry.Pos; //For alignment
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
  
  //Initialize images
  ImageView bee, bee2;
  
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
    //Images
    bee = image();
    bee2 = image();
    
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
    // String[] labelCatArr = new String[]{"Savings", "Paycheck", "Bonus", "Interest", "Allowance", "Others"};    
    
    //Prints out the elements inside labelCatArr on the far left side
    for (int i = 0; i < trends.incomeCat.length; i++){
      Label labelCat = new Label(trends.incomeCat[i]);
      labelCat.setFont(Font.font("Verdana", 12));
      farLeft.getChildren().add(labelCat);
    }
    
    left.getChildren().addAll(leftField, leftField2, leftField3, leftField4, leftField5, leftField6); 
    
    //Prints out the elements inside labelCatArr on the right side
    for (int i = 0; i < trends.expenseCat.length; i++){
      Label labelCat = new Label(trends.expenseCat[i]);
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
    hBoxTop.getChildren().addAll(bee, title, bee2);
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
    scene1 = new Scene(mainScreen, 1000, 600);    
    
    stage.setScene(scene1);
    stage.setTitle("Let's Talk Finance");
    stage.show();
  }
  
  //-------------------- SCENE TWO BELOW --------------------//
  public void showSceneTwo(Stage stage, String type){
    //Images
    bee = image();
    bee2 = image();
    
    //Combo boxes
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
      trends.populateActual(incomeCatArr, incomeAmtArr, trends.income2D, trends.incomeCat);
      trends.populateActual(expenseCatArr, expenseAmtArr, trends.expense2D, trends.expenseCat);
      trends.populateDiff(trends.income2D);
      trends.populateDiff(trends.expense2D);    
      showSceneThree(window, "Actual");  
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
    hBoxRight.getChildren().addAll(right, farRight, new Label());
    
    //Layout - Top of the GUI: Title and Images (*ADD IMAGES HERE*)
    HBox hBoxTop = new HBox(50);
    hBoxTop.getChildren().addAll(bee, title, bee2);
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
    scroll.setStyle("-fx-background-color:transparent;");
    //scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
    
    //Layout - centering ths middle
    HBox center = new HBox();
    center.getChildren().add(scroll);
    center.setAlignment(Pos.CENTER);
    
    //Layout - Entire GUI
    VBox mainScreen = new VBox();
    mainScreen.getChildren().addAll(hBoxTop, new Label(), center, new Label(), hBoxBottom, new Label());
    
    //Display the entire GUI
    // scene1 = new Scene(mainScreen, 1000, 500);
    
    // accScene = showAccScene(stage, type);
    
    scene2 = new Scene(mainScreen, 1000, 600);
    
    window.setScene(scene2);
    
    // return accScene;
  }
  
  //-------------------- SCENE THREE BELOW --------------------//
  public void showSceneThree(Stage stage, String type){
    //Images
    bee = image();
    bee2 = image();
    
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
    
    //Back button - goes back to scene 2
    Button buttonScene2 = new Button("Back");
    buttonScene2.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    buttonScene2.setOnAction(action -> {
      window.setScene(scene2);
    });
    
    //Far left
    VBox vBoxFarLeft = new VBox(10);
    vBoxFarLeft.getChildren().addAll(labelIncome, labelTotals);
    
    //Prints out the elements inside labelCatArr on the far left side
    for (int i = 0; i < trends.income2D[0].length; i++){
      Label labelFarLeft = new Label(trends.income2D[i][0]);
      labelFarLeft.setFont(Font.font("Verdana", 12));
      vBoxFarLeft.getChildren().add(labelFarLeft);
    }    
    
    //Second left
    VBox vBoxSecondLeft = new VBox(10);
    vBoxSecondLeft.getChildren().addAll(labelAnticipated, labelBlank);
    
    for (int i = 0; i < trends.income2D[0].length; i++){
      Label labelSecondLeft = new Label(trends.income2D[i][1]);
      labelSecondLeft.setFont(Font.font("Verdana", 12));
      vBoxSecondLeft.getChildren().add(labelSecondLeft);
    }   
    
    //Third left
    VBox vBoxThirdLeft = new VBox(10);
    vBoxThirdLeft.getChildren().addAll(labelActual, labelBlank2);
    
    for (int i = 0; i < trends.income2D[0].length; i++){
      Label labelThirdLeft = new Label(trends.income2D[i][2]);
      labelThirdLeft.setFont(Font.font("Verdana", 12));
      vBoxThirdLeft.getChildren().add(labelThirdLeft);
    }   
    
    //Fourth left
    VBox vBoxFourthLeft = new VBox(10);
    vBoxFourthLeft.getChildren().addAll(labelDiff, labelBlank3);
    
    for (int i = 0; i < trends.income2D[0].length; i++){
      Label labelFourthLeft = new Label(trends.income2D[i][3]);
      labelFourthLeft.setFont(Font.font("Verdana", 12));
      vBoxFourthLeft.getChildren().add(labelFourthLeft);
    }   
    
    HBox leftTable = new HBox(10);
    leftTable.getChildren().addAll(vBoxFarLeft, vBoxSecondLeft, vBoxThirdLeft, vBoxFourthLeft);
    
    //Fourth right
    VBox vBoxFourthRight = new VBox(10);
    vBoxFourthRight.getChildren().addAll(labelExpenses, labelTotals2);
    
    //Prints out the elements inside labelCatArr on the far left side
    for (int i = 0; i < trends.expense2D[0].length; i++){
      Label labelFourthRight = new Label(trends.expense2D[i][0]);
      labelFourthRight.setFont(Font.font("Verdana", 12));
      vBoxFourthRight.getChildren().add(labelFourthRight);
    }
    
    //Third right
    VBox vBoxThirdRight = new VBox(10);
    vBoxThirdRight.getChildren().addAll(labelAnticipated2, labelBlank6);
    
    for (int i = 0; i < trends.expense2D[0].length; i++){
      Label labelThirdRight = new Label(trends.expense2D[i][1]);
      labelThirdRight.setFont(Font.font("Verdana", 12));
      vBoxThirdRight.getChildren().add(labelThirdRight);
    }
    
    //Second right
    VBox vBoxSecondRight = new VBox(10);
    vBoxSecondRight.getChildren().addAll(labelActual2, labelBlank5);
    
    for (int i = 0; i < trends.expense2D[0].length; i++){
      Label labelSecondRight = new Label(trends.expense2D[i][2]);
      labelSecondRight.setFont(Font.font("Verdana", 12));
      vBoxSecondRight.getChildren().add(labelSecondRight);
    }
    
    //Far right
    VBox vBoxFarRight = new VBox(10);
    vBoxFarRight.getChildren().addAll(labelDiff2, labelBlank4);
    
    for (int i = 0; i < trends.expense2D[0].length; i++){
      Label labelFarRight = new Label(trends.expense2D[i][3]);
      labelFarRight.setFont(Font.font("Verdana", 12));
      vBoxFarRight.getChildren().add(labelFarRight);
    }   
    
    HBox rightTable = new HBox(10);
    rightTable.getChildren().addAll(vBoxFourthRight, vBoxThirdRight, vBoxSecondRight, vBoxFarRight);
    
    HBox hBoxTop = new HBox(10);
    hBoxTop.getChildren().addAll(bee, title, bee2);
    hBoxTop.setAlignment(Pos.CENTER);
    
    HBox hBoxMiddle = new HBox(20);
    hBoxMiddle.getChildren().addAll(leftTable, rightTable);
    hBoxMiddle.setAlignment(Pos.CENTER);
    
    //Layout - Entire GUI: Gathers the top, middle and bottom to make the entire GUI
    VBox mainScreen = new VBox(25);
    mainScreen.getChildren().addAll(hBoxTop, hBoxMiddle, buttonScene2);
    mainScreen.setAlignment(Pos.CENTER);
    
    scene3 = new Scene(mainScreen, 1000, 600);
    
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
  public ImageView image(){
    // Create a image object
    Image bee = new Image("bee.png");
    
    //creating ImageView for adding image
    ImageView imageView = new ImageView();
    imageView.setImage(bee);
    imageView.setFitWidth(75);
    imageView.setPreserveRatio(true);
    imageView.setSmooth(true);
    imageView.setCache(true);
    
    return imageView;
  }
  
  // some extra stuff to figure out later
  // Popup emptyMonth = new Popup();
  // Label errorMessage = new Label("Please choose a Month before continuing!");
  // if (cBMonths.getValue() == null){
  //     emptyMonth.getContent().add(errorMessage);
  // }    
  
}