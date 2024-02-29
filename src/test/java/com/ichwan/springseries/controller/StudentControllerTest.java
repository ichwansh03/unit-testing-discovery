package com.ichwan.springseries.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ichwan.springseries.dto.StudentRequest;
import com.ichwan.springseries.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@AutoConfigureMockMvc
class StudentControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void createStudentTest() throws Exception {
        StudentRequest studentRequest = new StudentRequest("Ichwan",22,"12039");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/student/", studentRequest)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print());

        Assertions.assertEquals("Ichwan", studentRequest.name());
        Assertions.assertEquals(22, studentRequest.age());
        Assertions.assertEquals("12039", studentRequest.nip());

        verify(studentService, times(0)).create(studentRequest);
    }

    
}