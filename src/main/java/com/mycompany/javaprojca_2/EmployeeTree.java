/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.javaprojca_2;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author gondo
 */
/**
 * EmployeeTree handles the binary tree presentation and hierarchy
 * of Employees, employees are added using the level order
 */

public class EmployeeTree {

    private TreeNode root;

    // ============================
    // INSERT USING LEVEL ORDER
    // ============================
    public void insert(Employee employee) {
        TreeNode newNode = new TreeNode(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();

            if (current.left == null) {
                current.left = newNode;
                return;
            } else queue.add(current.left);

            if (current.right == null) {
                current.right = newNode;
                return;
            } else queue.add(current.right);
        }
    }

    // ============================
    // LEVEL ORDER DISPLAY (FLAT LIST)
    // ============================
    public void displayLevelOrder() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            System.out.println(current.data);

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    // ============================
    // HIERARCHY DISPLAY (LEVEL ORDER WITH LEVEL GROUPING)
    // ============================
    public void displayHierarchy() {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }

        System.out.println("\n===== EMPLOYEE HIERARCHY (LEVEL ORDER) =====");

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        int level = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            System.out.println("\nLEVEL " + level + ":");

            for (int i = 0; i < levelSize; i++) {
                TreeNode current = queue.poll();
                System.out.println("  - " + current.data);

                if (current.left != null) queue.add(current.left);
                if (current.right != null) queue.add(current.right);
            }

            level++;
        }
    }

    // ============================
    // HEIGHT OF TREE
    // ============================
    public int getHeight() {
        return height(root);
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    // ============================
    // COUNT NODES
    // ============================
    public int getNodeCount() {
        return count(root);
    }

    private int count(TreeNode node) {
        if (node == null) return 0;
        return 1 + count(node.left) + count(node.right);
    }
}