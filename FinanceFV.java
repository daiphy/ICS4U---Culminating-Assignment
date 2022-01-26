/**
 * Daiphy, Hilary, Jane, Rachel
 * Teacher: Mr. Ho
 * ICS4U Culminating Project
 * January 24, 2022
 * FinanceFV.java
 */

//********** IMPORTS **********//
import java.io.*;
import java.util.ArrayList;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

//********** CLASS **********//
class FinanceFV {
    
    //-------------------- GLOBAL VARIABLES --------------------//
    public String fileName = ":)"; //this is the file name which should be "income.csv" or "expense.csv"   
    public boolean fullImport = false; //this checks for whether or not the user has imported BOTH "income.csv" AND "expense.csv" 
    
    private String nextMonth = ":)"; //this represents the month name that comes after the current month name
    private boolean[] fileArr = {false, false}; //this is to check which file is imported, "income.csv" or "expense.csv"

    private Trends trends = new Trends(); //this is an object that gets from the "trends.java" file

    //-------------------- CONSTRUCTOR --------------------//
    public FinanceFV(){

    }

    //-------------------- METHOD THAT COLLECTS ALL OTHER METHODS --------------------//
    /**
     * This method collects other methods created in this file in order to be used in "AppLayoutFV.java"
     * @param arr this is the array that needs to be put into the CSV file, which is a 2D array taken from "trends.java"
     * @param choice this determines with file it stores into, as a string called "income" or "expense"
     * @param month this is the current/chosen month name
     */
    public void toCSV(String[][] arr, String choice, String month){
        //get file name based on "choice"
        getFileName(choice);
        //initialize starting coordinates which represent the row and column indexes in a 2D array
        String coords = checkForMonth(month);
        //if the month DOES NOT EXIST in the CSV file:
        if(coords.equals(":)")){
            appendCSV(arr); //add data to the end of the file
        }
        //if the month EXISTS in the CSV file:
        else{
            //set "this.nextMonth" to be the next month in the year
            findNext(month); 
            //find the coordinates for the next month in order to know when to stop updating
            String initialCoords = checkForMonth(month);
            //get row and column from taking substrings of the string
            int row = Integer.parseInt(initialCoords.substring(0,initialCoords.indexOf(","))); 
            int col = Integer.parseInt(initialCoords.substring(initialCoords.indexOf(",") + 1,initialCoords.length()));
            //get row length and col length from total length of csv file and the 2D array that needs to be put in
            int r = readCSV().length + arr.length;
            int c = readCSV()[0].length;
            //initialize a 2D array 
            String[][] csvArr = new String[r][c];
            csvArr = readCSV(); //set the 2D array to be the contents of the CSV
            //if the 2D array is able to be updated, put the new data into the CSV file
            boolean updated = update2DArr(csvArr, arr, row, col, 0, 0);
            if(updated){
                updateCSV(csvArr);
            } 
        }
    }    
    
    //-------------------- CHECK IF CSV FILE METHOD --------------------//
    /**
     * This method opens a dialogue box to allow a user to attempt to import a file
     * - this also checks to see if they have imported BOTH "income.csv" AND "expense.csv"
     * @param primaryStage this is the "Scene" from AppLayout
     * @param month this is the current/chosen month 
     * @return this method will return a string that displays the status of their importing journey
     */
    public String checkInputtedFile(Stage primaryStage, String month) {
        //----- VARIABLES -----//
        FileChooser fileChooser = new FileChooser(); // allow user to input file
        File file = fileChooser.showOpenDialog(primaryStage);
        this.fileName = file.getName(); 
        String warningText = " ";        
    
        if (file != null) {
            // If inputted file is .csv file
            if ((this.fileName.substring(this.fileName.length() - 4, this.fileName.length())).equals(".csv")) {
                if(this.fileName.toLowerCase().equals("income.csv") || this.fileName.toLowerCase().equals("expense.csv")){
                    try {                        
                        warningText = "You have imported: " + this.fileName;
                        if(this.fileName.toLowerCase().equals("income.csv")){
                            this.fileArr[0] = true;
                        }
                        else if(this.fileName.toLowerCase().equals("expense.csv")){
                            this.fileArr[1] = true;
                        }       

                        if(this.fileArr[0] == true && this.fileArr[1] == false){
                            warningText += " please import expense.csv";                            
                        }
                        else if(this.fileArr[0] == false && this.fileArr[1] == true){
                            warningText += " please import income.csv";
                        }
                        else{ // if everything is imported/true
                            this.fullImport = true;
                            warningText += " both .csv files have been imported";                            
                        }
                        if(file.length() > 1){
                            repopulate(month, trends.income2D, "income");
                            repopulate(month, trends.expense2D, "expense");
                        }
                        else{
                            this.fullImport = false;
                        }                    
                                                                                           
                    } catch (Exception e) {
                        warningText = "Invalid, action terminated.";                        
                    }
                }
            }
            // Forces user to input .csv in order to go to continue
            else {
                warningText = "Please enter a .csv file.";
            }
            
        }
        return warningText;
    }   

