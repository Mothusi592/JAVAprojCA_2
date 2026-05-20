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

    static List<Applicants> applicants = new ArrayList<>();
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
                case SEARCH_EMPLOYEE -> searchEmployee();
                case CREATE_BINARY_TREE -> buildTree();
                case EXIT -> System.out.println("Exiting...");
            }

        } while (option != MenuOption.EXIT);
    }

    // ============================
    // LOAD APPLICANTS
    // ============================
    private static void loadApplicants() {
        try {
            File file = new File("Applicants_Form.txt");
            Scanner reader = new Scanner(file);

            boolean isFirstLine = true;

            while (reader.hasNextLine()) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;

                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                String[] parts = line.split(",");
                if (parts.length < 9) continue;

                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String jobTitle = parts[7].trim();
                String dept = parts[5].trim();

                String fullName = firstName + " " + lastName;

                // MAP JOB TITLE → ManagerType
                ManagerType mType = mapManagerType(jobTitle);

                // MAP DEPARTMENT → DepartmentType
                DepartmentType dType = mapDepartmentType(dept);

                applicants.add(new Applicants(fullName, mType.name(), dType.name()));
            }

            System.out.println("Applicants loaded successfully.");

        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // ============================
    // SORT APPLICANTS
    // ============================
    private static void sortApplicants() {
        if (applicants.isEmpty()) {
            System.out.println("No applicants loaded.");
            return;
        }

        List<String> formatted = new ArrayList<>();
        for (Applicants a : applicants) {
            formatted.add(a.getFullName() + " | " + a.getPosition() + " | " + a.getDepartment());
        }

        formatted = MergeSort.sort(formatted);

        System.out.println("\nFirst 20 sorted applicants (Name | Manager | Department):");
        for (int i = 0; i < 20 && i < formatted.size(); i++) {
            System.out.println((i + 1) + ". " + formatted.get(i));
        }
    }

    // ============================
    // SEARCH APPLICANT
    // ============================
    private static void searchApplicant() {
        System.out.print("Enter name to search: ");
        String name = input.nextLine().trim();

        for (Applicants a : applicants) {
            if (a.getFullName().equalsIgnoreCase(name)) {
                System.out.println("\nFound:");
                System.out.println(a.getFullName() + " | " + a.getPosition() + " | " + a.getDepartment());
                return;
            }
        }

        System.out.println("Applicant not found.");
    }

    // ============================
    // ADD EMPLOYEE
    // ============================
   private static void addEmployee() {

    System.out.println("\n=== ADD EMPLOYEE ===");

    // Ask for name
    System.out.print("Enter full name: ");
    String fullName = input.nextLine().trim();
    
     // Check if applicant exists
    Applicants found = null;
    for (Applicants a : applicants) {
        if (a.getFullName().equalsIgnoreCase(fullName)) {
            found = a;
            break;
        }
    }
if (found == null) {
        System.out.println(" Applicant not found in the list. You can only add employees from the sorted applicants.");
        return;
    }
    // Ask for ManagerType
    System.out.println("\nSelect Manager Type:");
    int index = 1;
    for (ManagerType mt : ManagerType.values()) {
        System.out.println(index + ". " + mt);
        index++;
    }
    System.out.print("Enter choice: ");
    int mChoice = getIntInput();
    ManagerType mType = ManagerType.values()[mChoice - 1];

    // Ask for DepartmentType
    System.out.println("\nSelect Department:");
    index = 1;
    for (DepartmentType dt : DepartmentType.values()) {
        System.out.println(index + ". " + dt);
        index++;
    }
    System.out.print("Enter choice: ");
    int dChoice = getIntInput();
    DepartmentType dType = DepartmentType.values()[dChoice - 1];

    // Create objects
    Manager manager = new Manager(fullName, mType);
    Department department = new Department(dType.name(), dType);

    Employee emp = new Employee(fullName, manager, department);
    employees.add(emp);

    System.out.println("\nEmployee added:");
    System.out.println(emp);
}

    // ============================
    // SEARCH EMPLOYEE
    // ============================
    private static void searchEmployee() {
        System.out.print("Enter employee name: ");
        String name = input.nextLine().trim();

        for (Employee e : employees) {
            if (e.getName().equalsIgnoreCase(name)) {
                System.out.println("\nEmployee found:");
                System.out.println(e);
                return;
            }
        }

        System.out.println("Employee not found.");
    }

    // ============================
    // BUILD TREE
    // ============================
    private static void buildTree() {
        if (employees.size() < 20) {
            System.out.println("You must have at least 20 employees to build the hierarchy tree.");
            return;
        }

        EmployeeTree tree = new EmployeeTree();

        for (int i = 0; i < 20; i++) {
            tree.insert(employees.get(i));
        }

        System.out.println("\n===== EMPLOYEE TREE =====");
        tree.displayLevelOrder();
        System.out.println("Tree Height: " + tree.getHeight());
        System.out.println("Total Nodes: " + tree.getNodeCount());
    }

    // ============================
    // INPUT VALIDATION
    // ============================
    private static int getIntInput() {
        while (true) {
            try {
                return Integer.parseInt(input.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid number. Try again.");
            }
        }
    }

    // ============================
    // MAPPING METHODS
    // ============================
    private static ManagerType mapManagerType(String job) {
        job = job.toLowerCase();

        if (job.contains("senior manager")) return ManagerType.SENIOR_MANAGER;
        if (job.contains("assistant manager")) return ManagerType.ASSISTANT_MANAGER;
        if (job.contains("manager")) return ManagerType.DIRECTOR;
        if (job.contains("developer") || job.contains("qa") || job.contains("full-stack"))
            return ManagerType.IT_TECHNICIAN;
        if (job.contains("hr")) return ManagerType.HR_ASSISTANT;
        if (job.contains("clerk") || job.contains("bookkeeper"))
            return ManagerType.ADMINISTRATOR;

        return ManagerType.ASSISTANT_MANAGER;
    }

    private static DepartmentType mapDepartmentType(String dept) {
        dept = dept.toLowerCase();

        if (dept.contains("it")) return DepartmentType.IT;
        if (dept.contains("customer")) return DepartmentType.CUSTOMER_SERVICE;
        if (dept.contains("finance")) return DepartmentType.FINANCE;
        if (dept.contains("account")) return DepartmentType.ACCOUNTS;
        if (dept.contains("hr")) return DepartmentType.HR;

        return DepartmentType.NONE;
    }
}
