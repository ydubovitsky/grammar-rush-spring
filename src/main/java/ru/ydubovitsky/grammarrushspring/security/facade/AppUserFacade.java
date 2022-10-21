package ru.ydubovitsky.grammarrushspring.security.facade;

import ru.ydubovitsky.grammarrushspring.security.model.AppUser;
import ru.ydubovitsky.grammarrushspring.security.requests.AppUserRegisterRequestDto;

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
