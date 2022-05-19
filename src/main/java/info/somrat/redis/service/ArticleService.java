package info.somrat.redis.service;

import info.somrat.redis.entity.Article;
import info.somrat.redis.respository.ArticleRepository;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class ArticleService implements ArticleServiceInterface {
    @Autowired
    private ArticleRepository articleRepository;

    @Override
    @Cacheable(value= "articleCache", key= "#articleId")
    public Article getArticleById(long articleId) {
        System.out.println("--- Inside getArticleById() ---");
        return articleRepository.findById(articleId).get();
    }
    @Override
    @Cacheable(value= "allArticlesCache", unless= "#result.size() == 0")
    public List<Article> getAllArticles(){
        System.out.println("--- Inside getAllArticles() ---");
        return articleRepository.findAll();
    }
    @Override
    @Caching(
        put= { @CachePut(value= "articleCache", key= "#article.id") },
        evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
    )
    public Article addArticle(Article article){
        System.out.println("--- Inside addArticle() ---");
        return articleRepository.save(article);
    }
    @Override
    @Caching(
        put= { @CachePut(value= "articleCache", key= "#article.id") },
        evict= { @CacheEvict(value= "allArticlesCache", allEntries= true) }
    )
    public Article updateArticle(Article article) {
        System.out.println("--- Inside updateArticle() ---");
        return articleRepository.save(article);
    }
    @Override
    @Caching(
        evict= {
            @CacheEvict(value= "articleCache", key= "#id"),
            @CacheEvict(value= "allArticlesCache", allEntries= true)
        }
    )
    public void deleteArticle(long id) {
        System.out.println("--- Inside deleteArticle() ---");
        articleRepository.delete(articleRepository.findById(id).get());
    }
}
