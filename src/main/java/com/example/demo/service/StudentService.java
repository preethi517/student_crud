package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import java.util.*;

@Service 
public class StudentService {

    @Autowired 
    public StudentRepository studentRepository;

//CREATE
public Student savestudent(Student student){
    return  studentRepository.save(student);
} 

//READ
public List<Student> getAllStudents(){
    return  studentRepository.findAll();
}

//READ BY ID
public Optional<Student> getStudentById(Integer Id){
    return  studentRepository.findById(Id);
}

//UPDATE
public Student updateStudent(Integer Id,Student studentDetails){
    return studentRepository.findById(Id)
    .map(student->{
        student.setName(studentDetails.getName());
                student.setCourse(studentDetails.getCourse());
                        student.setEmail(studentDetails.getEmail());
                        return  studentRepository.save(student);
    })
    .orElseThrow(() -> new RuntimeException("student not found with id:" +Id));
}

//DELETE
public void deleteStudent(Integer Id){
    studentRepository.deleteById(Id);
}






}
