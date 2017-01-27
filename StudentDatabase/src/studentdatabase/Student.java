/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentdatabase;
/*
 * File:  StudentDatabase.java
 * Author:  Will Donabedian
 * Date:  December 18, 2016
 * Purpose:  This program implements an GUI that allows the user to insert,
 * delete, find, and update student records in a hashmap database.
 */

public class Student {
    // declare variables
    private String studentName;
    private String studentMajor;
    private float totalQualityPoints;
    private float totalCredits;
    
    // Student constructor method
    public Student(String studentName, String studentMajor) {
        this.studentName = studentName;
        this.studentMajor = studentMajor;
        totalQualityPoints = 0;
        totalCredits = 0;      
    } // end method
    
    // Method for updating student records with new course information
    public void courseCompleted(float grade, int credits) {
        totalQualityPoints = totalQualityPoints + (grade * 
                credits);
        totalCredits = totalCredits + credits;      
    } // end method
    
    // Method for printing student information
    public String toString(){
        if (totalCredits == 0) {
            return "Name:  " + studentName + "\nMajor:  " + studentMajor +
                    "\nGPA:  4.0";
        } else {
            return  "Name:  " + studentName + "\nMajor:  " + studentMajor + 
                "\nGPA:  " + String.format("%.2g%n", 
                        totalQualityPoints/totalCredits);
        } // end if else
    } // end method
} // end class
