/*
 * Tablo Alanı
 * Örn:  user_name vharchar(10)
 */

package com.vizyon.felis.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author Kamil ÖRS
 */
public class Field {

    private String name;
    private FieldType type;
    private Box box;

    public Field() {
        box = new Box();
        type = new FieldType();
        box.setHeight(20);
        box.setBgColor(Color.white);
        name = "field_name_long";
    }

    // <editor-fold defaultstate="collapsed" desc="Getter And Setter">
    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FieldType getType() {
        return type;
    }

    public void setType(FieldType type) {
        this.type = type;
    }
    //</editor-fold>

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        box.draw(g);

        g.setColor(Color.black);
        g2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        g2.drawString(name+" - " + type, box.getLeft()+5, box.getTop()+15);
    }

}
