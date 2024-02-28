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
@Table(name = "teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @Column(unique = true)
    private String nik;
    @ManyToOne(cascade = CascadeType.ALL)
    private Student students;
    @OneToMany(mappedBy = "teacher")
    private Set<Teaching> teaching;
}
