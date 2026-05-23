/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojca_2;

/**
 *
 * @author gondo
 */

    public class Applicants {

    private String fullName;
    private String position;
    private String department;

    public Applicants(String fullName, String position, String department) {
        this.fullName = fullName;
        this.position = position;
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public String getManagerType() {
        return position;
    }

    public String getDepartmentType() {
        return department;
    }

    @Override
    public String toString() {
        return fullName + " | " + position + " | " + department;
    }
}
    

