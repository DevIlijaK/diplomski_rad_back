package com.diplomski.obavestavanje.nastavnika.Service;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.ApplicationUserSecond;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;

import java.util.List;

public interface ApplicationUserService {
  ApplicationUserSecond saveUser (ApplicationUserSecond user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  ApplicationUserSecond getUser(String username);
  List<ApplicationUserSecond> getUsers();
}
