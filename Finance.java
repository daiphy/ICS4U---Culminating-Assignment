/**
 * Base Class - Model - Finance
 */
import java.io.*;
import java.util.ArrayList;

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
    //insert importing method name (file io)
    //insert exporting method name
    //insert creating csv name
    //insert adding and deleting categories method
    //insert putting arraylist from the categories into 2d array (the customizeable portion)

}