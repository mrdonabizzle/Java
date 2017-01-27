/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sequencecalculator;

public class Sequence {
    // create variables
    private static int number = 0;
    private static int nMinusOne = 1;
    private static int nMinusTwo = 0;
    private static int counter = 0;
    private static int efficiency = 0;
    
    // create Iterative method
    public static int computeIterative(int n) {
        number = n;
        nMinusOne = 1;
        nMinusTwo = 0;
        counter = 0;
        if (number == 0) {
            counter++;
            return 0;
        } else if (number == 1) {
            counter++;
            return 1;
        } else { // if (number >= 2)
            for (int i = 2; i <= n; i++) {
                number = 2*nMinusOne + nMinusTwo;
                nMinusTwo = nMinusOne;
                nMinusOne = number;
                counter++;
            } // end for
        } // end if else
        return number;                
    } // end method
    
    // create Recursive method
    public static int computeRecursive(int n) {
        counter++;
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else { // if (n >= 2) 
            return 2*computeRecursive(n-1) + computeRecursive(n-2); 
        } // end if else
    } // end method
    
    public static int getEfficiency() {
        efficiency = counter;
        counter = 0;
        return efficiency;
    } // end method
} // end class
