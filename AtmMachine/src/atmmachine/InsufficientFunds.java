/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atmmachine;
import javax.swing.JOptionPane;

/**
 *
 * @author Greg
 */
public class InsufficientFunds extends Exception {
    public InsufficientFunds() {
        JOptionPane.showMessageDialog(null, "Insufficient Funds");
    }
}
