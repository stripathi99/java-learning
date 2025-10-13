package LinkedList;

public class SingleLinkedListHelper {
    public static void display(final Node node) {
        if (node == null) {
            System.out.println("List is empty.");
            return;
        }

        final var sb = new StringBuilder();
        var curr = node;

        while (curr.next != null) {
            sb.append(curr.value).append(" -> ");
            curr = curr.next;
        }

        sb.append(curr.value);
        System.out.println(sb);
    }
}