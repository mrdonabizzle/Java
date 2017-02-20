/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project3;

/**
 *
 * @author willd
 */
public class Fraction implements Comparable<Fraction>{
    private String fraction;
    private double key;
    private String[] fractionArray;
    private Fraction comparable;
    
    // Fraction constructor
    public Fraction(String fraction) {
        this.fraction = fraction;
        fractionArray = this.fraction.split("/");
        key = Double.parseDouble(fractionArray[0])/Double.parseDouble(fractionArray[1]);
        if (Integer.parseInt(fractionArray[1]) == 0) {
            // throw exception for denominator of 0
            throw new ArithmeticException();
        } // end if
        if (fractionArray.length > 2) {
            // throw exception for non numeric input
            throw new NumberFormatException();
        } // end if
        // modify so that it doesn't allow fractions like "1/3/"
    } // end method
    
    @Override
    public String toString() {
        return fraction;
    } // end method

    // This method returns the value of the fraction as a double
    public double getValue() {
        return key;
    }
    @Override
    public int compareTo(Fraction other) {
        comparable = other;
        if (getValue() > comparable.getValue()) {
            return 1;
        } else if (getValue() < comparable.getValue()) {
            return -1;
        } else
            return 0;
    } // end method
} // end class
