/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zendesk1;

/**
 *
 * @author karan
 */
public class Zendesk1 {
    public static UserInterface frame;
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        frame= new UserInterface(); //NEW UI object
        frame.setVisible(true); //Set it visible
    }
    
}
