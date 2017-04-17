/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author willd
 */
public class World extends Thing{
    ArrayList<SeaPort> ports = new ArrayList<>();
    PortTime time;

    //constructor
    public World() {
        
    }
    
    public World(Scanner sc) {
        super(sc);
    } // end constructor
    
    // method to add ports to the ArrayList<SeaPort>
    public void addPort(SeaPort s/*, HashMap<Integer, SeaPort> spHM*/) {
        ports.add(s);
    } // end method
    
    public void addDock(Dock d, HashMap<Integer, SeaPort> spHM) {
        if (spHM.containsKey(d.getParent())) {
            spHM.get(d.getParent()).addDock(d);
        } else {
            System.out.println("Parent of dock " + d.getName() + " not found.");
        } // end if else
    } // end method
    
    public void addShip(Ship s, HashMap<Integer, SeaPort> spHM, HashMap<Integer, Dock> dHM) {    
        if (spHM.containsKey(s.getParent())) {
            spHM.get(s.getParent()).addQue(s);
            spHM.get(s.getParent()).addShip(s);
        } else if (dHM.containsKey(s.getParent())) {
            dHM.get(s.getParent()).addShip(s);
            spHM.get(dHM.get(s.getParent()).getParent()).addShip(s);
        } else {
            System.out.println("Parent of ship " + s.getName() + " not found.");
        }
    } // end method
    
    public void addPerson(Person p, HashMap<Integer, SeaPort> spHM) {
        if (spHM.containsKey(p.getParent())) {
            spHM.get(p.getParent()).addPerson(p);
        } else {
            System.out.println("Parent of person " + p.getName() + " not found.");
        }
    }
    
    public void addJob(Job j) {
        for (SeaPort sp : ports) {
            for (Ship s : sp.ships) {
                if (j.getParent() == s.getIndex()) {
                    s.addJob(j);
                } // end if
            } // end for
        } // end for
    } // end method
    
    
    
    @Override
    public String toString() {
        String outputString = ">>>>> The world:";
        for (SeaPort sp : ports) {
            outputString += "\n" + sp;
        }
        return outputString;
    } // end method
    
}
