package com.example.productservicenov24.inheritances.TablePerClass;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "t_mentors")
public class Mentor extends User {
    String specialization;
}
