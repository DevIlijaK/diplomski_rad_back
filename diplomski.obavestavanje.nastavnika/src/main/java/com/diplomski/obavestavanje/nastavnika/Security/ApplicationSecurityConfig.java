package com.diplomski.obavestavanje.nastavnika.Security;

import com.diplomski.obavestavanje.nastavnika.Security.filter.CustomAuthenticationFilter;
import com.diplomski.obavestavanje.nastavnika.Security.filter.CustomAuthorizationFilter;
import com.diplomski.obavestavanje.nastavnika.Service.AppUserService;
import com.diplomski.obavestavanje.nastavnika.Service.Implementation.AppUserServiceImpl;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtSecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


  private final PasswordEncoder passwordEncoder;
  private final AppUserServiceImpl appUserService;
//  private final JwtConfig jwtConfig;
  private final JwtSecretKey jwtSecretKey;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().antMatchers("/api/login/**").permitAll();
    http.authorizeRequests().antMatchers("/api/user/**").hasAuthority("ROLE_ADMIN");
    http.authorizeRequests().anyRequest().authenticated();
    http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean(), jwtSecretKey));
    http.addFilterBefore(new CustomAuthorizationFilter(jwtSecretKey), UsernamePasswordAuthenticationFilter.class)
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


//  @Bean
//  public DaoAuthenticationProvider daoAuthenticationProvider() {
//    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//    provider.setPasswordEncoder(passwordEncoder);
//    provider.setUserDetailsService(applicationUserService);
//    return provider;
//  }
}
