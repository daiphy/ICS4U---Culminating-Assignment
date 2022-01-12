/**
 * Base Class - Model - Finance
 */

class Finance {
    //2D Arrays
    public String[][] antIncomeArr = new String[6][2];
    public String[][] accIncomeArr = new String[6][2];
    public String[][] accExpenseArr = new String[6][2];
    public String[][] antExpenseArr = new String[6][2];

    public Finance(){

    }

    //Methods
    public void populate(String[] arr, String[][] income, String[][] expenses){  
        String[] incomeCat = {"Savings", "Paycheck", "Bonus", "Interest", "Allowance", "Other"};      
        String[] expenseCat = {"Food", "Health", "Transportation", "Utilies", "Personal","Other"};
        
        int a = 0;
        //populate categories
        for(int i = 0; i < 12; i++){  
            if(i < 6){
                income[i][0] = incomeCat[i];  
                expenses[i][0] = expenseCat[i];  
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
                System.out.print(antExpenseArr[i][j] + ", ");
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