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
import javafx.collections.FXCollections;
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
    //For spacing/formatting
    public Label spacing(){
        Label space = new Label("                        ");
        return space;
    }
    
    //Set label formatting
    public Label setFont(String name, int size){
        Label labelFont = new Label(name);
        labelFont.setFont(Font.font("Verdana", FontWeight.BOLD, size));
        return labelFont;
    }
    /*-------------------- LABELS WE USE OFTEN ------------------ */
    //Label that says category
    public Label catLabel(){
        Label cLabel = setFont("Category : ", 12);
        return cLabel;
    }
    //Label that says amount
    public Label amntLabel(){
        Label aLabel = setFont("Amount : ", 12);
        return aLabel;
    }
    //Label that says income
    public Label incomeLabel(){
        Label label = setFont("INCOME", 15);
        return label;
    }
    //Label that says expense
    public Label expenseLabel(){
        Label label = setFont("EXPENSE", 15);
        return label;
    }
    //Label that says anticipated
    public Label anticipatedLabel(){
        Label label = setFont("ANTICIPATED", 12);
        return label;
    }
    //Label that says actual
    public Label actuaLabel(){
        Label label = setFont("ACTUAL", 12);
        return label;
    }
    //Label that says difference
    public Label diffLabel(){
        Label label = setFont("DIFFERENCE", 12);
        return label;
    }
    ////Label that says totals
    public Label totaLabel(){
        Label label = setFont("TOTALS", 12);
        return label;
    }
    /*----------------------------------------------------------- */
    
    //Formatting for buttons (makes them yellow)
    public Button yellowButton(String name){
        Button yellowB = new Button(name);
        yellowB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        return yellowB;
    }
    //Formatting for "add" buttons
    public Button addButton(){
        Button addB = new Button("ADD");
        addB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        return addB;
    }
    //Formatting for "delete" buttons
    public Button delButton(){
        Button delB = new Button("DELETE");
        delB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        return delB;
    }
    //Formatting for "confirm" buttons
    public Button confirmButton(Stage stage){
        Button confirm = new Button("Confirm");
        return confirm;
    }
    //Default amount value is "0.00"
    public TextField amntT(){
        TextField amountT = new TextField("0.00");
        return amountT;
    }
    
    //BorderPane formatting with proper spacing
    public BorderPane showBorder(VBox center){
        BorderPane bPane = new BorderPane();

        Label topSpace = spacing();
        Label bottomSpace = spacing();
        Label leftSpace = spacing();
        Label rightSpace = spacing();

        //Setting the borders
        bPane.setTop(topSpace);
        bPane.setBottom(bottomSpace);
        bPane.setLeft(leftSpace);
        bPane.setRight(rightSpace);
        bPane.setCenter(center);
        return bPane;
    } 
    
    //ScrollPane formatting and initializing 
    public ScrollPane showScrollPane(BorderPane contents){
        ScrollPane scroll = new ScrollPane();
        scroll.setContent(contents);
        scroll.setHbarPolicy(ScrollBarPolicy.NEVER);
        scroll.setStyle("-fx-background-color:transparent;");
        //scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        return scroll;
    }
    
    //Stack pane formatting and initializing
    public StackPane showSPane(Text cat, Text amnt){
        StackPane sPane = new StackPane();
        
        //Initialize rectangles and fill colour
        Rectangle rectangle = new Rectangle(100,150,900,150);
        rectangle.setFill(babyBlue);

        Label space = spacing(); //Formatting
        // testing

        // VBox cATotal = new VBox(10);
        // cATotal.getChildren().addAll(cat, amnt);

        // HBox catAmntBox = new HBox(10);
        // catAmntBox.getChildren().addAll(space, cATotal);
        // catAmntBox.setAlignment(Pos.TOP_LEFT);
        
        //Gathers components and formats the HBox
        HBox catAmntBox = new HBox(10);
        catAmntBox.getChildren().addAll(cat, space, amnt);
        catAmntBox.setAlignment(Pos.TOP_CENTER);
        catAmntBox.setBackground(new Background(new BackgroundFill(babyBlue, CornerRadii.EMPTY, Insets.EMPTY)));
        sPane.getChildren().addAll(rectangle, catAmntBox);
        return sPane;
    }
    
    //Pre-set combobox with months 
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
    // public ComboBox comboBoxIncome(String[][] income2D){
    //    
    //     ComboBox incomeCat = new ComboBox();
    //     incomeCat.getItems().addAll(
    //                                 "Savings", "Paycheck", "Bonus",
    //                                 "Interest", "Allowance", "Other"
    //                                );
    //     incomeCat.setPromptText("Select Category");
    //     incomeCat.setEditable(false);
        
    //     return incomeCat;
    // }
    
    //Combobox for customizing income categories 
    public ComboBox comboBoxIncome(String[][] income2D){        
        System.out.println("combo box printing");
        for(int i = 0; i < trends.income2D.length; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(trends.income2D[i][j] + ", ");
            } 
            System.out.println();
        }
        String temp = trends.income2D[1][0];
        ComboBox incomeCat = new ComboBox();        
        // if(income2D[1][0] == null){
        //     incomeCat.getItems().addAll(                            
        //             "Savings", "Paycheck", "Bonus",
        //                 "Interest", "Allowance", "Other"
        //     );
        // }
        // else{
            System.out.println("entering their categories");
            for(int i = 1; i < income2D.length; i++){                
                    incomeCat.getItems().addAll(
                        income2D[i][0]
                    );
            }            
        // }        
        incomeCat.setPromptText("Select Category");
        incomeCat.setEditable(false);
        return incomeCat;
    }
    
    //Combobox for expense categories
    public ComboBox comboBoxExpense(String[][] expense2D){
        String temp = trends.income2D[1][0];
        ComboBox expenseCat = new ComboBox();  
        
        //If the user does not input any of their own expense categories, default categories are given 
        if(expense2D[1][0] == null){
            expenseCat.getItems().addAll(                            
                "Food", "Health", "Transportation", 
                "Utilies", "Personal","Other"
            );
        }
        //If the user inputs customized expense categories
        else{
            System.out.println("entering their categories");
            for(int i = 1; i < expense2D.length; i++){                
                expenseCat.getItems().addAll(
                    expense2D[i][0]
                    );
            }            
        }                                             
        expenseCat.setPromptText("Select Category");
        expenseCat.setEditable(false);
        return expenseCat;
    }
    
    //For bee image to display
    public ImageView image(){
        //Create an image object
        Image bee = new Image("bee.png");
        
        //Creating ImageView for adding image
        ImageView imageView = new ImageView();
        imageView.setImage(bee);
        imageView.setFitWidth(75);
        imageView.setPreserveRatio(true);
        imageView.setSmooth(true);
        imageView.setCache(true);
        return imageView;
    }
}