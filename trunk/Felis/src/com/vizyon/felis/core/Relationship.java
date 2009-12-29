/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis.core;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;

/**
 *
 * @author Kamil ÖRS
 */
public class Relationship {

    static int LEFT = 0;
    static int RIGHT = 1;
    static int TOP = 2;
    static int BOTTOM = 3;

    public static int ONE_TO_ONE;
    public static int ONE_TO_MANY;
    public static int MANY_TO_MANY;

    private String name;
    private Field field;
    private Table toTable;
    private int type;

    //<editor-fold desc="Getter and setter">
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }

    public Table getToTable() {
        return toTable;
    }

    public void setToTable(Table toTable) {
        this.toTable = toTable;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //</editor-fold>



    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(33, 33, 33));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        
        float dash[] = {10.0f,5.0f,10.0f};
        g2.setStroke(new BasicStroke(1.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_ROUND,10.0f,dash,5.0f));

        Point from = getFromDrawPoint();
        Point to = getToDrawPoint();

        g2.drawLine(from.x, from.y, to.x, to.y);
        
    }


    public Point getFromDrawPoint() {
        Box from = field.getTable().getBox();
        Box to = toTable.getBox();
        int postion = reversePositionForDraw(getPosition(from, to));
        Point point;
        
        if(postion == LEFT) {
            point = new Point(from.getA().x, from.getA().y);
        }
        else if(postion == TOP) {
            point = new Point(from.getB().x, from.getB().y);
        }
        else if(postion == RIGHT) {
            point = new Point(from.getC().x, from.getC().y);
        }
        else if(postion == BOTTOM){
            point = new Point(from.getD().x, from.getD().y);
        }
        else {
            point = new Point();
        }
        
        return point;
    }

    public Point getToDrawPoint() {
        Box to = field.getTable().getBox();
        Box from = toTable.getBox();
        int postion = reversePositionForDraw(getPosition(from, to));
        Point point;

        if(postion == LEFT) {
            point = new Point(from.getA().x, from.getA().y);
        }
        else if(postion == TOP) {
            point = new Point(from.getB().x, from.getB().y);
        }
        else if(postion == RIGHT) {
            point = new Point(from.getC().x, from.getC().y);
        }
        else if(postion == BOTTOM){
            point = new Point(from.getD().x, from.getD().y);
        }
        else {
            point = new Point();
        }

        return point;
    }

    public int getPosition(Box from, Box to) {
        if(from.getC().x < to.getC().x) {
            if(from.getA().x + 30 < to.getC().x) {
                System.out.println("LEFT");
                return LEFT;
            }
            else  {
                if((from.getB().y > to.getD().y) ||(from.getB().y < to.getD().y && from.getB().y > to.getB().y)) {
                    System.out.println("TOP");
                    return TOP;
                }
                else {
                    System.out.println("BOTTOM");
                    return BOTTOM;
                }
            }
        }
        else {
            if(from.getC().x + 30 < to.getA().x) {
                System.out.println("RIGHT");
                return  RIGHT;
            }
            else {
                if((from.getB().y > to.getD().y) ||(from.getB().y < to.getD().y && from.getB().y > to.getB().y)) {
                    System.out.println("TOP");
                    return TOP;
                }
                else {
                    System.out.println("BOTTOM");
                    return BOTTOM;
                }
            }
        }
    }


    public int reversePositionForDraw(int position) {
        if(position == LEFT) {
            return RIGHT;
        }
        else if(position == RIGHT) {
            return LEFT;
        }

        return position;
    }
}
