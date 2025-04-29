package Trees;

import java.util.LinkedList;
import java.util.Queue;

public class TreeTravelsal {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root = BuildTree.build(arr, 0);
        System.out.println("in order tree traversal:");
        inOrder(root);

        System.out.println("pre order tree traversal:");
        preOrder(root);

        System.out.println("post order tree traversal:");
        postOrder(root);

        System.out.println("bfs tree traversal:");
        bfs(root);
    }

    private static void bfs(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()) {
            TreeNode nTreeNode = queue.poll();
            System.out.println(nTreeNode.value);
            if (nTreeNode.left != null) queue.offer(nTreeNode.left);
            if (nTreeNode.right != null) queue.offer(nTreeNode.right);
        }
    }

    private static void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    private static void preOrder(TreeNode node) {
        if (node == null) return;
        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    private static void postOrder(TreeNode node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }
}
