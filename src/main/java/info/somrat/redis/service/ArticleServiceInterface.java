package info.somrat.redis.service;
import info.somrat.redis.entity.Article;
import java.util.List;

public interface ArticleServiceInterface {
    List<Article> getAllArticles();
    Article getArticleById(long articleId);
    Article addArticle(Article article);
    Article updateArticle(Article article);
    void deleteArticle(long articleId);
}
