package com.example.usermanagement.service.forms;

import lombok.Data;
import mk.ukim.finki.emt.sharedkernel.domain.Address.Address;

import javax.validation.constraints.NotNull;

@Data
public class UserForm {
    @NotNull
    public String username;
    @NotNull
    public String name;
    @NotNull
    public String surname;
    @NotNull
    public Address address;
    @NotNull
    public String email;
    @NotNull
    public String phone;
}