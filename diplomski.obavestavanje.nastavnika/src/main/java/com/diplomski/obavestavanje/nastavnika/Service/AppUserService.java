package com.diplomski.obavestavanje.nastavnika.Service;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.dto.AppUserDTO;
import com.diplomski.obavestavanje.nastavnika.dto.requests.EditAppUserRequest;
import com.diplomski.obavestavanje.nastavnika.dto.requests.GetAppUsersRequest;
import com.diplomski.obavestavanje.nastavnika.dto.response.AppUsersSearchResponse;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface AppUserService {
  /**
   * Svi mora da se zamene da vracaju DTO ne app Usera
   * @param user
   * @return
   */
  AppUser saveUser (AppUser user);
  Role saveRole(Role role);
  void addRoleToUser(String username, String roleName);
  void updateRolesForUser(String username, Collection<Role> roles) throws IllegalArgumentException;

  AppUser getUser(String username);
  AppUsersSearchResponse getUsers(GetAppUsersRequest getAppUsersRequest);
  List<Role> getAppUserRoles();
  String updateAppUser(EditAppUserRequest editAppUserRequest);
}
