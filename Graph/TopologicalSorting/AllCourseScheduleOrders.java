package Graph.TopologicalSorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AllCourseScheduleOrders {
    public static void main(String[] args) {

        final var courses = new int[][] {
                {2,5}, {0,5}, {0, 4},
                {1, 4}, {3, 2}, {1, 3}
        };

        final int numCourses = courses.length;

        var allOrders = calculateAllPossibleOrders(courses, numCourses);
        allOrders.forEach(System.out::println);
    }

    private static List<List<Integer>> calculateAllPossibleOrders(final int[][] courses, final int numCourses) {
        var allOrders = new ArrayList<List<Integer>>();
        int[] inDegree = new int[numCourses];
        List<List<Integer>> graph = new ArrayList<>(numCourses);

        for (int i = 0; i < numCourses; i++) graph.add(new ArrayList<>());
        for (int[] course : courses) {
            graph.get(course[1]).add(course[0]);
            inDegree[course[0]]++;
        }

        var queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.add(i);
        }

        getAllSortedOrders(inDegree, allOrders, graph, queue, new ArrayList<>());

        return allOrders;
    }

    private static void getAllSortedOrders(int[] inDegree, List<List<Integer>> allOrders,
                                           List<List<Integer>> graph,
                                           Queue<Integer> queue, List<Integer> sortedOrder) {
        if (!queue.isEmpty()) {
            for (var course: queue) {
                sortedOrder.add(course);
                var clonedQueue = new LinkedList<>(queue);
                clonedQueue.remove(course);
                for (var neighbor : graph.get(course)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) clonedQueue.offer(neighbor);
                }

                getAllSortedOrders(inDegree, allOrders, graph, clonedQueue, sortedOrder);
                sortedOrder.removeLast();
                for (var neighbor : graph.get(course)) inDegree[neighbor]++;
            }
        }

        if (sortedOrder.size() == inDegree.length) allOrders.add(new ArrayList<>(sortedOrder));
    }

//    private static Queue<Integer> cloneQueue(final Queue<Integer> queue) {
//        var clonedQueue = new LinkedList<Integer>();
//        for (var course : queue) clonedQueue.offer(course);
//        return clonedQueue;
//    }
}
