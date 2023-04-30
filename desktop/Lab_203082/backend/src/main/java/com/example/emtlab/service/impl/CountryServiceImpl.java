package com.example.emtlab.service.impl;


import com.example.emtlab.model.Country;
import com.example.emtlab.repository.CountryRepository;
import com.example.emtlab.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public void AddCountry(String name, String continent) {
        Country c = new Country(name, continent);
        countryRepository.save(c);
    }

    @Override
    public List<Country> GetAllCountries() {
        return countryRepository.findAll();
    }
}
