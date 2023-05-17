package com.example.usermanagement.config;

import com.example.usermanagement.domain.model.SiteUser;
import com.example.usermanagement.domain.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import mk.ukim.finki.emt.sharedkernel.domain.Address.Address;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInit {
    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        for(int i = 0; i<3 ;i++)
            userRepository.saveAndFlush(new SiteUser("username" + i ,"name" + i, "surname" + i, new Address("Street" + i, "5", "City " + i, "Country"), "username@gmail.com", "078/346/223"));
        }
    }

