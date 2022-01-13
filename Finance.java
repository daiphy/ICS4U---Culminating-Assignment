/**
 * Base Class - Model - Finance
 */
import java.io.*;
import java.util.ArrayList;

class Finance {
    //2D Arrays
    public String[][] antIncomeArr = new String[6][2];
    public String[][] antExpenseArr = new String[6][2];
    public String[][] accIncomeArr = new String[6][2];
    public String[][] accExpenseArr = new String[6][2];
    public String[] incomeCat = {"Savings", "Paycheck", "Bonus", "Interest", "Allowance", "Other"};      
    public String[] expenseCat = {"Food", "Health", "Transportation", "Utilies", "Personal","Other"}; // we need to make this permanent in the 2d arrays first or something

    public String[] monthNames = {"January", "February", "March", "April", "May", 
    "June", "July", "August", "September", "October", "November", "December"};

    public int arraySize = 12;
    public String directory = "C:/Users/Rachel/Downloads/Comp Sci/5. Culminating/BudjetingApp/src/";
    public String csvName = "antIncome.csv";

    public Finance(){

    }

    //Methods
    public void populate(String[] arr, String[][] income, String[][] expenses){  
        
        int a = 0;
        //populate categories
        for(int i = 0; i < 12; i++){  
            if(i < 6){
                income[i][0] = this.incomeCat[i];  
                expenses[i][0] = this.expenseCat[i];  
                income[i][1] = arr[i];  
            }
            else{
                expenses[a][1] = arr[i]; 
                a++;  
            }       
        } 
        
        //testing populate() 
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(antIncomeArr[i][j] + ", ");
                
            } 
            System.out.println();
        }
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(antExpenseArr[i][j] + ", ");
                
            } 
            System.out.println();
        }


    }
    public void populateAccArr(ArrayList<String> catArr, ArrayList<String> amtArr, String[][] income, String[]incomeCat){
        
        
        for(int i = 0; i < 6; i++){ 
            income[i][0] = incomeCat[i];
        }

        
        for(int i = 0; i < 6; i++){
            String temp = incomeCat[i]; 
            int addOn = 0;
            for(int j = 0; j < catArr.size(); j++){
                if(!amtArr.get(j).isEmpty() && catArr.get(j) != null){
                    if(catArr.get(j).equals(temp)){
                        addOn += Integer.parseInt(amtArr.get(j));
                   }                
                }   
            }            
            
            income[i][1] = String.valueOf(addOn);

        }

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(income[i][j] + ", ");
                
            } 
            System.out.println();
        }
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