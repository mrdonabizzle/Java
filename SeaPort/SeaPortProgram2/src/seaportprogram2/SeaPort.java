/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 *
 * @author willd
 */
public class SeaPort extends Thing{
    ArrayList<Dock> docks; // list of all docks 
    ArrayList<Ship> que; //list of ships waiting to dock
    ArrayList<Ship> ships; //list of all ships at this port
    ArrayList<Person> persons; // people with skills at this port

    public SeaPort(Scanner sc) {
        super(sc);
        docks = new ArrayList<>();
        que = new ArrayList<>();
        ships = new ArrayList<>();
        persons = new ArrayList<>();
    } // end constructor
    /*
    public int getIndex() {
        return this.index;
    }
    */
    public void addDock(Dock d) {
        docks.add(d);
    }
    
    
    public void addQue(Ship s) {
       que.add(s);
    }
    
    public void addShip(Ship s) {
        ships.add(s);
    }
    
    public void addPerson(Person p) {
        persons.add(p);
    }
    
    public void addJob(Scanner sc) {
        
    }
     
    // this method produces a String
    @Override
    public String toString() {
        String outputString = "\nSeaPort: " + super.toString() + "\n";
        for (int i = 0; i<docks.size(); i++)
            outputString += "\n" + docks.get(i).toString();
        outputString += "\n\n --- List of all ships in que: ";
        for (Ship s: que)
            outputString += "\n  > " + s;
        outputString += "\n\n --- List of all ships: ";
        for (int i = 0; i<ships.size(); i++) 
            outputString += "\n  > " + ships.get(i).toString();
        outputString += "\n\n --- List of all persons: ";
        for (int i = 0; i<persons.size(); i++) 
            outputString += "\n  > " + persons.get(i).toString();
        return outputString;
    } // end method
    
    public String searchToString() {
        String outputString = "\nSeaPort: " + super.toString();
        return outputString;
    } // end method
    
    public String sortQueToString() {
        String output = "\n\nSeaPort: " + super.toString() + "\n";
        output += "\n --- List of all ships in que: ";
        for (Ship s : ships)
            output+= "\n > " + s;
        return output;
    }
} // end class
