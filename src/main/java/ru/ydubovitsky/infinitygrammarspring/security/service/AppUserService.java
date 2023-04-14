package ru.ydubovitsky.infinitygrammarspring.security.service;

import ru.ydubovitsky.infinitygrammarspring.security.model.AppUser;

public interface AppUserService {

    AppUser getAppUserByUsername(String username);

    AppUser registerNewAppUser(AppUser appUser);

}
