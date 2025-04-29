package Streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class Employee {
    public static void main(String[] args) {

        var employees = List.of(
                new EmployeeRecord("Sam", 190_000),
                new EmployeeRecord("Ram", 281_000),
                new EmployeeRecord("Pam", 372_000),
                new EmployeeRecord("Mat", 463_000),
                new EmployeeRecord("Tim", 554_000),
                new EmployeeRecord("Pet", 645_000),
                new EmployeeRecord("Get", 736_000),
                new EmployeeRecord("Agt", 836_000),
                new EmployeeRecord("Tes", 927_000),
                new EmployeeRecord("Est", 1018_000),
                new EmployeeRecord("Dex", 209_000)
        );

        var employeeList = new ArrayList<>(employees);

        var un = Collections.unmodifiableList(employeeList);

        var filteredEmployees = un.stream()
                .filter(salGreaterThan250.and(salLessThan750))
                .sorted(Comparator.comparingInt(EmployeeRecord::salary)
                        .reversed()
                        .thenComparing(EmployeeRecord::name))
                .toList();

        System.out.println(filteredEmployees);
    }

    static Predicate<EmployeeRecord> salGreaterThan250 = e -> e.salary() > 250_000;
    static Predicate<EmployeeRecord> salLessThan750 = e -> e.salary() < 750_000;

    record EmployeeRecord(String name, Integer salary) {
    }
}
