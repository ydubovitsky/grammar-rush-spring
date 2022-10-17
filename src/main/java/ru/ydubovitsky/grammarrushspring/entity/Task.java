package ru.ydubovitsky.grammarrushspring.entity;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String task;

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Theme theme;
}
