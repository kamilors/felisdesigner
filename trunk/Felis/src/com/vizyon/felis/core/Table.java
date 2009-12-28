/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.util.Arrays;
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
    private boolean closed;
    private boolean autoSize;

    public Table() {
        fields = new ArrayList<Field>(0);
        box = new Box();
        box.setRound(10);
        box.setBgColor(Color.orange);
        name = "table";
        autoSize = true;
        selected = false;
        closed = false;
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

    public boolean isClosed() {
        return closed;
    }

    public void setClosed(boolean closed) {
        this.closed = closed;
    }


    // </editor-fold>

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        Font font = new Font("Tahoma", Font.BOLD, 12);

        if(!closed) {
            box.setHeight(getFieldsHeight() + 30);
        }
        else {
            box.setHeight(30);
        }

        int maxWidt = getMaxWidth(g,font);

        if(maxWidt > 150) {
            box.setWidth(maxWidt);
        }

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

        g2.setColor(Color.white);
        g2.setFont(font);
        g2.drawString(name, box.getLeft()+5, box.getTop()+15);


        if(!closed) {
            int top = 22;
            for(Field field : fields) {
                field.getBox().setTop(box.getTop() + top);
                field.getBox().setLeft(box.getLeft() + 5);
                field.getBox().setWidth(box.getWidth() - 10);
                field.draw(g);
                top += 21;
            }
        }
    }

    private int getFieldsHeight() {
        int height = 0;
        for(Field field : fields) {
            height += field.getBox().getHeight() + 1;
        }
        return height;
    }

    private int getMaxWidth(Graphics g, Font font) {
        Graphics2D g2 = (Graphics2D) g;
        int[] widths = new int[this.fields.size() + 2];
        FontMetrics metrics = g2.getFontMetrics(font);

        widths[0] = metrics.stringWidth(getName()) + 20;

        int index = 1;
        for(Field field: fields) {
            String label = field.getName() + " - " + field.getType().toFormatString();
            widths[index] = metrics.stringWidth(label);
            index++;
        }

        Arrays.sort(widths);
        

        return widths[widths.length-1];
    }

    
}
