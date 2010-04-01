/**
 * Project : Felis
 * URL     : http://felis.vizyonyazilim.com
 * E-Mail  : kamil@vizyonyazilim.com
 * Desc    : Visual Java Entity Bean Designer
 */

package com.vizyon.felis;


import javax.swing.UIManager;

/**
 *
 * @author Kamil ÖRS
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        setLookAndFeel();
        FelisMain demo = new FelisMain();
        demo.setVisible(true);
   
    }

    public static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

}
