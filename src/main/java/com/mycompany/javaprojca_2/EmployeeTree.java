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
public class EmployeeTree {

    static class Node {
        Employee employee;
        Node left, right;

        Node(Employee employee) {
            this.employee = employee;
        }
    }

    private Node root;

    public void insert(Employee employee) {
        Node newNode = new Node(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();

            if (temp.left == null) {
                temp.left = newNode;
                return;
            } else queue.add(temp.left);

            if (temp.right == null) {
                temp.right = newNode;
                return;
            } else queue.add(temp.right);
        }
    }

    public void displayLevelOrder() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            System.out.println(temp.employee);

            if (temp.left != null) queue.add(temp.left);
            if (temp.right != null) queue.add(temp.right);
        }
    }
}
    

