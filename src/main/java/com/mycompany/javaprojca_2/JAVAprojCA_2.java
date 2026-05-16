/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.javaprojca_2;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author gondo
 */
public class JAVAprojCA_2 { 
    
    static List<String> applicants = new ArrayList<>();
    static List<Employee> employees = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        loadApplicants();

        MenuOption option = null;

        do {
            System.out.println("\n===== BANK MANAGEMENT MENU =====");

            for (MenuOption m : MenuOption.values()) {
                System.out.println((m.ordinal() + 1) + ". " + m.getLabel());
            }

            System.out.print("Enter choice: ");
            int choice = getIntInput();

            if (choice < 1 || choice > MenuOption.values().length) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            option = MenuOption.values()[choice - 1];

            switch (option) {
                case SORT -> sortApplicants();
                case SEARCH -> searchApplicant();
                case ADD_RECORD -> addEmployee();
                case CREATE_BINARY_TREE -> buildTree();
                case EXIT -> System.out.println("Exiting...");
            }

        } while (option != MenuOption.EXIT);
    }

    private static void loadApplicants() {
        try {
            File file = new File("Applicants_Form.txt");
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (!line.isEmpty()) {
                    applicants.add(line);
                }
            }

            System.out.println("File read successfully.");

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }

    private static void sortApplicants() {
        if (applicants.isEmpty()) {
            System.out.println("No applicants loaded.");
            return;
        }

        applicants = MergeSort.sort(applicants); // see signature note below

        System.out.println("\nFirst 20 sorted names:");
        applicants.stream().limit(20).forEach(System.out::println);
    }

    private static void searchApplicant() {
        if (applicants.isEmpty()) {
            System.out.println("No applicants loaded.");
            return;
        }

        applicants = MergeSort.sort(applicants);

        System.out.print("Enter name to search: ");
        String name = input.nextLine();

        int index = BinarySearch.search(applicants, name, 0, applicants.size() - 1);

        if (index == -1) {
            System.out.println("Name not found.");
        } else {
            System.out.println("Found: " + applicants.get(index));
        }
    }

    private static void addEmployee() {
        System.out.print("Enter employee name: ");
        String name = input.nextLine();

        System.out.println("Select Manager Type:");
        Manager[] managers = Manager.values();
        for (int i = 0; i < managers.length; i++) {
            System.out.println((i + 1) + ". " + managers[i]);
        }

        int mChoice;
        while (true) {
            mChoice = getIntInput();
            if (mChoice >= 1 && mChoice <= managers.length) break;
            System.out.println("Invalid choice. Try again.");
        }
        Manager manager = managers[mChoice - 1];

        System.out.println("Select Department:");
        Department[] deps = Department.values();
        for (int i = 0; i < deps.length; i++) {
            System.out.println((i + 1) + ". " + deps[i]);
        }

        int dChoice;
        while (true) {
            dChoice = getIntInput();
            if (dChoice >= 1 && dChoice <= deps.length) break;
            System.out.println("Invalid choice. Try again.");
        }
        Department dept = deps[dChoice - 1];

        Employee emp = new Employee(name, manager, dept);
        employees.add(emp);

        System.out.println("\n" + name + " added successfully!");
        System.out.println(emp);
    }

    private static void buildTree() {
        if (employees.size() < 20) {
            System.out.println("You need at least 20 employees to build the tree.");
            return;
        }

        EmployeeTree tree = new EmployeeTree();

        for (Employee e : employees) {
            tree.insert(e);
        }

        System.out.println("\n===== EMPLOYEE HIERARCHY (LEVEL ORDER) =====");
        tree.displayLevelOrder(); // ensure method name matches in EmployeeTree
    }

    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }
}