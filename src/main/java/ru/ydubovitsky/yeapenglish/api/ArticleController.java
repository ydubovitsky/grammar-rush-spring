package ru.ydubovitsky.yeapenglish.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.ydubovitsky.yeapenglish.dto.ArticleRequestDTO;
import ru.ydubovitsky.yeapenglish.dto.ArticleResponseDTO;
import ru.ydubovitsky.yeapenglish.entity.Article;
import ru.ydubovitsky.yeapenglish.facade.ArticleFacade;
import ru.ydubovitsky.yeapenglish.service.ArticleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/article")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<ArticleResponseDTO>> getArticleByPage(
            @RequestParam(name = "page") Integer page,
            @RequestParam(name = "size") Integer size
    ) {
        List<Article> articlesPerPageWithSize = articleService.getArticlesPerPageWithSize(page, size);
        List<ArticleResponseDTO> response = articlesPerPageWithSize
                .stream()
                .map(ArticleFacade::articleToArticleResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping(params = "id")
    public ResponseEntity<ArticleResponseDTO> getArticleById(@RequestParam(name = "id") Short id) {
        Article articleById = articleService.getArticleById(id);
        return ResponseEntity.ok(ArticleFacade.articleToArticleResponseDTO(articleById));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public void addArticle(@RequestBody ArticleRequestDTO articleRequestDTO) {
        articleService.addArticle(articleRequestDTO);
    }

    @PutMapping()
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void updateArticle(@RequestBody ArticleRequestDTO articleRequestDTO) {
        articleService.updateArticle(articleRequestDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'SUPER_ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteArticle(@PathVariable Short id) {
        articleService.deleteArticleById(id);
    }

    @GetMapping(value = "/search", params = "title")
    public ResponseEntity<List<ArticleResponseDTO>> findArticleByTitle(@RequestParam(name = "title") String title) {
        List<ArticleResponseDTO> response = articleService.searchArticlesByTitle(title)
                .stream()
                .map(article -> ArticleFacade.articleToArticleResponseDTO(article))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
