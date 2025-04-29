package micellaneous;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

public class LFU {
    public static void main(String[] args) {
        LFUCache lfuCache = new LFUCache(4);
        IntStream.range(1, 5).forEach(i -> {
            lfuCache.put(i, String.valueOf(i));
        });

        lfuCache.displayCacheHeap();

        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));
        System.out.println(lfuCache.get(1));
        System.out.println(lfuCache.get(2));

        lfuCache.displayCacheHeap();

        lfuCache.put(10, String.valueOf(10));
        lfuCache.displayCacheHeap();
    }
}

class LFUCache {
    private Map<Integer, LFUCacheObject> map;
    private int CACHE_CAPACITY;
    private PriorityQueue<LFUCacheObject> minHeap;

    LFUCache(int capacity) {
        this.map = new HashMap<>();
        this.CACHE_CAPACITY = capacity;
        minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.getCounter(), b.getCounter()));
    }

    public void put(int key, Object data) {
        if (map.size() == CACHE_CAPACITY) {
            System.out.println("cache full, removing the least freq. used obj: " + minHeap.peek());
            map.remove(getKeyForRemoval(minHeap.poll()));
            System.out.println(minHeap.size());
            System.out.println(map.size());
        }

        LFUCacheObject cacheObject = new LFUCacheObject(data);
        map.putIfAbsent(key, cacheObject);
        minHeap.offer(cacheObject);
    }

    public Object get(int key) {
        if (!map.containsKey(key)) return -1;
        LFUCacheObject cacheObject = map.get(key);
        cacheObject.increaseAccessCount();
        minHeap.remove(cacheObject);
        minHeap.offer(cacheObject);
        return cacheObject.getObject();
    }

    public void displayCacheHeap() {
        Iterator<LFUCacheObject> iterator = minHeap.iterator();
        while(iterator.hasNext()) {
            LFUCacheObject lfuCacheObject = iterator.next();
            System.out.println(lfuCacheObject.getObject() + ": " + lfuCacheObject.getCounter());
        }
    }

    private int getKeyForRemoval(LFUCacheObject lfuCacheObject) {
        for (Map.Entry<Integer, LFUCacheObject> entry: map.entrySet()) {
            if (entry.getValue().equals(lfuCacheObject)) return entry.getKey();
        }

        return -1;
    }
}

class LFUCacheObject {
    private Object cObject;
    private int counter;

    LFUCacheObject(Object cObject) {
        this.cObject = cObject;
        this.counter = 1;
    }

    public Object getObject() {
        return this.cObject;
    }

    public int getCounter() {
        return this.counter;
    }

    public void increaseAccessCount() {
        this.counter++;
    }

    @Override
    public String toString() {
        return "Obj: " + this.cObject;
    }
}