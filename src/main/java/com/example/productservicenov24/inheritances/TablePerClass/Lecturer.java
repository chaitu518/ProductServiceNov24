package com.example.productservicenov24.inheritances.TablePerClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "t_lecturers")
public class Lecturer extends User {
    String company;
    String course;
}
