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
                case SEARCH_EMPLOYEE -> searchEmployee();
                case CREATE_BINARY_TREE -> buildTree();
                case EXIT -> System.out.println("Exiting...");
            }

        } while (option != MenuOption.EXIT);
    }

    // -----------------------------
    // LOAD APPLICANTS (FIRST + LAST NAME,MANAGER TYPE AND DEPARTMENT)
    // -----------------------------
    private static void loadApplicants() {
           try {
        File file = new File("Applicants_Form.txt");
        Scanner reader = new Scanner(file);

        boolean isFirstLine = true;

        while (reader.hasNextLine()) {
            String line = reader.nextLine().trim();
            if (line.isEmpty()) continue;

            // Skip header row
            if (isFirstLine) {
                isFirstLine = false;
                continue;
            }

            String[] parts = line.split(",");
            if (parts.length < 2) continue;

            String fullName = parts[0].trim() + " " + parts[1].trim();
            applicants.add(fullName);
        }

        System.out.println("Applicants loaded successfully.");

    } catch (Exception e) {
        System.out.println("Error reading file.");
    }
}

    // -----------------------------
    // SORT APPLICANTS + DISPLAY CLEAN OUTPUT
    // -----------------------------
    private static void sortApplicants() {
        if (applicants.isEmpty()) {
            System.out.println("No applicants loaded.");
            return;
        }

        applicants = MergeSort.sort(applicants);

        System.out.println("\nFirst 20 sorted applicants (Name | Manager | Department):");

        for (int i = 0; i < 20 && i < applicants.size(); i++) {
            Employee e = createRandomEmployee(applicants.get(i));
            System.out.println(
                e.getName() + " | " +
                e.getManagerType() + " | " +
                e.getDepartment()
            );
        }
    }

    // -----------------------------
    // SEARCH APPLICANT
    // -----------------------------
   private static void searchApplicant() {
       
   if (input.hasNextLine()) {
            String leftover = input.nextLine();
            if (!leftover.trim().isEmpty()) {
                searchName(leftover.trim());
                return;
            }
        }    

    System.out.print("Enter name to search: ");
    String name = input.nextLine().trim();
    
         searchName(name);
    }
   
private static void searchName(String name) {

        // 1. Search applicants list
        applicants = MergeSort.sort(applicants);
        int index = BinarySearch.search(applicants, name, 0, applicants.size() - 1);

        if (index != -1) {
            System.out.println("\nFound in applicants:");
            System.out.println(applicants.get(index));
            return;
        }

 
    // 2. Search employees (manually added or tree-generated)
    for (Employee e : employees) {
        if (e.getName().equalsIgnoreCase(name)) {
            System.out.println("\nFound in employees:");
            System.out.println(
                e.getName() + " | " +
                e.getManagerType() + " | " +
                e.getDepartment()
            );
            return;
        }
    }

    System.out.println("\nName not found.");
}

    // -----------------------------
    // ADD EMPLOYEE MANUALLY
    // -----------------------------
    private static void addEmployee() {
        String name;
        
        while(true){
        System.out.print("Enter employee name: ");
        String nameInput = input.nextLine();
        if (nameInput.isEmpty()) {
            System.out.println("Name cannot be empty.");
            continue;
        }
        if(!nameInput.matches("[A-Za-z ]+")) {
            System.out.println("Invalid name. Only letters and spaces allowed.");
            continue;
        }
        name = nameInput;
        break;
        }
        System.out.println("Select Manager Type:");
        Manager[] managers = Manager.values();
        for (int i = 0; i < managers.length; i++) {
            System.out.println((i + 1) + ". " + managers[i]);
        }
        int mChoice = getValidatedChoice(managers.length);
        Manager manager = managers[mChoice - 1];
        
        Department dept;
        if(manager == Manager.CEO){
            dept = Department.NONE;
            System.out.println("\nCEO does not require a department");
        }else{
        System.out.println("Select Department:");
        Department[] deps = Department.values();
        for (int i = 0; i < deps.length; i++) {
            System.out.println((i + 1) + ". " + deps[i]);
        }
        int dChoice = getValidatedChoice(deps.length);
         dept = deps[dChoice - 1];
        }
        
        Employee emp = new Employee(name, manager, dept);
        employees.add(emp);

        System.out.println("\nEmployee added:");
        System.out.println(emp);
    }
  
private static void searchEmployee() {

    if (employees.isEmpty()) {
        System.out.println("No employees added yet.");
        return;
    }

    System.out.print("Enter employee name: ");
    String name = input.nextLine().trim();

    for (Employee e : employees) {
        if (e.getName().equalsIgnoreCase(name)) {
            System.out.println("\nEmployee found:");
            System.out.println(
                e.getName() + " | " +
                e.getManagerType() + " | " +
                e.getDepartment()
            );
            return;
        }
    }

    System.out.println("\nEmployee not found.");
}
    // -----------------------------
    // BUILD EMPLOYEE TREE (FIRST 20 APPLICANTS)
    // -----------------------------
    private static void buildTree() {
        if (employees.isEmpty()){
            System.out.println("No employees available to build a tree.");
            return;
        }

        EmployeeTree tree = new EmployeeTree();
//Adding Employees into the Organisation tree
        for (Employee e : employees){
            tree.insert(e);
        }

        System.out.println("\n===== EMPLOYEE TREE (LEVEL ORDER) =====");
        tree.displayLevelOrder();

        System.out.println("\nTree Height: " + tree.getHeight());
        System.out.println("Total Nodes: " + tree.getNodeCount());
    }

    // -----------------------------
    // VIEW ALL EMPLOYEES
    // -----------------------------
    private static void viewAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees added yet.");
            return;
        }

        System.out.println("\n===== ALL EMPLOYEES =====");
        for (Employee e : employees) {
            System.out.println(
                e.getName() + " | " +
                e.getManagerType() + " | " +
                e.getDepartment()
            );
        }
    }

    // -----------------------------
    // HELPER: CREATE RANDOM EMPLOYEE
    // -----------------------------
    private static Employee createRandomEmployee(String name) {
        Manager[] managers = Manager.values();
        Department[] departments = Department.values();

        Manager randomManager = managers[(int)(Math.random() * managers.length)];
        Department randomDept = departments[(int)(Math.random() * departments.length)];

        return new Employee(name, randomManager, randomDept);
    }

    // -----------------------------
    // INPUT VALIDATION
    // -----------------------------
    private static int getValidatedChoice(int max) {
        int choice;
        while (true) {
            choice = getIntInput();
            if (choice >= 1 && choice <= max) return choice;
            System.out.println("Invalid choice. Try again.");
        }
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