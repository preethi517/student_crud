package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import java.util.List;
import java.util.Optional;

@Service 
public class StudentService {

    @Autowired 
    public StudentRepository studentRepository;

//CREATE
public Student savestudent(Student student){
    return  studentRepository.save(student);
} 


// PAGINATED & SEARCHED READ
    public Page<Student> getStudentsPaginatedAndSearched(String search, int page, int size) {
        // "Id" capital matches your Student entity field (@Id public Integer Id;)
        Pageable pageable = PageRequest.of(page, size, Sort.by("Id").descending());
        
        if (search != null && !search.trim().isEmpty()) {
            return studentRepository.searchStudents(search.trim(), pageable);
        }
        return studentRepository.findAll(pageable);
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
