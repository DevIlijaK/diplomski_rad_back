package com.diplomski.obavestavanje.nastavnika.repository.auth;

import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
  AppUser findByUsername(String username);
}
