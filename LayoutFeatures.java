// import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.stage.Popup;
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

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    
    public FinanceFV finance = new FinanceFV();
    public Trends trends = new Trends();

    //-------------------- CONSTRUCTOR --------------------// 
    public LayoutFeatures(){
        
    }
 
    //-------------------- FEATURE METHODS --------------------//    

    // ---------------------------- LABELS ------------------------------ //
    public Label spacing(){
        Label space = new Label("                        ");
        return space;
    }
    public Label setFont(String name, int size){
        Label labelFont = new Label(name);
        labelFont.setFont(Font.font("Verdana", FontWeight.BOLD, size));
        return labelFont;
    }
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
    // ---------------------------- BUTTONS ------------------------------ //
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
    // ---------------------------- TEXTFIELD ------------------------------ //
    public TextField amntT(){
        TextField amountT = new TextField("0.00");
        return amountT;
    }
    // ---------------------------- POPUP ------------------------------ //
    public Popup showWarning(Color yellow){
        Popup warning = new Popup();
        Label warnLabel = setFont("Please select a month with \n previous data from your csv.", 15);
  
        Button exit = new Button("X");
        exit.setOnAction(action -> {
          warning.hide();
        });
  
        HBox mainPopup = new HBox(warnLabel, exit);
        mainPopup.setBackground(new Background(new BackgroundFill(yellow, CornerRadii.EMPTY, Insets.EMPTY)));
  
        warning.getContent().addAll(mainPopup);
  
        return warning;
    }
    // ---------------------------- PANES ------------------------------ //
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

        HBox catAmntBox = new HBox(10);
        catAmntBox.getChildren().addAll(cat, space, amnt);
        catAmntBox.setAlignment(Pos.TOP_CENTER);
        catAmntBox.setBackground(new Background(new BackgroundFill(babyBlue, CornerRadii.EMPTY, Insets.EMPTY)));

        sPane.getChildren().addAll(rectangle, catAmntBox);

        return sPane;
    }
    // ---------------------------- GRAPHS ------------------------------ //
    // pie chart of actual income earnings
    public PieChart showPieChart(String[][] incomeArr){
        ArrayList<PieChart.Data> categories = new ArrayList<PieChart.Data>();
        for(int i = 1; i < incomeArr.length; i++){   
            double amount = Double.valueOf(incomeArr[i][2])    ;
            if(amount != 0){                 
                categories.add(new PieChart.Data(incomeArr[i][0], amount));
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(categories);
                    
        PieChart piechart = new PieChart(pieChartData);
        piechart.setTitle("Actual Income");
        return piechart;
    }
    // line chart of expenses (actual and anticipated)
    public LineChart<String,String> showlLineChart(String[][] expenseArr){
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Category");
        
        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Amount($)");
    
        LineChart<String,String> lineChart = new LineChart<String, String>(xAxis,yAxis);
    
        lineChart.setTitle("Expense Line Graph");
    
        XYChart.Series antSeries = new XYChart.Series();
        XYChart.Series accSeries = new XYChart.Series();
    
        antSeries.setName("Anticipated");
        accSeries.setName("Actual");
    
        for(int i = 1; i < expenseArr.length; i++) {
            for (int j = 0; j < 4; j++){
                antSeries.getData().add(new XYChart.Data(expenseArr[i][0], expenseArr[i][1]));
            }
        }
        for(int i = 1; i < expenseArr.length; i++) {
            for (int j = 0; j < 4; j++){
                accSeries.getData().add(new XYChart.Data(expenseArr[i][0], expenseArr[i][2]));
            }
        }
        
        lineChart.getData().addAll(antSeries, accSeries);

        return lineChart;
    }   
    // -------------------------- COMBOBOX<STRING> ------------------------------ //
    public ComboBox<String> comboBoxMonths(){
        ComboBox<String> months = new ComboBox<String>();
        months.getItems().addAll(
                                 "January", "February", "March", "April", "May", "June",
                                 "July", "August", "September", "October", "November", "December"
                                );
        months.setPromptText("Select Month");
        months.setEditable(false);
        
        return months;
    }
    public ComboBox<String>comboBoxIncome(String[][] income2D){                
        // String temp = incomeArr[1][0];
        ComboBox<String>incomeCat = new ComboBox<String>();        
            for(int i = 1; i < income2D.length; i++){                
                    incomeCat.getItems().addAll(
                        income2D[i][0]
                    );
                
            }            
        incomeCat.setPromptText("Select Category");
        incomeCat.setEditable(false);
        
        return incomeCat;
    }

    public ComboBox<String>comboBoxExpense(String[][] expense2D){
        // String temp = trends.income2D[1][0];
        ComboBox<String>expenseCat = new ComboBox<String>();       
        // if(expense2D[1][0] == null){
        //     expenseCat.getItems().addAll(                            
        //         "Food", "Health", "Transportation", 
        //         "Utilies", "Personal","Other"
        //     );
        // }
        // else{
        //     System.out.println("entering their categories");
            for(int i = 1; i < expense2D.length; i++){                
                expenseCat.getItems().addAll(
                    expense2D[i][0]
                    );
                
            }            
        // }                 
                                     
        expenseCat.setPromptText("Select Category");
        expenseCat.setEditable(false);
        
        return expenseCat;
    }
    // ---------------------------- IMAGES ------------------------------ //
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
    //------------------- VOID METHODS (SHOW) ------------------//
    /**
     * 
     * @param add   
     * @param income
     * @param comboBox
     * @param amntTF
     * @param catInc
     * @param amtInc
     * @param catExp
     * @param amtExp
     * @param catArr
     * @param amtArr
     */
    public void showUserInput(boolean add, boolean income, ComboBox<String>comboBox, TextField amntTF, Text catInc, Text amtInc, Text catExp, Text amtExp, ArrayList<String> catArr, ArrayList<String> amtArr){
        // initialize strings
        String temp = (String) comboBox.getValue();
        String tempText = amntTF.getText();
        String stringCat, stringAmt;
        String printCat = "";
        String printAmt = "";
        
        // conds if it is the add btn or del btn
        if (add == true){
            catArr.add(temp);    //JANE can i jus put temp?
            amtArr.add(tempText);                // and like tempText here?
            
        }
        else{
            // for(int i = 0; i < CatArr.size(); i++){
                if( catArr.contains(temp) && amtArr.contains(tempText)){
                    catArr.remove(temp);
                    amtArr.remove(tempText);     
                }
            // }
            System.out.println(catArr + " : " + amtArr);      // for testing DELETE LTR
        }

        // conds if scene 3 or scene 4
        for(int i = 0; i < catArr.size(); i ++){
            // set string as the array element
            stringCat = catArr.get(i) + "\n";
            stringAmt = amtArr.get(i) + "\n";
            // concanate
            printCat += stringCat;
            printAmt += stringAmt;
        }
        System.out.println(catArr + " : " + amtArr);  // testing DELETE LTR
        
        // if the user deletes all inputs set it to default labels 
        if(catArr.size() == 0 || catArr.size() == 0){
            if(income == true){
                catInc.setText("Categories : ");
                amtInc.setText("Amount : ");
                System.out.println("inc empty");        //testng DELETE LTR
            }
            else{
                catExp.setText("Categories : ");
                amtExp.setText("Amount : ");   
                System.out.println("exp empty");   //testng DELETE LTR
            }
        }

        // conds if it is income or exp 
        // set text to the user inputs
        if(income == true){
            catInc.setText("Categories : \n" + printCat);
            amtInc.setText("Amount : \n" + printAmt);
            System.out.println("inc cat : " + printCat + " inc amt : " + printAmt);     //testng DELETE LTR

        }
        else {
            catExp.setText("Categories : \n" + printCat);
            amtExp.setText("Amount : \n" + printAmt);
            System.out.println("exp cat : " + printCat + " exp amt : " + printAmt);     //testng DELETE LTR
        }
        System.out.println("Button pressed"); // testing delete later
    }   
    // for new budget
    public void showCategory(boolean add, ArrayList<String> categoryArrList, TextField catTF, boolean income, Text incomeCatT, Text expensesCatT){
        String stringCat;
        String printCat = "";
        if(add == true){
            categoryArrList.add(catTF.getText());
        }
        else{
            if(categoryArrList.contains(catTF.getText())){
                System.out.println("going thru delete");
                categoryArrList.remove(catTF.getText());
            }    
        }
        
        if(categoryArrList.size() == 0){
            if(income == true){
                System.out.println("reset");
                incomeCatT.setText("Categories : ");
            }
            else{
                expensesCatT.setText("Categories : ");
            }
        }

        for(int i = 0; i < categoryArrList.size(); i ++){
            stringCat = categoryArrList.get(i) + "\n";      
            System.out.println("String cat is: " + stringCat)      ;
            printCat += stringCat;            
            System.out.println(printCat);
            if(income == true){
                incomeCatT.setText("Categories : \n" + printCat);
            }
            else {
                expensesCatT.setText("Categories : \n" + printCat);
            }
        }
        System.out.println("arraylist is: " + categoryArrList);
    }
    public void showDataThreeFour(ArrayList<String> catArr, ArrayList<String> amtArr, Text showCat, Text showAmt){
        // initialize strings                        
        String stringCat, stringAmt;
        String printCat = "";
        String printAmt = "";
    

        // conds if scene 3 or scene 4
        for(int i = 0; i < catArr.size(); i ++){
            // set string as the array element
            if(!catArr.get(i).equals("")){
                stringCat = catArr.get(i) + "\n";
                stringAmt = amtArr.get(i) + "\n";
                // concanate
                printCat += stringCat;
                printAmt += stringAmt;
            }
            
        }
        System.out.println(catArr + " : " + amtArr);  // testing DELETE LT
        
        // conds if it is income or exp 
        // set text to the user inputs
        showCat.setText("Categories : \n" + printCat);
        showAmt.setText("Amount : \n" + printAmt);
        System.out.println("inc cat : " + printCat + " inc amt : " + printAmt);     //testng DELETE LTR
                                
        
    }
    //set default combobox month name display
    public void setMonth(ComboBox<String> cBMonths, String month, String[][] incomeArr){
        if(month == null){
            month = incomeArr[0][0].substring(7,incomeArr[0][0].length());
        }
        for(int i = 0; i < trends.monthNames.length; i++){
            if(trends.monthNames[i].equals(month)){
                cBMonths.getSelectionModel().select(i);
            }
        }
    }
    public void noData(Text warningT, String month, boolean clicked){    
        if (month == null && clicked == false || finance.fullImport == false){
                warningT.setText("Please make a new budget first or import csvs");
                System.out.println("no data is working");
                System.out.println("month is: " + month + " clicked is " + clicked + " imported is " + finance.fullImport);
        }                    
    }
    //------------------- BOOLEAN CHECKS ------------------//
    public boolean onlyLetters(String str) { //checks if the string only contains letters
        
        if(str == null || str.equals("")){
            return false;
        }
        char[] chars = str.toCharArray();
    
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
    
        return true;
    }
    public static boolean isNumeric(String strNum){
        if(strNum == null){            
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;        
    } 

    
}