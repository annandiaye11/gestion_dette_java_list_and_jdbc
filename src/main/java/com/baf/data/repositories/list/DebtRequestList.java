package com.baf.data.repositories.list;

import com.baf.data.entities.Client;
import com.baf.data.entities.DebtRequest;
import com.baf.data.repositories.DebtRequestRepository;

public class DebtRequestList extends RepositoryImplList<DebtRequest>  implements DebtRequestRepository {
    // private List<DebtRequest> debtRequests = new ArrayList<>();

  
   @Override
    public DebtRequest getDebtRequestByClient(Client client) {
        for (DebtRequest debtRequest : data) {
            if (debtRequest.getClient().equals(client)) {
                return debtRequest;
            }
        }
        return null;
    }

    public void updateStatus(int idDebtRequest, String status) {
        for (DebtRequest debtRequestCl : data) {
            if (debtRequestCl.getId() == idDebtRequest) {
                debtRequestCl.setStatus(status);
            }
        }
    }

    @Override
    public DebtRequest selectById(int idDebtRequest) {
        for (DebtRequest debtRequest : data) {
            if (debtRequest.getId() == idDebtRequest) {
                return debtRequest;
            }
        }
        return null;
    }

    @Override
    public int insertWithId(DebtRequest data, int $index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }
}
