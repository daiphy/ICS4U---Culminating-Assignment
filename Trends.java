/**
 * Daiphy, Hilary, Jane, Rachel
 * Teacher: Mr. Ho
 * ICS4U Culminating Project
 * January 24, 2022
 * Trends.java
 */

//********** IMPORTS **********//
import java.util.ArrayList;

//********** CLASS **********//
public class Trends {

    // -------------------- GLOBAL VARIABLES --------------------//
    // 2D Arrays which store anticipated, actual, and difference data
    public String[][] income2D = new String[7][4]; // Set so that the user can add up to six categories
    public String[][] expense2D = new String[7][4];

    // Arraylists for scene 2, what the user enters is temporarily saved here
    public ArrayList<String> incomeCatList = new ArrayList<>();
    public ArrayList<String> expenseCatList = new ArrayList<>();

    // Default income and expense categories
    public String[] defaultInc = { "Savings", "Paycheck", "Bonus", "Interest", "Allowance", "Other" };
    public String[] defaultExp = { "Food", "Health", "Transportation", "Utilies", "Personal", "Other" }; 

    // Contains all the month names in a year
    public String[] monthNames = { "January", "February", "March", "April", "May",
            "June", "July", "August", "September", "October", "November", "December" };

    // -------------------- CONSTRUCTOR --------------------//
    public Trends() {
    }

    // -------------------- POPULATE USER ENTRIES ARRAYLIST WITH DEFAULT CATEGORIES --------------------//
    /**
     * Changes the user's entries arraylists to the default categories
     * 
     * @param categoryArrList the user's entries arraylist (can be income or expense)
     * @param defaultCat the array of the default categories (can be income or expense)
     * @return the updated version of the user's entries arraylist
     */
    public ArrayList<String> defaultCategories(ArrayList<String> categoryArrList, String[] defaultCat) {

        if (categoryArrList.isEmpty()) { // if they entered nothing, then populate the user's arraylist with default categories
            for (int i = 0; i < defaultCat.length; i++) { // starts at one because 0 is the month name
                categoryArrList.add(defaultCat[i]); // populate the categories with default if they entered nothing
            }
        }
        return categoryArrList;
    }

    // -------------------- POPULATE 2D ARRAY WITH CATEGORIES METHOD --------------------//
    /**
     * Populates the first column of the 2D array so that all the categories are
     * stored
     * 
     * @param categoryArrList the user's entries arraylist
     * @param twoDArr the 2D array that stores user data (can be income or expense)
     * @param month the month that the user wants to work on
     * @return the updated version of the 2D array
     */
    public String[][] populateCat(ArrayList<String> categoryArrList, String[][] twoDArr, String month) {

        int addEmpty = twoDArr.length - categoryArrList.size(); // number of empty spaces we need to add to the arraylist (so that it can match the length of the 2D arrays)
        for (int i = 0; i < addEmpty; i++) { // add the empty spaces
            categoryArrList.add("");
        }

        twoDArr[0][0] = "Month: " + month; // Add the month to the 2d array (will always be [0][0] of any 2D array)

        int a = 0; // counter for the arraylist since its one index less than 2D array
        for (int i = 1; i < twoDArr.length; i++) { // starts at one because 0 is the month name
            twoDArr[i][0] = categoryArrList.get(a); // populates the first column of the 2D array
            a++;
        }
        return twoDArr;
    }

