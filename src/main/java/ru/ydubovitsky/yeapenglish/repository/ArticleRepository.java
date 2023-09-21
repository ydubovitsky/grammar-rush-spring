package ru.ydubovitsky.yeapenglish.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.ydubovitsky.yeapenglish.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends PagingAndSortingRepository<Article, Short> {

    Optional<List<Article>> findAllByTitleContainsIgnoreCase(String text);

}
