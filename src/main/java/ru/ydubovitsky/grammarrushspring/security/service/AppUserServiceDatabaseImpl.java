package ru.ydubovitsky.grammarrushspring.security.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.ydubovitsky.grammarrushspring.security.model.AppUser;
import ru.ydubovitsky.grammarrushspring.security.model.roles.RolesEnum;
import ru.ydubovitsky.grammarrushspring.security.repository.AppUserRepository;

import java.util.Set;

@Primary
@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class AppUserServiceDatabaseImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getAppUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException(String.format("User with name %s not found", username)));
        return appUser;
    }

    @Override
    public AppUser registerNewAppUser(AppUser appUser) {
        appUser.setRoles(Set.of(RolesEnum.USER));
        appUser.setIsAccountNonExpired(true);
        appUser.setIsAccountNonLocked(true);
        appUser.setIsCredentialsNonExpired(true);
        appUser.setIsEnabled(true);
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        //! Save user
        AppUser savedUser = appUserRepository.save(appUser);
        //! Logging
        log.info(String.format("User with name %s saved", appUser.getUsername()));

        return savedUser;
    }
}
