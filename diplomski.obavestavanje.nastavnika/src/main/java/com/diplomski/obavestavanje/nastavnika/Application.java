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
}
