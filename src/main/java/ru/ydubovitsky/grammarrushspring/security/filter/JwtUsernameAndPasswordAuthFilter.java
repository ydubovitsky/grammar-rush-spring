package ru.ydubovitsky.grammarrushspring.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.ydubovitsky.grammarrushspring.security.config.JwtConfig;
import ru.ydubovitsky.grammarrushspring.security.model.AppUser;
import ru.ydubovitsky.grammarrushspring.security.requests.AppUserResponseDto;
import ru.ydubovitsky.grammarrushspring.security.requests.UsernameAndPasswordAuthRequest;
import ru.ydubovitsky.grammarrushspring.security.service.AppUserService;


import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.stream.Collectors;

public class JwtUsernameAndPasswordAuthFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final AppUserService appUserService;
    private final JwtConfig jwtConfig;

    public JwtUsernameAndPasswordAuthFilter(
            AuthenticationManager authenticationManager,
            AppUserService appUserService,
            JwtConfig jwtConfig
            ) {
        this.authenticationManager = authenticationManager;
        this.appUserService = appUserService;
        this.jwtConfig = jwtConfig;
    }

    @Override
    public Authentication attemptAuthentication(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws AuthenticationException {
        try {
            UsernameAndPasswordAuthRequest req = new ObjectMapper()
                    .readValue(request.getInputStream(), UsernameAndPasswordAuthRequest.class);

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    req.getUsername(),
                    req.getPassword(),
                    null);

            return authenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult
    ) throws IOException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(4)))
                .signWith(Keys.hmacShaKeyFor(jwtConfig.getSecurityKey().getBytes()))
                .compact();

        //TODO Вынести в отдельный метод?
        response.resetBuffer();
        response.setStatus(HttpStatus.OK.value());
        response.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
        response.getWriter().print(
                new ObjectMapper().writeValueAsString(getAuthResponse(authResult, jwtConfig.getTokenPrefix() + token))
        );
        response.flushBuffer();
    }

    private AppUserResponseDto getAuthResponse(Authentication authResult, String jwttoken) {
        AppUser appUser = appUserService.getAppUserByUsername(authResult.getName());

        return AppUserResponseDto.builder()
                .jwttoken(jwttoken)
                .id(appUser.getId())
                .username(appUser.getUsername())
                .roles(appUser.getRoles().stream().map(role -> role.name()).collect(Collectors.toSet()))
                .build();
    }
}

