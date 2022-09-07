package com.diplomski.obavestavanje.nastavnika.Security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ApplicationUserPermission {
  PROFESSOR_READ("professor:read");

  private final String permission;
}
