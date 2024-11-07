package com.example.productservicenov24.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private Long id;
    private String title;
    private double price;
    private String description;
    private Category category;
}
