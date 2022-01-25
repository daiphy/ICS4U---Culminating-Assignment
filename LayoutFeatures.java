/**
 * Daiphy, Hilary, Jane, Rachel
 * Teacher: Mr. Ho
 * ICS4U Culminating Project
 * January 24, 2022
 * LayoutFeatures.java
 */

//********** IMPORTS **********//
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

//********** CLASS **********//
public class LayoutFeatures{ 
 
    //-------------------- GLOBAL VARIABLES --------------------//
    public Color babyBlue = Color.web("#C9DAF8");
    public Color darkBlue = Color.web("#9AB3DF");
    public Color white = Color.web("#FFFFFF");
    public Color yellow = Color.web("#f8f3c9");
    // initialize finance and trends
    public FinanceFV finance = new FinanceFV();
    public Trends trends = new Trends();

    //-------------------- CONSTRUCTOR --------------------// 
    public LayoutFeatures(){
        
    }
    //---------------------------------- FEATURE METHODS -----------------------------------//    
    /**
     * Spacing is for formatting the GUI so component align properly
     * @return label of empty space
     */
    public Label spacing(){ 
        Label space = new Label("                        ");
        return space;
    }
    /**
     * This sets the font of every label
     * @param name name of the label
     * @param size the size of the label
     * @return label with set font
     */
    public Label setFont(String name, int size){
        Label labelFont = new Label(name);
        labelFont.setFont(Font.font("Verdana", FontWeight.BOLD, size));
        return labelFont;
    }
    // ---------------------------- BUTTONS ------------------------------ //
    /**
     * Generates a button that is yellow. These are generally used for moving from scene to scene
     * @param name name of the button (this is what is displayed)
     * @return button with font, colour and name
     */
    public Button yellowButton(String name){
        Button yellowB = new Button(name);
        yellowB.setStyle("-fx-font: 16 verdana; -fx-base: #f8f3c9;");
        return yellowB;
    }
    // ---------------------------- TEXTFIELD ------------------------------ //
    /**
     * Makes a textfield for the user to type in amount
     * @return textfield
     */
    public TextField amntT(){
        TextField amountT = new TextField("0.00"); 
        return amountT;
    }
    // ---------------------------- POPUP ------------------------------ //
    /**
     * Shows popup when user selects a month without previous data in the csv file, with an exit button which hides popup
     * @param yellow    colour of popups background
     * @return popup with warning label, exit button and coloured background
     */
    public Popup showWarning(){
        // initialize popup
        Popup warning = new Popup();    
        Label warnLabel = setFont("Please select a month with \n previous data from your csv.", 15);    // popup labels
        // intialize button
        Button exit = new Button("X");
        exit.setOnAction(action -> {    //when button is clicked
          warning.hide();               // popup is hidden
        });
        
        // hbox with warning label and exit button
        HBox mainPopup = new HBox(warnLabel, exit);
        mainPopup.setBackground(new Background(new BackgroundFill(this.yellow, CornerRadii.EMPTY, Insets.EMPTY)));
  
        // enter all contents into the popup
        warning.getContent().addAll(mainPopup);
  
        return warning; // return popup
    }
    // ---------------------------- PANES ------------------------------ //
    /**
     * creates a border for each scene using empty space labels as it's top, bottom, left, and right
     * the center has the vbox contents of the scene
     * @param center the scene contents in a VBox
     * @return the border (borderPane)
     */
    public BorderPane showBorder(VBox center){
        // initialize borderPane
        BorderPane bPane = new BorderPane();

        // labels to act as empty spacing as it's top, bottom, left, and right
        Label topSpace = spacing();
        Label bottomSpace = spacing();
        Label leftSpace = spacing();
        Label rightSpace = spacing();

        // setting the borders
        bPane.setTop(topSpace);
        bPane.setBottom(bottomSpace);
        bPane.setLeft(leftSpace);
        bPane.setRight(rightSpace);
        bPane.setCenter(center);    // the vbox scene contents

        return bPane;   // return borderpane
    }   
    /**
     * creates a scrollPane for each scene surrounging the borderpane contents
     * @param contents  borderPane contents of the vbox scene contents with a border
     * @return  the scroll wheel (scrollPane)
     */
    public ScrollPane showScrollPane(BorderPane contents){
        // initialize scrollPane
        ScrollPane scroll = new ScrollPane();
        
        scroll.setContent(contents);    // set the contents that is being scrolled as the borderpane contents
        scroll.setHbarPolicy(ScrollBarPolicy.ALWAYS);      // visible horizontal scroll wheel
        scroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);      // visible vertical scroll wheel
        scroll.setStyle("-fx-background-color:transparent;");       // ensures the middle is clear

