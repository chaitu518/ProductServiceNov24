package com.example.productservicenov24.inheritances.JoinedTable;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "jt_lecturer")
public class Lecturer extends User {
    String company;
    String course;
}
