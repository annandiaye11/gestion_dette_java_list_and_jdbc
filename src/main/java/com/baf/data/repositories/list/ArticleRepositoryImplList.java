package com.baf.data.repositories.list;

import com.baf.data.entities.Article;
import com.baf.data.repositories.ArticleRepository;

public class ArticleRepositoryImplList extends RepositoryImplList<Article> implements ArticleRepository {

    @Override
    public Article selectByLibelle(String libelle) {
        for (Article article : data) {
            
            if (article.getLibelle().equals(libelle.trim())){
                return article;
            }
        }
        return null;
    }

    @Override
    public Article selectById(int id) {
        for (Article article : data) {
            if (article.getId() == id){
                return article;
            }
        }
        return null;
    }
    
}
