package Streams;

import java.util.Comparator;
import java.util.List;

public class EmployeeSalary {
    private static class Employee {
        private final int id;
        private final String name;
        private double salary;

        @Override
        public String toString() {
            return "Employee{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }

        Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public double getSalary() {
            return salary;
        }

        public void setSalary(double salary) {
            this.salary = salary;
        }
    }

    public static void main(String[] args) {
        var employeeList = List.of(
                new Employee(101, "Ram", 109_000),
                new Employee(102, "Sam", 118_000),
                new Employee(103, "Mat", 127_000),
                new Employee(104, "Pat", 136_000),
                new Employee(105, "Tim", 145_000),
                new Employee(106, "Mit", 154_000),
                new Employee(107, "Ray", 163_000),
                new Employee(108, "Jay", 172_000),
                new Employee(109, "Bob", 181_000),
                new Employee(110, "Kim", 190_000),
                new Employee(110, "Omk", 190_000)
        );

        employeeList.stream()
                .filter(e -> e.salary > 150_000)
                .map(Employee::getName)
                .forEach(System.out::println);

        var employeesWithSalaryIncrement = employeeList
                .stream()
                .peek(e -> e.setSalary(e.getSalary() * 1.10))
                .toList();

        System.out.println(employeesWithSalaryIncrement);

        System.out.println(employeesWithSalaryIncrement.stream()
                .max(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName)));
    }
}
