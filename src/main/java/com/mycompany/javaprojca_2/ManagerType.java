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
 * The Manager enum I am giving managerial & administrative roles 
 * in the bank Organization Employees are categorise according to 
 * their job position, This ensures that valid employee roles are entered
 */
public enum ManagerType {
    CEO,
    MANAGING_DIRECTOR,
    DIRECTOR,
    ADMINISTRATOR,
    HEAD_MANAGER,
    TEAM_LEAD,
    ASSISTANT_MANAGER,
    IT_TECHNICIAN,
    HR_ASSISTANT,
    SENIOR_MANAGER;

    public int getRank() {
        return switch (this) {
            case CEO -> 1;
            case MANAGING_DIRECTOR -> 2;
            case DIRECTOR -> 3;
            case SENIOR_MANAGER -> 4;
            case HEAD_MANAGER -> 5;
            case TEAM_LEAD -> 6;
            case ASSISTANT_MANAGER -> 7;
            case ADMINISTRATOR -> 8;
            case HR_ASSISTANT -> 9;
            case IT_TECHNICIAN -> 10;
        };
    }
}