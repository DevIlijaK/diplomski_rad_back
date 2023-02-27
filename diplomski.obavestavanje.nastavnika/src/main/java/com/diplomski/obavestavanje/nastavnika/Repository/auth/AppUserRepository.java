package com.diplomski.obavestavanje.nastavnika.Repository.auth;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  AppUser findByUsername(String username);
}
