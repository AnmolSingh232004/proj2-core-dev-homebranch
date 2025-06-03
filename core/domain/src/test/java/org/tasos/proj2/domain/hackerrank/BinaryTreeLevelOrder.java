package org.tasos.proj2.domain.hackerrank;

import java.util.LinkedList;
import java.util.Queue;


//           1
//          / \
//          2   3
//          / \   \
//          4   5   6

class TreeNode {
    int data;
    TreeNode left, right;

    public TreeNode(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTreeLevelOrder {

    // Level Order Traversal Function
    public static void levelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode current = queue.poll(); // Remove front node
            System.out.print(current.data + " "); // Process node

            if (current.left != null) queue.add(current.left);  // Add left child
            if (current.right != null) queue.add(current.right); // Add right child
        }
    }

    public static void main(String[] args) {
        // Creating the tree from the example
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        System.out.println("Level Order Traversal:");
        levelOrder(root);
    }
}
