package Trees;

import javax.swing.JFrame;

public class BinaryTreeVisualizer {
    public static void visualizeBinaryTree(TreeNode root) {
        JFrame frame = new JFrame("Binary Tree Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new TreePanel(root));
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
 }
