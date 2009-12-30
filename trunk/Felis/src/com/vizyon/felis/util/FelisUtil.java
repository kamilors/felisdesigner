/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis.util;

import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

/**
 *
 * @author kamil
 */
public class FelisUtil {

    /**
     *
     * @param arry int[]
     * @param asc boolean
     * @return int[]
     */
    public static int[] sorting(int[] arry, boolean desc) {

        int temp,sort;
        for(int i = 0; i < arry.length-1; i++) {
            sort = i;
            for(int j = i; j < arry.length; j++) {

                if(desc) {
                    if(arry[j] > arry[sort]) {
                        sort = j;
                    }
                }
                else {
                    if(arry[j] < arry[sort]) {
                        sort = j;
                    }
                }

                temp = arry[i];
                arry[i] = arry[sort];
                arry[sort] = temp;
            }
        }

        return arry;
    }

    public static Cursor getHandCursor() {
        FelisUtil util = new FelisUtil();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        ImageIcon icon = new ImageIcon(util.getClass().getResource("/com/vizyon/felis/icon/hand_32.png"));
        toolkit.getBestCursorSize(16, 16);

        Cursor cursor = toolkit.createCustomCursor(icon.getImage(), new Point(0, 0), "hand");
        return cursor;
    }
}
