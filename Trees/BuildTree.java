package Trees;

class BuildTree {
    public static TreeNode build(int[] arr, int i) {
        TreeNode node = null;
        if (i < arr.length) {
            node = new TreeNode(arr[i]);
            node.left = build(arr, 2 * i + 1);
            node.right = build(arr, 2 * i + 2);
        }
        return node;
    }
}