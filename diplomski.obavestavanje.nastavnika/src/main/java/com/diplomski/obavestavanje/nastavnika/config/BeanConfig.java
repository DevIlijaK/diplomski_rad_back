package com.diplomski.obavestavanje.nastavnika.config;


import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.Service.AppUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Configuration
@Slf4j
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

            appUserService.saveUser(new AppUser(null, "asd", "asd", "asd", "asd", "asd", new ArrayList<>()));

            appUserService.addRoleToUser("asd", "ROLE_ADMIN");
            List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_PROFESSOR", "ROLE_STUDENT");
            Random random = new Random();
            for (int i = 1; i <= 20; i++) {
                String username = "user" + i;
                String password = "password" + i;
                String firstName = "firstName" + i;
                String lastName = "lastName" + i;
                String email = username + "@example.com";

                AppUser user = new AppUser(null, firstName, lastName, username, password, email, new ArrayList<>());
                appUserService.saveUser(user);

                int randomIndex = random.nextInt(roles.size());
                String randomRole = roles.get(randomIndex);
                log.info("Role" + randomRole);
                appUserService.addRoleToUser(username, randomRole);
            }
        };
    }
}
