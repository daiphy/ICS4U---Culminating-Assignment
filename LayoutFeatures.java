import java.io.File;
import javafx.stage.FileChooser;
import javafx.application.Application;
// import javafx.collections.ObservableList;
// import javafx.scene.Node;
// import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField; 
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos; 


public class LayoutFeatures{ 

    //-------------------- GLOBAL VARIABLES --------------------//
    Scene sceneFour, sceneFive, sceneSix, sceneSeven;
    Stage window;
    Color babyBlue = Color.web("#C9DAF8");
    ComboBox cBMonths, cBIncFour, cBIncFive, cBExpFour, cBExpFive;
    // StackPane stackPane = new StackPane();
    String emptyCat = "";
    String emptyAmnt = "";
    int y = 150; 

    //-------------------- CONSTRUCTOR --------------------// 
    public LayoutFeatures(){
        
    }
 
    //-------------------- FEATURE METHODS --------------------//    

    public StackPane showSPane(){
        StackPane sPane = new StackPane();

        Label rSpace = spacing();

        Rectangle rectangle = new Rectangle(100,150,900,150);
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
    public Button addButton(ComboBox cBTrans, TextField amountT, Text cat, Text amnt, String emptyAmnt, String emptyCat){
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
                // emptyCat += showCatI;
                // emptyAmnt += showAmntI;
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
    public Button nextButton(Stage stage, Scene sceneFive){
        Button nextB = new Button("NEXT");
        nextB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        nextB.setOnAction(action -> {
            // if(whichType.equals("Anticipated")){
                stage.setScene(sceneFive);
            // }
        });
        return nextB;
    }
    public Button backButton(Stage stage, Scene sceneFour){
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
}