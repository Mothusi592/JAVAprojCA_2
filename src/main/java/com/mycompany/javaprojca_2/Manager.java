/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojca_2;

/**
 *
 * @author gondo
 */

        
   public class Manager {

    private String name;
    private ManagerType managerType;

    public Manager(String name, ManagerType managerType) {
        this.name = name;
        this.managerType = managerType;
    }

    public String getName() {
        return name;
    }

    public ManagerType getType() {
        return managerType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManagerType(ManagerType managerType) {
        this.managerType = managerType;
    }

    @Override
    public String toString() {
        return name + " (" + managerType + ")";
    }
}