package ru.ydubovitsky.yeapenglish.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.ydubovitsky.yeapenglish.dto.ArticleRequestDTO;
import ru.ydubovitsky.yeapenglish.entity.Article;
import ru.ydubovitsky.yeapenglish.facade.ArticleFacade;
import ru.ydubovitsky.yeapenglish.repository.ArticleRepository;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article addArticle(ArticleRequestDTO articleRequestDTO) {
        Article article = ArticleFacade.articleRequestDTOtoArticle(articleRequestDTO);
        Article savedArticle = articleRepository.save(article);
        log.info(String.format("Article with name: %s - saved", savedArticle.getTitle()));
        return savedArticle;
    }

    //! Case ignores
    public List<Article> searchArticlesByTitle(String text) {
        List<Article> articles = articleRepository.findAllByTitleContainsIgnoreCase(text).orElseThrow(
                () -> new RuntimeException(String.format("Articles with title: %s - not found", text))
        );
        log.info(String.format("%s articles with %s found", articles.size(), text));
        return articles;
    }

    public List<Article> getArticlesPerPageWithSize(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return articleRepository.findAll(pageable).getContent();
    }

    public Article getArticleById(Short id) {
        return articleRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException(String.format("Article with id: %s not found!", id))
                );
    }

    //TODO Переписать метод, уж больно страшный
    public Article updateArticle(ArticleRequestDTO articleRequestDTO) {
        Article articleFromDatabase = articleRepository.findById(articleRequestDTO.getId()).orElseThrow(
                () -> new RuntimeException(String.format("Article with id %s not found for update!", articleRequestDTO.getId())));

        Article article = ArticleFacade.articleRequestDTOtoArticle(articleRequestDTO);

        if(Objects.nonNull(article.getImage())) {
            articleFromDatabase.setImage(article.getImage());
        }
        articleFromDatabase.setDescription(article.getDescription());
        articleFromDatabase.setText(article.getText());
        articleFromDatabase.setTitle(article.getTitle());

        Article updatedArticle = articleRepository.save(articleFromDatabase);
        log.info(String.format("Article with id: %s updated!", updatedArticle.getId()));
        return updatedArticle;
    }

    public void deleteArticleById(Short id) {
        Article articleFromDatabase = articleRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Article with id: %s not found!", id)));
        articleRepository.delete(articleFromDatabase);
        log.info(String.format("Post with id: %s marked liked deleted", id));
    }

}
