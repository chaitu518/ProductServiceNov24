package com.example.productservicenov24.inheritances.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_lecturers")
@DiscriminatorValue(value = "1")
public class Lecturer extends User {
    String company;
    String course;
}
