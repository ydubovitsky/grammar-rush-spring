package ru.ydubovitsky.grammarrushspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.grammarrushspring.entity.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Short> {

    Theme findByName(String name);

}
