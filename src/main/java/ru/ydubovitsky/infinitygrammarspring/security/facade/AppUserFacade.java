package ru.ydubovitsky.infinitygrammarspring.security.facade;

import ru.ydubovitsky.infinitygrammarspring.security.model.AppUser;
import ru.ydubovitsky.infinitygrammarspring.security.requests.AppUserRegisterRequestDto;

public class AppUserFacade {

    public static AppUser appUserRegisterRequestDtoToAppUser(AppUserRegisterRequestDto appUserRequest) {
        return AppUser.builder()
                .username(appUserRequest.getUsername())
                .password(appUserRequest.getPassword())
                .password2(appUserRequest.getPassword2())
                .email(appUserRequest.getEmail())
                .phone(appUserRequest.getPhone())
                .build();
    }

}
