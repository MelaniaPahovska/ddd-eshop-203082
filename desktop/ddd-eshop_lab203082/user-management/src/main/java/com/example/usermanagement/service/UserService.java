package com.example.usermanagement.service;

import com.example.usermanagement.domain.model.SiteUser;
import com.example.usermanagement.domain.model.SiteUserId;
import com.example.usermanagement.service.forms.UserForm;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<SiteUser> listAll();

    SiteUser findById(SiteUserId userId);

    SiteUserId createUser(UserForm userForm);

    SiteUser updateUser(SiteUserId userId, UserForm userForm);

    void deleteUser(SiteUserId userId);


}
