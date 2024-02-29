package com.ichwan.springseries.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "class")
public class RoomClass {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true)
    private String code;
    private int totalTable;
    @OneToMany(mappedBy = "clazz", orphanRemoval = true)
    private Set<Student> students;
    @OneToMany(mappedBy = "clazzes")
    private Set<Teaching> teachings;
}
