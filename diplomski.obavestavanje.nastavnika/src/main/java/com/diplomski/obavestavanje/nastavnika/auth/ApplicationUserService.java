package com.diplomski.obavestavanje.nastavnika.auth;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.diplomski.obavestavanje.nastavnika.Security.ApplicationUserRole.STUDENT;

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
