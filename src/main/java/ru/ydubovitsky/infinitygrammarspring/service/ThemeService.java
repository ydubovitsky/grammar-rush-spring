package ru.ydubovitsky.infinitygrammarspring.service;

import ru.ydubovitsky.infinitygrammarspring.entity.Theme;

import java.util.List;

public interface ThemeService {

    Theme addNewTheme(Theme theme);

    List<Theme> getAllThemes();

    Theme findByName(String name);

}
