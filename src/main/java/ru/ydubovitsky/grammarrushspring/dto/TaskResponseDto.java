package ru.ydubovitsky.grammarrushspring.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponseDto {

    private Long id;
    private String task;
    private String answer;

}
