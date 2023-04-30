package com.example.emtlab.service;

import com.example.emtlab.model.Country;

import java.util.List;

public interface CountryService {
    void AddCountry(String name, String continent);
    List<Country> GetAllCountries();
}
