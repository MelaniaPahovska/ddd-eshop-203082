package com.example.usermanagement.domain.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.emt.sharedkernel.domain.Address.Address;
import mk.ukim.finki.emt.sharedkernel.domain.base.AbstractEntity;

import java.util.Objects;

@Entity
@Getter
@Setter
public class SiteUser extends AbstractEntity<SiteUserId> {

    private String username;
    private String name;
    private String surname;
    private Address address;
    private String email;
    private String phone;

    public SiteUser() {
        super(SiteUserId.randomId(SiteUserId.class));
    }

    public SiteUser(String username, String name, String surname, Address address, String email, String phone) {
        super(SiteUserId.randomId(SiteUserId.class));
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    public void updateClient(String username, String name, String surname, Address address, String email, String phone) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SiteUser client = (SiteUser) o;
        return Objects.equals(name, client.name) && Objects.equals(address, client.address) && Objects.equals(email, client.email) && Objects.equals(phone, client.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, address, email, phone);
    }


}
