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
public class BinaryTree {
    

    public static class Node {
        Employee employee;
        Node left, right;

        public Node(Employee employee) {
            this.employee = employee;
        }
    }

    private Node root;

    public void insertLevelOrder(Employee employee) {
        Node newNode = new Node(employee);

        if (root == null) {
            root = newNode;
            return;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

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

    public void printLevelOrder() {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println(current.employee);

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }

    public int height(Node node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    public int getHeight() {
        return height(root);
    }

    public int countNodes(Node node) {
        if (node == null) return 0;
        return 1 + countNodes(node.left) + countNodes(node.right);
    }

    public int getNodeCount() {
        return countNodes(root);
    }
}

