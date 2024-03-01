package com.ichwan.springseries.service;

import com.ichwan.springseries.dto.RoomClassRequest;
import com.ichwan.springseries.entity.RoomClass;
import com.ichwan.springseries.repository.RoomClassRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class RoomClassService {

    private final RoomClassRepository roomClassRepository;

    @Transactional
    public RoomClass create(RoomClassRequest roomClassRequest) {
        RoomClass roomClass = new RoomClass();
        roomClass.setCode(roomClassRequest.code());
        return roomClassRepository.save(roomClass);
    }

    public RoomClass findById(UUID id) {
        return roomClassRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    public Page<RoomClass> findAll(int number, int size) {
        if (size <= 1){
            size = 1;
        }
        Pageable pageable = PageRequest.of(number, size);
        return roomClassRepository.findAll(pageable);
    }

    @Transactional
    public RoomClass update(UUID id, RoomClassRequest roomClassRequest) {
        RoomClass roomClass = roomClassRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        roomClass.setCode(roomClassRequest.code());

        return roomClassRepository.save(roomClass);
    }

    @Transactional
    public void delete(UUID id) {
        roomClassRepository.deleteById(id);
    }
}
