package Streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class StudentRank {

    private static final Random random = new Random();
    private static final int totalStudents = 10_000;
    private static final int topK = 5;

    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();
        for (int i = 1; i <= totalStudents; i++) {
            students.add(new Student(i, List.of(
                    new Subject("Math", random.nextInt(50, 100)),
                    new Subject("Physics", random.nextInt(50, 100)),
                    new Subject("Chemistry", random.nextInt(50, 100)),
                    new Subject("Computers", random.nextInt(50, 100)),
                    new Subject("English", random.nextInt(50, 100))
            )));
        }

        List<Student> topStudents = students.stream()
                .sorted(StudentRank::compareUsingAverageMarks)
                .limit(topK) // need only topK students
                .toList();

        Map<Integer, Double> mapOfAverageOfTopStudents = topStudents.stream()
                .collect(Collectors.toMap(Student::id, StudentRank::calculateAverage));

//        List<Double> averageOfTopStudents = topStudents.stream()
//                .map(StudentRank::calculateAverage)
//                .toList();

        topStudents.forEach(System.out::println);
        System.out.println(mapOfAverageOfTopStudents);
    }

    private static double calculateAverage(Student student) {
        return student.subjectList().stream()
                .mapToInt(Subject::marks)
                .average()
                .orElse(0);
    }

    private static int compareUsingAverageMarks(Student a, Student b) {
        return Double.compare(calculateAverage(b), calculateAverage(a));
    }

    record Student(int id, List<Subject> subjectList) {}
    record Subject(String name, int marks) {}
}