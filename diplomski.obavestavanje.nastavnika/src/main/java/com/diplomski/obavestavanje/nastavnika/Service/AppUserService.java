package com.diplomski.obavestavanje.nastavnika.Service;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;

import java.util.List;

public interface AppUserService {
  AppUser saveUser (AppUser user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  AppUser getUser(String username);
  List<AppUser> getUsers();
}
