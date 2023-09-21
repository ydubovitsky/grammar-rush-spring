package ru.ydubovitsky.yeapenglish.security.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ydubovitsky.yeapenglish.security.facade.AppUserFacade;
import ru.ydubovitsky.yeapenglish.security.model.AppUser;
import ru.ydubovitsky.yeapenglish.security.requests.AppUserRegisterRequestDto;
import ru.ydubovitsky.yeapenglish.security.service.AppUserService;

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
