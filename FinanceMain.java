public class FinanceMain{
    public static void main(String[] args){
        FinanceFV test = new FinanceFV();
        // String coords = test.checkForMonth(test.chosenMonth);
        // if(coords.equals("")){
        //     test.appendCSV(test.readCSV());
        // }
        //start of test for updating CSV
        test.findNext(); //this updates the global variable test.nextMonth
        String initialCoords = test.checkForMonth(test.chosenMonth);
        int row = Integer.parseInt(initialCoords.substring(0,initialCoords.indexOf(",")));
        int col = Integer.parseInt(initialCoords.substring(initialCoords.indexOf(",") + 1,initialCoords.length()));
        String[][] array = test.readCSV();
        boolean updated = test.update2DArr(array, row, col, 0);
        if(updated){
            test.updateCSV(array);
        }  
    }
}