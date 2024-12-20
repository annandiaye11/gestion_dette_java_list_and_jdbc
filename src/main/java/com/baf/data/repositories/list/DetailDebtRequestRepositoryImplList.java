package com.baf.data.repositories.list;

import java.util.List;

import com.baf.data.entities.DetailDebtRequest;
import com.baf.data.repositories.DetailDebtRequestRepository;

public class DetailDebtRequestRepositoryImplList extends RepositoryImplList<DetailDebtRequest> implements DetailDebtRequestRepository {

   

    @Override
    public DetailDebtRequest getDetailDebtById(int idDetailDebt) {
        for (DetailDebtRequest detailDebt : data) {
            if (detailDebt.getId() == idDetailDebt){
                return detailDebt;
            }
        }
        return null;
    }

    @Override
    public List<DetailDebtRequest> selectAllByArticleId(int idArticle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAllByArticleId'");
    }

    @Override
    public List<DetailDebtRequest> selectAllByDebtRequestId(int idArticle) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAllByDebtRequestId'");
    }

    
}
