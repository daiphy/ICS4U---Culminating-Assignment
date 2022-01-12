/**
 * Base Class - Model - Finance
 */

class Finance {
    //2D Arrays
    public String[][] antIncomeArr;
    public String[][] accIncomeArr;
    public String[][] accExpenseArr;
    public String[][] antExpenseArr;

    

    //Methods
    public void populateCategories(String[] arr, String[][] newArr){        
        String[] categoryArr = {"Food", "Health", "Transportation", "Utilies", "Personal","Other"};
        
        for(int i = 0; i < 6; i++){
            newArr[0][i] = categoryArr[i];
            newArr[1][i] = arr[i]; // the single arrays from the gui
        }        
    }
    //insert importing method name (file io)
    //insert exporting method name
    //insert creating csv name
    //insert adding and deleting categories method
    //insert putting arraylist from the categories into 2d array (the customizeable portion)

}
