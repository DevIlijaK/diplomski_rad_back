package com.diplomski.obavestavanje.nastavnika.Service.Implementation;


import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.Repository.auth.AppUserRepository;
import com.diplomski.obavestavanje.nastavnika.Repository.auth.RoleRepository;
import com.diplomski.obavestavanje.nastavnika.Service.AppUserService;
import com.diplomski.obavestavanje.nastavnika.dto.AppUserDTO;
import com.diplomski.obavestavanje.nastavnika.dto.requests.EditAppUserRequest;
import com.diplomski.obavestavanje.nastavnika.dto.requests.GetAppUsersRequest;
import com.diplomski.obavestavanje.nastavnika.dto.response.AppUsersSearchResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService, UserDetailsService {
    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByUsername(username);
        if (appUser == null) {
            log.error("User with username {} not found in database", username);
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        appUser.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        });
        return new org.springframework.security.core.userdetails.User(appUser.getUsername(), appUser.getPassword(), authorities);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        log.info("Saving new user {} to database", user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Userrr" + user);
        return appUserRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to database", role.getName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}", roleName, username);
        AppUser user = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        log.info("Roleee: " + role);
        log.info("User: " + user);
        user.getRoles().add(role);
    }

    @Override
    public void updateRolesForUser(String username, Collection<Role> roles) throws IllegalArgumentException {
        AppUser user = appUserRepository.findByUsername(username);
        Set<Role> newRoles = new HashSet<>();

        for (Role role : roles) {
            if (roleRepository.existsByName(role.getName())) {
                newRoles.add(roleRepository.findByName(role.getName()));
            } else {
                log.warn("Role {} does not exist in the database.", role.getName());
            }
        }

        user.getRoles().clear();
        user.getRoles().addAll(newRoles);
        appUserRepository.save(user);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}", username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public AppUsersSearchResponse getUsers(GetAppUsersRequest getAppUsersRequest) {
        log.info("Fetching all users");
        Pageable pageable = PageRequest.of(
                getAppUsersRequest.getPage(),
                getAppUsersRequest.getSize(),
                Sort.by(getAppUsersRequest.getSort()));
        List<AppUserDTO> appUserDTOS = appUserRepository.findAll(pageable).stream()
                .map(appUser -> AppUserDTO.builder()
                        .firstname(appUser.getFirstname())
                        .lastname(appUser.getLastname())
                        .username(appUser.getUsername())
                        .email(appUser.getEmail())
                        .roles(appUser.getRoles())
                        .build())
                .collect(Collectors.toList());
        int total = appUserRepository.findAll().size();
        return AppUsersSearchResponse.builder()
                .appUsers(appUserDTOS)
                .total(total)
                .build();
    }

    @Override
    public List<Role> getAppUserRoles() {
        return roleRepository.findAll();
    }

    @Override
    public String updateAppUser(EditAppUserRequest editAppUserRequest) {
        AppUser existingUser = appUserRepository.findByUsername(editAppUserRequest.getUsername());

        if (existingUser == null) {
            log.error("User with username {} not found in database", editAppUserRequest.getUsername());
            throw new UsernameNotFoundException("User not found in database");
        } else {
            log.info("User found in database");
        }

        // Update the user's information with the new values from the request body
        existingUser.setEmail(editAppUserRequest.getEmail());
        existingUser.setFirstname(editAppUserRequest.getFirstname());
        existingUser.setLastname(editAppUserRequest.getLastname());
        try {
            updateRolesForUser(existingUser.getUsername(), editAppUserRequest.getRoles());
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        // Save the updated user to the database
        AppUser updatedUser = appUserRepository.save(existingUser);

        if (updatedUser == existingUser) {
            return "User updated successfully";
        } else {
            throw new RuntimeException("Failed to update user");
        }
    }
}
