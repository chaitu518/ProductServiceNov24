package com.example.productservicenov24.Dtos;

import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private long id;
    private String name;
    private String email;
    private boolean isEmailVerified;
    List<RoleDto> roles;
}
