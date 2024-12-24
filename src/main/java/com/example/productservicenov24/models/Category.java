package com.example.productservicenov24.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity(name = "categories")
public class Category extends BaseModel implements Serializable {

}
