/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis.core;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;

/**
 *
 * @author Kamil ÖRS
 */
public class Box {

    private int left;
    private int top;
    private int width;
    private int height;
    private Color bgColor;
    private RectangularShape shape;


    public Box() {
        left = 0;
        top = 0;
        width = 100;
        height = 100;
        bgColor = new Color(33, 33, 33);
    }

    // <editor-fold defaultstate="collapsed" desc="Getter And Setter">
    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    // </editor-fold>

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(bgColor);
        shape = new Rectangle2D.Double(left, top, width, height);
        g2.fill(shape);
    }
}
