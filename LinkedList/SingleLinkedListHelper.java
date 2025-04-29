package LinkedList;

public class SingleLinkedListHelper {
    public static void display(Node node) {
        while (node != null) {
            System.out.print(node.value);
            if (node.next != null)
                System.out.print(" -> ");
            else
                System.out.println();
            node = node.next;
        }
    }
}