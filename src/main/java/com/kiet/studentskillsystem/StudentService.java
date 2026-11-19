package com.kiet.studentskillsystem;

import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) { this.repo = repo; }

    // Q2.8 - validation + duplicate prevention
    public Student addStudent(Student s) {
        validateScores(s.getProgrammingScore(), s.getMathematicsScore());
        if (repo.existsById(s.getStudentId()))
            throw new DuplicateStudentException("Student ID " + s.getStudentId() + " already exists");
        if (repo.existsByEmailIgnoreCase(s.getEmail()))
            throw new DuplicateStudentException("Email " + s.getEmail() + " is already registered");
        return repo.save(s);
    }

    public void validateScores(double programming, double mathematics) {
        if (programming < 0 || programming > 100 || mathematics < 0 || mathematics > 100)
            throw new IllegalArgumentException("Scores must be between 0 and 100");
    }

    // Q2.4 - search by ID, email or course
    public List<Student> search(Integer id, String email, String course) {
        if (id != null) {
            Optional<Student> s = repo.findById(id);
            return s.isPresent() ? Collections.singletonList(s.get()) : Collections.<Student>emptyList();
        }
        if (email != null) {
            Optional<Student> s = repo.findByEmailIgnoreCase(email);
            return s.isPresent() ? Collections.singletonList(s.get()) : Collections.<Student>emptyList();
        }
        if (course != null) return repo.findByCourseNameIgnoreCase(course);
        return Collections.emptyList();
    }

    // Q2.5 - merit list, average descending
    public List<Student> meritList() {
        List<Student> list = new ArrayList<>(repo.findAll());
        list.sort(Comparator.comparingDouble(Student::getAverage).reversed());
        return list;
    }

    // Q2.6 - skill -> students who have it (only skills shared by 2+ students)
    public Map<String, Set<String>> studentsWithSameSkills() {
        Map<String, Set<String>> bySkill = new TreeMap<>();
        for (Student s : repo.findAll()) {
            for (String skill : s.getSkills()) {
                bySkill.computeIfAbsent(skill.trim().toLowerCase(), k -> new TreeSet<>())
                       .add(s.getStudentName());
            }
        }
        bySkill.entrySet().removeIf(e -> e.getValue().size() < 2);
        return bySkill;
    }

    // Q2.7 - programming below 50 but maths 75 or more
    public List<Student> needsProgrammingImprovementButStrongMaths() {
        return repo.findByProgrammingScoreLessThanAndMathematicsScoreGreaterThanEqual(50, 75);
    }
}