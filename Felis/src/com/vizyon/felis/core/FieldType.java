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

    public static int INTEGER_TYPE = 0;
    public static int FLOAT_TYPE = 1;
    public static int DOUBLE_TYPE = 2;
    public static int LONG_TYPE = 3;
    public static int BOOLEAN_TYPE = 4;
    public static int VARCHAR_TYPE = 5;

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
