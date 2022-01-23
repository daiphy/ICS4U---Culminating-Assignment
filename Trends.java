import java.util.ArrayList;
import javafx.scene.control.ComboBox;

public class Trends {
    
    //-------------------- GLOBAL VARIABLES --------------------//
    //2D Arrays
    public String[][] income2D = new String[7][4];
    public String[][] expense2D = new String[7][4];
    
    public String[] incomeCat = {" ", "Savings", "Paycheck", "Bonus", "Interest", "Allowance", "Other"};      // the space in front makes it line up with the num of rows in the 2d arrays
    public String[] expenseCat = {" ", "Food", "Health", "Transportation", "Utilies", "Personal","Other"}; // we need to make this permanent in the 2d arrays first or something

    public String[] monthNames = {"January", "February", "March", "April", "May", 
    "June", "July", "August", "September", "October", "November", "December"};
    
    public String chosenMonth;
    public int totalAntIncome;
    public int totalAntExpenses;

    public int totalAccIncome;
    public int totalAccExpense;

    // public FinanceFV financeFV = new FinanceFV();

    //-------------------- CONSTRUCTOR --------------------//
    public Trends(){

    }

    //Methods
    // public String[][] populateCat(ArrayList<String> categoryArrList, String[][] twoDArr){
    //     // twoDArr = new String[categoryArrList.size()+ 1][4];
    //     int a = 0;
    //     for(int i = 1; i < categoryArrList.size()+1; i ++){ //starts at one because 0 is the month name
    //         twoDArr[i][0] = categoryArrList.get(a);
    //         a++;
    //     }
    //     System.out.println("the length is" + twoDArr.length);

    //     //testing populate() 
    //     for(int i = 0; i < twoDArr.length; i++){
    //         for(int j = 0; j < 2; j++){
    //             System.out.print(income2D[i][j] + ", ");
                
    //         } 
    //         System.out.println();
    //     }
    //     return twoDArr;
    // }

    // public void populateCat(String[] arr, String[][] income, String[][] expenses){  
        
    //     int a = 0;
    //     //populate categories and anticipated
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
    //             System.out.print(income[i][j] + ", ");
                
    //         } 
    //         System.out.println();
    //     }
    //     for(int i = 0; i < 6; i++){
    //         for(int j = 0; j < 2; j++){
    //             System.out.print(expenses[i][j] + ", ");
                
    //         } 
    //         System.out.println();
    //     }


    // }
    public void getMonth(ComboBox cBMonths){
        cBMonths.setOnAction(action ->{
            this.chosenMonth = (String)cBMonths.getValue();
            System.out.println(this.chosenMonth);
        });
    }
    public String[][] populate(ArrayList<String> catArr, ArrayList<String> amtArr, String[][] twoDArr, String[] labelCat, int col){ //CHANGE THE INCOME NAMES
        
        
        for(int i = 0; i < twoDArr.length; i++){ 
            if(i == 0){
                System.out.print(this.chosenMonth);
                twoDArr[i][0] = "Month: " + this.chosenMonth;
            }
            else{
                twoDArr[i][0] = labelCat[i]; //this inputs the categories in column 0 of the 2d array
            }
        }

        
        for(int i = 0; i < twoDArr.length; i++){
            String temp = labelCat[i]; 
            Double addOn = 0.0;
            for(int j = 0; j < catArr.size(); j++){
                if(!amtArr.get(j).isEmpty() && catArr.get(j) != null){
                    if(catArr.get(j).equals(temp)){
                        addOn += Double.parseDouble(amtArr.get(j));
                   }                
                }   
            }            
            
            twoDArr[i][col] = String.valueOf(addOn);

        }
        System.out.println("in populate");
        for(int i = 0; i < twoDArr.length; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(twoDArr[i][j] + ", ");
                
            } 
            System.out.println();
        }        
        return twoDArr;
    }

    //calculates the difference between two doubles
    //can use this to find the difference between acc and ant income/expense, total accIncome - total accExpense
    public String[][] populateDiff(String[][] twoDArr){        

        for(int i = 0; i < twoDArr.length; i++){
            if (twoDArr[i][1] == null || twoDArr[i][1].equals("")){
                System.out.println("no longer empty");
                twoDArr[i][1] = "0.0";
            }

            if (twoDArr[i][2] == null){
                twoDArr[i][2] = "0.0";
            }
            if (twoDArr[i][3] == null){
                twoDArr[i][3] = "0.0";
            }
        }
        System.out.println("in diff");
        for(int i = 0; i < twoDArr.length; i++){
            for(int j = 0; j < twoDArr[0].length; j++){
                System.out.print(twoDArr[i][j] + ", ");
                
            } 
            System.out.println();
        }
        
        for(int i = 0; i < twoDArr.length; i++){
            double ant = Double.parseDouble(twoDArr[i][1]);
            double acc = Double.parseDouble(twoDArr[i][2]);                        
            double diff = ant-acc;
            twoDArr[i][3] = Double.toString(diff);            
        }
        for(int i = 0; i < twoDArr.length; i++){
            for(int j = 0; j < twoDArr[0].length; j++){
                System.out.print(twoDArr[i][j] + ", ");
                
            } 
            System.out.println();
        }      
        return twoDArr;
    }


    //Calculates the sum of the amounts column in a two d array
    public double sumCalculator(String[][] twoDArr){
        double addTerm;
        double sum = 0;

        for(int i = 0; i < twoDArr[0].length; i++){
            addTerm = Double.valueOf(twoDArr[i][1]);
            sum += addTerm;
        }
        
        return sum;
    }    
    
    //Calculates the sum of the amounts column in a two d array
    public double sumAccCalculator(String[][] twoDArr){
        double addTerm;
        double sum = 0;

        for(int i = 0; i < twoDArr[0].length; i++){
            addTerm = Double.valueOf(twoDArr[i][2]);
            sum += addTerm;
        }
        
        return sum;
    }

    //calculates the end balance
    public double endBalance(double startingBalance, double accSavings){
        return startingBalance + accSavings;
    }
    //TO DO:
    // -    add all the totals in acc income and expense by using arraylists and cross references with category names to enter into 2d array
}
