package Trees;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

public class TreePanel extends JPanel {
    private TreeNode root;
    private int nodeRadius = 20;
    private int vGap = 50;

    public TreePanel(TreeNode root) {
        this.root = root;
        setPreferredSize(new Dimension(600, 400));
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTree(g, root, getWidth() / 2, 30, getWidth() / 4);
    }

    private void drawTree(Graphics g, TreeNode node, int x, int y, int hGap) {
        if (node == null) return;
        g.setColor(Color.BLACK);
        g.fillOval(x - nodeRadius, y - nodeRadius, 2 * nodeRadius, 2 * nodeRadius);
        g.setColor(Color.WHITE);
        g.drawString(Integer.toString(node.value), x - 6, y + 4);

        if (node.left != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x - hGap, y + vGap);
            drawTree(g, node.left, x - hGap, y + vGap, hGap / 2);
        }

        if (node.right != null) {
            g.setColor(Color.BLACK);
            g.drawLine(x, y, x + hGap, y + vGap);
            drawTree(g, node.right, x + hGap, y + vGap, hGap / 2);
        }
    }
}
