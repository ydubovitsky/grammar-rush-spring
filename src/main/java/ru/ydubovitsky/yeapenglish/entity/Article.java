package ru.ydubovitsky.yeapenglish.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;
    @Column(columnDefinition="TEXT")
    private String title;
    private String description;
    private String image;
    @Column(columnDefinition="TEXT")
    private String text;

}
