package DesignPatterns;

import java.time.LocalDate;

public class Builder {
    public static void main(String[] args) {
        Employee employee = Employee.builder()
                .withAge(29)
                .withName("Samael Smith")
                .withUserName("sam@1234")
                .withAddress("NYC, New York")
                .build();
        System.out.printf("Employee obj: %s", employee);
    }
}

class Employee {
    private final String name;
    private final String userName;
    private final String address;
    private final int age;
    private final String dateOfBirth;

    private Employee(String name, String userName, String address, int age, String dateOfBirth) {
        this.name = name;
        this.userName = userName;
        this.address = address;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
    }

    public static EmployeeBuilder builder() {
        return new EmployeeBuilder();
    }

    public static class EmployeeBuilder {
        private static final String DEFAULT_NAME = "Default Name";
        private static final String DEFAULT_USERNAME = "default-user-name";
        private static final String DEFAULT_ADDRESS = "127.0.0.1";
        private static final int DEFAULT_AGE = 35;
        private static final String DEFAULT_DOB = LocalDate.now().toString();

        private String name;
        private String userName;
        private String address;
        private int age;
        private String dateOfBirth;

        EmployeeBuilder() {
            this.name = DEFAULT_NAME;
            this.userName = DEFAULT_USERNAME;
            this.address = DEFAULT_ADDRESS;
            this.age = DEFAULT_AGE;
            this.dateOfBirth = DEFAULT_DOB;
        }

        public EmployeeBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public EmployeeBuilder withUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public EmployeeBuilder withAge(int age) {
            this.age = age;
            return this;
        }

        public EmployeeBuilder withAddress(String address) {
            this.address = address;
            return this;
        }

        public EmployeeBuilder withDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Employee build() {
            return new Employee(this.name, this.userName, this.address, this.age, this.dateOfBirth);
        }
    }

    @Override
    public String toString() {
        return String.format("Employee{name='%s', userName='%s', address='%s', age=%d, dateOfBirth='%s'}",
                name, userName, address, age, dateOfBirth);
    }
}