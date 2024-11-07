package com.example.productservicenov24.Dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeProduct {
    private Long id;
    private String title;
    private String description;
    private double price;
    private String category;
}
