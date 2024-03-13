package org.example.exservice.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.exservice.Api.ApiResponse;
import org.example.exservice.Model.NewsArticle;
import org.example.exservice.Service.NewsArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/news")
@RequiredArgsConstructor
public class NewsArticleController {


    private final NewsArticleService newsArticleService;


    @GetMapping("/get")
    public ResponseEntity getAllNewsArticles() {
        List<NewsArticle> newsArticles=newsArticleService.getNewsArticles();
        return ResponseEntity.status(200).body(newsArticles);
    }



    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new ApiResponse(message));
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(200).body(new ApiResponse("News Article added"));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable int id, @RequestBody @Valid NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.badRequest().body(new ApiResponse(message));
        }
        boolean isUpdated = newsArticleService.updateNewsArticle(id, newsArticle);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("News Article updated"));
        }
        return ResponseEntity.status(400).body("News Article not found");
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBlog(@PathVariable int id) {
        boolean isDeleted = newsArticleService.deleteNewsArticle(id);

        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("news deleted."));
        }
        return ResponseEntity.status(400).body("news not found.");
    }



    @PostMapping("/publish/{id}")
    public ResponseEntity publishNewsArticle(@PathVariable int id) {
        boolean isPublished = newsArticleService.publishNewsArticle(id);
        if (isPublished) {
            return ResponseEntity.status(200).body(new ApiResponse("News Article published"));
        }
        return ResponseEntity.status(400).body("News Article not found");
    }


    @GetMapping("/published")
    public ResponseEntity<List<NewsArticle>> getAllPublishedNewsArticles() {
        List<NewsArticle> publishedNewsArticles = newsArticleService.getAllPublishedNewsArticles();
        return ResponseEntity.status(200).body(publishedNewsArticles);
    }



    @GetMapping("/byCategory/{category}")
    public ResponseEntity<List<NewsArticle>> getNewsArticlesByCategory(@PathVariable String category) {
        List<NewsArticle> newsArticlesByCategory = newsArticleService.getNewsArticlesByCategory(category);
        return ResponseEntity.status(200).body(newsArticlesByCategory);
    }
}

