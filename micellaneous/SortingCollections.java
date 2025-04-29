package micellaneous;

import java.util.Arrays;
import java.util.Comparator;

public class SortingCollections {
    public static void main(String[] args) {
        final var employeeList = Arrays.asList(
                new Employee(1, "Tom", 25, 40_000),
                new Employee(2, "Harry", 30, 70_000),
                new Employee(3, "James", 35, 30_000),
                new Employee(4, "Taylor", 35, 80_000),
                new Employee(5, "Atkinson", 45, 60_000),
                new Employee(6, "James", 45, 80_000)
        );

        System.out.println("** Before sorting **");
        employeeList.forEach(System.out::println);

        employeeList.sort(Comparator.comparingInt(Employee::salary).reversed()
                .thenComparingInt(Employee::age));

        System.out.println("** After sorting **");
        employeeList.forEach(System.out::println);
    }

    record Employee(int id, String name, int age, int salary) {}
}
