package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import java.util.*;

@RestController 
@RequestMapping("/api/students")
public class StudentController {

    @Autowired 
    public StudentService studentService;

    @PostMapping 
    public Student createStudent(@RequestBody Student student){
        return  studentService.savestudent(student);
    }
    @GetMapping 
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("/{Id}") 
    public ResponseEntity<Optional<Student>> getStudentById(@PathVariable Integer Id){
        return  ResponseEntity.ok(studentService.getStudentById(Id));
    }
    @PutMapping("/{Id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Integer Id,@RequestBody Student student){
        return  ResponseEntity.ok(studentService.updateStudent(Id, student));
    }
    @DeleteMapping("/{Id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Integer Id){
        studentService.deleteStudent(Id);
        return  ResponseEntity.ok().build();
    }

}
