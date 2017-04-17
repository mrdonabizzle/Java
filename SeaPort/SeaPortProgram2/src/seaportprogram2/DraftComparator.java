/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package seaportprogram2;

import java.util.Comparator;

/**
 *
 * @author willd
 */
public class DraftComparator implements Comparator<Ship>{

    @Override
    public int compare(Ship o1, Ship o2) {
        return (int) (o1.draft-o2.draft);
    }
    
}
