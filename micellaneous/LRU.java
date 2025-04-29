package micellaneous;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Map;
import java.util.stream.IntStream;

public class LRU {
    public static void main(String[] args) {
        LRUCacheLinearImplementation<Integer> lruCacheLI = new LRUCacheLinearImplementation<Integer>(10);
        IntStream.range(0, 10).forEach(lruCacheLI::put);

        lruCacheLI.printList();

        System.out.println("\ncache: get(8) " + lruCacheLI.get(8));
        lruCacheLI.put(101);
        lruCacheLI.put(102);
        lruCacheLI.get(3);
        lruCacheLI.printList();


        LRUCacheConstantImplementation lruCacheCI = new LRUCacheConstantImplementation(5);
        lruCacheCI.put(0, 11);
        lruCacheCI.put(1, 12);
        lruCacheCI.put(2, 13);
        lruCacheCI.displayCache();

        lruCacheCI.get(1);
        lruCacheCI.displayCache();

        lruCacheCI.get(0);
        lruCacheCI.displayCache();

        lruCacheCI.put(3, 14);
        lruCacheCI.put(4, 15);
         lruCacheCI.displayCache();
        lruCacheCI.put(5, 16);
        lruCacheCI.displayCache();
    }
}

class LRUCacheConstantImplementation {
    private final int INITIAL_CAPACITY;
    private Map<Integer, CacheNode> map;
    private CacheNode head, tail;

    LRUCacheConstantImplementation(int capacity) {
        this.INITIAL_CAPACITY = capacity;
        this.map = new HashMap<>();
        this.head = new CacheNode(0);
        this.tail = new CacheNode(0);

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public void put(int key, Object value) {
        // check if cache is full 
        if(map.size() >= INITIAL_CAPACITY) {
            // first remove the obj at tail and map
            removeItemFromTail();
            //map.remove(tail);
        }

        // add obj at head and put it in map
        CacheNode cacheNode = new CacheNode(value);
        addItemAtHead(cacheNode);
        map.put(key, cacheNode);
    }

    public Object get(int key) {
        if(!map.containsKey(key)) return -1;
        CacheNode cacheNode = map.get(key);

        cacheNode.prev.next = cacheNode.next;
        cacheNode.next.prev = cacheNode.prev;

        addItemAtHead(cacheNode);
        return cacheNode.value;
    }

    private void addItemAtHead(CacheNode cacheNode) {
        cacheNode.prev = head;
        cacheNode.next = head.next;
        head.next.prev = cacheNode;
        head.next = cacheNode;
    }

    private void removeItemFromTail() {
        CacheNode prev = tail.prev;
        tail.prev = null;
        prev.next = null;
    }

    public void displayCache() {
        CacheNode curr = head.next;
        System.out.println("");
        while(curr.next != null) {
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
    }
}

class LRUCacheLinearImplementation<E> {
    private final int INITIAL_CAPACITY;
    private LinkedList<E> list;

    LRUCacheLinearImplementation(int capacity) {
        this.INITIAL_CAPACITY = capacity;
        list = new LinkedList<E>();
    }

    public void put(E e) {
        if(!(list.size() < INITIAL_CAPACITY)) list.removeLast();
        list.offerFirst(e);
    }

    public Object get(E e) {
        // return -1 if obj is not in the list
        if(!list.contains(e)) return -1; // this operation results in O(n)

        // remove the obj
        list.remove(e); // this operation results in O(n)

        // put it back in the list
        list.offerFirst(e);
        
        return e;
    }

    public void printList() {
        if(!list.isEmpty()) {
            ListIterator<E> iterator = list.listIterator();
            while(iterator.hasNext()) {
                System.out.print(iterator.next() + " ");
            }
        }
    }
}

class CacheNode {
    Object value;
    CacheNode prev;
    CacheNode next;

    CacheNode(Object val) {
        this.value = val;
        this.prev = null;
        this.next = null;
    }
}