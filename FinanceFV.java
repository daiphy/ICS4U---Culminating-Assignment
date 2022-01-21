/**
 * Base Class - Model - Finance
 */
import java.io.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

class FinanceFV {
    
    //-------------------- GLOBAL VARIABLES --------------------//
    public String fileName = "income.csv";
    public Trends trends = new Trends();
    public String chosenMonth = "February";
    public String nextMonth = "";
    public String[][] test2D = {{"Month: February", "0", "0", "0"},
                                {"Savings", "0", "0", "0"}, 
                                {"Paycheck", "0", "0", "0"}, 
                                {"Interest", "0", "0", "0"}, 
                                {"Bonus", "0", "0", "0"}, 
                                {"Allowance", "0", "0", "0"},
                                {"Other", "0", "0", "0"}};
    public String[] testArr = {"Month: February", "0", "0", "0",
                               "Savings", "0", "0", "0",
                               "Paycheck", "0", "0", "0",
                               "Interest", "0", "0", "0",
                               "Bonus", "0", "0", "0",
                               "Allowance", "0", "0", "0",
                               "Other", "0", "0", "0"};
    public int startRow = 7;

    //-------------------- CONSTRUCTOR --------------------//
    public FinanceFV(){

    }
    
    //-------------------- CHECK IF CSV FILE --------------------//
    public String checkInputtedFile(Stage primaryStage) {
        FileChooser fileChooser = new FileChooser(); // allow user to input file
        File file = fileChooser.showOpenDialog(primaryStage);
        this.fileName = file.getName();
    
        String warningText = " ";
    
        if (file != null) {
            // If inputted file is .csv file
            if ((this.fileName.substring(this.fileName.length() - 4, this.fileName.length())).equals(".csv")) {
                if(this.fileName.toLowerCase().equals("income.csv") || this.fileName.toLowerCase().equals("expense.csv")){
                    try {
                        //test();
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
    //-------------------- READ CSV FILE --------------------//
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
        //initializing 2d array//
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
    //Method to test "readCSV()" method
    public void test(String[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
    }
    //-------------------- APPEND OR UPDATE CSV --------------------//
    /**
     * these are methods that search through the csv file to locate if 
     * the current month selected exists or not to decide whether or not to
     * append to the file or write over the month section only
    */

    //--- method to check for chosen month name ---//
    //can be used for finding both the start and ending indexes
    /**
     * if the chosen month is the most recent month
     * - will set the total num rows between existing csv and 
     *   additional data as the end of appending section
    */
    public String checkForMonth(String monthName){
        String[][] arr = readCSV();
        String coords = "";
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
    //--- method to find next month name ---//
    /**
     * if the chosen month is not the most recent month
     * - eg. adding/updating February between January and March
     * - will find name of next month by searching through the array
     *   of month names located in "Trends.java"
     */
    public void findNext(){
        int index = 0;
        for(int i = 0; i < trends.monthNames.length; i++){
            if(trends.monthNames[i].equals(this.chosenMonth)){
                //the name of the next month will be at the next index from chosen month name
                index = i + 1;
            }
        }
        this.nextMonth = trends.monthNames[index];
    }

    //--- method to append to the csv ---//
    //SO FAR what this method does is: append to the end of the csv
    //data for chosen month has not been created yet
    //chosen month is after existing months
    public void appendCSV(String[][] arrOne){
        //try catch to append to existing data in csv
        try(PrintWriter writer = new PrintWriter(this.fileName)){
            StringBuilder builder = new StringBuilder();
            //loop through csv contents first and add to stringBuilder
            for(int i = 0; i < arrOne.length; i++){
                for(int j = 0; j < arrOne[0].length; j++){
                    builder.append(arrOne[i][j] + ",");
                }
                //create a new line each time a new row starts
                builder.append("\n");
            }
            //loop through data that needs to be added and add to stringBuilder
            for(int a = 0; a < test2D.length; a++){
                for(int b = 0; b < test2D[0].length; b++){
                    builder.append(test2D[a][b] + ",");
                }
                builder.append("\n");
            }
            //put all the information into the csv
            writer.write(builder.toString());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    //--- method to update an array containing csv data (chosen month already has existing data) ---//
    public boolean update2DArr(String[][] arr, int row, int col, int count){
        boolean bool = true;
        if(col < arr[0].length && row < arr.length){ //validation checks 
            if(arr[row][col].equals("Month: " + this.nextMonth)){
                return false;
            }
            else{
                if(bool){
                    if(col == arr[0].length - 1){
                        bool = update2DArr(arr, row + 1, 0, count + 1); //next row
                    }
                    bool = update2DArr(arr, row, col + 1, count + 1); //next col
                }
            }
        }
        if(col < arr[0].length && row < arr.length){
            arr[row][col] = testArr[count];            
        }
        return bool;
    }
    //--- method to write to csv ---//
    public void updateCSV(String[][] arr){
        //try catch to append to existing data in csv
        try(PrintWriter writer = new PrintWriter(this.fileName)){
            StringBuilder builder = new StringBuilder();
            //loop through 2D arr
            for(int a = 0; a < arr.length; a++){
                for(int b = 0; b < arr[0].length; b++){
                    builder.append(arr[a][b] + ",");
                }
                builder.append("\n");
            }
            //put all the information into the csv
            writer.write(builder.toString());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        //from 0,0 to row for starting point (decide for this vs next month)
        //bc you can either rewrite over smth existing or add a missing month
        //split into diff methods? 
    }
}