package com.diplomski.obavestavanje.nastavnika.security;

import com.diplomski.obavestavanje.nastavnika.security.filter.CustomAuthenticationFilter;
import com.diplomski.obavestavanje.nastavnika.security.filter.CustomAuthorizationFilter;
import com.diplomski.obavestavanje.nastavnika.service.Implementation.AppUserServiceImpl;
import com.diplomski.obavestavanje.nastavnika.service.Implementation.JWTTokenGenerateServiceImpl;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtSecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
    /**
     * Nisam siguran za ovaj Bean
     */
    private final PasswordEncoder passwordEncoder;
    private final AppUserServiceImpl appUserService;
    //  private final JwtConfig jwtConfig;
    private final JwtSecretKey jwtSecretKey;
    private final JWTTokenGenerateServiceImpl jwtTokenGenerateService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), jwtSecretKey, appUserService, jwtTokenGenerateService);
        customAuthenticationFilter.setFilterProcessesUrl("/api/user/login");

        http.cors().and().csrf().disable();
        http.headers().frameOptions().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(jwtSecretKey), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(appUserService).passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
