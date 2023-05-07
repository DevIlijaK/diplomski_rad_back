package com.diplomski.obavestavanje.nastavnika.service;

import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.dto.requests.EditAppUserRequest;
import com.diplomski.obavestavanje.nastavnika.dto.requests.GetAppUsersRequest;
import com.diplomski.obavestavanje.nastavnika.dto.response.AppUsersSearchResponse;

import java.util.Collection;
import java.util.List;

public interface AppUserService {
  /**
   * Svi mora da se zamene da vracaju DTO ne app Usera
   * @param user
   * @return
   */
  String saveUser (AppUser user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  void updateRolesForUser(String username, Collection<Role> roles) throws IllegalArgumentException;

  AppUser getUser(String username);
  AppUsersSearchResponse getUsers(GetAppUsersRequest getAppUsersRequest);
  List<Role> getAppUserRoles();
  String updateAppUser(EditAppUserRequest editAppUserRequest);
}
