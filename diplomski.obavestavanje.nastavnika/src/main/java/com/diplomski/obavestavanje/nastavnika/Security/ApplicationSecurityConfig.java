package com.diplomski.obavestavanje.nastavnika.Security;

import com.diplomski.obavestavanje.nastavnika.auth.ApplicationUserService;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtConfig;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtSecretKey;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtTokenVerifier;
import com.diplomski.obavestavanje.nastavnika.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor(onConstructor = @__(@Autowired))
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


  private final PasswordEncoder passwordEncoder;
  private final ApplicationUserService applicationUserService;
  private final JwtConfig jwtConfig;
  private final JwtSecretKey jwtSecretKey;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
                      //                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                      //                .and()
                      .csrf().disable()
                      .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                      .and()
                      .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, jwtSecretKey))
                      .addFilterAfter(new JwtTokenVerifier(jwtSecretKey, jwtConfig), JwtUsernameAndPasswordAuthenticationFilter.class)
                      .authorizeRequests()
                      //                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                      .antMatchers("/api/**").hasRole(ApplicationUserRole.STUDENT.name())
                      .anyRequest()
                      .authenticated();
//      .and()
//      .formLogin()
//      .loginPage("/login").permitAll()
//      .defaultSuccessUrl("/courses", true)
//      .and()
//      .rememberMe()
//      .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
//      .key("somethingverysecured")
//      .and()
//      .logout()
//      .logoutUrl("/logout")
//      .clearAuthentication(true)
//      .invalidateHttpSession(true)
//      .deleteCookies("JSESSIONID", "remember-me")
//      .logoutSuccessUrl("/login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(daoAuthenticationProvider());
  }

  @Bean
  public DaoAuthenticationProvider daoAuthenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder);
    provider.setUserDetailsService(applicationUserService);
    return provider;
  }
}
