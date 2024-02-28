package com.ichwan.springseries.controller;

import com.ichwan.springseries.dto.ResponseData;
import com.ichwan.springseries.dto.StudentDTO;
import com.ichwan.springseries.entity.Student;
import com.ichwan.springseries.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class StudentController {

    private final StudentService studentService;
    private static final String STATUS_OK = "200 OK";

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/student")
    public ResponseData<Student> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student student = studentService.create(studentDTO);
        return new ResponseData<>("201 Created", student);
    }

    @GetMapping("student/{id}")
    public ResponseData<Student> getStudentById(@PathVariable("id") UUID uuid){
        Student byId = studentService.findById(uuid);
        return new ResponseData<>(STATUS_OK, byId);
    }

    @GetMapping("students")
    public ResponseData<Page<Student>> getAllStudent(@RequestParam(defaultValue = "0") int pageNumber,
                                                     @RequestParam(defaultValue = "10") int size){
        Page<Student> students = studentService.findAll(pageNumber, size);
        return new ResponseData<>(STATUS_OK, students);
    }

    @PutMapping("student/{id}")
    public ResponseData<Student> updateStudent(@PathVariable("id") UUID uuid, @RequestBody StudentDTO studentDTO){
        Student update = studentService.update(uuid, studentDTO);
        return new ResponseData<>(STATUS_OK, update);
    }

    @DeleteMapping("student/{id}")
    public ResponseData<String> deleteStudent(@PathVariable("id") UUID uuid){
        studentService.delete(uuid);
        return new ResponseData<>(STATUS_OK,"Successfully deleted");
    }
}
