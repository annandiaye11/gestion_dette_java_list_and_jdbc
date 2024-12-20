package com.baf.services;

import com.baf.core.Service;
import com.baf.data.entities.Article;

public interface ArticleService extends Service<Article>  {
    Article selectByLibelle(String libelle);
    Article selectById(int id);


}
