package com.ichwan.springseries.entity;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentTest {

    @Test
    void studentTest() {
        Student student = new Student();
        student.setName("Ichwan");
        student.setAge(20);
        student.setNip("12939");

        Clazz clazz = new Clazz();
        student.setClazz(clazz);

        Set<Teacher> teachers = new HashSet<>();

        for (int i = 0; i < 5; i++) {
            Teacher teacher = new Teacher();
            teacher.setName("teacher "+i);
            teacher.setStudents(student);
            teacher.setNik("1231"+i);
            teachers.add(teacher);
        }

        student.setTeachers(teachers);

        assertEquals("Ichwan", student.getName());
        assertEquals(20, student.getAge());
        assertEquals("12939", student.getNip());
        assertEquals(teachers.size(), student.getTeachers().size());

        assertNotNull(student);
    }

}