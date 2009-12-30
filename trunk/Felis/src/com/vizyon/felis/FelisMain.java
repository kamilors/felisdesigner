/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis;

import com.vizyon.felis.core.Screen;
import com.vizyon.felis.form.ToolKitForm;
import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Kamil ÖRS
 */
public class FelisMain extends JFrame {

    ToolKitForm toolForm;

    public FelisMain() {

        setTitle("Felis - Vizyon Yazilim");
        setSize(800,600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel wrap = new JPanel();
        wrap.setLayout(new BorderLayout());
        getContentPane().add(wrap);

        Screen screen = new Screen();

        JScrollPane pane = new JScrollPane();
        pane.getViewport().add(screen);
        pane.setFocusable(false);
        pane.setAutoscrolls(true);
        
        wrap.add(pane,BorderLayout.CENTER);

        toolForm = new ToolKitForm(this,screen);
        toolForm.setVisible(true);
        
        windowListender();

    }

    private void windowListender() {
        addWindowListener(new WindowListener() {

            public void windowOpened(WindowEvent e) {
            }

            public void windowClosing(WindowEvent e) {
            }

            public void windowClosed(WindowEvent e) {
            }

            public void windowIconified(WindowEvent e) {
                toolForm.setState(JFrame.ICONIFIED);
            }

            public void windowDeiconified(WindowEvent e) {
                toolForm.setState(JFrame.NORMAL);
            }

            public void windowActivated(WindowEvent e) {
                toolForm.setState(JFrame.NORMAL);
            }

            public void windowDeactivated(WindowEvent e) {
                if(!toolForm.isActive()) {
                    //toolForm.setState(JFrame.ICONIFIED);
                }
            }
        });
    }


}
