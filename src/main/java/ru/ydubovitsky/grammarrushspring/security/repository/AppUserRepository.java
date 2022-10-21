package ru.ydubovitsky.grammarrushspring.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.grammarrushspring.security.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    Optional<AppUser> findByUsername(String username);
}
