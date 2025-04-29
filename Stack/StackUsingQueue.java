package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.stream.IntStream;

public class StackUsingQueue {
    public static void main(String[] args) {
        StackImplementation<Integer> stackImplementation = new StackImplementation<Integer>();
        IntStream.range(0,  10).forEach(stackImplementation::addToStack);
        int n = stackImplementation.getStackSize();
        for(int i = 0; i < n; i++) {
            System.out.print(stackImplementation.getFromStack());
            System.out.print(" ");
        }
    }
}

class StackImplementation<E> {
    Deque<E> deque;
    
    StackImplementation() {
        deque = new ArrayDeque<E>();
    }

    public void addToStack(E e) {
        deque.offerFirst(e);
    }

    public int getStackSize() {
        return deque.size();
    }

    public E getFromStack() {
        int n = deque.size();
        while(n-- > 0) {
            deque.offerFirst(deque.remove());
            //n--;
        }
        return deque.remove();
    }
}