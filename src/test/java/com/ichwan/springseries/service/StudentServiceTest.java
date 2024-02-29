package com.ichwan.springseries.service;

import com.ichwan.springseries.dto.StudentRequest;
import com.ichwan.springseries.entity.Student;
import com.ichwan.springseries.repository.RoomClassRepository;
import com.ichwan.springseries.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@SpringBootTest
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private RoomClassRepository roomClassRepository;

    StudentRequest studentRequest = new StudentRequest(
            "ichwan",
            12,
            "12121",
            null);

    Student student = new Student();

    @BeforeEach
    void setUp() {
        student.setName(studentRequest.name());
        student.setNip(studentRequest.nip());
        student.setAge(studentRequest.age());
    }

    @AfterEach
    void tearDown() {
        studentRepository.deleteAll();
    }

    @Test
    void createStudentTest() {
        when(studentRepository.save(student)).thenReturn(student);

        Student newStudent = studentService.create(studentRequest);

        Assertions.assertEquals(newStudent,student);
        Assertions.assertNotNull(student);

        ArgumentCaptor<Student> studentCaptor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(studentCaptor.capture());

        Student value = studentCaptor.getValue();
        Assertions.assertEquals(value, newStudent);
    }

    @Test
    void getStudentByIdTest() {
        UUID id = UUID.randomUUID();
        student = new Student();
        student.setId(id);
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        Student byId = studentService.findById(id);
         Assertions.assertNotNull(student);
        Assertions.assertEquals(student, byId);
    }

    @Test
    void getAllStudentsTest() {
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            student = new Student();
            student.setName("name "+i);
            student.setAge(i);
            student.setNip("12"+i);
            students.add(student);
        }

        Pageable pageable = PageRequest.of(students.size(), 1);
        studentService.findAll(pageable.getPageNumber(), pageable.getPageSize());

        when(studentRepository.findAll()).thenReturn(students);
        Assertions.assertNotNull(students);

        verify(studentRepository).findAll(any(PageRequest.class));
        verifyNoMoreInteractions(studentRepository);
    }

    @TestFactory
    Stream<Student> getAllStudentwithStreamTest(){
        Stream<Student> studentStream = studentRepository.findAll().stream();
        Assertions.assertEquals(0, studentStream.count());
        return studentRepository.findAll().stream();
    }

    @Test
    void updateStudentTest() {
        UUID studentId = UUID.randomUUID();

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(studentRepository.save(student)).thenReturn(student);

        Student updatedStudent = studentService.update(studentId, studentRequest);

        Assertions.assertEquals(updatedStudent.getName(), studentRequest.name());
        Assertions.assertEquals(updatedStudent.getAge(), studentRequest.age());
        Assertions.assertEquals(updatedStudent.getNip(), studentRequest.nip());
    }

    @Test
    void deleteStudentTest() {
        studentService.delete(any());
        verify(studentRepository, atLeastOnce()).deleteById(any());
    }
}