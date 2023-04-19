package ru.ydubovitsky.infinitygrammarspring.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Theme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(unique = true, nullable = false)
    private String themeName;

    @OneToMany(
            mappedBy = "theme",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Task> taskList = new ArrayList<>();

}
