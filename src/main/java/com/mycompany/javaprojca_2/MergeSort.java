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
 sorting algorithms, and in cases where Employee names are relative the same, they are kept in their original relative order, ensuring STABILITY
 *Works on divide and conquer
 */
public class MergeSort {
    // Public method to sort a list of names
  

    public static List<Applicants> sortApplicants(List<Applicants> list) {
        if (list.size() <= 1) return list;

        int mid = list.size() / 2;
        List<Applicants> left = sortApplicants(new ArrayList<>(list.subList(0, mid)));
        List<Applicants> right = sortApplicants(new ArrayList<>(list.subList(mid, list.size())));

        return merge(left, right);
    }

    private static List<Applicants> merge(List<Applicants> left, List<Applicants> right) {
        List<Applicants> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).getFullName().compareToIgnoreCase(right.get(j).getFullName()) <= 0) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }
}
