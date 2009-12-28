/*
 * Tablo Alan Tipi
 * Örn: vharchar(23) => name(value)
 */

package com.vizyon.felis.core;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Kamil ÖRS
 */
public class FieldType {

    public static int TYPE_SIZE = 8;
    public static int INTEGER_TYPE = 0;
    public static int FLOAT_TYPE = 1;
    public static int DOUBLE_TYPE = 2;
    public static int LONG_TYPE = 3;
    public static int BOOLEAN_TYPE = 4;
    public static int VARCHAR_TYPE = 5;
    public static int BLOB_TYPE = 6;
    public static int NUMBER_TYPE = 7;

    private int type;
    private int value;

    private static String[] TYPE_LABELS = {
        "INTEGER",
        "FLOAT",
        "DOUBLE",
        "LONG",
        "BOOLEAN",
        "VARCHAR",
        "BLOB",
        "NUMBER"
    };

    public FieldType() {
        value = 0;
        type = VARCHAR_TYPE;
    }
    
    public FieldType(int type, int value) {
        this();
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return typeName(type);
    }

    public String toFormatString() {
        if(type == VARCHAR_TYPE) {
            return typeName(VARCHAR_TYPE) + "(" + value + ")";
        }
        else {
            return typeName(type);
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static String typeName(int type) {
        return TYPE_LABELS[type];
    }

    public static int typeInt(String name) {
        for(int i = 0; i<TYPE_SIZE; i++) {
            if(TYPE_LABELS[i].equals(name)) {
                return i;
            }
        }

        return 0;
    }

    public static FieldType[] types() {
        FieldType[] types = new FieldType[TYPE_SIZE];
        for(int i = 0; i < TYPE_SIZE; i++) {
            types[i] = new FieldType();
            types[i].setType(i);
            types[i].setValue(0);
        }
        return types;
    }
}
