package com.diplomski.obavestavanje.nastavnika.config;


import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.service.AppUserService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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

            appUserService.saveUser(new AppUser(null, "ilija", "Kosanin", "ilija@gmail.com", "televizor123", "ilija@gmail.com", new ArrayList<>()));
            appUserService.saveUser(new AppUser(null, "Dušan", "Savić", "dusan.savic@fon.bg.ac.rs", "televizor123", "dusan.savic@fon.bg.ac.rs", new ArrayList<>()));
            appUserService.saveUser(new AppUser(null, "Tatjana", "Stojanović", "tatjana.stojanovic@fon.bg.ac.rs", "123", "tatjana.stojanovic@fon.bg.ac.rs", new ArrayList<>()));
            appUserService.saveUser(new AppUser(null, "Ivan", "Milenković", "ivan.milenković@fon.bg.ac.rs", "123", "ivan.milenković@fon.bg.ac.rs", new ArrayList<>()));

            appUserService.addRoleToUser("ilija@gmail.com", "ROLE_ADMIN");
            appUserService.addRoleToUser("dusan.savic@fon.bg.ac.rs", "ROLE_PROFESSOR");
            appUserService.addRoleToUser("tatjana.stojanovic@fon.bg.ac.rs", "ROLE_PROFESSOR");
            appUserService.addRoleToUser("ivan.milenković@fon.bg.ac.rs", "RROLE_PROFESSOR");
            List<String> roles = Arrays.asList("ROLE_ADMIN", "ROLE_PROFESSOR", "ROLE_STUDENT");
//            Random random = new Random();
//            for (int i = 1; i <= 20; i++) {
//                String username = "user" + i;
//                String password = "password" + i;
//                String firstName = "firstName" + i;
//                String lastName = "lastName" + i;
//                String email = username + "@example.com";
//
//                AppUser user = new AppUser(null, firstName, lastName, username, password, email, new ArrayList<>());
//                appUserService.saveUser(user);
//
//                int randomIndex = random.nextInt(roles.size());
//                String randomRole = roles.get(randomIndex);
//                log.info("Role" + randomRole);
//                appUserService.addRoleToUser(username, randomRole);
//            }
        };
    }
    @Bean
    @Primary
    ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }
}
