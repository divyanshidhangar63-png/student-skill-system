package com.kiet.studentskillsystem;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {

    // private fields = encapsulation
    private int studentId;
    private String studentName;
    private String email;
    private String courseName;
    private double programmingScore;
    private double mathematicsScore;
    private List<String> skills;

    // default constructor
    public Student() {
        this.skills = new ArrayList<>();
    }

    // parameterized constructor
    public Student(int studentId, String studentName, String email, String courseName,
                   double programmingScore, double mathematicsScore, List<String> skills) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.email = email;
        this.courseName = courseName;
        this.programmingScore = programmingScore;
        this.mathematicsScore = mathematicsScore;
        this.skills = (skills == null) ? new ArrayList<>() : new ArrayList<>(skills);
    }

    // getters and setters
    public int getStudentId() { return studentId; }
    public void setStudentId(int studentId) { this.studentId = studentId; }

    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public double getProgrammingScore() { return programmingScore; }
    public void setProgrammingScore(double programmingScore) { this.programmingScore = programmingScore; }

    public double getMathematicsScore() { return mathematicsScore; }
    public void setMathematicsScore(double mathematicsScore) { this.mathematicsScore = mathematicsScore; }

    public List<String> getSkills() { return skills; }
    public void setSkills(List<String> skills) {
        this.skills = (skills == null) ? new ArrayList<>() : new ArrayList<>(skills);
    }

    // object methods
    @Override
    public String toString() {
        return "Student{id=" + studentId + ", name=" + studentName + ", email=" + email
                + ", course=" + courseName + ", programming=" + programmingScore
                + ", maths=" + mathematicsScore + ", skills=" + skills + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        Student other = (Student) o;
        return studentId == other.studentId;   // two students are equal if their IDs match
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId);
    }
}