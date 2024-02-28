package com.ichwan.springseries.repository;

import com.ichwan.springseries.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClazzRepository extends JpaRepository<Clazz, UUID> {
}
