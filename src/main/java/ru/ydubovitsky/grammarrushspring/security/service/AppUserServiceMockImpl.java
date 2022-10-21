package ru.ydubovitsky.grammarrushspring.security.service;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.grammarrushspring.security.model.AppUser;
import ru.ydubovitsky.grammarrushspring.security.model.roles.RolesEnum;

import java.util.Set;

@Service
@AllArgsConstructor
public class AppUserServiceMockImpl implements AppUserService {

    private final PasswordEncoder passwordEncoder;

    @Override
    public AppUser getAppUserByUsername(String username) {
        if (!username.equals("u")) throw new RuntimeException("user not found");

        return AppUser.builder()
                .id(1)
                .roles(Set.of(RolesEnum.USER, RolesEnum.GUEST, RolesEnum.ADMIN, RolesEnum.OWNER))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .isAccountNonLocked(true)
                .isAccountNonExpired(true)
                .username("u")
                .password(passwordEncoder.encode("u"))
                .password2(passwordEncoder.encode("u"))
                .build();
    }

    @Override
    public AppUser registerNewAppUser(AppUser appUser) {
        //TODO Write me!
        return null;
    }
}
