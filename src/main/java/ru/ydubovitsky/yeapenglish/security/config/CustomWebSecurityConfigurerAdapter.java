package ru.ydubovitsky.yeapenglish.security.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.ydubovitsky.yeapenglish.security.filter.JwtUsernameAndPasswordAuthFilter;
import ru.ydubovitsky.yeapenglish.security.filter.JwtVerifierFilter;
import ru.ydubovitsky.yeapenglish.security.service.AppUserService;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    private final AppUserService appUserService;
    private final JwtConfig jwtConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager(), appUserService, jwtConfig));
        http.addFilterAfter(new JwtVerifierFilter(jwtConfig), JwtUsernameAndPasswordAuthFilter.class);
    }
}
