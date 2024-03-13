package org.example.exservice.Service;

import org.example.exservice.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class NewsArticleService {

    List<NewsArticle> newsArticles=new ArrayList<>();

    public List<NewsArticle> getNewsArticles(){
        return newsArticles;
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        newsArticles.add(newsArticle);
    }


    public boolean updateNewsArticle(int id, NewsArticle updatedArticle) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId()== id) {
                newsArticles.set(i, updatedArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticle(int id) {
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId()== id) {
                newsArticles.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean publishNewsArticle(int id) {
        for (NewsArticle article : newsArticles) {
            if (article.getId() == (id)) {
                article.setPublished(true);
                article.setPublishDate(new Date());
                return true;
            }
        }
        return false;
    }

    public List<NewsArticle> getAllPublishedNewsArticles() {
        List<NewsArticle> publishedNewsArticles = new ArrayList<>();
        for (NewsArticle article : newsArticles) {
            if (article.isPublished()) {
                publishedNewsArticles.add(article);
            }
        }
        return publishedNewsArticles;
    }

    public  List<NewsArticle> getNewsArticlesByCategory(String category) {
        List<NewsArticle> articlesByCategory = new ArrayList<>();
        for (NewsArticle article : newsArticles) {
            if (article.getCategory().equalsIgnoreCase(category)) {
                articlesByCategory.add(article);
            }
        }
        return articlesByCategory;
    }
}


