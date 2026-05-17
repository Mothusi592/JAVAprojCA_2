/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojca_2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gondo
 */
/**This is a recursive Merge Sort for the list of Employees
 * Merge sort  is the best in this case because it gives O(n log n), this is best compared to other
 sorting algorithms
 * in cases where Employee name are relative the same, the are kept in their original relative order, ensuring STABILITY
 *Works on divide and conquer
 */
public class MergeSort {
    // Public method to sort a list of names
    public static List<String> sort(List<String> list) {
        if (list == null || list.size() <= 1) {
            return list;
        }

        int mid = list.size() / 2;

        // Recursively split the list
        List<String> left = sort(new ArrayList<>(list.subList(0, mid)));
        List<String> right = sort(new ArrayList<>(list.subList(mid, list.size())));

        // Merge the sorted halves
        return merge(left, right);
    }

    // This merges two sorted lists
    private static List<String> merge(List<String> left, List<String> right) {
        List<String> result = new ArrayList<>();

        int i = 0, j = 0;

        // Compare elements from both lists
        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareToIgnoreCase(right.get(j)) <= 0) {
                result.add(left.get(i));
                i++;
            } else {
                result.add(right.get(j));
                j++;
            }
        }

        // Adds remaining name elements
        while (i < left.size()) {
            result.add(left.get(i));
            i++;
        }

        while (j < right.size()) {
            result.add(right.get(j));
            j++;
        }

        return result;
    }
}
