package ru.ydubovitsky.grammarrushspring.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.grammarrushspring.entity.Theme;
import ru.ydubovitsky.grammarrushspring.repository.ThemeRepository;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ThemeServiceDatabaseImpl implements ThemeService {

    private final ThemeRepository themeRepository;

    @Override
    public Theme addNewTheme(Theme theme) {
        Theme savedTheme = themeRepository.save(theme);
        log.info(String.format("Theme %s - saved", theme.getName()));
        return savedTheme;
    }

    @Override
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @Override
    public Theme findByName(String name) {
        return themeRepository.findByName(name);
    }
}
