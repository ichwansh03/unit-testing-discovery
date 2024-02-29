package com.ichwan.springseries.service;

import com.ichwan.springseries.dto.RoomClassRequest;
import com.ichwan.springseries.entity.RoomClass;
import com.ichwan.springseries.repository.RoomClassRepository;
import com.ichwan.springseries.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomClassService {

    private final RoomClassRepository roomClassRepository;
    private final StudentRepository studentRepository;

    public RoomClassService(RoomClassRepository roomClassRepository, StudentRepository studentRepository) {
        this.roomClassRepository = roomClassRepository;
        this.studentRepository = studentRepository;
    }

    //list siswa
    public RoomClass create(RoomClassRequest roomClassRequest) {
        RoomClass roomClass = new RoomClass();
        roomClass.setCode(roomClassRequest.code());
        return roomClassRepository.save(roomClass);
    }
    //buat kelas
    //list guru
}
