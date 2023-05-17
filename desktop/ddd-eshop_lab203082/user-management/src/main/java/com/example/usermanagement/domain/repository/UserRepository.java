package com.example.usermanagement.domain.repository;

import com.example.usermanagement.domain.model.SiteUser;
import com.example.usermanagement.domain.model.SiteUserId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<SiteUser, SiteUserId> {
}
