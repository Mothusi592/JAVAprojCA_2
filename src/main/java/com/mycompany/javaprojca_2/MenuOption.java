/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojca_2;

/**
 *
 * @author gondo
 */
public enum MenuOption {
    SORT("Sort Applicants"),
    SEARCH("Search Applicants"),
    ADD_RECORD("Add Employee Record"),
    CREATE_BINARY_TREE("Generate Employee Hierarchy"),
    EXIT("Exit Program");

    private final String label;

    MenuOption(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
    

