/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author willd
 */
public class World extends Thing{
    ArrayList<SeaPort> ports = new ArrayList<>();
    PortTime time;

    //default constructor
    public World() {
        
    }
    // one argument constructor---I don't think this will be used at this point
    public World(Scanner sc) {
        super(sc);
    } // end constructor
    
    // method to add ports to the ArrayList<SeaPort>
    public void addPort(SeaPort s) {
        ports.add(s);
    } // end method
    
    public void addDock(Dock d) {
        for (int i = 0; i<ports.size(); i++) {
            if (d.parent==ports.get(i).index) {
                ports.get(i).addDock(d);
            } // end if
        } // end for
    } // end method
    
    // method to add ship
    public void addShip(Ship s) {    
        for (int i = 0; i<ports.size(); i++) {
            // loop to determine if ship belongs to port
            if (s.parent == ports.get(i).index) {
                        ports.get(i).addQue(s);
                        ports.get(i).addShip(s);
            } else {
                // loop to determine if ship belongs to dock
                for (int j = 0; j<ports.get(i).docks.size(); j++) {
                    if (s.parent == ports.get(i).docks.get(j).index) {
                        ports.get(i).docks.get(j).addShip(s);
                        ports.get(i).addShip(s);
                    } // end if
                } // end for
            } // end if else
        } // end for
    } // end method
    
    // method to add a person
    public void addPerson(Person p) {
        for (int i = 0; i<ports.size(); i++) {
            if (p.parent == ports.get(i).index) {
                ports.get(i).addPerson(p);
            } // end if
        } // end for
    } // end method
    
    
    // method to return String output
    @Override
    public String toString() {
        String outputString = ">>>>> The world:";
        for (SeaPort sp : ports) {
            outputString += "\n" + sp;
        } // end for
        return outputString;
    } // end method
} // end class
