package com.vector.crudapp.controller;

import com.vector.crudapp.dao.StudentDAO;
import com.vector.crudapp.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/students")  // Base URL for all student-related APIs
public class StudentController {

    private final StudentDAO studentDAO;


    @Autowired
    public StudentController(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    // ✅ 1. Create a student
    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student) {
        studentDAO.save(student);
        return student;
    }

    // ✅ 2. Get all students
    @GetMapping("/all")
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    // ✅ 3. Get a student by ID
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentDAO.findbyID(id);
    }

    // ✅ 4. Update a student
    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Optional<Student> optionalStudent = Optional.ofNullable(studentDAO.findbyID(Math.toIntExact(id)));

        if (optionalStudent.isPresent()) {
            Student existingStudent = optionalStudent.get();

            // Update only non-null fields
            if (updatedStudent.getFirstname() != null && !updatedStudent.getFirstname().isEmpty()) {
                existingStudent.setFirstname(updatedStudent.getFirstname());
            }
            if (updatedStudent.getLastname() != null && !updatedStudent.getLastname().isEmpty()) {
                existingStudent.setLastname(updatedStudent.getLastname());
            }
            if (updatedStudent.getEmail() != null && !updatedStudent.getEmail().isEmpty()) {
                existingStudent.setEmail(updatedStudent.getEmail());
            }

            studentDAO.save(existingStudent);
            return ResponseEntity.ok(existingStudent);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // ✅ 5. Delete a student
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        studentDAO.delete(id);
        return "Student with ID " + id + " deleted.";
    }

    // ✅ 6. Delete all students
    @DeleteMapping("/clear")
    public String deleteAllStudents() {
        int deletedCount = studentDAO.deleteALL();
        return deletedCount + " students deleted.";
    }
}

