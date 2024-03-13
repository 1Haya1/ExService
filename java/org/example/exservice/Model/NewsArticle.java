package org.example.exservice.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
public class NewsArticle {
    @NotNull(message = "cant be null")
    private int id;

    @NotEmpty(message = "cant be empty")
    private String title;

    @NotNull(message = "cant be null")
    @Size(min = 5, max = 20)
    private String author;

    @NotNull(message = "cant be null")
    @Size(min = 200)
    private String content;

    @NotNull(message = "cant be null")
    @Pattern(regexp = "^(politics|sports|technology)$", message = "Category must be either 'politics', 'sports', or 'technology'.")
    private String category;


    @NotNull(message = "cant be null")
    private String imageUrl;

    private boolean isPublished = false;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate=null;


}
