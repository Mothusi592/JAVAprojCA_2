/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojca_2;

/**
 *
 * @author gondo
 */

/**
 * The MenuOption enum gives all available activities a user can perform in the system
 * in the main menu Each option describes what is performs label used for displaying 
 * user‑friendly text in the console This ensures consistency, readability, 
 * and type‑safe handling of menu selections throughout the program
 */
public enum MenuOption {
    SORT("Sort Applicants"),
    SEARCH("Search Applicants"),
    ADD_RECORD("Add Employee Record"),
    SEARCH_EMPLOYEE("Search Employee"),   
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
    

