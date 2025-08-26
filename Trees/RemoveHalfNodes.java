package Trees;

public class RemoveHalfNodes {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        BinaryTreeVisualizer.visualizeBinaryTree(root);
        BinaryTreeVisualizer.visualizeBinaryTree(removeHalfNodes(root));
    }

    private static TreeNode removeHalfNodes(TreeNode node) {
        if(node == null) return null;
        node.left = removeHalfNodes(node.left);
        node.right = removeHalfNodes(node.right);

        // in-case of child node
        if(node.left == null && node.right == null) return node;

        // when missing right child
        if(node.left == null) return node.right;

        // when missing left child
        if(node.right == null) return node.left;
        return node;
    }
}
