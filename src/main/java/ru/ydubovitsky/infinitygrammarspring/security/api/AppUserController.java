package ru.ydubovitsky.infinitygrammarspring.security.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ydubovitsky.infinitygrammarspring.security.facade.AppUserFacade;
import ru.ydubovitsky.infinitygrammarspring.security.model.AppUser;
import ru.ydubovitsky.infinitygrammarspring.security.requests.AppUserRegisterRequestDto;
import ru.ydubovitsky.infinitygrammarspring.security.service.AppUserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/appuser")
public class AppUserController {

    private final AppUserService appUserService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> registerNewAppUser(@RequestBody AppUserRegisterRequestDto user) {
        AppUser registeredUser = appUserService.registerNewAppUser(AppUserFacade.appUserRegisterRequestDtoToAppUser(user));
        return ResponseEntity.ok(registeredUser);
    }

}
