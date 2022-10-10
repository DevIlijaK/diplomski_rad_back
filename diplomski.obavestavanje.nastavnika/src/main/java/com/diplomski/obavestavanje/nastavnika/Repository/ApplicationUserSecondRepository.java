package com.diplomski.obavestavanje.nastavnika.Repository;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.ApplicationUserSecond;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserSecondRepository extends JpaRepository<ApplicationUserSecond, Long> {
  ApplicationUserSecond findByUsername(String username);
}
