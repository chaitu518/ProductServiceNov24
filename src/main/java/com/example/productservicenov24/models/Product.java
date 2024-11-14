package com.example.productservicenov24.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "products")
public class Product extends BaseModel {
    private double price;
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
}
