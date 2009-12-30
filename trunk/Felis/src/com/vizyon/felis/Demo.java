/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis;

import com.vizyon.felis.core.Screen;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.RuleBasedCollator;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Kamil ÖRS
 */
public class Demo extends JFrame {

    public Demo() {

        setTitle("demo");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JPanel wrap = new JPanel();
        wrap.setLayout(new BorderLayout());
        getContentPane().add(wrap);

        Screen screen = new Screen();

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(screen);
        pane.setFocusable(false);
        pane.setAutoscrolls(true);
        
        wrap.add(pane,BorderLayout.CENTER);
    }

}
