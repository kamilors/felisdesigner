/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis.core;

import com.sun.org.apache.bcel.internal.generic.F2D;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kamil ÖRS
 */
public class Table {

    private String name;    // Tablo adı ve Başlığı
    private Box box;    // Tablo Çizim Kutusu
    private List<Field> fields; // Tablo Alanları
    public Point dragDrop = new Point();
    private boolean selected;

    public Table() {
        fields = new ArrayList<Field>(0);
        box = new Box();
        box.setRound(10);
        box.setBgColor(Color.orange);
        name = "table";
        selected = false;
    }

    public Table(String name) {
        this();
        this.name = name;
    }

    // <editor-fold defaultstate="collapsed" desc="getter and setter">
    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    // </editor-fold>

    public void draw(Graphics g) {
        Graphics g2 = (Graphics2D) g;
        box.setHeight(getFieldsHeight() + 30);
        box.draw(g);

        if(isSelected()) {
            Box select = new Box();
            select.setBgColor(new Color(54, 147, 252));
            select.setTop(box.getTop());
            select.setLeft(box.getLeft());
            select.setWidth(box.getWidth());
            select.setHeight(box.getHeight());
            select.setRound(box.getRound());
            select.setFill(false);
            
            select.draw(g);
        }

        g.setColor(Color.white);
        g2.setFont(new Font("Tahoma", Font.BOLD, 12));
        g2.drawString(name, box.getLeft()+5, box.getTop()+15);

        int top = 22;

        for(Field field : fields) {
            field.getBox().setTop(box.getTop() + top);
            field.getBox().setLeft(box.getLeft() + 5);
            field.getBox().setWidth(box.getWidth() - 10);
            field.draw(g);
            top += 21;
        }
    }

    private int getFieldsHeight() {
        int height = 0;
        for(Field field : fields) {
            height += field.getBox().getHeight();
        }
        return height;
    }
}
