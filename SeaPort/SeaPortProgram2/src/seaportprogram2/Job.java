/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram2;

import java.util.ArrayList;
import java.util.Scanner;

/*
 * This class is optional until Projects 3 and 4
 * 
 */
public class Job extends Thing{
    double duration;
    ArrayList<String> requirements; // should be some of the skills of the persons

    public Job(Scanner sc) {
        super(sc);
    }
}
