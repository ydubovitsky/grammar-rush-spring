package ru.ydubovitsky.yeapenglish.security.service;

import ru.ydubovitsky.yeapenglish.security.model.AppUser;

public interface AppUserService {

    AppUser getAppUserByUsername(String username);

    AppUser registerNewAppUser(AppUser appUser);

}
