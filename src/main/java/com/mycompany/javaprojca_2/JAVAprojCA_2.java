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

    static String[] managerTypes = {
        "Senior Manager",
        "Assistant Manager",
        "Team Lead"
    };

    static String[] departments = {
        "Customer Service",
        "Foreign Exchange",
        "Loans",
        "Credit Department",
        "HR"
    };

    public static void main(String[] args) {

        loadApplicants();

        MenuOption option = null;

        do {
            System.out.println("\n===== BANK MANAGEMENT MENU =====");

            // ENUM‑based menu (assignment requirement)
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
                applicants.add(reader.nextLine().trim());
            }

            System.out.println("File read successfully.");

        } catch (Exception e) {
            System.out.println("Error reading file.");
        }
    }

    private static void sortApplicants() {
        applicants = MergeSort.sort(applicants);

        System.out.println("\nFirst 20 sorted names:");
        applicants.stream().limit(20).forEach(System.out::println);
    }

    private static void searchApplicant() {
        if (applicants.isEmpty()) {
            System.out.println("No applicants loaded.");
            return;
        }

        // Ensure list is sorted before searching
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
        for (int i = 0; i < managerTypes.length; i++)
            System.out.println((i + 1) + ". " + managerTypes[i]);
        int mChoice = getIntInput();
        Manager manager = new Manager(managerTypes[mChoice - 1]);

        System.out.println("Select Department:");
        for (int i = 0; i < departments.length; i++)
            System.out.println((i + 1) + ". " + departments[i]);
        int dChoice = getIntInput();
        Department dept = new Department(departments[dChoice - 1]);

        Employee emp = new Employee(name, manager, dept);
        employees.add(emp);

        System.out.println("\n" + name + " added successfully!");
    }

    private static void buildTree() {
        EmployeeTree tree = new EmployeeTree();

        for (Employee e : employees) {
            tree.insert(e);
        }

        System.out.println("\n===== EMPLOYEE HIERARCHY (LEVEL ORDER) =====");
        tree.displayLevelOrder();
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