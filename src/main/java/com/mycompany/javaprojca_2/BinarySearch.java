/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojca_2;

import java.util.List;

/**
 *
 * @author gondo
 */
public class BinarySearch {
   

    public static int search(List<String> list, String target, int low, int high) {
        if (low > high) return -1;

        int mid = (low + high) / 2;
        int compare = target.compareToIgnoreCase(list.get(mid));

        if (compare == 0) return mid;
        else if (compare < 0) return search(list, target, low, mid - 1);
        else return search(list, target, mid + 1, high);
    }
    
}
