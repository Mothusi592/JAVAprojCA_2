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
 * The Department enum defines all Bank departments departments that exist within the system
 * This gives valid departments and ensures consistency when assigning employees to their 
 * departments within the Bank
 */
public class Department {

    private String name;
    private DepartmentType departmentType;

    public Department(String name, DepartmentType departmentType) {
        this.name = name;
        this.departmentType = departmentType;
    }

    public String getName() {
        return name;
    }

    public DepartmentType getDepartmentType() {
        return departmentType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartmentType(DepartmentType departmentType) {
        this.departmentType = departmentType;
    }

    @Override
    public String toString() {
        return name + " (" + departmentType + ")";
    }
}