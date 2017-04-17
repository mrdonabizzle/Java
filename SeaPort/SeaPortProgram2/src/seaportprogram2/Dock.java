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
public class Dock extends Thing{
    Ship ship=null;

    public Dock(Scanner sc) {
        super(sc);       
    }
    
    public void addShip(Ship s) {
        /*
        if (s.parent==this.index) {
            ship = s;
        }*/
        ship = s;
        
    }
    
    public String toString() {
        String outputString = "Dock: " + super.toString() + "\n";
        if (ship!=null) {
            outputString+= "  Ship: " + ship.toString() + "\n";
        }
        return outputString;
    }
    
    public String searchToString() {
        String outputString = "Dock: " + super.toString();
        return outputString;
    }
}
