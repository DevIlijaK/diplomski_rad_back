package com.diplomski.obavestavanje.nastavnika.Security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;

import java.util.Set;

@AllArgsConstructor
public enum ApplicationUserRole {
  ADMIN(Sets.newHashSet(ApplicationUserPermission.PROFESSOR_READ)),
  PROFESSOR(Sets.newHashSet(ApplicationUserPermission.PROFESSOR_READ));

  private final Set<ApplicationUserPermission> permissions;
}
