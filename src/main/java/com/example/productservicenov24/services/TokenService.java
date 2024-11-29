package com.example.productservicenov24.services;

import com.example.productservicenov24.Dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {
    RestTemplate restTemplate;
    public TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public boolean validateToken(String token) {
        UserDto userDto = restTemplate.getForObject("http://localhost:8080/users/validate/" + token, UserDto.class);
        if(userDto != null
        && userDto.getEmail() != null) {
            return true;
        }
        return false;
    }
}
