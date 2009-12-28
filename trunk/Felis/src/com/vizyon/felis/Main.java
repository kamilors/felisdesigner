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
        JFrame frame = new JFrame("Felis - Vizyon Yazılım");
        frame.setSize(new Dimension(800, 600));
        frame.add(new Screen());
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
