package com.ichwan.springseries.service;

import com.ichwan.springseries.dto.RoomClassRequest;
import com.ichwan.springseries.entity.RoomClass;
import com.ichwan.springseries.repository.RoomClassRepository;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class RoomClassServiceTest {

    @InjectMocks
    private RoomClassService roomClassService;

    @Mock
    private RoomClassRepository roomClassRepository;

    RoomClassRequest roomClassRequest = new RoomClassRequest("12IPA A", 40);

    RoomClass roomClass = new RoomClass();

    @BeforeEach
    void setUp() {
        roomClass.setCode(roomClassRequest.code());
        roomClass.setTotalTable(roomClassRequest.totalTable());
    }

    @AfterEach
    void tearDown() {
        roomClassRepository.deleteAll();
    }

    @Test
    void createRoomClassTest() {
        when(roomClassRepository.save(roomClass)).thenReturn(roomClass);

        RoomClass newRoomClass = roomClassService.create(roomClassRequest);

        Assertions.assertEquals(newRoomClass, roomClass);
        Assertions.assertNotNull(newRoomClass);

        ArgumentCaptor<RoomClass> roomClassCaptor = ArgumentCaptor.forClass(RoomClass.class);
        verify(roomClassRepository).save(roomClassCaptor.capture());

        RoomClass value = roomClassCaptor.getValue();
        Assertions.assertEquals(value, newRoomClass);
    }

    @Test
    void getRoomClassByIdTest() {
        UUID id = UUID.randomUUID();
        roomClass = new RoomClass();
        roomClass.setId(id);
        when(roomClassRepository.findById(id)).thenReturn(Optional.of(roomClass));

        RoomClass byId = roomClassService.findById(id);
        Assertions.assertNotNull(byId);
        Assertions.assertEquals(roomClass, byId);
    }

    @Test
    void getAllRoomClassTest() {
        List<RoomClass> roomClasses = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            roomClass = new RoomClass();
            roomClass.setCode("IPA "+i);
            roomClass.setTotalTable(i);
            roomClasses.add(roomClass);
        }

        Pageable pageable = PageRequest.of(roomClasses.size(), 1);
        roomClassService.findAll(pageable.getPageNumber(), pageable.getPageSize());

        when(roomClassRepository.findAll()).thenReturn(roomClasses);
        Assertions.assertNotNull(roomClasses);
        Assertions.assertEquals(5, roomClasses.size());
    }

    @TestFactory
    Stream<RoomClass> getAllRoomClasswithStreamTest() {
        Stream<RoomClass> roomClassStream = roomClassRepository.findAll().stream();
        Assertions.assertEquals(0, roomClassStream.count());
        return roomClassRepository.findAll().stream();
    }

    @Test
    void updateRoomClassTest() {
        UUID roomId = UUID.randomUUID();

        when(roomClassRepository.findById(roomId)).thenReturn(Optional.of(roomClass));
        when(roomClassRepository.save(roomClass)).thenReturn(roomClass);

        RoomClass update = roomClassService.update(roomId, roomClassRequest);

        Assertions.assertEquals(update.getCode(), roomClassRequest.code());
        Assertions.assertEquals(update.getTotalTable(), roomClassRequest.totalTable());
    }

    @Test
    void deleteRoomClassTest() {
        roomClassService.delete(any());
        verify(roomClassRepository, atLeastOnce()).deleteById(any());
    }
}