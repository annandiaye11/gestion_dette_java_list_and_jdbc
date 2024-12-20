package com.baf.data.repositories;

import com.baf.core.Repository;
import com.baf.data.entities.Article;

public interface ArticleRepository  extends Repository<Article>{
    Article selectByLibelle(String libelle);
    Article selectById(int id);

}
