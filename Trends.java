public class Trends {
    
    public int totalAntIncome;
    public int totalAntExpenses;

    public int totalAccIncome;
    public int totalAccExpense;

    public Trends(){

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

    //calculates the difference between two doubles
    //can use this to find the difference between acc and ant income/expense, total accIncome - total accExpense
    public double differenceCalculator(double sumInitial, double sumFinal){
        double difference = sumInitial - sumFinal;
        return difference;
    }

    //calculates the end balance
    public double endBalance(double startingBalance, double accSavings){
        return startingBalance + accSavings;
    }
    //TO DO:
    // -    add all the totals in acc income and expense by using arraylists and cross references with category names to enter into 2d array
}
