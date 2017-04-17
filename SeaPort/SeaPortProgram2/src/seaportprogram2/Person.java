/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram2;

import java.util.Scanner;

/**
 *
 * @author willd
 */
public class Person extends Thing{
    String skill;

    // constructor
    public Person(Scanner sc) {
        super(sc);
        if (sc.hasNext()) {
            skill = sc.next();
        } // end if
    } // end constructor
    
    @Override
    public String toString() {
        String outputString = "Person: " + super.toString() + " " + skill;
        return outputString;
    }
}
