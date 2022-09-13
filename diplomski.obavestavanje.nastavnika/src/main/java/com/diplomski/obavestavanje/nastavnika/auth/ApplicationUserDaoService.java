package com.diplomski.obavestavanje.nastavnika.auth;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.diplomski.obavestavanje.nastavnika.Security.ApplicationUserRole.*;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApplicationUserDaoService implements ApplicationUserDao{
  private final PasswordEncoder passwordEncoder;

  @Override
  public Optional<ApplicationUser> selectedApplicationUserByUsername(String username) {
    return getApplicationUsers()
      .stream()
      .filter(applicationUser -> username.equals(applicationUser.getUsername()))
      .findFirst();
  }

  private List<ApplicationUser> getApplicationUsers() {
    List<ApplicationUser> applicationUsers = Lists.newArrayList(
      new ApplicationUser(
        "annasmith",
        passwordEncoder.encode("password"),
        STUDENT.getGrantedAuthorities(),
        true,
        true,
        true,
        true
      ),
      new ApplicationUser(
        "linda",
        passwordEncoder.encode("password"),
        ADMIN.getGrantedAuthorities(),
        true,
        true,
        true,
        true
      ),
      new ApplicationUser(
        "tom",
        passwordEncoder.encode("password"),
        ADMINTRAINEE.getGrantedAuthorities(),
        true,
        true,
        true,
        true
      )
    );

    return applicationUsers;
  }
}
