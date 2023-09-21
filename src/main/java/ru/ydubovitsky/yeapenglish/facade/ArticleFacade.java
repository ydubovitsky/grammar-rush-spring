package ru.ydubovitsky.yeapenglish.facade;

import ru.ydubovitsky.yeapenglish.dto.ArticleRequestDTO;
import ru.ydubovitsky.yeapenglish.dto.ArticleResponseDTO;
import ru.ydubovitsky.yeapenglish.entity.Article;

public class ArticleFacade {

    public static Article articleRequestDTOtoArticle(ArticleRequestDTO articleRequestDTO) {
        return Article.builder()
                .description(articleRequestDTO.description)
                .image(articleRequestDTO.image)
                .text(articleRequestDTO.text)
                .title(articleRequestDTO.title)
                .build();
    }

    public static ArticleResponseDTO articleToArticleResponseDTO(Article article) {
        return ArticleResponseDTO.builder()
                .id(article.getId())
                .description(article.getDescription())
                .image(article.getImage())
                .text(article.getText())
                .title(article.getTitle())
                .build();
    }

}
