/**
 * Base Class - Model - Finance
 */
import java.io.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;


class FinanceFV {
    
    //-------------------- GLOBAL VARIABLES --------------------//
    public String fileName = ":)";        
    public String nextMonth = ":)";
    Trends trends = new Trends();
    //-------------------- CONSTRUCTOR --------------------//
    public FinanceFV(){

    }
    //-------------------- METHOD THAT COLLECTS ALL OTHER METHODS --------------------//
    //this method collects methods from financeFV in order to be used in AppLayoutFV
    //arr is existing csv contents by using readCSV() in the param, choice is to determine which file it stores into
    public void toCSV(String[][] arr, String choice, String month){
        getFileName(choice);
        //put into CSV
        String coords = checkForMonth(month);
        if(coords.equals(":)") || readCSV().length == 7){
            appendCSV(arr);
        }
        else{
            findNext(month); 
            String initialCoords = checkForMonth(month);
            int row = Integer.parseInt(initialCoords.substring(0,initialCoords.indexOf(",")));
            int col = Integer.parseInt(initialCoords.substring(initialCoords.indexOf(",") + 1,initialCoords.length()));
            //replace arr with smth that will not change, a 2D to store everything
            int r = readCSV().length + arr.length;
            int c = readCSV()[0].length;
            String[][] csvArr = new String[r][c];
            csvArr = readCSV();
            boolean updated = update2DArr(csvArr, arr, row, col, 0, 0);
            if(updated){
                updateCSV(csvArr);
            } 
        }
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
    //--- method to find next month name ---//
    /**
     * if the chosen month is not the most recent month
     * - eg. adding/updating February between January and March
     * - will find name of next month by searching through the array
     *   of month names located in "Trends.java"
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

    //--- method to append to the csv ---//
    //SO FAR what this method does is: append to the end of the csv
    //data for chosen month has not been created yet
    //chosen month is after existing months
    public void appendCSV(String[][] addArr){
        String[][] arrOne = readCSV();
        String[][] temp; //maybe add a way to organize the stuff, so that all the months are in order
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
            for(int a = 0; a < addArr.length; a++){
                for(int b = 0; b < addArr[0].length; b++){
                    builder.append(addArr[a][b] + ",");
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
    public boolean update2DArr(String[][] array, String[][] arr, int row, int col, int r, int c){
        boolean bool = true;
        if(col < array[0].length && row < array.length){ //validation checks 
            if(array[row][col].equals("Month: " + this.nextMonth)){
                return false;
            }
            else{
                if(bool){
                    if(col == array[0].length - 1){
                        bool = update2DArr(array, arr, row + 1, 0, r + 1, 0); //next row
                    }
                    bool = update2DArr(array, arr, row, col + 1, r, c + 1); //next col
                }
            }
        }

        if(col < array[0].length && row < array.length && c < array[0].length && row < arr.length){
            array[row][col] = arr[r][c];  
        }
        return bool;
    }
    //--- method to write to csv ---//
    public void updateCSV(String[][] arr){
        try(PrintWriter writer = new PrintWriter(this.fileName)){
            StringBuilder builder = new StringBuilder();
            //loop through 2D arr
            for(int a = 0; a < arr.length; a++){
                for(int b = 0; b < arr[0].length; b++){
                    if(arr[a][b] == null){
                        builder.append("0.0,");
                    }
                    else{
                        builder.append(arr[a][b] + ",");
                    }
                }
                builder.append("\n");
            }
            //put all the information into the csv
            writer.write(builder.toString());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    //--- method to repopulate trends 2D arrays to display different things in scene 5 ---//
    //updateArr is pulled from trends.2D arrays
    //string month is from AppLayout
    //String choice is "income" vs "expense"
    //String[][] arr is existing information in the csv
    //
    //get startcoords depending on what month it is
    //set row and col to be these indexes of where the month name is
    //the nested for loops go through the row and col lengths of the trends 2D array
    //reset the trends 2D array with existing data in csv pulled from arr[][]
    //row and col just make sure it's iterating properly
    public void repopulate(String month, String[][] updateArr, String choice){        
        getFileName(choice);
        String[][] arr = readCSV();
        String startCoords = checkForMonth(month);
        if(!startCoords.equals(":)")){                      
            int row = Integer.parseInt(startCoords.substring(0,startCoords.indexOf(",")));
            int col = Integer.parseInt(startCoords.substring(startCoords.indexOf(",") + 1,startCoords.length()));
            for(int i = 0; i < updateArr.length; i++){
                col = 0;
                for(int j = 0; j < updateArr[0].length; j++){
                    updateArr[i][j] = arr[row][col];
                    col++;
                }
                row++;
            }
        }
        
    }

    //--- method to get file name ---//
    public void getFileName(String choice){
        //file name to inport to income vs expense
        if(choice.equals("income")){
            this.fileName = "income.csv";
        }
        else{
            this.fileName = "expense.csv";
        }
    }
}