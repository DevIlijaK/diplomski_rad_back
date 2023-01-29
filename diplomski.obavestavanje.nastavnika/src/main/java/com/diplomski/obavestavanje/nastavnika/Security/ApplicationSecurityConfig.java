package com.diplomski.obavestavanje.nastavnika.Security;

import com.diplomski.obavestavanje.nastavnika.Security.filter.CustomAuthenticationFilter;
import com.diplomski.obavestavanje.nastavnika.Security.filter.CustomAuthorizationFilter;
import com.diplomski.obavestavanje.nastavnika.Security.filter.CustomCorsFilter;
import com.diplomski.obavestavanje.nastavnika.Service.Implementation.AppUserServiceImpl;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtSecretKey;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;

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
  @Autowired
  private CustomCorsFilter customCorsFilter;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), jwtSecretKey);
    customAuthenticationFilter.setFilterProcessesUrl("/api/user/login");

    http.cors().and().csrf().disable();
    http.addFilterBefore(customCorsFilter, SessionManagementFilter.class);
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    http.authorizeRequests().antMatchers("/api/login/**", "/api/user/token/refresh/**").permitAll();
//    http.authorizeRequests().antMatchers("/api/user/**").hasAuthority("ROLE_ADMIN");
    http.authorizeRequests().anyRequest().authenticated();
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
