package com.baf.data.repositories;


import java.util.List;

import com.baf.core.Repository;
import com.baf.data.entities.DetailDebt;

public interface DetailDebtRepository extends Repository<DetailDebt> {
    DetailDebt getDetailDebtById(int idDetailDebt);
    List<DetailDebt> getAllDetailDebtByArticleId(int idArticle);
    List<DetailDebt> selectAllByDebtId(int idDebt);
}
