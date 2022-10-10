package com.diplomski.obavestavanje.nastavnika.Controller;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.ApplicationUserSecond;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.Service.ApplicationUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

  private final ApplicationUserService applicationUserService;

  @GetMapping("/users")
  public ResponseEntity<List<ApplicationUserSecond>> getUsers() {
    return ResponseEntity.ok().body(applicationUserService.getUsers());
  }
  @PostMapping("/user/save")
  public ResponseEntity<ApplicationUserSecond> saveUsers(@RequestBody ApplicationUserSecond applicationUser) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/user/save").toUriString());
    return ResponseEntity.ok().body(applicationUserService.saveUser(applicationUser));
  }
  @PostMapping("/role/save")
  public ResponseEntity<Role> saveRole(@RequestBody Role role) {
    URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/role/save").toUriString());
    return ResponseEntity.created(uri).body(applicationUserService.saveRole(role));
  }
  @PostMapping("/role/addtouser")
  public ResponseEntity<?> getUsers(@RequestBody RoleToUserForm roleToUserForm) {
    applicationUserService.addRoleToUser(roleToUserForm.getRolename(), roleToUserForm.getUsername());
    return ResponseEntity.ok().build();
  }
}
@Data
class RoleToUserForm{
  private String username;
  private String rolename;
}
