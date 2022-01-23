import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
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
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.geometry.Pos; 
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LayoutFeatures{ 

    //-------------------- GLOBAL VARIABLES --------------------//
    // Scene sceneFour, sceneFive, sceneSix, sceneSeven;
    // Stage window;
    Color babyBlue = Color.web("#C9DAF8");
    // ComboBox cBMonths, cBIncFour, cBIncFive, cBExpFour, cBExpFive;
    // // StackPane stackPane = new StackPane();
    // String emptyCat = "";
    // String emptyAmnt = "";
    // int y = 150; 
    
    public Trends trends = new Trends();

    //-------------------- CONSTRUCTOR --------------------// 
    public LayoutFeatures(){
        
    }
 
    //-------------------- FEATURE METHODS --------------------//    

    public Label spacing(){
        Label space = new Label("                        ");
        return space;
    }
    public Label setFont(String name, int size){
        Label labelFont = new Label(name);
        labelFont.setFont(Font.font("Verdana", FontWeight.BOLD, size));
        return labelFont;
    }
    /*-------------------- LABELS WE USE OFTEN ------------------ */
    public Label catLabel(){
        Label cLabel = setFont("Category : ", 12);
        return cLabel;
    }
    public Label amntLabel(){
        Label aLabel = setFont("Amount : ", 12);
        return aLabel;
    }
    public Label incomeLabel(){
        Label label = setFont("INCOME", 15);
        return label;
    }
    public Label expenseLabel(){
        Label label = setFont("EXPENSE", 15);
        return label;
    }
    public Label anticipatedLabel(){
        Label label = setFont("ANTICIPATED", 12);
        return label;
    }
    public Label actuaLabel(){
        Label label = setFont("ACTUAL", 12);
        return label;
    }
    public Label diffLabel(){
        Label label = setFont("DIFFERENCE", 12);
        return label;
    }
    public Label totaLabel(){
        Label label = setFont("TOTALS", 12);
        return label;
    }
    /*----------------------------------------------------------- */

    public Button yellowButton(String name){
        Button yellowB = new Button(name);
        yellowB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        return yellowB;
    }
    public Button addButton(){
        Button addB = new Button("ADD");
        addB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        return addB;
    }
    public Button delButton(){
        Button delB = new Button("DELETE");
        delB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        return delB;
    }
    public Button confirmButton(Stage stage){
        Button confirm = new Button("Confirm");
        
        return confirm;
    
    }
    public TextField amntT(){
        TextField amountT = new TextField("0.00");
        return amountT;
    }
    public BorderPane showBorder(VBox center){
        BorderPane bPane = new BorderPane();

        Label topSpace = spacing();
        Label bottomSpace = spacing();
        Label leftSpace = spacing();
        Label rightSpace = spacing();

        // setting the borders
        bPane.setTop(topSpace);
        bPane.setBottom(bottomSpace);
        bPane.setLeft(leftSpace);
        bPane.setRight(rightSpace);
        bPane.setCenter(center);

        return bPane;
    }   
    public ScrollPane showScrollPane(BorderPane contents){
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(contents);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color:transparent;");
        //scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);

        return scroll;
    }
    public StackPane showSPane(Text cat, Text amnt){
        StackPane sPane = new StackPane();

        Rectangle rectangle = new Rectangle(100,150,900,150);
        rectangle.setFill(babyBlue);

        Label space = spacing();
        // testing

        // VBox cATotal = new VBox(10);
        // cATotal.getChildren().addAll(cat, amnt);

        // HBox catAmntBox = new HBox(10);
        // catAmntBox.getChildren().addAll(space, cATotal);
        // catAmntBox.setAlignment(Pos.TOP_LEFT);

        HBox catAmntBox = new HBox(10);
        catAmntBox.getChildren().addAll(cat, space, amnt);
        catAmntBox.setAlignment(Pos.TOP_CENTER);
        catAmntBox.setBackground(new Background(new BackgroundFill(babyBlue, CornerRadii.EMPTY, Insets.EMPTY)));

        sPane.getChildren().addAll(rectangle, catAmntBox);

        return sPane;
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
}