    //-------------------- READ CSV FILE METHOD --------------------//
    /**
     * This method will read from a CSV in order to populate a 2D array
     * @return this method will return the 2D array
     */
    public String[][] readCSV(){
        //----- VARIABLES -----//
        File file = new File(this.fileName); //initializing a file using fileName
        int col = 0; //num cols in csvArr
        int row = 0; //num rows in csvArr

        //first try catch is to get num of rows and cols for 2d array//
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String line = "";
            while((line = buffer.readLine()) != null) {
                String[] arr = line.split(",");
                col = arr.length;
                row++;
            }
            buffer.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        //initializing 2D array//
        String[][] csvArr = new String[row][col];
        //second try catch is to populate 2d array with contents from the file//
        try{
            BufferedReader buffer = new BufferedReader(new FileReader(file));
            String line;
            int rowCounter = 0;
            while((line = buffer.readLine()) != null){
                String[] arr = line.split(",");
                for(int i = 0; i < arr.length; i++){
                    csvArr[rowCounter][i] = arr[i];
                }
                rowCounter++;
            }
            buffer.close();
        }catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return csvArr;
    }

    //-------------------- CHECK IF MONTH EXISTS IN CSV FILE METHOD --------------------//
    /**
     * This method attempts to find a month name in the CSV file
     * @param monthName this is the month you are trying to find
     * @return this method will return coordinates that represent 
     * the row and column indexes in one string separated by a comma
     * (eg. 0,0)
     */
    public String checkForMonth(String monthName){
        //----- VARIABLES -----//
        String[][] arr = readCSV();
        String coords = ":)";

        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                //check if the line contains the chosen month name
                if(arr[i][j].equals("Month: " + monthName)){
                    //save the coordinates in one line: row,col
                    //where i is row and j is col
                    coords = Integer.toString(i) + "," + Integer.toString(j);
                }
            }
        }
        return coords;
    }

    //-------------------- FIND NEXT MONTH NAME METHOD --------------------//
    /**
     * This method will find the next month in the year
     * @param month this takes in the current/chosen month
     */
    public void findNext(String month){
        int index = 0;
        for(int i = 0; i < trends.monthNames.length; i++){
            if(trends.monthNames[i].equals(month)){
                //the name of the next month will be at the next index from chosen month name
                index = i + 1;
            }
        }
        this.nextMonth = trends.monthNames[index];
    }

    //-------------------- APPEND TO CSV METHOD --------------------//
    /**
     * This method will append new data to the end of the CSV if a month does not yet exist in a CSV file
     * @param addArr this is a 2D array containing data that needs
     * to be added to the CSV
     */
    public void appendCSV(String[][] addArr){
        //----- ARRAY -----//
        String[][] arrOne = readCSV();

        // try catch to append to existing data in csv
        try(PrintWriter writer = new PrintWriter(this.fileName)){
            StringBuilder builder = new StringBuilder();
            // loop through csv contents first and add to stringBuilder
            for(int i = 0; i < arrOne.length; i++){
                for(int j = 0; j < arrOne[0].length; j++){
                    builder.append(arrOne[i][j] + ",");
                }
                // create a new line each time a new row starts
                builder.append("\n");
            }
            // loop through data that needs to be added and add to stringBuilder
            for(int a = 0; a < addArr.length; a++){
                for(int b = 0; b < addArr[0].length; b++){
                    builder.append(addArr[a][b] + ",");
                }
                builder.append("\n");
            }
            // put all the information into the csv
            writer.write(builder.toString());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    //-------------------- UPDATE ARRAY CONTAINING CSV DATA METHOD --------------------//
    /**
     * This method will recursively update a section of a 2D array that will then be written into a CSV file if the chosen/current month exists in the CSV file
     * @param array this is the array that contains the existing content in the CSV file
     * @param arr this is the array that contains the new data that needs to be entered into the CSV file
     * @param row this is the starting row index that was found by locating the indexes of the current/chosen month
     * @param col this is the starting column index that was found by locating the indexes of the current/chosen month
     * @param r this is a row counter variable that starts at 0 and goes up in order to be able to iterate through "arr"
     * @param c this is a column counter variable that starts at 0 and goes up in order to be able to iterate through "arr"
     * @return this method will return a boolean stating whether or not the 2D array containing CSV contents has been updated or not
     */
    public boolean update2DArr(String[][] array, String[][] arr, int row, int col, int r, int c){
        //----- VARIABLE -----//
        boolean bool = true;
        
        if(col < array[0].length && row < array.length){ // check if within bounds of the "array" size
            // check if it has reached its end goal (whether it finds the next month or hits the end of the CSV file)
            if(array[row][col].equals("Month: " + this.nextMonth) || (row == array.length - 1 && col == array[0].length - 1)){ 
                return false;
            }
            else{
                if(bool){
                    if(col == array[0].length - 1){ 
                        bool = update2DArr(array, arr, row + 1, 0, r + 1, 0); // will iterate through rows
                    }
                    bool = update2DArr(array, arr, row, col + 1, r, c + 1); // will iterate through columns
                }
            }
        }
        // base case: if the column and row are within bounds, set the section of the array containing old CSV data to the new data
        if(col < array[0].length && row < array.length){
            array[row][col] = arr[r][c];  
        }
        return bool;
    }
    //-------------------- WRITE TO CSV METHOD --------------------//
    /**
     * This method puts the updated 2D array data into the CSV
     * @param arr this is the updated 2D array 
     */
    public void updateCSV(String[][] arr){
        // this try catch attempts to write to a file
        try(PrintWriter writer = new PrintWriter(this.fileName)){
            StringBuilder builder = new StringBuilder();
            // loop through 2D arr and add each element
            for(int a = 0; a < arr.length; a++){
                for(int b = 0; b < arr[0].length; b++){
                    builder.append(arr[a][b] + ",");
                }
                builder.append("\n");
            }
            // put all the information into the csv
            writer.write(builder.toString());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    //-------------------- REPOPULATE DATA/DISPLAY ARRAYS METHOD --------------------//
    
    /**
     * This method repopulates "trends" 2D arrays with new data pulled from a CSV file in order for the correct information to be displayed in "AppLayout"
     * @param month this is the current/chosen month
     * @param updateArr this is the "trends" 2D array that needs to be updated
     * @param choice this is either "income" or "expense" in order to know which file to pull from in order to know which "trends" 2D array to repopulate
     */
    public void repopulate(String month, String[][] updateArr, String choice){
        // find which file to pull from depending on "choice"
        getFileName(choice);
        // create a 2D array representing all the data from the CSV chosen
        String[][] arr = readCSV();
        String startCoords = ":)";
        // if month has not yet been chosen, start repopulating from the beginning of the CSV file
        if(month == null){
            startCoords = "0,0";
        }
        else{
            // if month HAS been chosen, attempts to start repopulating from the indexes at which the month name is located (try to find the month in the CSV)
            startCoords = checkForMonth(month);
        }
        // if the month EXISTS:
        if(!startCoords.equals(":)")){
            //set starting row and column indexes in order to iterate through the arrays and repopulate the "trends" array
            int row = Integer.parseInt(startCoords.substring(0,startCoords.indexOf(",")));
            int col = Integer.parseInt(startCoords.substring(startCoords.indexOf(",") + 1,startCoords.length()));
            for(int i = 0; i < updateArr.length; i++){
                col = 0;
                for(int j = 0; j < updateArr[0].length; j++){
                    // reset the "trends" 2D array with existing data in the CSV file
                    updateArr[i][j] = arr[row][col];
                    col++;
                }
                row++;
            }
        }
    }

    //-------------------- GET FILE NAME METHOD --------------------//
    /**
     * This method sets the file name
     * @param choice this is either "income" or "expense"
     */
    public void getFileName(String choice){
        // file name to import to income vs expense
        if(choice.equals("income")){
            this.fileName = "income.csv";
        }
        else{
            this.fileName = "expense.csv";
        }
    }
}