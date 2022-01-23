/**
 * Base Class - Model - Finance
 */
import java.io.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

class Finance {
    
    //-------------------- GLOBAL VARIABLES --------------------//
    public String fileName;
    public Trends trends = new Trends();

    //-------------------- CONSTRUCTOR --------------------//
    public Finance(){

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
                        readCSV();
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
        int i = 0;
        try{
            File file = new File(this.fileName);
            FileReader reader = new FileReader(file);
            BufferedReader buffer = new BufferedReader(reader);
            String line = "";
            while((line = buffer.readLine()) != null) {
                String[] arr = line.split(",");
                trends.income2D[i][0] = arr[0];
                if(arr.length > 1){
                    if(this.fileName.toLowerCase().equals("income.csv")){
                        trends.income2D[i][1] = arr[1];
                    }
                    else{
                        trends.expense2D[i][1] = arr[1];
                    }
                }
                i++;
            }
            buffer.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return trends.income2D;
    }
    //Method to test "readCSV()" method
    public void test(){
        String[][] arr = readCSV();
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
    }     
    //-------------------- WRITE CSV FILE --------------------//
    //testing populating CSV
    public String[][] populate(){
        for(int i = 0; i < trends.income2D.length; i++){
            // trends.income2D[i][0] = trends.incomeCat.get(i);
        }
        return trends.income2D;
    }
    public void writeCSV(){
        try(PrintWriter writer = new PrintWriter(this.fileName)){
            StringBuilder builder = new StringBuilder();
            for(int i = 0; i < trends.income2D.length; i++){
                for(int j =0; j < trends.income2D[0].length; j++){
                    builder.append(trends.income2D[i][j] + ",");
                }
                builder.append("\n");
            }
            writer.write(builder.toString());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }
    //insert importing method name (file io)
    //insert exporting method name
    //insert creating csv name
    //insert adding and deleting categories method
    //insert putting arraylist from the categories into 2d array (the customizeable portion)
}