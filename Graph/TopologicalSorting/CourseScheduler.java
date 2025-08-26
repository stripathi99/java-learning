package Graph.TopologicalSorting;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CourseScheduler {
    public static void main(String[] args) {
        final var courses = new int[][] {
                {2, 5}, {0, 5}, {0, 4},
                {1, 4}, {3, 2}, {1, 3}
        };

        final int numCourses = courses.length;

        System.out.println(topologicalSort(courses, numCourses));
    }

    private static List<Integer> topologicalSort(final int[][] courses, final int numCourses) {
        var sortedOrder = new ArrayList<Integer>();
        var inDegree = new int[numCourses];
        var adjacencyList = new ArrayList<List<Integer>>(numCourses);
        for (int i = 0; i < numCourses; i++) adjacencyList.add(new ArrayList<>());
        for (int[] course : courses) {
            adjacencyList.get(course[1]).add(course[0]);
            inDegree[course[0]]++;
        }

        var queue = new LinkedList<Integer>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        while (!queue.isEmpty()) {
            var course = queue.poll();
            sortedOrder.add(course);
            for (int neighbor : adjacencyList.get(course)) {
                if (--inDegree[neighbor] == 0) queue.offer(neighbor);
            }
        }

        return sortedOrder.size() == numCourses ? sortedOrder : null;
    }
}