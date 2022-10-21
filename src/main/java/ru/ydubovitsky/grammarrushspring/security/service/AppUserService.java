package ru.ydubovitsky.grammarrushspring.security.service;

import ru.ydubovitsky.grammarrushspring.security.model.AppUser;

public interface AppUserService {

    AppUser getAppUserByUsername(String username);

    AppUser registerNewAppUser(AppUser appUser);

}