        return scroll;      //return scrollPane
    }
    /**
     * stacks a blue rectangle under the hbox contents
     * @param cat   updated Text Categories user has entered
     * @param amnt  updated Text Amount user has entered
     * @return
     */
    public StackPane showSPane(Text cat, Text amnt){
        // intilize stackpane
        StackPane sPane = new StackPane();

        // intialize rectangle
        Rectangle rectangle = new Rectangle(100,150,900,150);
        rectangle.setFill(babyBlue);    // colours the rectangle

        Label space = spacing();    // label space to separate the categories and amounts

        HBox catAmntBox = new HBox(10); // initialize hbox
        catAmntBox.getChildren().addAll(cat, space, amnt);    // add all contents (cat, amt, and the spacing in between) to the hbox
        catAmntBox.setAlignment(Pos.TOP_CENTER);    // where the hbox should be (alignment positioning)
        catAmntBox.setBackground(new Background(new BackgroundFill(babyBlue, CornerRadii.EMPTY, Insets.EMPTY)));    
        // ^^ background colour of the hbox so that when user adds over the preset rectangle the colour will continue as the background

        sPane.getChildren().addAll(rectangle, catAmntBox);  // add all contents (rectangle and hbox) to be stacked in order of back to front 

        return sPane;   // return stackpane
    }
    // ---------------------------- GRAPHS ------------------------------ //
    /**
     * Creates a pie chart of actual income earnings
     * @param incomeArr 2D array of income
     * @return piechart with all of the components
     */
    public PieChart showPieChart(String[][] incomeArr){
        ArrayList<PieChart.Data> categories = new ArrayList<PieChart.Data>(); // Initializes an arraylist with piechart data
        for(int i = 1; i < incomeArr.length; i++){
            double amount = Double.valueOf(incomeArr[i][2]);                  // Takes the data from the 2d array and makes it a double
            if(amount != 0){
                categories.add(new PieChart.Data(incomeArr[i][0], amount));   // If the data is not 0, then add this into the piechart
            }
        }

        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(categories); //Make the arraylist into an observable list

        PieChart piechart = new PieChart(pieChartData);
        piechart.setTitle("Actual Income");
        return piechart;
    }
    /**
     * Line chart -> takes the user inputted data in the expense array and shows the 
     * (actual and anticipated) data in the form of a line chart (a line for each)
     * @param expenseArr    updated trends.expense2D with the user inputs for the acc and ant expenses specified by the month
     * @return the line graph (LineChart)
     */
    public LineChart<String,String> showlLineChart(String[][] expenseArr){
        
        // initialize the x axis and y axis label 
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setLabel("Category");
        CategoryAxis yAxis = new CategoryAxis();
        yAxis.setLabel("Amount($)");
    
        // initialize the line chart with the labels
        LineChart<String,String> lineChart = new LineChart<String, String>(xAxis,yAxis);
    
        lineChart.setTitle("Expense Line Graph");       // set's the lineChart title 
    
        XYChart.Series antSeries = new XYChart.Series();    //the line for the anticipated data
        XYChart.Series accSeries = new XYChart.Series();    //the line for the actual data
    
        //sets name for the line (shows user which line correlates with which data)
        antSeries.setName("Anticipated");       
        accSeries.setName("Actual");
        
        // for loop to go through the data
        for(int i = 1; i < expenseArr.length; i++) {
            antSeries.getData().add(new XYChart.Data(expenseArr[i][0], expenseArr[i][1]));  
            //the (x,y) x = [i][0]to go through the categories data || y = [i][1] to go through the anticipated data
            accSeries.getData().add(new XYChart.Data(expenseArr[i][0], expenseArr[i][2]));
            //the (x,y) x = [i][0]to go through the categories data || y = [i][2] to go through the actual data
        }
        
        lineChart.getData().addAll(antSeries, accSeries);   // sets the series line data into the chart

        return lineChart; // returns line chart
    }   
    // -------------------------- COMBOBOX<STRING> ------------------------------ //
    /**
     * Makes a combo box for months. The user clicks this to choose what month they want to budget for. 
     * @return the combo box that has the month options
     */
    public ComboBox<String> comboBoxMonths(){
        ComboBox<String> months = new ComboBox<String>(); // Initializes the combo box
        months.getItems().addAll(                         // Adds options to the combo box
                                 "January", "February", "March", "April", "May", "June",
                                 "July", "August", "September", "October", "November", "December"
                                );
        months.setPromptText("Select Month");
        months.setEditable(false);

        return months;
    }
    /**
     * Makes a combo box for income categories. The user clicks this to select a category they want to add to
     * @param income2D the 2D array for income that stores user data (can be income or expense)
     * @return the combo box that has the category options for income
     */
    public ComboBox<String>comboBoxIncome(String[][] income2D){

        ComboBox<String>incomeCat = new ComboBox<String>();
            for(int i = 1; i < income2D.length; i++){
                    incomeCat.getItems().addAll(        // Adds category names to the combo box from the income 2D array 
                        income2D[i][0]
                    );

            }
        incomeCat.setPromptText("Select Category");
        incomeCat.setEditable(false);

        return incomeCat;
    }
    /**
     * Makes a combo box for expense categories. The user clicks this to select a category they want to add to
     * @param expense2D the 2D array for expense that stores user data (can be income or expense)
     * @return the combo box that has the category options for expense
     */
    public ComboBox<String>comboBoxExpense(String[][] expense2D){

        ComboBox<String>expenseCat = new ComboBox<String>();
            for(int i = 1; i < expense2D.length; i++){
                expenseCat.getItems().addAll(         // Adds category names to the combo box from the expense 2D array 
                    expense2D[i][0]
                    );

            }
        expenseCat.setPromptText("Select Category");
        expenseCat.setEditable(false);

        return expenseCat;
    }
    // ---------------------------- IMAGES ------------------------------ //
    /**
     * Imports a bee image that can be used in the GUI
     * @return imageView object of the bee
     */
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
    //------------------- DISPLAY USER INPUT IN PLAN AND TRANSACTION METHOD (SCENES 3 AND 4) ------------------//
    /**
     * This method displays the categories selected and amounts of money the user has inputted in the plan and transactions scenes 
     * @param add this is to determine whether the user is adding or deleting items (categories and amounts of money)
     * @param income this is to determine whether they are entering data for income or expense
     * @param comboBox this is a combobox containing all the possible category names to choose from
     * @param amntTF this is a textfield to display the amount of money the user has entered
     * @param catInc this is the text that will be displayed for income categories chosen
     * @param amtInc this is the text that will be displayed for income amounts of money entered
     * @param catExp this is the text that will be displayed for expense categories chosen
     * @param amtExp this is the text that will be displayed for expense amounts of money enetered
     * @param catArr this is an arraylist containing the category names
     * @param amtArr this is an arraylist containing the amounts of money entered
     */
    public void showUserInput(boolean add, boolean income, ComboBox<String>comboBox, TextField amntTF, Text catInc, Text amtInc, Text catExp, Text amtExp, ArrayList<String> catArr, ArrayList<String> amtArr){
        //----- VARIABLES -----//
        String temp = (String) comboBox.getValue();
        String tempText = amntTF.getText();
        String stringCat, stringAmt;
        String printCat = "";
        String printAmt = "";
        
        // conds if it is the add btn or del btn
        if (add == true){
            catArr.add(temp);   
            amtArr.add(tempText);               
            
        }
        else{
            if( catArr.contains(temp) && amtArr.contains(tempText)){
                catArr.remove(temp);
                amtArr.remove(tempText);     
            }
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
        
        // if the user deletes all inputs set it to default labels 
        if(catArr.size() == 0 || catArr.size() == 0){
            if(income == true){
                catInc.setText("Categories : ");
                amtInc.setText("Amount : ");
            }
            else{
                catExp.setText("Categories : ");
                amtExp.setText("Amount : ");   
            }
        }

        // conds if it is income or exp 
        // set text to the user inputs
        if(income == true){
            catInc.setText("Categories : \n" + printCat);
            amtInc.setText("Amount : \n" + printAmt);

        }
        else {
            catExp.setText("Categories : \n" + printCat);
            amtExp.setText("Amount : \n" + printAmt);
        }
    }   
    //------------------- SHOW CATEGORIES IN NEW BUDGET SCENE METHOD (SCENE 2) ------------------//
    /**
     * This method displays the categories entered and previously entered 
     * @param add this determines whether they are adding or deleting category name items
     * @param categoryArrList this is an arraylist for the category names
     * @param catTF this is a textfield for displaying the category names
     * @param income this determines whether the categories are being entered for income or expense
     * @param incomeCatT this is the text that will be displayed for income category names
     * @param expensesCatT this is the text that will be displayed for the expense category names
     */
    public void showCategory(boolean add, ArrayList<String> categoryArrList, TextField catTF, boolean income, Text incomeCatT, Text expensesCatT){
        //----- VARIABLES -----//
        String stringCat;
        String printCat = "";
        // if they are adding categories, add to the category arraylist
        if(add == true){
            categoryArrList.add(catTF.getText());
        }
        else{ // otherwise, if the category arraylist contains the text in the textfield, delete the item
            if(categoryArrList.contains(catTF.getText())){
                categoryArrList.remove(catTF.getText());
            }    
        }
        // if the category arraylist size is 0, simply display the "categories: " texts
        if(categoryArrList.size() == 0){
            if(income == true){
                incomeCatT.setText("Categories : ");
            }
            else{
                expensesCatT.setText("Categories : ");
            }
        }
        // this for loop iterates through the category arraylist to then set the texts to be displayed
        for(int i = 0; i < categoryArrList.size(); i ++){
            stringCat = categoryArrList.get(i) + "\n";      
            printCat += stringCat;            
            if(income == true){
                incomeCatT.setText("Categories : \n" + printCat);
            }
            else {
                expensesCatT.setText("Categories : \n" + printCat);
            }
        }
    }
    //------------------- SHOW DATA IN PLAN AND TRANSACTION SCENES METHOD (SCENES 3 AND 4) ------------------//
    /**
     * This method will show the data in scenes three and four
     * @param catArr this is an arraylist full of category names (can be default or user input)
     * @param amtArr this is an arraylist full of the amounts of money the user has inputed (or 0.0 if they have not)
     * @param showCat this is the text that will display the category arraylist items
     * @param showAmt this is the text that will display the amount arraylist items
     */
    public void showDataThreeFour(ArrayList<String> catArr, ArrayList<String> amtArr, Text showCat, Text showAmt){
        //----- VARIABLES -----//                      
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
        // conds if it is income or exp 
        // set text to the user inputs
        showCat.setText("Categories : \n" + printCat);
        showAmt.setText("Amount : \n" + printAmt);
    }
    //------------------- SET DEFAULT COMBOBOX MONTH NAME DISPLAY METHOD ------------------//
    /**
     * This method will set the month name display on the combo box depending on current/chosen month
     * @param cBMonths this is the combobox containing month names as items
     * @param month this is the current/chosen month
     * @param incomeArr this is an array that contains month names
     */
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
    //------------------- CHECK ACCESS TO SCENES METHOD ------------------//
    /**
     * This method checks if the user has gone through "select month" or successfully imported files
     * @param warningT this is text that will display if they attempt to acces "summary" or "trends" without going through "select month" or importing files
     * @param month this is the current/chosen month
     * @param clicked this checks if the user has clicked "next" on the "select month" page
     */
    public void noData(Text warningT, String month, boolean clicked){    
        if (month == null && clicked == false || finance.fullImport == false){
                warningT.setText("Please make a new budget first or import csvs");
        }                    
    }
    //------------------- CHECK IF TEXT CONTAINS ONLY LETTERS METHOD ------------------//
    /**
     * This method checks to see if the string entered in a text field only contains letters
     * @param str this is the string that was entered in the text field and needs to be checked
     * @return this method returns whether or not it contains only letters
     */
    public boolean onlyLetters(String str) { 
        // if the string does not contain any inputs, it does not contain only letters
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
    //------------------- CHECK IF TEXT CONTAINS ONLY NUMBERS METHOD ------------------//
    /**
     * This method checks to see if the string entered in a text field only contains numbers
     * @param strNum this is the string that was entered in the text field and needs to be checked
     * @return this method returns whether or not it contains only letters
     */
    public static boolean isNumeric(String strNum){
        if(strNum == null){
            return false;
        }
        try {
            double d = Double.parseDouble(strNum); // this checks if the string can turn into a double
        } catch (NumberFormatException nfe) {
            return false; // if it cannot be turned into a double, it does not only contain letters
        }
        return true; 
    }
    
}