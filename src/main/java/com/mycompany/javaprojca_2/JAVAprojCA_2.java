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

    static boolean applicantsSorted = false;

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
                System.out.println("Invalid choice. Try again, Only accepts digits 1-6.");
                continue;
            }

            option = MenuOption.values()[choice - 1];

            switch (option) {
                case SORT -> sortApplicants();
                case SEARCH -> searchApplicant();
                case ADD_RECORD -> addEmployee();
                case SEARCH_EMPLOYEE -> handleEmployeeMenu();
                case CREATE_BINARY_TREE -> buildTree();
                case EXIT -> System.out.println("Exiting...");
            }

        } while (option != MenuOption.EXIT);
    }

    // ============================
    // LOAD APPLICANTS
    // ============================
    private static void loadApplicants() {
        while(true){
        try {
            System.out.print("Enter the filename to load: ");
            String filename = input.nextLine().trim();

            File file = new File(filename);

            if (!file.exists()) {
                System.out.println("File not found. Please try again.");
                continue;
            }

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
                if (parts.length < 8) continue;

                String firstName = parts[0].trim();
                String lastName = parts[1].trim();
                String dept = parts[5].trim();
                String jobTitle = parts[7].trim();

                String fullName = firstName + " " + lastName;

                ManagerType mType = mapManagerType(jobTitle);
                DepartmentType dType = mapDepartmentType(dept);

                applicants.add(new Applicants(fullName, mType.name(), dType.name()));
            }

            System.out.println("Applicants Form loaded successfully.");
            break;
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
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

        applicants = MergeSort.sortApplicants(applicants);
        applicantsSorted = true;

        System.out.println("\nFirst 20 sorted applicants (Name | ManagerType | DepartmentType):");
        for (int i = 0; i < 20 && i < applicants.size(); i++) {
            Applicants a = applicants.get(i);
            System.out.println((i + 1) + ". " + a.getFullName() + " | " +
                    a.getManagerType() + " | " + a.getDepartmentType());
        }

        // NEW REQUIRED STEP
        convertApplicantsToEmployees();
    }

    // ============================
    // NEW METHOD: CONVERT FIRST 20 APPLICANTS TO EMPLOYEES
    // ============================
    private static void convertApplicantsToEmployees() {
        employees.clear();

        for (int i = 0; i < 20 && i < applicants.size(); i++) {
            Applicants a = applicants.get(i);

            ManagerType mType = ManagerType.valueOf(a.getManagerType());
            DepartmentType dType = DepartmentType.valueOf(a.getDepartmentType());

            Manager manager = new Manager(a.getFullName(), mType);
            Department department = new Department(dType.name(), dType);

            Employee emp = new Employee(a.getFullName(), manager, department);
            employees.add(emp);
        }

      
    }

    // ============================
    // SEARCH APPLICANT
    // ============================
    private static void searchApplicant() {
        if (applicants.isEmpty()) {
            System.out.println("No applicants loaded.");
            return;
        }

        if (!applicantsSorted) {
            applicants = MergeSort.sortApplicants(applicants);
            applicantsSorted = true;
        }

        System.out.print("Enter name to search: ");
        String name = input.nextLine().trim();

        int index = binarySearchApplicants(applicants, name, 0, applicants.size() - 1);
        if (index == -1) {
            System.out.println("Applicant not found.");
        } else {
            Applicants a = applicants.get(index);
            System.out.println("\nFound:");
            System.out.println(a.getFullName() + " | " + a.getManagerType() + " | " + a.getDepartmentType());
        }
    }

    private static int binarySearchApplicants(List<Applicants> list, String target, int low, int high) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        int cmp = list.get(mid).getFullName().compareToIgnoreCase(target);
        if (cmp == 0) return mid;
        if (cmp > 0) return binarySearchApplicants(list, target, low, mid - 1);
        return binarySearchApplicants(list, target, mid + 1, high);
    }

    // ============================
    // EMPLOYEE MENU
    // ============================
    private static void handleEmployeeMenu() {
        while (true) {
            System.out.println("\n=== EMPLOYEE MENU ===");
            System.out.println("1. Add Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Back");

            System.out.print("Enter choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1 -> addEmployee();
                case 2 -> searchEmployee();
                case 3 -> { return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ============================
    // ADD EMPLOYEE
    // ============================
    private static void addEmployee() {

        System.out.println("\n=== ADD EMPLOYEE ===");

        System.out.print("Enter full name: ");
        String fullName = input.nextLine().trim();
        while (fullName.isEmpty()) {
            System.out.print("Name cannot be empty. Enter full name: ");
            fullName = input.nextLine().trim();
        }

        System.out.println("\nSelect Manager Type:");
        int index = 1;
        for (ManagerType mt : ManagerType.values()) {
            System.out.println(index + ". " + mt);
            index++;
        }
        System.out.print("Enter choice: ");
        int mChoice = getIntInput();
        if (mChoice < 1 || mChoice > ManagerType.values().length) {
            System.out.println("Invalid Manager Type choice.");
            return;
        }
        ManagerType mType = ManagerType.values()[mChoice - 1];

        System.out.println("\nSelect Department:");
        index = 1;
        for (DepartmentType dt : DepartmentType.values()) {
            System.out.println(index + ". " + dt);
            index++;
        }
        System.out.print("Enter choice: ");
        int dChoice = getIntInput();
        if (dChoice < 1 || dChoice > DepartmentType.values().length) {
            System.out.println("Invalid Department choice.");
            return;
        }
        DepartmentType dType = DepartmentType.values()[dChoice - 1];

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
employees.sort((a, b) -> a.getManager().getType().getRank() - b.getManager().getType().getRank());
        EmployeeTree tree = new EmployeeTree();

        for (Employee e : employees) {
            tree.insert(e);
        }

        System.out.println("\n===== EMPLOYEE TREE =====");
        tree.displayHierarchy();
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
        if (dept.contains("ceo"))return DepartmentType.CEO;
        if (dept.contains("it")) return DepartmentType.IT;
        if (dept.contains("customer")) return DepartmentType.CUSTOMER_SERVICE;
        if (dept.contains("finance")) return DepartmentType.FINANCE;
        if (dept.contains("account")) return DepartmentType.ACCOUNTS;
        if (dept.contains("hr")) return DepartmentType.HR;

        return DepartmentType.CUSTOMER_SERVICE;
    }
}