package Queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

class QueueUsingStack {
    public static void main(String[] args) {
        System.out.println("implementation of queue using insertionStack");
        int size = 5;
        QueueImplementation<Integer> queueImplementation = new QueueImplementation<>(size);
        queueImplementation.enqueue(2);

        System.out.println(queueImplementation.dequeue());
        IntStream.range(0, size).forEach(queueImplementation::enqueue);
        
        IntStream.range(0, size).forEach(i -> {
            System.out.print(queueImplementation.dequeue() + ", ");
        });

        System.out.println("\n"+ queueImplementation.dequeue());
    }
}

class QueueImplementation<E> {
    int CAPACITY;
    Deque<E> insertionStack;
    Deque<E> removalStack;

    QueueImplementation(int size) {
        this.CAPACITY = size;
        this.insertionStack = new ArrayDeque<>();
        this.removalStack = new ArrayDeque<>();
    }

    public void enqueue(E e) {
        if (this.insertionStack.size() == this.CAPACITY) {
            throw new RuntimeException("queue is full, remove at least one element before inserting.");
        }
        
        this.insertionStack.push(e);
    }

    public E dequeue() {
        if (this.insertionStack.isEmpty() && this.removalStack.isEmpty()) {
            throw new RuntimeException("queue is empty, insert an element before retrieving.");
        }

        if (this.removalStack.isEmpty()) {
            while (!this.insertionStack.isEmpty()) {
                this.removalStack.push(this.insertionStack.pop());
            }
        }
        
        return removalStack.pop();
    }
}