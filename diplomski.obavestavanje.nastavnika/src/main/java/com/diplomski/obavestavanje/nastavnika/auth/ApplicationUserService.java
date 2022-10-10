package com.diplomski.obavestavanje.nastavnika.auth;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserService implements UserDetailsService {

  private final ApplicationUserDao applicationUserDao;


  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    return applicationUserDao
      .selectedApplicationUserByUsername(username)
      .orElseThrow(() ->
        new UsernameNotFoundException(String.format("Username %s not found", username))
      );
  }

}
