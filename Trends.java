import java.util.ArrayList;

public class Trends {
    
    //2D Arrays
    public String[][] income2D;
    public String[][] expense2D;
    
    public ArrayList<String> incomeCat = new ArrayList<String>();  
    public ArrayList<String> expenseCat = new ArrayList<String>(); // we need to make this permanent in the 2d arrays first or something

    public String[] monthNames = {"January", "February", "March", "April", "May", 
    "June", "July", "August", "September", "October", "November", "December"};
    
    
    public int totalAntIncome;
    public int totalAntExpenses;

    public int totalAccIncome;
    public int totalAccExpense;

    public Trends(){

    }

    //Methods
    public String[][] populateCat(ArrayList<String> categoryArrList, String[][] twoDArr){
        // twoDArr = new String[categoryArrList.size()+ 1][4];
        int a = 0;
        for(int i = 1; i < categoryArrList.size()+1; i ++){ //starts at one because 0 is the month name
            twoDArr[i][0] = categoryArrList.get(a);
            a++;
        }
        System.out.println("the length is" + twoDArr.length);

        //testing populate() 
        for(int i = 0; i < twoDArr.length; i++){
            for(int j = 0; j < 2; j++){
                System.out.print(income2D[i][j] + ", ");
                
            } 
            System.out.println();
        }
        return twoDArr;
    }

    public void populate(String[] arr, String[][] income, String[][] expenses){  
        
        int a = 0;
        //populate categories
        for(int i = 0; i < 12; i++){  
            if(i < 6){
                income[i][0] = this.incomeCat.get(i);  
                expenses[i][0] = this.expenseCat.get(i);  
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
    public void populateActual(ArrayList<String> catArr, ArrayList<String> amtArr, String[][] twoDArr, String[] labelCat){ //CHANGE THE INCOME NAMES
        
        
        for(int i = 0; i < 6; i++){ 
            twoDArr[i][0] = labelCat[i];
        }

        
        for(int i = 0; i < 6; i++){
            String temp = labelCat[i]; 
            Double addOn = 0.0;
            for(int j = 0; j < catArr.size(); j++){
                if(!amtArr.get(j).isEmpty() && catArr.get(j) != null){
                    if(catArr.get(j).equals(temp)){
                        addOn += Double.parseDouble(amtArr.get(j));
                   }                
                }   
            }            
            
            twoDArr[i][2] = String.valueOf(addOn);

        }

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(twoDArr[i][j] + ", ");
                
            } 
            System.out.println();
        }        
    }

    //calculates the difference between two doubles
    //can use this to find the difference between acc and ant income/expense, total accIncome - total accExpense
    public void populateDiff(String[][] twoDArr){        

        for(int i = 0; i < 6; i++){
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
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(twoDArr[i][j] + ", ");
                
            } 
            System.out.println();
        }
        
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