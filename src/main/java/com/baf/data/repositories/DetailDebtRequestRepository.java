package com.baf.data.repositories;

import java.util.List;

import com.baf.core.Repository;
import com.baf.data.entities.DetailDebtRequest;

public interface DetailDebtRequestRepository extends Repository<DetailDebtRequest> {
    DetailDebtRequest getDetailDebtById(int idDetailDebt);
    List<DetailDebtRequest> selectAllByArticleId(int idArticle);
    List<DetailDebtRequest> selectAllByDebtRequestId(int idArticle);
} 
