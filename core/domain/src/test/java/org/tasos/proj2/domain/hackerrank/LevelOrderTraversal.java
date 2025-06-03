package org.tasos.proj2.domain.hackerrank;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class levelOrderTraversal{

    public static String levelOrderTraversal(TreeNode root) {
        if (root == null) {
            return "None";
        }

        List<String> result = new ArrayList<>();
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<String> levelNodes = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode temp = queue.poll();
                levelNodes.add(String.valueOf(temp.data));

                if (temp.left != null) {
                    queue.add(temp.left);
                }
                if (temp.right != null) {
                    queue.add(temp.right);
                }
            }

            result.add(String.join(", ", levelNodes));
        }

        return String.join("; ", result);
    }

    public static void main(String[] argv) {

//        List<Integer> input = new ArrayList<Integer>();
//        input.add(100);input.add(50);input.add(200);input.add(25);input.add(75);input.add(350);
//        TreeNode root = BinaryTree.create_BST(input);


        //          100
        //          / \
        //          50  200
        //          / \   \
        //         25 75   350

        TreeNode root = new TreeNode(100);
        root.left = new TreeNode(50);
        root.right = new TreeNode(200);
        root.left.left = new TreeNode(25);
        root.left.right = new TreeNode(75);
        root.right.right = new TreeNode(350);


        System.out.print("Inorder = ");
//        BinaryTree.display_inorder(root);
        System.out.println("\nLevel order = "+ levelOrderTraversal(root));

    }
}