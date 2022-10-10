package com.diplomski.obavestavanje.nastavnika.Service.Implementation;


import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.ApplicationUserSecond;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.Repository.ApplicationUserSecondRepository;
import com.diplomski.obavestavanje.nastavnika.Repository.RoleRepository;
import com.diplomski.obavestavanje.nastavnika.Service.ApplicationUserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ApplicationUserServiceImpl implements ApplicationUserService {

  private final ApplicationUserSecondRepository userSecondRepository;
  private final RoleRepository roleRepository;


  @Override
  public ApplicationUserSecond saveUser(ApplicationUserSecond user) {
    log.info("Saving new user {} to database", user.getName());
    return userSecondRepository.save(user);
  }

  @Override
  public Role saveRole(Role role) {
    log.info("Saving new role {} to database", role.getName());
    return roleRepository.save(role);
  }

  @Override
  public void addRoleToUser(String username, String roleName) {
    log.info("Adding role {} to user {}", roleName, username);
    ApplicationUserSecond user = userSecondRepository.findByUsername(username);
    Role role = roleRepository.findByName(roleName);
    user.getRoles().add(role);
  }

  @Override
  public ApplicationUserSecond getUser(String username) {
    log.info("Fetching user {}", username);
    return userSecondRepository.findByUsername(username);
  }

  @Override
  public List<ApplicationUserSecond> getUsers() {
    log.info("Fetching all users");
    return userSecondRepository.findAll();
  }
}
