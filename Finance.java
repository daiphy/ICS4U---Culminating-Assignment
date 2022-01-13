/**
 * Base Class - Model - Finance
 */

import java.io.*;

class Finance {
    //-------------------- GLOBAL VARIABLES --------------------//
    public String[] monthNames = {"January", "February", "March", "April", "May", 
    "June", "July", "August", "September", "October", "November", "December"};
    public String[][] antIncomeArr = new String[6][2];
    public String[][] accIncomeArr = new String[6][2];
    public String[][] accExpenseArr = new String[6][2];
    public String[][] antExpenseArr = new String[6][2];

    public int arraySize = 12;
    public String directory = "C:/Users/Rachel/Downloads/Comp Sci/5. Culminating/BudjetingApp/src/";
    public String csvName = "antIncome.csv";
    //-------------------- CONSTRUCTOR --------------------//
    public Finance(){

    }

    //-------------------- METHODS --------------------//
    // //Methods
    // public void populate(String[] arr, String[][] income, String[][] expenses){
    //     String directory = "C:/Users/Rachel/Downloads/Comp Sci/5. Culminating/BudjetingApp/src/";
    //     String[] incomeCat = readCSV(directory + "antIncome.csv");
    //     String[] expenseCat = readCSV(directory + "antExpenses.csv");
        
    //     int a = 0;
    //     for(int i = 0; i < 12; i++){  
    //         if(i < 6){
    //             income[i][0] = incomeCat[i];  
    //             expenses[i][0] = expenseCat[i];  
    //             income[i][1] = arr[i];  
    //         }
    //         else{
    //             expenses[a][1] = arr[i]; 
    //             a++;  
    //         } 
    //     }

    //     //testing populate() 
    //     for(int i = 0; i < 6; i++){
    //         for(int j = 0; j < 2; j++){
    //             System.out.print(antIncomeArr[i][j] + ", "); 
    //         } 
    //         System.out.println();
    //     }
    //     //testing populate() 
    //     for(int i = 0; i < 6; i++){
    //         for(int j = 0; j < 2; j++){
    //             System.out.print(antExpenseArr[i][j] + ", "); 
    //         } 
    //         System.out.println();
    //     }

    // }

    //Method to read csv and put in 2d array
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
    //Method to populate with user input
    public void populate(String[] arr){         
        //param arr will be a list any column and this method
        //will populate the corresponding column in the 
        //corresponding 2d array 
    }
    //Method to write to CSV with new updates
    public void writeCSV(){
        //this method will update the csv using the updated 
        //2d arrays from "populate()" method
    }

    //insert importing method name (file io)
    //insert exporting method name
    //insert creating csv name
    //insert adding and deleting categories method
    //insert putting arraylist from the categories into 2d array (the customizeable portion)
}