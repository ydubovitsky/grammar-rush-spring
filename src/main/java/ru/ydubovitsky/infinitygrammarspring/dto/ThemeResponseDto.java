package ru.ydubovitsky.infinitygrammarspring.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThemeResponseDto {

    private Short id;
    private String name;
    private List<TaskResponseDto> taskList;

}
