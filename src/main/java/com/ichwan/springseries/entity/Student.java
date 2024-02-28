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
@Table(name = "students", uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "nip"})})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private int age;
    private String nip;
    @ManyToOne(cascade = CascadeType.ALL)
    private Clazz clazz;
    @OneToMany(mappedBy = "students", orphanRemoval = true)
    private Set<Teacher> teachers;
}
