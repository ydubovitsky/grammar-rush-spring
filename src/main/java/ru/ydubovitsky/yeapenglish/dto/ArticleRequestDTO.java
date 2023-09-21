package ru.ydubovitsky.yeapenglish.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleRequestDTO {

    public Short id;
    public String title;
    public String text;
    public String image;
    public String description;

}
