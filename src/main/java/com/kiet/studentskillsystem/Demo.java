package com.kiet.studentskillsystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
class Q2Demo implements CommandLineRunner {

    private final StudentService service;

    public Q2Demo(StudentService service) { this.service = service; }

    @Override
    public void run(String... args) {
        add(new Student(1, "Aman", "aman@kiet.edu", "CSE", 40, 90, Arrays.asList("Java", "SQL")));
        add(new Student(2, "Riya", "riya@kiet.edu", "CSE", 92, 88, Arrays.asList("java", "Python")));
        add(new Student(3, "Karan", "karan@kiet.edu", "IT", 65, 55, Arrays.asList("Python", "SQL")));
        add(new Student(4, "Neha", "neha@kiet.edu", "CSE", 30, 40, Arrays.asList("HTML")));

        System.out.println("\n--- Average, category, stronger subject ---");
        for (Student s : service.meritList())
            System.out.println(s.getStudentName() + " avg=" + s.getAverage()
                    + " " + s.getCategory() + " stronger=" + s.getStrongerSubject());

        System.out.println("\n--- Search by course CSE ---");
        service.search(null, null, "CSE").forEach(System.out::println);

        System.out.println("\n--- Merit list ---");
        service.meritList().forEach(s -> System.out.println(s.getStudentName() + " " + s.getAverage()));

        System.out.println("\n--- Same skills ---");
        System.out.println(service.studentsWithSameSkills());

        System.out.println("\n--- Weak programming, maths >= 75 ---");
        service.needsProgrammingImprovementButStrongMaths().forEach(System.out::println);

        System.out.println("\n--- Validation tests ---");
        add(new Student(1, "Duplicate", "dup@kiet.edu", "CSE", 50, 50, null));
        add(new Student(9, "BadScore", "bad@kiet.edu", "CSE", 150, 50, null));
    }

    private void add(Student s) {
        try {
            service.addStudent(s);
            System.out.println("Added: " + s.getStudentName());
        } catch (RuntimeException e) {
            System.out.println("Rejected " + s.getStudentName() + ": " + e.getMessage());
        }
    }
}