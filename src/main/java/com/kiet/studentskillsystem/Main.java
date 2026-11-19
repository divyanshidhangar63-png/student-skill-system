package com.kiet.studentskillsystem;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Student s = new Student(1, "Aman", "aman@kiet.edu", "CSE", 40, 90,
                Arrays.asList("Java", "SQL"));
        System.out.println(s);

        s.setProgrammingScore(60);
        System.out.println("Updated programming score: " + s.getProgrammingScore());
    }
}