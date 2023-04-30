package com.example.emtlab.model.dto;

import com.example.emtlab.model.Country;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class AuthorDto {
    private String name;
    private String surname;
    private Long countryId;

    public AuthorDto() {
    }

    public AuthorDto(String name, String surname, Long countryId) {
        this.name = name;
        this.surname = surname;
        this.countryId = countryId;
    }
}
