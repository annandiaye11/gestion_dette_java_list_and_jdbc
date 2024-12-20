package com.baf.services.impl;

import java.util.List;

import com.baf.data.entities.Article;
import com.baf.data.repositories.ArticleRepository;
import com.baf.services.ArticleService;

public class ArticleServiceImpl  implements ArticleService{

    private ArticleRepository articleRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository){
        this.articleRepository = articleRepository;

    }

    @Override
    public Article selectByLibelle(String libelle) {
        return articleRepository.selectByLibelle(libelle);
    }

    @Override
    public List<Article> selectAll() {
        return articleRepository.selectAll();
    }

    @Override
    public void insert(Article data) {
        articleRepository.insert(data);
    }

    @Override
    public Article selectById(int id) {
       return articleRepository.selectById(id);
    }
    
}
