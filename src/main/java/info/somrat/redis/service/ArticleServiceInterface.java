package info.somrat.redis.service;
import info.somrat.redis.entity.Article;
import info.somrat.redis.request.ArticleCreateRequest;
import java.util.List;

public interface ArticleServiceInterface {
    List<Article> getAllArticles();
    Article getArticleById(long articleId);
    int addArticle(Article article);
    Article updateArticle(Article article);
    void deleteArticle(long articleId);
}
