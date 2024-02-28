package com.ichwan.springseries.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
public class Teaching {

    @Id
    UUID id;
    @ManyToOne
    private Clazz clazzes;
    @ManyToOne
    private Teacher teacher;
    private String subject;
}
