package com.diplomski.obavestavanje.nastavnika.Repository.auth;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Role findByName(String name);
}