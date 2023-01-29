package com.diplomski.obavestavanje.nastavnika.config;


import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.Service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

@Configuration
public class BeanConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    CommandLineRunner run(AppUserService appUserService) {
        return args -> {
            appUserService.saveRole(new Role(null, "ROLE_ADMIN"));
            appUserService.saveRole(new Role(null, "ROLE_PROFESSOR"));
            appUserService.saveRole(new Role(null, "ROLE_STUDENT"));

            appUserService.saveUser(new AppUser(null, "ILija", "123", "123", new ArrayList<>()));
            appUserService.saveUser(new AppUser(null, "asd", "qwe", "qwe", new ArrayList<>()));
            appUserService.saveUser(new AppUser(null, "zxc", "asd", "asd", new ArrayList<>()));
            appUserService.saveUser(new AppUser(null, "123", "zxc", "zxc", new ArrayList<>()));

            appUserService.addRoleToUser("123", "ROLE_ADMIN");
        };
    }
}
