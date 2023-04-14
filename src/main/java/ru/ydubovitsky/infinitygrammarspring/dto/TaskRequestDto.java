package ru.ydubovitsky.infinitygrammarspring.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskRequestDto {

    private String task;
    private String answer;
    private String hint;
    private String theme;

}
