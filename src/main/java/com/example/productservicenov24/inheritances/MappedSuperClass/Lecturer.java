package com.example.productservicenov24.inheritances.MappedSuperClass;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mp_lecturers")
public class Lecturer extends User {
    String company;
    String course;
}
