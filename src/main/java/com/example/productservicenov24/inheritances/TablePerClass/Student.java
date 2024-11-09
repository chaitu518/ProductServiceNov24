package com.example.productservicenov24.inheritances.TablePerClass;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "t_students")
public class Student extends User {
    String batch;
}
