import java.util.ArrayList;

public class Trends {
    
    //2D Arrays
    public String[][] income2D = new String[6][4];
    public String[][] expense2D = new String[6][4];
    
    public String[] incomeCat = {"Savings", "Paycheck", "Bonus", "Interest", "Allowance", "Other"};      
    public String[] expenseCat = {"Food", "Health", "Transportation", "Utilies", "Personal","Other"}; // we need to make this permanent in the 2d arrays first or something

    public String[] monthNames = {"January", "February", "March", "April", "May", 
    "June", "July", "August", "September", "October", "November", "December"};
    
    
    public int totalAntIncome;
    public int totalAntExpenses;

    public int totalAccIncome;
    public int totalAccExpense;

    public Trends(){

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
                System.out.print(income[i][j] + ", ");
                
            } 
            System.out.println();
        }
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(expenses[i][j] + ", ");
                
            } 
            System.out.println();
        }


    }
    public void populateActual(ArrayList<String> catArr, ArrayList<String> amtArr, String[][] income, String[]incomeCat){ //CHANGE THE INCOME NAMES
        
        
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
            
            income[i][2] = String.valueOf(addOn);

        }

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(income[i][j] + ", ");
                
            } 
            System.out.println();
        }
    }

    //calculates the difference between two doubles
    //can use this to find the difference between acc and ant income/expense, total accIncome - total accExpense
    public void populateDiff(String[][] twoDArr){
        for(int i = 0; i < 6; i++){
            double ant = Double.parseDouble(twoDArr[i][1]);
            double acc = Double.parseDouble(twoDArr[i][2]);
            double diff = ant-acc;
            twoDArr[i][3] = Double.toString(diff);            
        }
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(twoDArr[i][j] + ", ");
                
            } 
            System.out.println();
        }      
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

    //calculates the end balance
    public double endBalance(double startingBalance, double accSavings){
        return startingBalance + accSavings;
    }
    //TO DO:
    // -    add all the totals in acc income and expense by using arraylists and cross references with category names to enter into 2d array
}
