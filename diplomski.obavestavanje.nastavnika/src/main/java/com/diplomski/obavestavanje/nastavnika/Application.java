package com.diplomski.obavestavanje.nastavnika;

import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.AppUser;
import com.diplomski.obavestavanje.nastavnika.Model.ApplicationUser.Role;
import com.diplomski.obavestavanje.nastavnika.Service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.ArrayList;

@SpringBootApplication
@EnableScheduling
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
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
