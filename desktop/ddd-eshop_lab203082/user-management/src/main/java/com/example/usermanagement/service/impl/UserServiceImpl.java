package com.example.usermanagement.service.impl;

import com.example.usermanagement.domain.exceptions.UserNotFoundException;
import com.example.usermanagement.domain.model.SiteUser;
import com.example.usermanagement.domain.model.SiteUserId;
import com.example.usermanagement.domain.repository.UserRepository;
import com.example.usermanagement.service.UserService;
import com.example.usermanagement.service.forms.UserForm;
import jakarta.validation.Validator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final Validator validator;

    @Override
    public List<SiteUser> listAll() {
        return userRepository.findAll();
    }

    @Override
    public SiteUser findById(@NotNull SiteUserId userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public SiteUserId createUser(UserForm userForm) {
        var violations = validator.validate(userForm);

        if (!violations.isEmpty()) {
            throw new IllegalArgumentException("Invalid client data");
        }

        var c = new SiteUser(userForm.getUsername(), userForm.getName(), userForm.getSurname(), userForm.getAddress(), userForm.getEmail(), userForm.getPhone());

        return userRepository.saveAndFlush(c).getId();
    }

    @Override
    public SiteUser updateUser(@NotNull SiteUserId userId, @NotNull UserForm userForm) {
        var user = findById(userId);

        user.setUsername(userForm.getUsername());
        user.setName(userForm.getName());
        user.setSurname(userForm.getSurname());
        user.setAddress(userForm.getAddress());
        user.setEmail(userForm.getEmail());
        user.setPhone(userForm.getPhone());

        return userRepository.saveAndFlush(user);
    }


    @Override
    public void deleteUser(SiteUserId userId) {
        userRepository.deleteById(userId);

    }
}
