package com.ichwan.springseries.service;

import com.ichwan.springseries.dto.StudentDTO;
import com.ichwan.springseries.entity.Student;
import com.ichwan.springseries.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student create(StudentDTO studentDTO) {
        Student student = new Student();
        student.setName(studentDTO.name());
        student.setAge(studentDTO.age());
        student.setNip(studentDTO.nip());

        return studentRepository.save(student);
    }

    public Student findById(UUID id){
        return studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public Page<Student> findAll(int number, int size){
        Pageable pageable = PageRequest.of(number, size);
        return studentRepository.findAll(pageable);
    }

    @Transactional
    public Student update(UUID id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        student.setName(studentDTO.name());
        student.setAge(studentDTO.age());
        student.setNip(studentDTO.nip());

        return studentRepository.save(student);
    }

    @Transactional
    public void delete(UUID id){
        studentRepository.deleteById(id);
    }
}
