import java.util.ArrayList;
import javafx.scene.control.ComboBox;

public class Trends {
    
    //-------------------- GLOBAL VARIABLES --------------------//
    // Arraylists for scene 2, what the user enters is temporarily saved here
    public ArrayList<String> incomeCatList = new ArrayList<>();
    public ArrayList<String> expenseCatList = new ArrayList<>();

        //2D Arrays
    public String[][] income2D = new String[7][4]; // it is initialized first so that it can be passed through to methods, but it's size changes depending on how many categories that the user enters
    public String[][] expense2D = new String[7][4];

    // Default income and expense categories
    public String[] defaultInc = {"Savings", "Paycheck", "Bonus", "Interest", "Allowance", "Other"};      // the space in front makes it line up with the num of rows in the 2d arrays
    public String[] defaultExp = {"Food", "Health", "Transportation", "Utilies", "Personal","Other"}; // we need to make this permanent in the 2d arrays first or something

    public String[] monthNames = {"January", "February", "March", "April", "May", 
    "June", "July", "August", "September", "October", "November", "December"};
    
    public int totalAntIncome;
    public int totalAntExpenses;

    public int totalAccIncome;
    public int totalAccExpense;

    // public FinanceFV financeFV = new FinanceFV();

    //-------------------- CONSTRUCTOR --------------------//
    public Trends(){

    }

    //Methods
    
    public ArrayList<String> defaultCategories(ArrayList<String> categoryArrList, String[] defaultCat){ //if they entered nothing, then populate the user's arraylist with default categories
        
            if(categoryArrList.isEmpty()){
                for(int i = 0; i < defaultCat.length; i ++){ //starts at one because 0 is the month name
                    categoryArrList.add(defaultCat[i]); // populate the categories with default if they entered nothing
                }    
            }
            
            return categoryArrList;
    }

    public String[][] populateCat(ArrayList<String> categoryArrList, String[][] twoDArr, String month){
        twoDArr = new String[categoryArrList.size()+ 1][4];  
        
        
        twoDArr[0][0] = "Month: " + month; // Add the month to the 2d array

        int a = 0;
        for(int i = 1; i < twoDArr.length; i ++){ //starts at one because 0 is the month name
            twoDArr[i][0] = categoryArrList.get(a);
            a++;
        }                
        
        return twoDArr;
    }
    
    public String[][] populate(ArrayList<String> catArr, ArrayList<String> amtArr, String[][] twoDArr, ArrayList<String> labelCat, int col, String month){ //CHANGE THE INCOME NAMES                                 
        
        int a = 0;
        for(int i = 1; i < twoDArr.length; i++){
            String temp = labelCat.get(a); 
            Double addOn = 0.0;
            for(int j = 0; j < catArr.size(); j++){
                if(!amtArr.get(j).isEmpty() && catArr.get(j) != null){
                    if(catArr.get(j).equals(temp)){
                        addOn += Double.parseDouble(amtArr.get(j));
                   }                
                }   
            }        
            a++;    
            
            twoDArr[i][col] = String.valueOf(addOn);            
        }

        for(int i = 0; i < twoDArr.length; i++){
            if (twoDArr[i][1] == null || twoDArr[i][1].equals("")){
                twoDArr[i][1] = "0.0";
            }
            if (twoDArr[i][2] == null){
                twoDArr[i][2] = "0.0";
            }
            if (twoDArr[i][3] == null){
                twoDArr[i][3] = "0.0";
            }
        }
           
        return twoDArr;
    }

    //calculates the difference between two doubles
    //can use this to find the difference between acc and ant income/expense, total accIncome - total accExpense
    public String[][] populateDiff(String[][] twoDArr){        
        
        System.out.println("in diff");
        for(int i = 0; i < twoDArr.length; i++){
            for(int j = 0; j < twoDArr[0].length; j++){
                System.out.print(twoDArr[i][j] + ", ");
                
            } 
            System.out.println();
        }
        
        for(int i = 1; i < twoDArr.length; i++){
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

        for(int i = 1; i < twoDArr.length; i++){
            addTerm = Double.valueOf(twoDArr[i][1]);
            sum += addTerm;
        }
        
        return sum;
    }    
    
    //Calculates the sum of the amounts column in a two d array
    public double sumAccCalculator(String[][] twoDArr){
        double addTerm;
        double sum = 0;

        for(int i = 1; i < twoDArr.length; i++){
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