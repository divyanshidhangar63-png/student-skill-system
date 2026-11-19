package com.kiet.studentskillsystem;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "students")
public class Student {

    @Id
    @Column(name = "student_id")
    private int studentId;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "programming_score", nullable = false)
    private double programmingScore;

    @Column(name = "mathematics_score", nullable = false)
    private double mathematicsScore;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "student_skills", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "skill")
    private List<String> skills = new ArrayList<>();

    public Student() { }

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

    // ---- derived business methods (not stored in the DB) ----
    public double getAverage() {                       // Q2.1
        return (programmingScore + mathematicsScore) / 2.0;
    }

    public String getCategory() {                      // Q2.2
        double avg = getAverage();
        if (avg >= 85) return "Excellent";
        if (avg >= 70) return "Good";
        if (avg >= 50) return "Satisfactory";
        return "Needs Improvement";
    }

    public String getStrongerSubject() {               // Q2.3
        if (programmingScore > mathematicsScore) return "Programming";
        if (mathematicsScore > programmingScore) return "Mathematics";
        return "Both equal";
    }

    // ---- getters and setters ----
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

    @Override
    public String toString() {
        return "Student{id=" + studentId + ", name=" + studentName + ", course=" + courseName
                + ", avg=" + getAverage() + ", category=" + getCategory() + ", skills=" + skills + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        return studentId == ((Student) o).studentId;
    }

    @Override
    public int hashCode() { return Objects.hash(studentId); }
}