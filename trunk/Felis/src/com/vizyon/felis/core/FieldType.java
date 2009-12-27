/*
 * Tablo Alan Tipi
 * Örn: vharchar(23) => name(value)
 */

package com.vizyon.felis.core;

/**
 *
 * @author Kamil ÖRS
 */
public class FieldType {

    private String name;
    private int value;
    private boolean notNull;
    private boolean autoIncrement;

    public FieldType() {
        notNull = true;
        autoIncrement = false;
        value = 0;
        name = "varchar";
    }
    
    public FieldType(String name, int value) {
        this();
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return name + "(" + value + ")";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isAutoIncrement() {
        return autoIncrement;
    }

    public void setAutoIncrement(boolean autoIncrement) {
        this.autoIncrement = autoIncrement;
    }

    public boolean isNotNull() {
        return notNull;
    }

    public void setNotNull(boolean notNull) {
        this.notNull = notNull;
    }

    

}
