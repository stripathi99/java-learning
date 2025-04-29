package LinkedList;

class SingleLinkedList {
    Node head, tail;

    SingleLinkedList() {
        head = tail = null;
    }

    public void addNode(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    public Node getHead() {
        return head;
    }
}