    // -------------------- POPULATE ANTICIPATED AND ACTUAL COLUMNS OF 2D ARRAY METHOD --------------------//
    /**
     * Populates the anticipated and actual columns of the 2D arrays from what the user entered.
     * Since the user can enter a specific category with an amount($) several times, this method takes the totals of the amount and stores in all into one place
     * @param catArr arraylist that holds all the categories that the user has entered (can be for income or expense)
     * @param amtArr arraylist that holds all the amounts that the user has entered (can be for income or expense)
     * @param twoDArr the 2D array that stores user data (can be income or expense)
     * @param labelCat the arraylist that has all of the categories (no duplicates, the one we reference off of)
     * @param col the index number of the column we want to store to (because anticipated and actual are in different columns)
     * @param month the month that the user wants to work on
     * @return the updated version of the 2D array (holds categories, anticipated and actual)
     */
    public String[][] populate(ArrayList<String> catArr, ArrayList<String> amtArr, String[][] twoDArr, ArrayList<String> labelCat, int col, String month) { // CHANGE THE INCOME NAMES

        int a = 0; // counter for the arraylist since its one index less than 2D array
        for (int i = 1; i < twoDArr.length; i++) { // loops through the 2D array with just the values and not the month name
            String temp = labelCat.get(a);
            Double addOn = 0.0;
            for (int j = 0; j < catArr.size(); j++) { // loops through the size of the catArr because the user can enter many different amounts per category
                if (!amtArr.get(j).isEmpty() && catArr.get(j) != null) { // if the amount and category arrays are not empty/null, and the category array is aligned with
                    if (catArr.get(j).equals(temp)) { // the category label list, then add the values of the amount together (this gets the total)
                        addOn += Double.parseDouble(amtArr.get(j));
                    }
                }
            }
            a++;

            twoDArr[i][col] = String.valueOf(addOn); // this populates the 2D array with the calculated sum of the
                                                     // entries for anticipated/actual
        }
        for (int i = 0; i < twoDArr.length; i++) { // looping through the entire array, if it's null or empty then
                                                   // replace that element with 0.0
            if (twoDArr[i][1] == null || twoDArr[i][1].equals("")) {
                twoDArr[i][1] = "0.0";
            }
            if (twoDArr[i][2] == null) {
                twoDArr[i][2] = "0.0";
            }
            if (twoDArr[i][3] == null) {
                twoDArr[i][3] = "0.0";
            }
        }

        return twoDArr;
    }

    // -------------------- POPULATE 2D ARRAY WITH CALCULATED DIFFERENCE METHOD --------------------//
    /**
     * This method calculates the difference between two doubles then
     * populates the calculated differences into a 2D array
     * 
     * @param twoDArr this is the 2D array that the data is being stored in
     * @param choice if it is populating income or expense
     * @return this method will return the 2D array with the updated data
     */
    public String[][] populateDiff(String[][] twoDArr, String choice) {
        for (int i = 1; i < twoDArr.length; i++) {
            // initialize a double for anticipated and actual income/expense
            double ant = Double.parseDouble(twoDArr[i][1]);
            double acc = Double.parseDouble(twoDArr[i][2]);
            double diff = ant - acc; // calculate the difference between two doubles
            if(choice.equals("income")){    // changes the diff to actual minus anticipated because your actual amount is important
                diff = acc - ant;
            }            
            twoDArr[i][3] = Double.toString(diff); // populate the 2D array with the calculated difference
        }
        return twoDArr;
    }
    // -------------------- CALCULATES SUM OF AMOUNTS COLUMN METHOD --------------------//
    /**
     * Calculates the sum of the amounts columns in the 2D array (anticipated or actual)
     * @param twoDArr the 2D array that stores user data (can be income or expense)                   
     * @param col the index number of the column for the 2D array
     * @return the sum of the amounts
     */
    public double sumCalculator(String[][] twoDArr, int col) {
        // Initialize doubles
        double addTerm; // value to add to the sum
        double sum = 0; // stores the sum of added values        
        
        for (int i = 1; i < twoDArr.length; i++) {    // Loops through a 2D array (either the income or expense array)
            addTerm = Double.valueOf(twoDArr[i][1]);  // Adds the values of the anticipated income/expense
            sum += addTerm;
        }
        return sum;
    }    
    // -------------------- REFRESH 2D ARRAY METHOD --------------------//
    /**
     * This refreshes the 2D array so that it can either repopulate or add new user entries to a new month
     * (everything except the categories and month)
     * @param twoDArr 2D array (either income or expense)
     * @return refreshed 2D array
     */
    public String[][] refreshArr(String[][] twoDArr){
        for(int i = 1; i < twoDArr.length; i++){
            for(int j = 1; j < twoDArr[0].length; j++){
                twoDArr[i][j] = "0.0";
            }
        }    
        return twoDArr;
    }
}