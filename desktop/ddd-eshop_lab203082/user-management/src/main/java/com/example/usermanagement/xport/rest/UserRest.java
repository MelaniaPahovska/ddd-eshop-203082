package com.example.usermanagement.xport.rest;

import com.example.usermanagement.domain.model.SiteUser;
import com.example.usermanagement.domain.model.SiteUserId;
import com.example.usermanagement.service.UserService;
import com.example.usermanagement.service.forms.UserForm;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserRest {
    private final UserService userService;

    @GetMapping
    public List<SiteUser> listAll() {
        return userService.listAll();
    }

    @GetMapping("/{id}")
    public SiteUser findById(@PathVariable SiteUserId id) {
        return userService.findById(id);
    }

    @PostMapping("/create")
    public void createClient(@RequestBody UserForm userForm, HttpServletResponse response) throws IOException {
        userService.createUser(userForm);
        response.sendRedirect("/api/users");
    }

    @PutMapping("/{id}")
    public void updateClient(@PathVariable("id") SiteUserId userId, @RequestBody UserForm userForm, HttpServletResponse response) throws IOException {
        userService.updateUser(userId, userForm);
        response.sendRedirect("/api/clients");
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") SiteUserId userId, HttpServletResponse response) throws IOException {
        userService.deleteUser(userId);
        response.sendRedirect("/api/users");
    }
}