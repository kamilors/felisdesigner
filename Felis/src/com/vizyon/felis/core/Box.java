/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RectangularShape;
import java.awt.geom.RoundRectangle2D;

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
    private int round;
    private boolean fill;

    public Box() {
        round = 0;
        left = 20;
        top = 20;
        width = 150;
        height = 100;
        bgColor = new Color(255, 173, 0);
        fill = true;
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

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public RectangularShape getShape() {
        return shape;
    }

    public void setShape(RectangularShape shape) {
        this.shape = shape;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }



    // </editor-fold>

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(bgColor);
        shape = new RoundRectangle2D.Double(left, top, width, height,round,round);
        if(fill) {
            g2.fill(shape);
        }
        else {
            g2.setStroke(new BasicStroke(new Float(2)));
            g2.draw(shape);
        }
    }
}
