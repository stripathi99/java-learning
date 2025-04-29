package LinkedList;

import java.util.Stack;
import java.util.stream.IntStream;

import static LinkedList.SingleLinkedListHelper.display;

public class ReverseLinkedList {
    public static void main(String[] args) {
        var sList = new SingleLinkedList();
        IntStream.range(0, 10).forEach(sList::addNode);
        Node curr = sList.getHead();
        display(curr);
        //display(reverseLL(curr));
        display(reverseUsingStack(curr));
    }

    private static Node reverseLL(final Node node) {
        Node curr = node, prev = null, next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    private static Node reverseUsingStack(final Node node) {
        Node curr = node, temp;
        var stack = new Stack<Node>();

        while (curr != null) {
            stack.push(curr);
            curr = curr.next;
        }

        curr = stack.pop();
        temp = curr;

        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
        }

        temp.next = null;
        return curr;
    }
}