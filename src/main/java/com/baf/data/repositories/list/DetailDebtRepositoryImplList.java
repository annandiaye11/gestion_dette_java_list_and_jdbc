package com.baf.data.repositories.list;

import java.util.List;

import com.baf.data.entities.DetailDebt;
import com.baf.data.repositories.DetailDebtRepository;

public class DetailDebtRepositoryImplList extends RepositoryImplList<DetailDebt> implements DetailDebtRepository {

   

    @Override
    public DetailDebt getDetailDebtById(int idDetailDebt) {
        for (DetailDebt detailDebt : data) {
            if (detailDebt.getId() == idDetailDebt){
                return detailDebt;
            }
        }
        return null;
    }

    @Override
    public List<DetailDebt> getAllDetailDebtByArticleId(int idArticle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllDetailDebtByArticleId'");
    }

    @Override
    public List<DetailDebt> selectAllByDebtId(int idDebt) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAllByDebtId'");
    }

    

    
}
