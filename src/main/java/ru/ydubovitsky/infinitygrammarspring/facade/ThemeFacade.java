package ru.ydubovitsky.infinitygrammarspring.facade;

import ru.ydubovitsky.infinitygrammarspring.dto.ThemeRequestDto;
import ru.ydubovitsky.infinitygrammarspring.dto.ThemeResponseDto;
import ru.ydubovitsky.infinitygrammarspring.entity.Theme;

import java.util.stream.Collectors;

public class ThemeFacade {

    public static Theme themeRequestDtoToTheme(ThemeRequestDto themeRequestDto) {
        return Theme.builder()
                .themeName(themeRequestDto.getThemeName())
                .build();
    }

    public static ThemeResponseDto themeToThemeResponseDto(Theme theme) {
        return ThemeResponseDto.builder()
                .id(theme.getId())
                .themeName(theme.getThemeName())
                .taskList(theme.getTaskList()
                        .stream()
                        .map(task -> TaskFacade.taskToTaskResponseDto(task))
                        .collect(Collectors.toList()))
                .build();
    }

}
