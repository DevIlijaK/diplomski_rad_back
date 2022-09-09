package com.diplomski.obavestavanje.nastavnika.Security;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum ApplicationUserRole {
  STUDENT(Sets.newHashSet()),
  ADMIN(Sets.newHashSet(ApplicationUserPermission.COURSE_READ, ApplicationUserPermission.COURSE_WRITE, ApplicationUserPermission.STUDENT_READ, ApplicationUserPermission.STUDENT_WRITE)),
  ADMINTRAINEE(Sets.newHashSet(ApplicationUserPermission.COURSE_READ, ApplicationUserPermission.STUDENT_READ));

  private final Set<ApplicationUserPermission> permissions;

  public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
    Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                                                              .map(applicationUserPermission -> new SimpleGrantedAuthority(applicationUserPermission.getPermission()))
                                                              .collect(Collectors.toSet());
    permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return permissions;
  }
}
