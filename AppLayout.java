import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField; 
import javafx.scene.layout.HBox;
import javafx.geometry.Pos; //For alignment

public class AppLayout extends Application {
  
  Stage window;
  Scene scene1, scene2;
  public String[] arr = new String[12];
  ComboBox cBMonths;
  ComboBox cBIncome, cBIncome2, cBIncome3, cBIncome4, cBIncome5, cBIncome6, cBIncome7, cBIncome8, cBIncome9, cBIncome10, cBIncome11, cBIncome12, cBIncome13, cBIncome14, cBIncome15; 
  ComboBox cBExpense, cBExpense2, cBExpense3, cBExpense4, cBExpense5, cBExpense6, cBExpense7, cBExpense8, cBExpense9, cBExpense10, cBExpense11, cBExpense12, cBExpense13, cBExpense14, cBExpense15;
  
  // constructor method
  public AppLayout(){
  }
  
  @Override
  public void start(Stage primaryStage) throws Exception{
    Finance finance = new Finance();
    
    window = primaryStage;

    showSceneOne(window);
  }
    //-------------------- SCENE ONE BELOW --------------------//
  public void showSceneOne(Stage stage){
//Labels and formatting - Title and Headers
    Label title = new Label("Budgeting App");
    title.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
    
    cBMonths = comboBoxMonths();
    
    cBIncome = comboBoxIncome();
    cBIncome2 = comboBoxIncome();
    cBIncome3 = comboBoxIncome();
    cBIncome4 = comboBoxIncome();
    cBIncome5 = comboBoxIncome();
    
    cBExpense = comboBoxExpense();
    cBExpense2 = comboBoxExpense();
    cBExpense3 = comboBoxExpense();
    cBExpense4 = comboBoxExpense();
    cBExpense5 = comboBoxExpense();
    
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
    
    //Buttons & Actions
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
    
    //Add action for buttonScene2
    // buttonScene2.setOnAction(e -> window.setScene(scene2));
    buttonScene2.setOnAction(action -> {
      showSceneTwo(window, "Actual");
      // window.setScene(accScene);
    });
    
    //String[] arr = new String[12];
    
    //Add action for buttonConfirm
    buttonConfirm.setOnAction(e -> {                 
      // arr[0] = (leftField.getText());  
      // arr[1] = (leftField2.getText());  
      // arr[2] = (leftField3.getText());  
      // arr[3] = (leftField4.getText());  
      // arr[4] = (leftField5.getText());  
      // arr[5] = (leftField6.getText());  
      // arr[6] = (rightField.getText());  
      // arr[7] = (rightField2.getText());  
      // arr[8] = (rightField3.getText());  
      // arr[9] = (rightField4.getText());  
      // arr[10] = (rightField5.getText());  
      // arr[11] = (rightField6.getText());  
      // //populating arrays 
      // finance.populate(arr, finance.antIncomeArr, finance.antExpenseArr);
      
      selectMonth.setText(cBMonths.getValue() + " selected");
    });
    
    //Layout - Vertical column on the far left
    VBox farLeft = new VBox(10);
    farLeft.getChildren().addAll(labelAntInc);
    farLeft.getChildren().addAll(labelCategory);
    
    //Layout - Vertical column on the left
    VBox left = new VBox(10);
    left.getChildren().addAll(labelBlank, labelAntAm);
    
    //Layout - Vertical column on the right
    VBox right = new VBox(10);
    right.getChildren().addAll(labelAntExp);
    right.getChildren().addAll(labelCategory2);
    
    //Layout - Vertical column on the far right
    VBox farRight = new VBox(10);
    farRight.getChildren().addAll(labelBlank2, labelAntAm2);
    
    /* For loop for combo box is not working
     //Labels - For left column        
     for (int i = 0; i < 6; i++)
     {
     //Label label = new Label("Label");
     //label.setFont(Font.font("Verdana", 12));
     farLeft.getChildren().add(cBIncome);
     }
     */
    
    farLeft.getChildren().addAll(cBIncome, cBIncome2, cBIncome3, cBIncome4, cBIncome5);
    
    //Text fields - For left column 
    leftField = new TextField("");
    leftField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField2 = new TextField("");
    leftField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField3 = new TextField("");
    leftField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField4 = new TextField("");
    leftField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField5 = new TextField("");
    leftField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField6 = new TextField("");
    leftField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    
    //Text fields - For left column 
    leftField = new TextField("");
    leftField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField2 = new TextField("");
    leftField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField3 = new TextField("");
    leftField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField4 = new TextField("");
    leftField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField5 = new TextField("");
    leftField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField6 = new TextField("");
    leftField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    left.getChildren().addAll(leftField, leftField2, leftField3, leftField4, leftField5, leftField6); 
    
    /* For loop not working for combo box
    //Labels - For right column
    for (int i = 0; i < 6; i++)
    {
      Label label2 = new Label("Label");
      label2.setFont(Font.font("Verdana", 12)); 
      right.getChildren().add(label2);
    }
    */
    
    right.getChildren().addAll(cBExpense, cBExpense2, cBExpense3, cBExpense4, cBExpense5);
    
    //Text fields - For right column 
    rightField = new TextField("");
    rightField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField2 = new TextField("");
    rightField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField3 = new TextField("");
    rightField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField4 = new TextField("");
    rightField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField5 = new TextField("");
    rightField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField6 = new TextField("");
    rightField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    
    //Text fields - For right column 
    rightField = new TextField("");
    rightField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField2 = new TextField("");
    rightField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField3 = new TextField("");
    rightField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField4 = new TextField("");
    rightField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField5 = new TextField("");
    rightField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField6 = new TextField("");
    rightField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
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
    stage.setTitle("bonjour");
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
    Label title = new Label("Budgeting App PAGE 2");
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
    
    //Buttons & Actions
    Button buttonConfirm = new Button("Confirm"); 
    buttonConfirm.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    
    Button buttonNextPage = new Button("Next page");
    buttonNextPage.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    
    //Button 2
    Button button2 = new Button("Back");
    button2.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
    button2.setOnAction(action -> {
        window.setScene(scene1);
    });
    
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
    
    //Add action for buttonScene2
    // buttonScene2.setOnAction(e -> window.setScene(scene2));
    
    
    //Layout - Vertical column on the far left
    VBox farLeft = new VBox(10);
    farLeft.getChildren().addAll(labelAntInc);
    farLeft.getChildren().addAll(labelCategory);
    
    //Layout - Vertical column on the left
    VBox left = new VBox(10);
    left.getChildren().addAll(labelBlank, labelAntAm);
    
    //Layout - Vertical column on the right
    VBox right = new VBox(10);
    right.getChildren().addAll(labelAntExp);
    right.getChildren().addAll(labelCategory2);
    
    //Layout - Vertical column on the far right
    VBox farRight = new VBox(10);
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
    
    //Text fields - For left column 
    leftField = new TextField("");
    leftField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField2 = new TextField("");
    leftField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField3 = new TextField("");
    leftField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField4 = new TextField("");
    leftField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField5 = new TextField("");
    leftField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField6 = new TextField("");
    leftField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField7 = new TextField("");
    leftField7.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField8 = new TextField("");
    leftField8.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField9 = new TextField("");
    leftField9.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField10 = new TextField("");
    leftField10.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField11 = new TextField("");
    leftField11.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField12 = new TextField("");
    leftField12.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField13 = new TextField("");
    leftField13.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField14 = new TextField("");
    leftField14.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    leftField15 = new TextField("");
    leftField15.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
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
    
    //Text fields - For right column 
    rightField = new TextField("");
    rightField.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField2 = new TextField("");
    rightField2.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField3 = new TextField("");
    rightField3.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField4 = new TextField("");
    rightField4.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField5 = new TextField("");
    rightField5.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField6 = new TextField("");
    rightField6.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField7 = new TextField("");
    rightField7.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField8 = new TextField("");
    rightField8.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField9 = new TextField("");
    rightField9.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField10 = new TextField("");
    rightField10.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField11 = new TextField("");
    rightField11.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField12 = new TextField("");
    rightField12.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField13 = new TextField("");
    rightField13.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField14 = new TextField("");
    rightField14.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
    rightField15 = new TextField("");
    rightField15.setStyle("-fx-font: 12 arial; -fx-background-color: #c9daf8;");
    
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
    hBoxBottom.getChildren().addAll(buttonConfirm, buttonNextPage, button2);
    hBoxBottom.setAlignment(Pos.CENTER);
    
    //Layout - Entire GUI: Gathers the top, middle and bottom to make the entire GUI
    VBox mainScreen = new VBox(25);
    mainScreen.getChildren().addAll(hBoxTop, hBoxMiddle, hBoxBottom);
    mainScreen.setAlignment(Pos.CENTER);
    
    //Display the entire GUI
    // scene1 = new Scene(mainScreen, 1000, 500);
    
    // accScene = showAccScene(stage, type);
    
    scene2 = new Scene(mainScreen, 1000, 500);
    
    window.setScene(scene2);
    
    // return accScene;
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
  
  // some extra stuff to figure out later
  // Popup emptyMonth = new Popup();
  // Label errorMessage = new Label("Please choose a Month before continuing!");
  // if (cBMonths.getValue() == null){
  //     emptyMonth.getContent().add(errorMessage);
  // }    
  
}