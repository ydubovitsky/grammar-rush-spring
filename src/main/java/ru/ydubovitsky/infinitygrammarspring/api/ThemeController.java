package ru.ydubovitsky.infinitygrammarspring.api;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.infinitygrammarspring.dto.ThemeRequestDto;
import ru.ydubovitsky.infinitygrammarspring.dto.ThemeResponseDto;
import ru.ydubovitsky.infinitygrammarspring.entity.Theme;
import ru.ydubovitsky.infinitygrammarspring.facade.ThemeFacade;
import ru.ydubovitsky.infinitygrammarspring.service.ThemeService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/theme")
@AllArgsConstructor
public class ThemeController {

    private final ThemeService themeService;

    @PostMapping("/add")
    public ResponseEntity<?> addNewTheme(@RequestBody ThemeRequestDto theme) {
        Theme savedTheme = themeService.addNewTheme(ThemeFacade.themeRequestDtoToTheme(theme));
        return ResponseEntity.ok(savedTheme);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ThemeResponseDto>> getAllThemes() {
        List<Theme> allThemes = themeService.getAllThemes();
        return ResponseEntity.ok(allThemes
                .stream()
                .map(ThemeFacade::themeToThemeResponseDto)
                .collect(Collectors.toList()));
    }

}
