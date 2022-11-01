package ru.ydubovitsky.grammarrushspring.service;

import ru.ydubovitsky.grammarrushspring.entity.Theme;

import java.util.List;

public interface ThemeService {

    Theme addNewTheme(Theme theme);

    List<Theme> getAllThemes();

    Theme findByName(String name);

}
