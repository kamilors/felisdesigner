/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vizyon.felis.util;

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
    public static int[] sorting(int[] arry, boolean asc) {

        int temp,sort;
        for(int i = 0; i < arry.length-1; i++) {
            sort = i;
            for(int j = i; j < arry.length; j++) {

                if(asc) {
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

}
