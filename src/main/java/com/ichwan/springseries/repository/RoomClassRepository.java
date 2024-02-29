package com.ichwan.springseries.repository;

import com.ichwan.springseries.entity.RoomClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoomClassRepository extends JpaRepository<RoomClass, UUID> {
}
