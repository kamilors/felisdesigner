/*
 * Çizimlerin Yapıldığı sınıftır. JPanel'den türetilmiştir.
 * Duruma göre Applet ya da JFrame içinde örneklenir.
 *
 */

package com.vizyon.felis.core;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author Kamil ÖRS
 */
public class Screen extends JPanel {

    public Screen() {
        initScreen();
    }

    // <editor-fold defaultstate="collapsed" desc="Initialize Screen">
    public void initScreen() {
        // Çizimlerde Göz Kırpma olayını ortadan kaldırır
        setDoubleBuffered(true);
        setBackground(Color.WHITE);
    }
    //</editor-fold>

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Izgarayı Çiz
        drawGrid(g);
    }

    // Grid Çiz
    public void drawGrid(Graphics g) {
        g.setColor(new Color(235, 235, 235));
        for(int i = 0; i<getWidth(); i=i+12) {
            g.drawLine(i, 0, i, getWidth());
        }

        for(int i = 0; i<getHeight(); i=i+12) {
            g.drawLine(0, i, getWidth(), i);
        }
    }


}
