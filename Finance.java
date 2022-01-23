/**
 * Base Class - Model - Finance
 */
import java.io.*;
import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

class Finance {
    

    public int arraySize = 12;
    public String directory = "C:/Users/Rachel/Downloads/Comp Sci/5. Culminating/BudjetingApp/src/";
    public String csvName = "antIncome.csv";

    public Finance(){

    }
    
    public String[][] readCSV(String csvFile){
        String[][] tempArray = new String[arraySize][2];
        int i = 0;
        try{
            File file = new File(csvFile);
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            while((line = buffer.readLine()) != null) {
                String[] arr = line.split(",");
                tempArray[i][0] = arr[0];
                if(arr.length > 1){
                    tempArray[i][1] = arr[1];
                }
                i++;
            }
            buffer.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return tempArray;
    }
    //Method to test "readCSV()" method
    public void test(){
        String[][] arr = readCSV(this.directory + "antExpenses.csv");
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
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
    //insert importing method name (file io)
    //insert exporting method name
    //insert creating csv name
    //insert adding and deleting categories method
    //insert putting arraylist from the categories into 2d array (the customizeable portion)

}