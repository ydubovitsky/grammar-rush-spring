package ru.ydubovitsky.grammarrushspring.facade;

import ru.ydubovitsky.grammarrushspring.dto.ThemeRequestDto;
import ru.ydubovitsky.grammarrushspring.dto.ThemeResponseDto;
import ru.ydubovitsky.grammarrushspring.entity.Theme;

import java.util.stream.Collectors;

public class ThemeFacade {

    public static Theme themeRequestDtoToTheme(ThemeRequestDto themeRequestDto) {
        return Theme.builder()
                .name(themeRequestDto.getName())
                .build();
    }

    public static ThemeResponseDto themeToThemeResponseDto(Theme theme) {
        return ThemeResponseDto.builder()
                .id(theme.getId())
                .name(theme.getName())
                .taskList(theme.getTaskList()
                        .stream()
                        .map(task -> TaskFacade.taskToTaskResponseDto(task))
                        .collect(Collectors.toList()))
                .build();
    }

}
