/**
 * Project : Felis
 * URL     : http://felis.vizyonyazilim.com
 * E-Mail  : kamil@vizyonyazilim.com
 * Desc    : Visual Database Designer
 */

package com.vizyon.felis;

import com.vizyon.felis.core.Screen;
import java.awt.Dimension;
import javax.swing.JFrame;
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
        Demo demo = new Demo();
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
