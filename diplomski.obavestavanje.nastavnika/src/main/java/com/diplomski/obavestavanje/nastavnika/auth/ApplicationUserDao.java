package com.diplomski.obavestavanje.nastavnika.auth;

import java.util.Optional;

public interface ApplicationUserDao {

  public Optional<ApplicationUser> selectedApplicationUserByUsername(String username);
}
