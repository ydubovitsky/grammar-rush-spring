package ru.ydubovitsky.grammarrushspring.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import java.util.ArrayList;
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
