package ru.ydubovitsky.infinitygrammarspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ydubovitsky.infinitygrammarspring.entity.Theme;

public interface ThemeRepository extends JpaRepository<Theme, Short> {

    Theme findByName(String name);

}
