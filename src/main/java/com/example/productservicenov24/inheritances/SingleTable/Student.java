package com.example.productservicenov24.inheritances.SingleTable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "st_students")
@DiscriminatorValue("3")
public class Student extends User {
    String batch;
}
